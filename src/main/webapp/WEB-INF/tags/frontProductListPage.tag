<%@tag pageEncoding="UTF-8" description="分页" %>
<%@ attribute name="listPath" type="java.lang.String" required="false" description="跳转到的List路径" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="aebiz" tagdir="/WEB-INF/tags" %>

<div class="pagination-container clearfix">
	<div class="page-in">
		<span class="prev-disabled" disabled="disabled"></span>
		
		<c:if test="${wm.nowPage > 1 }">
			<a class="prev" href="javascript:queryProductsPage('${wm.nowPage-1}');" title="上一页"><i class="fa fa-angle-double-left"></i>上一页</a>
		</c:if>
		<c:if test="${wm.nowPage <= 1}">
			<a class="prev" href="javascript:;" disabled="disabled">上一页</a>
		</c:if>
		
		<c:choose>
			<c:when test="${wm.totalPage <=5}">
				<c:forEach begin="1" end="${wm.totalPage }" var="i">
					<a <c:if test="${wm.nowPage == i}">class='active'</c:if> href="javascript:queryProductsPage('${i}');">${i}</a>
				</c:forEach>
			</c:when>
			
			<c:when test="${wm.nowPage <=3}">
				<c:forEach begin="1" end="5" var="i">
					<a <c:if test="${wm.nowPage == i}">class="active"</c:if> href="javascript:queryProductsPage('${i}');">${i}</a>
				</c:forEach>
			</c:when>
			
			<c:when test="${wm.nowPage >3 && wm.totalPage<=(wm.nowPage+2)}">
				<c:forEach begin="${(wm.totalPage-4)}" end="${wm.totalPage}" var="i">
					<a <c:if test="${wm.nowPage == i}">class="active"</c:if> href="javascript:queryProductsPage('${i}');">${i}</a>
				</c:forEach>
			</c:when>
			
			<c:when test="${wm.nowPage >3 && (wm.totalPage > (wm.nowPage+2))}">
				<c:forEach begin="${wm.nowPage-2}" end="${wm.nowPage+2}" var="i">
					<a <c:if test="${wm.nowPage == i}">class="active"</c:if> href="javascript:queryProductsPage('${i}');">${i}</a>
				</c:forEach>
			</c:when>
		</c:choose>
		
		<c:if test="${wm.nowPage < wm.totalPage }">
	  		<a class="next" href="javascript:queryProductsPage('${wm.totalPage}');" title="下一页">下一页<i class="fa fa-angle-double-right"></i></a>
	    </c:if>
		<c:if test="${wm.nowPage >= wm.totalPage }">
		  	<a class="next" href="javascript:;" disabled="disabled">下一页</a>
	    </c:if>
		
		<span class="page-skip">
			<em>&nbsp;&nbsp;共${wm.totalPage }页&nbsp;&nbsp;&nbsp;&nbsp;到第</em>
			<input name="skipPage" id="skipPage" class="jumpto" type="text"/>
			<em>页</em>
			<a class="btn btn-primary" href="javascript:skipPage()">确定</a>
		</span>
	</div>
	<script>
		function skipPage(){
			var skipPage = $("#skipPage").val();
			queryProductsPage(skipPage);
		}
	</script>
</div>








