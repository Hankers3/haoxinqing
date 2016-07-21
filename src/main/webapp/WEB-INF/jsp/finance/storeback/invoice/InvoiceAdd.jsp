<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/import/AddImport.jsp" %>

<link  rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/storeback/css/plugins/icheck/all.css ">
<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/icheck/jquery.icheck.min.js "></script>
<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/aebiz/aebiz.checkbox.js "></script>

</head>

<body>
<!-- 头部 -->
<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeHead.jsp"%>
<div class="container-fluid" id="content">
<!-- 左侧 -->
<%@include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeLeft.jsp"%>
			<div id="main">	
				<div class="container-fluid">
				<div class="page-header">
					<div class="pull-left">
						<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/></h1>
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
							<span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/></span>
						</li>
					</ul>
				</div>		
						
				<div class="box box-bordered bordered-top">
					<div class="box-content nopadding">																
						<form id="mainForm" action="${pageContext.servletContext.contextPath}/storeback/invoice/toSave" method="post" class='form-horizontal form-bordered form-validate'>						
							<div class="form-group">
								<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="invoice.buyerName"/></label>
								<div class="col-sm-10">
									<input type="text" name="buyerName" class="form-control" >
								</div>
							</div>
							<div class="form-group">
								<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="invoice.orderAmount"/></label>
								<div class="col-sm-10">
									<input type="text" name="orderAmount" class="form-control" >
								</div>
							</div>
							<div class="form-group">
								<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="invoice.orderTime"/></label>
								<div class="col-sm-10">
									<input type="text" name="oderTime" class="form-control">
								</div>
							</div>
							<div class="form-group">
									<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="invoice.m.invoiceType"/></label>
									<div class="col-sm-10">
										<c:forEach items="${invoiceCateType}" var="item" varStatus="i">
											<span class="y_icheckbox"><div><input type="radio" name="invoiceCate" id="${i.index}invoiceCate" value="${item}" checked="checked" data-skin="square" data-color="blue" class='icheck-me'></div><label for="${i.index}invoiceCate">${item}</label></span>
										</c:forEach>
									</div>
							</div>
							<div class="form-group">
								<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="invoice.m.invoiceTitle"/></label>
								<div class="col-sm-10">
									<c:forEach items="${invoiceType}" var="item" varStatus="i">
										<span class="y_icheckbox"><div><input type="radio" name="invoiceType" id="${i.index}invoiceType" value="${item}" checked="checked" data-skin="square" data-color="blue" class='icheck-me'></div><label for="${i.index}invoiceType">${item}</label></span>
									</c:forEach>
								</div>
							</div>
							<div class="form-group">
								<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="invoice.m.invoiceContent"/></label>
								<div class="col-sm-10">		
									<c:forEach items="${invoiceContent}" var="item" varStatus="i">
										<span class="y_icheckbox"><div><input type="checkbox" name="invoiceContents" id="${i.index}invoiceContents" value="${item}" data-skin="square" data-color="blue" class='icheck-me'></div><label for="${i.index}invoiceContents">${item}</label></span>
									</c:forEach>
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
</div>
</body>

</html>