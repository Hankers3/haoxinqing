<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebizs" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.aebiz.b2b2c.baseframework.servicelocator.ServiceLocator" %>
<%@page import="java.util.*" %>
<%@page import="com.aebiz.b2b2c.cms.contentcategory.service.impl.ContentCategoryInteractiveImpl" %>
<%@page import="com.aebiz.b2b2c.cms.contentcategory.vo.ContentCategoryModel" %>
<%@page import="com.aebiz.b2b2c.cms.content.vo.ContentModel" %>


<%
	ContentCategoryInteractiveImpl contentCategoryInteractive = (ContentCategoryInteractiveImpl)ServiceLocator.getInstance().getCtx().getBean("contentCategoryInteractiveImpl");
	List<Map.Entry<ContentCategoryModel, List<ContentModel>>> map =  contentCategoryInteractive.getContentCategoryByUuid("1");
%>
  

 <!--平台底部帮助中心组件-->
<div class="layout layout_auto grid_m0">
	<div class="DragContainer">
		<div class="DragBox">
			<!--底部帮助中心-->
			<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/decorate/platform_components/css/platHelpCenter/inner_footer.css"><!--底部帮助中心样式-->
			<div class="footer inner-footer">
			  <div class="container">
			  	<div class="y_fotcont">
			  	<c:forEach items="<%=map%>" var="cc">
			      <dl>
			        <dt><img src="${cc.key.iconPath}"/></dt>
			        <dd class="w_footerh">${cc.key.categoryName}</dd>
			        <c:if test="${!empty cc.value}"> 
						<c:forEach items="${cc.value}" var="content">
							<dd><a href="${pageContext.servletContext.contextPath}/frontshow/content/help/1/${content.uuid}">${content.contentTitle}</a></dd>
						</c:forEach>
				  	</c:if>
			      </dl>
			      </c:forEach>
			     
			      <dl>
			        <dt>优时代微信二维码</dt>
			        <dt class="w_footer_ewm"><img src="<aebizs:platConfig codeName="bottomAdPath"/>"></dt>
			      </dl>
			    </div>
			  </div><!-- End .container -->
			</div><!--底部帮助中心-->
		</div>	
	</div>
</div><!--平台底部帮助中心组件 end-->

	
<!--底部-->
<div class="footer footer-bottom">
  <div class="container">
    <aebizs:platConfig codeName='bottomInfo'/>
  </div><!-- End .container -->
</div><!-- End #footer-bottom -->
