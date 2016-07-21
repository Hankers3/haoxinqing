<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
				<h1>订单查看</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="ordermain.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="ordermain.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="ordermain.action.orderview"/></span>
				</li>
			</ul>
		</div>
		
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">							
				<form:form  action="" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>								
				
					<div class="form-group">
						<div class="col-sm-12">
							<i class="fa fa-user"></i>患者基本信息
						</div>
					</div>
				
					<table class="table table-bordered mb_0">
		      	<tbody>
							<tr>
								<th width="15%">患者ID</th>
								<td width="85%">${customer.customerId}</td>
							</tr>
							<tr>	
								<th>用户名</th>
								<td>
								${m.customerName}
								</td>
							</tr>
							<tr>
								<th>姓名</th>
								<td>
									${customerInfoModel.realName}
								</td>
							</tr>
							<tr>
								<th>性别</th>
								<td>
									<c:if test="${customerInfoModel.sex==1}">
			 		         	男
			 	          </c:if>
			          	<c:if test="${customerInfoModel.sex==2}">
			 	          	女
			          	</c:if>
								</td>
							</tr>
						  <tr>
								<th>出生日期</th>
								<td>
							 		${customerInfoModel.birthday}
								</td>
							</tr>
							<tr>
								<th>手机号</th>
								<td>
									${m.customerMobile}
								</td>
							</tr>
						</tbody>
					</table>	
		
					<div class="form-group">
						<div class="col-sm-12">
							<i class="fa fa-user"></i>医生基本信息	
						</div>
					</div>
					
					<table class="table table-bordered mb_0">
			      <tbody>
								<tr>
									<th width="15%">医生ID</th>
									<td width="85%">${staff.doctorNo}</td>
								</tr>
								<tr>	
									<th>姓名</th>
									<td>
										${staff.realName}
									</td>
								</tr>
								<tr>
									<th>医院</th>
									<td>
										${staff.hospitalName}
									</td>
								</tr>
									<tr>
									<th>科室</th>
									<td>
										${staff.departmentName}
									</td>
								</tr>
								<tr>
									<th>联系方式</th>
									<td>
										${staff.mobile}
									</td>
								</tr>
								<tr>
									<th>擅长</th>
									<td>
										${staff.mobile}
									</td>
								</tr>
								<tr>
									<th>标签</th>
									<td>
										<c:forEach items="${staff.doctorTags}" var="tag">
											 	<a href="javascript:;" class="btn " > ${tag.tagName}</a>
										</c:forEach>
									</td>
								</tr>
								<tr>
									<th>是否需要二次审核</th>
									<td>
										<c:if test="${m.checkType=='1'}">
											否
										</c:if>
										<c:if test="${m.checkType=='2'}">
											是
										</c:if>
									</td>
								</tr>
						</tbody>
					</table>	
			
					<div class="form-group">
						<div class="col-sm-12">
							<i class="fa fa-user"></i>订单详情
						</div>
					</div>
					
					<table class="table table-bordered mb_0">
			      <tbody>
								<tr>
									<th width="15%">套餐名</th>
									<td width="85%">${m.packgeName}</td>
								</tr>
								<tr>
									<th>套餐金额</th>
									<td>
										￥${m.totalMoney}
									</td>
								</tr>
								<tr>	
									<th>下单时间</th>
									<td>
										${m.orderTime}
									</td>
								</tr>
								<tr>	
									<th>服务结束时间</th>
									<td>
										${m.sendTime}
									</td>
								</tr>
								<tr>
									<th>订单状态</th>
									<td>
										<c:if test="${m.state=='1'}">
											待支付
										</c:if>
										<c:if test="${m.state=='2'}">
											订单取消
										</c:if>
										<c:if test="${m.state=='3'}">
											已支付,待审核
										</c:if>
										<c:if test="${m.state=='4'}">
											待执行
										</c:if>
										<c:if test="${m.state=='5'}">
											待回访
										</c:if>
										<c:if test="${m.state=='6'}">
											已完成
										</c:if>
									</td>
								</tr>
						</tbody>
					</table>	
			
					<div class="form-group">
						<div class="col-sm-12">
							<i class="fa fa-user"></i>套餐详情
						</div>
					</div>
			
					<table class="table table-bordered mb_0">
			      <tbody>
								<tr>
									<th width="15%">图文咨询次数</th>
									<td width="85%">无限</td>
								</tr>
								<tr>
								<th>电话咨询次数</th>
									<td>
										${pm.phoneTimes}
									</td>
								</tr>
								<tr>	
									<th>加号次数</th>
									<td>
										${pm.plusTimes}
									</td>
								</tr>
						</tbody>
					</table>	
				</form>
			</div>
			
			<div class="y_fixbtnbox">
				<div class="y_fixedbtn">		
					<c:if test="${m.state=='1'}">
						<button type="submit" class="btn btn-primary btn-large a_size_1" onclick="cancelOrder('${m.uuid}');" >关闭订单</button>
					</c:if>
					<button type="button" id="returenId" class="btn btn-primary btn-large a_size_1">返回</button>
				</div>
			</div>
		</div>
	
	
		<!--取消订单备注 end-->
		<div id="RemarkRefoud" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h3 id="user-infos">拒绝原因</h3>
					</div>
					<div class="modal-body">
						<div class="box">	
							<textarea name="note" id="note" class="form-control" rows="5"></textarea>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn moresearch" data-dismiss="modal">关闭</button>
						<button onclick="javascript:orderRefound('${m.uuid}','2');" class="btn btn-primary" data-dismiss="modal">提交</button>
					</div>
				</div>
			</div>
		</div>
	</body>
<!--取消订单备注 end-->
<script>
$(document).ready(function() {
	var returnType="${returnType}";
	$("#returenId").click(function(){
		if(returnType ==null || returnType==""){
			history.go(-1) ;
		}
	});
});
//重置密码
function addLog(uuid) {
	var note = $("#note1").val();
	//重置密码
	$.get("${pageContext.servletContext.contextPath}/sysback/order/addLog",
	{
		"uuid":uuid,
		"note":note,
		"operateType":"13",
		ranNum : Math.random()
	},
	function(data) {
		if (data == "success") {
			//刷新
			location.reload();
		} else {
			bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
		}
	});
}

//订单审核通过
function orderPass(orderUuid,state){
		var url="${pageContext.servletContext.contextPath}/sysback/order/checkOrder";
		$.post(url,{
				"note":"",
				"orderId":orderUuid,
				"state":state,
				ranNum : Math.random()
			},
			function(data){
				if(data=="success"){
					location.reload();
				}
			}
		);
}

//拒绝订单
function orderRefound(orderUuid,state){
		var note = $("#note").val();
		if(note=="" ||note==''){
			alert("请填写拒绝原因！");
			return;
		}
		var url="${pageContext.servletContext.contextPath}/sysback/order/checkOrder";
		$.get(url,
			{
				"note":note,
				"orderId":orderUuid,
				"state":state,
				ranNum : Math.random()
			},
			function(data){
				if(data=="success"){
					location.reload();
				}
			}
		);
}

//取消订单
function cancelOrder(orderUuid){
	bootbox.confirm("确认关闭订单", function(r){
  if(r) {
		var url="${pageContext.servletContext.contextPath}/sysback/order/orderCancel";
		$.get(url,{
				"orderId":orderUuid,
				ranNum : Math.random()
			},
			function(data){
				if(data=="success"){
					location.reload();
				}
			}
		);
	}   
  });   
}
	
</script>
</body>
</html>


