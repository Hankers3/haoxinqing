<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

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
					<h1><aebiz:showTitle titleId="storebondcharge.m.pay"/><aebiz:showTitle titleId="storebondcharge.moduleName_CN"/></h1>
				</div>
			</div>
			<div class="breadcrumbs">
				<ul>
					<li>
						<span><aebiz:showTitle titleId="storebondcharge.menuOne"/></span>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<span><aebiz:showTitle titleId="storebondcharge.menuTwo"/></span>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<span><aebiz:showTitle titleId="storebondcharge.m.pay"/><aebiz:showTitle titleId="storebondcharge.moduleName_CN"/></span>
					</li>
				</ul>
			</div>		
					
			<div class="box box-bordered bordered-top">
				<div class="box-content nopadding">																
					<form id="mainForm" action="${pageContext.servletContext.contextPath}/storeback/storebondcharge/add" method="post" class='form-horizontal form-bordered form-validate'>						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="storebondcharge.m.operAmount"/></label>
							<div class="col-sm-10">
								<div class="col-xs-2">
									<input type="text" name="operAmount" class="form-control" data-rule-number="true" data-rule-required="true" data-rule-maxlength="10">
								</div>
								<div class="col-xs-4">
									<span class="help-block pl_5"><aebiz:showTitle titleId="basebusiness.showmessage.yuan" /></span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="storebondcharge.m.payType"/></label>
							<div class="col-sm-10">
								<div class="y_shipttre y_clear">
									<div class="check-line y_mhfour nopadding">
										<input type="radio" id="payType1" name="payType" class='icheck-me' data-skin="square" data-color="blue" value="1" checked/> 
										<label class='inline' for="payType1"><aebiz:showTitle titleId="storebondcharge.m.online" /></label>
									</div>
									<div class="check-line y_mhfour nopadding">
										<input type="radio" id="payType2" name="payType" class='icheck-me' data-skin="square" data-color="blue" value="2" />
										<label class='inline' for="payType2"><aebiz:showTitle titleId="storebondcharge.m.offline" /></label>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="storebondcharge.m.note"/></label>
							<div class="col-sm-10">
								<div class="col-xs-5">
									<textarea name="note" cols="4" rows="4" class="form-control"  data-rule-maxlength="100"></textarea>
								</div>
							</div>
						</div>
						
						<div class="form-actions col-sm-offset-2 col-sm-10">
							<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="storebondcharge.m.submitBond"/>'>
							<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
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