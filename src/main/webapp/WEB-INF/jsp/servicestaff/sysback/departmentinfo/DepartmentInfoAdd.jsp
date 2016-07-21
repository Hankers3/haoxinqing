<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>

</head>

<body>
	
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>添加科室</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span>基础管理</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>科室信息</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>新增科室信息</span>
				</li>
			</ul>
		</div>		
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/departmentinfo/add" method="post" class='form-horizontal form-bordered form-validate'>						
							<div class="form-group" style="border-right: 1px solid #ddd;">
				    <div class="col-sm-12">
					   <i class="fa fa-user"></i>基本信息
				   </div>
		     	</div>
		     		
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="departmentinfo.m.departmentName"/></label>
						<div class="col-sm-10">
							<input type="text" name="departmentName" class="form-control" data-rule-required="true"  >
							<input type="hidden" name="nameHidden" value="true">
						</div>
						
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="departmentinfo.m.note"/></label>
						<div class="col-sm-10">
							<textarea  name="note" class="form-control" data-rule-required="true"  data-rule-maxlength="50" maxlength="50"  ></textarea>
						</div>
					</div>
					
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="button" class="btn submit btn-primary " value='<aebiz:showTitle titleId="basebusiness.showmessage.add"/>'>
						<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
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
    });
    
    	//检查科室名是否存在
		$("input[name='departmentName']").blur(function(){
			$("span[name='namespan']").remove();
			var departmentName=$("input[name='departmentName']").val();
			var nameReg=/^[\u4E00-\u9FA5\uF900-\uFA2D\w]{2,20}$/;
			if(departmentName.replace(/([\u4E00-\u9FA5\uf900-\ufa2d])/g,'aa').length>20 ||departmentName.replace(/([\u4E00-\u9FA5\uf900-\ufa2d])/g,'aa').length<4 ){
				$("input[name='nameHidden']").val("false");
				return ;
			}else{
				if(departmentName!=''&&nameReg.test(departmentName)){
					var url="${pageContext.servletContext.contextPath}/sysback/departmentinfo/checkName";
					$.post(url,{departmentName:departmentName,uuid:"",ranNum:Math.random()},function(data){
						if(data=="true"){
							$("input[name='departmentName']").after("<span name='namespan'><font color='red'>科室名已存在</font></span>");
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
    
    $(".submit").click(function(){
			 	var nameError=$("input[name='nameHidden']").val();
				if(nameError=="true"){
					$("#mainForm").submit();
				}
		});
    
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>