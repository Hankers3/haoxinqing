<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<!doctype html>
<html>
<head>
<title>雇我吧-服务标准</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/appmore/css/global.css" type="text/css" media="screen" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0">
<meta name="HandheldFriendly" content="true"/>
<meta name="MobileOptimized" content="1024"/>
<meta name="format-detection" content="telephone=no" />
<script src="${pageContext.request.contextPath }/static/appmore/js/jquery-1.11.1.min.js"></script>

<!--[if IE]>
   <script src="${pageContext.request.contextPath }/static/appmore/js/html5.js"></script>
<![endif]-->
</head>
<body>
	<section class="m_standard">
	 <!-- 静态数据 -->
		<ul>
		  <li>
		  	<!--雇我吧-厨房-->
				<div class="m_kitchen">
					<div class="m_tit clearfix"><span class="fl">厨房</span><i class="fr m_cur"></i></div>
					<div class="m_show_in m_block">
						
						<!--<div class="m_p m_price clearfix">
							<div class="fl m_fl">价  格</div>
							<div class="fl m_fr"><span class="f_color2">￥39.00</span> 元/平米</div>
						</div>-->
						
						<div class="m_remarks m_p clearfix">
							<div class="fl m_fl">备  注</div>
							<div class="fl m_fr">
								包含：地面、灶台、橱柜表面、油烟机表面和油盒、站地面上可触及的墙面和门窗、垃圾桶、电器表面、刀具、水盆水桶、微波炉内外、烤箱内外、锅碗瓢盆<p>不含：油烟机拆卸和内部清洗；冰箱内部清洗；设施维修</p>
				      </div>
						</div>
					</div>
				</div><!--雇我吧-厨房 end-->
		  </li>
		  <li>
		  	<!--雇我吧-卫生间-->
				<div class="m_toilet">
					<div class="m_tit clearfix"><span class="fl">卫生间</span><i class="fr"></i></div>
					<div class="m_show_in">
						
						<!--<div class="m_p m_price clearfix">
							<div class="fl m_fl">价  格</div>
							<div class="fl m_fr"><span class="f_color2">￥12.00</span> 元/平米</div>
						</div>-->
						
						<div class="m_remarks m_p clearfix">
							<div class="fl m_fl">备  注</div>
							<div class="fl m_fr">
								包含：地面、墙面、马桶、浴缸、梳妆台、洗手盆表面、垃圾桶、橱柜内外
								<p>不包含:管道疏通、设施维修</p>
				      </div>
						</div>
				  </div>
				</div><!--雇我吧-卫生间 end-->
		  </li>
		  <li>
		  	<!--雇我吧-客厅和卧室-->
				<div class="m_toilet">
					<div class="m_tit clearfix"><span class="fl">客厅和卧室</span><i class="fr"></i></div>
					<div class="m_show_in">
						
						<!--<div class="m_p m_price clearfix">
							<div class="fl m_fl">价  格</div>
							<div class="fl m_fr"><span class="f_color2">￥7.00</span> 元/平米</div>
						</div>-->
						
						<div class="m_remarks m_p clearfix">
							<div class="fl m_fl">备  注</div>
							<div class="fl m_fr">
								包含：地面、踢脚线、橱柜表面、门窗、桌椅床、家电表面
								<p>不包含：灯具、家电内部、水族箱内部</p>
				      </div>
						</div>
			  	</div>
				</div><!--雇我吧-客厅和卧室 end-->
		  </li>
		  <li>
		  	<!--雇我吧-抽油烟机-->
				<div class="m_toilet">
					<div class="m_tit clearfix"><span class="fl">抽油烟机</span><i class="fr"></i></div>
					<div class="m_show_in">
						
						<!--<div class="m_p m_price clearfix">
							<div class="fl m_fl">价  格</div>
							<div class="fl m_fr">
								<p>中式<span class="f_color2">￥200.00</span> 元/台</p>
								<p>欧式<span class="f_color2">￥300.00</span> 元/台</p>
							</div>
						</div>-->
						
						
						<div class="m_remarks m_p clearfix">
							<div class="fl m_fl">备  注</div>
							<div class="fl m_fr">
								包含：拆卸、内部清洗。
				      </div>
						</div>
					</div>
				</div><!--雇我吧-抽油烟机 end-->
		  </li>
		  <li>
		  	<!--雇我吧-冰箱-->
				<div class="m_toilet">
					<div class="m_tit clearfix"><span class="fl">冰箱</span><i class="fr"></i></div>
					<div class="m_show_in">
						
						<!--<div class="m_p m_price clearfix">
							<div class="fl m_fl">价  格</div>
							<div class="fl m_fr">
								<p>单门<span class="f_color2">￥160.00</span> 元/台</p>
								<p>双门<span class="f_color2">￥170.00</span> 元/台</p>
								<p>三门<span class="f_color2">￥190.00</span> 元/台</p>
								<p>对开门<span class="f_color2">￥220.00</span> 元/台</p>
							</div>
						</div>-->
						
						<div class="m_remarks m_p clearfix">
							<div class="fl m_fl">备  注</div>
							<div class="fl m_fr">
								包含：搁架、储物盒、抽屉等；除臭；臭氧杀菌。（请提前12小时断电化冻）
				      </div>
						</div>
					</div>
				</div><!--雇我吧-冰箱 end-->
		  </li>
	  </ul>
	  <!-- 动态数据 -->
		<%-- <ul>
		  <li>
		  <c:choose>
		  <c:when test="${productCityModels!=null&&productCityModels.size()>0}">
		  <c:forEach items="${productCityModels}" var="productCity">
		  	<c:if test="${productCity!=null && productCity.productMainModel!=null}">
		  	<div class="m_kitchen">
					<div class="m_tit clearfix"><span class="fl">${productCity.productMainModel.productName}</span><i class="fr m_cur"></i></div>
					<div class="m_show_in m_block">
						<div class="m_remarks m_p clearfix">
							<div class="fl m_fl">备  注</div>
							<div class="fl m_fr">
								${productCity.productMainModel.note}
				      		</div>
						</div>
					</div>
			</div>
			</c:if>
		  </c:forEach>
		  </c:when>
		  <c:otherwise>
		  	<div class="m_kitchen">暂无！</div>
		  </c:otherwise>
		  </c:choose>
		  </li>
	  </ul> --%>
	</section>
	<script>
	$(".m_standard .m_tit").click(function(){
		$(this).next().slideToggle(500);
		$(this).children(".fr").toggleClass("m_cur");
	});
	</script>
</body>
</html>
