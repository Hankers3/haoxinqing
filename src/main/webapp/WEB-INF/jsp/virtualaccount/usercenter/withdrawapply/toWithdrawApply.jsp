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
	
						
						<!-- main -->
						<div class="content">
						 	<div class="main-header">
								<h2><aebiz:showTitle titleId="withdrawapply.m.withdra"/></h2>
							</div>

							<div class="main-content">
								<div class="alert alert-warning withdraw2"> <!--提示信息 star-->
								  <strong><aebiz:showTitle titleId="withdrawapply.m.tips"/></strong><br/>
								  <ol>
								  	<li><aebiz:showTitle titleId="withdrawapply.m.mesg1"/></li>
								  	<li><aebiz:showTitle titleId="withdrawapply.m.mesg2"/></li>
								  	<li><aebiz:showTitle titleId="withdrawapply.m.mesg3"/></li>
								  </ol>
								</div> <!--提示信息 end-->
								<form action="${pageContext.servletContext.contextPath}/usercenter/virtualaccountcustomercharge/savewithdrawapply" method="post" id="bb" class="form-horizontal y_setpassd form-validate">
									<div class="widget serve-widget recharge"> <!--提现 star-->
									  <div class="widget-header">
											<h3><aebiz:showTitle titleId="withdrawapply.m.writeMes"/></h3>
										</div>
										<div class="widget-content">
											<div class="row col-bothbB logi-num">
											  <div class="col-mi-2 col-sm-2"><span class="imp-icon">*</span><aebiz:showTitle titleId="withdrawapply.apply.money"/></div>
											  <div class="col-mi-10 col-sm-10 col-bothbL">
											  	<i class="fa fa-rmb price-color"></i><input type="text" class="rmb-input" id="applyMoney" name="applyMoney"  data-rule-required="true" data-rule-float="true"/>
											  	<div class="alert alert-info mt_10 mb_10">
											  			<input type="hidden" name="hdaccountBalance" id="hdaccountBalance" value="${m.accountBalance}">
												  	   <aebiz:showTitle titleId="withdrawapply.m.frozzenMoney"/>：<aebiz:showTitle titleId="pub.moneytype"/>${m.virtualFrezonMount}  &nbsp;&nbsp;&nbsp;&nbsp; 
												  	   <aebiz:showTitle titleId="withdrawapply.m.canUseMoney"/>：<aebiz:showTitle titleId="pub.moneytype"/>${m.accountBalance}  &nbsp;&nbsp;&nbsp;&nbsp;
												  	</div>
											  </div>
											</div>
											<div class="row col-bothbB">
											 <div class="col-mi-2 col-sm-2"><aebiz:showTitle titleId="withdrawapply.qm.bankName"/></div>		 
											 <div class="col-mi-10 col-sm-10 col-bothbL">${m.bankName}</div>
											</div>
											<div class="row col-bothbB">
											 <div class="col-mi-2 col-sm-2"><aebiz:showTitle titleId="withdrawapply.m.accountName"/></div>		 
											 <div class="col-mi-10 col-sm-10 col-bothbL">${m.customerName}</div>
											</div>
											<div class="row col-bothbB">
											 <div class="col-mi-2 col-sm-2"><aebiz:showTitle titleId="withdrawapply.m.bankNo"/></div>		 
											 <div class="col-mi-10 col-sm-10 col-bothbL">${m.bankNo}</div>
											</div>
											<!--
											<div class="row col-bothbB logi-num">
											 <div class="col-mi-2 col-sm-2">手机号码</div>		 
											 <div class="col-mi-10 col-sm-10 col-bothbL"><i class="fa fa-phone"></i><input type="text" placeholder="请输入手机号码"/></div>
											</div>
											<div class="row col-bothbB logi-num">
											 <div class="col-mi-2 col-sm-2"><span class="imp-icon">*</span>短信验证码</div>		 
											 <div class="col-mi-10 col-sm-10 col-bothbL"><i class="fa fa-barcode"></i><input type="text" placeholder="输入验证码"/><button class="btn btn-warning btn-yzm">获取短信验证码</button></div>
											</div>
											-->
											<div class="row col-bothbB logi-num">
											 <div class="col-mi-2 col-sm-2"><span class="imp-icon">*</span><aebiz:showTitle titleId="withdrawapply.m.virtualPass"/></div>		 
											 <div class="col-mi-10 col-sm-10 col-bothbL"><i class="fa fa-lock"></i><input type="text" id="payPasswd" name="payPasswd" data-rule-required="true" placeholder="<aebiz:showTitle titleId="withdrawapply.m.inputVirtualPass"/>"/></div>
											</div>
											<div class="row col-bothbB">
											 <div class="col-mi-2 col-sm-2"></span><aebiz:showTitle titleId="withdrawapply.m.note"/></div>		 
											 <div class="col-mi-10 col-sm-10 col-bothbL">
											 <textarea rows="4" cols="4" name="note" id="note" class="form-control" data-rule-maxlength="100"></textarea>
											 </div>
											</div>
											<div class="row sub-box">
												<div class="col-mi-2 col-sm-2"></div>
												<div class="col-mi-10 col-sm-10"><button type="submit" class="btn btn-warning subbut" id="subut"><aebiz:showTitle titleId="withdrawapply.m.submitCommit"/></button></div>
											</div>											
										</div>
									</div><!--提现 end-->
								</form>
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
	<!-- 会员中心公用的js 5个-->
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/jquery.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/bootstrap.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/modernizr.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/king-common.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/demo-style-switcher/js/deliswitch.js"></script><!--页面右侧设置的js-->
		<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/validation/jquery.validate.min.js"></script><!--验证插件的js-->
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/validation/aebiz.validate.expand.js"></script><!--验证拓展插件的js-->
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/validation/aebiz.validate.js"></script><!--调用验证插件的js-->
	<!-- 当前页面使用的js 5个-->
	
<script>
</script>
</body>
</html>
<aebiz:showErrorMsg></aebiz:showErrorMsg>