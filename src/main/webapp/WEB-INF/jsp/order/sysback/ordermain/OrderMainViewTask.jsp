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
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.buttonfloat.js"></script>
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
				<div  class="form-horizontal form-validate form-bordered">	
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
								${customer.realName}
								</td>
							</tr>
							<tr>
								<th>性别</th>
								<td>
									<c:if test="${customer.sex==1}">
					         	男
				          </c:if>
				        	<c:if test="${customer.sex==2}">
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
									${m.customerMobile}&nbsp;&nbsp;
									<a href="javascript:callMobile('${m.customerMobile}')" ><img src="${pageContext.servletContext.contextPath}/static/sysback/img/u181.png" width="30px" high="30px"></a>
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
								${staff.mobile}&nbsp;&nbsp;
								<a href="javascript:callDoctorMobile('${staff.mobile}')" ><img src="${pageContext.servletContext.contextPath}/static/sysback/img/u181.png" width="30px" high="30px"></a>
								</td>
							</tr>
							<tr>
								<th>擅长</th>
								<td>
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
								<th width="15%">预约日期</th>
								<td width="85%">${m.bookTime}</td>
							</tr>
							<tr>	
								<th>开始时间</th>
								<td>
									${m.receiveTime}
								</td>
							</tr>
							<tr>
								<th>预约时长</th>
								<td>
									${m.consultDuration}分钟
								</td>
							</tr>
							<tr>
								<th>咨询费用</th>
								<td>
									￥${m.totalMoney}
								</td>
							</tr>
							<tr>
								<th>咨询描述</th>
								<td>
									${m.consultContent}
								</td>
							</tr>
							<tr>
								<th>审核类型</th>
								<td>
									<c:if test="${m.checkType=='1'}">
										平台审核
									</c:if>
									<c:if test="${m.checkType=='2'}">
										医生审核
									</c:if>
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
				</div>
				
				<div class="y_fixbtnbox">
					<div class="y_fixedbtn">
							<button type="submit" class="btn btn-primary btn-large a_size_1" onclick="confMemberResume()" >取消患者禁听</button>
							<button type="submit" class="btn btn-primary btn-large a_size_1" onclick="quitConf()" >客服退出</button>
							<c:if test="${m.state=='4'}">
								<button type="submit" class="btn btn-primary btn-large a_size_1" onclick="orderPass('${m.uuid}','7');"  >转到动态任务</button>						
							</c:if>
							
							<c:forEach items="${csmList}" var="list" varStatus="varStatus">
		  					<c:if test="${list!=null  && list.state==1 }">
		  						<button type="submit" class="btn btn-primary btn-large a_size_1" onclick="ThreeSideCall('${list.customerServiceMobile}');" >${list.customerServiceName}</button>
							</c:if>
		  					</c:forEach>
					</div>
				</div>
			</div>
		</div>
		
	</div>
<input type="hidden" name="callSid" id="callSid" >
<input type="hidden" name="confid" id="confid" >
<input type="hidden" name="callId" id="callId" >

<script>
$(document).ready(function() {
	var returnType="${returnType}";
	$("#returenId").click(function(){
		if(returnType ==null || returnType==""){
			history.go(-1) ;
		}else if(returnType=="servicemanagelist"){
			location.href="${pageContext.servletContext.contextPath}/sysback/order/servicemanagelist/1/1/20?init=true";
		}else if(returnType=="list"){
			location.href="${pageContext.servletContext.contextPath}/sysback/order/list/0/1/20?init=true";
		}else if(returnType=="mainlist"){
			location.href="${pageContext.servletContext.contextPath}/sysback/order/list/1/20?init=true";
		}
	});
});

//邀请患者	
function callMobile(customerMobile){
	var url="${pageContext.servletContext.contextPath}/conrest/inviteJoinConf";
	 var confid = $("#confid").val();
	 var callSid = $("#callSid").val();
		$.post(url,{
			  "callMobile":customerMobile,
			  "confid":confid,
			  "callid":callSid,
			  "userType":"1",
			  ranNum : Math.random()
		},
			function(data){
				 var result = eval("("+data+")") ;
				 var callId =result.callSid;
				 $("#callId").val(callId);
				 if(result.result=="success"){
					 alert("邀请患者加入成功");
				 }
			}
		);
}	


//邀请医生
function callDoctorMobile(doctorMobile){
	var url="${pageContext.servletContext.contextPath}/conrest/inviteJoinConf";
	 var confid = $("#confid").val();
	 var callSid = $("#callId").val();
		$.post(url,{
			  "callMobile":doctorMobile,
			  "confid":confid,
			  "callid":callSid,
			  "userType":"2",
				ranNum : Math.random()
		},
			function(data){
				 var result = eval("("+data+")") ;	       
				 if(result.result=="success"){
					 alert("邀请医生加入成功");
				 }
			}
		);
}

//建立三方通话
function ThreeSideCall(kfMobile){
		var url="${pageContext.servletContext.contextPath}/conrest/createConf";
		$.post(url,{
				"kfMobile":kfMobile,
				"orderMainUuid":"${m.uuid}",
				ranNum : Math.random()
		},
			function(data){
				 var result = eval("("+data+")") ;	       
				 if(result.result=="success"){
					 alert("建立三方会议成功，邀请客服加入成功");
					 var callSid=result.callSid;
					 var confid=result.confid;
					 $("#callSid").val(callSid);
					 $("#confid").val(confid);
				 }
			}
		);
}

//客服退出
function quitConf(){
	var callSid  = $("#callSid").val();
	var confid = $("#confid").val();
	var url="${pageContext.servletContext.contextPath}/conrest/quitConf";
		$.post(url,{
				"callSid":callSid,
				"confid":confid,
				"consultDuration":"1",
				ranNum : Math.random()
		},
			function(data){
				 if(data=="success"){
					 alert("客服退出成功，计时开始");
					 
				 }
			}
		);
}

//取消患者禁听
function confMemberResume(){
	var callSid  = $("#callId").val();
	var confid = $("#confid").val();
	var url="${pageContext.servletContext.contextPath}/conrest/confMemberResume";
		$.post(url,{
				"callSid":callSid,
				"confid":confid,
				ranNum : Math.random()
		},
		function(data){
			 if(data=="success"){
				 alert("取消禁听成功");
			 }
		}
		);
}


</script>
</body>
</html>


