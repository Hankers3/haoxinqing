<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script> 
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/datepicker/datepicker.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.datepicker.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.passwdcheck.js"></script> 

<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=4YoG8bce6ygQp3lgXz9U7xaO"></script>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="servicestaff.moduleName_CN"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="servicestaff.menuOne"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="servicestaff.menuTwo"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="servicestaff.moduleName_CN"/></span></li>
			</ul>
		</div>
		
		
		<!--标签跳转-->
		<ul class="tabs tabs-inline tabs-top border1-top">
			<li><a href="#accountInfo" data-toggle='tab'><aebiz:showTitle titleId="servicestaff.m.customerAccountInfo"/></a></li>
			<li class='active'><a href="#baseInfo" data-toggle='tab'><aebiz:showTitle titleId="servicestaffinfo.moduleName_CN"/></a></li>
		</ul>
		
		
		<div class="tab-content y_tabdatable padding tab-content-inline tab-content-bottom">
			<div class="tab-pane" id="accountInfo">
				<div class="table table-bordered table-hover table-nomargin">
					<form:form id="accountInfoForm" action="${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/updateAccountInfo" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>
						<form:hidden path="servicestaffModel.uuid"/>
						<form:hidden path="servicestaffModel.password"/>
						<form:hidden path="servicestaffModel.frozenTyp"/>
						<form:hidden path="servicestaffModel.lastWrongLoginTime"/>
						<form:hidden path="servicestaffModel.activeCode"/>
						<form:hidden path="servicestaffModel.loginErrorTimes"/>
						<form:hidden path="servicestaffModel.createTime"/> 
						<form:hidden path="servicestaffModel.deviceToken"/>
						<form:hidden path="servicestaffModel.serviceStaffLevel"/>
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="servicestaff.m.serviceStaffName"/></label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:input path="servicestaffModel.serviceStaffName" class='form-control' readonly="true"/>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="servicestaff.m.mobile" /></label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:input path="servicestaffModel.mobile" class='form-control' readonly="true"/>
								</div>
							</div>
						</div>
						
						<!--冻结状态-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="servicestaff.m.frozenSate"/></label>
							<div class="col-sm-10">
								<div class="col-sm-1">
									<div class="check-line">
										<form:radiobutton path="servicestaffModel.sate" id="c1" value="1" class="icheck-me" data-skin="square" data-color="blue"/>
										<label class='inline' for="c1"><aebiz:showTitle titleId="servicestaff.m.frozened" /></label>
									</div>
								</div>
								<div class="col-sm-1">
									<div class="check-line">
										<form:radiobutton path="servicestaffModel.sate" id="c2" value="0" class="icheck-me" data-skin="square" data-color="blue"/>
										<label class='inline' for="c2"><aebiz:showTitle titleId="servicestaff.m.unfrozened" /></label>
									</div>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="servicestaff.m.serviceStaffLevel" /></label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:input path="servicestaffModel.serviceStaffLevelName" class='form-control' readonly="true"/>
								</div>
							</div>
						</div>
						
						<div class="form-actions col-sm-offset-2 col-sm-10">
							<input type="submit" class="btn btn-primary submit" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>
							<a class="btn" href="${pageContext.servletContext.contextPath}/sysback/customercomb/toList"><aebiz:showTitle titleId="basebusiness.showmessage.return"/></a>
						</div>
					</form:form>
				</div>
			</div>
		
		<!--医生基本信息-->
		<div class="tab-pane active" id="baseInfo">
				<div class="table table-bordered table-hover table-nomargin">
					<form:form id="baseInfoForm" action="${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/updateBaseInfoaboutAdd" method="post" commandName="m" class='form-horizontal form-validate form-bordered' enctype="multipart/form-data">							
					<form:hidden path="servicestaffinfoModel.uuid"/>
					<form:hidden path="servicestaffinfoModel.serviceStaffUuid"/>

						
					<div class="form-group">
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">&nbsp;<aebiz:showTitle titleId="servicestaffinfo.m.nickName"/></label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:input path="servicestaffinfoModel.nickName"  class="form-control" data-rule-required="true" data-rule-minlength="4" data-rule-maxlength="20" />
								</div>
							</div>
						</div>
						
						
					<div class="form-group">
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">&nbsp;<aebiz:showTitle titleId="servicestaffinfo.m.realName"/></label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:input path="servicestaffModel.realName" class='form-control' data-rule-required="true" />
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">&nbsp;<aebiz:showTitle titleId="servicestaffinfo.m.image"/></label>
							<div class="col-sm-10">
								<div class="fileinput <c:choose><c:when test="${empty m.servicestaffinfoModel.image}">fileinput-new </c:when><c:otherwise>fileinput-exists</c:otherwise></c:choose>" data-provides="fileinput">
									<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 150px; height: 150px;">
										<c:if test="${!empty m.servicestaffinfoModel.image}"><img src="${m.servicestaffinfoModel.imgUrl}" /></c:if>
									</div>
									<div>
										<span class="btn btn-default btn-file">
											<span class="fileinput-new">Select image</span>
											<span class="fileinput-exists">Change</span>
											<input type="file" name="imgFile"   data-rule-required="true" />
										</span>
										<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
									</div>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="servicestaffinfo.m.sex"/></label>
							<div class="col-sm-10">
								<div class="col-sm-1">
									<div class="check-line">
										<form:radiobutton path="servicestaffinfoModel.sex" id="c5" value="1" class="icheck-me" data-skin="square" data-color="blue"/>
										<label class='inline' for="c5"><aebiz:showTitle titleId="servicestaffinfo.m.female" /></label>
									</div>
								</div>
								
								<div class="col-sm-1">
									<div class="check-line">
										<form:radiobutton path="servicestaffinfoModel.sex" id="c6" value="2" class="icheck-me" data-skin="square" data-color="blue"/>
										<label class='inline' for="c6"><aebiz:showTitle titleId="servicestaffinfo.m.male"/></label>
									</div>
								</div>
								
								<div class="col-sm-1">
									<div class="check-line">
										<form:radiobutton path="servicestaffinfoModel.sex" id="c7" value="3" class="icheck-me"  data-skin="square" data-color="blue"/>
										<label class='inline' for="c7"><aebiz:showTitle titleId="servicestaffinfo.m.secret"/></label>
									</div>
								</div>
							</div>
						</div>
						
				    <div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">&nbsp;<aebiz:showTitle titleId="servicestaffinfo.m.age"/></label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:input path="servicestaffinfoModel.age" class='form-control'  data-rule-required="true" />
								</div>
							</div>
						</div>
					
					
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="servicestaffinfo.m.birthday"/></label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:input path="servicestaffinfoModel.birthday" class='form-control datepick'/>
								</div>
							</div>
						</div>
						
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">&nbsp; 地区</label>	
							<aebiz:area checkProvince="${m.servicestaffinfoModel.province}" checkCity="${m.servicestaffinfoModel.city}" checkRegion="${m.servicestaffinfoModel.region}"></aebiz:area>
						</div>
	
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">&nbsp;详细地址</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:input path="servicestaffinfoModel.address" class='form-control' data-rule-required="true"  data-rule-maxlength="18" />
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">&nbsp;<aebiz:showTitle titleId="servicestaffinfo.m.certCode"/></label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:input path="servicestaffinfoModel.certCode" class='form-control' data-rule-required="true"  data-rule-maxlength="18" />
								</div>
							</div>
						</div>
						
						
						
						<!--工作经验-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">&nbsp;<aebiz:showTitle titleId="servicestaffinfo.m.experience"/></label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:input path="servicestaffinfoModel.experience" class='form-control' data-rule-required="true" />
								</div>
							</div>
						</div>
						<!--执业医院-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">&nbsp;<aebiz:showTitle titleId="servicestaffinfo.m.hospital"/></label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:select path="servicestaffinfoModel.hospital" class='select2-me form-control'>								
										<form:option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</form:option>
										<c:forEach items="${hospitalInfos}" var="hospital">	
											<form:option value="${hospital.uuid}">${hospital.hospitalName}</form:option>
										</c:forEach>										
									</form:select>
								</div>
							</div>
						</div>
						<!--执业科室-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">&nbsp;<aebiz:showTitle titleId="servicestaffinfo.m.department"/></label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:select path="servicestaffinfoModel.department" class='select2-me form-control'>								
										<form:option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</form:option>
										<c:forEach items="${departmentInfos}" var="department">	
											<form:option value="${department.uuid}">${department.departmentName}</form:option>
										</c:forEach>										
									</form:select>
								</div>
							</div>
						</div>
						<!--科室电话-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="servicestaffinfo.m.departmentLine"/></label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:input path="servicestaffinfoModel.departmentLine" class='form-control' data-rule-required="true" />
								</div>
							</div>
						</div>
						
						<!--医生分类-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">&nbsp;<aebiz:showTitle titleId="servicestaffinfo.m.doctorClassify"/></label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:select path="servicestaffinfoModel.doctorClassify" class='select2-me form-control'>								
										<form:option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</form:option>
										<c:forEach items="${doctorCategorys}" var="category">	
											<form:option value="${category.uuid}">${category.categoryName}</form:option>
										</c:forEach>										
									</form:select>
								</div>
							</div>
						</div>
							<!--擅长领域-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="servicestaffinfo.m.territory"/></label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:input path="servicestaffinfoModel.territory" class='form-control' data-rule-required="true" />
								</div>
							</div>
						</div>
						<!--职称-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">职称</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<form:select path="servicestaffinfoModel.professional" class='select2-me form-control'>								
										<form:option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</form:option>
										<c:forEach items="${professionalList}" var="dpp">	
											<form:option value="${dpp.name}">${dpp.value}</form:option>
										</c:forEach>										
									</form:select>
								</div>
							</div>
						</div>
						
						<!-- 二维码 -->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="servicestaffinfo.m.twoCode"/></label>
							<div class="col-sm-10">
								<div class="fileinput <c:choose><c:when test="${empty m.servicestaffinfoModel.twoCode}">fileinput-new </c:when><c:otherwise>fileinput-exists</c:otherwise></c:choose>" data-provides="fileinput">
									<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 150px; height: 150px;">
										<c:if test="${!empty m.servicestaffinfoModel.twoCode}"><img src="${m.servicestaffinfoModel.imgUrl}" /></c:if>
									</div>
									<div>
										<span class="btn btn-default btn-file">
											<span class="fileinput-new">Select image</span>
											<span class="fileinput-exists">Change</span>
											<input type="file" name="imgFile"   data-rule-required="true" />
										</span>
										<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
									</div>
								</div>
							</div>
						</div>
						<!-- 执业证书 -->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="servicestaffinfo.m.photoOne"/></label>
							<div class="col-sm-10">
								<div class="fileinput <c:choose><c:when test="${empty m.servicestaffinfoModel.photoOne}">fileinput-new </c:when><c:otherwise>fileinput-exists</c:otherwise></c:choose>" data-provides="fileinput">
									<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 150px; height: 150px;">
										<c:if test="${!empty m.servicestaffinfoModel.photoOne}"><img src="${m.servicestaffinfoModel.imgUrl}" /></c:if>
									</div>
									<div>
										<span class="btn btn-default btn-file">
											<span class="fileinput-new">Select image</span>
											<span class="fileinput-exists">Change</span>
											<input type="file" name="imgFile"   data-rule-required="true" />
										</span>
										<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
									</div>
								</div>
							</div>
						</div>
						<!-- 职称证书 -->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="servicestaffinfo.m.photoTwo"/></label>
							<div class="col-sm-10">
								<div class="fileinput <c:choose><c:when test="${empty m.servicestaffinfoModel.photoTwo}">fileinput-new </c:when><c:otherwise>fileinput-exists</c:otherwise></c:choose>" data-provides="fileinput">
									<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 150px; height: 150px;">
										<c:if test="${!empty m.servicestaffinfoModel.photoTwo}"><img src="${m.servicestaffinfoModel.imgUrl}" /></c:if>
									</div>
									<div>
										<span class="btn btn-default btn-file">
											<span class="fileinput-new">Select image</span>
											<span class="fileinput-exists">Change</span>
											<input type="file" name="imgFile"   data-rule-required="true" />
										</span>
										<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
									</div>
								</div>
							</div>
						</div>
						<!-- 胸牌 -->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="servicestaffinfo.m.photoThree"/></label>
							<div class="col-sm-10">
								<div class="fileinput <c:choose><c:when test="${empty m.servicestaffinfoModel.photoThree}">fileinput-new </c:when><c:otherwise>fileinput-exists</c:otherwise></c:choose>" data-provides="fileinput">
									<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 150px; height: 150px;">
										<c:if test="${!empty m.servicestaffinfoModel.photoThree}"><img src="${m.servicestaffinfoModel.imgUrl}" /></c:if>
									</div>
									<div>
										<span class="btn btn-default btn-file">
											<span class="fileinput-new">Select image</span>
											<span class="fileinput-exists">Change</span>
											<input type="file" name="imgFile"   data-rule-required="true" />
										</span>
										<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
									</div>
								</div>
							</div>
						</div>
						<!-- 工作证 -->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="servicestaffinfo.m.photoFour"/></label>
							<div class="col-sm-10">
								<div class="fileinput <c:choose><c:when test="${empty m.servicestaffinfoModel.photoFour}">fileinput-new </c:when><c:otherwise>fileinput-exists</c:otherwise></c:choose>" data-provides="fileinput">
									<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 150px; height: 150px;">
										<c:if test="${!empty m.servicestaffinfoModel.photoFour}"><img src="${m.servicestaffinfoModel.imgUrl}" /></c:if>
									</div>
									<div>
										<span class="btn btn-default btn-file">
											<span class="fileinput-new">Select image</span>
											<span class="fileinput-exists">Change</span>
											<input type="file" name="imgFile"   data-rule-required="true" />
										</span>
										<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
									</div>
								</div>
							</div>
						</div>
						<!-- 其他 -->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="servicestaffinfo.m.photoFive"/></label>
							<div class="col-sm-10">
								<div class="fileinput <c:choose><c:when test="${empty m.servicestaffinfoModel.photoFive}">fileinput-new </c:when><c:otherwise>fileinput-exists</c:otherwise></c:choose>" data-provides="fileinput">
									<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 150px; height: 150px;">
										<c:if test="${!empty m.servicestaffinfoModel.photoFive}"><img src="${m.servicestaffinfoModel.imgUrl}" /></c:if>
									</div>
									<div>
										<span class="btn btn-default btn-file">
											<span class="fileinput-new">Select image</span>
											<span class="fileinput-exists">Change</span>
											<input type="file" name="imgFile"   data-rule-required="true" />
										</span>
										<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
									</div>
								</div>
							</div>
						</div>
						<div class="form-actions col-sm-offset-2 col-sm-10">
							<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>
							<a class="btn" href="${pageContext.servletContext.contextPath}/sysback/servicestaff/toList"><aebiz:showTitle titleId="basebusiness.showmessage.return"/></a>
						</div>
					</form:form>
				</div>
			</div>
		</div>	
	</div>
	
