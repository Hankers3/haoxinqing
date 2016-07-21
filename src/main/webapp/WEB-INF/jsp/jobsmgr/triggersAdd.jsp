<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>
<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>增加任务</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span>任务管理</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>任务管理</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>增加任务</span>
				</li>
			</ul>
		</div>		
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				
				<form id="mainForm2" action="${pageContext.servletContext.contextPath}/jobsmgr/addTriggerByCronExpression" method="post" class='form-horizontal form-bordered form-validate'>						
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">任务的类名</label>
						<div class="col-sm-10">
							<input type="text" name="className" class="form-control">
						</div>	
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">任务名称</label>
						<div class="col-sm-10">
							<input type="text" name="jobName" class="form-control">
						</div>	
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">任务的组名</label>
						<div class="col-sm-10">
							<input type="text" name="groupName" class="form-control">
						</div>	
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">触发器的名称</label>
						<div class="col-sm-10">
							<input type="text" name="triggerName" class="form-control">
						</div>	
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">触发器的组名</label>
						<div class="col-sm-10">
							<input type="text" name="triggerGroup" class="form-control">
						</div>	
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">cron表达式</label>
						<div class="col-sm-10">
							<input type="text" name="cronExpression" class="form-control">
						</div>	
					</div>
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-primary" value='新增'>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>

</html>