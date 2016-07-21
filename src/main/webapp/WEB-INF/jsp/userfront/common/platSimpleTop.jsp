<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>  
<!-- Bootbox -->
	<script src="${pageContext.servletContext.contextPath}/static/frontpage/js/jquery.bootbox.js"></script>
  <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/userfront/css/jquery-ui.css">
	<script src="${pageContext.servletContext.contextPath}/static/userfront/js/jquery-ui.js"></script>
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/userfront/css/jquery.autocomplete.css">
  
<!-- 	头部LOGO seach cart -->
<div class="header-top">
  <div class="container">
      <div class="fl">
      	<a href="javascript:addFavorite('<aebiz:platConfig codeName="platName"/>','<aebiz:platConfig codeName="platDomain"/>');" class="y_sc"><b></b>收藏优食代<aebiz:platConfig codeName="platName"/></a>
      	<span id="isLogin"></span>
      </div>
      <ul class="fr">
    		<li class="y_headrli" id="myUSD"></li>
    		<li class="y_headrli" id="myMSG"></li>
    		<li class="y_headrli" id="myORDER"></li>
    	<li class="y_headrli">
   			<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-mobile"></i> 移动优食代 <i class="fa fa-caret-down"></i></a>
   			<s></s>
            <ul class="code dropdown-menu" role="menu">
              <li>
                <img src="img/IOS.jpg" width="110">
                <p class="y_smewm">IOS 版</p>
              </li>
              <li>
                <img src="img/android.jpg" width="110">
                <p class="y_smewm">Android 版</p>
              </li>
         	</ul>
    	</li>
    	<li class="y_headrli"><a class="y_plr10" href="${pageContext.servletContext.contextPath}/frontshow/content/toSellerHelpCenter">帮助中心</a>|</li>
    	<li class="y_headrli"><a class="y_plr10" href="${pageContext.servletContext.contextPath}/frontshow/storeresidentapply/toApply">商家入驻</a>|</li>
    	<li class="y_headrli"><a class="y_plr10" href="#">积分商城</a>|</li>
    	<li class="y_headrli"><a class="y_plr10" href="#">商品溯源</a>|</li>
    	<li class="y_headrli">
      			<span class="w_pl10 f_color1"><i class="fa fa-phone"></i> 客服&nbsp;<aebiz:platConfig codeName='servicePhone'/></span>
      		</li>	
      </ul>
  </div>