</body>
</html>
<script>
   $(document).ready(function() {
		
	//验证家政员身份证号码是否存在
	$("input[name='servicestaffinfoModel.certCode']").blur(function(){
			$("span[name='servicestaffinfoModel.certCode']").remove();
			var certCode=$("input[name='servicestaffinfoModel.certCode']").val();
			if(certCode!=''){
				var url="${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/checkServiceStaffinfoCertCode";
				$.get(url,{certCode:certCode,ranNum:Math.random()},function(data){
					if(data=="true"){
						$("input[name='servicestaffinfoModel.certCode']").after("<span name='mobilespan'><font color='red'><aebiz:showTitle titleId="servicestaffinfoModel.certCode.existed" /></font></span>");
						$("input[name='mobileHidden']").val("false");
					}else{
						$("input[name='mobileHidden']").val("true");
						$("span[name='mobilespan']").remove();
					}
				});
			}
		});
	
	
		
		
	});
	
</script>




<script type="text/javascript">
		// 百度地图API功能
		function G(id) {
			return document.getElementById(id);
		}
	
		var map = new BMap.Map("l-map");
		map.centerAndZoom("北京",12);                   // 初始化地图,设置城市和地图级别。
	
		var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
			{"input" : "suggestId"
			,"location" : map
		});
	
		ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
		var str = "";
			var _value = e.fromitem.value;
			var value = "";
			if (e.fromitem.index > -1) {
				value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
			}    
			str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
			value = "";
			if (e.toitem.index > -1) {
				_value = e.toitem.value;
				value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
			}    
			str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
			G("searchResultPanel").innerHTML = str;
		});
	
		var myValue;
		ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
			var _value = e.item.value;
			myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
			G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
			setPlace();
		});
	
		function setPlace(){
			map.clearOverlays(); 
	   //清除地图上所有覆盖物
			function myFun(){
				var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
				map.centerAndZoom(pp, 18);
				map.addOverlay(new BMap.Marker(pp));    //添加标注
				map.panTo(pp);
				$("#longitude").val(pp.lng);
				$("#latitude").val(pp.lat);
			}
			var local = new BMap.LocalSearch(map, { //智能搜索
			  onSearchComplete: myFun
			});
			
			local.search(myValue);
		}
</script>





<aebiz:showErrorMsg></aebiz:showErrorMsg>