<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="ff row bask-img-container" id="show${nowCount}">
	<div class="col-sm-2 text-right"><aebiz:showTitle titleId="ordershow.m.productPic"/>${nowCount+1}:</div>
		<div class="col-sm-3">
				<input type="hidden" name="picName${nowCount}" value="${picName}"/>
				<input type="hidden" name="picUrl${nowCount}" value="${remotePath}"/>
				<input type="hidden" class="frontCover-mark" name="frontCover${nowCount}" <c:if test="${nowCount==0}">value="1"</c:if><c:if test="${nowCount>0}">value="0"</c:if> >
				<div class="bask-img">
					<img src="${remotePath}"/>
				</div>
		</div>
		<div class="col-sm-7 editor-img">
			<div class="zassesment-text">
				<div class="front-cover">
					<div class="simple-radio simple-radio-inline radio-green">
						<input id="frontCover${nowCount}" type="radio" name="frontCover" <c:if test="${nowCount==0}">checked</c:if>>
						<label for="frontCover${nowCount}"><aebiz:showTitle titleId="ordershow.m.setupCover"/></label>
					</div>
				</div>
				<p class="text-left"><aebiz:showTitle titleId="ordershow.m.editPicExplain"/></p>
				<textarea name="picDesc${nowCount}" maxlength="100" cols="110" rows="2" class="form-control" id="textarea" data-rule-required="true" data-rule-minlength="10" data-rule-maxlength="100"></textarea>
				<p class="text-right"><span class="text-muted"><aebiz:showTitle titleId="ordershow.m.maxInput100"/></span></p>
				<p class="file-image">
					<button type="button" class="btn btn-deflaut btn-xs btn-delete" onclick="javascript:removeShow(${nowCount})"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></button>
				</p>
			</div>
		</div>
	</div>
</div>
