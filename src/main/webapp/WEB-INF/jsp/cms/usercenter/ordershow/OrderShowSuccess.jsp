<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
	<!--[if IE 9 ]><html class="ie ie9" lang="en" class="no-js"> <![endif]-->
	<!--[if !(IE)]><!--><html lang="en" class="no-js"> <!--<![endif]-->
	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImport.jsp"%>			
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
							<div class="col-sm-4">
								<ul class="breadcrumb">
									<li><i class="fa fa-home"></i><a href="#"><aebiz:showTitle titleId="ordershow.m.memberCenter"/></a></li>
									<li class="active"><a href="#"><aebiz:showTitle titleId="ordershow.m.orderMang"/></a></li>
									<li class="active"><aebiz:showTitle titleId="ordershow.m.confirmReceipt"/></li>
								</ul>
							</div>
							<div class="col-sm-8">
								<div class="top-content">
									<ul class="list-inline quick-access">
										<li>
											<a href="#">
												<span class="quick-access-item bg-color-green">
													<i class="fa fa-bar-chart-o"></i>
													<h5>订单</h5><em><aebiz:showTitle titleId="ordershow.m.viewOrdershowList"/></em>
												</span>
											</a>
										</li>
										<li>
											<a href="#">
												<span class="quick-access-item bg-color-blue">
													<i class="fa fa-envelope"></i>
													<h5><aebiz:showTitle titleId="ordershow.m.myRating"/></h5><em><aebiz:showTitle titleId="ordershow.m.viewMyRating"/></em>
												</span>
											</a>
										</li>
										<li>
											<a href="#">
												<span class="quick-access-item bg-color-orange">
													<i class="fa fa-table"></i>
													<h5><aebiz:showTitle titleId="ordershow.m.backDocument"/></h5><em><aebiz:showTitle titleId="ordershow.m.viewMyBackDocument"/></em>
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
								<div class="main-header">
									<h2><aebiz:showTitle titleId="basebusiness.showmessage.opeOkTitle"/></h2>
									<em>The success of the transaction</em>
								</div>
								<!--交易成功提示-->
								<div class="y_ordsuccess alert alert-success">
									<span class="y_odsicon text-success"><i class="fa fa-check-circle"></i></span>
									<div class="y_odsuccms">
										<h2 class="text-success"><aebiz:showTitle titleId="ordershow.m.BackDocumentMessage"/></h2>
										<p>
											您可以 <a class="btn btn-primary" href="${pageContext.servletContext.contextPath}/usercenter/ordershow/toShareOrder/${odm.orderMainUuid}" title="<aebiz:showTitle titleId="basebusiness.showmessage.return"/>"><aebiz:showTitle titleId="basebusiness.showmessage.return"/></a> <aebiz:showTitle titleId="ordershow.m.myBackDocumentList"/>
										</p>
									</div>
									<p class="y_ordsuclst">
										<aebiz:showTitle titleId="ordershow.m.scores"/><a href="#"><aebiz:showTitle titleId="ordershow.m.evenMore"/>>></a>
									</p>
								</div>
								<!--广告位-->
								<div class="ad100 mb_20"><img src="img/aebiz/11.jpg"></div>
								<!--同类推荐商品列表-->
								<div class="y_tlcordlist">
									<h4><aebiz:showTitle titleId="ordershow.m.similar"/></h4>
									<ul>
										<li>
											<div class="y_pcbox">
												<div class="y_tlpic"><a href="#"><img src="img/aebiz/y_tlpic.jpg"></a><a class="y_picbtn" href="#"><aebiz:showTitle titleId="ordershow.m.viewNow"/> <i class="fa fa-angle-right"></i></a></div>
												<span>￥<b>89.00</b></span><s>￥599.00</s>
												<p class="y_tlname"><a href="#"><aebiz:showTitle titleId="ordershow.m.showProduct"/></a></p>
											</div>
										</li>
										<li>
											<div class="y_pcbox">
												<div class="y_tlpic"><a href="#"><img src="img/aebiz/y_tlpic.jpg"></a><a class="y_picbtn" href="#"><aebiz:showTitle titleId="ordershow.m.viewNow"/> <i class="fa fa-angle-right"></i></a></div>
												<span>￥<b>89.00</b></span><s>￥599.00</s>
												<p class="y_tlname"><a href="#"><aebiz:showTitle titleId="ordershow.m.showProduct"/></a></p>
											</div>
										</li>	
										<li>
											<div class="y_pcbox">
												<div class="y_tlpic"><a href="#"><img src="img/aebiz/y_tlpic.jpg"></a><a class="y_picbtn" href="#"><aebiz:showTitle titleId="ordershow.m.viewNow"/> <i class="fa fa-angle-right"></i></a></div>
												<span>￥<b>89.00</b></span><s>￥599.00</s>
												<p class="y_tlname"><a href="#"><aebiz:showTitle titleId="ordershow.m.showProduct"/>/a></p>
											</div>
										</li>	
										<li>
											<div class="y_pcbox">
												<div class="y_tlpic"><a href="#"><img src="img/aebiz/y_tlpic.jpg"></a><a class="y_picbtn" href="#"><aebiz:showTitle titleId="ordershow.m.viewNow"/> <i class="fa fa-angle-right"></i></a></div>
												<span>￥<b>89.00</b></span><s>￥599.00</s>
												<p class="y_tlname"><a href="#"><aebiz:showTitle titleId="ordershow.m.showProduct"/></a></p>
											</div>
										</li>	
										<li>
											<div class="y_pcbox">
												<div class="y_tlpic"><a href="#"><img src="img/aebiz/y_tlpic.jpg"></a><a class="y_picbtn" href="#"><aebiz:showTitle titleId="ordershow.m.viewNow"/> <i class="fa fa-angle-right"></i></a></div>
												<span>￥<b>89.00</b></span><s>￥599.00</s>
												<p class="y_tlname"><a href="#"><aebiz:showTitle titleId="ordershow.m.showProduct"/></a></p>
											</div>
										</li>	
										<li>
											<div class="y_pcbox">
												<div class="y_tlpic"><a href="#"><img src="img/aebiz/y_tlpic.jpg"></a><a class="y_picbtn" href="#"><aebiz:showTitle titleId="ordershow.m.viewNow"/><i class="fa fa-angle-right"></i></a></div>
												<span>￥<b>89.00</b></span><s>￥599.00</s>
												<p class="y_tlname"><a href="#"><aebiz:showTitle titleId="ordershow.m.showProduct"/></a></p>
											</div>
										</li>
										<li>
											<div class="y_pcbox">
												<div class="y_tlpic"><a href="#"><img src="img/aebiz/y_tlpic.jpg"></a><a class="y_picbtn" href="#"><aebiz:showTitle titleId="ordershow.m.viewNow"/><i class="fa fa-angle-right"></i></a></div>
												<span>￥<b>89.00</b></span><s>￥599.00</s>
												<p class="y_tlname"><a href="#"><aebiz:showTitle titleId="ordershow.m.showProduct"/></a></p>
											</div>
										</li>
									</ul>
									<p class="y_tlnext">
										<span class="y_cur"><i class="fa fa-circle"></i></span>	
										<span><i class="fa fa-circle"></i></span>
										<span><i class="fa fa-circle"></i></span>
										<span><i class="fa fa-circle"></i></span>
									</p>
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

	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterBottom.jsp"%>
	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImportJs.jsp"%>
	<script src="${pageContext.request.contextPath}/static/usercenter/js/jquery.colorbox-min.js"></script>
	<script src="${pageContext.request.contextPath}/static/usercenter/js/ajaxfileupload.js"></script>
	<script src="${pageContext.request.contextPath}/static/usercenter/js/validation/jquery.validate.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/usercenter/js/validation/aebiz.validate.js"></script>

	<script type="text/javascript">
	  
	</script>
</body>
</html>
