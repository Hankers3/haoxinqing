<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/static/basebusiness/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/static/basebusiness/ueditor/ueditor.all.js"></script>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<c:choose>
					<c:when test="${templateType=='mobile'}">
						<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="thirdInterfaceTemplate.m.smsMgr"/></h1>
					</c:when>
					<c:when test="${templateType=='email'}">
						<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="thirdInterfaceTemplate.m.emailMgr"/></h1>
					</c:when>
					<c:otherwise>
						<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>消息模板</h1>
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
							<span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="thirdInterfaceTemplate.m.smsMgr"/></span>
						</c:when>
						<c:when test="${templateType=='email'}">
							<span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="thirdInterfaceTemplate.m.emailMgr"/></span>
						</c:when>
						<c:otherwise>
							<span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>消息模板</span>
						</c:otherwise>				
					</c:choose>
				</li>
			</ul>
		</div>		
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/thirdInterfaceTemplate/add" method="post" class='form-horizontal form-bordered form-validate'>						
					<input type="hidden" name="templateType" value="${templateType}">
				
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="thirdInterfaceTemplate.m.templateId"/></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<input type="text" name="templateId" class="form-control">
							</div>
						</div>
					</div>
					<!--
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="thirdInterfaceTemplate.m.templateName"/></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<input type="text" name="templateName" class="form-control">
							</div>
						</div>
					</div>-->
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="thirdInterfaceTemplate.m.templateTitle"/></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<input type="text" name="templateTitle" class="form-control">
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="thirdInterfaceTemplate.m.templateState"/></label>
						<div class="col-sm-10">
							<div class="y_validatainput y_clear">
								<div class="col-sm-1 col-xs-4 check-line">
									<input type="radio" id="c1" class='icheck-me' value="1" checked name="templateState" data-skin="square" data-color="blue">
									<label class='inline' for="c1"><aebiz:showTitle titleId="thirdInterfaceTemplate.m.startUse"/></label>
								</div>
									
								<div class="col-sm-1 col-xs-4 check-line">
									<input type="radio" id="c2" class='icheck-me' value="0" name="templateState" data-skin="square" data-color="blue">
									<label class='inline' for="c2"><aebiz:showTitle titleId="thirdInterfaceTemplate.m.endUse"/></label>
								</div>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="thirdInterfaceTemplate.m.position"/></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<input type="text" name="position" class="form-control">
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="thirdInterfaceTemplate.m.templateContent"/></label>
						<div class="col-sm-10">
							<c:choose>
							<c:when test="${templateType=='mobile'}">
								<textarea name="templateContent" style="width:500px;height:200px" id="templateContent"></textarea>
							</c:when>
							<c:when test="${templateType=='email'}">
								<textarea name="templateContent" id="templateContent"></textarea>
							</c:when>
							<c:otherwise>
								<textarea name="templateContent" style="width:500px;height:200px" id="templateContent"></textarea>
							</c:otherwise>				
							</c:choose>
						</div>
					</div>
					
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.add"/>'>
						<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
					</div>						
				</form>
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