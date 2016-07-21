<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.passwdcheck.js"></script> 

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="servicestaffinterview.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="servicestaffinterview.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="servicestaffinterview.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="servicestaffinterview.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></span>
				</li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">								
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/servicestaffinterview/update" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>							
					
					
   				<!--����-->	
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinterview.m.serviceStaffinfoRealName"/></label>
						<div class="col-sm-10">
						<form:input path="serviceStaffinfoRealName" class='form-control' readonly="true" />
						</div>
					</div>
					
					<!--���Ը�λ-->	
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinterview.m.serviceStaffinfoIndustry"/></label>
						<div class="col-sm-10">
						<form:input path="serviceStaffinfoIndustry" class='form-control' readonly="true" />
						</div>
					</div>
					
					<!--���Ա��-->	
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinterview.m.uuid"/></label>
						<div class="col-sm-10">
						<form:input path="uuid" class='form-control' readonly="true" />
						</div>
					</div>		
					
					
					
					<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  "><aebiz:showTitle titleId="servicestaffinterview.m.competence.describe"/></label>
							<div class="col-sm-10">
								<div class="col-sm-1">
									<div class="check-line">
										<form:radiobutton path="competence" id="c1" value="1" class="icheck-me" data-skin="square" data-color="blue"/>
										<label class='inline' for="c1"><aebiz:showTitle titleId="servicestaff.m.frozened" /></label>
									</div>
								</div>
								<div class="col-sm-1">
									<div class="check-line">
										<form:radiobutton path="competence" id="c2" value="0" class="icheck-me" data-skin="square" data-color="blue"/>
										<label class='inline' for="c2"><aebiz:showTitle titleId="servicestaffinterview.m.competence" /></label>
									</div>
								</div>
								<div class="col-sm-1">
									<div class="check-line">
										<form:radiobutton path="competence" id="c2" value="2" class="icheck-me" data-skin="square" data-color="blue"/>
										<label class='inline' for="c2"><aebiz:showTitle titleId="servicestaffinterview.m.competence" /></label>
									</div>
								</div>
								<div class="col-sm-1">
									<div class="check-line">
										<form:radiobutton path="competence" id="c2" value="3" class="icheck-me" data-skin="square" data-color="blue"/>
										<label class='inline' for="c2"><aebiz:showTitle titleId="servicestaffinterview.m.competence" /></label>
									</div>
								</div><div class="col-sm-1">
									<div class="check-line">
										<form:radiobutton path="competence" id="c2" value="4" class="icheck-me" data-skin="square" data-color="blue"/>
										<label class='inline' for="c2"><aebiz:showTitle titleId="servicestaffinterview.m.competence" /></label>
									</div>
								</div><div class="col-sm-1">
									<div class="check-line">
										<form:radiobutton path="competence" id="c2" value="5" class="icheck-me" data-skin="square" data-color="blue"/>
										<label class='inline' for="c2"><aebiz:showTitle titleId="servicestaffinterview.m.competence" /></label>
									</div>
								</div>
							</div>
						</div>
						
						
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinterview.m.experience"/></label>
						<div class="col-sm-10">
						<form:input path="experience" class='form-control'  />
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinterview.m.comprehend"/></label>
						<div class="col-sm-10">
						<form:input path="comprehend" class='form-control'  />
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinterview.m.learn"/></label>
						<div class="col-sm-10">
						<form:input path="learn" class='form-control'  />
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinterview.m.personalImage"/></label>
						<div class="col-sm-10">
						<form:input path="personalImage" class='form-control'  />
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinterview.m.remark"/></label>
						<div class="col-sm-10">
						<form:input path="remark" class='form-control'  />
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="servicestaffinterview.m.interviewUuid"/></label>
						<div class="col-sm-10">
						<form:input path="interviewUuid" class='form-control'  />
						</div>
					</div>
	
	
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>
						<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
					</div>
							
				</form:form>
			</div>
		</div>
	</div>
	
	
	
	
	
