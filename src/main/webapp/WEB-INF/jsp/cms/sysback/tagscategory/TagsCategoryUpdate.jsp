<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp"%>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/chosen/chosen.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/chosen/chosen.jquery.min.js"></script>
</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>
					<aebiz:showTitle titleId="tagscategory.moduleName_CN" />
					<aebiz:showTitle titleId="basebusiness.showmessage.edit" />
				</h1>
			</div>
		</div>

		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="tagscategory.menuOne" /></span>
					<i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="tagscategory.menuTwo" /></span>
					<i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="tagscategory.moduleName_CN" /><aebiz:showTitle titleId="basebusiness.showmessage.edit" /></span></li>
			</ul>
		</div>

		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/tagscategory/update" method="post" commandName="m" class='form-horizontal form-validate form-bordered'>
					<form:hidden path="uuid" />

					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="tagscategory.m.categoryName" /></label>
						<div class="col-sm-10">
							<form:input path="categoryName" class='form-control' data-rule-required="true" data-rule-maxlength="20"/>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="tagscategory.m.categoryType" /></label>
						<div class="col-sm-10">
							<form:select path="categoryType" class='chosen-select inline-form'>
								<c:forEach items="${tagsList}" var="item">
									<form:option value="${item.value }">${item.name }</form:option>
								</c:forEach>
							</form:select>	
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="tagscategory.m.note" /></label>
						<div class="col-sm-10">
							<form:textarea path="note" class='form-control'  data-rule-required="true" data-rule-maxlength="200"/>
						</div>
					</div>

					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>
						<button type="button" class="btn cancel">
							<aebiz:showTitle titleId="basebusiness.showmessage.cancel" />
						</button>
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

	$(function(){
		// Chosen (chosen)
    if ($('.chosen-select').length > 0) {  
       $('.chosen-select').each(function() {
         var $el = $(this);
         var search = ($el.attr("data-nosearch") === "true") ? true : false,  //判断元素是否设置了data-nosearch="true"属性
           opt = {};
         if (search) opt.disable_search_threshold = 9999999;
         $el.chosen(opt);  //调用js
       });
    }	
	})	
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>