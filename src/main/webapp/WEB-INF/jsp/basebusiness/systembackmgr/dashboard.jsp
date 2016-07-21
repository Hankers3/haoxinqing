<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<!doctype html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<!-- Apple devices fullscreen -->
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<!-- Apple devices fullscreen -->
	<meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />

	<title>YOU时代-最先进的电商平台</title>

	<!-- Bootstrap -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/bootstrap.min.css">
	<!-- jQuery UI -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/jquery-ui/smoothness/jquery-ui.css">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/jquery-ui/smoothness/jquery.ui.theme.css">
	<!-- PageGuide -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/pageguide/pageguide.css">
	<!-- Fullcalendar -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/fullcalendar/fullcalendar.css">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/fullcalendar/fullcalendar.print.css" media="print">
	<!-- chosen -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/chosen/chosen.css">
	<!-- select2 -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/select2/select2.css">
	<!-- icheck -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
	<!-- Theme CSS -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/style.css">
	<!--[if lt IE 8]>
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/font-awesome-ie7.min.css">
	<![endif]-->
	<!-- Color CSS -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/themes.css">
	<!-- aebiz CSS -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/aebiz-0.1.css">
	<!-- jQuery -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/jquery.min.js"></script>


	<!-- Nice Scroll -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/nicescroll/jquery.nicescroll.min.js"></script>
	<!-- jQuery UI -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/jquery-ui/jquery.ui.core.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/jquery-ui/jquery.ui.widget.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/jquery-ui/jquery.ui.mouse.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/jquery-ui/jquery.ui.draggable.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/jquery-ui/jquery.ui.resizable.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/jquery-ui/jquery.ui.sortable.min.js"></script>
	<!-- Touch enable for jquery UI -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/touch-punch/jquery.touch-punch.min.js"></script>
	<!-- slimScroll -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<!-- Bootstrap -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/bootstrap.min.js"></script>
	<!-- vmap -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/vmap/jquery.vmap.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/vmap/jquery.vmap.world.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/vmap/jquery.vmap.sampledata.js"></script>
	<!-- Bootbox -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/bootbox/jquery.bootbox.js"></script>
	<!-- Flot -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/flot/jquery.flot.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/flot/jquery.flot.bar.order.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/flot/jquery.flot.pie.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/flot/jquery.flot.resize.min.js"></script>
	<!-- imagesLoaded -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/imagesLoaded/jquery.imagesloaded.min.js"></script>
	<!-- PageGuide -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/pageguide/jquery.pageguide.js"></script>
	<!-- FullCalendar -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fullcalendar/fullcalendar.min.js"></script>
	<!-- Chosen -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/chosen/chosen.jquery.min.js"></script>
	<!-- select2 -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/select2/select2.min.js"></script>
	<!-- icheck -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>

	<!-- Theme framework -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/eakroko.min.js"></script>
	<!-- Theme scripts -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/application.min.js"></script>
	<!-- Just for demonstration -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/demonstration.min.js"></script>

	<!--[if lte IE 9]>
		<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/placeholder/jquery.placeholder.min.js"></script>
		<script>
			$(document).ready(function() {
				$('input, textarea').placeholder();
			});
		</script>
		<![endif]-->
		
	<!--ie8及以下支持响应式的js-->
	<!--[if lte IE 8]>
    <script src="${pageContext.servletContext.contextPath}/static/sysback/js/respond.min.js"></script>
   
    <!--jquery.flot.js兼容IE8以下浏览器需引入excanvas.js-->
    <script src="${pageContext.servletContext.contextPath}/static/sysback/js/excanvas.js"></script>    
  <![endif]-->

	<!-- Favicon -->
	<link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/static/sysback/img/favicon.ico" />
	<!-- Apple devices Homescreen icon -->
	<link rel="apple-touch-icon-precomposed" href="${pageContext.servletContext.contextPath}/static/sysback/img/apple-touch-icon-precomposed.png" />
</head>

<body>
	
	<!--头部导航#navigation-->
<div id="navigation" style="background:#204e81">
</div>

