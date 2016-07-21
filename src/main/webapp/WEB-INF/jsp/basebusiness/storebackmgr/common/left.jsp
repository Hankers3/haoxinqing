<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Nice Scroll 页面左侧边栏使用美化滚动条插件，每个页面都引 -->
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/nicescroll/jquery.nicescroll.min.js"></script>
<form action="search-results.html" method="GET" class='search-form'>
	<div class="search-pane">
		<input type="text" name="search" placeholder="Search here...">
		<button type="submit">
			<i class="fa fa-search"></i>
		</button>
	</div>
</form>


<c:forEach items="${Menu_One}" var="smmOne">	
			<div class="subnav">
				<div class="subnav-title">
					<a herf="#" class='toggle-subnav'>
						<i class="fa fa-angle-down"></i>
						<span>${smmOne.name}</span>
					</a>
				</div>
						
				<c:forEach items="${Menu_OneTwo[smmOne]}" var="smmTwo">
					<ul class="subnav-menu">	
						<c:choose> 
						  <c:when test="${Menu_TwoThree[smmTwo] == null}"> 
								<li <c:if test="${menuId == smmTwo.uuid}">class='active'</c:if>>
								<a href="javascript:void(0);" onClick="javascript:addLeftCookies('${smmTwo.uuid}','${smmTwo.toUrl}');">${smmTwo.name}</a>
								</li>
						  </c:when> 
						  
						  <c:otherwise>   
						    <li class="dropdown">
									<a href="#" data-toggle="dropdown">${smmTwo.name}</a>
									<ul class="dropdown-menu">
										<c:forEach items="${Menu_TwoThree[smmTwo]}" var="smmThree">
											<li <c:if test="${menuId == smmThree.uuid}">class='active'</c:if>>
											<a href="javascript:void(0);" onClick="javascript:addLeftCookies('${smmThree.uuid}','${smmThree.toUrl}');">${smmThree.name}</a>
											</li>
										</c:forEach>
									</ul>
								</li>
						  </c:otherwise> 
						</c:choose> 
						
					</ul>
				</c:forEach>	
			</div>	
		</c:forEach>
<script>
function addLeftCookies(menuId,url){
	$.get("${pageContext.servletContext.contextPath}/store/addLeftCookies/"+menuId,
			{},
			function(data) {
			if("success" == data){
				window.location.href="${pageContext.servletContext.contextPath}/"+url;
			}
			});
}
</script>
