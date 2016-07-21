<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/datepicker/datepicker.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.datepicker.js"></script>
</head>

<body>
	
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="packagemanagement.moduleName_CN"/></h1>
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
					<span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="packagemanagement.moduleName_CN"/></span>
				</li>
			</ul>
		</div>		
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/packagemanagement/add" method="post" class='form-horizontal form-bordered form-validate'>						
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="packagemanagement.m.packageName"/></label>
						<div class="col-sm-10">
						<input type="text" name="packageName" class="form-control" data-rule-required="true">
						<input type="hidden" name="packageNameHidden" value="true">
						<span name='packageNamespan' ></span>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="packagemanagement.m.plusTimes" /></label>
						<div class="col-sm-10">
						<input type="text" name="plusTimes" class="form-control" data-rule-required="true">
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="packagemanagement.m.phoneTimes"/></label>
						<div class="col-sm-10">
						<input type="text" name="phoneTimes" class="form-control" data-rule-required="true">
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="packagemanagement.m.length"/></label>
						<div class="col-sm-10">
							<select name="duration" data-rule-required="true" >
								<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</option>
								<option value="1">15分钟</option>
								<option value="2">30分钟</option>
							</select>
						</div>
					</div>
				 <div class="form-group">
						<label for="textfield" class="control-label col-sm-2">周期</label>
						<div class="col-sm-10">
							<select name="period" data-rule-required="true" >
								<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</option>
								<option value="0">一个月</option>
								<option value="1">一季度</option>
								<option value="2">一年</option>
							</select>
						</div>
					</div>
					
					<!--
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2" ><aebiz:showTitle titleId="packagemanagement.m.startTime"/></label>
						<div class="col-sm-10">
						<input type="text" name="startTime" class='form-control datepick' data-rule-required="true"> 
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="packagemanagement.m.endTime"/></label>
						<div class="col-sm-10">
						<input type="text" name="endTime" class='form-control datepick'  onchange="checkdate();" data-rule-required="true">   
						</div>
					</div>-->
					
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="packagemanagement.m.money"/></label>
						<div class="col-sm-10">
						<input type="text" name="money" class="form-control" data-rule-required="true">
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="packagemanagement.m.note"/></label>
						<div class="col-sm-10">
						<textarea rows="5" name="note" class="form-control" data-rule-maxlength="40" ></textarea>
						</div>
					</div>
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="button" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.add"/>' onclick="subm();">
						<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
					</div>						
				</form>
			</div>
		</div>
	</div>
</body>

</html>


<script>
		var flag=0; //flag=0时不能提交 =1时可以提交
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
								$("span[name='packageNamespan']").html("<font color='red'>套餐名已存在</font>"); 	
								$("input[name='packageNameHidden']").val("false");
								flag = 0;
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