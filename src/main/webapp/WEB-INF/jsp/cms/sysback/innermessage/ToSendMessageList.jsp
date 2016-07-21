<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<form class="form-inline2" id="searchForm2" action="${pageContext.servletContext.contextPath}/sysback/order/updateStaff/" method="post">
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="user-infos">推送消息</h3>
		</div>
		<div class="modal-body">
			<div class="row">
			<div class="col-sm-12">
				<div class="box">	
					<div class="y_clear">
						<div class="form-inline table_formnew">
							<div class="form-group">	
								<label class="control-label">选择家政员等级：</label>									
								<select name="staffLevel" id="levelId" class='form-control'>
									<option value="all">--<aebiz:showTitle titleId="basebusiness.showmessage.all" />--</option>
									<c:forEach items="${levelList}" var="type">
										<option value="${type.uuid }" >${type.levelName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
		<div class="modal-footer">
			<button class="btn moresearch" data-dismiss="modal">关闭</button>
			<a class="btn btn-primary"  onclick="javascript:bgToPushInner('${innerUuid}');"  >发送站内信</a>
		</div>
	</div>
</div>
</form>