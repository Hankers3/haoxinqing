<%@tag pageEncoding="UTF-8" description="分页" %>
<%@ attribute name="listPath" type="java.lang.String" required="true" description="跳转到的List路径" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="aebiz" tagdir="/WEB-INF/tags" %>


<div class="turn_page mt_10" id="page">	
	<ul class="pagination pagination-custom">
		<c:if test="${wm.totalPage <= 1 }">
			<li><a href="javascript:;" class='disabled'>首页</a></li>
		</c:if>
		
		<c:if test="${wm.totalPage > 1 }">
			<li><a href="javascript:loadPageBrands('1','${wm.pageShow}')" >首页</a></li>
		</c:if>
		
		<c:if test="${wm.nowPage > 1 }">
			<li><a href="javascript:loadPageBrands('${wm.nowPage-1}','${wm.pageShow}')" title="上一页">上一页</a></li>
		</c:if>
		
		<c:if test="${wm.nowPage <= 1}">
			<li><a href="javascript:;" class='disabled'>上一页</a></li>
		</c:if>
		
		<c:choose>
			<c:when test="${wm.totalPage <=5}">
				<c:forEach begin="1" end="${wm.totalPage }" var="i">
					<li <c:if test="${wm.nowPage == i}">class='active'</c:if>><a href="javascript:loadPageBrands('${i}','${wm.pageShow}')">${i}</a></li>	
				</c:forEach>
			</c:when>
			
			<c:when test="${wm.nowPage <=3}">
				<c:forEach begin="1" end="5" var="i">
					<li <c:if test="${wm.nowPage == i}">class="active"</c:if> ><a href="javascript:loadPageBrands('${i}','${wm.pageShow}')">${i}</a></li>
				</c:forEach>
			</c:when>
			
			<c:when test="${wm.nowPage >3 && wm.totalPage<=(wm.nowPage+2)}">
				<c:forEach begin="${(wm.totalPage-4)}" end="${wm.totalPage}" var="i">
					<li <c:if test="${wm.nowPage == i}">class="active"</c:if> ><a href="javascript:loadPageBrands('${i}','${wm.pageShow}')">${i}</a></li>
				</c:forEach>
			</c:when>
			
			<c:when test="${wm.nowPage >3 && (wm.totalPage > (wm.nowPage+2))}">
				<c:forEach begin="${wm.nowPage-2}" end="${wm.nowPage+2}" var="i">
					<li <c:if test="${wm.nowPage == i}">class="active"</c:if> ><a href="javascript:loadPageBrands('${i}','${wm.pageShow}')">${i}</a></li>
				</c:forEach>
			</c:when>
		</c:choose>
		
	  <c:if test="${wm.nowPage < wm.totalPage }">
	  	<li><a href="javascript:loadPageBrands('${wm.nowPage+1}','${wm.pageShow}')" title="下一页">下一页</a></li>
	  </c:if>
	  	
	  <c:if test="${wm.nowPage >= wm.totalPage }">
	  	<li><a href="javascript:;" class='disabled'>下一页</a></li>
	  </c:if>
	  
	  <c:if test="${wm.totalPage <= 1 }">
			<li><a href="javascript:;" class='disabled'>尾页</a></li>
		</c:if>
		
		<c:if test="${wm.totalPage > 1 }">
			<li><a href="javascript:loadPageBrands('${wm.totalPage}','${wm.pageShow}')" >尾页</a></li>
		</c:if>
	</ul>
	<span class="y_pglength">共${wm.totalNum }条/${wm.totalPage }页</span>
	到第<input name="skipPage" id="skipPage" type="text" class="form-control">页
	<a href="javascript:skipPage()" class="btn btn-primary">确定</a>
	<script>
		function skipPage(){
			var skipPage = $("#skipPage").val();
			loadPageBrands(skipPage,'${wm.pageShow}');
		}
	</script>
</div>









