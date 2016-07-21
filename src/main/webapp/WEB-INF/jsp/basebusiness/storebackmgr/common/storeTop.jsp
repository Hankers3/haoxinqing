<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div id="navigation">
		<div class="container-fluid">
			<a href="${pageContext.servletContext.contextPath}/store/toIndex" id="brand">AEBIZ</a>
			<a href="#" class="toggle-nav" rel="tooltip" data-placement="bottom" title="Toggle navigation">
				<i class="fa fa-bars"></i>
			</a>
			<ul class='main-nav'>
				<c:forEach items="${Menu_One}" var="smmOne">	
					<li <c:if test="${parentId == smmOne.uuid}">class='active'</c:if>>
						<a href="javascript:void(0);" onClick="javascript:addCookies('${smmOne.uuid}','${smmOne.toUrl}');">
							<span>${smmOne.name}</span>
						</a>
					</li>
				</c:forEach>
			</ul>		
			
			<div class="user">
				<ul class="icon-nav">
					<li class='dropdown'>
						<a href="#" class='dropdown-toggle' data-toggle="dropdown">
							<i class="fa fa-envelope"></i>
							<span class="label label-lightred">4</span>
						</a>
						<ul class="dropdown-menu pull-right message-ul">
							<li>
								<a href="#">
									<img src="../../../img/demo/user-1.jpg" alt="">
									<div class="details">
										<div class="name">Jane Doe</div>
										<div class="message">
											Lorem ipsum Commodo quis nisi ...
										</div>
									</div>
								</a>
							</li>
							<li>
								<a href="#">
									<img src="../../../img/demo/user-2.jpg" alt="">
									<div class="details">
										<div class="name">John Doedoe</div>
										<div class="message">
											Ut ad laboris est anim ut ...
										</div>
									</div>
									<div class="count">
										<i class="fa fa-comment"></i>
										<span>3</span>
									</div>
								</a>
							</li>
							<li>
								<a href="#">
									<img src="../../../img/demo/user-3.jpg" alt="">
									<div class="details">
										<div class="name">Bob Doe</div>
										<div class="message">
											Excepteur Duis magna dolor!
										</div>
									</div>
								</a>
							</li>
							<li>
								<a href="components-messages.html" class='more-messages'>Go to Message center
									<i class="fa fa-arrow-right"></i>
								</a>
							</li>
						</ul>
					</li>
					
					<div class="dropdown">
						<a href="#" class='dropdown-toggle' data-toggle="dropdown">${accountModel.storeName }
							<img src="${companyInfoModel.logoPath }" alt="" style="width:25px;height:25px;">
						</a>
						<ul class="dropdown-menu pull-right">
							<li>
								<a href="${pageContext.servletContext.contextPath}/store/toLogin">退出</a>
							</li>
						</ul>
					</div>
			</div>
		</div>
	</div>
<script>
function addCookies(parentId,url){
	//var str = "CHOOSEMENU="+parentId;
	//document.cookie=str; 
	$.get("${pageContext.servletContext.contextPath}/store/addCookies/"+parentId,
			{},
			function(data) {
			if("success" == data){
				window.location.href="${pageContext.servletContext.contextPath}/"+url;
			}
			});
}
</script>
