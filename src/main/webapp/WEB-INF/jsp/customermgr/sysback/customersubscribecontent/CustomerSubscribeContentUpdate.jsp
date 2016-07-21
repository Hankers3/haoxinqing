<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.edit"/><aebiz:showTitle titleId="customersubscribecontent.moduleName_CN"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="customersubscribecontent.menuOne"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="customersubscribecontent.menuTwo"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="basebusiness.showmessage.edit"/><aebiz:showTitle titleId="customersubscribecontent.moduleName_CN"/></span></li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">								
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/customersubscribecontent/update" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>							
					<form:hidden path="uuid"/>	
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customersubscribecontent.m.subscribeNo"/></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<input type="hidden" name="subscribeNoHidden" value="true">
								<form:input path="subscribeNo" class='form-control' data-rule-required="true" data-rule-minlength="4" data-rule-maxlength="20"/>
								<span id='subSpan'></span>
							</div>
						</div>
					</div>		
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customersubscribecontent.m.subscribeName"/></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<form:input path="subscribeName" class='form-control' data-rule-required="true" data-rule-minlength="2" data-rule-maxlength="20"/>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customersubscribecontent.m.subscribeType"/></label>
						<div class="col-sm-10">
							<div class="col-xs-3">
								<form:select path="subscribeType" id="questionType" class="form-control" data-rule-required="true">
									<form:option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"></aebiz:showTitle>-</form:option>
									<form:option value="1"><aebiz:showTitle titleId="customersubscribecontent.subtype.week"></aebiz:showTitle></form:option>
									<form:option value="2"><aebiz:showTitle titleId="customersubscribecontent.subtype.month"></aebiz:showTitle></form:option>
									<form:option value="3"><aebiz:showTitle titleId="customersubscribecontent.subtype.season"></aebiz:showTitle></form:option>
								</form:select>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customersubscribecontent.m.state"/></label>
						<div class="col-sm-10">
							<div class="col-sm-1">
								<div class="check-line">
									<form:radiobutton path="state" id="c1" value="1" class="icheck-me" data-skin="square" data-color="blue"/>
									<label class='inline' for="c1"><aebiz:showTitle titleId="basebusiness.showmessage.yes" /></label>
								</div>
							</div>
							
							<div class="col-sm-1">
								<div class="check-line">
									<form:radiobutton path="state" id="c2" value="0" class="icheck-me" data-skin="square" data-color="blue"/>
									<label class='inline' for="c2"><aebiz:showTitle titleId="basebusiness.showmessage.no" /></label>
								</div>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customersubscribecontent.m.description"/></label>
						<div class="col-sm-10">
							<form:textarea path="description" class="form-control" rows="5"/>
						</div>
					</div>
	
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="button" class="btn btn-primary submit" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>
						<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
					</div>
				</form:form>
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
		$("#subSpan").empty();
		var subscribeNo=$("input[name='subscribeNo']").val();
		var uuid=$("#uuid").val();
		if(subscribeNo!=''){
			var url="${pageContext.servletContext.contextPath}/sysback/customersubscribecontent/checkSubscribeNo";
			$.get(url,{uuid:uuid,subscribeNo:subscribeNo,ranNum:Math.random()},function(data){
				if(data=="true"){
					$("#subSpan").append("<font color='red'><aebiz:showTitle titleId="customersubscribecontent.subscribeNo.exist"/></font>");
					$("input[name='subscribeNoHidden']").val("false");
				}else{
					$("input[name='subscribeNoHidden']").val("true");
					$("#subSpan").empty();
				}
			});
		}
	});

    $(document).ready(function() {
		$(".cancel").click(function(){
			history.go(-1) ;
		});	    	
    });
</script>

</body>

</html>
<aebiz:showErrorMsg></aebiz:showErrorMsg>