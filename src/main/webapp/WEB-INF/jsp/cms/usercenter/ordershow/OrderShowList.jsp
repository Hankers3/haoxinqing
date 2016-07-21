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
									<li><i class="fa fa-home"></i><a href="#">会员中心</a></li>
									<li><a href="#">订单管理</a></li> <li class="active">发表晒单</li>
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
									<aebiz:showTitle titleId="productappraise.m.mayApp"/>
								</div>
								<div class="widget">
									<div class="widget-content assesment-container">
										
										<c:forEach items="${list}" var="m" varStatus="status">

										<div class="item-tr"><!-- item-tr star -->
											<div class="item-detial">
												<dl>
													<dt class="assesment-item-image"><a href="#"><img src="img/aebiz/y_ad78.jpg"/></a></dt>
													<dd class="assesment-item-detial">
														<p class="assesment-item-name"><a href="#">${webModel.odm.productName} </a></p>
														<p>
														   <span class="assesment-item-price"><i class="RMB-Symbol">￥</i>${webModel.odm.finalPrice}</span> 
														   <b class="assesment-item-multiple">x</b><span class="assesment-item-num">${webModel.odm.buyNum}</span>
														</p>
														<c:if test="${!empty webModel.odm.specList}">
															<c:forEach items="${webModel.odm.specList}" var="specObj">
																<p class="assesment-item-color">${specObj.name}：${specObj.value}</p>
															</c:forEach>																
														</c:if>
													</dd>
												</dl>
											</div>
											<div class="assesment-btn assesmented-btn">
											    <p class="re-buy"><a href="${pageContext.request.contextPath}/front/product/toProduct/${webModel.odm.productUuid}" class="btn btn-warning">再次购买</a></p>
												<p class="see-assesmented">
												<a href="${pageContext.request.contextPath}/usercenter/productappraise/toMyAppraise/1/10" class="see-assesment btn-link  btn-ms">查看评价</a></p>
											</div> 
											<div class="assesment-content" style="display:block;">
												<div class="ff row">
													<div class="col-mi-2 col-sm-2 text-right text-height">
														标题:
													</div>
													<div class="col-mi-10 col-sm-8 basked-text">
														${webModel.odm.orderShowModel.showTitle}
													</div>
												</div>
												<div class="ff row">
													<div class="col-mi-2 col-sm-2 text-right text-height">
														心得:
													</div>
													<div class="col-mi-10 col-sm-10 basked-text">
													    ${webModel.odm.orderShowModel.showContent}
													</div>
												</div>
												<c:if test="${!empty webModel.odm.orderShowModel.ospList}" >
													<div class="ff row basked-images-more"><!--  basked-images-more star -->
														<div class="col-mi-2 col-sm-2 text-right">
															晒单图片:
														</div>
														<div class="col-mi-10 col-sm-10 gallery">
															<c:forEach 	items="${webModel.odm.orderShowModel.ospList}" var="orderShowPic" >
															  <div class="col-mi-2 col-sm-2 bask-img" rel="popover" data-trigger="hover" title="图片描述" data-placement="bottom" data-content="图片描述内容图片描述内容图片描述内容图片描述内容图片描述内容">
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
														  <div class="col-mi-2 col-sm-2 see-more">
																<p>共<span>${webModel.odm.orderShowModel.ospList.length}</span>张图片</p>
															<span class="btns-see"><button class="btn btn-primary btn-xs btn-see-more">查看更多</button><button class=" btn btn-primary btn-xs btn-close-more none">返回</button>
															</div>
														</div>
													</div><!--  basked-images-more end -->
												</div>
											</c:if>
										</div><!-- item-tr end -->
										</c:forEach>
										
									   <!-- 页码 star -->
											<aebiz:cmsPage listPath="usercenter/ordershow/toMyOrderShow"/>
										<!-- 页码 end -->
									</div>
								</div>
							</div><!-- /main-content -->
							
						</div><!-- /main -->
					</div><!-- /content-wrapper -->
				</div><!-- /row -->
			</div><!-- /container -->
		</div>
		<!-- END BOTTOM: LEFT NAV AND RIGHT MAIN CONTENT -->
		<div class="push-sticky-footer"></div>
	</div><!-- /wrapper -->

	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImportJs.jsp"%>
	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterBottom.jsp"%>
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
