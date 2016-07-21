<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImport.jsp" %>

</head>

<body>
	<!-- WRAPPER 头部、左侧、右侧大框架 -->
	<div class="wrapper">
		<!-- TOP BAR 头部导航 -->
		<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterHead.jsp" %>
		

		<!-- BOTTOM: LEFT NAV AND RIGHT MAIN CONTENT 左侧导航和右侧主体 -->
		<div class="bottom">
			<div class="container">
				<div class="row">
					<!-- left sidebar 左侧导航 -->
					<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterLeft.jsp" %>
					<h1><aebiz:showTitle titleId="withdrawapply.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
					<!-- end left sidebar -->
					<!-- content-wrapper 右侧主体 -->
					<div class="col-xs-10 content-wrapper">
						<!-- main -->
						<div class="content">
							<div class="main-content">
								<div class="main-header">
									<h2><aebiz:showTitle titleId="withdrawapply.m.withdrawapplySuccess"/></h2>
									<em>The success of the transaction</em>
								</div>
								<!--提现申请成功提示-->
								<div class="y_ordsuccess alert alert-success">
									<span class="y_odsicon text-success"><i class="fa fa-check-circle"></i></span>
									<div class="y_odsuccms">
										<h2 class="text-success"><aebiz:showTitle titleId="withdrawapply.m.uncheck"/></small></h2>
										<p>
											<a class="btn btn-primary" href="" title="<aebiz:showTitle titleId="withdrawapply.m.withdrawapplySuccess"/>"><aebiz:showTitle titleId="withdrawapply.m.withdrawapplySuccess"/></a>
											<a class="btn btn-primary" href="${pageContext.servletContext.contextPath}/usercenter/withdrawapply/userCenterDummy" title="<aebiz:showTitle titleId="basebusiness.showmessage.return"/>" data-toggle="modal"><aebiz:showTitle titleId="basebusiness.showmessage.return"/></a>
										</p>
									</div>
								</div>
								<!--广告位-->
								<div class="ad100 mb_20"><img src="img/aebiz/11.jpg"></div>
								<!--同类推荐商品列表-->
								<div class="y_tlcordlist">
									<h4>同类推荐商品</h4>
									<ul>
										<li>
											<div class="y_pcbox">
												<div class="y_tlpic"><a href="#"><img src="img/aebiz/y_tlpic.jpg"></a><a class="y_picbtn" href="#">立即查看 <i class="fa fa-angle-right"></i></a></div>
												<span>￥<b>89.00</b></span><s>￥599.00</s>
												<p class="y_tlname"><a href="#">美的全不锈钢电热水壶</a></p>
											</div>
										</li>
										<li>
											<div class="y_pcbox">
												<div class="y_tlpic"><a href="#"><img src="img/aebiz/y_tlpic.jpg"></a><a class="y_picbtn" href="#">立即查看 <i class="fa fa-angle-right"></i></a></div>
												<span>￥<b>89.00</b></span><s>￥599.00</s>
												<p class="y_tlname"><a href="#">美的全不锈钢电热水壶热水壶热水壶热水壶</a></p>
											</div>
										</li>	
										<li>
											<div class="y_pcbox">
												<div class="y_tlpic"><a href="#"><img src="img/aebiz/y_tlpic.jpg"></a><a class="y_picbtn" href="#">立即查看 <i class="fa fa-angle-right"></i></a></div>
												<span>￥<b>89.00</b></span><s>￥599.00</s>
												<p class="y_tlname"><a href="#">美的全不锈钢电热水壶</a></p>
											</div>
										</li>	
										<li>
											<div class="y_pcbox">
												<div class="y_tlpic"><a href="#"><img src="img/aebiz/y_tlpic.jpg"></a><a class="y_picbtn" href="#">立即查看 <i class="fa fa-angle-right"></i></a></div>
												<span>￥<b>89.00</b></span><s>￥599.00</s>
												<p class="y_tlname"><a href="#">美的全不锈钢电热水壶热水壶</a></p>
											</div>
										</li>	
										<li>
											<div class="y_pcbox">
												<div class="y_tlpic"><a href="#"><img src="img/aebiz/y_tlpic.jpg"></a><a class="y_picbtn" href="#">立即查看 <i class="fa fa-angle-right"></i></a></div>
												<span>￥<b>89.00</b></span><s>￥599.00</s>
												<p class="y_tlname"><a href="#">美的全不锈钢电热水壶热水壶热水壶</a></p>
											</div>
										</li>	
										<li>
											<div class="y_pcbox">
												<div class="y_tlpic"><a href="#"><img src="img/aebiz/y_tlpic.jpg"></a><a class="y_picbtn" href="#">立即查看 <i class="fa fa-angle-right"></i></a></div>
												<span>￥<b>89.00</b></span><s>￥599.00</s>
												<p class="y_tlname"><a href="#">美的全不锈钢美的全不锈钢电热水壶电热水壶</a></p>
											</div>
										</li>
										<li>
											<div class="y_pcbox">
												<div class="y_tlpic"><a href="#"><img src="img/aebiz/y_tlpic.jpg"></a><a class="y_picbtn" href="#">立即查看 <i class="fa fa-angle-right"></i></a></div>
												<span>￥<b>89.00</b></span><s>￥599.00</s>
												<p class="y_tlname"><a href="#">美的全不锈钢美的全不锈钢电热水壶电热水壶</a></p>
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
	
	
	<!--关注店铺成功弹出框-->
	<div id="modal-success" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h3>提示</h3>
				</div>
				<div class="modal-body">
					<div class="y_clear">
						<span class="y_succicon text-success"><i class="fa fa-check-circle"></i></span>
						<div class="y_succtext">
							<h3 class="text-success">关注成功！</h3>
							您已关注2个店铺，<a href="#">查看我的关注 >></a>
						</div>
					</div>
					<div class="alert alert-warning y_gzsttagalt">
						<h4>选择标签<small>（最多可选3个）</small></h4>
						<div class="y_gzsttag">
							<span>电器店</span><span>优惠</span>
							<div class="y_staddtag"><input type="text" class="form-control" placeholder="自定义"><a href="#">添加</a></div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" data-dismiss="modal">取消</button>
					<button class="btn btn-primary" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>
	<!--关注店铺成功弹出框 end-->
	<!-- FOOTER 底部 -->
	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImportJs.jsp" %>
	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterBottom.jsp" %>
	<!-- END FOOTER -->

	<!-- STYLE SWITCHER 右侧更换皮肤 -->
	<div class="del-style-switcher">
		<div class="del-switcher-toggle toggle-hide"></div>
		<form>
			<div class="del-section del-section-skin">
				<h5 class="del-switcher-header">Choose Skins:</h5>

				<ul>
					<li><a href="#" title="Slate Gray" class="switch-skin slategray" data-skin="css/skins/slategray.css">Slate Gray</a></li>
					<li><a href="#" title="Dark Blue" class="switch-skin darkblue" data-skin="css/skins/darkblue.css">Dark Blue</a></li>
					<li><a href="#" title="Dark Brown" class="switch-skin darkbrown" data-skin="css/skins/darkbrown.css">Dark Brown</a></li>
					<li><a href="#" title="Light Green" class="switch-skin lightgreen" data-skin="css/skins/lightgreen.css">Light Green</a></li>
					<li><a href="#" title="Orange" class="switch-skin orange" data-skin="css/skins/orange.css">Orange</a></li>
					<li><a href="#" title="Red" class="switch-skin red" data-skin="css/skins/red.css">Red</a></li>
					<li><a href="#" title="Teal" class="switch-skin teal" data-skin="css/skins/teal.css">Teal</a></li>
					<li><a href="#" title="Yellow" class="switch-skin yellow" data-skin="css/skins/yellow.css">Yellow</a></li>
				</ul>

				<button type="button" class="switch-skin-full fulldark" data-skin="css/skins/fulldark.css">Full Dark</button>
				<button type="button" class="switch-skin-full fullbright" data-skin="css/skins/fullbright.css">Full Bright</button>
			</div>
			
			<p><a href="#" title="Reset Style" class="del-reset-style">Reset Style</a></p>
		</form>
	</div>
	<!-- END STYLE SWITCHER -->

	<!-- Javascript -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/modernizr.js"></script>
	<script src="js/king-common.js"></script>
	<script src="demo-style-switcher/js/deliswitch.js"></script><!--页面右侧设置的js-->
	<script>
		$(function(){
			//关注店铺成功选择标签js
			$(".y_gzsttagalt").on("click",".y_gzsttag span",function(){
				$(this).toggleClass("active");	
			});
			$(".y_staddtag a").click(function(){
				if($(this).prev().val()!==''){
					var addspan = "<span>"+$(this).prev().val()+"</span>";
					$(this).parent(".y_staddtag").before(addspan)
				}
			})
		})	
	</script>
</body>
</html>
