<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImport.jsp" %>

</head>
   
<body class="forms-inplace-editing">
	<!-- WRAPPER -->
	<div class="wrapper">
		<!-- TOP BAR 头部导航 -->
		<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterHead.jsp" %>
		<!-- /top -->
		
		<!-- BOTTOM: LEFT NAV AND RIGHT MAIN CONTENT -->
		<div class="bottom">
			<div class="container">
				<div class="row">
					<!-- left sidebar 左侧导航 -->
					<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterLeft.jsp" %>
					<!-- end left sidebar -->

					<!-- content-wrapper -->
					<div class="col-xs-10 content-wrapper">
						
						<!-- main -->
						<div class="content">
						 	<div class="main-header">
								<h2><aebiz:showTitle titleId="withdrawapply.m.myVirtual"/></h2>
							</div>

							<div class="main-content">

								<div class="clearfix state-box integral"> <!--我的虚拟账户状态 star-->
									<div class="row nomargin">
										<div class="col-sm-9 col-li nopadding">
										  <ul>
										  	<li>总余额&nbsp;:<span class="rem-price"><i class="fa fa-rmb"></i>399.<b class="pr-lit">99</b></span></li>
												<li>可用余额&nbsp;:<span class="rem-price"><i class="fa fa-rmb"></i>399.<b class="pr-lit">99</b></span></li>
												<li>提现中金额&nbsp;:<span class="rem-price"><i class="fa fa-rmb"></i>399.<b class="pr-lit">99</b></span></li>
										  </ul>
										</div>
										<div class="col-sm-3 p-btn">
										   <p class="text-right viracc-btn"><a href="#" class="btn btn-primary" title="<aebiz:showTitle titleId="withdrawapply.m.payMsg"/>"><aebiz:showTitle titleId="withdrawapply.m.pay"/></a> <a href="${pageContext.servletContext.contextPath}/usercenter/withdrawapply/extractionMoney" class="btn btn-warning" title="<aebiz:showTitle titleId="withdrawapply.m.withdraMsg2"/>"><aebiz:showTitle titleId="withdrawapply.m.withdraTitle"/></a></p>
										</div>
								  </div>
								</div> <!--我的虚拟账户状态 end-->
								
								<div class="alert alert-success">
									<strong><i class="fa fa-exclamation"></i></strong><aebiz:showTitle titleId="withdrawapply.m.withdraMsg"/>
								</div>
								 
                <div class="disct-couB viracc-con">
                  <ul class="nav nav-tabs mb_20">
									 <li ><a data-toggle="tab" href="#"><i class="fa fa-list-alt"></i><aebiz:showTitle titleId="withdrawapply.m.detail"/></a></li>
									 <li><a data-toggle="tab" href="#"><i class="fa fa-indent"></i><aebiz:showTitle titleId="withdrawapply.m.paylog"/></a></li>
									 <li class="active"><a data-toggle="tab" href="#tixianjilu" id="tixijl"><i class="fa fa-dedent"></i><aebiz:showTitle titleId="withdrawapply.m.withdralog"/></a></li>
								  </ul>

									 <!-- 提现记录 -->
								  <div class="tab-content" id="tixianjilu">
								  	<form action="${pageContext.servletContext.contextPath}/usercenter/withdrawapply/queryWithdrawApply" method="post">
								  		<input type="hidden" name="pageShow" value="${wm.pageShow}">
									  	<div class="logi-nr">
											  <select class="select2" id="time" name="time">
														<option value=""><aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/></option>
														<c:forEach items="${timeList}" var="time">
															<option value="${time.value}" <c:if test='${qm.time == time.value }'>selected="selected"</c:if>>${time.name}</option>
														</c:forEach>
												</select>
												<button type="submit" value="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" class="btn btn-primary"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
											</div>
								  	
								     <!-- 提现列表 -->
								    <div>
									    <table class="table table-sorting table-striped table-hover" cellpadding="0" cellspacing="0" width="100%">
												<thead> 
													<tr>
													  <th><aebiz:showTitle titleId="withdrawapply.m.applyType"/></th>
													  <th><aebiz:showTitle titleId="withdrawapply.qm.applyMoney"/></th>
													  <th><aebiz:showTitle titleId="withdrawapply.m.Whereabouts"/></th>
													  <th><aebiz:showTitle titleId="withdrawapply.qm.state"/></th>
													  <th><aebiz:showTitle titleId="withdrawapply.qm.successTime"/></th>
													</tr>
												</thead>
												<tbody>
													 <c:forEach items="${list1 }" var="w" varStatus="c">
														 <tr>
														    <td>${w.applyTime }</td>
														    <td><span class="rem-price-noc"><i class="fa fa-rmb"></i>${w.applyMoney}</b></span></td>
														  	<td>
														   		<c:choose>
														   			<c:when test="${w.state == 0 }">
														   				<aebiz:showTitle titleId="withdrawapply.qm.untreated"/>
														   			</c:when>
														   			<c:when test="${w.state == 1 }">
														   				<aebiz:showTitle titleId="withdrawapply.qm.transfer"/>
														   			</c:when>
														   			<c:when test="${w.state == 2 }">
														   				${w.failReason }
														   			</c:when>
														   		</c:choose>
														    </td>
														    <td>
														   		<c:choose>
														   			<c:when test="${w.state == 0 }">
														   				<aebiz:showTitle titleId="withdrawapply.qm.untreated"/>
														   			</c:when>
														   			<c:when test="${w.state == 1 }">
														   				<aebiz:showTitle titleId="withdrawapply.qm.withdrawSuccess"/>
														   			</c:when>
														   			<c:when test="${w.state == 2 }">
														   				<aebiz:showTitle titleId="withdrawapply.qm.withdrawFail"/>
														   			</c:when>
														   		</c:choose>	
														    </td>
														    <td>${w.successTime }</td>
														  </tr>
													 </c:forEach>
												</tbody>
											</table>
											<div class="page-box clearfix">
												<!-- 分页开始 -->
													<aebiz:page listPath="usercenter/withdrawapply/userCenterWithdrawList"/>
												<!-- 分页结束 -->
											</div>
											</form>
								     </div>
								   </div>
                 </div>
							</div><!-- /main-content -->
						</div><!-- /main -->
					</div><!-- /content-wrapper -->
				</div><!-- /row -->
			</div><!-- /container -->
		</div>
		<!-- END BOTTOM: LEFT NAV AND RIGHT MAIN CONTENT -->
		<div class="push-sticky-footer"></div>
	</div><!-- /wrapper -->

	<!-- FOOTER 底部 -->
	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImportJs.jsp" %>
	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterBottom.jsp" %>
	<!-- END FOOTER -->

	<!-- STYLE SWITCHER -->
	<div class="del-style-switcher">
		<div class="del-switcher-toggle toggle-hide"></div>
		<form>
			<div class="del-section del-section-skin">
				<h5 class="del-switcher-header">Choose Skins:</h5>

				<ul>
					<li><a href="#" title="Slate Gray" class="switch-skin slategray" data-skin="css/skins/slategray.css">Slate Gray</a></li>
					<li><a href="#" title="Dark Blue" class="switch-skin darkblue" data-skin="css/skins/darkblue.css">Dark Blue</a></li>
					<li><a href="#" title="Dark Brown" class="switch-skin darkbrown" data-skin="css/skins/darkbrown.css">Dark Brown</a></li>
					<li><a href="#" title="Light Green" class="switch-skin lightgreen" data-skin="css/skins/lightgreen.css">Light Green</a></li>
					<li><a href="#" title="Orange" class="switch-skin orange" data-skin="css/skins/orange.css">Orange</a></li>
					<li><a href="#" title="Red" class="switch-skin red" data-skin="css/skins/red.css">Red</a></li>
					<li><a href="#" title="Teal" class="switch-skin teal" data-skin="css/skins/teal.css">Teal</a></li>
					<li><a href="#" title="Yellow" class="switch-skin yellow" data-skin="css/skins/yellow.css">Yellow</a></li>
				</ul>

				<button type="button" class="switch-skin-full fulldark" data-skin="css/skins/fulldark.css">Full Dark</button>
				<button type="button" class="switch-skin-full fullbright" data-skin="css/skins/fullbright.css">Full Bright</button>
			</div>
			
			<p><a href="#" title="Reset Style" class="del-reset-style">Reset Style</a></p>
		</form>
	</div>
	<!-- END STYLE SWITCHER -->

	<!-- Javascript -->
	<!-- 会员中心公用的js 5个-->
  <script src="${pageContext.servletContext.contextPath}/static/usercenter/js/jquery.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/bootstrap.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/modernizr.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/king-common.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/usercenter/demo-style-switcher/js/deliswitch.js"></script><!--页面右侧设置的js-->
	<!-- 当前页面使用的js-->
    	
</body>
</html>