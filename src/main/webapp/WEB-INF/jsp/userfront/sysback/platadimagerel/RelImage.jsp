<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>

</head>

<body>
	
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="platadimagerel.moduleName_CN"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="platadimagerel.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="platadimagerel.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="platadimagerel.moduleName_CN"/></span>
				</li>
			</ul>
		</div>
		<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/platadimagerel/saveRel" method="post"  commandName="m" class='form-horizontal form-bordered form-validate'>
			<input type="hidden" name="adUuid" value="${adUuid}"/>
			<ul class="y_pic_list">
				<c:forEach items="${adImageRelModels}" var="a">
					<li class="image">
					<div class="w_has_img">
						<img src="${a.imageUrl }"/>
						<div class="cz" >
							<i class="glyphicon-left_arrow"></i>
							<i class="glyphicon-right_arrow"></i>
							<i class="glyphicon-remove_2"></i>	
						</div>
						<div class="bg"></div>
				    </div>
				    <div class="y_imgms">
				    	<input type="hidden" value="${a.imageUuid }" id="uuid" name="uuids" />
				    	<input type="text" id="url" name="${a.imageUuid }urls" value="${a.url }" data-rule-maxlength="500" data-rule-url="true"   class="form-control" placeholder="<aebiz:showTitle titleId="platadimagerel.m.url"/>">
				    	<span class="font-999"><aebiz:showTitle titleId="platadimagerel.m.urlRule"/></span>
				   		<input type="text"  name="imageNotes" value="${a.imageNote }" data-rule-maxlength="500"    class="form-control" placeholder="图片描述">
				   		<span class="font-999">字数小于500字</span>

				   	</div>
				</li>
				</c:forEach>
				<!-- <li>
					<div class="w_has_img">
						<img src="../img/demo/product.jpg" alt="图片名称" title="图片名称"/>
						<div class="cz">
							<i class="glyphicon-left_arrow"></i>
							<i class="glyphicon-right_arrow"></i>
							<i class="glyphicon-remove_2" ></i>	
						</div>
						<div class="bg"></div>
				    </div>
				    <div class="y_imgms">
				    	<input type="hidden" value="" id="uuid" name="uuid" />
				    	链接地址:<input type="text" id="url" name="url" data-rule-maxlength="100"  data-rule-required="true" class="form-control"><span class="font-999">链接地址以http://或https://开头</span>
				   	</div>
				</li> -->
			</ul>
			<div class="y_picbtn">
						<input type="button"   class="btn submit btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>

			</div>
		</form>
		<div id="image">
			<%@ include file="/WEB-INF/jsp/userfront/sysback/platadimagerel/PlattImageLibraryList.jsp" %>
		</div>	
		
	</div>
	
<script>
	$("#mainForm").on('click','.glyphicon-remove_2',function(){
		$(this).parents(".image").remove();
	})
	
	//图片向后移
	$("#mainForm").on('click','.glyphicon-right_arrow',function(){
		var now = $(this).parents(".image");
		var next = $(this).parents(".image").next();
		next.insertBefore(now);

	})
	//图片向前移
	$("#mainForm").on('click','.glyphicon-left_arrow',function(){
		var now = $(this).parents(".image");
		var before = $(this).parents(".image").prev();
		before.insertAfter(now);

	})
	$(".submit").click(function(){
		$("#mainForm").submit();
	});
</script>
</body>

</html>



<aebiz:showErrorMsg></aebiz:showErrorMsg>