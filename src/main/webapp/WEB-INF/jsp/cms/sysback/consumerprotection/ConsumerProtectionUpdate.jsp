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
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>
</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="consumerprotection.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="consumerprotection.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="consumerprotection.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="consumerprotection.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></span>
				</li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">								
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/consumerprotection/updates" method="post" commandName="m" class='form-horizontal form-validate form-bordered' enctype="multipart/form-data">							
					<form:hidden path="uuid"/>	
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="consumerprotection.m.name"/></label>
						<div class="col-sm-10">
							<form:input path="name" class='form-control' data-rule-required="true" data-rule-maxlength="100"/>
						</div>
					</div>
					<div class="form-group">
						
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="consumerprotection.m.icon"/></label>
						<div class="col-sm-10">
							<div class="fileinput <c:choose><c:when test="${empty m.icon}">fileinput-new</c:when><c:otherwise>fileinput-exists</c:otherwise></c:choose>" data-provides="fileinput">
							<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 75px; height: 75px;">
								<c:if test="${!empty m.icon}"><img src="${m.iconUrl}" /></c:if>
							</div>
							<div>
								<span class="btn btn-default btn-file">
									<span class="fileinput-new">Select image</span>
									<span class="fileinput-exists">Change</span>
									<form:hidden path="icon"/>
									<input type="file" name="files"/>
								</span>
								<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
							</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">
							<aebiz:showTitle titleId="consumerprotection.m.needChooseProduct"/>
						</label>
						<div class="col-sm-10">
							<div class="check-line col-xs-4 col-sm-2">
								<input type="radio" id="needChooseProduct_1" name="needChooseProduct" class='icheck-me' data-skin="square" data-color="blue" value="0" />
								<label class='inline' for="needChooseProduct_1"><aebiz:showTitle titleId="basebusiness.showmessage.no"/></label>
							</div>
							<div class="check-line col-xs-4 col-sm-2">
								<input type="radio" id="needChooseProduct_2" name="needChooseProduct" class='icheck-me' data-skin="square" data-color="blue" value="1"/>
								<label class='inline' for="needChooseProduct_2"><aebiz:showTitle titleId="basebusiness.showmessage.yes"/></label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">
							<aebiz:showTitle titleId="consumerprotection.m.needAgreeProtocol"/>
						</label>
						<div class="col-sm-10">
							<div class="check-line col-xs-4 col-sm-2">
								<input type="radio" id="needAgreeProtocol_1" name="needAgreeProtocol" class='icheck-me' data-skin="square" data-color="blue" value="0" />
								<label class='inline' for="needAgreeProtocol_1"><aebiz:showTitle titleId="basebusiness.showmessage.no"/></label>
							</div>
							<div class="check-line col-xs-4 col-sm-2">
								<input type="radio" id="needAgreeProtocol_2" name="needAgreeProtocol" class='icheck-me' data-skin="square" data-color="blue" value="1"/>
								<label class='inline' for="needAgreeProtocol_2"><aebiz:showTitle titleId="basebusiness.showmessage.yes"/></label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="consumerprotection.m.protocolContent"/></label>
						<div class="col-sm-10">
							<form:input path="protocolContent" class='form-control'  data-rule-required="true" data-rule-maxlength="1000"/>
						</div>
					</div>
				
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="consumerprotection.m.note"/></label>
						<div class="col-sm-10">
							<form:input path="note" class='form-control' data-rule-required="true" data-rule-maxlength="200"/>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="consumerprotection.m.state"/></label>
						<div class="col-sm-10">
							<div class="check-line col-xs-4 col-sm-2">
								<input type="radio" id="state_1" name="state" class='icheck-me' data-skin="square" data-color="blue" value="0" />
								<label class='inline' for="state_1"><aebiz:showTitle titleId="basebusiness.showmessage.no"/></label>
							</div>
							<div class="check-line col-xs-4 col-sm-2">
								<input type="radio" id="state_2" name="state" class='icheck-me' data-skin="square" data-color="blue" value="1"/>
								<label class='inline' for="state_2"><aebiz:showTitle titleId="basebusiness.showmessage.yes"/></label>
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
</body>

</html>


<script>
    $(document).ready(function() {
    	//判断是否被选种
    	if("${m.needChooseProduct}"==0){
    		$("#needChooseProduct_1").iCheck('check');
    	}else{
    		$("#needChooseProduct_2").iCheck('check');
    	}
    	
    	if("${m.needAgreeProtocol}"==0){
    		$("#needAgreeProtocol_1").iCheck('check');
    	}else{
    		$("#needAgreeProtocol_2").iCheck('check');
    	}
    	
    	if("${m.state}"==0){
    		$("#state_2").iCheck('check');
    	}else{
    		$("#state_2").iCheck('check');
    	}
    	
		$(".cancel").click(function(){
			history.go(-1) ;
		});	    	
    });
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>