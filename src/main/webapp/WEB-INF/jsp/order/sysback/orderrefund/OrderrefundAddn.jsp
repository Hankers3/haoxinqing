<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script>  
</head>

<body>
	<!--静态页面-->
	<div class="container-fluid" >
	<div class="page-header">
		<div class="pull-left">
			<h1><aebiz:showTitle titleId="orderrefund.moduleName_CN"/></h1>
		</div>
		<!--<div class="pull-right">
			<ul class="minitiles">
				<li class='grey'>
					<a href="#">
						<i class="fa fa-cogs"></i>
					</a>
				</li>
				<li class='lightgrey'>
					<a href="#">
						<i class="fa fa-globe"></i>
					</a>
				</li>
			</ul>
			<ul class="stats">
				<li class='satgreen'>
					<i class="fa fa-money"></i>
					<div class="details">
						<span class="big">$324,12</span>
						<span>Balance</span>
					</div>
				</li>
				<li class='lightred'>
					<i class="fa fa-calendar"></i>
					<div class="details">
						<span class="big">February 22, 2013</span>
						<span>Wednesday, 13:56</span>
					</div>
				</li>
			</ul>
		</div>-->
	</div>
	
	
	<div class="breadcrumbs">
		<ul>
			<li>
				<span><aebiz:showTitle titleId="orderrefund.menuOne"/></span>
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<span><aebiz:showTitle titleId="orderrefund.menuTwo"/></span>
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<span><aebiz:showTitle titleId="orderrefund.moduleName_CN"/></span>							
			</li>
		</ul>
	</div>
<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/order/AddRefund/${m.uuid}" method="get" class='form-horizontal form-bordered form-validate'>
	<div class="box box-bordered">
		<div class="box-title">
			<h3><i class="fa fa-list-ul"></i><aebiz:showTitle titleId="orderrefund.moduleName_CN"/></h3>
		</div>
		<div class="box-content nopadding">
				<!--订单号-->
				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="orderrefund.m.orderMainUuid"/></label>
					<div class="col-sm-10">
						<div class="col-sm-3 mr10">
							<input type="text" id ="orderMainUuid" name="uuid" class="form-control" value="${m.uuid}" readonly="true">
						</div> 
						<!--查看订单详情-->
						<a href="${pageContext.servletContext.contextPath}/sysback/order/view/${m.uuid}" target="_Blank" class='btn' rel='tooltip' title='<aebiz:showTitle titleId="ordermain.m.lookOver"/>'><aebiz:showTitle titleId="ordermain.m.lookOver"/></a>
					</div>
				</div>
				<!--下单客户-->
				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="orderrefund.m.name"/></label>
					<div class="col-sm-10">
					   ${t.name}
					</div>
				</div>
				<!--联系电话-->
				<div class="form-group">
					<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="orderrefund.m.mobile"/></label>
					<div class="col-sm-10">
					 ${t.mobile}
					</div>
				</div>
				<!--服务时间-->
				<div class="form-group">
					<label for="firstname" class="control-label col-sm-2"><aebiz:showTitle titleId="orderrefund.m.serviceTime"/></label>
					<div class="col-sm-10">
				    ${t.serviceTime}
					</div>
				</div>
				<!--服务详细地址-->
				<div class="form-group">
					<label for="firstname" class="control-label col-sm-2"><aebiz:showTitle titleId="orderrefund.m.address"/></label>
					<div class="col-sm-10">
				   ${t.addressDetail}
					</div>
				</div>
		</div>
	</div>
	
		<div class="box box-bordered">
			<div class="box-content ">
				<!--退款金额-->
				<div class="col-sm-4">	
					<label class="control-label fl pt_5"><aebiz:showTitle titleId="orderrefund.m.refundPrice"/></label>
					<div class="col-sm-10">
						<input type="hidden" id="refundPrice1" name="refundPrice1" class="form-control"  >
						<input type="text" id="refundMoney" name="refundMoney" class="form-control" placeholder="${m.totalMoney}" onblur="tocompare()" >
					</div>
				</div>
				<!--退款原因-->
				<div class="col-sm-8">	
					<label class="control-label fl pt_5"><aebiz:showTitle titleId="orderrefund.m.refundReson"/></label>
					<div class="col-sm-10">				
						<input type="hidden" id="refundReson1" name="refundReson1" class="form-control" >
						<input type="text" name="refundReson" id ="refundReson" class="form-control">
					</div>
				</div>
			</div>
		</div>
		<!--提交申请退款单-->
		<div class="form-actions col-sm-offset-2 col-sm-10">
				<input type="button" onclick="javascript:toUpdate();"  class="btn btn-primary" value='<aebiz:showTitle titleId="orderrefund.m.submitrefund"/>' readonly="readonly">
		</div>
 </form>
</div>
</body>
</html>
<script>
    $(document).ready(function() {
		$(".cancel").click(function(){
			history.go(-1) ;
		});
    });
		
		function toUpdate(){
	
			var refundPrice =$("#refundMoney").val();
			var defaultPrice=${m.totalMoney};
			if(refundPrice <= 0){
				alert("请输入正确的退款金额");
				return ;
			}
			if(refundPrice>defaultPrice){
				 bootbox.alert("<aebiz:showTitle titleId="orderrefund.m.tocompare"/>") ;
				}else{
				  $("#refundPrice1").val(refundPrice);
				  $("#refundReson1").val($("#refundReson").val());
					$("#mainForm").submit();
				}
		}
		
		 function tocompare() {
			var refundPrice =$("#refundMoney").val();
			var defaultPrice=${m.totalMoney};
			if(refundPrice>defaultPrice){
				bootbox.alert("<aebiz:showTitle titleId="orderrefund.m.tocompare"/>") ;
				}else{
					
				}
			
		} 
</script>




<aebiz:showErrorMsg></aebiz:showErrorMsg>