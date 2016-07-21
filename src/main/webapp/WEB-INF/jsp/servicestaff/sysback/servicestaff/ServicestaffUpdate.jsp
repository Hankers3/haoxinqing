<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include
	file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp"%>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script
	src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/datepicker/datepicker.css">
<script
	src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datepicker/bootstrap-datepicker.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.datepicker.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.passwdcheck.js"></script>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>
					<aebiz:showTitle titleId="basebusiness.showmessage.edit" />
					<aebiz:showTitle titleId="servicestaff.moduleName_CN" />
				</h1>
			</div>
		</div>

		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle
							titleId="servicestaff.menuOne" /></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle
							titleId="servicestaff.menuTwo" /></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle
							titleId="basebusiness.showmessage.edit" />
						<aebiz:showTitle titleId="servicestaff.moduleName_CN" /></span></li>
			</ul>
		</div>
		<!--家政人员基本信息-->

		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding" id="baseInfo">
				<form:form id="baseInfoForm"
					action="${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/updateBaseInfo"
					method="post" commandName="m"
					class='form-horizontal form-validate form-bordered'
					enctype="multipart/form-data" >
					<form:hidden path="serviceStaffInfo.uuid" />
					<form:hidden path="serviceStaffInfo.serviceStaffUuid" />
					<form:hidden path="serviceStaffInfo.image" />
