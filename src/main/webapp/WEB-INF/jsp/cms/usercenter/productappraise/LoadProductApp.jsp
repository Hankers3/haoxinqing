<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="assesment-content">
	<div class="assesment-innerbox clearfix">
		<div class="assesment-word">
			<div class="custom-me">
				<div class="title-photo">
					<a href="#"><img src="${customerImage}"/></a>
					<a href="#" class="custom-name">${m.customerName}</a>
				</div>
				<div class="assesment-word">
					<div class="grad-result">
						<span class="result-text"><aebiz:showTitle titleId="productappraise.m.appScore"/>:</span>
						<ul class="rating-level">
							<li><a <c:if test="${m.appScore==1}">class="actived1"</c:if> href="#">1</a></li>
							<li><a <c:if test="${m.appScore==2}">class="actived2"</c:if> href="#">2</a></li>
							<li><a <c:if test="${m.appScore==3}">class="actived3"</c:if> href="#">3</a></li>
							<li><a <c:if test="${m.appScore==4}">class="actived4"</c:if> href="#">4</a></li>
							<li><a <c:if test="${m.appScore==5}">class="actived5"</c:if> href="#">5</a></li>
						</ul>	
					</div>
					<c:if test="${m.appTag != ''}">
						<div class="label-result">
						   <span class="result-text"><aebiz:showTitle titleId="productappraise.m.appTag"/>:</span>
						   <div class="label-container btn-group">
							   <c:forEach items="${m.tags}" var="tag" >	
						   	 		<button class="btn btn-success btn-xs" type="button">${tag}</button>
						   	 </c:forEach>
						   </div>
						</div>
					</c:if>		
					<div class="text-reslut">
						<p>${m.appContent}</p>
						<p class="write-time">${m.appTime}</p>
					</div>
				</div>
			</div>	<!-- end custom-me -->
		</div>
	</div>
</div>
