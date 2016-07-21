<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<title>好欣晴 -后台管理 </title>

	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/themes.css">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/aebiz-menu-0.1.css">
	
	<!--[if lt IE 8]>
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/font-awesome-ie7.min.css">
	<![endif]-->
	
	<!-- jQuery 库 -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/jquery.min.js"></script>
	<!-- Bootstrap 框架的js-->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/bootstrap.min.js"></script>
	
	<!-- Nice Scroll 页面左侧边栏使用美化滚动条插件 -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/nicescroll/jquery.nicescroll.min.js"></script>
	
	<!-- jQuery UI 这5个jquery.ui插件，左侧模块可拖拽、美化滚动条-->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/jquery-ui/jquery.ui.core.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/jquery-ui/jquery.ui.widget.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/jquery-ui/jquery.ui.mouse.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/jquery-ui/jquery.ui.sortable.min.js"></script>	
	
	<!-- 左侧菜单的公共css-->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz_menu-0.1.js"></script>
	
	<!--[if lte IE 9]>
		<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/placeholder/jquery.placeholder.min.js"></script>
		<script>
			$(document).ready(function() {
				$('input, textarea').placeholder();
			});
		</script>
	<![endif]-->
	
	<!--[if lt IE 9]>
    <script src="${pageContext.servletContext.contextPath}/static/sysback/js/respond.min.js"></script>
  <![endif]-->
</head>


<body class="left_body theme-darkblue">
		<!--左侧边栏#left-->
	<div id="left-box">
		<c:forEach items="${Menu_One}" var="smmOne" varStatus="stOne">	
			<div class="subnav">
				<div class="subnav-title">
					<a herf="#" class='toggle-subnav'>
						<i class="fa fa-angle-down"></i>
						<span>${smmOne.name}</span>
					</a>
				</div>
				<c:forEach items="${Menu_OneTwo[smmOne]}" var="smmTwo" varStatus="st">
					<ul class="subnav-menu" <c:if test="${st.index == 0 && stOne.index == 0}"> style="background: none repeat scroll 0% 0% rgb(221, 221, 221); color: rgb(34, 34, 34)" </c:if>>	
					<input id="meun_${stOne.index}_${st.index}" type="hidden" value="${pageContext.request.contextPath}/${smmTwo.toUrl}"/>

						<c:choose> 
						  <c:when test="${Menu_TwoThree[smmTwo] == null}">   
								<li>
									<c:choose>
										<c:when test="${smmTwo.id=='OrderMainLIst' }" >
												<a target="mainFrame" href="${pageContext.request.contextPath}/${smmTwo.toUrl}">${smmTwo.name}<span id="show${smmTwo.id}">(0)</span></a>
												<script>
													$(document).ready(function() {
															getNum1();
													});
														
													function getNum1(){
															var menuId ="${smmTwo.id}";
															var url="${pageContext.servletContext.contextPath}/sysback/order/getOrderNum";
															$.post(url,{
																"menuId":menuId,
																ranNum : Math.random()
															},
															function(data){
																$("#show"+menuId).html("("+data+")");
															});
													}
													setInterval('getNum1()',60000); //指定1秒刷新一次
													
												</script>
										</c:when>
										
										
										<c:when test="${ smmTwo.id=='dynamicTask1' }" >
												<a target="mainFrame" href="${pageContext.request.contextPath}/${smmTwo.toUrl}">${smmTwo.name}<span id="show${smmTwo.id}">(0)</span></a>
												<script>
													$(document).ready(function() {
															getNum2();
													});
														
													function getNum2(){
															var menuId ="${smmTwo.id}";
															var url="${pageContext.servletContext.contextPath}/sysback/order/getOrderNum";
															$.post(url,{
																"menuId":menuId,
																ranNum : Math.random()
															},
															function(data){
																$("#show"+menuId).html("("+data+")");
															});
													}
													setInterval('getNum2()',60000); //指定1秒刷新一次
												</script>
										</c:when>
										
										
										<c:when test="${smmTwo.id=='todayOrderTask1'}" >
												<a target="mainFrame" href="${pageContext.request.contextPath}/${smmTwo.toUrl}">${smmTwo.name}<span id="show${smmTwo.id}">(0)</span></a>
												<script>
													$(document).ready(function() {
															getNum3();
													});
														
													function getNum3(){
															var menuId ="${smmTwo.id}";
															var url="${pageContext.servletContext.contextPath}/sysback/order/getOrderNum";
															$.post(url,{
																"menuId":menuId,
																ranNum : Math.random()
															},
															function(data){
																$("#show"+menuId).html("("+data+")");
															});
													}
													
													setInterval('getNum3()',60000); //指定1秒刷新一次

												</script>
										</c:when>
										
										
										<c:otherwise>
												<a target="mainFrame" href="${pageContext.request.contextPath}/${smmTwo.toUrl}">${smmTwo.name}</a>
										</c:otherwise>
									</c:choose>
								</li>
						  </c:when> 
						  <c:otherwise>   
						    <li class="dropdown">
									<a href="#" data-toggle="dropdown">${smmTwo.name}</a>
									<ul class="dropdown-menu">
										<c:forEach items="${Menu_TwoThree[smmTwo]}" var="smmThree">
											<li>
												<a target="mainFrame" href="${pageContext.request.contextPath}/${smmThree.toUrl}">${smmThree.name}</a>
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
	</div>			
</body>

<script>
	$(function(){
		$(".subnav ul").click(function(){
		$(".subnav ul").css({background:"#eee",color:"#555"});
			$(this).css({background:"#DDD",color:"#222"})
		})
		var url = $("#meun_0_0").val();
		
		if(typeof(url) != "undefined" && url != "" && url != "/" && url != "/haoxinqing/"){
			parent.mainFrame.location.href=$("#meun_0_0").val();
		}
	})	
</script>

</html>
