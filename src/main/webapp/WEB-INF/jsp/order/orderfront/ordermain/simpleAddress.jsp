<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>                        
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=4YoG8bce6ygQp3lgXz9U7xaO"></script>    
<style>
.y_areashow{float:left;margin:0 -5px 0 15px;line-height:34px;}
.modal-dialog{width:670px;}
.y_setpassd{font-family:"Microsoft Yahei";}
.y_setpassd h3{font-weight:normal; font-size: 18px;}
.y_setpassd label{font-weight:normal;  }
.y_setpassd .form-control{width:90%;}
.y_setpassd .simple-checkbox input ,.simple-checkbox label{vertical-align: middle; margin:0px;}
.y_setpassd .btn{border-radius:3px;}
.tangram-suggestion-main{z-index:99999}
</style>
	<ul>
		<c:forEach items="${addressList }" var="address">
		<li><input type="radio" name="check_address" value="${address.uuid}"  <c:if test="${address.isDefault == '1'}">checked="checked"</c:if>  id="c3" class='icheck-me' name="same" data-skin="minimal"><label class='inline' for="c">
		<c:choose>
			<c:when test="${empty address.linkName }">${address.customerName }</c:when>
			<c:otherwise>${address.linkName }</c:otherwise>
		</c:choose>
		</label><b>|</b>${address.mobile }<b>|</b>${address.cityName }${address.region }${address.community }${address.address }
			<a href="javascript:removeAddress('${address.uuid }');" class="m_hide">删除</a>
			<a  href="#address-update" data-toggle="modal" onclick="javascript:toUpdateAddress('${address.uuid }');" class="m_hide">编辑</a>
			</li>
		</c:forEach>
	</ul>
