<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp"%>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/storeback/css/plugins/bootstrap-switch/bootstrap-switch.min.css">
<!-- bootstrap-switch js-->
<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/bootstrap-switch/bootstrap-switch.min.js "></script>
<!-- bootstrap-switch js调用 -->
<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<!-- validation 调用 -->
<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/aebiz/aebiz.validate.js"></script>
<link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/static/storeback/img/favicon.ico" />
<!-- Apple devices Homescreen icon -->
<link rel="apple-touch-icon-precomposed" href="${pageContext.servletContext.contextPath}/static/storeback/img/apple-touch-icon-precomposed.png" />

</head>
<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>
					<aebiz:showTitle titleId="messageremind.moduleName_CN" />
					<aebiz:showTitle titleId="basebusiness.showmessage.manager" />
				</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="messageremind.menuOne" /></span> <i class="fa fa-angle-right"></i>
				</li>
				<li><span><aebiz:showTitle titleId="messageremind.menuTwo" /></span> <i class="fa fa-angle-right"></i>
				</li>
				<li><span><aebiz:showTitle titleId="messageremind.moduleName_CN" /> <aebiz:showTitle titleId="basebusiness.showmessage.manager" /></span></li>
			</ul>
		</div>
		<div class="row">
			<div class="col-sm-12 newstips">
				<div class="alert alert-warning">
					<span class="fa-icon font-red"> <i class="fa fa-info-circle"></i>
					</span>
					<div class="alertnews">
						<p>为了您能准确的接收到提醒信息，请确认您的信息是否正确，是否需要修改？</p>
						<div>
							<p>手机号码：<strong>2132165496749</strong> <a href="#">修改手机</a></p>
							<p>邮箱地址：<strong>1965465487@qq.com</strong> <a href="#">修改邮箱</a></p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="newstips">
			<table class="table table-hover table-nomargin table-bordered" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th><aebiz:showTitle titleId="messageremind.message.messageContent" /></th>
						<th><aebiz:showTitle titleId="messageremind.message.mobile" /></th>
						<th><aebiz:showTitle titleId="messageremind.message.email" /></th>
						<th><aebiz:showTitle titleId="messageremind.message.innerMessage" /></th>
					</tr>
				</thead>
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/storeback/remindsub/saveSet" method="post">
					<tbody>
						<tr>
							<th colspan="4"><aebiz:showTitle titleId="messageremind.messageType.sellerremind" /></th>
						</tr>
						<c:forEach items="${wm.map['seller']}" var="messageRemind">
							<tr>
								<td class="news-text"><span class="imp-icon">*</span>${messageRemind.remindName}</td>
								<td>
									<div class="simple-checkbox">
										<input type="hidden" id="${messageRemind.messageUuid}0" value="${messageRemind.messageUuid}" name="messageUuid">
										<input type="hidden" id="${messageRemind.messageUuid}1" value="${messageRemind.remindSubUuid}" name="redmindSubUuid${messageRemind.messageUuid}"> 
										<input type="checkbox" id="${messageRemind.messageUuid}2" name="mobileSend${messageRemind.messageUuid}"
											<c:if test="${messageRemind.mobileSend == '1'}"> checked="checked" </c:if> value="1" >
											<label for="${allMessageRemind.messageUuid}2"></label>
									</div>
								</td>
								<td>
									<div class="simple-checkbox">
										<input type="checkbox" id="${messageRemind.messageUuid}3" name="emailSend${messageRemind.messageUuid}"
											<c:if test="${messageRemind.emailSend == '1'}"> checked="checked" </c:if> value="1">
											<label for="${allMessageRemind.messageUuid}3"></label>
									</div>
								</td>
								<td>
									<div class="simple-checkbox">
										<input type="checkbox" id="${messageRemind.messageUuid}4" name="innerSend${messageRemind.messageUuid}"
											<c:if test="${messageRemind.innerSend == '1'}"> checked="checked" </c:if> value="1">
											<label for="${allMessageRemind.messageUuid}4"></label>
									</div>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<th colspan="4"><aebiz:showTitle titleId="messageremind.messageType.refundremind" /></th>
						</tr>
						<c:forEach items="${wm.map['refund']}" var="messageRemind">
							<tr>
								<td class="news-text"><span class="imp-icon">*</span>${messageRemind.remindName}</td>
								<td>
									<div class="simple-checkbox">
										<input type="hidden" id="${messageRemind.messageUuid}0" value="${messageRemind.messageUuid}" name="messageUuid">
										<input type="hidden" id="${messageRemind.messageUuid}1" value="${messageRemind.remindSubUuid}" name="redmindSubUuid${messageRemind.messageUuid}"> 
										<input type="checkbox" id="${messageRemind.messageUuid}2" name="mobileSend${messageRemind.messageUuid}"
											<c:if test="${messageRemind.mobileSend == '1'}"> checked="checked" </c:if> value="1" >
											<label for="${allMessageRemind.messageUuid}2"></label>
									</div>
								</td>
								<td>
									<div class="simple-checkbox">
										<input type="checkbox" id="${messageRemind.messageUuid}3" name="emailSend${messageRemind.messageUuid}"
											<c:if test="${messageRemind.emailSend == '1'}"> checked="checked" </c:if> value="1">
											<label for="${allMessageRemind.messageUuid}3"></label>
									</div>
								</td>
								<td>
									<div class="simple-checkbox">
										<input type="checkbox" id="${messageRemind.messageUuid}4" name="innerSend${messageRemind.messageUuid}"
											<c:if test="${messageRemind.innerSend == '1'}"> checked="checked" </c:if> value="1">
											<label for="${allMessageRemind.messageUuid}4"></label>
									</div>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<th colspan="4"><aebiz:showTitle titleId="messageremind.messageType.promotionremind" /></th>
						</tr>
						<c:forEach items="${wm.map['promotion']}" var="messageRemind">
							<tr>
								<td class="news-text"><span class="imp-icon">*</span>${messageRemind.remindName}</td>
								<td>
									<div class="simple-checkbox">
										<input type="hidden" id="${messageRemind.messageUuid}0" value="${messageRemind.messageUuid}" name="messageUuid">
										<input type="hidden" id="${messageRemind.messageUuid}1" value="${messageRemind.remindSubUuid}" name="redmindSubUuid${messageRemind.messageUuid}"> 
										<input type="checkbox" id="${messageRemind.messageUuid}2" name="mobileSend${messageRemind.messageUuid}"
											<c:if test="${messageRemind.mobileSend == '1'}"> checked="checked" </c:if> value="1" >
											<label for="${allMessageRemind.messageUuid}2"></label>
									</div>
								</td>
								<td>
									<div class="simple-checkbox">
										<input type="checkbox" id="${messageRemind.messageUuid}3" name="emailSend${messageRemind.messageUuid}"
											<c:if test="${messageRemind.emailSend == '1'}"> checked="checked" </c:if> value="1">
											<label for="${allMessageRemind.messageUuid}3"></label>
									</div>
								</td>
								<td>
									<div class="simple-checkbox">
										<input type="checkbox" id="${messageRemind.messageUuid}4" name="innerSend${messageRemind.messageUuid}"
											<c:if test="${messageRemind.innerSend == '1'}"> checked="checked" </c:if> value="1">
											<label for="${allMessageRemind.messageUuid}4"></label>
									</div>
								</td>
							</tr>
						</c:forEach>
						<tr class="last-tr">
							<td colspan="4">
								<button id="submit" class="btn btn-primary btn-warn"><aebiz:showTitle titleId="messageremind.message.yes" /></button>
								<button type="button" class="btn btn-primary btn-lg"><aebiz:showTitle titleId="messageremind.message.returnDefault" /></button>
							</td>
						</tr>
					</tbody>
				</form>
			</table>
		</div>
	</div>
</body>
</html>
<script>
	$("#submit").click(function() {
		$("#mainForm").submit();
	});
</script>