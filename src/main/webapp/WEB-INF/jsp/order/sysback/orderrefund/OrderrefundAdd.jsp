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
	<div class="container-fluid" id="OrderrefundId">
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
	<div class="box box-bordered">
		<div class="box-title">
			<h3><i class="fa fa-list-ul"></i><aebiz:showTitle titleId="orderrefund.moduleName_CN"/></h3>
		</div>
		<div class="box-content nopadding">
			<form id="mainForm5" action="#" method="post" class='form-horizontal form-bordered form-validate'>
					<!--订单号-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="orderrefund.m.orderMainUuid"/></label>
						<div class="col-sm-10">
							<div class="col-sm-3 mr10">
								<input type="text" id ="orderId" name="orderMainUuid" class="form-control">
							</div>
							<!--查询-->
							<div class="col-sm-3" style="padding-top:3px;">
								<a class="btn btn-primary search" onclick="toAdd()" title="orderrefund.m.query" rel="tooltip"><aebiz:showTitle titleId="orderrefund.m.query"/></a>	
							</div>
						</div>
					</div>
					<!--下单客户-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="orderrefund.m.name"/></label>
						<div class="col-sm-10">
						&nbsp;
						</div>
					</div>
					<!--联系电话-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="orderrefund.m.mobile"/></label>
						<div class="col-sm-10">
							&nbsp;
						</div>
					</div>
					<!--服务时间-->
					<div class="form-group">
						<label for="firstname" class="control-label col-sm-2"><aebiz:showTitle titleId="orderrefund.m.serviceTime"/></label>
						<div class="col-sm-10">
					&nbsp;
						</div>
					</div>
					<!--服务详细地址-->
					<div class="form-group">
						<label for="firstname" class="control-label col-sm-2"><aebiz:showTitle titleId="orderrefund.m.address"/></label>
						<div class="col-sm-10">
					&nbsp;
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
					<!--<input type="text" name="id" id="id" class="form-control ">-->
					<input type="text" name="refundPrice" class="form-control">
				</div>
			</div>
			<!--退款原因-->
			<div class="col-sm-8">	
				<label class="control-label fl pt_5"><aebiz:showTitle titleId="orderrefund.m.refundReson"/></label>
				<div class="col-sm-10">
					<!--<input type="text" name="id" id="id" class="form-control ">-->
					<input type="text" name="refundReson" class="form-control">
				</div>
			</div>
		</div>
	</div>
	
 </form>
</div>
	
	
<div id="show">
</div>	
	
	
	
</body>

</html>


<script>
    $(document).ready(function() {
		$(".cancel").click(function(){
			history.go(-1) ;
		});
    });
    
 
    
    function toAdd() {
			var orderId =$("#orderId").val();
			if(orderId.trim() == ""){
				alert("请输入订单号");
				return ;
			}
			$.get(
			"${pageContext.servletContext.contextPath}/sysback/order/toAddRefund",
			{
				"orderId":orderId,
				ranNum : Math.random()
			},
			function(data) {
						$("#OrderrefundId").hide();
						$("#show").html(data);
			});
		}
	
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>