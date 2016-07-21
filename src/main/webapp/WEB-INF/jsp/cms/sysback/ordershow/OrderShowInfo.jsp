<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<!-- 图片放大效果 css-->
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/colorbox/colorbox.css">
<!-- 图片放大效果插件js-->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/colorbox/jquery.colorbox-min.js"></script>
<!-- 图片放大效果调用js-->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.colorbox.js"></script>
<!--设置浮动按钮-->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.buttonfloat.js"></script>
</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="ordershow.m.orderShowReview"/></h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="ordershow.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="ordershow.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="ordershow.m.orderShowReview"/></span>
				</li>
			</ul>
		</div>
				
		<div class="box box-color box-bordered blue y_orderbx">
			<div class="box-title">
				<h3><aebiz:showTitle titleId="ordershow.m.showDesc"/></h3>
				<div class="actions"><a class="btn btn-mini content-slideUp" href="#"><i class="fa fa-angle-down"></i></a></div>
			</div>
			<div class="box-content">
				<div class="shenhe-state">
				   <h2>
				   	<c:if test="${orderShowModel.state==1}">
				   		<aebiz:showTitle titleId="basebusiness.showmessage.uncheck"/>
				   	</c:if>
				   	<c:if test="${orderShowModel.state==2}">
				   		<aebiz:showTitle titleId="basebusiness.showmessage.checkPass"/>
				   	</c:if>
				   	<c:if test="${orderShowModel.state==3}">
				   		<aebiz:showTitle titleId="basebusiness.showmessage.checkUnPass"/>
				   	</c:if>
				   </h2>
				</div>
				<div class="table-responsive2 mb_10">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<th width="15%"><aebiz:showTitle titleId="ordershow.m.orderUuid"/></th>
								<td width="20%">${detailModel.orderMainUuid}</td>
								<th width="15%"><aebiz:showTitle titleId="ordershow.m.productNo"/></th>
								<td width="20%">${orderShowModel.productNo}</td>
								<th width="15"><aebiz:showTitle titleId="ordershow.m.productPrice"/></th>
								<td width="20%" class="font-red">￥${detailModel.finalPrice} x ${detailModel.buyNum}</td>
							</tr>
							<tr>
								<th><aebiz:showTitle titleId="ordershow.qmf.productName"/></th>
								<td colspan="5">${detailModel.productName}</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="row y_orderbox">
					<div class="col-sm-12">
						<div class="y_odxxbx">
							<h4><aebiz:showTitle titleId="ordershow.m.showDesc"/></h4>
							<dl>
								<dt><aebiz:showTitle titleId="ordershow.m.showTitle"/>：</dt>
								<dd>${orderShowModel.showTitle}</dd>	
							</dl>
							<dl>
								<dt><aebiz:showTitle titleId="ordershow.m.showContent"/>：</dt>
								<dd>${orderShowModel.showContent}</dd>	
							</dl>
							<dl>
								<dt><aebiz:showTitle titleId="ordershowpic.m.pic"/>：</dt>
								<dd>
									  <div class="row gallery">
									  	<c:forEach 	items="${orderShowModel.ospList}" var="orderShowPic" >
										    <div class="col-mi-4 col-sm-3 bask-img" rel="popover" data-trigger="hover" title="<aebiz:showTitle titleId="ordershowpic.m.picDesc"/>" data-placement="top" data-content="${orderShowPic.picDesc}">
										      <div class="basked-img-container">
														<img src="${orderShowPic.picUrl}"/>
														<div class="bask-img-mask">
															<div class="img-mask-inner">
																<a href="${orderShowPic.picUrl}" class='colorbox-image' rel="group-1">
																  <i class="fa fa-eye fa-2x"></i>
																</a>
															</div>
														</div>
													</div>
										     </div>
									     </c:forEach>
									  </div>				  
								</dd>	
							</dl>
						</div>
					</div>
					<div class="col-sm-12">
						<div class="y_odxxbx">
							<h4><aebiz:showTitle titleId="ordershow.m.showReview"/></h4>
					    <table class="table table-bordered">
								<tbody>
									<tr>
										<th width="15%"><aebiz:showTitle titleId="ordershow.m.state"/></th>
										<td width="20%">
											<c:if test="${orderShowModel.state==1}">
									   		<span class="text-danger"><aebiz:showTitle titleId="basebusiness.showmessage.uncheck"/></span>
									   	</c:if>
									   	<c:if test="${orderShowModel.state==2}">
									   		<span class="text-success"><aebiz:showTitle titleId="basebusiness.showmessage.checkPass"/></span>
									   	</c:if>
									   	<c:if test="${orderShowModel.state==3}">
									   		<span class="text-primary"><aebiz:showTitle titleId="basebusiness.showmessage.checkUnPass"/></span>
									   	</c:if>
										</td>
										<th width="15%"><aebiz:showTitle titleId="ordershow.m.reviewUser"/></th>
										<td width="20%">${orderShowModel.reviewUser}</td>
										<th width="15"><aebiz:showTitle titleId="ordershow.m.reviewTime"/></th>
										<td width="20%">${orderShowModel.reviewTime}</td>
									</tr>
									<tr>
										<th><aebiz:showTitle titleId="ordershow.m.reviewDesc"/></th>
										<td colspan="5"><textarea id="reviewDesc" name="reviewDesc" class="form-control">${orderShowModel.reviewDesc}</textarea></td>
									</tr>
								</tbody>
							</table>	
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="y_fixedbtn">
			<button id="checkPass" type="submit" class="btn btn-primary btn-large a_size_1"><aebiz:showTitle titleId="basebusiness.showmessage.checkPass"/></button>
			<button id="checkUnPass" type="submit" class="btn btn-default  btn-large a_size_1"><aebiz:showTitle titleId="basebusiness.showmessage.checkUnPass"/></button>
			<button type="button" class="btn cancel btn-large a_size_1"><aebiz:showTitle titleId="basebusiness.showmessage.return"/></button>
		</div>	
	</div>
