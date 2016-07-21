<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script> 
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/datepicker/datepicker.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.datepicker.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>
<!-- colorbox -->
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/colorbox/colorbox.css">
<!-- colorbox -->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/colorbox/jquery.colorbox-min.js"></script>
<!-- 调用放大图插件的js -->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.colorbox.js"></script>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="customer.m.viewCustomerInfo"/></h1>
			</div>
		</div>
		
		<!--修改-->
		<div class="y_tablebtn">
				<a href='${pageContext.servletContext.contextPath}/sysback/customercomb/toUpdateCustomer/${m.customerModel.uuid}' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'>
					<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>
				</a>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="customer.menuOne"/></span><i class="fa fa-angle-"></i></li>
				<li><span><aebiz:showTitle titleId="customer.menuTwo"/></span><i class="fa fa-angle-"></i></li>
				<li><span><aebiz:showTitle titleId="customer.m.viewCustomerInfo"/></span></li>
			</ul>
		</div>

		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">
					<form:form id="baseInfoForm" action="${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/updateBaseInfo" method="post" commandName="m" class='form-horizontal form-validate form-bordered' enctype="multipart/form-data">							
						<div class="form-group">
							<div class="col-sm-12">
								<div class="col-sm-3">
									<i class="fa fa-user"></i>患者基本信息
								</div>
							</div>
						</div>
						
						<!--流水号-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">患者ID</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.customerModel.customerId}
								</div>
							</div>
						</div>
						
						<!--昵称-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">昵称</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.customerModel.customerName}
								</div>
							</div>
						</div>
						
						<!--头像-->
						<div class="form-group">
							<td colspan="3" style="border-right:1px solid #ddd">
								<label for="textfield" class="control-label col-sm-2  ">头像</label>
								<div class="col-sm-10" style="padding:5px;">
									<ul class="gallery">
									<c:choose>
										
										<c:when test="${m.customerInfoModel.imgUrl!=''}">
											<li><a href="#"><img src="${m.customerInfoModel.imgUrl}" alt="logo" width="200px" height="200px"></a>
											<div class="extras">
												<div class="extras-inner">
													<a href="${m.customerInfoModel.imgUrl}" class='colorbox-image' rel="group-1" target="_blank"> <i class="fa fa-search"></i></a>
												</div>
											</div>
										</li>
										</c:when>
										<c:otherwise>
											<li><a href="#"><img src="../../static/sysback/img/default.jpg" alt="logo"></a>
												<div class="extras">
													<div class="extras-inner">
														<a href="../../static/sysback/img/default.jpg" class='colorbox-image' rel="group-1" target="_blank"> <i class="fa fa-search"></i></a>
													</div>
												</div>
											</li>
										</c:otherwise>
									</c:choose>
									</ul>
								</div>
							</td>						
						</div>
						
						<!--真实姓名-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">患者姓名</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.customerModel.realName}
								</div>
							</div>
						</div>
						
						<!--性别-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">性别</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<c:choose>
											<c:when test="${m.customerModel.sex eq '1'}">
												&nbsp;男
											</c:when>
											<c:when test="${m.customerModel.sex eq '2'}">
												&nbsp;女
											</c:when>
											<c:otherwise>
												&nbsp;保密
											</c:otherwise>
										</c:choose>
								</div>
							</div>
						</div>
						
						<!--出生日期-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">出生日期</label>
							<div class="col-sm-10">
								<div class="col-sm-4">
										&nbsp;${m.customerModel.birthday}
								</div>
							</div>
						</div>
						
						<!--手机号码-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">手机号码</label>
							<div class="col-sm-10">
								<div class="col-sm-4">
										&nbsp;${m.customerModel.mobile}
								</div>
							</div>
						</div>
						
						<!--邮箱-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">邮箱</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.customerModel.email}
								</div>
							</div>
						</div>
						
						<!--病情描述-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">病情描述</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;暂无数据原型
								</div>
							</div>
						</div>
						<!-- --------------------------- -->
						<!--身份证号-->
						 <div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">身份证号</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.customerInfoModel.certCode}
								</div>
							</div>
						</div> 
						
						 <!--选择婚姻状况-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">选择婚姻状况</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.customerInfoModel.marryState} 
								</div>
							</div>
						</div>
						<!--地址-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">地址</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.customerInfoModel.address}
								</div>
							</div>
						</div>
						<!--职业-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">职业</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.customerInfoModel.industry}
								</div>
							</div>
						</div>
						<!--病程-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">病程</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.customerInfoModel.diseaseTime}
								</div>
							</div>
						</div>
						<!--首次就诊时间-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">首次就诊时间</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.customerInfoModel.firstDiagnosis}
								</div>
							</div>
						</div>
						<!--选着是否首发-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">是否首发</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.customerInfoModel.ifStart}
								</div>
							</div>
						</div>
						<!--复发次数-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">复发次数</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.customerInfoModel.seizureTimes}
								</div>
							</div>
						</div>
						<!--身高-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">身高</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.customerInfoModel.height}
								</div>
							</div>
						</div>
						<!--体重-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">体重</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.customerInfoModel.weight}
								</div>
							</div>
						</div>
						<!--近三个月使用药物-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">近三个月使用药物</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.customerInfoModel.nearlyDrugs}
								</div>
							</div>
						</div> 
						<!-- -------------------------------------------- -->
						
						
						<!--图文咨询记录-->
						<div class="form-group">
							<div class="col-sm-12">
								<div class="col-sm-3">
									<i class="fa fa-lock"></i>图文咨询记录
								</div>
							</div>
						</div>
						
						<div class="tab-content y_tabdatable">	
								<table class="y_storeodlist">
									<thead>
										<tr>
											<th>咨询的医生</th>
											<th>首次咨询时间</th>
											<th><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
										</tr>
									</thead>
									<tbody>								
										<c:forEach items="${picWordRecordList}" var="picWord">
											<tr>
												<th>&nbsp;
													<a href="${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/toServicestaffView/${picWord.doctorUuid}"  rel='tooltip' title='${picWord.docoterName}详情'>
														${picWord.docoterName}
													</a>	
												</th>
												<th>&nbsp;${picWord.createTime}</th>
												<th>
													<a href="${pageContext.servletContext.contextPath}/sysback/consultrecord/toView/${picWord.uuid}"  rel='tooltip' title='1'>查看详情</a>	
												</th>
											</tr>
										</c:forEach>
									</tbody>
								</table>	
						</div>
					
						<!--电话咨询记录-->
						<div class="form-group">
							<div class="col-sm-12">
								<div class="col-sm-3">
									<i class="fa fa-lock"></i>电话咨询记录
								</div>
							</div>
						</div>
						
						<div class="tab-content y_tabdatable">	
							<table class="y_storeodlist">
								<thead>
									<tr>
										<th>咨询的医生</th>
										<th>首次咨询时间</th>
										<th><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
									</tr>
								</thead>
								<tbody>								
									<c:forEach items="${telRecordList}" var="tel">
										<tr>
											<th>&nbsp;
												<a href="${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/toServicestaffView/${tel.doctorUuid}"  rel='tooltip' title='详情'>
													${tel.doctorName}
												</a>	
											</th>
											<th>&nbsp;${tel.receiveTime}</th>
											<th>
												<a href="${pageContext.servletContext.contextPath}/sysback/order/view/${tel.uuid}"  rel='tooltip' title='1'>查看详情</a>	
											</th>
										</tr>
									</c:forEach>
								</tbody>
							</table>	
						</div>
					
						<!--加号记录-->
						<div class="form-group">
							<div class="col-sm-12">
								<div class="col-sm-3">
									<i class="fa fa-lock"></i>加号记录
								</div>
							</div>
						</div>
						
						<div class="tab-content y_tabdatable">	
								<table class="y_storeodlist">
									<thead>
										<tr>
											<th>加号的医生</th>
											<th>首次加号时间</th>
											<th><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
										</tr>
									</thead>
									<tbody>								
										<c:forEach items="${plusRecordList}" var="plus">
											<tr>
												<th>&nbsp;
													<a href="${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/toServicestaffView/${plus.doctorUuid}"  rel='tooltip' title='${plus.docoterName}详情'>
														${plus.docoterName}
													</a>	
												</th>												
												<th>&nbsp;${plus.createTime}</th>
												<th>
													<a href="${pageContext.servletContext.contextPath}/sysback/consultrecord/toViewAppointment/${plus.uuid}"  rel='tooltip' title='1'>查看详情</a>	
												</th>
											</tr>
										</c:forEach>
									</tbody>
								</table>	
						</div>
					
						<!--私人医生-->
						<div class="form-group ">
							<div class="col-sm-12">
								<div class="col-sm-3">
									<i class="fa fa-lock"></i>私人医生
								</div>
							</div>
						</div>
						<div class="tab-content y_tabdatable">	
								<table class="y_storeodlist">
								<thead>
									<tr>
										<th>服务的医生</th>
										<th>首次购买时间</th>
										<th><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
									</tr>
								</thead>
								<tbody>								
									<c:forEach items="${perList}" var="per">
										<tr>
											<th>&nbsp;
												<a href="${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/toServicestaffView/${tel.doctorUuid}"  rel='tooltip' title='${tel.docoterName}详情'>
													${per.doctorName}
												</a>	
											</th>
											<th>&nbsp;${per.orderTime}</th>
											<th>
												<a href="${pageContext.servletContext.contextPath}/sysback/order/viewPackage/${tel.uuid}"  rel='tooltip' title='1'>查看详情</a>	
											</th>
										</tr>
									</c:forEach>
								</tbody>
							</table>	
						</div>		
						
						<!--关注的医生-->
						<div class="form-group">
							<div class="col-sm-12">
								<div class="col-sm-3">
									<i class="fa fa-lock"></i>关注的医生
								</div>
							</div>
						</div>
						
						<div class="tab-content y_tabdatable">	
								<table class="y_storeodlist">
								<thead>
									<tr>
										<th>关注的医生</th>
										<th>关注时间</th>
									</tr>
								</thead>
								<tbody>								
									<c:forEach items="${concernList}" var="concern">
										<tr>
											<th>&nbsp;
												<a href="${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/toServicestaffView/${tel.doctorUuid}"  rel='tooltip' title='${tel.docoterName}详情'>
													${concern.doctorName}
												</a>	
											</th>
											<th>&nbsp;${concern.createTime}</th>
										</tr>
									</c:forEach>
								</tbody>
							</table>	
						</div>						
					
						<!--随访-->
						<div class="form-group">
							<div class="col-sm-12">
								<div class="col-sm-3">
									<i class="fa fa-lock"></i>随访
								</div>
							</div>
						</div>
						<div class="tab-content y_tabdatable">	
								<table class="y_storeodlist">
									<thead>
										<tr>
											<th>随访的医生</th>
											<th>首次随访时间</th>
											<th>随访方案</th>
											<th><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
										</tr>
									</thead>
									<tbody>								
										<c:forEach items="${visitRecordList}" var="visit">
											<tr>
												<th>&nbsp;
													<a href="${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/toServicestaffView/${visit.serviceStaffUuid}"  rel='tooltip' title='${visit.doctorName}详情'>
														${visit.doctorName}
													</a>	
												</th>													
												<th>&nbsp;${visit.createTime}</th>
												<th>&nbsp;${visit.preceptName}</th>
												<th>
													<a href="${pageContext.servletContext.contextPath}/sysback/visitrecord/toDetail/${visit.uuid}"  rel='tooltip' title='1'>查看详情</a>	
												</th>
											</tr>
										</c:forEach>
									</tbody>
								</table>	
						</div>					
					</form:form>
		</div>
		
		<div class="y_fixedbtn">
			<button type="button" class="btn btn-primary cancel btn-large a_size_1"><aebiz:showTitle titleId="basebusiness.showmessage.return"/></button>
		</div>
	</div>
<script>
   $(function(){
		$(".cancel").click(function(){
			history.go(-1) ;
		});		
	 });
</script>
</body>
</html>

<aebiz:showErrorMsg></aebiz:showErrorMsg>