</div>
<a href="<aebiz:platConfig codeName='pcTopAdUrl'/>"><img src="<aebiz:platConfig codeName='pcTopAdPath'/>"  width="1280" height="100"/></a>
<!--产品LOGO 地区选择 认证  cart serach-->
<div class="header">
	<div class="container">
		<div class="row">
			<div class="col-xs-4 col-sm-4">
				<h1 class="w_logo"> <a href="${pageContext.request.contextPath }/" title="<aebiz:platConfig codeName='platName'/>"><img src="<aebiz:platConfig codeName='platLogoPath'/>"></a></h1>
				<div class="w_selectarea">
					<div class="w_hoverarea">
						<a class="w_current" href="#"><span class="this_city">北京</span><i class="fa fa-caret-down fr"></i></a>
						<div class="w_area">
							<span>热门城市：</span>
							<p><a href="#">北京</a><a href="#">上海</a><a href="#">天津</a><a href="#">广州</a><a href="#">东京</a><a href="#">深证</a><a href="#">北京</a><a href="#">北京</a><a href="#">北京</a><a href="#">北京</a><a href="#">北京</a><a href="#">北京</a><a href="#">上海</a><a href="#">天津</a><a href="#">广州</a><a href="#">东京</a><a href="#">深证</a><a href="#">北京</a></p>
							<a  class="w_more_city" href="#">更多城市</a>
						</div>
					</div>
					<h2><img src="${pageContext.servletContext.contextPath}/static/frontpage/img/adimg/logotext.png"/></h2>
				</div>
				<!--地区选择效果-->
				<script type="text/javascript">
					//触碰盒子的切换
					$(".w_selectarea .w_hoverarea").hover(function(){
						$(this).find(".fa").addClass("fa-caret-up");
						$(this).find(".w_area").show(300);
						$(this).find(".w_area").slideDown(100);	
					},function(){
						$(this).find(".fa").removeClass("fa-caret-up");
						$(this).find(".w_area").hide(600);
						$(this).find(".w_area").slideUp(300);
					})
				
					//触碰盒子的切换
					$(".w_selectarea .w_area a").click(function(){
						var aText = $(this).text();
						$(this).parents(".w_selectarea").find(".this_city").text(aText);
						$(this).parents(".w_selectarea").find(".fa").removeClass("fa-caret-up");
						$(this).parents(".w_area").hide(600);
						$(this).parents(".w_area").slideUp(300);
					})
				</script>
			</div>
			<div class="col-xs-4 col-sm-5">
				<div class="w_attestation">
				<img src="${pageContext.servletContext.contextPath}/static/img/adimg/icon_renzhengs.jpg"/>
				</div>
			</div>
			<div class="col-xs-4 col-sm-3">
				<form action="${pageContext.servletContext.contextPath}/order/toBalance" id="cartFormTop" method="post">
				<div class="fl w_cart w_nologin">
					<img src="${pageContext.servletContext.contextPath}/static/frontpage/img/adimg/icon_gwc.png" alt=""/>
					<div class="dropdown-cart-menu">
					<div class="arrow_top"></div>
				    <div class="no_logintc" id="topCartShow">
				    	购物车是空的，马上购物吧
				    </div>
				  </div>
				</div>	
				</form>
				<div class="w_search">
					<input type="text" id="keyword" value="" placeholder='搜“食品安全课堂”' onkeydown="javascript:if(event.keyCode==13) return search();"><a class="btn btn-custom" href="javascript:search();"><i class="fa fa-search"></i></a>
					<p class="clearfix"><span class="fl">热门搜索：</span>
					<aebiz:platConfig codeName='wordAd'/></p>
				</div>
			</div>
		</div>
	</div>
</div>
    
<script>
	$.getJSON("${pageContext.servletContext.contextPath}/customer/isLogin",
			{runN:Math.random()},
			function(data) {
				if(data.nickName != null){
					var customerName = data.nickName;
					var head =  data.head;
					var customerUuid = data.customerUuid ;
					$("#isLogin").html('HI，<a href="${pageContext.servletContext.contextPath}/customer/toIndex">'+customerName+'</a>&nbsp;<a href="${pageContext.servletContext.contextPath}/customer/logout">退出</a>');
					$(".y_userbox").html('<div class="y_rtbtn y_userbtn add-tooltip" data-toggle="tooltip" data-placement="left" title="'+customerName+'"><a class="y_pic" href="${pageContext.servletContext.contextPath}/customer/toIndex"><img src="'+head+'"></a></div>');
					$(".y_ifixed").addClass("y_haslogoin").removeClass("y_nologin");
					$("#myUSD").html('<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">我的优食代 <i class="fa fa-caret-down"></i></a><s></s><ul class="dropdown-menu" role="menu"><li><a href="${pageContext.servletContext.contextPath}/usercenter/order/list/1/1/10?init=true">订单</a></li><li><a href="${pageContext.servletContext.contextPath}/usercenter/productfavorite/toPage?init=true">收藏的商品</a></li><li><a href="${pageContext.servletContext.contextPath}/usercenter/storefavorite/toPage">收藏的店铺</a></li><li><a href="${pageContext.servletContext.contextPath}/usercenter/giftcards/toList/1/4?init=true">我的礼品卡</a></li></ul>');
					$("#myMSG").html('<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">消息 <i class="w_pr5 f_clr9db032 num" id="msgcountid">0</i></a>');
					$("#myORDER").html('<a href="${pageContext.servletContext.contextPath}/usercenter/order/list/1/1/10?init=true" class="dropdown-toggle">订单 <i class="w_pr5 f_clr9db032 num" id="ordercountid">0</i></a>');
					$.post("${pageContext.servletContext.contextPath}/usercenter/innermessage/searchUnRead",{runN:Math.random()},function(data) {
						$("#msgcountid").html(data);
					});
					$.get("${pageContext.servletContext.contextPath}/usercenter/order/query/orderCount",{orderState:"1",commentState:"",runN:Math.random()},function(data) {
						$("#ordercountid").html(data);
					});
				}else{
					$("#isLogin").html('欢迎进入优食代！ <a class="y_mlr5" href="${pageContext.servletContext.contextPath}/customer/toLogin">请登录</a> <a class="y_mlr5" href="${pageContext.servletContext.contextPath}/customerregist/toRegister">免费注册</a>');
					$(".y_ifixed").addClass("y_nologin");
					$("#myUSD").html("");
				}
			});
	
	//获取商户入住的方式 0:注册  1:入住申请,线下回访
	
	$.get("${pageContext.servletContext.contextPath}/store/getEnterType",
			{runN:Math.random()},
			function(data) {
				if(data == 'reg'){
					$("#storereg").attr('href',"${pageContext.servletContext.contextPath}/frontshow/storecomplex/toRegStepOne");
				}else{
					$("#storereg").attr('href',"${pageContext.servletContext.contextPath}/frontshow/storeresidentapply/toApply");
				}
			});

