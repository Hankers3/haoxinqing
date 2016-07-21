<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
	
		<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datepicker/bootstrap-datepicker.js"></script>
<head>
  <title>家政员 -个人设置</title>
  <meta name="description" content="aebiz b2b2c index">
  <!--[if IE]> <meta http-equiv="X-UA-Compatible" content="IE=edge"> <![endif]-->
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/import/ListImport.jsp"%>	
	<%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/import/ListImportJs.jsp"%>
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/style.css">
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>	
</head>
<body>
	<%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/common/servicestaffcenterHead.jsp"%>
	<!-- 家政员-个人中心top end-->
	<!-- 家政员-个人中心主体 -->
	<div class="container member_main">
		<div class="row">
			<%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/common/servicestaffcenterLeft.jsp"%>
			<!-- 家政员-个人中心主体右侧 -->
		<form:form id="accountInfoForm" action="${pageContext.servletContext.contextPath}/usercenter/servicestaffcomb/updateServicestaffcombModel" method="post" commandName="m" class='form-horizontal form-validate form-bordered' enctype="multipart/form-data">

		        <form:hidden path="servicestaffModel.uuid"/>
						<form:hidden path="servicestaffModel.password"/>
						<form:hidden path="servicestaffModel.frozenTyp"/>
						<form:hidden path="servicestaffModel.lastWrongLoginTime"/>
						<form:hidden path="servicestaffModel.activeCode"/>
						<form:hidden path="servicestaffModel.loginErrorTimes"/>
						<form:hidden path="servicestaffModel.createTime"/>
						<form:hidden path="servicestaffModel.deviceToken"/>
						<form:hidden path="servicestaffModel.frozenSate"/>
						<form:hidden path="servicestaffModel.serviceStaffLevel"/>		
						<form:hidden path="servicestaffinfoModel.uuid"/>
						<form:hidden path="servicestaffinfoModel.serviceStaffUuid"/>
						<form:hidden path="servicestaffinfoModel.image"/>
						<form:hidden path="servicestaffinfoModel.nickName"/>
						<form:hidden path="servicestaffinfoModel.hobby"/>
						<form:hidden path="servicestaffinfoModel.realName"/>
						<form:hidden path="servicestaffinfoModel.province"/>
						<form:hidden path="servicestaffinfoModel.city"/>
						<form:hidden path="servicestaffinfoModel.region"/>
						<form:hidden path="servicestaffinfoModel.zipCode"/>
						<form:hidden path="servicestaffModel.Sate"/>
						<form:hidden path="servicestaffinfoModel.experience"/>
			<div class="col-mi-9">
				<div class="member_tab">
					<span class="m_cur"><i class="fa fa-user"></i>会员资料</span>
					<b>|</b>
					<span><i class="fa fa-camera"></i>会员头像</span>
				</div>
				<div class="member_tabin" style="display:block;">
					<div class="member_head fl">
						<!--会员资料--会员头像-->
						<div class="member_pic">
							<c:choose>
											<c:when test="${!empty m.servicestaffinfoModel.imgUrl}">
												<li>
													  <img src="${m.servicestaffinfoModel.imgUrl}" alt="" class="img-thumbnail">	
												</li>
											</c:when>
											<c:otherwise>
													<li>
															<img src="../../static/sysback/img/default.jpg" alt="logo">															
													</li>
											</c:otherwise>
							</c:choose>			
						</div>
						
						<!--会员资料--手机号-->
						<p>${m.servicestaffModel.mobile}</p>
						
						<!--修改登录密码-->
						<div class="member_head_btn"><a href="#address-add" data-toggle="modal" class="btn btn-custom">修改登录密码</a></div>									
					
						 <!--修改登录密码弹出框-->
							<div id="address-add" class="modal fade">
								<div class="modal-dialog modal-lg">
									<div class="modal-content">
										<div  class="form-horizontal y_setpassd form-validate" >
											<input type="hidden" id="longitude" name="longitude" value="0">
											<input type="hidden" id="latitude" name="latitude" value="0">
											<input type="hidden" id="region_name" name="region_name">
											<input type="hidden" id="community_name" name="community_name">
											<input type="hidden" id="cityId" value="${qm.cartManagerModel.cityId }"/>
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
												<h3>修改登录密码</h3>
											</div>
											<div class="modal-body">
												
												<div class="form-group" >
													<label for="detial_area" class="col-mi-2 control-label"><span class="text2-color">*</span> 原&nbsp;密&nbsp;码：</label>
													<div class="y_areashow"></div>
													<div class="col-mi-10" id="w_bdapi">
														<div id="l-map" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;" ></div>
														<input type="password" id="password" name="password" size="20" value="" style="width:250px;" class="form-control"/>
														<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
													</div>
												</div>
												<div class="form-group">
													<label for="area_alias" class="col-mi-2 control-label">新&nbsp;密&nbsp;码：</label>
													<div class="col-mi-10">
														<input type="password" id="NewPassword" name="NewPassword" style="width:250px;" class="form-control">
													</div>
												</div>
													<div class="form-group">
													<label for="area_alias" class="col-mi-2 control-label">确认新密码：</label>
													<div class="col-mi-10">
														<input type="password" id="sureNewPassword" name="sureNewPassword" style="width:250px;" class="form-control">
													</div>
												</div>
											
											 </div>
											<div class="modal-footer">
												<button type="button" class="btn btn-custom save_address" onclick="javascript:checkpassword1();">保存</button>
											</div>
										</div>
									</div>
								</div>
							</div>

						
					</div>
					<div class="fl member_form">
						<ul>
							<li>
								<div class="fl form_left">姓名：</div>
								  <div class="fl form_right">
										<!--	<input value="${m.servicestaffModel.serviceStaffName}" class="form-control">-->
										<form:input path="servicestaffModel.serviceStaffName" class='form-control' />
									</div>
							</li>
							<li>
								<!--	有问题 -->
								<div class="fl form_left">性别：</div>
								<div class="fl form_right form_input1">
                  <div class="w_xzf">
                  	<c:if test="${m.servicestaffinfoModel.sex=='1'}">
											<input type="radio" id="c3" class='icheck-me' name="sex" value="1" data-skin="minimal" checked><label class="m_inline_in" for="c3">女</label>
											<input type="radio" id="c4" class='icheck-me' name="sex" value="2" data-skin="minimal" ><label class="m_inline_in" for="c4">男</label>
										</c:if>
										<c:if test="${m.servicestaffinfoModel.sex=='2'}">
											<input type="radio" id="c3" class='icheck-me' name="sex" value="1" data-skin="minimal" ><label class="m_inline_in" for="c3">女</label>
											<input type="radio" id="c4" class='icheck-me' name="sex" value="2" data-skin="minimal" checked><label class="m_inline_in" for="c4">男</label>
										</c:if>
                  	
						       <c:if test="${m.servicestaffinfoModel.sex==''}">
											<input type="radio" id="c3" class='icheck-me' name="sex" value="1" data-skin="minimal" ><label class="m_inline_in" for="c3">女</label>
											<input type="radio" id="c4" class='icheck-me' name="sex" value="2" data-skin="minimal" ><label class="m_inline_in" for="c4">男</label>
									</c:if>
						      </div>
								</div>
							</li>
						</ul>
						<ul>
							<li>
								<div class="fl form_left">出生年月：</div>
								<div class="fl form_right">
									<!--<form:input path="servicestaffinfoModel.birthday" class="form-control"/>-->
									<form:input path="servicestaffinfoModel.birthday" class="form-control  datepick" maxlength="30" id="birthday"  onblur="javascript:Checkbirthday();"/>
								</div>
							</li>
							<li>
								<div class="fl form_left">应聘岗位：</div>
								<div class="fl form_right">
									<input type="radio" id="c3" class='icheck-me' name="industry"  data-skin="minimal" checked><label class="m_inline_in" for="c3">&nbsp;&nbsp;家政员</label>
								</div>
							</li>
						</ul>
						<ul>
							<li>
								<div class="fl form_left">联系电话：</div>
								<div class="fl form_right">
									<form:input path="servicestaffModel.mobile" class="form-control" maxlength="11" id="mobile" onblur="javascript:CheckMobile();"/>
								</div>
							</li>
							<li>
								<div class="fl form_left">身份证号：</div>
								<div class="fl form_right">
									<form:input path="servicestaffinfoModel.certCode" class="form-control" maxlength="18"/>
								</div>
							</li>
						</ul>
						<ul>
							<li>
								<div class="fl form_left">最高学历：</div>
								<div class="fl form_right">
										<form:select path="servicestaffinfoModel.education" class='select2-me form-control'>								
										<form:option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</form:option>
										<c:forEach items="${educationList}" var="dpp">	
											<form:option value="${dpp.name}">${dpp.value}</form:option>
										</c:forEach>										
									</form:select>
										
										
								</div>
							</li>
							<!--<li>
								<div class="fl form_left">籍贯：</div>
								<div class="fl form_right">
									<form:input path="servicestaffinfoModel.address" class="form-control"/>
								</div>
							</li>-->
							<li>
								<div class="fl form_left">QQ/邮箱：</div>
								<div class="fl form_right">
									<!--<form:input path="servicestaffModel.email" class="form-control"/>-->
									<form:input path="servicestaffModel.email" class="form-control" maxlength="30" id="mail"  onblur="javascript:CheckMail();"/>	
								</div>
							</li>
						</ul>
						<ul>
							<li class="m_p">
								<div class="fl form_left">婚育状况：</div>
								<!--<div class="fl form_right m_xia" id="nnn"><input value="已婚已育" class="form-control m_form"></div>
								<div class="form_xia">
									<p>已婚已育</p>
									<p>未婚未育</p>
								</div>-->
								
								 <div class="fl form_right">
										<form:select path="servicestaffinfoModel.maritalStatus" class='select2-me form-control'>								
										  <form:option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</form:option>
											
											<form:option value="1">已婚</form:option>
											<form:option value="2">未婚</form:option>
											<form:option value="3">保密</form:option>
																		
									  </form:select>
									</div>
								
							</li>
							<li>
								<div class="fl form_left">健康状况：</div>
								<div class="fl form_right">
									<!--<input value="良好" class="form-control">-->
									<form:input path="servicestaffinfoModel.healthCondition" class="form-control"/>
								</div>
							</li>
						</ul>
						<div class="clearfix form_gj">
							<div class="fl form_left">是否有职业病/<br/>精神病/传染病史：</div>
							<div class="fl form_right">
								<!--<input value="湖北武汉" class="form-control">-->
								<form:input path="servicestaffinfoModel.industrialDsease" class="form-control"/>
							</div>
						</div>
							<!--<ul>
						<li>
								<div class="fl form_left">推荐人：</div>
								<div class="fl form_right"><input value="无" class="form-control"></div>
							</li>
							<li>
								<div class="fl form_left">QQ/邮箱：</div>
								<div class="fl form_right">
									<form:input path="servicestaffModel.email" class="form-control"/>
								</div>
							</li>
						</ul>-->
						<div class="clearfix form_zz">
							<div class="clearfix">
								<div class="fl form_left">现住址：</div>
								<div class="fl form_right">
									<form:input path="servicestaffinfoModel.address" class="form-control" maxlength="50"/>
								</div>
							</div>
							
							<div class="clearfix">
								<div class="fl form_left">籍贯：</div>
								<div class="fl form_right">
									<aebiz:area checkProvince="${m.servicestaffinfoModel.province}" checkCity="${m.servicestaffinfoModel.city}" checkRegion="${m.servicestaffinfoModel.region}"></aebiz:area>
								</div>
						  </div>
						  
						
						  
						</div>
						<!--备用联系人 -->
						<!--<div class="form_spare">
							<p>【 备用联系人 】</p>
							<ul>
							<li>
								<div class="fl form_left">联系人<br/>姓名：</div>
								<div class="fl form_right">
									<input value="本科" class="form-control">
								</div>
							</li>
							<li>
								<div class="fl form_left">联系人<br/>电话：</div>
								<div class="fl form_right">
									<input value="湖北武汉" class="form-control">
								</div>
							</li>
						</ul>
						</div>-->
						<!--备用联系人 end-->
						<!--家庭成员 -->
						<div class="form_family">
							<p>【 家庭成员 】</p>
							
							<c:forEach items="${familylist}" var="familylist">
						<!--<form id="accountInfoForm" action="${pageContext.servletContext.contextPath}/servicestaffcomb/updateAccountInfo" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>-->
							<div class="form_familyin">
								<!--	<div class="m_ab"><i class="fa fa-trash-o"></i>删除</div>-->
								<!--<div class="m_addto"><i class="fa fa-plus-square"></i>删除</div>${familylist.uuid}-->
								<a class="m_addto"  onclick="toSavefamily('${familylist.uuid}')"><i class="fa fa-plus-square"></i>保存</a>	
								<a class="m_addto"  onclick="toRemove('${familylist.uuid}')"><i class="fa fa-plus-square"></i>删除</a>		
								<!--<input type="submit" class="m_addto" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>-->				
								<div class="clearfix">
									
										<div class="clearfix fl main">
											<div class="fl form_left">姓名：</div>
										  <div class="fl form_right">
											 	<input value="${familylist.name}"  id="${familylist.uuid}name" class="form-control">
										  </div>
										</div>
										<div class="clearfix fl main">
											<div class="fl form_left">关系：</div>
										  <div class="fl form_right">
										  	<input value="${familylist.relation}" id="${familylist.uuid}relation" class="form-control">
										  </div>
										</div>
										<div class="clearfix fl main">
											<div class="fl form_left">年龄：</div>
										  <div class="fl form_right">
										  	<input value="${familylist.age}" id="${familylist.uuid}age" class="form-control">
										  </div>
										</div>
									
								</div>
								<div class="clearfix main_dw">
										<div class="clearfix fl m_rzdw">
											<div class="fl form_left">任职单位：</div>
										  <div class="fl form_right">
										  	<input value="${familylist.workUnit}" id="${familylist.uuid}workUnit" class="form-control">
										  </div>
										</div>
										<div class="clearfix fl m_lxdh">
											<div class="fl form_left">联系电话：</div>
											<div class="fl form_right"><input value="${familylist.contactPhone}" id="${familylist.uuid}contactPhone" data-rule-mobilezh="true" data-rule-maxlength="11" class="form-control"></div>
										</div>
								</div>
							  <div class="clearfix form_familyin_lxdz">
							  	
							  	<div class="fl form_left">联系地址：</div>
									<div class="fl form_right"><input value="${familylist.contactAddress}" id="${familylist.uuid}contactAddress" class="form-control"></div>
							  	
							  </div>
							</div>
						</c:forEach> 
							
					<!--</form>-->
					
						<!--添加家庭成员-->	
					<div class="m_addto"><a href="#address-add1" data-toggle="modal" class="fa fa-plus-square">添加家庭成员</a></div>		
					<!--添加家庭成员弹出框-->
					<div id="address-add1" class="modal fade">
							<div class="modal-dialog modal-lg">
									<div class="modal-content">
										  <div class="modal-header">
												<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
												<h3>添加家庭成员</h3>
											</div>
										<div  class="form_familyin" >																		
											<div class="clearfix">											
												<div class="clearfix fl main">
													<div class="fl form_left">姓名：</div>
												  <div class="fl form_right">
													 	<input value=""  name="nameAdd" id="nameAdd" class="form-control" maxlength="30">
												  </div>
												</div>
												<div class="clearfix fl main">
													<div class="fl form_left">关系：</div>
												  <div class="fl form_right">
												  	<input value=""  name="relationAdd" id="relationAdd" class="form-control" maxlength="5">
												  </div>
												</div>
												<div class="clearfix fl main">
													<div class="fl form_left">年龄：</div>
												  <div class="fl form_right">
												  	<input value="" name="ageAdd" id="ageAdd" class="form-control" maxlength="3"  onKeyUp="value=value.replace(/[^\d]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
												  </div>
												</div>
									  </div>
										<div class="clearfix main_dw">
												<div class="clearfix fl m_rzdw">
													<div class="fl form_left">任职单位：</div>
												  <div class="fl form_right">
												  	<input value="" name="workUnitAdd" id="workUnitAdd" class="form-control" maxlength="30">
												  </div>
												</div>
												<div class="clearfix fl m_lxdh">
													<div class="fl form_left">联系电话：</div>
													<div class="fl form_right"><input value="" id="contactPhoneAdd" name="contactPhoneAdd" class="form-control" maxlength="11"  onKeyUp="value=value.replace(/[^\d]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"></div>
												</div>
										</div>
									  <div class="clearfix form_familyin_lxdz">
									  	<div class="fl form_left">联系地址：</div>
											<div class="fl form_right"><input value=""  id="contactAddressAdd" name="contactAddressAdd"  class="form-control" maxlength="40"></div>
									  </div>
									  <div class="modal-footer">
														<button type="button" class="btn btn-custom save_address1" onclick="javascript:addFamily('${m.servicestaffModel.uuid}')">保存</button>
										</div>									
								</div>
							 </div>
							</div>
					</div>
					<!--添加家庭成员end-->	
							
					<!--<div class="m_addto"><button class="fa fa-plus-square" data-toggle='modal' data-target='#modal-user1' rel='tooltip'>添加家庭成员</button></div>-->
					<!--<div class="m_addto"><input type="submit" onclick="#" class="btn btn-primary moresearch"  data-toggle='modal' data-target='#modal-user1' rel='tooltip' value='添加家庭成员'></div>-->
						</div>
						<!--家庭成员 end-->
						<!--教育经历 -->
						<div class="form_family form_education">
							<p>【 教育经历 】</p>
						<c:forEach items="${educationlist}" var="educationlist">
							
							<a class="m_addto"  onclick="toSaveEducation('${educationlist.uuid}')"><i class="fa fa-plus-square"></i>保存</a>		
							<a class="m_addto"  onclick="toRemoveEducation('${educationlist.uuid}')"><i class="fa fa-plus-square"></i>删除</a>	
							<div class="form_familyin">
								<div class="clearfix">
									<div class="clearfix fl main1">
										<div class="fl form_left">起止年月：</div>
									  <div class="fl form_right"><input value="${educationlist.timeDuration}" id="${educationlist.uuid}timeDuration" class="form-control m_input"></div>
									</div>
									<div class="clearfix fl main">
										<div class="fl form_left">学历：</div>
									  <div class="fl form_right"><input value="${educationlist.educationBackgroun}" id="${educationlist.uuid}educationBackgroun" class="form-control"></div>
									</div>
								</div>
							  <div class="clearfix form_familyin_lxdz">
							  	<div class="fl form_left">学校名称：</div>
									<div class="fl form_right"><input value="${educationlist.schoolName}" id="${educationlist.uuid}schoolName" class="form-control"></div>
							  </div>
							  <div class="clearfix main_dw">
									<div class="clearfix fl m_rzdw">
										<div class="fl form_left">专业：</div>
									  <div class="fl form_right"><input value="${educationlist.major}" id="${educationlist.uuid}major" class="form-control"></div>
									</div>
									<div class="clearfix fl m_lxdh">
										<div class="fl form_left">获得证书<br/>/奖励：</div>
									  <div class="fl form_right"><input value="${educationlist.getCertificate}"  id="${educationlist.uuid}getCertificate" class="form-control"></div>
									</div>
							  </div>
							</div>
						</c:forEach>
							
							
								
							<!--添加教育经历-->	
					<div class="m_addto"><a href="#address-add2" data-toggle="modal" class="fa fa-plus-square">添加教育经历</a></div>		
					<!--添加教育经历弹出框-->
					<div id="address-add2" class="modal fade">
							<div class="modal-dialog modal-lg">
									<div class="modal-content">
										 <div class="modal-header">
												<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
												<h3>添加教育经历</h3>
											</div>
									<div  class="form_familyin" >																	
													
										<div class="clearfix">
											<div class="clearfix fl main1">
												<div class="fl form_left">起止年月：</div>
											  <div class="fl form_right"><input value="" id="timeDurationAdd" class="form-control m_input" maxlength="20"></div>
											</div>
											<div class="clearfix fl main">
												<div class="fl form_left">学历：</div>
											  <div class="fl form_right"><input value="" id="educationBackgrounAdd" class="form-control" maxlength="11"></div>
											</div>
										</div>
									  <div class="clearfix form_familyin_lxdz">
									  	<div class="fl form_left">学校名称：</div>
											<div class="fl form_right"><input value="" id="schoolNameAdd" class="form-control" maxlength="20"></div>
									  </div>
									  <div class="clearfix main_dw">
											<div class="clearfix fl m_rzdw">
												<div class="fl form_left">专业：</div>
											  <div class="fl form_right"><input value="" id="majorAdd" class="form-control" maxlength="20"></div>
											</div>
											<div class="clearfix fl m_lxdh">
												<div class="fl form_left">获得证书<br/>/奖励：</div>
											  <div class="fl form_right"><input value=""  id="getCertificateAdd" class="form-control" maxlength="25"></div>
											</div>
									  </div>
									  
									  
									  <div class="modal-footer">
														<button type="button" class="btn btn-custom save_address2" onclick="javascript:addEducation('${m.servicestaffModel.uuid}')">保存</button>
										</div>									
								</div>
							 </div>
							</div>
					</div>
					<!--添加教育经历end-->	
						
						<!--教育经历 end-->
						<!--<div class="form_education_btn"><a href="##" class="btn btn-custom">保  存</a></div>-->
						
						<div style="text-align: center;padding: 10px;">
							<input type="submit" class=" btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>
						</div>
					</div>
				</div>
			</div>
			
			<!--会员头像-->
			<div class="member_tabin" style="display:none;">
					<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="servicestaffinfo.m.image"/></label>
							<div class="col-sm-10">
								<div class="fileinput <c:choose><c:when test="${empty m.servicestaffinfoModel.image}">fileinput-new </c:when><c:otherwise>fileinput-exists</c:otherwise></c:choose>" data-provides="fileinput">
									<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 150px; height: 150px;">
										<c:if test="${!empty m.servicestaffinfoModel.image}"><img src="${m.servicestaffinfoModel.imgUrl}" /></c:if>
									</div>
									<div>
										<span class="btn btn-default btn-file">
											<span class="fileinput-new">Select image</span>
											<span class="fileinput-exists">Change</span>
											<input type="file" name="imgFile"/>
										</span>
										<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
									</div>
								</div>
							</div>
			</div>
			<!--会员头像 end-->
			
			
			<script type="text/javascript"> 
				$(".member_tab span").click(function(){
					var x = $(this).index(".member_tab span");
					$(this).addClass("m_cur").siblings(".member_tab span").removeClass("m_cur");
					$(".member_tabin").eq(x).show().siblings(".member_tabin").hide();
				});
				
				//下拉菜单
				$(".member_form .m_xia").click(function(){
					$(this).next(".form_xia").toggle();
				});
				$(".form_xia p").click(function(){
					$(this).parent().hide();
			    $(this).parent().prev(".m_xia").children("input").val($(this).text());
					});
					$(document).click(function(e){ 
					 e = window.event || e; 
					 obj = $(e.srcElement || e.target);
					    if ($(obj).is("#nnn,#nnn *")) { 
					      $('.form_xia').show(); 
					   } else {
					    $('.form_xia').hide(); 
					 } 
					});
			</script>
		  </div>		  
		 </form:form>
		<!-- 家政员-个人中心主体右侧 end -->
	  </div>
  </div>
	 <!-----雇我吧首页底部----->
   <%@ include file="/WEB-INF/jsp/basebusiness/servicestaffcenter/common/servicestaffcenterBottom.jsp"%>
  <!-----雇我吧首页底部 end----->
  <script>
  	 //单选框复选框样式重置
  	if ($(".icheck-me").length > 0) {
      $(".icheck-me").each(function() {
        var $el = $(this);
        var skin = ($el.attr('data-skin') !== undefined) ? "_" + $el.attr('data-skin') : "",  //将data-skin属性的值 传递到参数中
          color = ($el.attr('data-color') !== undefined) ? "-" + $el.attr('data-color') : ""; //将data-color属性的值 传递到参数中

        var opt = {  //设置参数
          checkboxClass: 'icheckbox' + skin + color,
          radioClass: 'iradio' + skin + color,
          increaseArea: "10%"
        }

        $el.iCheck(opt);  //带参数调用js
      });
    }	
  </script>
  
  <script>
	  function toSavefamily(uuid) {
			//alert(uuid);
		
		  //姓名
			var name1=uuid + "name";
			var name=$("#"+name1).val();
			//alert(name);
			
			//关系
			var relation1=uuid + "relation";
			var relation=$("#"+relation1).val();
			//alert(relation);
			
			//年龄
			var age1=uuid+"age";
			var age=$("#"+age1).val();
			//alert(age);
			
			//任职单位
			var workUnit1=uuid+"workUnit";
			var workUnit=$("#"+workUnit1).val();
			//alert(workUnit);
			
			//联系地址
			var contactAddress1=uuid+"contactAddress";
			var contactAddress=$("#"+contactAddress1).val();
			//alert(contactAddress);
			
			//联系电话
			var contactPhone1=uuid+"contactPhone";
			var contactPhone=$("#"+contactPhone1).val();
			//alert(contactPhone);
			
			$.get(
			"${pageContext.servletContext.contextPath}/usercenter/servicestaffcomb/updateFamily",
			{
				"uuid":uuid,
				"name":name,
				"relation":relation,
				"age":age,
				"workUnit":workUnit,
				"contactAddress":contactAddress,
				"contactPhone":contactPhone,
				ranNum : Math.random()
			},
			function(data) {
						
					if(data=="success"){
						   alert("保存成功！");
					}else{
						   alert("保存失败！");
					}
			});
		}
		
		
		
		 function toSaveEducation(uuid) {
			//alert(uuid);
		
		  //起止年月：
			var timeDuration1=uuid + "timeDuration";
			var timeDuration=$("#"+timeDuration1).val();
			//alert(timeDuration);
			
			//学历
			var educationBackgroun1=uuid + "educationBackgroun";
			var educationBackgroun=$("#"+educationBackgroun1).val();
			//alert(educationBackgroun);
			
			//学校名称
			var schoolName1=uuid+"schoolName";
			var schoolName=$("#"+schoolName1).val();
		//	alert(schoolName);
			
			//专业
			var major1=uuid+"major";
			var major=$("#"+major1).val();
			//alert(major);
			
			//证书/奖励
			var getCertificate1=uuid+"getCertificate";
			var getCertificate=$("#"+getCertificate1).val();
			//alert(getCertificate);
			
			
			
			$.get(
			"${pageContext.servletContext.contextPath}/usercenter/servicestaffcomb/updateEducation",
			{
				"uuid":uuid,
				"timeDuration":timeDuration,
				"schoolName":schoolName,
				"educationBackgroun":educationBackgroun,
				"major":major,
				"getCertificate":getCertificate,
				ranNum : Math.random()
			},
			function(data) {
						
					if(data=="success"){
						  alert("保存成功！");
							
					}else{
						
						 alert("保存失败！");
					}
			});
		}
		
		function toRemove(uuid) {
			//alert(uuid);
			
			$.get(
			"${pageContext.servletContext.contextPath}/usercenter/servicestaffcomb/deleteFamily",
			{
				"uuid":uuid,
				ranNum : Math.random()
			},
			function(data) {
						
					if(data=="success"){
						   alert("删除成功！");
						   location.reload();
					}else{
						   alert("删除失败！");
					}
			});
		}
		
		function CheckMail() { 
			var mail=$("#mail").val();
			var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/; 
			if (filter.test(mail)){ 
				 return true; 
			}else {
				 alert('您的电子邮件格式不正确'); 
				 return false;
				 }
			}
			
		function CheckMobile() { 
			var mobile=$("#mobile").val();
			var filter  = /^1[3|4|5|8][0-9]\d{4,8}$/; 
			if (filter.test(mobile)){ 
				 return true; 
			}else {
				 alert('您的手机号格式不正确！请重新输入！'); 
				 return false;
				 }
			}
			
