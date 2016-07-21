<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>

<%@ include
	file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp"%>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>
					<aebiz:showTitle titleId="platformcommunication.moduleName_CN" />
					<aebiz:showTitle titleId="basebusiness.showmessage.edit" />
				</h1>
			</div>
		</div>

		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle
							titleId="platformcommunication.menuOne" /></span> <i
					class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle
							titleId="platformcommunication.menuTwo" /></span> <i
					class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle
							titleId="platformcommunication.moduleName_CN" /> <aebiz:showTitle
							titleId="basebusiness.showmessage.edit" /></span></li>
			</ul>
		</div>

		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">
				<form:form id="mainForm"
					action="${pageContext.servletContext.contextPath}/sysback/platformcommunication/update"
					method="post" commandName="m"
					class='form-horizontal form-validate form-bordered'>
					<form:hidden path="uuid" />
					<!-- <input type="hidden" id="state" name="state" />	 -->

					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle
								titleId="platformcommunication.m.platformrName" /></label>
						<div class="col-sm-10">
							<form:input path="platformrName" class='form-control' />
						</div>
					</div>

					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle
								titleId="platformcommunication.m.questionerUuid" /></label>
						<div class="col-sm-10">
							<form:input path="questionerUuid" class='form-control' />
						</div>
					</div>

					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle
								titleId="platformcommunication.m.questionerName" /></label>
						<div class="col-sm-10">
							<form:input path="questionerName" class='form-control' />
						</div>
					</div>

					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"> 
						
						
						<aebiz:showTitle
								titleId="platformcommunication.m.userType" /></label>
						<div class="col-sm-10">
							<form:input path="userType" class='form-control' />
						</div>
					</div>

					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle
								titleId="platformcommunication.m.problemDescription" /></label>
						<div class="col-sm-10">
							<form:input path="problemDescription" class='form-control' />
						</div>
					</div>

					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle
								titleId="platformcommunication.m.createTime" /></label>
						<div class="col-sm-10">
							<form:input path="createTime" class='form-control' />
						</div>
					</div>

					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"> 
						<%-- <c:choose>
							 	<c:when
									test="${platformcommunication.m.conftimeState=='1'}">
									<aebiz:showTitle titleId="platformcommunication.m.doctor" />
								</c:when>
								<c:when
									test="${platformcommunication.m.conftimeState=='2'}">
									<aebiz:showTitle titleId="platformcommunication.m.customer" />
								</c:when>
							</c:choose>  --%>
							<aebiz:showTitle titleId="platformcommunication.m.conftimeState" />
						</label>
						<div class="col-sm-10">
							<form:input path="conftimeState" class='form-control' />
						</div>
					</div>

					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle
								titleId="platformcommunication.m.admin" /></label>
						<div class="col-sm-10">
							<form:input path="admin" class='form-control' />
						</div>
					</div>

					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle
								titleId="platformcommunication.m.replyMessage" /></label>
						<div class="col-sm-10">
							<form:input path="replyMessage" class='form-control' />
						</div>
					</div>

					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle
								titleId="platformcommunication.m.replyTime" /></label>
						<div class="col-sm-10">
							<form:input path="replyTime" class='form-control' />
						</div>
					</div>

					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle
								titleId="platformcommunication.m.conftime" /></label>
						<div class="col-sm-10">
							<form:input path="conftime" class='form-control' />
						</div>
					</div>

					<div class="y_fixedbtn">
						<!--审核通过-->
						<input class="btn btn-primary  btn-large a_size_1"
							onclick="toSubmit('1')"
							value='<aebiz:showTitle titleId="basebusiness.showmessage.agreed"/>'>
						<!--审核不通过-->
						<input class="btn btn-primary  btn-large a_size_1"
							onclick="toSubmit('2')"
							value='<aebiz:showTitle titleId="basebusiness.showmessage.disagreed"/>'>
						<!--返回-->
						<button type="button"
							class="btn btn-primary cancel btn-large a_size_1">
							<aebiz:showTitle titleId="basebusiness.showmessage.return" />
						</button>
						<%-- <input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.review"/>'>
						<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button> --%>
					</div>

				</form:form>
			</div>
		</div>
	</div>
</body>

</html>


<script>
	$(document).ready(function() {
		$(".cancel").click(function() {
			history.go(-1);
		});
	});

	 function toSubmit(s) {
		$("#state").val(s);
		$("#mainForm").submit();
	} 
</script>




<aebiz:showErrorMsg></aebiz:showErrorMsg>