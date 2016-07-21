<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="medicalrecord.moduleName_CN"/>查看详细</h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="medicalrecord.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="medicalrecord.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="medicalrecord.moduleName_CN"/>查看详细</span>
				</li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">								










				<div class="tab-pane active" id="baseInfo">
					<form:form id="baseInfoForm" action="${pageContext.servletContext.contextPath}/sysback/medicalrecord/updateBaseInfo" method="post" commandName="m" class='form-horizontal form-validate form-bordered' enctype="multipart/form-data">
						<!--患者基本信息-->
						<div class="form-group">
							<div class="col-sm-10">
								<div class="col-sm-3">
									&患者基本信息
								</div>
							</div>
						</div>
						
						
						<!--流水号 患者ID-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">患者ID</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;暂无数据
								</div>
							</div>
						</div>
						
						<!--用户名-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">患者ID</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${}
								</div>
							</div>
						</div>
						
						<!--患者姓名-->
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2  ">患者姓名</label>
							<div class="col-sm-10">
								<div class="col-sm-3">
									&nbsp;${}
								</div>
							</div>
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
		$(".cancel").click(function(){
			history.go(-1) ;
		});	    	
    });
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>