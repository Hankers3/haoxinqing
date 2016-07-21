<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

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
<!-- ���÷Ŵ�ͼ�����js -->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.colorbox.js"></script>

</head>

<body>
	<div class="container-fluid">
		<!--�ύ�?-->
		<form:form id="baseInfoForm" action="${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/tochangefrozenSate/${m.servicestaffModel.uuid}" method="post" commandName="m" class='form-horizontal form-validate form-bordered' enctype="multipart/form-data">							
		
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="servicestaff.m.viewCustomerInfo"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="servicestaff.menuOne"/></span><i class="fa fa-angle-"></i></li>
				<li><span><aebiz:showTitle titleId="servicestaff.menuTwo"/></span><i class="fa fa-angle-"></i></li>
				<li><span><aebiz:showTitle titleId="servicestaff.m.viewCustomerInfo"/></span></li>
			</ul>
		</div>
		
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">
				
				<div class="tabs-container">
					<ul class="tabs tabs-inline tabs-left">
						<li class='active'><a href="#AccountInfo" data-toggle='tab'><i class="fa fa-lock"></i> <aebiz:showTitle titleId="servicestaff.m.customerAccountInfo"/></a></li>
						<li><a href="#BaseInfo" data-toggle='tab'><i class="fa fa-user"></i> <aebiz:showTitle titleId="servicestaffinfo.moduleName1_CN"/></a></li>
					
				</div>
				
				<div class="tab-content padding tab-content-inline y_storesee">
					<!--������Ա��Ϣ-->
					<div class="tab-pane active" id="AccountInfo">
						<table class="table table-bordered">
							<tbody>
								<tr>
									<th width="15%"><aebiz:showTitle titleId="servicestaff.m.serviceStaffName"/></th>
									<td width="35%">${m.servicestaffModel.serviceStaffName}</td>
									<th width="15%"><aebiz:showTitle titleId="servicestaff.m.mobile" /></th>
									<td width="35%">${m.servicestaffModel.mobile }</td>
								</tr>
								
								<tr>
									<th><aebiz:showTitle titleId="servicestaff.m.email"/></th>
									<td>${m.servicestaffModel.email}</td>
									<th><aebiz:showTitle titleId="servicestaff.m.serviceStaffLevel"/></th>
									<td>
										<c:forEach items="${levelList}" var="level">
											<c:if test="${m.servicestaffModel.serviceStaffLevel==level.uuid}">
												<c:out value="${level.levelName}"></c:out>
											</c:if>
										</c:forEach>
									</td>
								</tr>
							
							<!--����״̬-->
								<th ><aebiz:showTitle titleId="servicestaff.m.frozenSate"/></th>
									<td colspan=3>
										<c:if test="${m.servicestaffModel.frozenSate=='1'}">
											<aebiz:showTitle titleId="servicestaff.m.frozened" />
										</c:if>
										<c:if test="${m.servicestaffModel.frozenSate=='0'}">
											<aebiz:showTitle titleId="servicestaff.m.unfrozened" />
										</c:if>
										<c:if test="${m.servicestaffModel.frozenSate=='2'}">
											<aebiz:showTitle titleId="servicestaff.m.waitChoose" />
										</c:if>
										<c:if test="${m.servicestaffModel.frozenSate=='3'}">
											<aebiz:showTitle titleId="servicestaff.m.waitInterview" />
										</c:if>
										<c:if test="${m.servicestaffModel.frozenSate=='4'}">
											<aebiz:showTitle titleId="servicestaff.m.trained" />
										</c:if>
									</td>
							
							</tbody>
						</table>
					</div>
					
					
					<!--������Ա����Ϣ-->
					<div class="tab-pane" id="BaseInfo">
						<table class="table table-bordered">
							<tbody>
								<tr>
									<th width="15%"><aebiz:showTitle titleId="servicestaffinfo.m.nickName"/></th>
									<td width="35%">${m.servicestaffinfoModel.nickName }</td>
									<th width="15%"><aebiz:showTitle titleId="servicestaffinfo.m.sex"/></th>
									<td width="35%"  style="border-right:1px solid #ddd">
										<c:choose>
											<c:when test="${m.servicestaffinfoModel.sex=='1'}"><aebiz:showTitle titleId="servicestaffinfo.m.male" /></c:when>
											<c:when test="${m.servicestaffinfoModel.sex=='2'}"><aebiz:showTitle titleId="servicestaffinfo.m.female" /></c:when>
											<c:when test="${m.servicestaffinfoModel.sex=='3'}"><aebiz:showTitle titleId="servicestaffinfo.m.secret"/></c:when>
										</c:choose>
									</td>
								</tr>
								
								<tr>
									<th><aebiz:showTitle titleId="servicestaffinfo.m.zipCode"/></th>
									<td>${m.servicestaffinfoModel.zipCode}</td>
									<th><aebiz:showTitle titleId="servicestaffinfo.m.birthday"/></th>
									<td  style="border-right:1px solid #ddd">${m.servicestaffinfoModel.birthday}</td>
								</tr>	
												
								<tr>
									<th><aebiz:showTitle titleId="servicestaffinfo.m.hobby"/></th>
									<td>${m.servicestaffinfoModel.hobby}</td>
									
									<th><aebiz:showTitle titleId="servicestaffinfo.m.industry"/></th>
									<td colspan="3" >
										<c:forEach items="${industryList}" var="dpp">
											<c:if test="${m.servicestaffinfoModel.industry==dpp.name}">
												<c:out value="${dpp.value}"></c:out>
											</c:if>
										</c:forEach>
									</td>
								</tr>	
								
									
									<th><aebiz:showTitle titleId="servicestaffinfo.m.education"/></th>
									<td  colspan="3" style="border-right:1px solid #ddd">
										<c:forEach items="${educationList}" var="dpp">
											<c:if test="${m.servicestaffinfoModel.education==dpp.name}">
												<c:out value="${dpp.value}"></c:out>
											</c:if>
										</c:forEach>
									</td>
								</tr>	
								
								<tr>
									<th><aebiz:showTitle titleId="servicestaff.m.realName"/></th>
									<td>${m.servicestaffinfoModel.realName}</td>
									<th><aebiz:showTitle titleId="servicestaffinfo.m.province"/></th>
									<td  style="border-right:1px solid #ddd">${m.servicestaffinfoModel.province}</td>
								</tr>
								
								<tr>
									<th><aebiz:showTitle titleId="servicestaffinfo.m.city"/></th>
									<td>${m.servicestaffinfoModel.city}</td>
									<th><aebiz:showTitle titleId="servicestaffinfo.m.region"/></th>
									<td style="border-right:1px solid #ddd">${m.servicestaffinfoModel.region}</td>
								</tr>
								
								<tr>
									<th><aebiz:showTitle titleId="servicestaffinfo.m.address"/></th>
									<td>${m.servicestaffinfoModel.address}</td>
									<th><aebiz:showTitle titleId="servicestaffinfo.m.certCode"/></th>
									<td  style="border-right:1px solid #ddd">${m.servicestaffinfoModel.certCode}</td>
								</tr>
								
								<tr>
									<th><aebiz:showTitle titleId="servicestaffinfo.m.image"/></th>
									<td colspan=3 style="border-right:1px solid #ddd">
										<ul class="gallery">
										<c:choose>
											<c:when test="${!empty m.servicestaffinfoModel.imgUrl}">
												<li><a href="#"><img src="${m.servicestaffinfoModel.imgUrl}" alt="logo"></a>
												<div class="extras">
													<div class="extras-inner">
														<a href="${m.servicestaffinfoModel.imgUrl}" class='colorbox-image' rel="group-1" target="_blank"> <i class="fa fa-search"></i></a>
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
									</td>						
								</tr>
							</tbody>
						</table>
					</div>
					
					
					
				</div>
			</div>
		</div>
		
		
		
		</form:form>
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