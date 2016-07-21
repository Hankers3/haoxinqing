<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<head>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script>  
</head>
<body>

<div class="container-fluid">
	<div class="page-header">
		<div class="pull-left">
			<h1><aebiz:showTitle titleId="orderIncome.m.incomMeunOne"/></h1>
		</div>
		
		<!--<div class="pull-right">
			<ul class="stats">
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
				<span><aebiz:showTitle titleId="orderIncome.m.incomMeunTwo"/></span> 
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<span><aebiz:showTitle titleId="orderIncome.m.incomMeunThree"/></span> 
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<span><aebiz:showTitle titleId="orderIncome.m.incomMeunOne"/></span>
			</li>
		</ul>
	</div>
	<div class="y_clear">
		<div class="form-inline table_formnew">
		<form class="form-inline2" id="searchForm" action="${pageContext.servletContext.contextPath}/sysback/order/queryIncomeList/" method="post">
		<input type="hidden" name="pageShow" value="${wm.pageShow}">
			<div class="form-group">
				<input type="hidden" id="yearValue" value="${incomeYear}"/>
				<input type="hidden" id="monthValue" value="${incomeMonth}"/>
				<select name="incomeYear" id="incomeYear" class='form-control'>
				</select>
				<select name="incomeMonth" id="incomeMonth" class='form-control'>
				</select>
			</div>
			<div class="form-group">
				<button class="btn btn-primary search" title="<aebiz:showTitle titleId="orderrefund.m.query"/>" rel="tooltip"><aebiz:showTitle titleId="orderrefund.m.query"/></button>
			</div>
			<div class="form-group y_mr1l2">
				<label class="control-label pt_5"><aebiz:showTitle titleId="orderIncome.m.incomeTotalPaid"/>:</label>
				<strong class="font-red tot_price" style="vertical-align:middle;">¥${totalPaid}</strong>
			</div>
			</form>
		</div>
		<!--
		<div class="y_tablebtn">
			<a href='customerInfoImport.html' class='btn' rel='tooltip' title=''>导入</a>
			<a href='#' id="exportshow" class='btn' rel='tooltip' title=''>导出</a>
			<a href='#' class='btn' rel='tooltip' title=''>冻结</a>
			<a href='#' class='btn' rel='tooltip' title=''>解冻</a>
		</div>-->
	</div>
	<div class="table-responsive2">
		<table id="user" class="table table-bordered table-hover table-striped dataTable dataTable-nosort dataTable-reorder" data-nosort="">
			<thead>		
				<tr>
					<th><aebiz:showTitle titleId="orderIncome.m.incomeDate"/></th>
					<th><aebiz:showTitle titleId="orderIncome.m.incomeOrderTotalMoney"/></th>
					<th>-<aebiz:showTitle titleId="orderIncome.m.incomeintegralMoney"/></th>
					<th>-<aebiz:showTitle titleId="orderIncome.m.incomecouponMoney"/></th>
					<th>-<aebiz:showTitle titleId="orderIncome.m.incomeotherMoney"/></th>
					<th>=<aebiz:showTitle titleId="orderIncome.m.incomepaidMoney"/></th>
					<th><aebiz:showTitle titleId="orderIncome.m.incomealipayMoney"/></th>
					<th>+<aebiz:showTitle titleId="orderIncome.m.incomewxMoney"/></th>
					<th>=<aebiz:showTitle titleId="orderIncome.m.incomeTodayMoney"/></th>
					<th><aebiz:showTitle titleId="orderrouting.m.sysRouting"/></th>
					<th><aebiz:showTitle titleId="orderIncome.m.incomedifferenceMoney"/></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${wm.rows}" var="income">
				<tr>
					<td>${income.date}</td>
					<td>${income.orderTotalMoney}</td>
					<td>${income.integralTotalMoney}</td>
					<td>${income.couponTotalMoney}</td>
					<td>${income.otherTotalMoney}</td>
					<td>${income.paidTotalMoney}</td>
					<td>${income.alipayTotalMoney}</td>
					<td>${income.wxTotalMoney}</td>
					<td>${income.alipayTotalMoney+income.wxTotalMoney}</td>
					<td>${income.sysRoutingMoney}</td>
					<td>${income.orderTotalMoney-income.paidTotalMoney}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<aebiz:storePage listPath="sysback/order/incomeList" />
</div>
</body>
</html>
<script>
$(function(){
    var yearid = $('#incomeYear')    //年所在的控件
    var myDate = new Date();
    var year = myDate.getFullYear();
    var year_value = $('#yearValue').val(); 
    for(var i=2014;i<=(year+5);i++){
    	if(i== year && (year_value == "" || year_value == null)){
    		yearid.append('<option value="'+i+'" selected="selected"> '+i+'<aebiz:showTitle titleId="orderrouting.m.year"/></option>')
    	}else{
    		if(i ==year_value ){
    			yearid.append('<option value="'+i+'" selected="selected"> '+i+'<aebiz:showTitle titleId="orderrouting.m.year"/></option>')
    		}else{
    			yearid.append('<option value="'+i+'">'+i+'<aebiz:showTitle titleId="orderrouting.m.year"/></option>')
    		}
    	}
    }
})

$(function(){
    var monthid = $('#incomeMonth')    //年所在的控件
    var myDate = new Date();
    var month=myDate.getMonth()+1;
    var month_value = $('#monthValue').val(); 
    for(var i=1;i<=12;i++){
    	if(i<10){
    		if(i ==month && (month_value == "" || month_value == null)){
    			monthid.append('<option value="'+'0'+i+'" selected="selected">0'+i+'<aebiz:showTitle titleId="orderrouting.m.month"/></option>')
    		}else{
    			if(i==month_value){
    				monthid.append('<option value="'+'0'+i+'" selected="selected">0'+i+'<aebiz:showTitle titleId="orderrouting.m.month"/></option>')
    			}else{
    				monthid.append('<option value="'+'0'+i+'">0'+i+'<aebiz:showTitle titleId="orderrouting.m.month"/></option>')
    			}
    		}
    	}else{
    		if(i ==month && (month_value == "" || month_value == null)){
    			monthid.append('<option value="'+i+'" selected="selected">'+i+'<aebiz:showTitle titleId="orderrouting.m.month"/></option>')
    		}else{
    			if(i==month_value){
    				monthid.append('<option value="'+i+'" selected="selected">'+i+'<aebiz:showTitle titleId="orderrouting.m.month"/></option>')
    			}else{
    				monthid.append('<option value="'+i+'" >'+i+'<aebiz:showTitle titleId="orderrouting.m.month"/></option>')
    			}
    		}
    	}
    }
})
</script>