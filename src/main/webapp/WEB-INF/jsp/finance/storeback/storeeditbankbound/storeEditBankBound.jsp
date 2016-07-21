<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/import/ListImport.jsp" %>
<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/aebiz/aebiz.validate.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/validation/jquery.validate.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/storeback/js/aebiz.validate.expand.js"></script>
</head>

<body>
	<div id="navigation">
		<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeHead.jsp" %>
	</div>
	<div class="container-fluid" id="content">
		<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeLeft.jsp" %>
		<div id="main">	
			<div class="container-fluid">
				<div class="page-header">
					<div class="pull-left">
						<h1><aebiz:showTitle titleId="editbankbound.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
					</div>
				</div>
				<div class="breadcrumbs">
					<ul>
						<li>
							<span><aebiz:showTitle titleId="editbankbound.menuOne"/></span>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<span><aebiz:showTitle titleId="editbankbound.menuTwo"/></span>
							<i class="fa fa-angle-right"></i>							
						</li>							
						<li>
							<span><aebiz:showTitle titleId="editbankbound.moduleName_CN"/></span>
						</li>						
					</ul>				
				</div>
				
				<div class="row">
					<div class="col-sm-12">
						<div class="box box-bordered bordered-top">
							<div class="box-content nopadding">
								<form action="/store/storeEditBankBound/saveBankBound" method="POST" class='form-horizontal form-bordered form-validate'  id="bb">
									<div class="form-group">
										<input type="hidden" name="uuid" id="uuid" value="${m.uuid}">
										<label for="firstname" class="control-label col-sm-2"><aebiz:showTitle titleId="editbankbound.m.openAccountName"/></label>
										<div class="col-sm-10">
											<div class="col-sm-2">
												<input type="text" name="openAccountName" id="openAccountName" value="${m.openAccountName}" class="form-control" data-rule-required="true">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="firstname" class="control-label col-sm-2"><aebiz:showTitle titleId="editbankbound.m.companyAccountNo"/></label>
										<div class="col-sm-10">
											<div class="col-sm-2">
												<input type="text" name="companyAccountNo" id="companyAccountNo" value="${m.companyAccountNo}" class="form-control" data-rule-required="true" data-rule-number="true">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="firstname" class="control-label col-sm-2"><aebiz:showTitle titleId="editbankbound.m.bankNo"/></label>
										<div class="col-sm-10">
											<div class="col-sm-2">
												<input type="text" name="bankNo" id="bankNo" value="${m.bankNo}" class="form-control" data-rule-required="true" data-rule-number="true">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="firstname" class="control-label col-sm-2"><aebiz:showTitle titleId="editbankbound.m.openAccountBankName"/></label>
										<div class="col-sm-10">
											<div class="col-sm-2">
												<input type="text" name="openAccountBankName" id="openAccountBankName" value="${m.openAccountBankName}" class="form-control"  data-rule-required="true">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="firstname" class="control-label col-sm-2"><aebiz:showTitle titleId="editbankbound.m.bankArea"/></label>
										<div class="col-sm-10">
											<div class="col-sm-6">
											<aebiz:area checkProvince="${m.province }" checkCity="${m.city }" checkRegion="${m.region }"></aebiz:area>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="firstname" class="control-label col-sm-2"><aebiz:showTitle titleId="editbankbound.m.mobile"/></label>
										<div class="col-sm-10">
											<div class="col-sm-2">
												<input type="text" name="mobile" id="mobile" value="${m.mobile}" class="form-control" data-rule-required="true" data-rule-number="true" data-rule-mobilezh="true">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2"><aebiz:showTitle titleId="editbankbound.m.vilidateCode" />&nbsp;:</label>
										<div class="col-sm-10">
												<div class="col-sm-2">
													<input type="text" id="validateCode"  name="validateCode" value="${validateCode }" class="form-control y_width100" data-rule-required="true" data-rule-maxlength="6">   
												</div>
												<div class="prompt-text">
													<p class="col-sm-8" ><input class="btn btn-primary mr10" id="getCode" value="<aebiz:showTitle titleId='editbankbound.m.evilidateCode' />"/></p>
													<div id="success" style="display:none;"><i class="fa fa-check-square text-success"></i> </div>
												</div>
										</div>
									</div>
									<div class="form-group">
										<label for="firstname" class="control-label col-sm-2"><aebiz:showTitle titleId="editbankbound.m.description"/></label>
										<div class="col-sm-10">
											<div class="col-sm-4">
												<textarea rows="3" cols="18" name="description" id="description" class="form-control" data-rule-maxlength="500">${m.description}</textarea>
											</div>
										</div>
									</div>
									<div class="form-actions col-sm-offset-2 col-sm-10">
										<button type="button" class="btn btn-primary" id="sumbtn"><aebiz:showTitle titleId="basebusiness.showmessage.confirm"/></button>
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
				
				$("#bb").submit();
		});
		
		$("#getCode").click(function(){
			//执行倒计时方法
			time(this);
			var mobile =  $("input[name='mobile']").val();
			$.get("${pageContext.servletContext.contextPath}/storeback/storeEditBankBound/getCode",
				{
					"mobile":mobile,
					ranNum : Math.random()
				},
				function(data) {
					if (data == "success") {
						$("#success").show();
					} else {
						alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
					}
				});
		});
</script>

<script>
  	var wait=60;
	function time(o) {
      if (wait == 0) {
          o.removeAttribute("disabled");
          o.value = "重新获取验证码";
          wait = 60;
          $("#success").hide();
      } else {
          o.setAttribute("disabled", true);
          o.value = wait+"秒后可以重新发送";
          wait--;
          setTimeout(function() {
              time(o)
          },
          1000)
      }
  }
  </script>
				
</html>		
<aebiz:showErrorMsg></aebiz:showErrorMsg>