<%-- 					<form:hidden path="serviceStaffInfo.photoOne" />
					<form:hidden path="serviceStaffInfo.photoTwo" />
					<form:hidden path="serviceStaffInfo.photoThree" />
					<form:hidden path="serviceStaffInfo.photoFour" />
					<form:hidden path="serviceStaffInfo.photoFive" /> --%>
					<form:hidden path="serviceStaff.uuid" />
					<form:hidden path="serviceStaff.doctorNo" />
			<%-- 		<form:hidden path="serviceStaff.password" /> --%>
					<form:hidden path="serviceStaff.frozenTyp" />
					<form:hidden path="serviceStaff.regState" />
					<form:hidden path="serviceStaff.lastWrongLoginTime" />
					<form:hidden path="serviceStaff.activeCode" />
					<form:hidden path="serviceStaff.loginErrorTimes" />
					<form:hidden path="serviceStaff.createTime" />
					<form:hidden path="serviceStaff.deviceToken" />
					<form:hidden path="serviceStaff.frozenSate" />
					<form:hidden path="serviceStaff.sate" />
				<%-- 	<form:hidden path="serviceStaffInfo.territory" /> --%>
					<input type="hidden" id="type" name="type" value="${type}">
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">&nbsp;<aebiz:showTitle
								titleId="servicestaffinfo.m.realName" /></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<form:input path="serviceStaff.realName"
									class='form-control' />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">&nbsp;<aebiz:showTitle
								titleId="servicestaff.m.mobile" /></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<form:input path="serviceStaff.mobile" class='form-control'
									data-rule-maxlength="11" maxlength='11' />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">&nbsp;<aebiz:showTitle
								titleId="servicestaff.m.email" /></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<form:input path="serviceStaff.email" class='form-control' />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle
								titleId="servicestaffinfo.m.image" /></label>
						<div class="col-sm-10">
							<div
								class="fileinput <c:choose><c:when test="${empty m.serviceStaffInfo.image}">fileinput-new </c:when><c:otherwise>fileinput-exists</c:otherwise></c:choose>"
								data-provides="fileinput">
								<div class="fileinput-preview thumbnail"
									data-trigger="fileinput" style="width: 150px; height: 150px;">
									<c:if test="${!empty m.serviceStaffInfo.image}">
										<img src="${m.serviceStaffInfo.image}" />
									</c:if>
								</div>
								<div>
									<span class="btn btn-default btn-file"> <span
										class="fileinput-new">Select image</span> <span
										class="fileinput-exists">Change</span> <input type="file"
										name="imgFile" />
									</span> <a href="#" class="btn btn-default fileinput-exists"
										data-dismiss="fileinput">Remove</a>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle
								titleId="servicestaffinfo.m.sex" /></label>
						<div class="col-sm-10">
							<div class="col-sm-1">
								<div class="check-line">
									<form:radiobutton path="serviceStaffInfo.sex" id="c5"
										value="2" class="icheck-me" data-skin="square"
										data-color="blue" />
									<label class='inline' for="c5"><aebiz:showTitle
											titleId="servicestaffinfo.m.female" /></label>
								</div>
							</div>

							<div class="col-sm-1">
								<div class="check-line">
									<form:radiobutton path="serviceStaffInfo.sex" id="c6"
										value="1" class="icheck-me" data-skin="square"
										data-color="blue" />
									<label class='inline' for="c6"><aebiz:showTitle
											titleId="servicestaffinfo.m.male" /></label>
								</div>
							</div>

							<div class="col-sm-1">
								<div class="check-line">
									<form:radiobutton path="serviceStaffInfo.sex" id="c7"
										value="3" class="icheck-me" data-skin="square"
										data-color="blue" />
									<label class='inline' for="c7"><aebiz:showTitle
											titleId="servicestaffinfo.m.secret" /></label>
								</div>
							</div>
						</div>
					</div>


					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">&nbsp;地区</label>
						<div class="col-sm-10">
							<aebiz:seachArea
								checkProvince="${m.serviceStaffInfo.province}"
								checkCity="${m.serviceStaffInfo.city}"
								checkRegion="${m.serviceStaffInfo.region}"
								hospitalId="#hospital"
								checkHospital="${m.serviceStaff.hospital}"></aebiz:seachArea>
						</div>
					</div>


					<!--医院 -->
					<div class="form-group">
						<label class="control-label col-sm-2  ">医院名称：</label>
						<div class="col-sm-10">
							<div class="col-sm-4">
								<select name="serviceStaff.hospital" id="hospital" class="form-control">
									<option value="">--请选择--</option>
								</select> <input type="hidden" name="hospital_q" id="hospital_q"
									class="form-control" value="Like">
							</div>
						</div>

					</div>


					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2 ">&nbsp;详细地址</label>
						<div class="col-sm-10">
							<div class="col-sm-4">
								<form:input path="serviceStaffInfo.address"
									class='form-control' />
							</div>
						</div>
					</div>





					<div id="showHospitalId"></div>


					<!--执业科室-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">&nbsp;<aebiz:showTitle
								titleId="servicestaffinfo.m.department" /></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<form:select path="serviceStaff.department"
									class='select2-me form-control'>
									<form:option value="">-<aebiz:showTitle
											titleId="basebusiness.showmessage.pleasechoose" />-</form:option>
									<c:forEach items="${departmentInfos}" var="department">
										<form:option value="${department.uuid}">${department.departmentName}</form:option>
									</c:forEach>
								</form:select>
							</div>
						</div>
					</div>
					<!--科室电话-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle
								titleId="servicestaffinfo.m.departmentLine" /></label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<form:input path="serviceStaff.departmentLine"
									class='form-control' />
							</div>
						</div>
					</div>


					<!--个人简介-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">个人简介</label>
						<div class="col-sm-10">
							<div class="col-sm-9" style="height: 150px;">
								<form:textarea rows="6" path="serviceStaffInfo.synopsis"
									class='form-control' maxlength="1000"></form:textarea>
							</div>
						</div>
					</div>
						<!--擅长领域-->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">擅长领域</label>
						<div class="col-sm-10">
							<div class="col-sm-9" style="height: 150px;">
								<form:textarea rows="6" path="serviceStaffInfo.territory"
									class='form-control' maxlength="1000"></form:textarea>
							</div>
						</div>
					</div>
										
					<!-- 职称 -->
		 			<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">职称</label>
						<div class="col-sm-10">
							<div class="col-sm-3">
								<form:select path="serviceStaffInfo.professional"
									class='select2-me form-control'>
									<form:option value="">-<aebiz:showTitle
											titleId="basebusiness.showmessage.pleasechoose" />-</form:option>
									<c:forEach items="${professionalList}" var="dpp">
										<form:option value="${dpp.name}" >${dpp.value}</form:option>
									</c:forEach>
								</form:select>
							</div>
						</div>
					</div>  

					<!-- 执业证书 -->
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  ">认证</label>
						<div class="col-sm-10">
							<div
								class="fileinput <c:choose><c:when test="${empty m.serviceStaffInfo.certImage}">fileinput-new </c:when><c:otherwise>fileinput-exists</c:otherwise></c:choose>"
								data-provides="fileinput">
								<div class="fileinput-preview thumbnail"
									data-trigger="fileinput" style="width: 150px; height: 150px;">
									<c:if test="${!empty m.serviceStaffInfo.certImage}">
										<img src="${m.serviceStaffInfo.certImage}" />
									</c:if>
								</div>
								<div>
									<span class="btn btn-default btn-file"> <span
										class="fileinput-new">Select image</span> <span
										class="fileinput-exists">Change</span> <input type="file"
										name="certFile"  />
									</span> <a href="#" class="btn btn-default fileinput-exists"
										data-dismiss="fileinput">Remove</a>
								</div>
							</div>
						</div>
					</div>

				</form:form>

				<div class="form-actions col-sm-offset-2 col-sm-10">
					<input type="submit" onclick="updateService()"
						class="btn btn-primary"
						value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>
					<a class="btn"
						href="<c:if test="${type==0}">${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/toView/${m.serviceStaff.uuid}</c:if><c:if test="${type==1}">${pageContext.servletContext.contextPath}/sysback/servicestaff/toList</c:if>"><aebiz:showTitle
							titleId="basebusiness.showmessage.return" /></a>
				</div>

			</div>
		</div>
	</div>

