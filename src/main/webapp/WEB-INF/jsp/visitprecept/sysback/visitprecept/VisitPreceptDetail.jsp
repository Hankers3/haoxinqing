<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>随访方案详情</h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span>随访系统</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>随访管理</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>随访方案详情</span>
					<i class="fa fa-angle-right"></i>
				</li>
			</ul>
		</div>	
				
		<div class="box box-bordered bordered-top">	
			<div class="box-content nopadding" id="baseInfo">
				<div  class='form-horizontal form-validate form-bordered'>	
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
						</tbody>
					</table>	
		
					<div class="form-group">
						<div class="col-sm-12">
							<i class="fa fa-user"></i>随访方案
						</div>
					</div>
					
					<table class="table table-bordered mb_0">
		      	<tbody>
							<tr>
								<th width="15%">方案名称</th>
								<td width="85%">${m.preceptName}</td>
							</tr>
							<tr>	
								<th>药物治疗</th>
								<td>
								${m.drugTherapy}
								</td>
							</tr>
							<tr>
								<th>药物不良反应处理</th>
								<td>
								${m.sideEffects}
								</td>
							</tr>
							<tr>
								<th>科室</th>
								<td>
								${m.hospitalUuid}
								</td>
							</tr>
							<tr>
								<th>随访周期</th>
								<td>
								${m.period}
								</td>
							</tr>
							<tr>
								<th width="15%">心电图检查周期</th>
								<td width="85%">${m.electrocardiogram}</td>
							</tr>
							<tr>	
								<th>血常规</th>
								<td>
								${m.bloodRoutine}
								</td>
							</tr>
							<tr>
								<th>肝功能</th>
								<td>
								${m.hepatic}
								</td>
							</tr>
							<tr>
								<th>伙食指导</th>
								<td>
								${m.dietGuide}
								</td>
							</tr>
							<tr>
								<th>运动指导</th>
								<td>
								${m.sport}
								</td>
							</tr>
							<tr>
								<th>睡眠指导</th>
								<td>
								${m.sleep}
								</td>
							</tr>
						</tbody>
					</table>
				
					<div class="form-group">
						<div class="col-sm-12">
							<i class="fa fa-user"></i>关联患者信息
						</div>
					</div>
					
					<table class="table table-bordered mb_0">
			      <tbody>
							<tr>
								<c:forEach items="${customerList}" var="cm">	
									<th width="10%">患者ID</th>
									<td width="15%">${cm.customerId}</td>
									<th width="10%">患者用户名</th>
									<td width="15%">${cm.customerName}</td>
									<th width="10%">患者姓名</th>
									<td width="20%">${cm.realName}</td>
									<td width="20%"><a href="${pageContext.servletContext.contextPath}/sysback/customercomb/toView/{cm.uuid}" target="_blank" >查看</a></td>
								</c:forEach>
							</tr>
						</tbody>
					</table>
							
				</div>			
			</div>
		
			<div class="y_fixedbtn">
				<button type="button" class="btn btn-primary cancel btn-large a_size_1">返回</button>
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
</script>
<aebiz:showErrorMsg></aebiz:showErrorMsg>