//收藏本站
function addFavorite(title, url) {
  try {
      window.external.addFavorite(url, title);
  }catch (e) {
     try {
       window.sidebar.addPanel(title, url, "");
    }catch (e) {
         bootbox.alert("抱歉，您所使用的浏览器无法完成此操作。\n\n加入收藏失败，请使用Ctrl+D进行添加");
     }
  }

}

</script>
<script>
	
	var jsContextPath=getContextPath() ;
	function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    if(result == "/sysback"){
    	return "";
    }
    return result;
}
	
	$(function(){
		loadTopCart();
		//头部购物车移上时显示购物车信息
		$('.w_cart').hover(function(){
			$(this).addClass('open');
			loadTopCart();
		},function(){
			$(this).removeClass('open');
		})
		
		$(".w_cart").on("click", "[id^='topremove_']", function(){	
			var operId = $(this).attr("id") ;							
			//调用异步更新数量，计算价格		
			ajaxTopRemove(operId) ;
		})
		
		$(".w_cart").on("click", "[id^='goBlanceTop']", function(){
			var loginUrl = "${pageContext.servletContext.contextPath}/cart/isLogin"	
			$.get(loginUrl,{ranNum:Math.random()} ,function(data){
				if("0"==data) {
					//弹出登录框
					$("#modal-login-form").modal();
				}else{
					$("#cartFormTop").submit() ;
				}
			})	
		})	;
		
		                   $("#keyword").autocomplete({

		                        source: function (request, response) {
			                    $.ajax({
			                        url: "${pageContext.request.contextPath }/front/productList/getKeyWords",
			                        dataType: "json",
			                        data: {
			          							  key: request.term,
			          							  random:Math.random()
			          							},
			                        success: function (data) {
			                             response($.map(data.keyWords, function(item){
			                            	 return {
			                            		 lable:"uuid",
			                            		 value:item,
			                                 }
										}));
			                        }
			                        
			                    });
			                },
			                select: function(event, ui) {
			                    var keyword = ui.item.value;
			                    window.location.href="${pageContext.servletContext.contextPath}/front/productList/toProductList?keyword="+keyword;
			                },

		                   });
	});
	
	//加载头部购物车
	function loadTopCart(){
		var url = jsContextPath+"/cart/ajaxtopcartshow" ;
		$.post(url,function(data){
			$("#topCartShow").html(data);
			
			//var totalMount = $("#totalMount").val();
			//$("#showTotalMount").html(totalMount);	
		});			
	}
	
	//删除购物车中的商品
	function ajaxTopRemove(operId) {		
		var changeNumUrl = jsContextPath+"/cart/ajaxtopremove" ;			
		$.post(changeNumUrl,{productIdAndAttrId:operId,ranNum:Math.random()} ,function(data){
				$("#topCartShow").html(data);	
				//var totalMount = $("#totalMount").val();
				//$("#showTotalMount").html(totalMount);		
		})
	}



function search(){
	var keyword =  $('#keyword').val();
	if(keyword==""){
		$('#keyword').focus();
		return;
	}
	window.location.href="${pageContext.servletContext.contextPath}/front/productList/toProductList?keyword="+encodeURI(encodeURI(keyword));
}

</script>