</script>	

<script>
	
	function Checkbirthday() { 
			var mail=$("#birthday").val();
			var filter  = /^((?!0000)[0-9]{4}-((0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])-(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)-02-29)$/; 
			if (filter.test(mail)){ 
				 return true; 
			}else {
				 alert('您的日期格式不正确'); 
				 return false;
				 }
			}
	
</script>



<script>
	
	
	function checkpassword1(){
	  	
	  	var password=$("#password").val();
			//alert(password);	
			
	  	var newpassword=$("#NewPassword").val();
	    //alert(newpassword);
	  	
	  	var sureNewPassword=$("#sureNewPassword").val();
	    //alert(sureNewPassword);
	  	   
			$.get(
			"${pageContext.servletContext.contextPath}/usercenter/servicestaffcomb/checkpasswordAndSave",
			{
				"password":password,
				"newpassword":newpassword,
				"sureNewPassword":sureNewPassword,
				ranNum : Math.random()
			},
			function(data) {
						
					if(data=="true"){
						  alert("修改成功！");
					}else if(data=="false1" ){
						  alert("原密码不存在！");
					}else if( data=="false2"){
						  alert("新密码与确认密码均不准为空！");	 	
					}else if( data=="false3"){
						  alert("原密码不正确！请重新填写！");
					}else if( data=="false4"){
						  alert("新密码与确认密码不一致！");
					}
					
					
			});
		}
		
		
		$('#address-add .save_address').on("click",function(){
     	$('#address-add').modal('hide');
     	$('#address-add').on('hidden.bs.modal',function(){
     	 backdrop:false
       });
     })

