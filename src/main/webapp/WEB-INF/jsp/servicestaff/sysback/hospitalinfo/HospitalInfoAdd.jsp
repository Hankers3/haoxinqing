<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script> 
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datatable/jquery.dataTables.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.passwdcheck.js"></script>

</head>
<body>
	
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="hospitalinfo.moduleName_CN"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="hospitalinfo.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="hospitalinfo.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="hospitalinfo.moduleName_CN"/></span>
				</li>
			</ul>
		</div>		
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/hospitalinfo/add" method="post" class='form-horizontal form-bordered form-validate'>						
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="hospitalinfo.m.hospitalName"/></label>
						<div class="col-sm-10">
							<input type="text" name="hospitalName" class="form-control" data-rule-required="true" >
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="hospitalinfo.m.hospitalClassify"/></label>
						<div class="col-sm-10">
							<div class="col-sm-3"> 
							<select name="hospitalLevel" class='select2-me form-control' data-rule-required="true">		
								 <option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</option> 
								 <option value="1">一级综合</option> 
								 <option value="2">二级综合</option> 
								 <option value="3">三级综合</option> 
								 <option value="1a">一级精神专科</option> 
								 <option value="2a">二级精神专科</option> 
								 <option value="3a">三级精神专科</option> 
								 <option value="z">综合</option> 
								 <option value="js">精神专科</option> 
							</select>	
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2  "><span style="color:red">*</span>&nbsp; 地区</label>	
						<div class="col-sm-10">
						<aebiz:area checkProvince="${m.province}" checkCity="${m.city}" checkRegion="${m.region}"></aebiz:area>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">详细地址</label>
						<div class="col-sm-10">
							<input type="text"   name="address" class="form-control" data-rule-required="true"  data-rule-maxlength="100" maxlength='100'>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">医院简介</label>
						<div class="col-sm-10">
							<textarea  name="note" class="form-control" data-rule-required="true"  data-rule-maxlength="2000" maxlength='2000'></textarea>
						</div>
					</div>
					
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.add"/>'>
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
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>