<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>                        
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>           
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   

<c:if test="${!empty carts && !empty carts.shoppingCart}">
	<div class="y_rtcttit"><label><input id="CheckAll" name="CheckAll" type="checkbox" <c:if test="${carts.checked}"> checked="checked" </c:if>>全选</label><a href="cart.html">去购物车查看</a></div>
	<div class="y_ctlistbox">
		<div class="y_h28">&nbsp;</div>
		<c:forEach items="${cartStore}" var="cartStore" >	
			<c:if test="${!empty cartStore && !empty carts.shoppingCart[cartStore]}" >
				<div class="y_rtpbox">
					<p class="y_rtptit">
						<input type="checkbox" value="${cartStore.storeUuid}" id="store_${cartStore.storeUuid}" <c:if test="${cartStore.checked}"> checked="checked" </c:if>>
						<span>${cartStore.storeName}</span><b>99</b>
					</p>
					<ul class="y_rtctlist">
						<c:forEach items="${carts.shoppingCart[cartStore]}" var="shoppingProduct" >
							<li>
								<label class="y_rtliipt"><input type="checkbox" id="product_${cartStore.storeUuid}_${shoppingProduct.productId}_${shoppingProduct.attrIds}" value="product_${shoppingProduct.productId}_${shoppingProduct.attrIds}" <c:if test="${shoppingProduct.checked}"> checked="checked" </c:if>></label>
								<div class="y_rtlipic"><a href="#"><img src="${shoppingProduct.productImg}"></a></div>
								<p class="y_rtlsxb">
									<c:forEach items="${shoppingProduct.allAttrs}" var="allattr" varStatus="status" >	
										${attrValue.value}<br/>
									</c:forEach>
								</p>
								<span class="y_rtpsize">
									<a href="#" id="reducestock_${shoppingProduct.productId}_${shoppingProduct.attrIds}"><i class="fa fa-minus-circle"></i></a>
									<input type="text" id="stock_${shoppingProduct.productId}_${shoppingProduct.attrIds}" value="${shoppingProduct.buyNum}" disabled>
									<a href="#" id="addstock_${shoppingProduct.productId}_${shoppingProduct.attrIds}"><i class="fa fa-plus-circle"></i></a>
								</span>
								<span class="y_rtpprce">${shoppingProduct.finalPrice}</span>
								<a class="y_delct" href="#" id="remove_${shoppingProduct.productId}_${shoppingProduct.attrIds}"><i class="fa fa-times-circle"></i></a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
		</c:forEach>
		<p class="y_rtfkbx"><a href="#">反馈意见</a></p>
	</div>
	<div class="y_rtjsbx">
		<p class="clearfix"><strong>已选${carts.totalCount}件</strong><span class="y_jsprce">￥${carts.totalMount}</span></p>
		<a href="#" class="btn btn-custom" id="goBlance">结 算</a>
	</div>
</c:if>
<c:if test="${empty carts || empty carts.shoppingCart}">
	<!--购物车无商品-->
		<div class="y_slidecartno">
			<span class="y_carticon"><i class="fa fa-shopping-cart"></i></span>
			<span class="y_cartnoms">
				购物车内暂时没有商品，快去挑选自己喜欢的商品吧~
			</span>
		</div>
	<!--购物车无商品 end-->
</c:if>