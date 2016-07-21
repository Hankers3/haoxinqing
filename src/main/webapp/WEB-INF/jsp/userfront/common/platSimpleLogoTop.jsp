<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<div class="login_header clearfix">
 <div class="w_1280">
	<h1 class="y_logo">
		<a href="${pageContext.request.contextPath }/" title="<aebiz:platConfig codeName='platName'/>"><img src="<aebiz:platConfig codeName='platLogoPath'/>"></a>
	</h1>
  <div class="w_selectarea">
		<div class="w_hoverarea">
				<a class="w_current" href="#"><span class="this_city">北京</span><i class="fa fa-caret-down fr"></i></a>
					<div class="w_area">
						<span>热门城市：</span>
						<p><a href="#">北京</a><a href="#">上海</a><a href="#">天津</a><a href="#">广州</a><a href="#">东京</a><a href="#">深证</a><a href="#">北京</a><a href="#">北京</a><a href="#">北京</a><a href="#">北京</a><a href="#">北京</a><a href="#">北京</a><a href="#">上海</a><a href="#">天津</a><a href="#">广州</a><a href="#">东京</a><a href="#">深证</a><a href="#">北京</a></p>
					  <a class="w_more_city" href="#">更多城市</a>
			    </div>
       </div>
       <h3 class="second_title">一个只做食品安全的网站</h3>
       </div>
	<div class="w_page-title">
	  <h2>欢迎登录</h2>
	</div>
  <div class="w_attestation fr"><img src="${pageContext.request.contextPath }/static/frontpage/img/adimg/icon_renzhengs.jpg"/></div>
 </div>
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
