<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>

<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script> 
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datatable/jquery.dataTables.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.passwdcheck.js"></script> 

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>患者信息</h1>
			</div>
		</div>
		
		<div class="breadcrumbs">
			<ul>
				<li><span>患者系统</span><i class="fa fa-angle-right"></i></li>
				<li><span>患者管理</span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>患者信息</span></li>
			</ul>
		</div>	
		
		<div class="row">
			<div class="col-sm-12">
				<div class="box box-bordered bordered-top">
					<div class="box-content nopadding">
						<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/customercomb/addCustomer" method="post" class='form-horizontal form-bordered form-validate'>
							<div class="form-group">
								<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="customer.m.customerName"/></label>
								<div class="col-sm-10">
									<div class="col-sm-3">
										<input type="text" name="customerModel.customerName" class="form-control" data-rule-required="true" data-rule-minlength="2" data-rule-maxlength="20">
										<input type="hidden" name="nameHidden" value="true">
									</div>
									<div class="col-xs-4">
										<span class="help-block pl_5" id="nameTip"></span>
									</div>
								</div>
							</div>
					
							<div class="form-group">
								<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="customer.m.mobile" /></label>
								<div class="col-sm-10">
									<div class="col-sm-3">
										<input type="text" name="customerModel.mobile" class="form-control" data-rule-required="true" data-rule-mobilezh="true" maxlength="11">
										<input type="hidden" name="mobileHidden" value="true">
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="customer.m.email"/></label>
								<div class="col-sm-10">
									<div class="col-sm-3">
										<input type="text" name="customerModel.email" class="form-control" data-rule-required="true" data-rule-email="true" data-rule-maxlength="30">
										<input type="hidden" name="emailHidden" value="true">
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customer.m.password"/></label>
								<div class="col-sm-10">
									<div class="col-sm-3">
										<input type="password" id="password" name="customerModel.password"  class="form-control" data-rule-required="true" data-rule-minlength="6" data-rule-maxlength="20">
									</div>
									
									<div  id="pwdpower" class="paddword_leve paddword_leve_3" > 
										<span id="pweak" style=""><aebiz:showTitle titleId="system.m.pweak"/></span>
										<span id="pmedium" style=""><aebiz:showTitle titleId="system.m.pmedium"/></span>
										<span id="pstrong" style=""><aebiz:showTitle titleId="system.m.pstrong"/></span>
									</div> 	
								</div>
							</div>
							
							<div class="form-group">
								<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customer.m.confirmPwd"/></label>
								<div class="col-sm-10" id="confirmPwdError">
									<div class="col-sm-3">
										<input type="password" data-rule-equalTo="#password" class="form-control" data-rule-required="true">
									</div>
								</div>
							</div>
							
							<div class="form-actions col-sm-offset-2 col-sm-10">
								<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.return"/></button>
								<input type="button" class="btn submit btn-primary" value="<aebiz:showTitle titleId="basebusiness.showmessage.nextStep"/>">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script>
	$(document).ready(function(){
		 $(".submit").click(function(){
		 	var nameError=$("input[name='nameHidden']").val();
			var mobileError=$("input[name='mobileHidden']").val();
			var emailError=$("input[name='emailHidden']").val();
			if(nameError=="true"&&mobileError=="true"&&emailError=="true"){
				$("#mainForm").submit();
			}
		 });
		 
		 $("input[name='customerModel.customerName']").focus(function(){
			 $("#nameTip").empty();
			 $("#nameTip").append("<font color='red'><aebiz:showTitle titleId="customer.add.nameTip"/></font>");
		 });
		 
		$("input[name='customerModel.customerName']").blur(function(){
			$("span[name='namespan']").remove();
			var customerName=$("input[name='customerModel.customerName']").val();
			var nameReg=/^[\u4E00-\u9FA5\uF900-\uFA2D\w]{2,20}$/;
			if(customerName!=''&&nameReg.test(customerName)){
				$("#nameTip").empty();
				var url="${pageContext.servletContext.contextPath}/sysback/customer/checkCustomerName";
				$.get(url,{customerName:customerName,ranNum:Math.random()},function(data){
					if(data=="true"){
						$("input[name='customerModel.customerName']").after("<span name='namespan'><font color='red'><aebiz:showTitle titleId="customerModel.customerName.existed" /></font></span>");
						$("input[name='nameHidden']").val("false");
					}else{
						$("input[name='nameHidden']").val("true");
						$("span[name='namespan']").remove();
					}
				});
			}else{
				$("input[name='nameHidden']").val("false");
			}
		});
	
		$("input[name='customerModel.mobile']").blur(function(){
			$("span[name='mobilespan']").remove();
			var mobile=$("input[name='customerModel.mobile']").val();
			if(mobile!=''){
				var url="${pageContext.servletContext.contextPath}/sysback/customer/checkMobile";
				$.get(url,{mobile:mobile,ranNum:Math.random()},function(data){
					if(data=="true"){
						$("input[name='customerModel.mobile']").after("<span name='mobilespan'><font color='red'><aebiz:showTitle titleId="customerModel.mobile.existed" /></font></span>");
						$("input[name='mobileHidden']").val("false");
					}else{
						$("input[name='mobileHidden']").val("true");
						$("span[name='mobilespan']").remove();
					}
				});
			}
		});
	
		$("input[name='customerModel.email']").blur(function(){
			$("span[name='emailspan']").remove();
			var email=$("input[name='customerModel.email']").val();
			if(email!=''){
				var url="${pageContext.servletContext.contextPath}/sysback/customer/checkEmail";
				$.get(url,{email:email,ranNum:Math.random()},function(data){
					if(data=="true"){
						$("input[name='customerModel.email']").after("<span name='emailspan'><font color='red'><aebiz:showTitle titleId="customerModel.email.existed" /></font></span>");
						$("input[name='emailHidden']").val("true");
					}else{
						$("input[name='emailHidden']").val("true");
						$("span[name='emailspan']").remove();
					}
				});
			}
		});
		
		 //修改密码强弱校验  
		 $("#password").change(function(e) {
		 	EvalPwd($("#password").val()) ;
		 });  
		 
		 //修改密码强弱校验  
		 $("#password").keyup(function(e) {
		 	EvalPwd($("#password").val()) ;
		 });
		
		$(".cancel").click(function(){
			history.go(-1) ;
		});
	});
	
</script>
	
</body>
</html>
<aebiz:showErrorMsg></aebiz:showErrorMsg>