</body>

</html>


<script>
    $(document).ready(function() {
    	
    	$("#checkPass").click(function(){
    			var reviewDesc = $("#reviewDesc").val();
    			$.getJSON(
			    	"${pageContext.servletContext.contextPath}/sysback/ordershow/review",
			    	{"uuid":"${orderShowModel.uuid}","reviewType":"2","reviewDesc":reviewDesc,ranNum:Math.random()},	
				    function(data) {    
				       if(data=="success") {
				       		alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;
				       		location.href="${pageContext.servletContext.contextPath}/sysback/ordershow/toList";	
				       }else if(data=="fail"){
				       		alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
				       }else{
				       		alert(data);	
				       }
				    }
				);
    	});
    	
    	$("#checkUnPass").click(function(){
    			var reviewDesc = $("#reviewDesc").val();
    			if(reviewDesc==""){
    				alert("<aebiz:showTitle titleId="ordershow.m.pleaseInputReviewDesc"/>");
    				return;	
    			}
    			if(reviewDesc!="" && reviewDesc.length>250){
    				alert("<aebiz:showTitle titleId="ordershow.m.reviewDescOverLength"/>");
    				reutrn;
    			}
    			$.getJSON(
			    	"${pageContext.servletContext.contextPath}/sysback/ordershow/review",
			    	{"uuid":"${orderShowModel.uuid}","reviewType":"3","reviewDesc":reviewDesc,ranNum:Math.random()},	
				    function(data) {    
				       if(data=="success") {
				       		alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;   	
				       		location.href="${pageContext.servletContext.contextPath}/sysback/ordershow/toList";
				       }else if(data=="fail"){
				       		alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
				       }else{
				       		alert(data);	
				       }
				    }
				);
    	});
    	
			$(".cancel").click(function(){
				history.go(-1) ;
			});	    	
    });
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>