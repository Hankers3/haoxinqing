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
										<!-- 会员评论 star-->
										<div class="item-tr clearfix">
											<div class="item-detial">
												<dl>
													<dt class="assesment-item-image"><a href="#"><img src="${m.productPicUrl}"/></a></dt>
													<dd class="assesment-item-detial">
														<p class="assesment-item-name"><a href="#">${m.odm.productName}</a></p>
														<p>
														   <span class="assesment-item-price"><i class="RMB-Symbol">￥</i>${m.odm.finalPrice}</span> 
														   <b class="assesment-item-multiple">x</b><span class="assesment-item-num">${m.odm.buyNum}</span>
														</p>
														<p class="assesment-item-color">
															<c:forEach items="${m.odm.specList}" var="specObj">
																<span class="y_pdsta">${specObj.name}：${specObj.value}</span>
															</c:forEach>	
														</p>
													</dd>
												</dl>
											</div>
											<div class="assesment-btn assesmented-btn">
											  <p class="re-buy"><a href="#" class="btn btn-warning"><aebiz:showTitle titleId="productappraise.m.buyAgain"/></a></p>
												<p class="see-assesmented"><span class="see-assesment btn-link btn-toggle-expand1" title="Expand/Collapse"><aebiz:showTitle titleId="productappraise.m.viewApp"/><i class="fa fa-chevron-up fa-chevron-down"></i></span>
												<c:if test="${!empty m.odm.orderShowModel}">
													<a href="${pageContext.request.contextPath}/usercenter/ordershow/toShareOrder/${m.odm.orderMainUuid}" class="see-assesment btn-link  btn-ms"><aebiz:showTitle titleId="productappraise.m.viewOrderShow"/></a></p>
												</c:if>
												<c:if test="${empty m.odm.orderShowModel}">
													<a href="${pageContext.request.contextPath}/usercenter/ordershow/toShare/${m.odm.uuid}" class="see-assesment btn-link  btn-ms"><aebiz:showTitle titleId="productappraise.m.noOrderShow"/></a></p>
												</c:if>
											</div>
											<c:choose>
												<c:when  test="${status.index==0}">
													<div class="assesment-box assesbshow"> 
												</c:when >
												<c:otherwise>
													<div class="assesment-box"> 
												</c:otherwise>
											</c:choose>
												<div class="ascon-arrow-up"></div>
												<div class="assesment-content clearfix">
													<div class="assesment-innerbox">
														<div class="assesment-word">
															<div class="custom-me">
																<div class="title-photo">
																	<a href="#"><img src="${m.customerImage}"/></a>
																	<a href="#" class="custom-name">${m.pam.customerName}</a>
																</div>
																<div class="assesment-word">
																	<div class="grad-result">
																		<span class="result-text"><aebiz:showTitle titleId="productappraise.m.appScore"/>:</span>
																		<ul class="rating-level">
																			<li><a <c:if test="${m.pam.appScore==1}">class="actived1"</c:if> class="one-star" star:value="1" href="#">1</a></li>
																			<li><a <c:if test="${m.pam.appScore==2}">class="actived2"</c:if> class="two-stars" star:value="2" href="#">2</a></li>
																			<li><a <c:if test="${m.pam.appScore==3}">class="actived3"</c:if> class="three-stars" star:value="3" href="#">3</a></li>
																			<li><a <c:if test="${m.pam.appScore==4}">class="actived4"</c:if> class="four-stars" star:value="4" href="#">4</a></li>
																			<li><a <c:if test="${m.pam.appScore==5}">class="actived5"</c:if> class="five-stars" star:value="5" href="#">5</a></li>
																		</ul>
																		<span class="result" id="stars3-tips"></span>
																		<input type="hidden" id="stars3-input" name="b" value="" size="2"/> 
																	</div>	
																	<div class="label-result">
																	   <span class="result-text"><aebiz:showTitle titleId="productappraise.m.appTag"/>:</span>
																	   <div class="label-container btn-group">
																		   <c:forEach items="${m.pam.tags}" var="tag" >	
																	   	 		<button class="btn btn-success btn-xs" type="button">${tag}</button>
																	   	 </c:forEach>
																	   </div>
																	</div>	
																	<div class="text-reslut">
																		<p>${m.pam.appContent}</p>
																		<p class="write-time">${m.pam.appTime}</p>
																	</div>
																</div>
															</div>	<!-- end custom-me -->
														</div>
													</div>
												</div><!--  end assesment-content -->
											</div>											
										</div> <!-- end item-tr -->
										<!-- 会员评论 end-->
										</c:forEach>
										
									   <!-- 页码 star -->
											<aebiz:cmsPage listPath="usercenter/productappraise/toMyAppraise"/>
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
