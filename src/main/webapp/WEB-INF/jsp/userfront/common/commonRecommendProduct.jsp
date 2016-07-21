<%@page import="com.aebiz.b2b2c.product.productpub.vo.ProductModel"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.aebiz.b2b2c.baseframework.servicelocator.ServiceLocator" %>
<%@page import="java.util.*" %>
<%@page import="com.aebiz.b2b2c.product.interactive.product.service.impl.ProductInteractiveServiceImpl" %>
<%@page import="com.aebiz.b2b2c.product.interactive.product.vo.ProductInteractiveQueryModel" %>
<%@page import="com.aebiz.b2b2c.product.interactive.product.vo.StoreProductInteractiveModel" %>
<%@page import="com.aebiz.b2b2c.baseframework.utils.StringUtil" %>
<%
	ProductInteractiveServiceImpl productInteractiveService = (ProductInteractiveServiceImpl)ServiceLocator.getInstance().getCtx().getBean("productInteractiveServiceImpl");
	ProductModel pmode = (ProductModel)request.getAttribute("m");
	List<StoreProductInteractiveModel> productList = null;
	String storeUuid = "";
	String storeCategoryUuid = "";
	if(pmode!=null&&pmode.getProductMain()!=null){
		storeUuid = pmode.getProductMain().getStoreUuid();
		//storeCategoryUuid = pmode.getProductMain().getCategoryUuid();
	}
	String productName = "";
	String shopPrice = "";
	String shopPrice1 = "";
	String showNumber = "12";
	if(!StringUtil.isEmpty(showNumber)){
		int num = Integer.parseInt(showNumber);
		productList = productInteractiveService.getStoreProductList(storeUuid, storeCategoryUuid, productName, shopPrice, shopPrice1, 1, num);
	}

%>

<div class="y_border y_mb30">
		<div class="y_tit"><h3>推荐商品</h3></div>
		<div class="recommend_product container_product owl-carousel">
			<c:if test="<%=productList!=null&&productList.size()>0%>">
				<c:forEach items="<%=productList%>" var="item">
					<div class="item">
						<div class="y_tlpic"><a href="${pageContext.servletContext.contextPath}/front/product/toProduct/${item.uuid}"><img src="${item.samllImageUrl}"></a><a class="y_picbtn" href="${pageContext.servletContext.contextPath}/front/product/toProduct/${item.uuid}">立即查看 <i class="fa fa-angle-right"></i></a></div>
						<span>￥<b>${item.price}</b></span><s>￥${item.marketPrice}</s>
						<p class="y_tlname"><a href="${pageContext.servletContext.contextPath}/front/product/toProduct/${item.uuid}">${item.productName}</a></p>
					</div>
				</c:forEach>
			</c:if>
		</div>
	</div>