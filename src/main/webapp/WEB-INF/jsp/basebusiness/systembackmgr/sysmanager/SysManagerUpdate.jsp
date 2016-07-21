<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>

<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>   

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="sysmanager.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="sysmanager.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="sysmanager.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="sysmanager.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></span>
				</li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">								
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/sysmanager/update" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>							
					<form:hidden path="uuid"/>	
					<form:hidden path="pwd"/>	
							
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="sysmanager.m.id"/></label>
						<div class="col-sm-10">
							<form:input path="id" class='form-control'  data-rule-required="true" data-rule-minlength="5" data-rule-maxlength="20"/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="sysmanager.m.name"/></label>
						<div class="col-sm-10">
							<form:input path="name" class='form-control' data-rule-required="true" data-rule-minlength="2" data-rule-maxlength="20" />
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="sysmanager.m.departmentUuid"/></label>
						<div class="col-sm-10">							
							<form:select path="departmentUuid" class='select2-me form-control' data-rule-required="true">										
								<form:option value="">--<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>--</form:option>
								<c:forEach items="${returnDept}" var="sd">	
									<form:option value="${sd.uuid}">${sd.departmentName}</form:option>
								</c:forEach>	
							</form:select>													
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="sysmanager.m.mobile"/></label>
						<div class="col-sm-10">
							<form:input path="mobile" class='form-control' data-rule-maxlength="11" data-rule-minlength="11" data-rule-number="true" />
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="sysmenu.m.state"/></label>
						<div class="col-sm-10">
							<div class="y_validatainput y_clear">
								<div class="check-line col-xs-4 col-sm-1">
									<form:radiobutton path="state" class='icheck-me' data-skin="square" data-color="blue" value="1" />
									<label class="inline" for="state"><aebiz:showTitle titleId="basebusiness.showmessage.yes"/></label>
								</div>
								<div class="check-line col-xs-4 col-sm-1" >
									<form:radiobutton path="state" value="0" class='icheck-me' data-skin="square" data-color="blue"/>
									<label class="inline" for="state"><aebiz:showTitle titleId="basebusiness.showmessage.no"/></label>
								</div>
							</div>
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
	
	<script>
	  $(document).ready(function() {
			$(".cancel").click(function(){
				history.go(-1) ;
			});	    	
	  });
	</script>

	<aebiz:showErrorMsg></aebiz:showErrorMsg>

</body>
</html>


