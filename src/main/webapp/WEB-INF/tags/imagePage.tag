<%@tag pageEncoding="UTF-8" description="分页" %>
<%@ attribute name="listPath" type="java.lang.String" required="true" description="跳转到的List路径" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="aebiz" tagdir="/WEB-INF/tags" %>


<div class="page-box  text-center mt_20">
	<ul class="pagination">
		<c:if test="${wm.totalPage <= 1 }">
			<li class='disabled'><a href="#">首页</a></li>
		</c:if>
		
		<c:if test="${wm.totalPage > 1 }">
			<li><a href="javascript:imagePage('1','${wm.pageShow}')" >首页</a></li>
		</c:if>

		<c:if test="${wm.nowPage > 1 }">
			<li><a href="javascript:imagePage('${wm.nowPage-1}','${wm.pageShow}')" title="上一页">上一页</a></li>
		</c:if>
		
		<c:if test="${wm.nowPage <= 1}">
			<li class='disabled'><a href="#" >上一页</a></li>
		</c:if>
		
		<c:choose>
		
			<c:when test="${wm.totalPage <=5}">
				<c:forEach begin="1" end="${wm.totalPage }" var="i">
					<li <c:if test="${wm.nowPage == i}">class="active"</c:if>><a href="javascript:imagePage('${i}','${wm.pageShow}')"  >${i}</a></li>
				</c:forEach>
			</c:when>
			
			<c:when test="${wm.nowPage <=3}">
				<c:forEach begin="1" end="5" var="i">
					<li <c:if test="${wm.nowPage == i}">class="active"</c:if>><a href="javascript:imagePage('${i}','${wm.pageShow}')"  >${i}</a></li>
				</c:forEach>
			</c:when>
			
			<c:when test="${wm.nowPage >3 && wm.totalPage<=(wm.nowPage+2)}">
				<c:forEach begin="${(wm.totalPage-4)}" end="${wm.totalPage}" var="i">
					<li <c:if test="${wm.nowPage == i}">class="active"</c:if>><a href="javascript:imagePage('${i}','${wm.pageShow}')"  >${i}</a></li>
				</c:forEach>
			</c:when>
			
			<c:when test="${wm.nowPage >3 && (wm.totalPage > (wm.nowPage+2))}">
				<c:forEach begin="${wm.nowPage-2}" end="${wm.nowPage+2}" var="i">
					<li <c:if test="${wm.nowPage == i}">class="active"</c:if>><a href="javascript:imagePage('${wm.nowPage-1}','${wm.pageShow}')" >${i}</a></li>
				</c:forEach>
			</c:when>
		</c:choose>
		
	  <c:if test="${wm.nowPage < wm.totalPage }">
	  	<li><a href="javascript:imagePage('${wm.totalPage}','${wm.pageShow}')" title="下一页">下一页</a></li>
	  </c:if>
	  	
	  <c:if test="${wm.nowPage >= wm.totalPage }">
	  	<li class='disabled'><a href="#" >下一页</a></li>
	  </c:if>
	  
	  <c:if test="${wm.totalPage <= 1 }">
			<li class='disabled'><a href="#" >尾页</a></li>
		</c:if>
		
		<c:if test="${wm.totalPage > 1 }">
			<li><a href="javascript:imagePage('${wm.totalPage}','${wm.pageShow}')" >尾页</a></li>
		</c:if>
	</ul>
</div>













