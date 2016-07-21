<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
									<li><i class="fa fa-home"></i><a href="#"><aebiz:showTitle titleId="vipclubintegrallog.usercenter.firstPage"/></a></li>
									<li><a href="#"><aebiz:showTitle titleId="vipclubintegrallog.usercenter.manageAccount"/></a></li>
									<li class="active"><aebiz:showTitle titleId="vipclubintegrallog.usercenter.myIntegral"/></li>
								</ul>
							</div>
							<div class="col-sm-8 ">
								<div class="top-content">
									<ul class="list-inline quick-access">
										<li><a href="charts-statistics-interactive.html"> <span
												class="quick-access-item bg-color-green"> <i
													class="fa fa-bar-chart-o"></i>
													<h5>CHARTS</h5> <em>basic, interactive, real-time</em>
											</span>
										</a></li>
										<li><a href="page-inbox.html"> <span
												class="quick-access-item bg-color-blue"> <i
													class="fa fa-envelope"></i>
													<h5>INBOX</h5> <em>inbox with gmail style</em>
											</span>
										</a></li>
										<li><a href="tables-dynamic-table.html"> <span
												class="quick-access-item bg-color-orange"> <i
													class="fa fa-table"></i>
													<h5>DYNAMIC TABLE</h5> <em>tons of features and
														interactivity</em>
											</span>
										</a></li>
									</ul>
								</div>
							</div>
						</div>

						<!-- main -->
						<div class="content">
							<div class="main-header">
								<h2><aebiz:showTitle titleId="vipclubintegrallog.usercenter.myIntegral"/></h2>
							</div>

							<div class="main-content">
								<form class="form-inline2" id="searchForm" action="${pageContext.servletContext.contextPath}/usercenter/vipclubintegrallog/query" method="post">
									<div class="clearfix state-box integral">
										<!--我的积分状态 star-->
										<div class="row nomargin">
											<div class="col-mi-12 col-sm-9 col-li nopadding">
												<ul>
													<li><aebiz:showTitle titleId="vipclubintegrallog.usercenter.usableIntegral"/>&nbsp;&nbsp;:<span class="fa-color1">${integralStatModel.intergralCount}</span></li>
													<li><aebiz:showTitle titleId="vipclubintegrallog.usercenter.overTimingIntegral"/>&nbsp;&nbsp;:<span class="fa-color1">159</span></li>
													<li><aebiz:showTitle titleId="vipclubintegrallog.usercenter.cooledIntegral"/>&nbsp;&nbsp;:<span class="fa-color2">159</span></li>
												</ul>
											</div>
											<div class="col-mi-8 col-sm-3 p-btn">
												<p class="text-right viracc-btn">
													<a href="#" class="btn btn-warning" title=""><aebiz:showTitle titleId="vipclubintegrallog.usercenter.messageOne"/></a>
												</p>
											</div>
										</div>
									</div>
									<!--我的积分状态 end-->
									<div class="alert alert-success text-right">
										<aebiz:showTitle titleId="vipclubintegrallog.usercenter.messageTwo"/> <a href="#"><aebiz:showTitle titleId="vipclubintegrallog.usercenter.messageThree"/></a>
									</div>

									<div class="disct-couB integral-con">
										<ul class="nav nav-tabs mb_20">
											<li
												<c:if test="${qm.integrals == null || qm.integrals == ''}"> class="active" </c:if>><a data-toggle="tab" href="#jifenmingxi" class="integralDetail">
													<i class="fa fa-list-alt"></i><aebiz:showTitle titleId="vipclubintegrallog.usercenter.datailIntegral"/></a></li>
											<input type="hidden" name="pageShow" value="${wm.pageShow}">
											<li 
												<c:if test="${qm.integrals == 'add'}"> class="active" </c:if>><a data-toggle="tab" href="#jifenshouru" class="integralIncome">
													<i class="fa fa-indent"></i><aebiz:showTitle titleId="vipclubintegrallog.usercenter.incomeIntegral"/></a></li>
											<input type="hidden" name="integrals" id="integrals" value="${qm.integrals}">
											<li
												<c:if test="${qm.integrals == 'reduce'}"> class="active" </c:if>><a data-toggle="tab" href="#jifenzhichu" class="integralExpand">
													<i class="fa fa-dedent"></i><aebiz:showTitle titleId="vipclubintegrallog.usercenter.expandIntegral"/></a></li>
											<li><a data-toggle="tab" href="#guoqijifen"><i class="fa fa-inbox"></i><aebiz:showTitle titleId="vipclubintegrallog.usercenter.overtimedIntegral"/></a></li>
											<li><a data-toggle="tab" href="#dongjiejifen"><i class="fa fa-lock"></i><aebiz:showTitle titleId="vipclubintegrallog.usercenter.cooledIntegral"/></a></li>
										</ul>

										<div class="logi-text">选择时间</div>
										<div class="logi-nr">
											<select class="select2" id="queryTime" name="queryTimeType">
												<option value="1" <c:if test="${'1' eq qm.queryTimeType}"> selected='true' </c:if>><aebiz:showTitle titleId="vipclubintegrallog.queryTime.in_three_month"/></option>
												<option value="2" <c:if test="${'2' eq qm.queryTimeType}"> selected='true' </c:if>><aebiz:showTitle titleId="vipclubintegrallog.queryTime.out_three_month"/></option>
												<option value="3" <c:if test="${'3' eq qm.queryTimeType}"> selected='true' </c:if>><aebiz:showTitle titleId="vipclubintegrallog.queryTime.in_one_year"/></option>
												<option value=""  <c:if test="${'' eq qm.queryTimeType || qm.queryTimeType == null }" > selected='true' </c:if>><aebiz:showTitle titleId="vipclubintegrallog.queryTime.all"/></option>
											</select>
										</div>

										<div class="tab-content inte-content">
											<div id="jifenmingxi" class="tab-pane jfmx active">
												<table class="table table-sorting table-striped table-hover" cellpadding="0" cellspacing="0" width="100%">
													<thead>
														<tr>
															<th><aebiz:showTitle titleId="vipclubintegrallog.usercenter.comeAndUse"/></th>
															<c:if test="${qm.integrals == '' || qm.integrals == null}">
																<th><aebiz:showTitle titleId="vipclubintegrallog.usercenter.integralChange"/></th>
															</c:if>
															<c:if test="${qm.integrals == 'add'}">
																<th><aebiz:showTitle titleId="vipclubintegrallog.usercenter.integralIncome"/></th>
															</c:if>
															<c:if test="${qm.integrals == 'reduce'}">
																<th><aebiz:showTitle titleId="vipclubintegrallog.usercenter.integralExpand"/></th>
															</c:if>
															<th><aebiz:showTitle titleId="vipclubintegrallog.usercenter.dataTime"/></th>
															<th><aebiz:showTitle titleId="vipclubintegrallog.usercenter.remark"/></th>
															<th><aebiz:showTitle titleId="vipclubintegrallog.usercenter.number"/></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${wm.rows}" var="vipclubIntegrallog">
															<tr>
																<td width="28%">
																	<dl class="w_inte-item">
																		<dt>
																			<a href="#"><img src="img/aebiz/pro-list-01.jpg" /></a>
																		</dt>
																		<dd>
																			<a href="#">${vipclubIntegrallog.productUuid}</a>
																		</dd>
																	</dl>
																</td>
																<td class="w_szr">
																	<c:if test="${vipclubIntegrallog.intergralType =='11' || vipclubIntegrallog.intergralType =='12' || vipclubIntegrallog.intergralType =='13' || vipclubIntegrallog.intergralType =='14'}">
																		<i class="fa-color1">+</i>
																	</c:if> 
																	<c:if test="${vipclubIntegrallog.intergralType =='21' || vipclubIntegrallog.intergralType =='22' || vipclubIntegrallog.intergralType =='23'}">
																		<i class="fa-color2">-</i>
																	</c:if> 
																	<span  	
																		<c:if test="${vipclubIntegrallog.intergralType =='11' || vipclubIntegrallog.intergralType =='12' || vipclubIntegrallog.intergralType =='13' || vipclubIntegrallog.intergralType =='14'}"> class="fa-color1" </c:if> 
																		<c:if test="${vipclubIntegrallog.intergralType =='21' || vipclubIntegrallog.intergralType =='22' || vipclubIntegrallog.intergralType =='23'}"> class="fa-color2" </c:if> >
																		${vipclubIntegrallog.intergralCount}
																	</span>
																</td>
																<td>${vipclubIntegrallog.createTime}</td>
																<td>${vipclubIntegrallog.description}</td>
																<td>${vipclubIntegrallog.productUuid}</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
												<c:if test="${empty wm.rows}">
								       	如果没有任何记录的情况下 使用
										   		<div class="alert alert-warning">
														您现在还没有积分 <a class="btn btn-warning" href="#">马上兑换积分</a>
													</div>
												</c:if>
												<c:if test="${qm.integrals == 'add'}">
													<div id="jifenshouru" class="tab-pane active">
														<div class="tab-botm">
															<p><aebiz:showTitle titleId="vipclubintegrallog.usercenter.totalIncome"/>： <span class="rem-price-noc fa-color1"><i class="fa fa-rmb"></i> ${totalIntegral}</span></p>
														</div>
													</div>
												</c:if>
												<c:if test="${qm.integrals == 'reduce'}">
													<div id="jifenzhichu" class="tab-pane active">
														<div class="tab-botm">
															<p><aebiz:showTitle titleId="vipclubintegrallog.usercenter.totalExpand"/>： <span class="rem-price-noc fa-color1"><i class="fa fa-rmb"></i>${totalIntegral}</span></p>
														</div>
													</div>
												</c:if>
												<!--过期积分温馨提示
												<div id="guoqijifen" class="tab-pane">
													<div class="alert alert-warning">
														<strong><aebiz:showTitle titleId="vipclubintegrallog.usercenter.warnTip"/></strong><br />
														<ol>
															<li>1. <aebiz:showTitle titleId="vipclubintegrallog.usercenter.overtimeRuleOne"/></li>
															<li>2. <aebiz:showTitle titleId="vipclubintegrallog.usercenter.overtimeRuleTwo"/></li>
														</ol>
													</div>
												</div>
												-->
												<!--冻结积分温馨提示
												<div class="alert alert-warning">
													<strong><aebiz:showTitle titleId="vipclubintegrallog.usercenter.warnTip"/></strong><br />
													<ol>
														<li>1. <aebiz:showTitle titleId="vipclubintegrallog.usercenter.whyCooled"/></li>
														<li>2. <aebiz:showTitle titleId="vipclubintegrallog.usercenter.howCooled"/></li>
													</ol>
												</div>
												-->
											</div>
										</div>
										<aebiz:page listPath="usercenter/vipclubintegrallog/toList" />
										<div class="widget serve-widget">
											<!--积分常见问题 star-->
											<div class="widget-header">
												<h3><aebiz:showTitle titleId="vipclubintegrallog.usercenter.usualProblems"/></h3>
											</div>
											<div class="widget-content clearfix">
												<ul class="integral-help">
													<li><a href="#">1. <aebiz:showTitle titleId="vipclubintegrallog.usercenter.ProblemOne"/></a></li>
													<li><a href="#">2. <aebiz:showTitle titleId="vipclubintegrallog.usercenter.ProblemTwo"/></a></li>
													<li><a href="#">3. <aebiz:showTitle titleId="vipclubintegrallog.usercenter.ProblemThree"/></a></li>
													<li><a href="#">4. <aebiz:showTitle titleId="vipclubintegrallog.usercenter.ProblemFour"/></a></li>
													<li><a href="#">5. <aebiz:showTitle titleId="vipclubintegrallog.usercenter.ProblemFive"/></a></li>
												</ul>
											</div>
										</div>
									</div>
									<!--积分常见问题 end-->
							</div>
							</form>
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
		$(function() {
			$(".integralIncome").click(function() {
				$("#integrals").val("add");
				$("#searchForm").submit();
			});

			$(".integralExpand").click(function() {
				$("#integrals").val("reduce");
				$("#searchForm").submit();
			});

			$(".integralDetail").click(function() {
				$("#integrals").val("");
				$("#searchForm").submit();
			});
			$("#queryTime").change(function(){
				$("#searchForm").submit();
			});	
		});
	</script>
</body>
</html>
