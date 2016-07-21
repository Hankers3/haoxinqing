<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImport.jsp" %>

</head>
   
<body class="forms-inplace-editing">
	<!-- WRAPPER -->
	<div class="wrapper">
		<!-- TOP BAR 头部导航 -->
		<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterHead.jsp" %>
		<!-- /top -->
		
		<!-- BOTTOM: LEFT NAV AND RIGHT MAIN CONTENT -->
		<div class="bottom">
			<div class="container">
				<div class="row">
					<!-- left sidebar 左侧导航 -->
					<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterLeft.jsp" %>
					<!-- end left sidebar -->

					<!-- content-wrapper -->
					<div class="col-xs-10 content-wrapper">
						
						<!-- main -->
						<div class="content">
						 	<div class="main-header">
								<h2><aebiz:showTitle titleId="virtualaccountcustomercharge.m.myVirtual"/></h2>
							</div>

							<div class="main-content">

								<div class="clearfix state-box integral"> <!--我的虚拟账户状态 star-->
									<div class="row nomargin">
										<div class="col-sm-9 col-li nopadding">
										  <ul>
										  	<li><aebiz:showTitle titleId="virtualaccountcustomerlog.m.withdrawalTotal"/>&nbsp;:<span class="rem-price"><i class="fa fa-rmb"></i>${account.accountBalance }</span></li>
												<li><aebiz:showTitle titleId="virtualaccountcustomerlog.m.availablewithdrawal"/>&nbsp;:<span class="rem-price"><i class="fa fa-rmb"></i>${account.accountBalance }</span></li>
												<li><aebiz:showTitle titleId="virtualaccountcustomerlog.m.withdrawaling"/>&nbsp;:<span class="rem-price"><i class="fa fa-rmb"></i>${account.virtualFrezonMount }</span></li>
										  </ul>
										</div>
										<div class="col-sm-3 p-btn">
										   <p class="text-right viracc-btn"><a href="${pageContext.servletContext.contextPath}/usercenter/virtualaccountcustomercharge/toAdd" class="btn btn-primary" title="<aebiz:showTitle titleId="virtualaccountcustomercharge.m.payMsg"/>"><aebiz:showTitle titleId="virtualaccountcustomercharge.m.pay"/></a> <a href="${pageContext.servletContext.contextPath}/usercenter/virtualaccountcustomercharge/toWithdrawApply" class="btn btn-warning" title="<aebiz:showTitle titleId="virtualaccountcustomercharge.m.withdraMsg2"/>"><aebiz:showTitle titleId="virtualaccountcustomercharge.m.withdraTitle"/></a></p>
										</div>
								  </div>
								</div> <!--我的虚拟账户状态 end-->
								
								<div class="alert alert-success">
									<strong><i class="fa fa-exclamation"></i></strong><aebiz:showTitle titleId="virtualaccountcustomercharge.m.withdraMsg"/>
								</div>
								 
				                <div class="disct-couB viracc-con">
				                  <ul class="nav nav-tabs mb_20">
									 <li class="active"><a data-toggle="tab" href="#shouzhimingxi"><i class="fa fa-list-alt"></i><aebiz:showTitle titleId="virtualaccountcustomercharge.m.detail"/></a></li>
									 <li><a data-toggle="tab" href="#chongzhijilu"><i class="fa fa-indent"></i><aebiz:showTitle titleId="virtualaccountcustomercharge.m.paylog"/></a></li>
									 <li><a data-toggle="tab" href="#tixianjilu" id="tixijl"><i class="fa fa-dedent"></i><aebiz:showTitle titleId="virtualaccountcustomercharge.m.withdralog"/></a></li>
								  </ul>

									 <!-- 提现记录 -->
								  <div class="tab-content">
								  
								   </div>
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

	<!-- FOOTER 底部 -->
	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImportJs.jsp" %>
	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterBottom.jsp" %>
	<!-- END FOOTER -->

	<!-- STYLE SWITCHER -->
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
	<!-- 会员中心公用的js 5个-->
  <script src="${pageContext.servletContext.contextPath}/static/usercenter/js/jquery.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/bootstrap.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/modernizr.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/king-common.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/demo-style-switcher/js/deliswitch.js"></script><!--页面右侧设置的js-->
	<!-- 当前页面使用的js-->
    	
    <script>
    $(document).ready(function(){
    	//页面加载时查询收支明细
    	searchCustomerLogDetail("","1","10");
		
		$("a[href='#shouzhimingxi']").click(function(){
			searchCustomerLogDetail("","1","10");
		});
		$("a[href='#chongzhijilu']").click(function(){
			searchChargeDetail("","1","5")
		});
		$("a[href='#tixianjilu']").click(function(){
			searchWithapplyDetail("","","1","5")
		});
		
	})
	
		//收支明细
		function searchCustomerLogDetail(operType, nowPage,
				showPage) {
			var url = "${pageContext.request.contextPath}/usercenter/virtualaccountcustomerlog/searchCustomerLogDetail";
			$.post(url, {
				operType : operType,
				nowPage : nowPage,
				showPage : showPage,
				ranNum : Math.random()
			}, function(data) {
				$(".tab-content").text("");
				$(".tab-content").append(data);
			});
		}
    
		//充值记录
		function searchChargeDetail(state, nowPage,
				showPage) {
			var url = "${pageContext.request.contextPath}/usercenter/virtualaccountcustomercharge/searchChargeDetails";
			$.post(url, {
				state : state,
				nowPage : nowPage,
				showPage : showPage,
				ranNum : Math.random()
			}, function(data) {
				$(".tab-content").text("");
				$(".tab-content").append(data);
			});
		}
		
    	//提现记录
		function searchWithapplyDetail(state,timeType, nowPage,
				showPage) {
			var url = "${pageContext.request.contextPath}/usercenter/virtualaccountcustomercharge/searchWithapplyDetail";
			$.post(url, {
				state : state,
				timeType : timeType,
				nowPage : nowPage,
				showPage : showPage,
				ranNum : Math.random()
			}, function(data) {
				$(".tab-content").text("");
				$(".tab-content").append(data);
			});
		}
    	
		
    
	function customerAccountPage(nowPage,showPage){
		var operType = $("#operType").val();
		searchCustomerLogDetail(operType,nowPage,showPage);
	}
	
	function chargePage(nowPage,showPage){
		var state = $("#state").val();
		searchChargeDetail(state,nowPage,showPage);
	}
	
	function wirhdrawapplyPage(nowPage,showPage){
		var state = $("#state").val();
		var timeType = $("#timeType").val();
		searchWithapplyDetail(state,timeType,nowPage,showPage);
	}
	</script>
</body>
</html>
