<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImport.jsp" %>
<!-- colorbox图片弹出框 -->
<link  href="${pageContext.request.contextPath }/static/usercenter/css/colorbox.css" rel="stylesheet" type="text/css"/>

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
							<div class="main-header">
								<h2>绑定银行卡</h2>
								<em>Bind the bank card</em>
							</div>
							<c:if test="${!empty customerAuthModel }">
								<div class="main-content">
									<form id="basic-form" class="form-horizontal y_setpassd form-validate" action="${pageContext.servletContext.contextPath}/usercenter/customerbankrel/add" method="post">
										<input type="hidden" id="idCard1" name="idCard1" value="${customerAuthModel.certImage1Url }" >
										<input type="hidden" id="idCard2" name="idCard2" value="${customerAuthModel.certImage2Url }" >
										<%-- <input type="hidden" id="cardTypeName" name="cardTypeName" value="${customerAuthModel.certName }" ><!--  --> --%>
										<div class="form-group">
											<label for="bank_name" class="col-xs-3 control-label">开户人姓名：</label>
											<div class="col-xs-1">
												<p class="y_hasuremob">${customerAuthModel.realName }</p>
												<input type="hidden" id="openAccountName" name="openAccountName" value="${customerAuthModel.realName }" class="form-control"  data-rule-maxlength="20" data-rule-required="true">
												
											</div>
											<div class="col-xs-8">
												<span class="text-999 inblock control-label">注:只能绑定实名认证人的银行卡</span>
											</div>
										</div>
										<div class="form-group">
											<label for="open_bank" class="col-xs-3 control-label">开户行：</label>
											<div class="col-xs-4">
												<input type="text" id="openAccountBank" name="openAccountBank" value="${m.openAccountBank }" class="form-control" data-rule-maxlength="30" data-rule-required="true">
											</div>
										</div>
										<div class="form-group">
											<label for="bank_number" class="col-xs-3 control-label">银行卡号：</label>
											<div class="col-xs-4">
												<input type="text" id="cardNo" name="cardNo" value="${m.cardNo }" class="form-control" data-rule-maxlength="40" data-rule-required="true">
											</div>
										</div>
										<c:if test="${customerAuthModel.certType == '1' }">
											<div class="form-group">
												<label for="password2" class="col-mi-3 control-label">身份证号：</label>
												<div class="col-mi-4">
													<p class="y_hasuremob">${customerAuthModel.certCode }</p>
													<input type="hidden" id="idcardNo" name="idcardNo" value="${customerAuthModel.certCode }" >
												</div>
											</div>
										</c:if>
										<c:if test="${customerAuthModel.certType != '1' }">
											<div class="form-group">
												<label for="id_number" class="col-xs-3 control-label">身份证号：</label>
												<div class="col-xs-4">
													<input type="text" id="idcardNo" name="idcardNo" value="${m.idcardNo }"  class="form-control" data-rule-maxlength="20"  data-rule-required="true">
												</div>
											</div>
										</c:if>
										
										<div class="form-group">
											<label for="password2" class="col-xs-3 control-label">证件照片1：</label>
											<div class="col-xs-9 row">
												<div class="col-xs-5 y_picwidh">
													<div class="y_realnamepic y_seebig"><img src="${customerAuthModel.certImage1Url }" width=120 height=90><a class="y_seebtn colorbox-image back-color" href="${customerAuthModel.certImage1Url }" rel="group1"><i class="fa fa-search"></i></a></div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label for="password2" class="col-xs-3 control-label">证件照片2：</label>
											<div class="col-xs-9 row">
												<div class="col-xs-5 y_picwidh">
													<div class="y_realnamepic y_seebig"><img src="${customerAuthModel.certImage2Url }" width=120 height=90><a class="y_seebtn colorbox-image back-color" href="${customerAuthModel.certImage2Url }" rel="group1"><i class="fa fa-search"></i></a></div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label for="phone_number" class="col-xs-3 control-label">手机号：</label>
											<div class="col-xs-4">
												<input type="text" id="mobile" name="mobile"  value="${m.mobile }" class="form-control" data-rule-mobilezh="true" data-rule-required="true">
											</div>
										</div>
										<div class="form-group" >
											<label  class="col-xs-3 control-label"><aebiz:showTitle titleId="editbankbound.m.vilidateCode" />：</label>
											<div class="col-sm-2">
												<input type="text" id="vilidateCode" name="vilidateCode" value="${m.vilidateCode }" class="form-control" data-rule-required="true" data-rule-maxlength="6">
											</div>
											<p class="col-sm-6" ><input class="btn btn-danger mr10" id="getCode" value="<aebiz:showTitle titleId='editbankbound.m.evilidateCode' />"/>
										</div>
										<div class="form-group" >
											<label  class="col-xs-3 control-label"></label>
											<div class="col-sm-8">
												<div id="success" style="display:none;"><i class="fa fa-check-square text-success"></i> <aebiz:showTitle titleId="editbankbound.m.send" /></div></p>
											</div>
										</div>
										<div class="row"><p class="col-xs-9 col-xs-offset-3"><button id="SaveChange" type="submit" class="btn btn-custom-primary">提交验证</button></p></div>
									</form>
								</div><!-- /main-content -->
							</c:if>
							
							<c:if test="${empty customerAuthModel }">
								<div class="main-content">
									<div class="y_ordsuccess alert alert-fail">
										<span class="y_odsicon text-fail"><i class="fa fa-check-circle"></i></span>
										<div class="y_odsuccms">
											<h2 class="text-fail"> <aebiz:showTitle titleId="customerbankrel.m.pleaseApprove"/></h2>
											<!--这里是访问的会员系统的链接,以后需要修改成会员系统的链接  -->
											<p><a href="${pageContext.servletContext.contextPath}/usercenter/customerauth/toCustomerAuth/${uuid}" class="btn btn-custom-primary"><aebiz:showTitle titleId="customerbankrel.m.toApprove"/></a></p>
										</div>
									</div>
								</div>
							</c:if>
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
	
	<script>
    $(document).ready(function() {
		$(".cancel").click(function(){
			history.go(-1) ;
		});
		
		$("#getCode").click(function(){
			//执行倒计时方法
			time(this);
			var mobile =  $("input[name='mobile']").val();
			$.get("${pageContext.servletContext.contextPath}/usercenter/customerbankrel/getCode",
				{
					"mobile":mobile,
					ranNum : Math.random()
				},
				function(data) {
					if (data == "success") {
						$("#success").show();
					} else {
						alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
					}
				});
		});
    });
</script>
<script>
  	var wait=60;
	function time(o) {
      if (wait == 0) {
          o.removeAttribute("disabled");
          o.value = "重新获取验证码";
          wait = 60;
          $("#success").hide();
      } else {
          o.setAttribute("disabled", true);
          o.value = wait+"秒后可以重新发送";
          wait--;
          setTimeout(function() {
              time(o)
          },
          1000)
      }
  }
  </script>
</body>
</html>


<aebiz:showErrorMsg></aebiz:showErrorMsg>