</body>
</html>
<script>
	function updateService() {
		$("#baseInfoForm").submit();
	}

	function showHospital(showId) {
		var note = $("#note1").val();
		//重置密码
		$
				.get(
						"${pageContext.servletContext.contextPath}/sysback/order/addLog",
						{
							"uuid" : uuid,
							"note" : note,
							"operateType" : "13",
							ranNum : Math.random()
						},
						function(data) {
							if (data == "success") {
								//刷新
								location.reload();
							} else {
								bootbox
										.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
							}
						});
	}

	$(document)
			.ready(
					function() {
						//验证家政员身份证号码是否存在
						$("input[name='servicestaffinfoModel.certCode']")
								.blur(
										function() {
											$(
													"span[name='servicestaffinfoModel.certCode']")
													.remove();
											var certCode = $(
													"input[name='servicestaffinfoModel.certCode']")
													.val();
											if (certCode != '') {
												var url = "${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/checkServiceStaffinfoCertCode";
												$
														.get(
																url,
																{
																	certCode : certCode,
																	ranNum : Math
																			.random()
																},
																function(data) {
																	if (data == "true") {
																		$(
																				"input[name='servicestaffinfoModel.certCode']")
																				.after(
																						"<span name='mobilespan'><font color='red'><aebiz:showTitle titleId="servicestaffinfoModel.certCode.existed" /></font></span>");
																		$(
																				"input[name='mobileHidden']")
																				.val(
																						"false");
																	} else {
																		$(
																				"input[name='mobileHidden']")
																				.val(
																						"true");
																		$(
																				"span[name='mobilespan']")
																				.remove();
																	}
																});
											}
										});
					});
	function showAddress() {
		$("#addressId").show();
	}
</script>

<script>
	function toSavefamily(uuid) {
		//alert(uuid);

		//姓名
		var name1 = uuid + "name";
		var name = $("#" + name1).val();
		//alert(name);

		//关系
		var relation1 = uuid + "relation";
		var relation = $("#" + relation1).val();
		//alert(relation);

		//年龄
		var age1 = uuid + "age";
		var age = $("#" + age1).val();
		//alert(age);

		//任职单位
		var workUnit1 = uuid + "workUnit";
		var workUnit = $("#" + workUnit1).val();
		//alert(workUnit);

		//联系地址
		var contactAddress1 = uuid + "contactAddress";
		var contactAddress = $("#" + contactAddress1).val();
		//alert(contactAddress);

		//联系电话
		var contactPhone1 = uuid + "contactPhone";
		var contactPhone = $("#" + contactPhone1).val();
		//alert(contactPhone);

		$
				.get(
						"${pageContext.servletContext.contextPath}/usercenter/servicestaffcomb/updateFamily",
						{
							"uuid" : uuid,
							"name" : name,
							"relation" : relation,
							"age" : age,
							"workUnit" : workUnit,
							"contactAddress" : contactAddress,
							"contactPhone" : contactPhone,
							ranNum : Math.random()
						}, function(data) {

							if (data == "success") {
								alert("保存成功！");
							} else {
								alert("保存失败！");
							}
						});
	}

	function toSaveEducation(uuid) {
		//alert(uuid);

		//起止年月：
		var timeDuration1 = uuid + "timeDuration";
		var timeDuration = $("#" + timeDuration1).val();
		//alert(timeDuration);

		//学历
		var educationBackgroun1 = uuid + "educationBackgroun";
		var educationBackgroun = $("#" + educationBackgroun1).val();
		//alert(educationBackgroun);

		//学校名称
		var schoolName1 = uuid + "schoolName";
		var schoolName = $("#" + schoolName1).val();
		//	alert(schoolName);

		//专业
		var major1 = uuid + "major";
		var major = $("#" + major1).val();
		//alert(major);

		//证书/奖励
		var getCertificate1 = uuid + "getCertificate";
		var getCertificate = $("#" + getCertificate1).val();
		//alert(getCertificate);

		$
				.get(
						"${pageContext.servletContext.contextPath}/usercenter/servicestaffcomb/updateEducation",
						{
							"uuid" : uuid,
							"timeDuration" : timeDuration,
							"schoolName" : schoolName,
							"educationBackgroun" : educationBackgroun,
							"major" : major,
							"getCertificate" : getCertificate,
							ranNum : Math.random()
						}, function(data) {

							if (data == "success") {
								alert("保存成功！");

							} else {

								alert("保存失败！");
							}
						});
	}
	$(document).ready(function() {
		$(".cancel").click(function() {
			history.go(-1);
		});
	});
</script>
<aebiz:showErrorMsg></aebiz:showErrorMsg>

