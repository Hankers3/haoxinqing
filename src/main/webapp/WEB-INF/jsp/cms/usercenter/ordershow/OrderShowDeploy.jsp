<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
	<!--[if IE 9 ]><html class="ie ie9" lang="en" class="no-js"> <![endif]-->
	<!--[if !(IE)]><!--><html lang="en" class="no-js"> <!--<![endif]-->
	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImport.jsp"%>		
		
	<!-- colorbox图片弹出框 -->
	<link  href="${pageContext.request.contextPath}/static/usercenter/css/colorbox.css" rel="stylesheet" type="text/css"/>		
</head>

<body>
	<!-- WRAPPER -->
	<div class="wrapper">
		<!-- TOP BAR 头部导航 -->
		<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterHead.jsp"%>
		<!-- /top -->
	
		<!-- BOTTOM: LEFT NAV AND RIGHT MAIN CONTENT -->
		<div class="bottom">
			<div class="container">
				<div class="row">
					<!-- left sidebar 左侧导航 -->
					<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterLeft.jsp"%>
					<!-- end left sidebar -->

					<!-- content-wrapper 右侧主体 -->
					<div class="col-xs-10 content-wrapper">
						<div class="row">
							<div class="col-lg-4 ">
								<ul class="breadcrumb">
									<li><i class="fa fa-home"></i><a href="#"><aebiz:showTitle titleId="ordershow.m.memberCenter"/></a></li>
									<li><a href="#"><aebiz:showTitle titleId="ordershow.m.orderMang"/></a></li> <li class="active"><aebiz:showTitle titleId="ordershow.m.publish"/></li>
								</ul>
							</div>
							<div class="col-lg-8 ">
								<div class="top-content">
									<ul class="list-inline quick-access">
										<li>
											<a href="charts-statistics-interactive.html">
												<span class="quick-access-item bg-color-green">
													<i class="fa fa-bar-chart-o"></i>
													<h5>CHARTS</h5><em>basic, interactive, real-time</em>
												</span>
											</a>
										</li>
										<li>
											<a href="page-inbox.html">
												<span class="quick-access-item bg-color-blue">
													<i class="fa fa-envelope"></i>
													<h5>INBOX</h5><em>inbox with gmail style</em>
												</span>
											</a>
										</li>
										<li>
											<a href="tables-dynamic-table.html">
												<span class="quick-access-item bg-color-orange">
													<i class="fa fa-table"></i>
													<h5>DYNAMIC TABLE</h5><em>tons of features and interactivity</em>
												</span>
											</a>
										</li>
									</ul>
								</div>
							</div>
						</div>
						
						<!-- main -->
						<div class="content">
							<div class="main-content">
								<div class="assesment-title">
									<aebiz:showTitle titleId="ordershow.m.publish"/>
								</div>
								<div class="widget">
									<div class="widget-header">
										<ul class="order-infor">
											<li class="order-time">${omm.orderTime} <span class="span-interval">|</span></li>
											<li class="order-number"><aebiz:showTitle titleId="ordershow.m.orderNo"/>：${omm.orderId}  <span class="span-interval">|</span></li>
											<li class="store-name"><a href="#">${omm.storeName}</a></li>
											<li class="contact-service"><i class="fa fa-comments-o"></i></li>
										</ul>
									</div>
									<div class="widget-content assesment-container">
										
											<div class="item-tr clearfix">
												<div class="item-detial">
													<dl>
														<dt class="assesment-item-image"><a href="#"><img src="${oswm.productPicUrl}"/></a></dt>
														<dd class="assesment-item-detial">
															<p class="assesment-item-name"><a href="#">${detailModel.productName}</a></p>
															<p>
															   <span class="assesment-item-price"><i class="RMB-Symbol">￥</i>${detailModel.finalPrice}</span> 
															   <b class="assesment-item-multiple">x</b><span class="assesment-item-num">${detailModel.buyNum}</span>
															</p>
															<p class="assesment-item-color">
																<c:forEach items="${detailModel.specList}" var="specObj">
																		<span class="y_pdsta">${specObj.name}：${specObj.value}</span>
																</c:forEach>		
															</p>															
														</dd>
													</dl>
												</div>
												
											<form id="mainForm" action="${pageContext.servletContext.contextPath}/usercenter/ordershow/saveShare" method="post" class='form-horizontal form-bordered form-validate'>																			
												<input type="hidden" name="orderDetailUuid" value="${detailModel.uuid}"/>
												<input type="hidden" id="nowCount" name="nowCount" value="0"/>
												<div class="assesment-box assesbshow"> 													
													<div class="assesment-content">
														<div class="ff ff-bask row">
															<div class="col-mi-2 col-sm-2 text-right text-height">
																<aebiz:showTitle titleId="ordershow.m.title"/>:
															</div>
															<div class="col-mi-8 col-sm-8">
																<input type="text" name="showTitle" placeholder="<aebiz:showTitle titleId="ordershow.m.message"/> " class="form-control ff-bask-input" data-rule-required="true" data-rule-minlength="5" data-rule-maxlength="20">
															</div>
														</div>
														<div class="ff row">
															<div class="col-mi-2 col-sm-2 text-right text-height">
																<aebiz:showTitle titleId="ordershow.m.experience"/>:
															</div>
															<div class="col-mi-10 col-sm-10">
															   <div class="assesment-text">
																	<textarea name="showContent" cols="110" rows="2" class="form-control" id="textarea" data-rule-required="true" data-rule-minlength="10" data-rule-maxlength="100"></textarea>
																	<p class="text-right"><span class="text-muted">&nbsp;&nbsp;</span></p>
															   </div>	
															</div>
														</div>
														<div class="ff row">
															<div class="col-mi-2 col-sm-2 text-right">
																<aebiz:showTitle titleId="ordershow.m.uploadpic"/>:
															</div>
															<div class="col-mi-2 col-sm-1 file-image">
																	<p class="input-file">
																		<aebiz:showTitle titleId="ordershow.m.browse"/>
																		<input type="file" id="uploadFiles" class="files" name="myfiles" onchange="uploadImage()"/>
																	</p>
																</div>
															<div class="col-mi-8 col-sm-7 file-image-notice">
																<aebiz:showTitle titleId="ordershow.m.hint"/> 
															</div>
														</div>
														
														<div id="showPics">
															
														</div>
														
														<!-- 发表晒单按钮 star -->
														<div class="ff row issue-bask">
															<div class="col-mi-2 col-sm-2 ">
															 <!--  不能去掉 -->
															</div>
															<div class="col-mi-4 col-sm-4">
																<button type="submit" class="btn btn-warning issue-btn"><aebiz:showTitle titleId="ordershow.m.release"/></button>
															</div>
														</div>
														<!-- 发表晒单按钮 end -->
												  </div>
												</div>
											</form>
																							
										</div><!-- item-tr end -->										
								</div><!-- end widget -->
							</div><!-- /main-content -->
						</div><!-- /main -->
					</div><!-- /content-wrapper -->
				</div><!-- /row -->
			</div><!-- /container -->
		</div>
		<!-- END BOTTOM: LEFT NAV AND RIGHT MAIN CONTENT -->
		<div class="push-sticky-footer"></div>
	</div><!-- /wrapper -->

	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterBottom.jsp"%>
	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImportJs.jsp"%>
	<script src="${pageContext.request.contextPath}/static/usercenter/js/jquery.colorbox-min.js"></script>
	<script src="${pageContext.request.contextPath}/static/usercenter/js/ajaxfileupload.js"></script>
	<script src="${pageContext.request.contextPath}/static/usercenter/js/validation/jquery.validate.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/usercenter/js/validation/aebiz.validate.js"></script>

	<script type="text/javascript">
	  //调用放大图hover的效果代码!
	   $(".basked-img-container").hover(function(){
				$(this).find(".bask-img-mask").fadeIn();	
		 },function(){
			  $(this).find(".bask-img-mask").fadeOut();	
		 });
	  
	  //调用放大图插件
	  if ($(".colorbox-image").length > 0) {
		  $(".colorbox-image").colorbox({  
			maxWidth: "90%",
			maxHeight: "90%",
			rel: $(this).attr("rel")
		  });
		}	
		
	  function uploadImage() {
	  	 var uploadFilesCount = parseInt($("#nowCount").val());	 
   		 $.ajaxFileUpload({
       		url: '${pageContext.request.contextPath}/usercenter/ordershow/uploadFile/'+uploadFilesCount, //用于文件上传的服务器端请求地址
        	secureuri: false, //是否需要安全协议，一般设置为false
        	fileElementId: 'uploadFiles', //文件上传域的ID
        	dataType: 'text', //返回值类型 一般设置为json
        	success: function (data, status){
        		$("#showPics").append(data) ;
        		
        		var newCount = uploadFilesCount + 1;
        		
        		$("#nowCount").val(newCount);
        },
        error: function (data, status, e){
        	//服务器响应失败处理函数            
        }
       })                  
       
       return false;
	  }
	   	
	  function removeShow(nowCount) {
	  	if($("#show"+nowCount) != undefined) {
	   		$("#show"+nowCount).remove() ;	   				   				   				   				   				   				   				   			
	   	}
	  }
	  
	  $(document).ready(function() {
	  	$("#mainForm").on('click','input[name="frontCover"]',function(){
	  		var id = $(this).attr("id");
	  		$("input[name='"+id+"']").val("1");
	  		$("input[name='"+id+"']").parents(".bask-img-container").siblings(".bask-img-container").find(".frontCover-mark").val("0");
	  	})
	  });
	  
	</script>
</body>
</html>
