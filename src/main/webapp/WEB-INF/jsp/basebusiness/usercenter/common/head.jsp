<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!-- TOP BAR 头部导航 -->
<div class="top-bar">
	<div class="container">
		<div class="row">
			<!-- logo -->
			<div class="col-mi-4 col-xs-2 logo">
				<label id="to_home"><a href="${pageContext.servletContext.contextPath}/"><img src="img/aebiz/logo2.jpg" alt="AEBIZ" /></a></label>
				<h1 class="sr-only">AEBIZ全网数商</h1>
			</div>
			
			<!-- 频道导航 -->
			<div class="col-mi-8 col-xs-3">
				<a class="btn btn-link y_xzcur" href="${pageContext.servletContext.contextPath}/customer/toIndex">我的全网</a>
				
				<div class="account_setting">
					<a class="btn btn-link y_xzcur" href="${pageContext.servletContext.contextPath}/usercenter/customer/toSecuritySetting">账户管理</a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.servletContext.contextPath}/usercenter/customer/toSecuritySetting"><aebiz:showTitle titleId="ucenter_head_safetyCenter"/></a></li>	
						<li><a href="${pageContext.servletContext.contextPath}/usercenter/customercomplex/toModifyCustomerInfo"><aebiz:showTitle titleId="ucenter_head_personalInfo"/></a></li>
						<li><a href="${pageContext.servletContext.contextPath}/usercenter/customeraddress/toCustomerAddress"><aebiz:showTitle titleId="ucenter_head_deliveryAddress"/></a></li>	
						<li><a href="${pageContext.servletContext.contextPath}/usercenter/customersubscription/toMySubscription"><aebiz:showTitle titleId="ucenter_head_mySubscription"/></a></li>	
						<li><a href="${pageContext.servletContext.contextPath}/usercenter/messageremind/showMessageRemindList"><aebiz:showTitle titleId="ucenter_head_siteNotice"/></a></li>	
					</ul>
				</div>
				
				<div class="account_setting">
					<a class="btn btn-link y_xzcur" href="${pageContext.servletContext.contextPath}/usercenter/virtualaccountcustomercharge/toMyAccount">资产中心</a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.servletContext.contextPath}/usercenter/virtualaccountcustomercharge/toMyAccount"><span class="text">虚拟账户</span></a></li>
						<li><a href="${pageContext.servletContext.contextPath}/usercenter/withdrawapply/extractionMoney"><span class="text">提现申请</span></a></li>
						<li><a href="${pageContext.servletContext.contextPath}/usercenter/vipclubintegrallog/toList/1/4"><span class="text">我的积分</span></a></li>
						<li><a href="${pageContext.servletContext.contextPath}/usercenter/coupondetails/toList"><span class="text">我的优惠券</span></a></li>
						<li><a href="${pageContext.servletContext.contextPath}/usercenter/giftcards/toList"><span class="text">我的礼品卡</span></a></li>
						<li><a href="${pageContext.servletContext.contextPath}/usercenter/customerbankrel/toBondBank"><span class="text">银行卡绑定</span></a></li>
					</ul>
				</div>
			</div>
			<!--end 频道导航 -->
			
			<div class="col-mi-12 col-xs-7">
				<div class="top-bar-right">
					<!-- 搜索框 -->
					<div class="input-group searchbox">
						<input type="search" class="form-control" placeholder="请输入商品/店铺名称">
						<span class="input-group-btn">
							<button class="btn btn-default" type="button"><i class="fa fa-search"></i></button>
						</span>
					</div>
					<!-- end 搜索框 -->
					
					<!-- 响应式按钮（小屏时展示） -->
					<a href="#" class="visible-in-mi main-nav-toggle"><i class="fa fa-bars"></i></a>
					<!-- end 响应式按钮 -->
					
					<div class="notifications">
						<ul>
							<!-- 头部购物车信息 -->
							<li class="notification-item general">
								<div class="btn-group">
									<a href="#" class="dropdown-toggle">
										<i class="fa fa-shopping-cart"></i><span class="count">2</span>
									</a>
								</div>
							</li>
							<!-- end 头部购物车信息 -->

							<!-- 头部邮件信息 -->
							<li class="notification-item inbox">
								<div class="btn-group">
									<c:choose>
										<c:when test="${messageCount==0}">
											<a href="#" class="dropdown-toggle" data-toggle="dropdown">
												<i class="fa fa-envelope"></i>
												<span class="circle"></span>
											</a>
										</c:when>
										<c:otherwise>
											<a href="#" class="dropdown-toggle" data-toggle="dropdown">
												<i class="fa fa-envelope"></i>
												<span class="count">${messageCount}</span>
												<span class="circle"></span>
											</a>
										</c:otherwise>
									</c:choose>
								
									<ul class="dropdown-menu" role="menu">
										<c:choose>
											<c:when test="${messageCount==0}">
												<li class="notification-header">
													<em>未读新消息</em>
												</li>
											</c:when>
											<c:otherwise>
												<li class="notification-header">
													<em>您有${messageCount}条未读消息</em>
												</li>
											</c:otherwise>
										</c:choose>
										
										<c:choose>
											<c:when test="${messageCount==0}">
												<li class="inbox-item clearfix">
													<a href="${pageContext.servletContext.contextPath}/usercenter/innermessage/toList">
														<div class="media">
															<div class="media-body">
																<p class="text">没有消息了</p>
															</div>
														</div>
													</a>
												</li>
											</c:when>
											<c:otherwise>
												<c:forEach items="${messageList}" var="message">
													<li class="inbox-item clearfix">
														<a href="${pageContext.servletContext.contextPath}/usercenter/innermessage/toList">
															<div class="media">
																<div class="pull-left" href="#">
																	<img class="media-object" src="${message.userImage}" alt="Antonio">
																</div>
																<div class="media-body">
																	<h5 class="media-heading name">${message.title}</h5>
																	<p class="text">${message.content}</p>
																	<span class="timestamp">${message.sendTime}</span>
																</div>
															</div>
														</a>
													</li>
												</c:forEach>
												
												<li class="notification-footer">
													<a href="${pageContext.servletContext.contextPath}/usercenter/innermessage/toList">查看全部信件</a>
												</li>
											</c:otherwise>
										</c:choose>
									</ul>
								</div>
							</li>
							<!-- end 头部邮件信息 -->
						</ul>
					</div>
					
					<!-- 头部用户信息和菜单 -->
					<div class="logged-user">
						<div class="btn-group">
							<a href="#" class="btn btn-link dropdown-toggle" data-toggle="dropdown">
								<c:if test="${!empty info }">
									<c:choose>
						  				<c:when test="${!empty info.imgUrl}">
						  					<img width="25" height="25" src="${info.imgUrl}"/>
						  				</c:when>
					  					<c:otherwise>
					  						<img width="25" height="25" src="${pageContext.servletContext.contextPath}/static/usercenter/img/default.png">
					  					</c:otherwise>
					  				</c:choose>
									<span class="name">${info.nickName}</span> <span class="caret"></span>
								</c:if>
							</a>
							
							<ul class="dropdown-menu" role="menu">
								<li>
									<a href="${pageContext.servletContext.contextPath}/usercenter/virtualaccountcustomercharge/toMyAccount">
										<i class="fa fa-user"></i>
										<span class="text">账户信息</span>
									</a>
								</li>
								<li>
									<a href="${pageContext.servletContext.contextPath}/usercenter/customercomplex/toModifyCustomerInfo">
										<i class="fa fa-cog"></i>
										<span class="text">编辑资料</span>
									</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath }/customer/logout">
										<i class="fa fa-power-off"></i>
										<span class="text">退出</span>
									</a>
								</li>
							</ul>
						</div>
					</div>
					<!-- end 头部用户信息和菜单 -->
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	// main responsive nav toggle 页面分辨率较小时按钮点击切换左侧菜单显示、隐藏
	$('.main-nav-toggle').clickToggle(
		function() {
			$('.left-sidebar').slideDown(300)
		},
		function() {
			$('.left-sidebar').slideUp(300);
		}
	);
	//会员中心首页向导的js
	var tour = new Tour({
	  steps: [
		  {
		    element: "#to_home",
		    title: "返回首页",
		    content: "点击LOGO可以返回到商城的首页哦！",
		    placement: "bottom",
		    backdrop:true
		  },
		  {
		    element: "#member_ms",
		    title: "会员基本信息",
		    content: "在这里可以查看到会员的基本信息哦，也可直接编辑会员资料",
		    placement: "right",
		    backdrop:true
		  },
		  {
		    element: "#member_remind",
		    title: "会员提醒",
		    content: "这里可以设置日程提醒，可以将要做的事添加进来哦！",
		    placement: "bottom",
		    backdrop:true
		  },
		  {
		    element: "#my_cart",
		    title: "我的购物车",
		    content: "这里是我的购物车信息，可以将商品添加到收藏夹，也可以直接去购物车结算哦！",
		    placement: "left",
		    backdrop:true
		  }
		]
	});
	tour.init();
	tour.restart();
</script>