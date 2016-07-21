<!-- left sidebar 左侧导航 -->
<div class="col-xs-2 left-sidebar" data-spy="affix" data-offset-top="45" data-offset-bottom="55">
	<!-- main-nav -->
	<div class="main-nav">
		<ul class="main-menu">
			<li class="y_ltmenuop">
				<a href="#" class="js-sub-menu-toggle"><i class="fa fa-file-text"></i><span class="text">订单中心</span><i class="toggle-icon fa fa-angle-down"></i></a>
				<ul class="sub-menu open">
					<li><a href="${pageContext.servletContext.contextPath}/usercenter/order/list/1/10"><span class="text">我的订单</span></a></li>
					<li><a href="${pageContext.servletContext.contextPath}/usercenter/productappraise/toMyAppraise/1/10"><span class="text">我的评价</span></a></li>
					<li><a href="${pageContext.servletContext.contextPath}/usercenter/ordershow/toMyOrderShow/1/10"><span class="text">我的晒单</span></a></li>
					<!-- 
						<li><a href="本地生活订单列表.html"><span class="text">本地生活订单</span></a></li>
					 -->
				</ul>
			</li>
			<li class="y_ltmenuop">
				<a href="#" class="js-sub-menu-toggle"><i class="fa fa-star"></i><span class="text">关注中心</span><i class="toggle-icon fa fa-angle-down"></i></a>
				<ul class="sub-menu open">
					<li><a href="${pageContext.servletContext.contextPath}/usercenter/productfavorite/toPage"><span class="text">收藏的商品</span></a></li>
					<!-- 
						<li><a href="店铺收藏.html"><span class="text">收藏的店铺</span></a></li>
						<li><a href="购买过的店铺.html"><span class="text">购买过的店铺</span></a></li>
						<li><a href="浏览历史.html"><span class="text">浏览历史</span></a></li>
					-->
				</ul>
			</li>
			<li class="y_ltmenuop"><a href="#" class="js-sub-menu-toggle"><i class="fa fa-gavel"></i><span class="text">客户服务</span>
				<i class="toggle-icon fa fa-angle-down"></i></a>
				<ul class="sub-menu open">
					<li><a href="${pageContext.servletContext.contextPath}/usercenter/afterSale/list/1/10"><span class="text">退款管理</span></a></li>
					<li><a href="${pageContext.servletContext.contextPath}/ucentercomplaints/toPage"><span class="text">投诉管理</span></a></li>
				</ul>
			</li>
		</ul>
	</div>
	<!-- /main-nav -->

	<div class="sidebar-minified js-toggle-minified">
		<i class="fa fa-angle-left"></i>
	</div>
</div>
<!-- end left sidebar -->