<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>套餐详情</h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="packagemanagement.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="packagemanagement.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="packagemanagement.moduleName_CN"/>详细信息</span>
				</li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">
	   		<form:form id="baseInfoForm"  method="post" commandName="m" class='form-horizontal form-validate form-bordered' enctype="multipart/form-data">							
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="packagemanagement.m.packageName"/></label>
						<div class="col-sm-10">
						&nbsp;${m.packageName}
						</div>
					</div>
					<div class="form-group">
					<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="packagemanagement.m.plusTimes"/></label>
						<div class="col-sm-10">
							&nbsp;${m.plusTimes}
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="packagemanagement.m.phoneTimes"/></label>
						<div class="col-sm-10">
							&nbsp;${m.phoneTimes}
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="packagemanagement.m.length"/></label>
						<div class="col-sm-10">
							<c:if test="${m.duration eq '1'}"> 
									15分钟
							</c:if> 
							<c:if test="${m.duration eq '2'}"> 
									30分钟
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">周期</label>
						<div class="col-sm-10">
							<c:if test="${m.period=='0'}"> 
									一个月
							</c:if> 
							<c:if test="${m.period=='1'}"> 
									一个季度
							</c:if> 
							<c:if test="${m.duration=='2'}"> 
									一年
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="packagemanagement.m.money"/></label>
						<div class="col-sm-10">
								&nbsp;${m.money}
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="packagemanagement.m.note"/></label>
						<div class="col-sm-10">
									&nbsp;${m.note}
						</div>
					</div> 
				</form:form>
				
				<div class="y_fixbtnbox">
					<div class="y_fixedbtn">
						<button type="button" class="btn btn-primary cancel btn-large a_size_1">返回</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>	
	<div class="y_fixedbtn">		
		<button type="button"  class="btn btn-primary cancel btn-large a_size_1">返回</button>
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