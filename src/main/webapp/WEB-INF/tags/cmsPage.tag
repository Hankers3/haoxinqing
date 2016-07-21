<%@tag pageEncoding="UTF-8" description="分页" %>
<%@ attribute name="listPath" type="java.lang.String" required="true" description="跳转到的List路径" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="aebiz" tagdir="/WEB-INF/tags" %>

<div class="Xht_BottomPage">
	<div class="Xht_BottomPage_bg">
		<div style="float:left;padding-top:4px;">
			每页显示：
			<a style="cursor:pointer" <c:if test="${wm.pageShow==20 }">class="y_this"</c:if>
				href="${pageContext.servletContext.contextPath}/${listPath}/1/20">20</a>&nbsp;&nbsp;|&nbsp;
			<a style="cursor:pointer" <c:if test="${wm.pageShow==50 }">class="y_this"</c:if>
				href="${pageContext.servletContext.contextPath}/${listPath}/1/50">50</a>&nbsp;&nbsp;|&nbsp;
			<a style="cursor:pointer" <c:if test="${wm.pageShow==100}">class="y_this"</c:if>
				href="${pageContext.servletContext.contextPath}/${listPath}/1/100">100</a>
		</div>
		<div style="float:right;">
			共${wm.totalNum }条记录&nbsp;&nbsp;&nbsp;&nbsp;
			<c:if test="${wm.nowPage > 1 }">	
				<a href="${pageContext.servletContext.contextPath}/${listPath}/1/${wm.pageShow}"><img border="0" align="absmiddle" src="/static/images/Xht_tb29.gif"></a>
				&nbsp;
				<a href="${pageContext.servletContext.contextPath}/${listPath}/${wm.nowPage-1}/${wm.pageShow}"><img border="0" align="absmiddle" src="/static/images/Xht_tb25.gif"></a>
			</c:if>	
			<c:if test="${wm.nowPage <= 1}">	
				<img border="0" align="absmiddle" src="/static/images/Xht_tb22.gif">
				&nbsp;
				<img border="0" align="absmiddle" src="/static/images/Xht_tb24.gif">
			</c:if>			
			<select id="selectOnePage">
				<c:forEach begin="1" end="${wm.totalPage }" var="i">	
					<option  <c:if test="${i==wm.nowPage }">selected</c:if>  value="${i }">${i }</option>
				</c:forEach>
			</select>
			&nbsp;
			分${wm.nowPage }/${wm.totalPage }页&nbsp;&nbsp;&nbsp;
			<c:if test="${wm.nowPage < wm.totalPage }">
				&nbsp;
				<a href="${pageContext.servletContext.contextPath}/${listPath}/${wm.nowPage+1}/${wm.pageShow}"><img border="0" align="absmiddle" src="/static/images/Xht_tb26.gif"></a>
				&nbsp;
				<a href="${pageContext.servletContext.contextPath}/${listPath}/${wm.totalPage}/${wm.pageShow}"><img border="0" align="absmiddle" src="/static/images/Xht_tb28.gif"></a>
			</c:if>
			<c:if test="${wm.nowPage >= wm.totalPage }">
				&nbsp;
				<img border="0" align="absmiddle" src="/static/images/Xht_tb27.gif">
				&nbsp;
				<img border="0" align="absmiddle" src="/static/images/Xht_tb23.gif">
			</c:if>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-1.11.1.min.js"></script>
<script>
	$(function(){
		$("#selectOnePage").change(function(){
			window.location.href="${pageContext.servletContext.contextPath}/${listPath}/"+$(this).val()+"/${wm.pageShow}";
		});
	})	
</script>