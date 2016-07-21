<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<!doctype html>
<html>
<%@ include
	file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp"%>
<%@ include
	file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp"%>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script
	src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script>
<style>
.form-horizontal.form-bordered .form-group .control-label {
	border-right: none;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>
					<aebiz:showTitle titleId="servicestaff.m.viewCustomerInfo" />
				</h1>
			</div>
		</div>
		<!--修改-->
		<div class="y_tablebtn">
			<a
				href='${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/toUpdateServicestaff/${m.serviceStaff.uuid}?type=1'
				class='btn' rel='tooltip'
				title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'>
				<aebiz:showTitle titleId="basebusiness.showmessage.edit" />
			</a>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle
							titleId="servicestaff.menuOne" /></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle
							titleId="servicestaff.menuTwo" /></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle
							titleId="servicestaff.m.viewCustomerInfo" /></span></li>
			</ul>
		</div>
		<div class="box box-bordered bordered-top">
			<!--家政人员基本信息-->
			<div class="box-content nopadding">
				<form:form action="" method="post" commandName="m"
					class='form-horizontal form-validate form-bordered'>
					<!--医生基本信息-->
					<div class="form-group">
						<div class="col-sm-12" style="border-left: none">
							<div class="col-sm-3">
								<i class="fa fa-user"></i>医生基本信息
							</div>
						</div>
					</div>
					<%-- 						<!--流水号-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">医生ID</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${m.serviceStaff.doctorNo}
								</div>
							</div>
						</div> --%>
					<!--真实姓名-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle
								titleId="servicestaffinfo.m.realName" /></label>
						<div class="col-sm-10">
							<div class="col-sm-3">&nbsp;${m.serviceStaff.realName}</div>
						</div>
					</div>

					<!--手机号码-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle
								titleId="servicestaff.m.mobile" /></label>
						<div class="col-sm-10">
							<div class="col-sm-4">&nbsp;${m.serviceStaff.mobile}</div>
						</div>
					</div>

					<!--邮箱-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle
								titleId="servicestaff.m.email" /></label>
						<div class="col-sm-10">
							<div class="col-sm-3">&nbsp;${m.serviceStaff.email}</div>
						</div>
					</div>

					<!--地区-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle
								titleId="servicestaff.m.address" /></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								&nbsp;${m.serviceStaffInfo.provinceName}${m.serviceStaffInfo.cityName}${m.serviceStaffInfo.regionName}
							</div>
						</div>
					</div>

					<!--医院-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle
								titleId="servicestaffinfo.m.hospital" /></label>
						<div class="col-sm-10">
							<div class="col-sm-3">&nbsp;${m.serviceStaff.hospitalName}
							</div>
						</div>
					</div>

					<!--科室-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle
								titleId="servicestaffinfo.m.department" /></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								&nbsp;${m.serviceStaff.departmentName}</div>
						</div>
					</div>

					<!--科室电话-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle
								titleId="servicestaffinfo.m.departmentLine" /></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								&nbsp;${m.serviceStaff.departmentLine}</div>
						</div>
					</div>

					<!--职称-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle
								titleId="servicestaffinfo.m.professional" /></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								&nbsp;${m.serviceStaffInfo.professional}
							</div>
						</div>
					</div>

					<!--擅长-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle
								titleId="servicestaffinfo.m.territory" /></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								&nbsp;${m.serviceStaffInfo.territory}</div>
						</div>
					</div>

					<!--个人简介-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle
								titleId="servicestaffinfo.m.synopsis" /></label>
						<div class="col-sm-10">
							<div class="col-sm-3">&nbsp;${m.serviceStaffInfo.synopsis}
							</div>
						</div>
					</div>

					<!--医生标签-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle
								titleId="servicestaff.m.doctorTag" /></label>
						<div class="col-sm-10">
							<div class="col-sm-12">
								<c:forEach items="${m.serviceStaff.doctorTags}" var="tag">
									<input type="hidden" name="tagname">
									<a
										href="javascript:deleteTag('${m.serviceStaff.uuid}','${tag.tagUuid}')"
										class="btn "> ${tag.tagName} <i class="del">x</i></a>
								</c:forEach>
								<a href="#" class="btn btn-primary a_size_1" data-toggle="modal"
									data-target="#RemarkRefoud">新增标签</a>
							</div>
						</div>
					</div>

					<!--资质信息-->
					<div class="form-group">
						<div class="col-sm-12" style="border-left: none">
							<div class="col-sm-3">
								<i class="fa fa-lock"></i>资质信息
							</div>
						</div>
					</div>
					<div class="form-group" style="background: #fff;">
						<div class="col-sm-2" style="border-left: none">
							<ul class="gallery">
								<c:choose>
									<c:when test="${!empty m.serviceStaffInfo.certImage}">
										<li><a href="#"><img
												src="${m.serviceStaffInfo.certImage}" alt="logo"
												width="200px" height="200px"></a>
											<div class="extras">
												<div class="extras-inner">
													<a href="${m.serviceStaffInfo.certImage}"
														class='colorbox-image' rel="group-1" target="_blank">
														<i class="fa fa-search"></i>
													</a>
												</div>
											</div></li>
									</c:when>
								</c:choose>
							</ul>
						</div>
						<%-- 							<div class="col-sm-2">
									<td colspan="3" style="border-right:1px solid #ddd">
										<ul class="gallery">
										<c:choose>
											<c:when test="${!empty m.serviceStaffInfo.imgUrlTwo}">
												<li><a href="#"><img src="${m.serviceStaffInfo.imgUrlTwo}" alt="logo" width="200px" height="200px"></a>
												<div class="extras">
													<div class="extras-inner">
														<a href="${m.serviceStaffInfo.imgUrlTwo}" class='colorbox-image' rel="group-1" target="_blank"> <i class="fa fa-search"></i></a>
													</div>
												</div>
											</li>
											</c:when>
										</c:choose>
										</ul>
									</td>						
								</div> --%>

						<%-- 								<div class="col-sm-2">
									<td colspan="3" style="border-right:1px solid #ddd">
										<ul class="gallery">
										<c:choose>
											<c:when test="${!empty m.serviceStaffInfo.imgUrlThree}">
												<li><a href="#"><img src="${m.serviceStaffInfo.imgUrlThree}" alt="logo" width="200px" height="200px"></a>
												<div class="extras">
													<div class="extras-inner">
														<a href="${m.serviceStaffInfo.imgUrlThree}" class='colorbox-image' rel="group-1" target="_blank"> <i class="fa fa-search"></i></a>
													</div>
												</div>
											</li>
											</c:when>
										</c:choose>
										</ul>
									</td>						
								</div> --%>

						<%-- 								<div class="col-sm-2">
									<td colspan="3" style="border-right:1px solid #ddd">
										<ul class="gallery">
										<c:choose>
											<c:when test="${!empty m.serviceStaffInfo.imgUrlFour}">
												<li><a href="#"><img src="${m.serviceStaffInfo.imgUrlFour}" alt="logo" width="200px" height="200px"></a>
												<div class="extras">
													<div class="extras-inner">
														<a href="${m.serviceStaffInfo.imgUrlFour}" class='colorbox-image' rel="group-1" target="_blank"> <i class="fa fa-search"></i></a>
													</div>
												</div>
											</li>
											</c:when>
										</c:choose>
										</ul>
									</td>						
								</div> --%>

						<%-- 								<div class="col-sm-2">
									<td colspan="3" style="border-right:1px solid #ddd">
										<ul class="gallery">
										<c:choose>
											<c:when test="${!empty m.serviceStaffInfo.imgUrlFive}">
												<li><a href="#"><img src="${m.serviceStaffInfo.imgUrlFive}" alt="logo" width="200px" height="200px"></a>
												<div class="extras">
													<div class="extras-inner">
														<a href="${m.serviceStaffInfo.imgUrlFive}" class='colorbox-image' rel="group-1" target="_blank"> <i class="fa fa-search"></i></a>
													</div>
												</div>
											</li>
											</c:when>
										</c:choose>
										</ul>
									</td>						
								</div>	 --%>
					</div>

					<!--运营信息-->
					<div class="form-group" style="border-bottom: none">
						<div class="col-sm-12" style="border-left: none">
							<div class="col-sm-3">
								<i class="fa fa-lock"></i>运营信息
							</div>
						</div>
					</div>
					<div class="table-responsive2 nopadding mb_20">
						<table class="table table-bordered">
							<tbody>
								<tr style="border-top: 1px solid #ddd;">
									<th>图文咨询量</th>
									<td>${picWordCount}</td>
									<th>电话咨询状态</th>
									<td>${telState}</td>
									<th>电话咨询量</th>
									<td>${telCount}</td>
								</tr>
								<tr>
									<th>加号咨询状态</th>
									<td>${orderState}</td>
									<th>加号咨询量</th>
									<td>${orderCount}</td>
									<th>私人医生状态</th>
									<td>${personalState}</td>
								</tr>
								<tr>
									<th>私人医生服务量</th>
									<td>${personalCount}</td>
									<th>随访患者数</th>
									<td>${visitCount}</td>
									<th>粉丝数</th>
									<td>${telCount}</td>
								</tr>
							</tbody>
						</table>
					</div>
			</div>
			</form:form>
		</div>
		<div class="y_fixbtnbox">
			<div class="y_fixedbtn">
				<button type="button" id="returenId"
					class="btn btn-primary  btn-large a_size_1">返回</button>
			</div>
		</div>
	</div>


	<!--标签库 end-->
	<div id="RemarkRefoud" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h3 id="user-infos">标签库</h3>
				</div>
				<div class="modal-body">
					<c:forEach items="${tags}" var="tag">
						<input type="button" id="${tag.uuid}" class="btn"
							value="${tag.tagName}">
					</c:forEach>
					<input type="hidden" id="tagId" value="">
				</div>

				<div class="modal-footer">
					<button class="btn moresearch" data-dismiss="modal">关闭</button>
					<button onclick="javascript:addTag('${m.serviceStaff.uuid}');"
						class="btn btn-primary" data-dismiss="modal">提交</button>
				</div>
			</div>
		</div>
	</div>
	<!--标签库 end-->
</body>
</html>
<script>
    $(document).ready(function() {
			$("#returenId").click(function(){
				history.go(-1) ;
			});
    });
    
    $(".modal-body input").click(function(){
		  	var tagUuids="";
		  	var alength =0;
		  	$(this).toggleClass("btn-primary");
		  	$(this).parent().find('.btn-primary').each(function(){
		  		var _btnl = $(this).parent().find('.btn-primary').length;
		  		var _tagl = $("input[name='tagname']").length;
		  		alength = _btnl + _tagl;
		  		var tagUuid = $(this).attr('id');
		  		tagUuids = tagUuid+";"+tagUuids
		  	});
		    if(alength>5){
		  			alert("最多拥有5个标签");
		  			return;
		  	}
		  	$("#tagId").val(tagUuids);
		});	
    
    function deleteTag(uuid ,tagUuid){
    		$.get("${pageContext.servletContext.contextPath}/sysback/servicestaff/toDeleteTag",
				{
					"uuid":uuid,
					"tagUuid":tagUuid,
					ranNum : Math.random()
				},
				function(data) {
					if (data == "success") {
						//刷新 当前页面
						location.reload();
					} else {
						bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
					}
				});
    }
    
    
    function addTag(uuid){
    	  var tagId = $("#tagId").val();
    		$.get("${pageContext.servletContext.contextPath}/sysback/servicestaff/addTag",
				{
					"uuid":uuid,
					"tagId":tagId,
					ranNum : Math.random()
				},
				function(data) {
					if (data == "success") {
						//刷新 当前页面
						location.reload();
					} else {
						bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
					}
				});
    }
</script>
<aebiz:showErrorMsg></aebiz:showErrorMsg>