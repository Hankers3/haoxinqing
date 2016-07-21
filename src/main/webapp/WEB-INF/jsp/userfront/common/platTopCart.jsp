<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>                        
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>           
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  

<c:if test="${!empty carts && !empty carts.shoppingCart}">
		<input type="hidden" id="totalMount" value="${carts.totalMount}">
		<c:forEach items="${cartStore}" var="cartStore" >	
			<c:if test="${!empty cartStore && !empty carts.shoppingCart[cartStore]}" >
			  <ul class="dropdown-cart-product-list">
			  	<c:forEach items="${carts.shoppingCart[cartStore]}" var="shoppingProduct" >
				    <li>
				      <a href="javascript:void(0);" id="topremove_${shoppingProduct.productId}_${shoppingProduct.attrIds}" title="删除" class="delete-item"><i class="fa fa-times"></i></a>
				      <div class="y_cartpic">
				         <a href="${pageContext.servletContext.contextPath}/front/product/toProduct/${shoppingProduct.productId}"><img src="${shoppingProduct.productImgUrl}" alt="${shoppingProduct.productName}"></a>
				      </div>
				      <div class="dropdown-cart-details">
				        <p class="item-name"><a href="${pageContext.servletContext.contextPath}/front/product/toProduct/${shoppingProduct.productId}">${shoppingProduct.productName}</a></p>
				        <p>${shoppingProduct.buyNum}x<span class="item-price"><span class="f_wryh">￥</span>${shoppingProduct.basePrice}</span></p>
				        <c:if test="${'1'==shoppingProduct.productWarning}">
									<span class="y_label">库存不足,最多可购买${shoppingProduct.buyNum}件</span>	
								</c:if>													
								<c:if test="${'2'==shoppingProduct.productWarning}">
									<span class="y_label">库存紧张</span>	
								</c:if>
								<c:if test="${'3'==shoppingProduct.integralWarning}">
									<span class="y_label">积分不足</span>	
								</c:if>
				      </div><!-- End .dropdown-cart-details -->
				    </li>
				   </c:forEach>
			  </ul>
	  	</c:if>
		</c:forEach>
	  <ul class="dropdown-cart-total">
	      <li><span class="dropdown-cart-total-title">商品总数:</span>${carts.totalCount}</li>
	      <li><span class="dropdown-cart-total-title">总价:</span>￥${carts.totalMount}</li>
	  </ul><!-- .dropdown-cart-total -->
	  <c:if test="${carts.showToBlance}">
		  <div class="dropdown-cart-action">
		      <p><a href="javascript:void(0);" id="goBlanceTop" class="btn btn-custom btn-block">去结算</a></p>
		  </div><!-- End .dropdown-cart-action -->
	  </c:if>

</c:if>
<c:if test="${empty carts || empty carts.shoppingCart}">
	<!--购物车无商品-->
			<input type="hidden" id="totalMount" value="0.00">
			购物车还没有商品、赶紧去选购吧

	<!--购物车无商品 end-->
</c:if>