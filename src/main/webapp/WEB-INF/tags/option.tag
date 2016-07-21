<%@ tag pageEncoding="UTF-8" description="生成下拉框项"%>
<%@ tag import="org.slf4j.Logger,org.slf4j.LoggerFactory"%>
<%@ tag import="com.wxcommon.util.*,com.wxcommon.repository.SpringDao"%>
<%@ tag import="java.util.Map,org.apache.commons.beanutils.BeanUtils"%>
<%@ tag import="org.springframework.web.context.WebApplicationContext"%>
<%@ tag import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ attribute name="data" required="true" rtexprvalue="true" description="数据源"%>
<%@ attribute name="selected" rtexprvalue="true" description="选中项(多项以delimiter间隔)"%>
<%@ attribute name="delimiter" rtexprvalue="true" description="间隔字符，默认逗号"%>
<%@ attribute name="parameters" type="java.lang.Object[]" rtexprvalue="true" description="参数值"%>
<%@ attribute name="parameterTypes" type="java.lang.Class[]" rtexprvalue="true" description="参数类型"%>
<%
	Logger log = LoggerFactory.getLogger("option.tag");
/*
	source必须是以下类型之一：
	enum : value1=name1,value2=name2...;
	sql : select ... from ...;
	bean : beanId, methodName, valueProperty, nameProperty;
	class : className, methodName, valueProperty, nameProperty;
*/
	String[] arr = null;
	String strType = null;
	Object[] result =null;
	String[] selecteds = selected.split(ObjectUtils.isEmpty(delimiter) ? "," : delimiter);
	int intIdx = data.indexOf(":");
	if (intIdx > 0) strType = data.substring(0, intIdx).trim();

	try {
		if (strType == null) {
		} else if (strType.equalsIgnoreCase("enum")) {//遍历枚举型 value1=name1,value2=name2,...
			String[] item = null;
			arr = data.substring(intIdx + 1).trim().split(",");
			StringBuffer sbf = new StringBuffer(1000);
			for (String str : arr) {
				item = str.trim().split("=");
				sbf.append("<option value=\"").append(item[0].trim())
				.append(ObjectUtils.indexOfArray(selecteds, item[0].trim()) > -1 ? "\" selected>" : "\">")
				.append(item[1].trim()).append("</option>");
			}
			out.write(sbf.toString());
	
		} else if (strType.equalsIgnoreCase("sql")) {//执行SQL语句
			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
			SpringDao dao = (SpringDao) context.getBean("DBRepository");
			Object[][] ary = dao.queryForArray(data.substring(intIdx + 1).trim(), parameters, null, false, null);
			out.write(makeArrayToString(ary, "0", "1", selecteds));
	
		} else if (strType.equalsIgnoreCase("bean")) {//实例类方法{类id,方法名,value属性,name属性}
			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
			arr = data.substring(intIdx + 1).trim().split(",");
			Object bean = context.getBean(StringUtils.trimToEmpty(arr[0]));
			arr = data.substring(intIdx + 1).trim().split(",");
			result = ObjectUtils.getArrayByBean(bean, StringUtils.trimToEmpty(arr[1]), parameters, parameterTypes);
			out.write(makeArrayToString(result, StringUtils.trimToEmpty(arr[2]), StringUtils.trimToEmpty(arr[3]), selecteds));
	
		} else if (strType.equalsIgnoreCase("class")) {//类静态方法{类名,方法名,value属性,name属性}
			arr = data.substring(intIdx + 1).trim().split(",");
			Class<?> clazz = Class.forName(StringUtils.trimToEmpty(arr[0]));
			result = ObjectUtils.getArrayByClass(clazz, StringUtils.trimToEmpty(arr[1]), parameters, parameterTypes);
			out.write(makeArrayToString(result, StringUtils.trimToEmpty(arr[2]), StringUtils.trimToEmpty(arr[3]), selecteds));
		}
	} catch (Exception e) {
		log.error("option.tag", e);
	}
%>
<%!
	private static String makeArrayToString(Object[] result, String key, String keyname, String[] selected) throws Exception {
		if (result == null || result.length < 1) return "";
		String value,name = null;
		StringBuffer sbf = new StringBuffer(1000);
		for (Object obj : result) {
			if (obj instanceof Map) {
				value = StringUtils.trimToEmpty(((Map<?, ?>) obj).get(key));
				name = StringUtils.trimToEmpty(((Map<?, ?>) obj).get(keyname));
			} else if (obj.getClass().isArray()) {
				value = StringUtils.trimToEmpty(((Object[]) obj)[MathUtils.toInt(key, 0)]);
				name = StringUtils.trimToEmpty(((Object[]) obj)[MathUtils.toInt(keyname, 1)]);
			} else {
				value = BeanUtils.getProperty(obj, key);
				name = BeanUtils.getProperty(obj, keyname);
			}
			sbf.append("<option value=\"").append(value)
			.append(ObjectUtils.indexOfArray(selected, value) > -1 ? "\" selected>" : "\">")
			.append(name).append("</option>");
		}
		return sbf.toString();
	}
%>