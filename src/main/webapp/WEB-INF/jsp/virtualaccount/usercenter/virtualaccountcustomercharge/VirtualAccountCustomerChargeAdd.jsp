<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImport.jsp" %>

</head>
   
<body class="forms-inplace-editing">
	<!-- WRAPPER -->
	<div class="wrapper">
		<!-- TOP BAR 头部导航 -->
		<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterHead.jsp" %>
		<!-- /top -->
		
		<!-- BOTTOM: LEFT NAV AND RIGHT MAIN CONTENT -->
		<div class="bottom">
			<div class="container">
				<div class="row">
					<!-- left sidebar 左侧导航 -->
					<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterLeft.jsp" %>
					<!-- end left sidebar -->

					<!-- content-wrapper -->
					<div class="col-xs-10 content-wrapper">
						<div class="row">
							<div class="col-sm-4 ">
								<ul class="breadcrumb">
									<li><i class="fa fa-home"></i><a href="#">首页</a></li>
									<li><a href="#">账户管理</a></li> <li class="active">我的虚拟账户-充值</li>
								</ul>
							</div>
							<div class="col-sm-8 ">
								<div class="top-content">
									<ul class="list-inline quick-access">
										<li>
											<a href="charts-statistics-interactive.html">
												<span class="quick-access-item bg-color-green">
													<i class="fa fa-bar-chart-o"></i>
													<h5>CHARTS</h5><em>basic, interactive, real-time</em>
												</span>
											</a>
										</li>
										<li>
											<a href="page-inbox.html">
												<span class="quick-access-item bg-color-blue">
													<i class="fa fa-envelope"></i>
													<h5>INBOX</h5><em>inbox with gmail style</em>
												</span>
											</a>
										</li>
										<li>
											<a href="tables-dynamic-table.html">
												<span class="quick-access-item bg-color-orange">
													<i class="fa fa-table"></i>
													<h5>DYNAMIC TABLE</h5><em>tons of features and interactivity</em>
												</span>
											</a>
										</li>
									</ul>
								</div>
							</div>
						</div>
						
						<!-- main -->
						<div class="content">
						 	<div class="main-header">
								<h2><aebiz:showTitle titleId="withdrawapply.m.withdra"/></h2>
							</div>

							<div class="main-content">
								
								 <div class="widget serve-widget recharge"> <!--充值 star-->
								    <div class="widget-header">
											<h3><aebiz:showTitle titleId="virtualaccountcustomercharge.m.operAmount"/></h3>
										</div>
										<div class="widget-content">
										<form id="basic-form" class="form-horizontal y_setpassd form-validate" action="${pageContext.servletContext.contextPath}/usercenter/virtualaccountcustomercharge/add" method="post">
											<div class="row col-bothbB logi-num">
											  <div class="col-mi-2 col-sm-2"><aebiz:showTitle titleId="virtualaccountcustomercharge.m.operAmount"/></div>
											  <div class="col-mi-10 col-sm-10 col-bothbL">
											  	<i class="fa fa-rmb price-color"></i><input type="text" name="operAmount" class="rmb-input" data-rule-required="true" data-rule-float="true"/><br/>
											  	<aebiz:showTitle titleId="virtualaccountcustomercharge.m.notice"/>
											  	<div class="alert alert-info">
											  	   <aebiz:showTitle titleId="virtualaccountcustomercharge.m.servicePhone"/>：400-600-8000 &nbsp;&nbsp;&nbsp;&nbsp;<aebiz:showTitle titleId="virtualaccountcustomercharge.m.serviceTime"/>：周一至周日 0:00--24:00
											  	</div>
											  	<p></p>
											  </div>
											</div>
											<div class="row sub-box">
												<div class="col-mi-2 col-sm-2"></div>
												<div class="col-mi-10 col-sm-10">
													<input type="submit" class="btn btn-warning subbut" value='<aebiz:showTitle titleId="basebusiness.showmessage.nextStep"/>'>
												</div>
											</div>
											<div class="alert alert-warning">
											  <strong><aebiz:showTitle titleId="virtualaccountcustomercharge.m.tips"/></strong><br/>
											  <ol>
											  	<li><aebiz:showTitle titleId="virtualaccountcustomercharge.m.tip1"/></li>
											  	<li><aebiz:showTitle titleId="virtualaccountcustomercharge.m.tip2"/></li>
											  	<li><aebiz:showTitle titleId="virtualaccountcustomercharge.m.tip3"/></li>
											  </ol>
											</div>
										</form>
										</div>

								 </div><!--充值 end-->

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

	<!-- STYLE SWITCHER -->
	<div class="del-style-switcher">
		<div class="del-switcher-toggle toggle-hide"></div>
		<form>
			<div class="del-section del-section-skin">
				<h5 class="del-switcher-header">Choose Skins:</h5>

				<ul>
					<li><a href="#" title="Slate Gray" class="switch-skin slategray" data-skin="css/skins/slategray.css">Slate Gray</a></li>
					<li><a href="#" title="Dark Blue" class="switch-skin darkblue" data-skin="css/skins/darkblue.css">Dark Blue</a></li>
					<li><a href="#" title="Dark Brown" class="switch-skin darkbrown" data-skin="css/skins/darkbrown.css">Dark Brown</a></li>
					<li><a href="#" title="Light Green" class="switch-skin lightgreen" data-skin="css/skins/lightgreen.css">Light Green</a></li>
					<li><a href="#" title="Orange" class="switch-skin orange" data-skin="css/skins/orange.css">Orange</a></li>
					<li><a href="#" title="Red" class="switch-skin red" data-skin="css/skins/red.css">Red</a></li>
					<li><a href="#" title="Teal" class="switch-skin teal" data-skin="css/skins/teal.css">Teal</a></li>
					<li><a href="#" title="Yellow" class="switch-skin yellow" data-skin="css/skins/yellow.css">Yellow</a></li>
				</ul>

				<button type="button" class="switch-skin-full fulldark" data-skin="css/skins/fulldark.css">Full Dark</button>
				<button type="button" class="switch-skin-full fullbright" data-skin="css/skins/fullbright.css">Full Bright</button>
			</div>
			
			<p><a href="#" title="Reset Style" class="del-reset-style">Reset Style</a></p>
		</form>
	</div>
	<!-- END STYLE SWITCHER -->

	<!-- Javascript -->
	<!-- 会员中心公用的js 5个-->
  <script src="${pageContext.servletContext.contextPath}/static/usercenter/js/jquery.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/bootstrap.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/modernizr.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/king-common.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/demo-style-switcher/js/deliswitch.js"></script><!--页面右侧设置的js-->
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/validation/jquery.validate.min.js"></script><!--验证插件的js-->
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/validation/aebiz.validate.expand.js"></script><!--验证拓展插件的js-->
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/validation/aebiz.validate.js"></script><!--调用验证插件的js-->
	<!-- 当前页面使用的js-->
    	
</body>
</html>
