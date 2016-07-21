<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.aebiz.b2b2c.baseframework.servicelocator.ServiceLocator" %>
<%@page import="java.util.*" %>
<%@page import="com.aebiz.b2b2c.cms.channel.service.impl.ChannelInteractiveServiceImpl" %>
<%@page import="com.aebiz.b2b2c.cms.channel.vo.ChannelModel" %>

<%
	ChannelInteractiveServiceImpl channelInteractive = (ChannelInteractiveServiceImpl)ServiceLocator.getInstance().getCtx().getBean("channelInteractiveServiceImpl");
	List<ChannelModel> channelList = channelInteractive.getChannelsByBelongType("1");
%>

<ul class="menu clearfix">
	<c:forEach items="<%=channelList%>" var="channel" varStatus="status">
		<c:choose>
			<c:when test="${status.index==0}"	 >
			<li class="y_active">	
			</c:when>
			<c:otherwise>
			<li>	
			</c:otherwise>
		</c:choose>
		<c:if test="${channel.type=='1'}">
			<a href="${pageContext.servletContext.contextPath}/toChannel/${channel.pageUuid}">${channel.channelName}</a></li>
		</c:if>
		<%-- <c:if test="${channel.type=='2'}">
			<a href="${channel.channelUrl}">${channel.channelName}</a></li>
		</c:if>  --%>
	</c:forEach>
</ul>

<script>
	$("ul li a").each(function(){
		$this = $(this);  
    if($this[0].href==String(window.location)){  
      $this.parent().addClass("y_active").siblings().removeClass("y_active");  
    }  
	});	
</script>