<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/servicestaffinterview/update" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>							
	<!--����������-->
<div id="modal-user" >
	<div class="modal-dialog modal_1000">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">��</button>
				<h3 id="user-infos"><aebiz:showTitle titleId="servicestaffinterview.moduleName_CN"/></h3>
			</div>
			<div class="modal-body">
				<div class="tab-content">
					<div class="y_clear mb_10">	
						<!--����-->
						<div class="col-sm-2"><aebiz:showTitle titleId="servicestaffinterview.m.serviceStaffinfoRealName"/><form:input path="serviceStaffinfoRealName" class='form-control' readonly="true" /></div>
						<!--��ҵ-->
						<div class="col-sm-2"><aebiz:showTitle titleId="servicestaffinterview.m.serviceStaffinfoIndustry"/><form:input path="serviceStaffinfoIndustry" class='form-control' readonly="true" /></div>
						<!--���Ա��-->
						<div class="col-sm-2"><aebiz:showTitle titleId="servicestaffinterview.m.uuid"/><form:input path="uuid" class='form-control' readonly="true" /></div>
					</div>
					<table id="user" class="table table-bordered table-striped dataTable-reorder ms_list">
						<thead>		
							<tr>
								<th><aebiz:showTitle titleId="servicestaffinterview.m.manageroption"/></th>
							</tr>
						</thead>
						<tbody>
							
							<!--��ӦƸ��λ�Ĺ���ʤ�γ̶�-->
							<tr>
								<td><aebiz:showTitle titleId="servicestaffinterview.m.competence.describe"/></td>
							</tr>
	
							<tr>	
								<td>
									<span class="col-sm-3">
										<div class="check-line">
											<form:radiobutton path="competence" id="1" value="0" class="icheck-me" data-skin="square" data-color="blue"/>
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
							
							<!--���ۡ����顢�������λҪ����ϳ̶�-->
							<tr>
								<td><aebiz:showTitle titleId="servicestaffinterview.m.experience.describe"/></td>
							</tr>
							
							<tr>
								<td>
									<span class="col-sm-2">
										<div class="check-line">
											<form:radiobutton path="experience" id="a1" value="0" class="icheck-me"  data-skin="square" data-color="blue"  />
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
							
							<!--��������������-->
							<tr>
								<td><aebiz:showTitle titleId="servicestaffinterview.m.comprehend.describe"/></td>
							</tr>
							
							<tr>
								<td>
									<span class="col-sm-2">
										<div class="check-line">
											<form:radiobutton path="comprehend" id="b1"  value="0" class="icheck-me"  data-skin="square" data-color="blue"  />
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
							
							<!--ѧϰ����-->
							<tr>
								<td><aebiz:showTitle titleId="servicestaffinterview.m.learn.describe"/></td>
							</tr>
							
							<tr>
								<td>
									<span class="col-sm-2">
										<div class="check-line">
											<form:radiobutton path="learn" id="c1"  value="0" class="icheck-me"  data-skin="square" data-color="blue"  />
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
							
							<!--�������󡢾�����ò��̬����ӦƸ��λҪ�����϶�-->
							<tr>
								<td><aebiz:showTitle titleId="servicestaffinterview.m.personalImage.describe"/></td>
							</tr>
							
							<tr>
								<td>
									<span class="col-sm-2">
										<div class="check-line">
											<form:radiobutton path="personalImage" id="d1"  value="0" class="icheck-me"  data-skin="square" data-color="blue"  />
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
							
								<!--���ǩ��-->
							<tr>
								<td><aebiz:showTitle titleId="servicestaffinterview.m.remark.describe"/></td>
							</tr>
							
							<tr>
								<td>
									<form:input path="remark" class='form-control'/>
								</td>
							</tr>
							
						</tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer">
						<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>
						<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
					
			</div>
		</div>
	</div>
</div>
<!--����������-->
</form:form>
	
	
	
	
	
	
	
	
	
	
	
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