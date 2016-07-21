<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/import/ListImport.jsp" %>
<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/aebiz/aebiz.validate.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/validation/jquery.validate.min.js"></script>

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
					<h1><aebiz:showTitle titleId="withdrawapply.qm.merchantBalance"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
				</div>
			</div>
			<div class="breadcrumbs">
				<ul>
					<li>
						<span><aebiz:showTitle titleId="withdrawapply.menuOne"/></span>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<span><aebiz:showTitle titleId="withdrawapply.menuTwo2"/></span>
						<i class="fa fa-angle-right"></i>							
					</li>							
					<li>
						<span><aebiz:showTitle titleId="withdrawapply.qm.merchantBalance"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
					</li>						
				</ul>				
			</div>
			
			<div class="row">
				<div class="col-sm-12">
					<div class="box box-bordered bordered-top">
						<div class="box-content nopadding">
							<form action="/storeback/storewithdrawapply/saveWithdrawApplyStoreBalance" method="POST" class='form-horizontal form-bordered form-validate'  id="bb">
								<div class="form-group">
									<label for="firstname" class="control-label col-sm-2"><aebiz:showTitle titleId="withdrawapply.qm.bankNo"/></label>
									<div class="col-sm-10">
										<div class="col-sm-2">
											<input type="text" name="companyAccountNo" id="companyAccountNo" value="${m.companyAccountNo}" class="form-control" readonly="readonly">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label for="firstname" class="control-label col-sm-2"><aebiz:showTitle titleId="withdrawapply.m.enabled"/></label>
									<div class="col-sm-10">
										<div class="col-sm-2">
											<input type="text" name="money" id="money" value="${m.virtualMoney}" class="form-control" readonly="readonly">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label for="firstname" class="control-label col-sm-2"><aebiz:showTitle titleId="withdrawapply.apply.money"/></label>
									<div class="col-sm-10">
										<div class="col-sm-2">
											<input type="text" name="apply" id="apply" class="form-control"  data-rule-required="true" data-rule-number="true">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label for="firstname" class="control-label col-sm-2"><aebiz:showTitle titleId="withdrawapply.m.note"/></label>
									<div class="col-sm-10">
										<div class="col-sm-5">
											<textarea rows="3" cols="5" name="note" id="note" class="form-control"></textarea>
										</div>
									</div>
								</div>
								<div class="form-actions col-sm-offset-2 col-sm-10">
									<button type="button" class="btn btn-primary" id="sumbtn"><aebiz:showTitle titleId="withdrawapply.m.nowApply"/></button>
									<button type="button" class="btn"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>				
	</div>
</div>
</body>	
<script>
		$("#sumbtn").click(function(){
				//余额不足
				if($("#money").val()*1 < $("#apply").val()*1){
					alert("<aebiz:showTitle titleId="withdrawapply.apply.notSufficient"/>");	
					return;
				}
				
				//金额必须大于0
				if($("#apply").val()*1 < 0){
					alert("<aebiz:showTitle titleId="withdrawapply.apply.gtZero"/>");	
					return;
				}
				
				//金额必须为数字
				if(isNaN($("#apply").val())){
					alert("<aebiz:showTitle titleId="withdrawapply.apply.number"/>");	
					return;
				}
				
				$("#bb").submit();
		});
</script>
				
</html>		

<aebiz:showErrorMsg></aebiz:showErrorMsg>