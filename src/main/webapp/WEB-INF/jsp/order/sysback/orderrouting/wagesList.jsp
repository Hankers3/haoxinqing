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
			<h1><aebiz:showTitle titleId="orderrouting.m.wages"/></h1>
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
				<span><aebiz:showTitle titleId="orderrouting.menuOne"/></span> 
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<span><aebiz:showTitle titleId="orderrouting.menuTwo"/></span> 
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<span><aebiz:showTitle titleId="orderrouting.m.wages"/></span>
			</li>
		</ul>
	</div>
	<div class="y_clear">
	<form class="form-inline2" id="searchForm" action="${pageContext.servletContext.contextPath}/sysback/orderrouting/query/" method="post">
	<input type="hidden" name="pageShow" value="${wm.pageShow}">
		<div class="form-inline table_formnew">
			<div class="form-group">
				<input type="hidden" id="yearValue" value="${routYear}"/>
				<input type="hidden" id="monthValue" value="${routMonth}"/>
				<select name="routYear" id="routYear" class='form-control'>
				</select>
				<select name="routMonth" id="routMonth" class='form-control'>
				</select>
			</div>
			<div class="form-group">
				<button class="btn btn-primary search" title="<aebiz:showTitle titleId="orderrefund.m.query"/>" rel="tooltip"><aebiz:showTitle titleId="orderrefund.m.query"/></button>
			</div>
			<div class="form-group y_mr1l2">
				<label class="control-label pt_5"><aebiz:showTitle titleId="orderrouting.m.wagestotalmoney"/>:</label>
				<strong class="font-red tot_price" style="vertical-align:middle;">¥${totalRouting}</strong>
			</div>
		</div>
		
		<div class="y_tablebtn">
			<!--<a href='customerInfoImport.html' class='btn' rel='tooltip' title=''>导入</a>
			<a href='#' id="exportshow" class='btn' rel='tooltip' title=''>导出</a>
			<a href='#' class='btn' rel='tooltip' title=''>冻结</a>
			<a href='#' class='btn' rel='tooltip' title=''>解冻</a>-->
		</div>
		</form>
	</div>
	<div class="table-responsive2">
		<table id="user" class="table table-bordered table-hover table-striped dataTable dataTable-nosort dataTable-reorder" data-nosort="">
			<thead>		
				<tr>
					<th><aebiz:showTitle titleId="performance.m.serviceStaffName"/></th>
					<th><aebiz:showTitle titleId="orderrouting.m.wagesStaffSex"/></th>
					<th><aebiz:showTitle titleId="orderrouting.m.mobile"/></th>
					<th><aebiz:showTitle titleId="orderrouting.m.wagesStaffCardId"/></th>
					<th><aebiz:showTitle titleId="orderrouting.m.wagesStaffYfMoney"/></th>
					<th><aebiz:showTitle titleId="orderrouting.m.wagesStaffLjMoney"/></th>
					<th><aebiz:showTitle titleId="orderrouting.m.wagesStaffLjOper"/></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${wm.rows}" var="wages">
				<tr>
					<td>${wages.serviceStaffName}</td>
					<td>
					<c:if test="${wages.staffSex == 1}"><aebiz:showTitle titleId="orderrouting.m.male"/></c:if>
					<c:if test="${wages.staffSex == 2}"><aebiz:showTitle titleId="orderrouting.m.female"/></c:if>
					<c:if test="${wages.staffSex == 3}"><aebiz:showTitle titleId="orderrouting.m.secrecy"/></c:if>
					</td>
					<td>${wages.serviceStaffMobile}</td>
					<td>${wages.staffIdCard}</td>
					<td>${wages.oneStaffRoutPrice}</td>
					<td>${wages.totalRoutingMoney}</td>
					<td><a onclick="javascript:toView('${wages.serviceStaffUuid}');" class='btn' rel='tooltip' title='<aebiz:showTitle titleId="ordermain.m.lookOver"/>'  data-toggle="modal" data-target="#modal-user"><aebiz:showTitle titleId="ordermain.m.lookOver"/></a></td>
				</tr>
			</c:forEach>	
			</tbody>
		</table>
	</div>
	<aebiz:storePage listPath="sysback/orderrouting/wagesList" />
</div>
	
<!--高级搜索-->
<div id="modal-user" class="modal fade">
	<div id="showWages"></div>
</div>
<!--高级搜索-->

</body>
</html>
 <script>
$(function(){
    var yearid = $('#routYear')    //年所在的控件
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
    var monthid = $('#routMonth')    //年所在的控件
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
 <script type="text/javascript">
   	function toView(customerUuid){	
	 	var year = $('#routYear').val(); 
	 	var month = $('#routMonth').val(); 
	 	var time = year+"-"+month ;
		var url="${pageContext.servletContext.contextPath}/sysback/orderrouting/searchWages/"+customerUuid+"/"+time;
		$.post(url,
		{
			ranNum : Math.random()
		},
		function(data){
			$("#showWages").html(data);
		}
		);
		
	}
 
</script>
