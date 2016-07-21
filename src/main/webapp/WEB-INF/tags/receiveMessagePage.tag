<%@tag pageEncoding="UTF-8" description="分页" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="aebiz" tagdir="/WEB-INF/tags" %>

<div class="y_page">
	<ul class="pagination">
		
		<c:if test="${wm.nowPage > 1 }">
			<li class="y_pgbt"><a href="javascript:receiveMessagePage('sx','${wm.nowPage-1}','${wm.pageShow}')" title="上一页">&lt;</a></li>
		</c:if>
		<c:if test="${wm.nowPage <= 1}">
			<li class="y_pgbt disabled"><span>&lt;</span></li>
		</c:if>
		
		<c:choose>
			<c:when test="${wm.totalPage <=5}">
				<c:forEach begin="1" end="${wm.totalPage }" var="i">
					<li <c:if test="${wm.nowPage == i}">class="active"</c:if>><a href="javascript:receiveMessagePage('sx','${i}','${wm.pageShow}')" title="第2页">${i}</a></li>	
				</c:forEach>
			</c:when>
			
			<c:when test="${wm.nowPage <=3}">
				<c:forEach begin="1" end="5" var="i">
					<li <c:if test="${wm.nowPage == i}">class="active"</c:if>><a href="javascript:receiveMessagePage('sx','${i}','${wm.pageShow}')" title="第2页">${i}</a></li>	
				</c:forEach>
			</c:when>
			
			<c:when test="${wm.nowPage >3 && wm.totalPage<=(wm.nowPage+2)}">
				<c:forEach begin="${(wm.totalPage-4)}" end="${wm.totalPage}" var="i">
					<li <c:if test="${wm.nowPage == i}">class="active"</c:if>><a href="javascript:receiveMessagePage('sx','${i}','${wm.pageShow}')" title="第2页">${i}</a></li>	
				</c:forEach>
			</c:when>
			
			<c:when test="${wm.nowPage >3 && (wm.totalPage > (wm.nowPage+2))}">
				<c:forEach begin="${wm.nowPage-2}" end="${wm.nowPage+2}" var="i">
					<li <c:if test="${wm.nowPage == i}">class="active"</c:if>><a href="javascript:receiveMessagePage('sx','${i}','${wm.pageShow}')" title="第2页">${i}</a></li>	
				</c:forEach>
			</c:when>
		</c:choose>
		
	  	<c:if test="${wm.nowPage < wm.totalPage }">
	  		<li class="y_pgbt"><a href="javascript:receiveMessagePage('sx','${wm.totalPage}','${wm.pageShow}')" title="下一页">&gt;</a></li>
	  	</c:if>
	  	
	  	<c:if test="${wm.nowPage >= wm.totalPage }">
	  		<li class="y_pgbt disabled"><span>&gt;</span></li>
	  	</c:if>
	</ul>		
	共${wm.totalPage}页，到第<input class="form-control" type="text" value="" id="goPage">页
	<a class="btn btn-primary" href="javascript:goPage()" id="goPage" title="确定">确定</a>
</div>

<script type="text/javascript">
	function goPage() {
		var page = document.getElementById("goPage").value ;
		if(page.trim() == "") {
			return ;
		}
		if(isNaN(parseInt(page))) {
			return ;
		}
		receiveMessageType('sx',parseInt(page),'${wm.pageShow}');
	}
</script>









