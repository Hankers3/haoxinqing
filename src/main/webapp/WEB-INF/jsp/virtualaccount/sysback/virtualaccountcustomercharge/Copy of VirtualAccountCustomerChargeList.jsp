<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<!-- 日期插件 -->
<script src="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/datepicker/datepicker.css"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datepicker/bootstrap-datepicker.js"></script>
</head>
<body>

<div class="container-fluid">
	<div class="page-header">
		<div class="pull-left">
			<h1><aebiz:showTitle titleId="virtualaccountcustomercharge.charge.menuThree"/></h1>
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
				<span><aebiz:showTitle titleId="virtualaccountcustomercharge.charge.menuOne"/></span> 
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<span><aebiz:showTitle titleId="virtualaccountcustomercharge.charge.menuTwo"/></span> 
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<span ><aebiz:showTitle titleId="virtualaccountcustomercharge.charge.menuThree"/></span>
			</li>
		</ul>
	</div>
	<div class="y_clear">
		<form class="form-inline2" id="searchForm" action="${pageContext.servletContext.contextPath}/sysback/virtualaccountcharge/query/" method="post">
		<input type="hidden" name="pageShow" value="${wm.pageShow}">
			<div class="form-inline table_formnew">
				<div class="form-group">
					<label class="control-label"><aebiz:showTitle titleId="virtualaccountcustomercharge.charge.customername"/>:</label>
					<input type="text" name="userName" id="userName" class="form-control" value="${qm.userName}" >
				</div>
				<div class="form-group">
					<label class="control-label"><aebiz:showTitle titleId="virtualaccountcustomercharge.charge.mobile"/>:</label>
					<input type="text" name="mobile" id="mobile" class="form-control" value="${qm.mobile}" >
					<input type="hidden" name="mobile_q" id="mobile_q" class="form-control" value="EQ">
				</div>
				<div class="form-group">
					<button class="btn btn-primary search" title="查询" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
					<a class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></a>
				</div>
			</div>
			<!--
			<div class="y_tablebtn">
				<a href='customerInfoImport.html' class='btn' rel='tooltip' title=''>导入</a>
				<a href='#' id="exportshow" class='btn' rel='tooltip' title=''>导出</a>
				<a href='#' class='btn' rel='tooltip' title=''>冻结</a>
				<a href='#' class='btn' rel='tooltip' title=''>解冻</a>
			</div>-->
		</form>
	</div>
	<div class="table-responsive2">
		<table id="user" class="table table-bordered table-hover table-striped dataTable dataTable-nosort dataTable-reorder" data-nosort="7">
			<thead>		
				<tr>
					<th><aebiz:showTitle titleId="virtualaccountcustomercharge.charge.customername"/></th>
					<th><aebiz:showTitle titleId="virtualaccountcustomercharge.charge.mobile"/></th>
					<th><aebiz:showTitle titleId="virtualaccountcustomercharge.m.operAmount"/></th>
					<!--<th><aebiz:showTitle titleId="virtualaccountcustomercharge.m.createTime"/></th>-->
					<th><aebiz:showTitle titleId="virtualaccountcustomercharge.charge.accountbalance"/></th>
					<th><aebiz:showTitle titleId="virtualaccountcustomercharge.charge.chargetimes"/></th>
					<th><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${wm.rows}" var="vacc">
				<tr>
					<td>${vacc.customerName} <c:if test="${empty vacc.customerName}" >&nbsp;</c:if></td>
					<td>${vacc.mobile} <c:if test="${empty vacc.mobile}" >&nbsp;</c:if></td>
					<td>${vacc.operAmount} <c:if test="${empty vacc.operAmount}" >&nbsp;</c:if></td>
					<!--<td>${vacc.createTime } <c:if test="${empty vacc.createTime}" >&nbsp;</c:if></td>-->
					<td>${vacc.accountBalance} <c:if test="${empty vacc.accountBalance}" >&nbsp;</c:if></td>
					<td>${vacc.chargeTimes} <c:if test="${empty vacc.chargeTimes}" >&nbsp;</c:if></td>
					<td><a  onclick="javascript:toView('${vacc.customerUuid}');" class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.view"/>'  data-toggle="modal" data-target="#modal-user"><aebiz:showTitle titleId="basebusiness.showmessage.view"/></a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<aebiz:storePage listPath="sysback/virtualaccountcharge/accountchargelist" />
</div>
	
<!--明细查看start-->
<div id="modal-user" class="modal fade">
	<div id="showcharges"></div>
</div>
<!--明细查看end-->
<script>
	function clearSearch(){
		$("#userName").val("");
		$("#mobile").val("");
	}
	
	function toView(customerUuid){	
		var url="${pageContext.servletContext.contextPath}/sysback/virtualaccountcharge/searchcusstomercharges/"+customerUuid;
		$.post(url,
		{
			ranNum : Math.random()
		},
		function(data){
			$("#showcharges").html(data);
		}
		);
		
	}
</script>
</body>
</html>