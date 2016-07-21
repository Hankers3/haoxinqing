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

<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>


</head>

<body>
	
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="giftpackage.moduleName_CN"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="giftpackage.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="giftpackage.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="giftpackage.moduleName_CN"/></span>
				</li>
			</ul>
		</div>		
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/giftpackage/adds" method="post" class='form-horizontal form-bordered form-validate' enctype="multipart/form-data">						
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="giftpackage.m.packageName"/></label>
						<div class="col-sm-10">
							<input type="text" name="packageName" value="${m.packageName}" class="form-control" data-rule-required="true" data-rule-maxlength="40"/>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="giftpackage.m.packageCount"/></label>
						<div class="col-sm-10">
							<div class="col-xs-2">
								<input type="text" name="packageCount" value="${m.packageCount}" class="form-control" data-rule-required="true" data-rule-positivenum="true" data-rule-maxlength="9">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="giftpackage.m.prizeType"/></label>
						<div class="col-sm-10">
							<div class="y_validatainput y_clear">
								<div class="check-line col-xs-4 col-sm-1">
									<input type="checkbox" id="prizeTypeS_1" name="prizeTypeS" class='icheck-me' data-skin="square" data-color="blue" value="1" data-rule-required="true"/>
									<label class='inline' for="prizeTypeS_1"><aebiz:showTitle titleId="giftpackage.m.prizeTypeIntegral"/></label>
								</div>
								<div class="check-line col-xs-4 col-sm-1">
									<input type="checkbox" id="prizeTypeS_2" name="prizeTypeS" class='icheck-me' data-skin="square" data-color="blue" value="2" data-rule-required="true"/>
									<label class='inline' for="prizeTypeS_2"><aebiz:showTitle titleId="giftpackage.m.prizeTypeCoupon"/></label>
								</div>
							</div>
						</div>
					</div>
					<div id="integralCountDIV" class="form-group" style="display:none">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="giftpackage.m.integralCount"/></label>
						<div class="col-sm-10">
							<div class="col-xs-2">
								<input type="text" name="integralCount" value="${m.integralCount}" value="0" class="form-control" data-rule-required="true" data-rule-positivenum="true" data-rule-maxlength="9">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="giftpackage.m.packageImage"/></label>
						<div class="col-sm-10">
							<div class="fileinput <c:choose><c:when test="${empty m.packageImage}">fileinput-new</c:when><c:otherwise>fileinput-exists</c:otherwise></c:choose>" data-provides="fileinput">
								<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 75px; height: 75px;">
									<c:if test="${!empty m.packageImage}"><img src="${m.packageImageUrl}" /></c:if>
								</div>
								<div>
									<span class="btn btn-default btn-file">
										<span class="fileinput-new">Select image</span>
										<span class="fileinput-exists">Change</span>
										<input type="hidden" name="packageImage" value="${m.packageImage}"/>
										<input type="file" name="files" data-rule-required="true">
									</span>
									<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="giftpackage.m.note"/></label>
						<div class="col-sm-10">
							<textarea type="text" name="note" value="${m.note}" class="form-control" data-rule-maxlength="250"></textarea>
						</div>
					</div>
					
					<div class="form-actions col-sm-offset-2 col-sm-10 y_fixedbtn">
						<input type="submit" class="btn btn-primary btn-large a_size_1" value='<aebiz:showTitle titleId="basebusiness.showmessage.add"/>'>
						<button type="button" class="btn cancel btn-large a_size_1"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
					</div>						
				</form>
			</div>
		</div>
	</div>
</body>

</html>


<script>
    $(document).ready(function() {
    	
    	$("#prizeTypeS_1").on("ifChecked",function(){
    			$("#integralCountDIV").show();
    	})
    	$("#prizeTypeS_1").on("ifUnchecked",function(){
    			$("input[name='integralCount']").val("0");
    			$("#integralCountDIV").hide();
    	})
    	
			$(".cancel").click(function(){
				history.go(-1) ;
			});
    });
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>