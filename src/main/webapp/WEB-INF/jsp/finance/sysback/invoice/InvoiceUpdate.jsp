<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<!doctype html>
<html>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>

<link  rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css ">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js "></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js "></script>
</head>
<body>
<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="invoice.moduleName_CN"/><aebiz:showTitle titleId="invoice.operate.goinvoice"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="storebondcharge.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="invoice.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="invoice.operate.goinvoice"/></span>
				</li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">								
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/invoice/invoice" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>							
					<form:hidden path="uuid"/>	
					<form:hidden path="invoiceState"/>	
					<form:hidden path="saleType"/>	
					<form:hidden path="invoiceTime"/>
					<form:hidden path="createTime"/>
					<form:hidden path="storeUuid"/>
					<form:hidden path="invoiceContent"/>
						
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="invoice.m.orderMainUuid"/></label>
						<div class="col-sm-3">
							<form:input path="orderMainUuid" class='form-control' readonly="true" />
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="invoice.m.invoiceNO"/></label>
						<div class="col-sm-3">
							<form:input path="invoiceNO" class='form-control'  data-rule-required="true" />
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="invoice.m.invoiceAmount"/></label>
						<div class="col-sm-3">
							<form:input path="invoiceAmount" class='form-control' readonly="true" />
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="invoice.m.invoiceType"/></label>
						<div class="col-sm-3">	
								<span class="y_icheckbox"><form:radiobuttons  path="invoiceType" items="${invoiceType}" delimiter="&nbsp;&nbsp;&nbsp;" data-skin="square" data-color="blue" class='icheck-me' /></span>
						</div>
					</div>
					
					<div class="form-group" id="" >
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="invoice.m.invoiceTitle"/></label>
						<div class="col-sm-3">	
								<form:input path="invoiceTitle" class='form-control' id=""/>
						</div>
					</div>		
								
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="invoice.m.invoiceCate"/></label>
						<div class="col-sm-3">
							<span class="y_icheckbox"><form:radiobuttons path="invoiceCate" items="${invoiceCateType}" delimiter="&nbsp;&nbsp;&nbsp;" data-skin="square" data-color="blue" class='icheck-me' /></span>
							
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="invoice.m.invoiceContent"/></label>
						<div class="col-sm-10">
							<span class="y_icheckbox"><form:checkboxes path="invoiceContents" items="${invoiceContent}" delimiter="&nbsp;&nbsp;&nbsp;"  data-skin="square" data-color="blue" class='icheck-me'/></span>
						</div>
					</div>			
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="invoice.operate.goinvoice"/>'>
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