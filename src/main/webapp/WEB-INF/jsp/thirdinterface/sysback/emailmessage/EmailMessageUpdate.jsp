<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="EmailMessage.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="EmailMessage.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="EmailMessage.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="EmailMessage.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></span>
				</li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">								
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/emailmessage/update" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>							
					<form:hidden path="uuid"/>	
							
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="EmailMessage.m.smtpService"/></label>
						<div class="col-sm-10">
							<div class="col-xs-3">
								<form:input path="smtpService" class='form-control' data-rule-required="true" data-rule-maxlength="30" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="EmailMessage.m.smtpUser"/></label>
						<div class="col-sm-10">
							<div class="col-xs-3">
								<form:input path="smtpUser" class='form-control' data-rule-required="true" data-rule-maxlength="30" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="EmailMessage.m.smtpPassword"/></label>
						<div class="col-sm-10">
							<div class="col-xs-3">
								<form:input path="smtpPassword" type="passWord" class='form-control' data-rule-required="true" data-rule-maxlength="30" onblur="checkpwd()"/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="EmailMessage.m.repeatPassWord"/></label>
						<div class="col-sm-10">
							<div class="col-xs-3">
								<form:input path="repeatPassWord" type="passWord" class='form-control' data-rule-required="true" data-rule-maxlength="30" onblur="checkpwd()"/>
							</div>
							<div class="col-xs-9">
								<p id="checkpwd" class="y_input-groupts text-danger"></p>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="EmailMessage.m.smtpAddress"/></label>
						<div class="col-sm-10">
							<div class="col-xs-3">
								<form:input path="smtpAddress" class='form-control' data-rule-required="true" data-rule-maxlength="30" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="EmailMessage.m.smtpUserName"/></label>
						<div class="col-sm-10">
							<div class="col-xs-3">
								<form:input path="smtpUserName" class='form-control' data-rule-required="true" data-rule-maxlength="30" />
							</div>
						</div>
					</div>
					
					<div class="form-actions col-sm-offset-2 col-sm-10 y_fixedbtn">
						<input type="submit" class="btn btn-primary btn-large a_size_1" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>
						<button type="button" class="btn cancel btn-large a_size_1"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
					</div>
							
				</form:form>
			</div>
		</div>
	</div>
</body>

</html>


<script>
    $(document).ready(function() {
			$(".cancel").click(function(){
				history.go(-1) ;
			});	    	
    });
    function checkpwd(){
    	var pwd1 = $("input[name='smtpPassword']").val();
    	var pwd2 = $("input[name='repeatPassWord']").val();
    	if(pwd2=="" || pwd1==""){
    		return;	
    	}
    	if(pwd1 != pwd2){
    		$("#checkpwd").text("2次密码输入不匹配……");	
    	}else{
    		$("#checkpwd").text("");		
    	}	
    }
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>