<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/import/AddImport.jsp" %>
<!-- 单选和多选 -->
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/storeback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/aebiz/aebiz.checkbox.js"></script>
</head>

<body>
<!-- 头部 -->
<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeHead.jsp"%>
<div class="container-fluid" id="content">
<!-- 左侧 -->
<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeLeft.jsp"%>
	<div id="main">	
		<div class="container-fluid">
			<div class="page-header">
				<div class="pull-left">
					<h1><aebiz:showTitle titleId="storeinvoiceset.moduleName_CN"/></h1>
				</div>
			</div>
			<div class="breadcrumbs">
				<ul>
					<li>
						<span><aebiz:showTitle titleId="storeinvoiceset.menuOne"/></span>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<span><aebiz:showTitle titleId="storeinvoiceset.menuTwo"/></span>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<span><aebiz:showTitle titleId="storeinvoiceset.moduleName_CN"/></span>
					</li>
				</ul>
			</div>		
			
			<div class="box box-bordered bordered-top">
				<div class="box-content nopadding">																
					<form id="mainForm" action="${pageContext.servletContext.contextPath}/storeback/storeinvoiceset/add" method="post" class='form-horizontal form-bordered form-validate'>						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="storeinvoiceset.m.invoiceType"/></label>
							<div class="col-sm-10">
							<c:forEach items="${web.invoice_type_list}" var="invoiceType" varStatus="content">
								<div class="check-line">
									<input type="checkbox" name="invoiceType" value="${invoiceType}" <c:if test="${fn:contains(m.invoiceType, invoiceType)}">checked=checked</c:if> id="type${content.index}" class='icheck-me' data-skin="square" data-color="blue" >
									<label class='inline' for="type${content.index}">${invoiceType}</label>
								</div>
							</c:forEach>
							</div>
						</div>
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="storeinvoiceset.m.invoiceCate"/></label>
							<div class="col-sm-10">
								<div class="check-demo-col">
									<c:forEach items="${web.invoice_head_list}" var="invoiceHead" varStatus="content" >
										<div class="check-line">
											<input type="checkbox" name="invoiceCate" value="${invoiceHead}" <c:if test="${fn:contains(m.invoiceCate, invoiceHead)}">checked=checked</c:if> id="head${content.index}" class='icheck-me' data-skin="square" data-color="blue">
											<label class='inline' for="head${content.index}">${invoiceHead}</label>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="storeinvoiceset.m.invoiceContent"/></label>
							<div class="col-sm-10">
								<div class="check-demo-col">
									<c:forEach items="${web.invoice_content_list}" var="invoiceContent" varStatus="content" >
										<div class="check-line">
											<input type="checkbox" name="invoiceContent" value="${invoiceContent}"  <c:if test="${fn:contains(m.invoiceContent, invoiceContent)}">checked=checked</c:if> id="content${content.index}" class='icheck-me' data-skin="square" data-color="blue">
											<label class='inline' for="content${content.index}">${invoiceContent} </label>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
						
						<div class="form-actions col-sm-offset-2 col-sm-10">
							<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>
						</div>						
					</form>
				</div>
			</div>
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
</body>

</html>


<aebiz:showErrorMsg></aebiz:showErrorMsg>