</script>


<script>
	
	function addFamily(servicestaffuuid) {
			
	  	var nameAdd=$("#nameAdd").val();
			var relationAdd=$("#relationAdd").val();
			var ageAdd=$("#ageAdd").val();
			var workUnitAdd=$("#workUnitAdd").val();
			var contactAddressAdd=$("#contactAddressAdd").val();
			var contactPhoneAdd=$("#contactPhoneAdd").val();
	  	
			if(nameAdd!=""||relationAdd!=""||ageAdd!=""||workUnitAdd!=""||contactAddressAdd!=""||contactPhoneAdd!=""  ){
			$.get(
			"${pageContext.servletContext.contextPath}/usercenter/servicestaffcomb/AddFamily",
			{
				"servicestaffuuid":servicestaffuuid,
				"nameAdd":nameAdd,
				"relationAdd":relationAdd,
				"ageAdd":ageAdd,
				"workUnitAdd":workUnitAdd,
				"contactAddressAdd":contactAddressAdd,
				"contactPhoneAdd":contactPhoneAdd,
				ranNum : Math.random()
			},
			function(data) {
						
					if(data=="success"){
						   alert("增加成功！");
						   location.reload();
					}else{
						   alert("增加失败！");
					}
			});
		}else{
			
			alert("请完整填写数据！");
			
			
		}}
		
		
		function addEducation(servicestaffuuid) {
			
	  	var timeDurationAdd=$("#timeDurationAdd").val();
			var educationBackgrounAdd=$("#educationBackgrounAdd").val();
			var schoolNameAdd=$("#schoolNameAdd").val();
			var majorAdd=$("#majorAdd").val();
			var getCertificateAdd=$("#getCertificateAdd").val();
	  	
			if(timeDurationAdd!=""||educationBackgrounAdd!=""||schoolNameAdd!=""||majorAdd!=""||getCertificateAdd!="" ){
			
			$.get(
			"${pageContext.servletContext.contextPath}/usercenter/servicestaffcomb/AddEducation",
			{
				"servicestaffuuid":servicestaffuuid,
				"timeDurationAdd":timeDurationAdd,
				"educationBackgrounAdd":educationBackgrounAdd,
				"schoolNameAdd":schoolNameAdd,
				"majorAdd":majorAdd,
				"getCertificateAdd":getCertificateAdd,
				ranNum : Math.random()
			},
			function(data) {
						
					if(data=="success"){
						   alert("增加成功！");
						   location.reload();
					}else{
						   alert("增加失败！");
					}
			});
			}else{
				
				alert("请完整填写数据！");
				
				}
		}
		
	 $('#address-add2 .save_address2').on("click",function(){
     	$('#address-add2').modal('hide');
     	$('#address-add2').on('hidden.bs.modal',function(){
     	 backdrop:false
       });
     })
     
    function toRemoveEducation(uuid) {
			//alert(uuid);
			
			$.get(
			"${pageContext.servletContext.contextPath}/usercenter/servicestaffcomb/deleteEducation",
			{
				"uuid":uuid,
				ranNum : Math.random()
			},
			function(data) {
						
					if(data=="success"){
						   alert("删除成功！");
						   location.reload();
					}else{
						   alert("删除失败！");
					}
			});
		}
</script>
</body>
</html>