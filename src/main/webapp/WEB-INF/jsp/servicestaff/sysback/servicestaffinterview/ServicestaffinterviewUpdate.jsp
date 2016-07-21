<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


	
<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/servicestaffinterview/update" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>							

	<form:hidden path="serviceStaffinfoRealName"  />
	<form:hidden path="serviceStaffinfoIndustry" />
	<form:hidden path="uuid" />
	<form:hidden path="serviceStaffUuid" />

	<!--面试评估表-->
	<div class="modal-dialog modal_1000">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="user-infos"><aebiz:showTitle titleId="servicestaffinterview.moduleName_CN"/></h3>
			</div>
			<div class="modal-body">
				<div class="tab-content">
					<div class="y_clear mb_10">	
						<!--姓名-->
						<div class="col-sm-2"><aebiz:showTitle titleId="servicestaffinterview.m.serviceStaffinfoRealName"/>:
							${m.serviceStaffinfoRealName}
						</div>
						<!--行业-->
						<div class="col-sm-2"><aebiz:showTitle titleId="servicestaffinterview.m.serviceStaffinfoIndustry"/>:
							<c:if test="${m.serviceStaffinfoIndustry=='1'}">
								家政
							</c:if>
							<c:if test="${m.serviceStaffinfoIndustry=='2'}">
								月嫂
							</c:if>
						</div>
					</div>
					<table id="user" class="table table-bordered table-striped dataTable-reorder ms_list">
						<thead>		
							<tr>
								<th><aebiz:showTitle titleId="servicestaffinterview.m.manageroption"/></th>
							</tr>
						</thead>
						<tbody>
							
							<!--对应聘岗位的工作胜任程度-->
							<tr>
								<td><aebiz:showTitle titleId="servicestaffinterview.m.competence.describe"/></td>
							</tr>
	
							<tr>	
								<td>
									<span class="col-sm-3">
										<div class="check-line">
											<form:radiobutton path="competence" id="1" value="0" class="icheck-me" data-skin="square" data-color="blue" checked="true"/>
											<label class='inline' for="1"><aebiz:showTitle titleId="servicestaffinterview.m.competence.describe0"/></label>
										</div>
									</span>
									<span class="col-sm-2">
										<div class="check-line">
											<form:radiobutton path="competence" id="2" value="1" class="icheck-me" data-skin="square" data-color="blue"/>
											<label class='inline' for="2"><aebiz:showTitle titleId="servicestaffinterview.m.competence.describe1"/></label>
										</div>
									</span>
									<span class="col-sm-2">
										<div class="check-line">
											<form:radiobutton path="competence" id="3" value="2" class="icheck-me" data-skin="square" data-color="blue"/>
											<label class='inline' for="3"><aebiz:showTitle titleId="servicestaffinterview.m.competence.describe2"/></label>
										</div>
									</span>
									<span class="col-sm-4">
										<div class="check-line">
											<form:radiobutton path="competence" id="4" value="3" class="icheck-me" data-skin="square" data-color="blue"/>
											<label class='inline' for="4"><aebiz:showTitle titleId="servicestaffinterview.m.competence.describe3"/></label>
										</div>
									</span>
									<span class="col-sm-2">
										<div class="check-line">
											<form:radiobutton path="competence" id="5" value="4" class="icheck-me"  data-skin="square" data-color="blue"  />
											<label class='inline' for="5"><aebiz:showTitle titleId="servicestaffinterview.m.competence.describe4"/></label>
										</div>
									</span>
									<span class="col-sm-2">
										<div class="check-line">
											<form:radiobutton path="competence" id="6" value="5" class="icheck-me"  data-skin="square" data-color="blue"  />
											<label class='inline' for="6"><aebiz:showTitle titleId="servicestaffinterview.m.competence.describe5"/></label>
										</div>
									</span>
								</td>
							</tr>
							
							<!--理论、经验、能力与岗位要求配合程度-->
							<tr>
								<td><aebiz:showTitle titleId="servicestaffinterview.m.experience.describe"/></td>
							</tr>
							
							<tr>
								<td>
									<span class="col-sm-2">
										<div class="check-line">
											<form:radiobutton path="experience" id="a1" value="0" class="icheck-me"  data-skin="square" data-color="blue"  checked="true"/>
											<label class='inline' for="a1"><aebiz:showTitle titleId="servicestaffinterview.m.experience.describe0"/></label>
										</div>
									</span>
									<span class="col-sm-2">
										<div class="check-line">
											<form:radiobutton path="experience" id="a2"  value="1" class="icheck-me"  data-skin="square" data-color="blue"  />
											<label class='inline' for="a2"><aebiz:showTitle titleId="servicestaffinterview.m.experience.describe1"/></label>
										</div>
									</span>
									<span class="col-sm-2">
										<div class="check-line">
												<form:radiobutton path="experience" id="a3"  value="2" class="icheck-me"  data-skin="square" data-color="blue"  />
											<label class='inline' for="a3"><aebiz:showTitle titleId="servicestaffinterview.m.experience.describe2"/></label>
										</div>
									</span>
									<span class="col-sm-3">
										<div class="check-line">
												<form:radiobutton path="experience" id="a4"  value="3" class="icheck-me"  data-skin="square" data-color="blue"  />
											<label class='inline' for="a4"><aebiz:showTitle titleId="servicestaffinterview.m.experience.describe3"/></label>
										</div>
									</span>
									<span class="col-sm-2">
										<div class="check-line">
												<form:radiobutton path="experience" id="a5"  value="4" class="icheck-me"  data-skin="square" data-color="blue"  />
											<label class='inline' for="a5"><aebiz:showTitle titleId="servicestaffinterview.m.experience.describe4"/></label>
										</div>
									</span>
								</td>
							</tr>
							
							<!--领悟能力、灵活度-->
							<tr>
								<td><aebiz:showTitle titleId="servicestaffinterview.m.comprehend.describe"/></td>
							</tr>
							
							<tr>
								<td>
									<span class="col-sm-2">
										<div class="check-line">
											<form:radiobutton path="comprehend" id="b1"  value="0" class="icheck-me"  data-skin="square" data-color="blue" checked="true" />
											<label class='inline' for="b1"><aebiz:showTitle titleId="servicestaffinterview.m.comprehend.describe0"/></label>
										</div>
									</span>
									<span class="col-sm-2">
										<div class="check-line">
											<form:radiobutton path="comprehend" id="b2"  value="1" class="icheck-me"  data-skin="square" data-color="blue"  />
											<label class='inline' for="b2"><aebiz:showTitle titleId="servicestaffinterview.m.comprehend.describe1"/></label>
										</div>
									</span>
									<span class="col-sm-2">
										<div class="check-line">
											<form:radiobutton path="comprehend" id="b3"  value="2" class="icheck-me"  data-skin="square" data-color="blue"  />
											<label class='inline' for="b3"><aebiz:showTitle titleId="servicestaffinterview.m.comprehend.describe2"/></label>
										</div>
									</span>
									<span class="col-sm-2">
										<div class="check-line">
											<form:radiobutton path="comprehend" id="b4"  value="3" class="icheck-me"  data-skin="square" data-color="blue"  />
											<label class='inline' for="b4"><aebiz:showTitle titleId="servicestaffinterview.m.comprehend.describe3"/></label>
										</div>
									</span>
									<span class="col-sm-2">
										<div class="check-line">
											<form:radiobutton path="comprehend" id="b5"  value="4" class="icheck-me"  data-skin="square" data-color="blue"  />
											<label class='inline' for="b5"><aebiz:showTitle titleId="servicestaffinterview.m.comprehend.describe4"/></label>
										</div>
									</span>
								</td>
							</tr>
							
							<!--学习能力-->
							<tr>
								<td><aebiz:showTitle titleId="servicestaffinterview.m.learn.describe"/></td>
							</tr>
							
							<tr>
								<td>
									<span class="col-sm-2">
										<div class="check-line">
											<form:radiobutton path="learn" id="c1"  value="0" class="icheck-me"  data-skin="square" data-color="blue" checked="true" />
											<label class='inline' for="c1"><aebiz:showTitle titleId="servicestaffinterview.m.learn.describe0"/></label>
										</div>
									</span>
									<span class="col-sm-2">
										<div class="check-line">
											<form:radiobutton path="learn" id="c2"  value="1" class="icheck-me"  data-skin="square" data-color="blue"  />
											<label class='inline' for="c2"><aebiz:showTitle titleId="servicestaffinterview.m.learn.describe1"/></label>
										</div>
									</span>
									<span class="col-sm-2">
										<div class="check-line">
											<form:radiobutton path="learn" id="c3"  value="2" class="icheck-me"  data-skin="square" data-color="blue"  />
											<label class='inline' for="c3"><aebiz:showTitle titleId="servicestaffinterview.m.learn.describe2"/></label>
										</div>
									</span>
									<span class="col-sm-2">
										<div class="check-line">
											<form:radiobutton path="learn" id="c4"  value="3" class="icheck-me"  data-skin="square" data-color="blue"  />
											<label class='inline' for="c4"><aebiz:showTitle titleId="servicestaffinterview.m.learn.describe3"/></label>
										</div>
									</span>
								</td>
							</tr>
							
							<!--个人形象、精神面貌、态度与应聘岗位要求的配合度-->
							<tr>
								<td><aebiz:showTitle titleId="servicestaffinterview.m.personalImage.describe"/></td>
							</tr>
							
							<tr>
								<td>
									<span class="col-sm-2">
										<div class="check-line">
											<form:radiobutton path="personalImage" id="d1"  value="0" class="icheck-me"  data-skin="square" data-color="blue" checked="true" />
											<label class='inline' for="d1"><aebiz:showTitle titleId="servicestaffinterview.m.personalImage.describe0"/></label>
										</div>
									</span>
									<span class="col-sm-2">
										<div class="check-line">
											<form:radiobutton path="personalImage" id="d2"  value="1" class="icheck-me"  data-skin="square" data-color="blue"  />
											<label class='inline' for="d2"><aebiz:showTitle titleId="servicestaffinterview.m.personalImage.describe1"/></label>
										</div>
									</span>
									<span class="col-sm-2">
										<div class="check-line">
											<form:radiobutton path="personalImage" id="d3"  value="2" class="icheck-me"  data-skin="square" data-color="blue"  />
											<label class='inline' for="d3"><aebiz:showTitle titleId="servicestaffinterview.m.personalImage.describe2"/></label>
										</div>
									</span>
									<span class="col-sm-2">
										<div class="check-line">
											<form:radiobutton path="personalImage" id="d4"  value="3" class="icheck-me"  data-skin="square" data-color="blue"  />
											<label class='inline' for="d4"><aebiz:showTitle titleId="servicestaffinterview.m.personalImage.describe3"/></label>
										</div>
									</span>
									<span class="col-sm-2">
										<div class="check-line">
											<form:radiobutton path="personalImage" id="d5"  value="4" class="icheck-me"  data-skin="square" data-color="blue"  />
											<label class='inline' for="d5"><aebiz:showTitle titleId="servicestaffinterview.m.personalImage.describe4"/></label>
										</div>
									</span>
								</td>
							</tr>
							
								<!--评语、签名(已限制最大长度为40)-->
							<tr>
								<td><aebiz:showTitle titleId="servicestaffinterview.m.remark.describe"/></td>
							</tr>
							
							<tr>
								<td>
									<form:input path="remark" class='form-control' maxlength="40"/>
								</td>
							</tr>
							
						</tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer">
						<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.pass"/>'>
						<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.nopass"/></button>
					
			</div>
		</div>
	</div>
<!--面试评估表-->
</form:form>


<script>
    $(document).ready(function() {
		$(".cancel").click(function(){
			window.location.href="${pageContext.servletContext.contextPath}/sysback/servicestaff/frozen/${m.serviceStaffUuid}";
			//fhistory.go(-1) ;
		});	    	
    });
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>