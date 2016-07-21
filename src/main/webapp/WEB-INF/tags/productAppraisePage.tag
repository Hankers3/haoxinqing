<%@tag pageEncoding="UTF-8" description="分页" %>
<%@ attribute name="listPath" type="java.lang.String" required="true" description="跳转到的List路径" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="aebiz" tagdir="/WEB-INF/tags" %>

		<c:if test="${wm.nowPage > 1 }">
			<a class="prev" href="javascript:loadAppraise('${wm.nowPage-1}','${wm.pageShow}')" title="上一页"><i class="fa fa-angle-double-left"></i>上一页</a>
		</c:if>
		
		<c:if test="${wm.nowPage <= 1}">
			<a href="javascript:;" disabled="disabled">上一页</a>
		</c:if>
		
		<c:choose>
			<c:when test="${wm.totalPage <=5}">
				<c:forEach begin="1" end="${wm.totalPage }" var="i">
					 <a <c:if test="${wm.nowPage == i}">class='active'</c:if> href="javascript:loadAppraise('${i}','${wm.pageShow}')">${i}</a>
				</c:forEach>
			</c:when>
			
			<c:when test="${wm.nowPage <=3}">
				<c:forEach begin="1" end="5" var="i">
					 <a <c:if test="${wm.nowPage == i}">class='active'</c:if> href="javascript:loadAppraise('${i}','${wm.pageShow}')">${i}</a>
				</c:forEach>
			</c:when>
			
			<c:when test="${wm.nowPage >3 && wm.totalPage<=(wm.nowPage+2)}">
				<c:forEach begin="${(wm.totalPage-4)}" end="${wm.totalPage}" var="i">
					<a <c:if test="${wm.nowPage == i}">class='active'</c:if> href="javascript:loadAppraise('${i}','${wm.pageShow}')">${i}</a>
				</c:forEach>
			</c:when>
			
			<c:when test="${wm.nowPage >3 && (wm.totalPage > (wm.nowPage+2))}">
				<c:forEach begin="${wm.nowPage-2}" end="${wm.nowPage+2}" var="i">
					<a <c:if test="${wm.nowPage == i}">class='active'</c:if> href="javascript:loadPageProducts('${i}','${wm.pageShow}')">${i}</a>
				</c:forEach>
			</c:when>
		</c:choose>
		
	  <c:if test="${wm.nowPage < wm.totalPage }">
	  	<a class="next" href="javascript:loadAppraise('${wm.totalPage}','${wm.pageShow}')" title="下一页">下一页<i class="fa fa-angle-double-right"></i></a>
	  </c:if>
	  	
	  <c:if test="${wm.nowPage >= wm.totalPage }">
	  	<a href="javascript:;" disabled="disabled">下一页</a>
	  </c:if>
	<span class="page-skip"><em>&nbsp;&nbsp;共${wm.totalNum }条/${wm.totalPage }页</span>
	&nbsp;&nbsp;&nbsp;&nbsp;到第</em><input name="skipPage" id="skipPage" type="text" class="jumpto"><em>页</em>
	<a href="javascript:skipPage()" class="btn btn-primary">确定</a>
	<script>
		function skipPage(){
			var skipPage = $("#skipPage").val();
			loadAppraise(skipPage,'${wm.pageShow}');
		}
	</script>










