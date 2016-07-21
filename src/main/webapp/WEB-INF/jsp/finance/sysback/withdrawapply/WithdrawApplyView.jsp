<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>医生提现申请明细</h1>
			</div>
		</div>
		<!--修改-->

		<div class="breadcrumbs">
			<ul>
				<li><span>财务管理</span><i class="fa fa-angle-right"></i></li>
				<li><span>医生财务管理</span><i class="fa fa-angle-right"></i></li>
				<li><span>医生提现申请明细</span></li>
			</ul>
		</div>	
		
		<div class="box box-bordered bordered-top">	
			<div class="box-content nopadding" id="baseInfo">
				<form:form id="baseInfoForm" action="" method="post" commandName="m" class='form-horizontal form-validate form-bordered' enctype="multipart/form-data">							

					<!--医生基本信息-->
					<div class="form-group">
						<div class="col-sm-12">
							<div class="col-sm-3">
								<i class="fa fa-user"></i>医生基本信息
							</div>
						</div>
					</div>
					
					<!--流水号-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">医生ID</label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								&nbsp;${servicestaffModel.doctorNo}
							</div>
						</div>
					</div>
					
					<!--真实姓名-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">医生姓名</label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								&nbsp;${servicestaffModel.realName}
							</div>
						</div>
					</div>
					
					<!--医院-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">所在医院</label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								&nbsp;${servicestaffModel.hospitalName}
							</div>
						</div>
					</div>
					
					<!--科室-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">科室</label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								&nbsp;${servicestaffModel.departmentName}
							</div>
						</div>
					</div>
					
					<!--当前余额-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">当前余额</label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								&nbsp;${servicestaffModel.accountAmount}
							</div>
						</div>
					</div>						
				
					<!--提现申请-->
					<div class="form-group">
						<div class="col-sm-12">
							<i class="fa fa-lock"></i>提现申请
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">提现金额</label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								&nbsp;${withdrawApplyModel.applyMoney}
							</div>
						</div>
					</div>	

					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">持卡人姓名</label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								&nbsp;${bankRelationModel.realName}
							</div>
						</div>
					</div>	

					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">身份证号</label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								&nbsp;${bankRelationModel.ID}
							</div>
						</div>
					</div>	

					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">银行卡类型</label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								&nbsp;${bankRelationModel.bankName}
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">银行卡号</label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								&nbsp;${bankRelationModel.bankCode}
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">申请提现申请时间</label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								&nbsp;${withdrawApplyModel.applyTime}
							</div>
						</div>
					</div>
							 
				</form:form>
				
				<div class="form-actions col-sm-offset-2 col-sm-10">
					<c:if test="${withdrawApplyModel.state=='0'}">
						<button class="btn btn-primary" onclick="transfer();" title="确认发放" rel="tooltip">确认发放</button>					
					</c:if>
					<button type="button" class="btn cancel">返回</button>
				</div>
				
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
    
    
//提现
function transfer() {
		bootbox.confirm("请确认财务已将款项发放给用户，确认后用户的余额将被扣除", function(r){
        	if(r) {
        		//提现
							$.post(
					    	"${pageContext.servletContext.contextPath}/sysback/withdrawapply/transfer",
					    	{"uuid":'${withdrawApplyModel.uuid}',ranNum:Math.random()},	
						    function(data) {	     
							       if(data=='success') {
							       	location.href="${pageContext.servletContext.contextPath}/sysback/withdrawapply/toList";
							       }else{
						       		bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
						       }
						    }
						);	
        	}   
    });   
}
</script>
<aebiz:showErrorMsg></aebiz:showErrorMsg>