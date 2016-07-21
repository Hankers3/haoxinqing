<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<!--[if IE 9 ]><html class="ie ie9" lang="en" class="no-js"> <![endif]-->
<!--[if !(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<%@include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImport.jsp"%>

<!-- colorbox图片弹出框 -->
<link href="${pageContext.request.contextPath}/static/usercenter/css/colorbox.css" rel="stylesheet" type="text/css" />
</head>
<body class="forms-inplace-editing">
	<!-- WRAPPER -->
	<div class="wrapper">
		<!-- TOP BAR 头部导航 -->
		<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterHead.jsp"%>
		<!-- /top -->

		<!-- BOTTOM: LEFT NAV AND RIGHT MAIN CONTENT -->
		<div class="bottom">
			<div class="container">
				<div class="row">
					<!-- left sidebar 左侧导航 -->
					<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterLeft.jsp"%>
					<!-- end left sidebar -->


					<!-- content-wrapper -->
					<div class="col-xs-10 content-wrapper">
						<div class="row">
							<div class="col-sm-4 ">
								<ul class="breadcrumb">
									<li><i class="fa fa-home"></i><a href="${pageContext.servletContext.contextPath}/customer/toIndex"><aebiz:showTitle titleId="messageremind.title.usercenter" /></a></li>
									<li class="active"><aebiz:showTitle titleId="messageremind.menuOne" /></li>
								</ul>
							</div>
							<div class="col-sm-8 ">
								<div class="top-content">
									<ul class="list-inline quick-access">
										<li><a href="charts-statistics-interactive.html"> <span class="quick-access-item bg-color-green"> <i class="fa fa-bar-chart-o"></i>
													<h5>CHARTS</h5> <em>basic, interactive, real-time</em>
											</span>
										</a></li>
										<li><a href="page-inbox.html"> <span class="quick-access-item bg-color-blue"> <i class="fa fa-envelope"></i>
													<h5>INBOX</h5> <em>inbox with gmail style</em>
											</span>
										</a></li>
										<li><a href="tables-dynamic-table.html"> <span class="quick-access-item bg-color-orange"> <i class="fa fa-table"></i>
													<h5>DYNAMIC TABLE</h5> <em>tons of features and interactivity</em>
											</span>
										</a></li>
									</ul>
								</div>
							</div>
						</div>

						<!-- main -->
						<div class="content">
							<div class="main-header">
								<h2>网站提醒</h2>
							</div>

							<div class="main-content w_alert-style">
								<div class="alert alert-warning news-warn">
									<p class="tit">
										<i class="fa fa-info-circle"></i>为了您能准确的接收到提醒信息，请确认您的信息是否正确，是否需要修改？
									</p>
									<p>手机号码：<strong>2132165496749</strong> <a href="#">修改手机</a></p>
									<p>邮箱地址：<strong>1965465487@qq.com</strong> <a href="#">修改邮箱</a></p>
								</div>
								<div class="news-content">
									<div class="table-responsive2">
										<form id="mainForm" action="${pageContext.servletContext.contextPath}/usercenter/remindsub/saveSet" method="post">
											<table class="table table-hover my-scribe">
												<thead>
													<tr>
														<th width="70%"><aebiz:showTitle titleId="messageremind.message.messageContent" /></th>
														<th width="10%"><aebiz:showTitle titleId="messageremind.message.mobile" /></th>
														<th width="10%"><aebiz:showTitle titleId="messageremind.message.email" /></th>
														<th width="10%"><aebiz:showTitle titleId="messageremind.message.innerMessage" /></th>
													</tr>
												</thead>
												<tbody>
													<!-- 复选框不可点击的状态 只要在 input 上添加 disabled 就可以了。其中 id="checkbox2"和for="checkbox2" 数字是一样的 且是唯一的。如果有重复 则其中一项无法点击。-->
													<tr><td colspan="4" class="tr-tit"><aebiz:showTitle titleId="messageremind.messageType.buyerremind" /></td></tr>
													<c:forEach items="${wm.customerMessageReminds}" var="allMessageRemind">
														<tr>
															<td class="news-text"><span class="imp-icon">*</span>${allMessageRemind.remindName}</td>
															<td>
																<div class="simple-checkbox">
																	<input type="hidden" id="${allMessageRemind.messageUuid}0" value="${allMessageRemind.messageUuid}" name="messageUuid">
																	<input type="hidden" id="${allMessageRemind.messageUuid}1" value="${allMessageRemind.remindSubUuid}" name="redmindSubUuid${allMessageRemind.messageUuid}">
																	<input type="checkbox" id="${allMessageRemind.messageUuid}2" name="mobileSend${allMessageRemind.messageUuid}" <c:if test="${allMessageRemind.mobileSend == '1'}"> checked="checked" </c:if> value="1" ><label for="${allMessageRemind.messageUuid}2"></label>
																</div>
															</td>
															<td>
																<div class="simple-checkbox">
																	<input type="checkbox" id="${allMessageRemind.messageUuid}3" name="emailSend${allMessageRemind.messageUuid}" <c:if test="${allMessageRemind.emailSend == '1'}"> checked="checked" </c:if> value="1" ><label for="${allMessageRemind.messageUuid}3"></label>
																</div>
															</td>
															<td>
																<div class="simple-checkbox">
																	<input type="checkbox" id="${allMessageRemind.messageUuid}4" name="innerSend${allMessageRemind.messageUuid}" <c:if test="${allMessageRemind.innerSend == '1'}"> checked="checked" </c:if> value="1" ><label for="${allMessageRemind.messageUuid}4"></label>
																</div>
															</td>
														</tr>
													</c:forEach>
													<tr>
														<td class="btn-submit" colspan="4">
															<button class="btn btn-primary btn-warn" id="submit"><aebiz:showTitle titleId="messageremind.message.yes" /></button>
															<button class="btn btn-primary"><aebiz:showTitle titleId="messageremind.message.returnDefault" /></button>
														</td>
													</tr>
												</tbody>
											</table>
										</form>
									</div>
								</div>
							</div>
						</div>
						<!-- /main-content -->
					</div>
					<!-- /main -->
				</div>
				<!-- /content-wrapper -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- END BOTTOM: LEFT NAV AND RIGHT MAIN CONTENT -->
	<div class="push-sticky-footer"></div>
	</div>
	<!-- /wrapper -->

	<%@include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImportJs.jsp"%>
	<%@include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterBottom.jsp"%>
	
	<script src="${pageContext.request.contextPath }/static/usercenter/js/jquery.colorbox-min.js"></script>
	<script src="${pageContext.request.contextPath }/static/usercenter/js/ajaxfileupload.js"></script>
	<script>
		$("#submit").click(function(){
			$("#mainForm").submit();
			window.location.reload();
			});	
	</script>
</body>
</html>
