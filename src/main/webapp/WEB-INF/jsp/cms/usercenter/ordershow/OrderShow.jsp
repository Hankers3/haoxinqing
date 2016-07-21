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
							<div class="col-sm-4 ">
								<ul class="breadcrumb">
									<li><i class="fa fa-home"></i><a href="#"><aebiz:showTitle titleId="ordershow.m.memberCenter"/></a></li>
									<li><a href="#"><aebiz:showTitle titleId="ordershow.m.orderMang"/></a></li> <li class="active"><aebiz:showTitle titleId="ordershow.m.publish"/></li>
								</ul>
							</div>
							<div class="col-sm-8 ">
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
										
									<c:forEach items="${list}" var="webModel" >	
										<div class="item-tr clearfix">
											<div class="item-detial">
												<dl>
													<dt class="assesment-item-image"><a href="#"><img src="${webModel.productPicUrl}"/></a></dt>
													<dd class="assesment-item-detial">
														<p class="assesment-item-name"><a href="#">${webModel.odm.productName}</a></p>
														<p>
														   <span class="assesment-item-price"><i class="RMB-Symbol">￥</i>${webModel.odm.finalPrice}</span> 
														   <b class="assesment-item-multiple">x</b><span class="assesment-item-num">${webModel.odm.buyNum}</span>
														</p>
														<c:if test="${!empty webModel.odm.specList}">
															<p class="assesment-item-color">
																<c:forEach items="${webModel.odm.specList}" var="specObj">
																	<span class="y_pdsta">${specObj.name}：${specObj.value}</span>
																</c:forEach>																
															</p>
														</c:if>
													</dd>
												</dl>
											</div>
											<div class="assesment-btn assesmented-btn">
												<!--
											  <p class="re-buy"><a class="btn btn-warning" href="评价页面.html">发表评价</a></p>
											  -->
											  
											  <c:if test="${!empty webModel.odm.orderShowModel}">
													<p class="see-assesmented"><span title="Expand/Collapse" class="see-assesment btn-link btn-toggle-expand1"><aebiz:showTitle titleId="ordershow.m.view"/><i class="fa fa-chevron-up fa-chevron-down"></i></span></p>
												</c:if>
												
												<c:if test="${empty webModel.odm.orderShowModel}">
													<p class="re-buy"><a class="btn btn-warning" href="${pageContext.request.contextPath }/usercenter/ordershow/toShare/${webModel.odm.uuid}"><aebiz:showTitle titleId="ordershow.m.go"/></a></p>
												</c:if>
												
											</div>
											
											<c:if test="${!empty webModel.odm.orderShowModel}">
												<div class="assesment-box">
												<div class="ascon-arrow-up"></div>
												<div class="assesment-content">
													<div class="ff row">
														<div class="col-mi-2 col-sm-2 text-right text-height">
															<aebiz:showTitle titleId="ordershow.m.title"/>:
														</div>
														<div class="col-mi-8  col-sm-8 basked-text">
															${webModel.odm.orderShowModel.showTitle}
														</div>
													</div>
													<div class="ff row">
														<div class="col-mi-2  col-sm-2 text-right text-height">
															<aebiz:showTitle titleId="ordershow.m.experience"/>:
														</div>
														<div class="col-mi-10 col-sm-10 basked-text">
															${webModel.odm.orderShowModel.showContent}
														</div>
													</div>
													
													<c:if test="${!empty webModel.odm.orderShowModel.ospList}" >
														<div class="ff row basked-images-more"><!--  basked-images-more star -->
															<div class="col-mi-2 col-sm-2 text-right">
																<aebiz:showTitle titleId="ordershowpic.m.pic"/>:
															</div>
															
															<div class="col-mi-10 col-sm-10 gallery">																																
																																
																<c:forEach 	items="${webModel.odm.orderShowModel.ospList}" var="orderShowPic" >
																	<div class="col-mi-2 col-sm-2 bask-img">
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
														</div>
													</c:if>
												
												</div>
											  </div>
											</c:if>
										</div>
										</c:forEach>
										
									</div> <!-- end assesment-container -->
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
	<script src="${pageContext.request.contextPath }/static/usercenter/js/jquery.colorbox-min.js"></script>
	<script src="${pageContext.request.contextPath }/static/usercenter/js/ajaxfileupload.js"></script>
	
	<script type="text/javascript">
		// widget toggle expand
		$(".assesment-container .assesment-btn .btn-toggle-expand1").click(function(){
			$(this).parent().parent().next('.assesment-box').slideToggle(300);
			$(this).find('i.fa-chevron-up').toggleClass('fa-chevron-down');
			$(this).parents(".item-tr").siblings(".item-tr").find(".assesment-box").slideUp();
	  });
	  
	  
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
	</script>
</body>
</html>
