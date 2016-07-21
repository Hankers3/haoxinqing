<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>
<!--设置浮动按钮-->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.buttonfloat.js"></script>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>加号详情</h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span>患者管理</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>加号管理</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>加号详情</span>
					<i class="fa fa-angle-right"></i>
				</li>
				
			</ul>
		</div>
		
		<div  class="form-horizontal form-bordered border1-top">	
			<div class="form-group" style="border-right: 1px solid #ddd;">
				<div class="col-sm-12">
					<i class="fa fa-user"></i>患者基本信息
				</div>
			</div>		
			<table class="table table-bordered mb_0">
	      <tbody>
						<tr>	
							<th width="15%">用户名</th>
							<td width="85%">
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
		</div>
				
		<div  class="form-horizontal form-bordered border1-top">	
			<div class="form-group" style="border-right: 1px solid #ddd;">
				<div class="col-sm-12">
					<i class="fa fa-user"></i>医生基本信息
				</div>
			</div>	
			<table class="table table-bordered mb_0">
	      <tbody>
						<tr>
							<th width="15%">医生ID</th>
							<td width="85%">${servicestaffModel.doctorNo}</td>
						</tr>
						<tr>	
							<th>姓名</th>
							<td>
								${servicestaffModel.realName}
							</td>
						</tr>
						<tr>
							<th>医院</th>
							<td>
							${servicestaffModel.hospitalName}
							</td>
						</tr>
							<tr>
							<th>科室</th>
							<td>
							${servicestaffModel.departmentName}
							</td>
						</tr>
						
							<tr>
							<th>联系方式</th>
							<td>
							${m.docoterMobile}
							</td>
						</tr>
						
						<tr>
							<th>擅长</th>
							<td>
							${servicestaffModel.serviceStaffTerritory}
							</td>
						</tr>
						
						<tr>
							<th>标签</th>
							<td>
								<c:forEach items="${servicestaffModel.doctorTags}" var="tag">
								 	<a href="javascript:;" class="btn " > ${tag.tagName}</a>
								</c:forEach>
							</td>
						</tr>

						<tr>
							<th>加号要求</th>
							<td>
								暂无相关数据
							</td>
						</tr>
						<tr>
							<th>是否需要二次审核</th>
							<td>
								暂无相关数据
							</td>
						</tr>
					</tbody>
			</table>	
		</div>
				
			<div  class="form-horizontal form-bordered border1-top">	
				<div class="form-group" style="border-right: 1px solid #ddd;">
					<div class="col-sm-12">
						<i class="fa fa-user"></i>订单详情
					</div>
				</div>	
				<table class="table table-bordered mb_0">
		      <tbody>
							<tr>
								<th width="15%">就诊时间</th>
								<td width="85%">${m.seeDoctorTime}</td>
							</tr>
							<tr>	
								<th>疾病</th>
								<td>
									${m.orderIllness}
								</td>
							</tr>
							<tr>
								<th>预约目的</th>
								<td>
								${m.orderReason}
								</td>
							</tr>
								<tr>
								<th>当前加号状态</th>
								<td>
									<c:if test="${m.state==0}">
		   		         	未审核
		   	          </c:if>
		            	<c:if test="${m.state==1}">
		   	          	已审核
		            	</c:if>
		            	<c:if test="${m.state==2}">
		   	         	  审核未通过
		            	</c:if>
								</td>
							</tr>
							<c:if test="${m.state==2}">
								<tr>
									<th>拒绝理由</th>
									<td>
										${m.refuseReason}
									</td>
								</tr>	
							</c:if>
						</tbody>
				</table>	
			</div>
				
				
			<div class="y_fixedbtn">
				<button type="button" class="btn  btn-primary cancel btn-large a_size_1">返回</button>	
			</div>	
		
<!--审核不通过弹出-->
<div id="Remarks" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="user-infos">审核不通过理由</h3>
			</div>
			<div class="modal-body">
				<div class="box">	
					<textarea name="refuseReason" id="refuseReason" class="form-control" rows="5"></textarea>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn moresearch" data-dismiss="modal">关闭</button>
				<button id="checkUnPass" type="submit" class="btn btn-primary btn-large a_size_1">拒绝</button>
			</div>
		</div>
	</div>
</div>
<!--审核不通过弹出 end-->
		
</div>
</body>
</html>
<script>
    $(document).ready(function() {
    	
    	$("#checkPass").click(function(){
    		var remark =2;
    			$.get(
			    	"${pageContext.servletContext.contextPath}/sysback/consultrecord/updateState/${m.uuid}",
			    	{"state":"1",refuseReason:"",ranNum:Math.random()},	
				    function(data) {  
				    	alert(data);
				       if(data=="success") {
				       		alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;
				       		location.href="${pageContext.servletContext.contextPath}/sysback/consultrecord/toAppointmentList";	
				       }else if(data=="fail"){
				       		alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
				       }else{
				       		alert(data);	
				       }
				    }
				);
    	});
    	$("#checkUnPass").click(function(){
    			var remark = 1;
    		  var refuseReason = $("#refuseReason").val();
    			$.get(
			    	"${pageContext.servletContext.contextPath}/sysback/consultrecord/updateState/${m.uuid}",
			    	{"refuseReason":refuseReason,"state":"2",remark:remark,ranNum:Math.random()},	
				    function(data) {    
				       if(data=="success") {
				       		alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;   	
				       		location.href="${pageContext.servletContext.contextPath}/sysback/consultrecord/toAppointmentList";
				       }else if(data=="fail"){
				       		alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
				       }else{
				       		alert(data);	
				       }
				    }
				);
    	});
			$(".cancel").click(function(){
				history.go(-1) ;
			});	    	
    });
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>