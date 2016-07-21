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
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="customerCategory.moduleName_CN"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="customerCategory.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="customerCategory.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="customerCategory.moduleName_CN"/></span>
				</li>
			</ul>
		</div>		
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/customerCategory/add" method="post" class='form-horizontal form-bordered form-validate'>						
					<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerCategory.m.categoryName"/></label>
	<div class="col-sm-10">
		<input type="text" name="categoryName" class="form-control" data-rule-required="true" data-rule-mobilezh="true" data-rule-maxlength="11">
		<input type="hidden" name="categoryNameHidden" value="true">
		<span name='categoryNamespan'></span>
	</div>
</div>
<div class="form-group">
	<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerCategory.m.note"/></label>
	<div class="col-sm-10">
	<textarea rows="5" name="note" class="form-control" data-rule-maxlength="40" maxlength="40"></textarea>
	</div>
</div>
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="button" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.add"/>' onclick="add();">
						<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
					</div>						
				</form>
			</div>
		</div>
	</div>
</body>

</html>


<script>
	 var flag = 1; // flag为1时，可以提交， 为0不能提交
    $(document).ready(function() {
		$(".cancel").click(function(){
			history.go(-1) ;
		});
    });
    
       $(document).ready(function(){
     	
     	//验证医生分类名称是否存在
			$("input[name='categoryName']").blur(function(){
					var categoryName=$("input[name='categoryName']").val();
					if(categoryName!=''){
						var url="${pageContext.servletContext.contextPath}/sysback/customerCategory/checkName";
						$.get(url,{"categoryName":categoryName,ranNum:Math.random()},function(data){
							if(data=="true"){
								$("span[name='categoryNamespan']").html("<font color='red'>分类名已存在</font>"); 	
								$("input[name='categoryNameHidden']").val("false");
								flag = 0;
							}else{
										$("span[name='categoryNamespan']").html(""); 	 
								flag = 1;
							}
						});
					}
				});
     	});
     	
     	
     	function add(){
    	if(flag==1){
    		$("#mainForm").submit();
    		}
			
    	}
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>