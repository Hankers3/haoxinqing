<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="store-assesment-content">
	<div class="store-ff">
		<div class="ff grad">
			<span class="ff-text"><aebiz:showTitle titleId="productappraise.m.storeScore"/>:</span>
			<ul class="rating-level">
				<li><a <c:if test="${oam.storeScore==1}">class="actived1"</c:if> href="#">1</a></li>
				<li><a <c:if test="${oam.storeScore==2}">class="actived2"</c:if> href="#">2</a></li>
				<li><a <c:if test="${oam.storeScore==3}">class="actived3"</c:if> href="#">3</a></li>
				<li><a <c:if test="${oam.storeScore==4}">class="actived4"</c:if> href="#">4</a></li>
				<li><a <c:if test="${oam.storeScore==5}">class="actived5"</c:if> href="#">5</a></li>
			</ul>
		</div>
		<div class="ff grad">
			<span class="ff-text"><aebiz:showTitle titleId="productappraise.m.speedScore"/>:</span>
			<ul class="rating-level">
				<li><a <c:if test="${oam.speedScore==1}">class="actived1"</c:if> href="#">1</a></li>
				<li><a <c:if test="${oam.speedScore==2}">class="actived2"</c:if> href="#">2</a></li>
				<li><a <c:if test="${oam.speedScore==3}">class="actived3"</c:if> href="#">3</a></li>
				<li><a <c:if test="${oam.speedScore==4}">class="actived4"</c:if> href="#">4</a></li>
				<li><a <c:if test="${oam.speedScore==5}">class="actived5"</c:if> href="#">5</a></li>
			</ul>
		</div>
	</div>
	<div  class="store-ff">
		<div class="ff grad">
			<span class="ff-text"><aebiz:showTitle titleId="productappraise.m.descScore"/>:</span>
			<ul class="rating-level">
				<li><a <c:if test="${oam.descScore==1}">class="actived1"</c:if> href="#">1</a></li>
				<li><a <c:if test="${oam.descScore==2}">class="actived2"</c:if> href="#">2</a></li>
				<li><a <c:if test="${oam.descScore==3}">class="actived3"</c:if> href="#">3</a></li>
				<li><a <c:if test="${oam.descScore==4}">class="actived4"</c:if> href="#">4</a></li>
				<li><a <c:if test="${oam.descScore==5}">class="actived5"</c:if> href="#">5</a></li>
			</ul>
		</div>
		<div class="ff grad">
			<span class="ff-text"><aebiz:showTitle titleId="productappraise.m.serviceScore"/>:</span>
			<ul class="rating-level">
				<li><a <c:if test="${oam.serviceScore==1}">class="actived1"</c:if> href="#">1</a></li>
				<li><a <c:if test="${oam.serviceScore==2}">class="actived2"</c:if> href="#">2</a></li>
				<li><a <c:if test="${oam.serviceScore==3}">class="actived3"</c:if> href="#">3</a></li>
				<li><a <c:if test="${oam.serviceScore==4}">class="actived4"</c:if> href="#">4</a></li>
				<li><a <c:if test="${oam.serviceScore==5}">class="actived5"</c:if> href="#">5</a></li>
			</ul>
		</div>
	</div>
	<div class="assesment-store-result">
		<p class="reslut-words">${oam.content}</p>
		<p class="write-time text-right">${oam.appraiseTime}</p>
	</div>
</div><!--  end store-assesment-content -->