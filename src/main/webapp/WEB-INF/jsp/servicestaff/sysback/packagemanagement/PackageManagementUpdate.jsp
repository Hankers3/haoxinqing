<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/datepicker/datepicker.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.datepicker.js"></script>
</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="packagemanagement.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></h1>
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
					<span><aebiz:showTitle titleId="packagemanagement.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></span>
				</li>
			</ul>
		</div>
				
	<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">							
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/packagemanagement/update" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>							
					<form:hidden path="uuid"/>	
					<form:hidden path="createTime"/>	
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="packagemanagement.m.packageName"/></label>
						<div class="col-sm-10">
							<form:input path="packageName" class='form-control'  data-rule-required="true"/>
							<input type="hidden" name="packageNameHidden" value="true">
						<span name='packageNamespan' ></span>
					</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="packagemanagement.m.plusTimes"/></label>
						<div class="col-sm-10">
							<form:input path="plusTimes" class='form-control' data-rule-required="true" />
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="packagemanagement.m.phoneTimes"/></label>
						<div class="col-sm-10">
							<form:input path="phoneTimes" class='form-control' data-rule-required="true" />
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="packagemanagement.m.length"/></label>
						<div class="col-sm-10">
						 <div class="col-xs-2">
							<form:select path="duration" data-rule-required="true">								
								<form:option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</form:option>
								<form:option value="1">15分钟</form:option>	
								<form:option value="2">30分钟</form:option>									
							</form:select>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">有效周期</label>
						<div class="col-sm-10">
							<form:select path="period" data-rule-required="true">								
								<form:option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</form:option>
								<form:option value="0">一个月</form:option>	
								<form:option value="1">一季度</form:option>	
								<form:option value="2">一年</form:option>		
							</form:select>
						</div>
					</div>
					<!--
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="packagemanagement.m.startTime"/></label>
						<div class="col-sm-10">
							<form:input path="startTime" class='form-control datepick' data-rule-required="true"/>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="packagemanagement.m.endTime"/></label>
						<div class="col-sm-10">
							<form:input path="endTime" class='form-control datepick' onchange="checkdate();" data-rule-required="true"/>
						</div>
					</div>-->
					
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="packagemanagement.m.money"/></label>
						<div class="col-sm-10">
							<form:input path="money" class='form-control' data-rule-required="true" />
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="packagemanagement.m.note"/></label>
						<div class="col-sm-10">
							<form:textarea rows="5" path="note" class='form-control' data-rule-maxlength="40" maxlength="40"/>
						</div>
					</div>
							
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="button" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>' onclick="subm();">
						<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
					</div>
							
				</form:form>
			</div>
		</div>
	</div>
</body>

</html>


<script>
		var packageNameJUDGE=$("input[name='packageName']").val();// 判断用
		var flag=1; //flag=0时不能提交 =1时可以提交
    $(document).ready(function() {
		$(".cancel").click(function(){
			history.go(-1) ;
		});	    	
    });
      $(document).ready(function(){
     	
     	//验证套餐名称是否存在
			$("input[name='packageName']").blur(function(){
					var packageName=$("input[name='packageName']").val();
					if(packageName!=''){
						var url="${pageContext.servletContext.contextPath}/sysback/packagemanagement/checkName";
						$.get(url,{"packageName":packageName,ranNum:Math.random()},function(data){
							if(data=="true"){		
								if(packageNameJUDGE==packageName){
									flag = 1;
									$("span[name='packageNamespan']").html(""); 	 
									} else{				
											$("span[name='packageNamespan']").html("<font color='red'>套餐名已存在</font>"); 	
											$("input[name='packageNameHidden']").val("false");
											flag = 0;
									}
							}else{
								$("span[name='packageNamespan']").html(""); 	 
								flag = 1;
							}
						});
					}
				});
     	});
     
    function checkdate(){
    	var starttime = $("[name='startTime']").val();
    	var endtime = $("[name='endTime']").val();
    	
    	 var d1 = new Date(starttime.replace(/\-/g, "\/"));  
			 var d2 = new Date(endtime.replace(/\-/g, "\/"));  	
			if(d1>d2){
				alert("开始时间不能大于结束时间！");  
			} else {
				flag=1;
				}
    }
    function subm(){
    if(flag==1){
    	$("#mainForm").submit();
    	}
    }
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>