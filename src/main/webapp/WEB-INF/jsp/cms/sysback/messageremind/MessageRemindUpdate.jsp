<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>
<link  rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/storeback/css/plugins/icheck/all.css ">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js "></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js "></script>
</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="messageremind.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="messageremind.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="messageremind.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="messageremind.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></span>
				</li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">								
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/messageremind/update" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>							
					<form:hidden path="uuid"/>	
							
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="messageremind.m.mobileName"/></label>
						<div class="col-sm-5">
							<form:input path="mobileName" class='form-control'  />
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="messageremind.m.emailName"/></label>
						<div class="col-sm-5">
							<form:input path="emailName" class='form-control'  />
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="messageremind.m.innerMessageName"/></label>
						<div class="col-sm-5">
							<form:input path="innerMessageName" class='form-control'  />
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="messageremind.m.remindName"/></label>
						<div class="col-sm-5">
							<form:input path="remindName" class='form-control'  />
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="messageremind.m.accountType"/></label>
						<div class="col-sm-10">
							<span class="y_icheckbox"><form:radiobuttons  path="accountType" items="${accountTypes}" delimiter="&nbsp;&nbsp;&nbsp;" data-skin="square" data-color="blue" class='icheck-me' /></span>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="messageremind.m.type"/></label>
						<div class="col-sm-10">
							<span class="y_icheckbox"><form:radiobuttons  path="type" items="${types}" delimiter="&nbsp;&nbsp;&nbsp;" data-skin="square" data-color="blue" class='icheck-me' /></span>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="messageremind.m.status"/></label>
						<div class="col-sm-10">
							<span class="y_icheckbox"><form:radiobuttons  path="status" items="${states}" delimiter="&nbsp;&nbsp;&nbsp;" data-skin="square" data-color="blue" class='icheck-me' /></span>
							
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="messageremind.m.mobileCheck"/></label>
						<div class="col-sm-10">
							<span class="y_icheckbox"><form:radiobuttons  path="mobileCheck" items="${states}" delimiter="&nbsp;&nbsp;&nbsp;" data-skin="square" data-color="blue" class='icheck-me' /></span>
							
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="messageremind.m.emailCheck"/></label>
						<div class="col-sm-10">
							<span class="y_icheckbox"><form:radiobuttons  path="emailCheck" items="${states}" delimiter="&nbsp;&nbsp;&nbsp;" data-skin="square" data-color="blue" class='icheck-me' /></span>
							
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="messageremind.m.innerMessageCheck"/></label>
						<div class="col-sm-10">
							<span class="y_icheckbox"><form:radiobuttons  path="innerMessageCheck" items="${states}" delimiter="&nbsp;&nbsp;&nbsp;" data-skin="square" data-color="blue" class='icheck-me' /></span>
							
						</div>
					</div>
					
	
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>
						<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
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
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>