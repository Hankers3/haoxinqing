<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script> 
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datatable/jquery.dataTables.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.passwdcheck.js"></script> 
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
						<li>
							<span><aebiz:showTitle titleId="servicestaff.menuOne"/></span>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<span><aebiz:showTitle titleId="servicestaff.menuTwo"/></span>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="servicestaff.moduleName_CN"/></span>
						</li>
					</ul>
				</div>		
				
	
					
					<div class="row">
						<div class="col-sm-12">
							<div class="box box-bordered bordered-top">
								<div class="box-content nopadding">
									<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/addServicestaff" method="post" class='form-horizontal form-bordered form-validate' enctype="multipart/form-data" >
										
										<div class="form-group">
											<label for="textfield" class="control-label col-sm-2  ">&nbsp;<aebiz:showTitle titleId="servicestaff.m.serviceStaffName"/></label>
											<div class="col-sm-10">
												<div class="col-sm-3">
													<input type="text" name="servicestaffModel.serviceStaffName" class="form-control" data-rule-required="true"  data-rule-maxlength="20">
													<input type="hidden" name="nameHidden" value="true">
												</div>
												<div class="col-xs-4">
													<span class="help-block pl_5" id="nameTip"></span>
												</div>
											</div>
										</div>
					
									<div class="form-group">
										<label for="textfield" class="control-label col-sm-2  ">&nbsp;<aebiz:showTitle titleId="servicestaff.m.mobile" /></label>
										<div class="col-sm-10">
											<div class="col-sm-3">
												<input type="text" name="servicestaffModel.mobile" class="form-control" data-rule-required="true" data-rule-mobilezh="true" data-rule-maxlength="11" >
												<input type="hidden" name="mobileHidden" value="true">
											</div>
										</div>
									</div>
								
								<div class="form-group">
								<label for="textfield" class="control-label col-sm-2">&nbsp;<aebiz:showTitle titleId="servicestaff.m.password"/></label>
								<div class="col-sm-10">
									<div class="col-sm-3">
										<input type="password" id="password" name="ServicestaffModel.password"  class="form-control" data-rule-required="true" data-rule-minlength="6" data-rule-maxlength="20">
									</div>
									
									<div  id="pwdpower" class="paddword_leve paddword_leve_3" > 
										<span id="pweak" style=""><aebiz:showTitle titleId="system.m.pweak"/></span>
										<span id="pmedium" style=""><aebiz:showTitle titleId="system.m.pmedium"/></span>
										<span id="pstrong" style=""><aebiz:showTitle titleId="system.m.pstrong"/></span>
									</div> 	
								</div>
							</div>
							
							
				     <div class="form-group">
								<label for="textfield" class="control-label col-sm-2">&nbsp;<aebiz:showTitle titleId="servicestaff.m.confirmPwd"/></label>
								<div class="col-sm-10" id="confirmPwdError">
									<div class="col-sm-3">
										<input type="password" data-rule-equalTo="#password" class="form-control" data-rule-required="true">
									</div>
								</div>
							</div>
					
					   
					   
					   
					   
							
							
							
							
							
							<!--医生基本信息-->
		<div class="tab-pane active" id="baseInfo">
				<div class="table table-bordered table-hover table-nomargin">
					<div class="form-group">
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">&nbsp;<aebiz:showTitle titleId="servicestaffinfo.m.realName"/></label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<input name="servicestaffModel.realName" class='form-control' data-rule-required="true" />
								</div>
							</div>
						</div>
						
		
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="servicestaffinfo.m.sex"/></label>
							<div class="col-sm-10">
								<div class="col-sm-1">
									<div class="check-line">
											<input type=radio name="servicestaffinfoModel.sex" CHECKED value="1" id="c5" class="icheck-me" data-skin="square" data-color="blue">
										<label class='inline' for="c5"><aebiz:showTitle titleId="servicestaffinfo.m.male" /></label>
									</div>
								</div>
								
								<div class="col-sm-1">
									<div class="check-line">
										<INPUT type=radio name="servicestaffinfoModel.sex" value="2" id="c6" class="icheck-me" data-skin="square" data-color="blue">
										<label class='inline' for="c6"><aebiz:showTitle titleId="servicestaffinfo.m.female"/></label>
									</div>
								</div>
							</div>
						</div>
						
				    <div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">&nbsp;<aebiz:showTitle titleId="servicestaffinfo.m.age"/></label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<input name="servicestaffinfoModel.age" class='form-control'  data-rule-required="true" />
										
								</div>
							</div>
						</div>
					
					
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="servicestaffinfo.m.birthday"/></label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<input name="servicestaffinfoModel.birthday" class='form-control datepick'/>
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
									<input name="servicestaffinfoModel.address" class='form-control' data-rule-required="true"  data-rule-maxlength="18" />
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">&nbsp;<aebiz:showTitle titleId="servicestaffinfo.m.certCode"/></label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<input name="servicestaffinfoModel.certCode" class='form-control' data-rule-required="true"  data-rule-maxlength="18" />
								</div>
							</div>
						</div>
						
						
						
						<!--工作经验-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">&nbsp;<aebiz:showTitle titleId="servicestaffinfo.m.experience"/></label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									<input name="servicestaffinfoModel.experience" class='form-control' data-rule-required="true" />
								</div>
							</div>
						</div>

						</div>
						<div class="form-actions col-sm-offset-2 col-sm-10">
							<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>
							<a class="btn" href="${pageContext.servletContext.contextPath}/sysback/servicestaff/toList"><aebiz:showTitle titleId="basebusiness.showmessage.return"/></a>
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
											<input type="file" name="imgFile"  data-rule-required="true" />
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
												
				</form>
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
			
			
			$(".submit").click(function(){
				
			
			 	var nameError=$("input[name='nameHidden']").val();
				var mobileError=$("input[name='mobileHidden']").val();
				if(nameError=="true"&&mobileError=="true"){
					$("#mainForm").submit();
				}
			 });
   });
    
    $(document).ready(function(){
		
			 $("input[name='servicestaffModel.serviceStaffName']").focus(function(){
			 $("#nameTip").empty();
			 $("#nameTip").append("<font color='red'><aebiz:showTitle titleId="servicestaff.add.nameTip"/></font>");
		 });
		 
		 //验证家政人员名称是否存在
		$("input[name='servicestaffModel.serviceStaffName']").blur(function(){
			$("span[name='namespan']").remove();
			var serviceStaffName=$("input[name='servicestaffModel.serviceStaffName']").val();
			var nameReg=/^[\u4E00-\u9FA5\uF900-\uFA2D\w]{2,20}$/;
			if(serviceStaffName.replace(/([\u4E00-\u9FA5\uf900-\ufa2d])/g,'aa').length>20 ||serviceStaffName.replace(/([\u4E00-\u9FA5\uf900-\ufa2d])/g,'aa').length<4 ){
				$("input[name='nameHidden']").val("false");
				$("#nameTip").empty();
				$("#nameTip").append("<font color='red'><aebiz:showTitle titleId="servicestaff.add.nameTip"/></font>");
				return ;
			}else{
				if(serviceStaffName!=''&&nameReg.test(serviceStaffName)){
					$("#nameTip").empty();
					var url="${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/checkServiceStaffName";
					$.get(url,{serviceStaffName:serviceStaffName,ranNum:Math.random()},function(data){
						if(data=="true"){
							$("input[name='servicestaffModel.serviceStaffName']").after("<span name='namespan'><font color='red'><aebiz:showTitle titleId="ServicestaffModel.serviceStaffName.existed" /></font></span>");
							$("input[name='nameHidden']").val("false");
						}else{
							$("input[name='nameHidden']").val("true");
							$("span[name='namespan']").remove();
						}
					});
				}else{
					$("input[name='nameHidden']").val("false");
				}
			}
		});
		
	//验证家政员手机号是否存在
	$("input[name='servicestaffModel.mobile']").blur(function(){
			$("span[name='servicestaffModel.mobilespan']").remove();
			var mobile=$("input[name='servicestaffModel.mobile']").val();
			if(mobile!=''){
				var url="${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/checkMobile";
				$.get(url,{mobile:mobile,ranNum:Math.random()},function(data){
					if(data=="true"){
						$("input[name='servicestaffModel.mobile']").after("<span name='mobilespan'><font color='red'><aebiz:showTitle titleId="ServicestaffModel.mobile.existed" /></font></span>");
						$("input[name='mobileHidden']").val("false");
					}else{
						$("input[name='mobileHidden']").val("true");
						$("span[name='mobilespan']").remove();
					}
				});
			}
		});
	
	
		
		
		 //修改密码强弱校验  
		 $("#password").change(function(e) {
		 	EvalPwd($("#password").val()) ;
		 });  
		 
		 //修改密码强弱校验  
		 $("#password").keyup(function(e) {
		 	EvalPwd($("#password").val()) ;
		 });
		

	});
	
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>