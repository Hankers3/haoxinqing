<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImport.jsp" %>

</head>

<body class="demo-only-page-blank">
	
	<!-- WRAPPER 头部、左侧、右侧大框架 -->
	<div class="wrapper">
		<!-- TOP BAR 头部导航 -->
		<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterHead.jsp" %>
		<!-- /top -->
		

		<!-- BOTTOM: LEFT NAV AND RIGHT MAIN CONTENT 左侧导航和右侧主体 -->
		<div class="bottom">
			<div class="container">
				<div class="row">
					<!-- left sidebar 左侧导航 -->
					<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterLeft.jsp" %>
					<!-- end left sidebar -->

					<!-- content-wrapper 右侧主体 -->
					<div class="col-xs-10 content-wrapper">
						<div class="row">
							<div class="col-sm-4 ">
								<ul class="breadcrumb">
									<li><i class="fa fa-home"></i><a href="会员中心首页.html">会员中心</a></li>
									<li>账户管理</li>
									<li class="active">会员绑定银行卡</li>
								</ul>
							</div>
							<div class="col-sm-8 ">
								<div class="top-content">
									<ul class="list-inline quick-access">
										<li>
											<a href="#">
												<span class="quick-access-item bg-color-green">
													<i class="fa fa-bar-chart-o"></i>
													<h5>订单</h5><em>查看订单列表</em>
												</span>
											</a>
										</li>
										<li>
											<a href="#">
												<span class="quick-access-item bg-color-blue">
													<i class="fa fa-envelope"></i>
													<h5>我的评价</h5><em>查看我所有的评价</em>
												</span>
											</a>
										</li>
										<li>
											<a href="#">
												<span class="quick-access-item bg-color-orange">
													<i class="fa fa-table"></i>
													<h5>晒单</h5><em>查看我的晒单</em>
												</span>
											</a>
										</li>
									</ul>
								</div>
							</div>
						</div>
						
				<!-- main -->
					<div class="content">
							<div class="main-content">
								<c:if test="${m.cardValidateState == '2' }">
									<div class="main-header">
										<h2><aebiz:showTitle titleId="customerbankrel.m.bondSuccess"/></h2>
										<em>To enable bank successfully</em>
									</div>
									<!--交易成功提示-->
									<div class="y_ordsuccess alert alert-success">
										<span class="y_odsicon text-success"><i class="fa fa-check-circle"></i></span>
										<div class="y_odsuccms">
											<h2 class="text-success"><aebiz:showTitle titleId="customerbankrel.m.bondSuccessNote"/></h2>
											<p><aebiz:showTitle titleId="customerbankrel.m.cardNo"/>:${m.cardNo }</p>
										</div>
									</div>
								</c:if>
								<c:if test="${m.cardValidateState == '1' }">
									<div class="main-header">
										<h2><aebiz:showTitle titleId="customerbankrel.m.bondFail"/></h2>
										<em>To enable bank fail</em>
									</div>
									<div class="y_ordsuccess alert alert-fail">
										<span class="y_odsicon text-fail"><i class="fa fa-check-circle"></i></span>
										<div class="y_odsuccms">
											<h2 class="text-fail"><aebiz:showTitle titleId="customerbankrel.m.bondFailNote"/></h2>
											<p><aebiz:showTitle titleId="customerbankrel.m.bondFailAlert" /><font color='red'>${m.failTimes }</font><aebiz:showTitle titleId="customerbankrel.m.times" /></p>
											<p><a href="${pageContext.servletContext.contextPath}/usercenter/customerbankrel/toAdd"><aebiz:showTitle titleId="basebusiness.showmessage.return"/></a></p>
										</div>
									</div>
								</c:if>
								<c:if test="${m.cardValidateState == '3' }">
									<div class="main-header">
										<h2><aebiz:showTitle titleId="customerbankrel.m.bondFail"/></h2>
										<em>To enable bank fail</em>
									</div>
									<div class="y_ordsuccess alert alert-fail">
										<span class="y_odsicon text-fail"><i class="fa fa-check-circle"></i></span>
										<div class="y_odsuccms">
											<h2 class="text-fail"><aebiz:showTitle titleId="customerbankrel.m.bondFailNote"/></h2>
											<p><aebiz:showTitle titleId="customerbankrel.m.bondFailAlert3Times"/></p>
										</div>
									</div>
								</c:if>
							</div><!-- /main-content -->
						</div><!-- /main -->
					</div><!-- /content-wrapper -->
				</div><!-- /row -->
			</div><!-- /container -->
		</div>
		<!-- END BOTTOM: LEFT NAV AND RIGHT MAIN CONTENT -->
		<div class="push-sticky-footer"></div>
	</div><!-- /wrapper -->
	
	<!-- FOOTER 底部 -->
	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImportJs.jsp" %>
	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterBottom.jsp" %>
	<!-- END FOOTER -->

	<!-- STYLE SWITCHER 右侧更换皮肤 -->
	<div class="del-style-switcher">
		<div class="del-switcher-toggle toggle-hide"></div>
		<form>
			<div class="del-section del-section-skin">
				<h5 class="del-switcher-header">Choose Skins:</h5>

				<ul>
					<li><a href="#" title="Slate Gray" class="switch-skin slategray" data-skin="${pageContext.servletContext.contextPath}/static/usercenter/css/skins/slategray.css">Slate Gray</a></li>
					<li><a href="#" title="Dark Blue" class="switch-skin darkblue" data-skin="${pageContext.servletContext.contextPath}/static/usercenter/css/skins/darkblue.css">Dark Blue</a></li>
					<li><a href="#" title="Dark Brown" class="switch-skin darkbrown" data-skin="${pageContext.servletContext.contextPath}/static/usercenter/css/skins/darkbrown.css">Dark Brown</a></li>
					<li><a href="#" title="Light Green" class="switch-skin lightgreen" data-skin="${pageContext.servletContext.contextPath}/static/usercenter/css/skins/lightgreen.css">Light Green</a></li>
					<li><a href="#" title="Orange" class="switch-skin orange" data-skin="${pageContext.servletContext.contextPath}/static/usercenter/css/skins/orange.css">Orange</a></li>
					<li><a href="#" title="Red" class="switch-skin red" data-skin="${pageContext.servletContext.contextPath}/static/usercenter/css/skins/red.css">Red</a></li>
					<li><a href="#" title="Teal" class="switch-skin teal" data-skin="${pageContext.servletContext.contextPath}/static/usercenter/css/skins/teal.css">Teal</a></li>
					<li><a href="#" title="Yellow" class="switch-skin yellow" data-skin="${pageContext.servletContext.contextPath}/static/usercenter/css/skins/yellow.css">Yellow</a></li>
				</ul>

				<button type="button" class="switch-skin-full fulldark" data-skin="${pageContext.servletContext.contextPath}/static/usercenter/css/skins/fulldark.css">Full Dark</button>
				<button type="button" class="switch-skin-full fullbright" data-skin="${pageContext.servletContext.contextPath}/static/usercenter/css/skins/fullbright.css">Full Bright</button>
			</div>
			
			<p><a href="#" title="Reset Style" class="del-reset-style">Reset Style</a></p>
		</form>
	</div>
	<!-- END STYLE SWITCHER -->

	<!-- Javascript -->
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/jquery.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/bootstrap.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/modernizr.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/king-common.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/demo-style-switcher/js/deliswitch.js"></script><!--页面右侧设置的js-->
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/validation/jquery.validate.min.js"></script><!--验证插件的js-->
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/validation/aebiz.validate.expand.js"></script><!--拓展的验证插件的js-->
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/validation/aebiz.validate.js"></script><!--调用验证插件的js-->
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/jquery.colorbox-min.js"></script>
	<script>
		$(function(){
			//调用放大图插件
		  if ($(".colorbox-image").length > 0) {
			  $(".colorbox-image").colorbox({  
				maxWidth: "90%",
				maxHeight: "90%",
				rel: $(this).attr("rel")
			  });
			};
		})	
	</script>
</body>
</html>


<script>
    $(document).ready(function() {
		$(".cancel").click(function(){
			history.go(-1) ;
		});
    });
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>