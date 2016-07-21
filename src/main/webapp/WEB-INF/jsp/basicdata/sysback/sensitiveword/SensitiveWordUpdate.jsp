<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="sensitiveword.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="basicdata.title"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="sensitiveword.moduleName_CN"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="sensitiveword.m.sensitiveName"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></span></li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">								
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/sensitiveword/update" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>							
					<form:hidden path="uuid"/>	
							
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="sensitiveword.m.sensitiveName"/></label>
						<div class="col-sm-10">
							<form:input path="sensitiveName" class='form-control'  data-rule-required="true" data-rule-minlength="2" data-rule-maxlength="40"/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="sensitiveword.m.sensitiveLevel"/></label>
						<div class="col-sm-10">
							<div class="col-sm-2">
								<form:select path="sensitiveLevel" class='select2-me form-control' data-rule-required="true">								
									<form:option value="">--<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>--</form:option>
										<c:forEach items="${sensitiveLevelList}" var="dpp">	
											<form:option value="${dpp.value}">${dpp.name}</form:option>
										</c:forEach>											
								</form:select>		
							</div>		
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="sensitiveword.m.replaceContent"/></label>
						<div class="col-sm-10">
							<form:input path="replaceContent" class='form-control' data-rule-maxlength="40"/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="sensitiveword.m.description"/></label>
						<div class="col-sm-10">
							<form:input path="description" class='form-control' data-rule-maxlength="100"/>
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