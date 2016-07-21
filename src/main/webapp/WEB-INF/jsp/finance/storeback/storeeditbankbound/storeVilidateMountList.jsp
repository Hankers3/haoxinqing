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
<!-- 头部 -->
<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeHead.jsp"%>
<div class="container-fluid" id="content">
<!-- 左侧 -->
<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeLeft.jsp"%>
	<div id="main">	
		<div class='form-horizontal form-validate form-bordered'>
			<input type="hidden" name="uuid" id="uuid" value="${m.uuid}"/>
			<div class="container-fluid">	
				<div class="page-header">
					<div class="pull-left">
						<h1><aebiz:showTitle titleId="editbankbound.m.vilidate"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
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
							<span><aebiz:showTitle titleId="editbankbound.m.vilidate"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
						</li>						
					</ul>				
				</div>				
						
				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="editbankbound.m.openAccountName"/></label>
					<div class="col-sm-4">
						${m.openAccountName}
						<c:if test="${empty m.openAccountName}">&nbsp;</c:if>
					</div>
					<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="editbankbound.m.companyAccountNo"/></label>
					<div class="col-sm-4">
						${m.companyAccountNo}
						<c:if test="${empty m.companyAccountNo}">&nbsp;</c:if>
					</div>
				</div>		
				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="editbankbound.m.bankNo"/></label>
					<div class="col-sm-4">
						${m.bankNo}
						<c:if test="${empty m.bankNo}">&nbsp;</c:if>
					</div>
					<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="editbankbound.m.openAccountBankName"/></label>
					<div class="col-sm-4">
						${m.openAccountBankName}
						<c:if test="${empty m.openAccountBankName}">&nbsp;</c:if>
					</div>
				</div>
				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="editbankbound.m.bankArea"/></label>
					<div class="col-sm-4">
						${m.bankArea}
						<c:if test="${empty m.bankArea}">&nbsp;</c:if>
					</div>
					<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="editbankbound.m.mobile"/></label>
					<div class="col-sm-4">
						${m.mobile}
						<c:if test="${empty m.mobile}">&nbsp;</c:if>
					</div>
				</div>
				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="editbankbound.m.vilidateCode"/></label>
					<div class="col-sm-2">
						${m.vilidateCode}
						<c:if test="${empty m.vilidateCode}">&nbsp;</c:if>
					</div>
					<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="editbankbound.m.mobileVilidateState"/></label>
					<div class="col-sm-2">
						${m.mobileVilidateState}
						<c:if test="${empty m.mobileVilidateState}">&nbsp;</c:if>
					</div>
					<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="editbankbound.m.failTimes"/></label>
					<div class="col-sm-2">
						${m.failTimes}
						<c:if test="${empty m.failTimes}">&nbsp;</c:if>
						<input type="hidden" name="hdfailTimes" id="hdfailTimes" value="${m.failTimes}"/>
					</div>
				</div>
				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="editbankbound.m.applyTime"/></label>
					<div class="col-sm-4">
						${m.applyTime}
						<c:if test="${empty m.applyTime}">&nbsp;</c:if>
					</div>
					<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="editbankbound.m.vilidateTime"/></label>
					<div class="col-sm-4">
						${empty m.vilidateTime}
						<c:if test="${empty m.vilidateTime}">&nbsp;</c:if>
					</div>
				</div>
				
				<br/><br/>
				<!-- 金额验证 -->
				<label for="textfield" class="control-label col-sm-1"><aebiz:showTitle titleId="editbankbound.m.vilidateMount"/></label>
				<div class="col-sm-4">
					<input type="text" name="textarea" id="textarea" class="form-control" data-rule-required="true" data-rule-number="true" />
				</div>
				<div class="col-sm-4">
					<button class="btn" id="butText"><aebiz:showTitle titleId="basebusiness.showmessage.confirm"/></button>
				</div>
				
			</div>
		</div>
  </div>
</body>

</html>
<script>
//验证金额
$().ready(function(){
	$("#butText").click(function(){
		var uuid = $("#uuid").val();
		var value = $("#textarea").val();
		var failTimes = $("#hdfailTimes").val();
		if(failTimes*1 > 2){
			alert("<aebiz:showTitle titleId="editbankbound.m.freeze"/>");
			return;
		}else{
			var reUrl = "${pageContext.servletContext.contextPath}/storeback/storeEditBankBound/updataVilidateMount"
			$.get(
				reUrl,
				{
					"uuid" : uuid,
					"vilidateMount":value,
					ranNum : Math.random()
				},
				function(data) {
					if(data == "success"){
						alert("<aebiz:showTitle titleId="editbankbound.m.vilidateSuccess"/>");
						window.location.reload();
					}else if(data == "fail"){
						alert("<aebiz:showTitle titleId="editbankbound.m.vilidateFail"/>");
						window.location.reload();	
					}else if(data == "freeze"){
							alert("<aebiz:showTitle titleId="editbankbound.m.freeze"/>");
							window.location.reload();
					}
			});
		}
	});
});
</script>
<aebiz:showErrorMsg></aebiz:showErrorMsg>
