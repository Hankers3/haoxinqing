<%@ tag pageEncoding="UTF-8" import="com.wxcommon.Constants,com.wxcommon.util.*" description="生成分页信息"%>
<%
	StringBuffer sbf = new StringBuffer("第  ");
	sbf.append(MathUtils.toInt(request.getAttribute(Constants.PAGINATION_PAGE_NO), 0)).append("/")
	.append(MathUtils.toInt(request.getAttribute(Constants.PAGINATION_TOTAL_PAGE), 0)).append(" 页  共 ")
	.append(MathUtils.toInt(request.getAttribute(Constants.PAGINATION_TOTAL_ROWS), 0)).append(" 条");
	out.write(sbf.toString());
%>