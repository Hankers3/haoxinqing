<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/static/basebusiness/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/static/basebusiness/ueditor/ueditor.all.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<c:choose>
					<c:when test="${templateType=='mobile'}">
						<h1><aebiz:showTitle titleId="basebusiness.showmessage.edit"/><aebiz:showTitle titleId="thirdInterfaceTemplate.m.smsMgr"/></h1>
					</c:when>
					<c:when test="${templateType=='email'}">
						<h1><aebiz:showTitle titleId="basebusiness.showmessage.edit"/><aebiz:showTitle titleId="thirdInterfaceTemplate.m.emailMgr"/></h1>
					</c:when>
					<c:otherwise>
						<h1><aebiz:showTitle titleId="basebusiness.showmessage.edit"/>消息模板</h1>
					</c:otherwise>				
				</c:choose>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="thirdInterfaceTemplate.menuOne"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="thirdInterfaceTemplate.menuTwo"/></span><i class="fa fa-angle-right"></i></li>
				<li>
					<c:choose>
						<c:when test="${templateType=='mobile'}">
							<span><aebiz:showTitle titleId="basebusiness.showmessage.edit"/><aebiz:showTitle titleId="thirdInterfaceTemplate.m.smsMgr"/></span>
						</c:when>
						<c:when test="${templateType=='email'}">
							<span><aebiz:showTitle titleId="basebusiness.showmessage.edit"/><aebiz:showTitle titleId="thirdInterfaceTemplate.m.emailMgr"/></span>
						</c:when>
						<c:otherwise>
							<span><aebiz:showTitle titleId="basebusiness.showmessage.edit"/>消息模板</span>
						</c:otherwise>				
					</c:choose>
				</li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">								
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/thirdInterfaceTemplate/update" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>							
					<form:hidden path="uuid"/>	
					<form:hidden path="templateType"/>
					<form:hidden path="templateName"/>
		
					<div class="form-group">
						<label for="templateId" class="control-label col-sm-2"><aebiz:showTitle titleId="thirdInterfaceTemplate.m.templateId"/></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<form:input path="templateId" class='form-control' readonly="true"/>
							</div>
						</div>
					</div>
					<!--
					<div class="form-group">
						<label for="templateName" class="control-label col-sm-2"><aebiz:showTitle titleId="thirdInterfaceTemplate.m.templateName"/></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<form:input path="templateName" class='form-control' data-rule-required="true" data-rule-maxlength="20"/>
							</div>
						</div>
					</div>
					-->
					
					<div class="form-group">
						<label for="templateTitle" class="control-label col-sm-2"><aebiz:showTitle titleId="thirdInterfaceTemplate.m.templateTitle"/></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<form:input path="templateTitle" class='form-control' data-rule-required="true" data-rule-maxlength="20" />
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="templateState" class="control-label col-sm-2"><aebiz:showTitle titleId="thirdInterfaceTemplate.m.templateState"/></label>
						<div class="col-sm-10">
							<div class="y_validatainput y_clear">
								<div class="col-sm-1 col-xs-4 check-line">
									<form:radiobutton path="templateState" id="c1" value="1" class="icheck-me" data-skin="square" data-color="blue"/>
									<label class='inline' for="c1"><aebiz:showTitle titleId="thirdInterfaceTemplate.m.startUse"/></label>
								</div>
									
								<div class="col-sm-1 col-xs-4 check-line">
									<form:radiobutton path="templateState" id="c2" value="0" class="icheck-me" data-skin="square" data-color="blue"/>
									<label class='inline' for="c2"><aebiz:showTitle titleId="thirdInterfaceTemplate.m.endUse"/></label>
								</div>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="position" class="control-label col-sm-2"><aebiz:showTitle titleId="thirdInterfaceTemplate.m.position"/></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<form:input path="position" class='form-control' data-rule-integernum="true" data-rule-maxlength="3"/>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="templateContent" class="control-label col-sm-2"><aebiz:showTitle titleId="thirdInterfaceTemplate.m.templateContent"/></label>
						<div class="col-sm-10">
							<c:choose>
							<c:when test="${templateType=='mobile'}">
								<form:textarea path="templateContent" id="templateContent"  cssStyle="width:500px;height:200px"  />
							</c:when>
							<c:when test="${templateType=='email'}">
								<form:textarea path="templateContent" id="templateContent" />
							</c:when>
							<c:otherwise>
								<form:textarea path="templateContent" id="templateContent"  cssStyle="width:500px;height:200px"  />							</c:otherwise>				
							</c:choose>
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
    	<c:if test="${templateType=='email'}">
    		var ue = UE.getEditor('templateContent'); 
    	</c:if>
		$(".cancel").click(function(){
			history.go(-1) ;
		});	    	
    });
</script>
</body>

</html>
<aebiz:showErrorMsg></aebiz:showErrorMsg>