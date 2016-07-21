<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>  
<!-- Bootbox -->
	<script src="${pageContext.servletContext.contextPath}/static/frontpage/js/jquery.bootbox.js"></script>

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
                <!-- <img src="img/IOS.jpg" width="110"> -->
                <p class="y_smewm">IOS 版</p>
              </li>
              <li>
                <!-- <img src="img/android.jpg" width="110"> -->
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
					$("#myUSD").html('<a href="#" class="dropdown-toggle" data-toggle="dropdown">我的优食代 <i class="fa fa-caret-down"></i></a><s></s><ul class="dropdown-menu" role="menu"><li><a href="${pageContext.servletContext.contextPath}/usercenter/order/list/1/1/10?init=true">订单</a></li><li><a href="${pageContext.servletContext.contextPath}/usercenter/productfavorite/toPage?init=true">收藏的商品</a></li><li><a href="${pageContext.servletContext.contextPath}/usercenter/storefavorite/toPage">收藏的店铺</a></li><li><a href="${pageContext.servletContext.contextPath}/usercenter/giftcards/toList/1/4?init=true">我的礼品卡</a></li></ul>');
					$("#myMSG").html('<a href="#" class="dropdown-toggle" data-toggle="dropdown">消息 <i class="w_pr5 f_clr9db032 num" id="msgcountid">0</i></a>');
					$("#myORDER").html('<a href="javascript:void(0);" onClick="goOrder();" class="dropdown-toggle" data-toggle="dropdown">订单 <i class="w_pr5 f_clr9db032 num" id="ordercountid">0</i></a>');
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

function goOrder(){
	window.location.href="${pageContext.servletContext.contextPath}/usercenter/order/list/1/1/10?init=true";
}
</script>
<!--打开网页后的提示注册信息-->
<div class="modal w_activies" role="dialog" id="w_activies">
  <div class="modal_content">
  	 <!-- 关闭 -->
  	 <div class="modal_head" id="closeDode">
  	 	<img src="${pageContext.servletContext.contextPath}/static/frontpage/img/adimg/guanbi.png" alt="" onclick="closeModal()"/>
  	 </div>
  	 <!--内容部分-->
  	 <div class="modal_body">
  	 	<dl class="tr_one">
  	 		<dt>购物保障</dt>
  	 		<dd><a href="javascript:void(0);"><img src="${pageContext.servletContext.contextPath}/static/frontpage/img/adimg/gwbz_span.png"/><span class="line"></span></a><a href="javascript:void(0);"><img src="${pageContext.servletContext.contextPath}/static/frontpage/img/adimg/gwbz_yjrz.png"/><span class="line"></span></a><a href="javascript:void(0);" class="last"><img src="${pageContext.servletContext.contextPath}/static/frontpage/img/adimg/gwbz_tgsq.png"/></a></dd>
  	 	</dl>
  	 	<dl class="tr_two">
  	 		<dt>新客专享</dt>
  	 		<dd><a href="javascript:void(0);"><img src="${pageContext.servletContext.contextPath}/static/frontpage/img/adimg/gwbz_mfsh.png"/></a><a href="javascript:void(0);"><img src="${pageContext.servletContext.contextPath}/static/frontpage/img/adimg/xkzx_zclq.png"/></a></dd>
  	 	</dl>
  	 	<p><a href="${pageContext.servletContext.contextPath}/customerregist/toRegister"><img src="${pageContext.servletContext.contextPath}/static/frontpage/img/adimg/zejjsfen.png"/></a></p>
  	 </div>
  </div>
</div>
<!--打开网页后的提示注册信息js代码-->
<script type="text/javascript">
$.get("${pageContext.servletContext.contextPath}/getRegisterTipCookies",
		{ranNum:Math.random()},function(data) {
			if("false" == data){
				$(".w_activies").show();
				$(".w_activies").animate({top:"100px"},1000);
				$.get("${pageContext.servletContext.contextPath}/addRegisterTipCookies",{ranNum:Math.random()},function(data){
				});
			}
		});
function closeModal(){
	$(".w_activies").fadeOut(500);
 }  
</script>