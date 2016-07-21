<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<!-- colorbox -->
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/colorbox/colorbox.css">
<!-- colorbox -->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/colorbox/jquery.colorbox-min.js"></script>
<!-- 调用放大图插件的js -->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.colorbox.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>
					<aebiz:showTitle titleId="customerbankrel.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/>
				</h1>
			</div>
		</div>

		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="customerbankrel.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="customerbankrel.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span><aebiz:showTitle titleId="customerbankrel.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.view"/></span>
				</li>	
			</ul>
		</div>

		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding y_storesee">
			<form:form id="mainForm" action="" method="get" commandName="m" enctype="multipart/form-data"
					class='form-horizontal form-validate form-bordered'>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.customerUuid" /></label>
						<div class="col-sm-4">
							${m.customerNo }
							<c:if test="${empty m.customerNo}">&nbsp;</c:if>
						</div>
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.mobile" /></label>
						<div class="col-sm-4">
							${m.mobile }
							<c:if test="${empty m.mobile}">&nbsp;</c:if>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.openAccountBank" /></label>
						<div class="col-sm-4">
							${m.openAccountBank }
							<c:if test="${empty m.openAccountBank}">&nbsp;</c:if>
						</div>
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.openAccountName" /></label>
						<div class="col-sm-4">
							${m.openAccountName }
							<c:if test="${empty m.openAccountName}">&nbsp;</c:if>
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.cardValidateState" /></label>
						<div class="col-sm-4">
							<c:if test="${m.cardValidateState == '0'}"><aebiz:showTitle titleId="customerbankrel.m.nocheck" /></c:if>
							<c:if test="${m.cardValidateState == '1'}"><aebiz:showTitle titleId="customerbankrel.m.undercheck" /></c:if>
							<c:if test="${m.cardValidateState == '2'}"><aebiz:showTitle titleId="customerbankrel.m.checkSuccess" /></c:if>
							<c:if test="${m.cardValidateState == '3'}"><aebiz:showTitle titleId="customerbankrel.m.checkFail" /></c:if>
						</div>
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.vilidateMount" /></label>
						<div class="col-sm-4">
							${m.vilidateMount }
							<c:if test="${empty m.vilidateMount}">&nbsp;</c:if>
						</div>
					</div>
					
					<div class="form-group">
						<c:if test="${!empty m.idCard1}">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.idCard1" /></label>
							<div class="col-sm-4">
								<ul class="gallery">
									<li><a href="#"> <img src="${m.idCard1}"  alt=""></a>
										<div class="extras">
											<div class="extras-inner">
												<a href="${m.idCard1}" class='colorbox-image' target="_blank" rel="group-1" > <i class="fa fa-search"></i></a>
											</div>
										</div>
									</li>
								</ul>
							</div>
						</c:if>
						<c:if test="${!empty m.idCard2}">
							<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customerbankrel.m.idcard2" /></label>
							<div class="col-sm-4">
								<ul class="gallery">
									<li><a href="#"> <img src="${m.idCard2}"  alt=""></a>
										<div class="extras">
											<div class="extras-inner">
												<a href="${m.idCard2}" class='colorbox-image' target="_blank" rel="group-1" > <i class="fa fa-search"></i></a>
											</div>
										</div>
									</li>
								</ul>
							</div>
						</c:if>
					</div>


					<div class="form-actions col-sm-offset-2 col-sm-10">
						<button type="button" class="btn return">
							<aebiz:showTitle titleId="basebusiness.showmessage.return" />
						</button>
					</div>
			</form:form>

			</div>
		</div>
	</div>

<script>
    $(document).ready(function() {
		$(".return").click(function(){
			history.go(-1) ;
		});
    });
</script>
</body>


</html>