<!--头部导航#navigation end-->
<div class="container-fluid" id="content">
	<div id="main" class="y_indexmain" style="margin-left:0">
		<div class="container-fluid">
			<div class="page-header">
				<div class="pull-left">
					<h1>仪表板</h1>
				</div>
				<div class="pull-right">
					<ul class="minitiles">
						<li class='grey'>
							<a href="#">
								<i class="fa fa-cogs"></i>
							</a>
						</li>
					</ul>
					<ul class="stats">

						<li class='lightred'>
							<i class="fa fa-calendar"></i>
							<div class="details">
								<span class="big">February 22, 2013</span>
								<span>Wednesday, 13:56</span>
							</div>
						</li>
					</ul>
				</div>
			</div>
			<div class="breadcrumbs">
				<ul>
					<li>
						<span>首页</span>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<span>仪表板</span>
					</li>
				</ul>
			</div>
			
			<div class="row">
				<div class="col-sm-3">
					<ul class="tiles index_tiles">
						<li class="orange long">
							<a href="${pageContext.servletContext.contextPath}/admin/toIndex/SysMenu0000000211?toUrl=${pageContext.servletContext.contextPath}/sysback/order/toList">
								<span class="count"><i class="fa fa-shopping-cart"></i>${newOrderNum}<label>新订单</label></span>
								<span class="name_bg"></span>
								<span class="ts_name"><i class="fa fa-arrow-circle-right"></i>查看更多</span>
							</a>
						</li>
					</ul>
				</div>
				<div class="col-sm-3">
					<ul class="tiles index_tiles">
						<li class="lime long">
							<a href="${pageContext.servletContext.contextPath}/admin/toIndex/SysMenu0000000311?toUrl=${pageContext.servletContext.contextPath}/sysback/productappraise/toList">
								<span class='count'><i class="fa fa-comment"></i>${orderAppNum}<label>评论</label></span>
								<span class="name_bg"></span>
								<span class="ts_name"><i class="fa fa-arrow-circle-right"></i>查看更多</span>
							</a>
						</li>
					</ul>	
				</div>
				<div class="col-sm-3">
					<ul class="tiles index_tiles">
						<li class="green long">
							<a href="${pageContext.servletContext.contextPath}/admin/toIndex/SysMenu0000000431?toUrl=${pageContext.servletContext.contextPath}/sysback/customercomb/toList">
								<span class='count'><i class="fa fa-globe"></i>${storeCustomerNum}<label>会员</label></span>
								<span class="name_bg"></span>
								<span class="ts_name"><i class="fa fa-arrow-circle-right"></i>查看更多</span>
							</a>
						</li>
					</ul>	
				</div>
				<div class="col-sm-3">
					<ul class="tiles index_tiles">
						<li class="pink long">
							<a href="${pageContext.servletContext.contextPath}/admin/toIndex/SysMenu0000000211?toUrl=${pageContext.servletContext.contextPath}/sysback/order/toList">
								<span class='count'><i class="fa fa-money"></i>+￥${storePaySuccessAmount}<label>新成交额</label></span>
								<span class="name_bg"></span>
								<span class="ts_name"><i class="fa fa-arrow-circle-right"></i>查看更多</span>
							</a>
						</li>
					</ul>		
				</div>
			</div>
			
			<div class="row">
				<div class="col-sm-6">
					<div class="box box-color box-bordered">
						<div class="box-title">
							<h3>
								<i class="fa fa-check"></i>信息提醒
							</h3>
						</div>
						
						<div class="box-content nopadding">
							<ul class="tasklist wait_work">
								<li class=''>
									<span class="check">
										订单提醒：
									</span>
									<span class="task index_ts">
										<div>
											<i class="fa fa-check"></i>
											<a href="${pageContext.servletContext.contextPath}/admin/toIndex/SysMenu0000000211?toUrl=${pageContext.servletContext.contextPath}/sysback/order/toList">交易完成订单(<b>${orderSuccessOrderNum}</b>)</a>
										</div>
										<div>
											<i class="fa fa-check"></i>
											<a href="${pageContext.servletContext.contextPath}/admin/toIndex/SysMenu0000000211?toUrl=${pageContext.servletContext.contextPath}/sysback/afterSale/toList">订单售后(<b>${arbitrateNum}</b>)</a>
										</div>
									</span>
								</li>
								<li class=''>
									<span class="check">
										商品提醒：
									</span>
									<span class="task index_ts">
										<div>
											<i class="fa fa-check"></i>
											<a href="${pageContext.servletContext.contextPath}/admin/toIndex/SysMenu0000000191?toUrl=${pageContext.servletContext.contextPath}/sysback/product/toList">正在出售的商品(<b>${shelveProductNum}</b>)</a>
										</div>
									</span>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="box box-color box-bordered lightred">
						<div class="box-title">
							<h3>
								<i class="fa fa-check"></i>紧急处理事项
							</h3>
						</div>
						
						<div class="box-content nopadding">
							<ul class="tasklist wait_work">
								<li class=''>
									<span class="check">
										投诉处理：
									</span>
									<span class="task index_ts">
										<div>
											<i class="fa fa-check"></i>
											<a href="${pageContext.servletContext.contextPath}/admin/toIndex/SysMenu0000000211?toUrl=${pageContext.servletContext.contextPath}/sysback/complaints/toList?complaintType=1">订单投诉(<b>${orderComplaintNum}</b>)</a>
										</div>
										<div>
											<i class="fa fa-check"></i>
											<a href="${pageContext.servletContext.contextPath}/admin/toIndex/SysMenu0000000211?toUrl=${pageContext.servletContext.contextPath}/sysback/complaints/toList?complaintType=2">商品投诉(<b>${productComplaintNum}</b>)</a>
										</div>
									</span>
								</li>
								<ul class="tasklist wait_work">
								<li class=''>
									<span class="check">
										审核处理：
									</span>
									<span class="task index_ts">
										<div>
											<i class="fa fa-check"></i>
											<a href="${pageContext.servletContext.contextPath}/admin/toIndex/SysMenu0000000191?toUrl=${pageContext.servletContext.contextPath}/sysback/product/toAudit">待审核商品(<b>${waitAuditProductNum}</b>)</a>
										</div>
										<%-- <div>
											<i class="fa fa-check"></i>
											<a href="${pageContext.servletContext.contextPath}/admin/toIndex/SysMenu0000000431?toUrl=${pageContext.servletContext.contextPath}/sysback/customeraudit/toList">待审核会员实名认证(<b>${waitAuditCustomerNum}</b>)</a>
										</div> --%>
										<div>
											<i class="fa fa-check"></i>
											<a href="${pageContext.servletContext.contextPath}/admin/toIndex/SysMenu0000000321?toUrl=${pageContext.servletContext.contextPath}/sysback/storecomplex/toUnCheckList">待审核商户(<b>${waitAuditStoreNum}</b>)</a>
										</div>
										</div>
									</span>
								</li>
							</ul>
						</div>
					</div>
				</div>
									
				
			</div>

		</div>
	</div>
</div>
<script>
	$("#navigation").load("${pageContext.servletContext.contextPath}/admin/toHeader");
</script>
</body>

</html>
