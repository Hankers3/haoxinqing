<%@ tag pageEncoding="UTF-8" import="com.wxcommon.Constants,com.wxcommon.http.*,com.wxcommon.util.*" description="生成按前后分页链接"%>
<%@ attribute name="url" required="true" rtexprvalue="true" description="翻页链接地址"%>
<div class="y_page">
	<ul class="pagination">
<%
	url = RequestUtil.url(request, StringUtils.trimToEmpty(url));
	int pageno = MathUtils.toInt(request.getAttribute(Constants.PAGINATION_PAGE_NO), 0);
	int totalpage = MathUtils.toInt(request.getAttribute(Constants.PAGINATION_TOTAL_PAGE), 0);

	if (totalpage > 1) {
		StringBuffer sbf = new StringBuffer(1000);
		sbf.append("<li class=\"y_pgbt\"><a href=\"").append(url)
		.append("?").append(Constants.PAGINATION_PAGE_NO).append("=");
		String href = sbf.toString();
		sbf.delete(0, sbf.length());

		boolean blank = false;
		if (pageno > 1 && totalpage > 1) {
			sbf.append(href).append("1\">");
			sbf.append("首页");//sbf.append("<img src=\"\" alt=\"首页\"/>");
			sbf.append("</a></li>");
			blank = true;
		}
		if (pageno > 2) {
			if (blank) sbf.append("&#160;");
			sbf.append(href).append(pageno - 1).append("\">");
			sbf.append("上一页");//sbf.append("<img src=\"\" alt=\"上一页\"/>");
			sbf.append("</a></li>");
			blank = true;
		}
		if (pageno < (totalpage - 1)) {
			if (blank) sbf.append("&#160;");
			sbf.append(href).append(pageno + 1).append("\">");
			sbf.append("下一页");//sbf.append("<img src=\"\" alt=\"下一页\"/>");
			sbf.append("</a></li>");
			blank = true;
		}
		if (pageno < totalpage && totalpage > 1) {
			if (blank) sbf.append("&#160;");
			sbf.append(href).append(totalpage).append("\">");
			sbf.append("尾页");//sbf.append("<img src=\"\" alt=\"尾页\"/>");
			sbf.append("</a></li>");
		}
		out.write(sbf.toString());
	}
%>
	</ul>		
</div>