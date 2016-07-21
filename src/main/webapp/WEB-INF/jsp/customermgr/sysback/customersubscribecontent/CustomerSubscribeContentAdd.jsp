<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>

	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
	 
</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="customersubscribecontent.moduleName_CN"/></h1>
			</div>
		</div>
		
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="customersubscribecontent.menuOne"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="customersubscribecontent.menuTwo"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="customersubscribecontent.moduleName_CN"/></span></li>
			</ul>
		</div>		
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/customersubscribecontent/add" method="post" class='form-horizontal form-bordered form-validate'>						
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customersubscribecontent.m.subscribeNo"/></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<input type="hidden" name="subscribeNoHidden" value="true">
								<input type="text" name="subscribeNo" class="form-control" data-rule-required="true" data-rule-minlength="4" data-rule-maxlength="20">
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customersubscribecontent.m.subscribeName"/></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<input type="text" name="subscribeName" class="form-control" data-rule-required="true" data-rule-minlength="2" data-rule-maxlength="20">
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customersubscribecontent.m.subscribeType"/></label>
						<div class="col-sm-10">
							<div class="col-xs-3">
								<select name="subscribeType" id="subscribeType" class='form-control' data-rule-required="true">
									<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</option>
									<option value="1"><aebiz:showTitle titleId="customersubscribecontent.subtype.week"/></option>
									<option value="2"><aebiz:showTitle titleId="customersubscribecontent.subtype.month"/></option>
									<option value="3"><aebiz:showTitle titleId="customersubscribecontent.subtype.season"/></option>									
								</select>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customersubscribecontent.m.state"/></label>
						<div class="col-sm-10">
							<div class="y_validatainput y_clear">
								<div class="col-sm-1 col-xs-4 check-line">
									<input type="radio" id="c1" class='icheck-me' value="1" name="state" data-skin="square" data-color="blue" data-rule-required="true">
									<label class='inline' for="c1"><aebiz:showTitle titleId="customersubscribecontent.m.publish"/></label>
								</div>
									
								<div class="col-sm-1 col-xs-4 check-line">
									<input type="radio" id="c2" class='icheck-me' value="0" checked name="state" data-skin="square" data-color="blue" data-rule-required="true">
									<label class='inline' for="c2"><aebiz:showTitle titleId="customersubscribecontent.m.unpublish"/></label>
								</div>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customersubscribecontent.m.description"/></label>
						<div class="col-sm-10">
							<textarea rows="5" cols="" name="description" class="form-control"></textarea>
						</div>
					</div>
					
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="button" class="btn submit btn-primary " value='<aebiz:showTitle titleId="basebusiness.showmessage.add"/>'>
						<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
					</div>						
				</form>
			</div>
		</div>
	</div>
	
<script>
	$(".submit").click(function(){
		var subError = $("input[name='subscribeNoHidden']").val();
		if(subError == "true"){
			$("#mainForm").submit();
		}
	});

	$("input[name='subscribeNo']").blur(function(){
		$("span[name='subNoSpan']").remove();
		var subscribeNo=$("input[name='subscribeNo']").val();
		var uuid="";
		if(subscribeNo!=''){
			var url="${pageContext.servletContext.contextPath}/sysback/customersubscribecontent/checkSubscribeNo";
			$.get(url,{uuid:uuid,subscribeNo:subscribeNo,ranNum:Math.random()},function(data){
				if(data=="true"){
					$("input[name='subscribeNo']").after("<span name='subNoSpan'><font color='red'><aebiz:showTitle titleId="customersubscribecontent.subscribeNo.exist"/></font></span>");
					$("input[name='subscribeNoHidden']").val("false");
				}else{
					$("input[name='subscribeNoHidden']").val("true");
					$("span[name='subNoSpan']").remove();
				}
			});
		}
	});
	
	$(document).ready(function(){
		$(".cancel").click(function(){
			history.go(-1) ;
		});
	})
		
</script>
</body>

</html>

<aebiz:showErrorMsg></aebiz:showErrorMsg>