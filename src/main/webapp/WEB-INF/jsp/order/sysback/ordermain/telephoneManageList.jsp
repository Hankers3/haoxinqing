<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<!-- 日期插件 -->
<script src="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/datepicker/datepicker.css"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datepicker/bootstrap-datepicker.js"></script>
</head>
<body>

<div class="container-fluid">
	<div class="page-header">
		<div class="pull-left">
			<h1>电话回访管理</h1>
		</div>
	</div>
	<div class="breadcrumbs">
		<ul>
			<li>
				<span>客服系统</span>
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<span>回访任务管理</span>
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<span>电话回访管理</span>							
			</li>
		</ul>
	</div>

	<div class="row">
		<div class="col-sm-12">
			<div class="box">
				<div class="box-content nopadding y_tableser">

					<!--订单搜索-->
					<form class="form-inline2" id="searchForm" action="${pageContext.servletContext.contextPath}/sysback/order/query/" method="post">
						<div class="y_clear">
							<div class="form-inline table_formnew">
								<input type="hidden" name="orderState" value="5">
								<input type="hidden" name="pageShow" value="${wm.pageShow}">

								<div class="form-group">
									<label class="control-label">患者姓名：</label>
									<input type="text" name="customerName" id="customerName" class="form-control" value="${qm.customerName}">
								</div>
						   		
						   		<div class="form-group">
									<label class="control-label">患者手机：</label>
									<input type="text" name="customerMobile" id="mobile" class="form-control " value="${qm.customerMobile}">
						   		</div>
						   		
						   		<div class="form-group">
									<label class="control-label">医生姓名：</label>
									<input type="text" name="serviceStaffName" id="serviceStaffName" class="form-control" value="${qm.serviceStaffName}">
						   		</div>
						   		
						   		<div class="form-group">
									<label class="control-label">医生手机号：</label>
									<input type="text" name="serviceStaffMobile" id="serviceStaffMobile" class="form-control" value="${qm.serviceStaffMobile}">
						   		</div>
						   		
						   		<div  class="form-group">		
									<label class="control-label">预约时间：</label>
									<input type="text" name="beginTime" id="beginTime" class="form-control datepick" />
								  <label class="control-label">至</label>
									<input type="text" name="endTime" id="endTime" class="form-control datepick"  />
									</div>
						   		
								<div class="form-group">
									<a class="btn btn-primary search"  onclick="submitForm()" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip" type="submit" ><aebiz:showTitle titleId="basebusiness.showmessage.query"/></a>
									<a class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></a>									
								</div>
							</div>
						</div>
					</form>

					<!--订单列表-->
					<div class="tab-content y_tabdatable">
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>
								<tr>
									<th>患者姓名</th>
									<th>患者手机号</th>
									<th>医生姓名</th>
									<th>医院名称</th>
									<th>预约日期</th>
									<th>开始时间</th>
									<th>咨询时长</th>
									<th>咨询描述</th>
									<th>下单时间</th>
									<th>订单金额</th>
									<th>订单状态</th>
									<th class='hidden-480'><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
								</tr>
							</thead>
							<tbody>
							<c:choose>
								<c:when test="${fn:length(wm.rows)>0}" >
								<c:forEach items="${wm.rows}" var="orderMain">
								<input type="hidden" value="${orderMain.orderTime}" id="${orderMain.uuid}"/>
								<tr>
									<td>
										${orderMain.customerName}
									</td>	
									<td>
										${orderMain.customerMobile}
									</td>
									<td>
										${orderMain.doctorName}
									</td>	
									<td>
										${orderMain.hospitalName}
									</td>
									<td>
										${orderMain.bookTime}
									</td>	
									<td>
										${orderMain.receiveTime}
									</td>
									<td>
										${orderMain.consultDuration}分钟
									</td>	
									<td>
										${orderMain.content}
									</td>
									<td>
										${orderMain.orderTime}
									</td>	
									<td>
										${orderMain.totalMoney}
									</td>	
									<td>
										<c:if test="${orderMain.state=='1'}">
											待支付
										</c:if>
										<c:if test="${orderMain.state=='2'}">
											订单取消
										</c:if>
										<c:if test="${orderMain.state=='3'}">
											已支付
										</c:if>
										<c:if test="${orderMain.state=='4'}">
											待执行
										</c:if>
										<c:if test="${orderMain.state=='5'}">
											待回访
										</c:if>
										<c:if test="${orderMain.state=='6'}">
											已完成
										</c:if>
									</td>
									<td class="y_odbtnbx">
										<a href="${pageContext.servletContext.contextPath}/sysback/order/viewTelTask/${orderMain.uuid}" class='btn' rel='tooltip' title='执行'>执行</a>
										
									</td>
								</tr>
								</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<th colspan="12">
											暂无相关信息
										</th>	
									</tr>
								</c:otherwise>			
							</c:choose>
							</tbody>
						</table>
					</div>

				</div>
		  	</div>
		</div>
	</div>
	
	<aebiz:storePage listPath="sysback/order/list/" />	
		<div id="modal-stars" class="modal fade">
			<div id="show">
			</div>	
		</div>	

</div>
<script>
	function clearSearch(){
			$("#customerName").val("");
			$("#state").val("");
			$("#mobile").val("");
			$("#serviceStaffName").val("");
			$("#serviceStaffMobile").val("");
			$("#checkType").val("");
			$("#beginTime").val("");
			$("#endTime").val("");
	}
	

  function toAdd(uuid) {
		$.get(
		"${pageContext.servletContext.contextPath}/sysback/orderrevisit/revisitcl",
		{
			"uuid":uuid,
			ranNum : Math.random()
		},
		function(data) {
					
					$("#show").html(data);
		});
	}
	
	function submitForm(){
			var province=$("#province").val();
			var city=$("#city").val();
			if(province=='true'&&city=="false"){
				alert("查询地区至少选择第二级查询");
			}else{
				$("#searchForm").submit();
			}
	}
	
	Date.prototype.format1 = function(format){ 
		var o = { 
		"M+" : this.getMonth()+1, //month 
		"d+" : this.getDate(), //day 
		"h+" : this.getHours(), //hour 
		"m+" : this.getMinutes(), //minute 
		"s+" : this.getSeconds(), //second 
		"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
		"S" : this.getMilliseconds() //millisecond 
		} 

		if(/(y+)/.test(format)) { 
		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
		} 

		for(var k in o) { 
		if(new RegExp("("+ k +")").test(format)) { 
		format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
		} 
		} 
		return format; 
		} 
	
	
	function toWuxiao(orderUuid){
		$.get(
			"${pageContext.servletContext.contextPath}/sysback/order/toInvalid",
			{
				"uuid":orderUuid,
				ranNum : Math.random()
			},
			function(data) {
				location.reload();
			});
	}
</script>	
</body>
</html>
