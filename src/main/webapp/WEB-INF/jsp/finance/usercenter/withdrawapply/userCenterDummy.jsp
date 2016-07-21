<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

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
												<li>提现金额&nbsp;:<span class="rem-price"><i class="fa fa-rmb"></i>399.<b class="pr-lit">99</b></span></li>
										  </ul>
										</div>
										<div class="col-sm-3 p-btn">
										   <p class="text-right viracc-btn">
										   		<a href="#" class="btn btn-primary" title="<aebiz:showTitle titleId="withdrawapply.m.payMsg"/>">
										   			<aebiz:showTitle titleId="withdrawapply.m.pay"/>
										   		</a> 
										   		<a href="${pageContext.servletContext.contextPath}/usercenter/withdrawapply/extractionMoney" class="btn btn-warning" title="把账户余额中的现金提取出来！">
										   			<aebiz:showTitle titleId="withdrawapply.m.withdraTitle"/>
										   		</a>
										   	</p>
										</div>
								 </div>
								</div> <!--我的虚拟账户状态 end-->
								
								<div class="alert alert-success">
									<strong><i class="fa fa-exclamation"></i></strong><aebiz:showTitle titleId="withdrawapply.m.withdraMsg"/>
								</div>
								 
                 <div class="disct-couB viracc-con">
                   <ul class="nav nav-tabs mb_20">
									  <li class="active"><a data-toggle="tab" href="#shouzhimingxi"><i class="fa fa-list-alt"></i><aebiz:showTitle titleId="withdrawapply.m.detail"/></a></li>
									  <li><a data-toggle="tab" href="#chongzhijilu"><i class="fa fa-indent"></i><aebiz:showTitle titleId="withdrawapply.m.paylog"/></a></li>
									  <li><a href="${pageContext.servletContext.contextPath}/usercenter/withdrawapply/userCenterWithdrawList/1/5?init=true" id="tixijl"><i class="fa fa-dedent"></i><aebiz:showTitle titleId="withdrawapply.m.withdralog"/></a></li>
								   </ul>

								   <div class="logi-nr">
									   <select class="select2" id="select2" name="select2">
												<option value=""><aebiz:showTitle titleId="withdrawapply.m.all"/></option>
												<option value="1" <c:if test='${qm.time == 1 }'>selected="selected"</c:if>><aebiz:showTitle titleId="withdrawapply.m.trimesterin"/></option>
												<option value="2" <c:if test='${qm.time == 2 }'>selected="selected"</c:if>><aebiz:showTitle titleId="withdrawapply.m.trimesterout"/></option>
												<option value="3" <c:if test='${qm.time == 3 }'>selected="selected"</c:if>><aebiz:showTitle titleId="withdrawapply.m.yearin"/></option>
											</select>
									 </div>
									 
									 <!-- 提现记录 -->
								   <div class="tab-content">
								   	
								   	<!-- 收支明细 -->
								     <div id="shouzhimingxi" class="tab-pane active">
								       <table class="table table-sorting table-striped table-hover" cellpadding="0" cellspacing="0" width="100%">
								         <thead>
								           <tr>
										         <th>编号</th>
										         <th>用途类型</th>
										         <th>收支</th>
										         <th>金额</th>
										         <th>所用订单</th>
										         <th>使用时间</th>
										         <th>备注信息</th>
										       </tr>
								         </thead>
								         <tbody>
								           <tr>
								             <td>1213213</td>
								             <td>购物消费</td>
								             <td class="szr"><i class="fa fa-plus fa-color1"></i></td>
								             <td><span class="rem-price-noc"><i class="fa fa-rmb"></i>399.<b class="pr-lit">99</b></span></td>
								             <td><a href="#" title="点击查看支付详情">123146546549</a></td>
								             <td>2014-11-14 15:27:09</td>
								             <td>无</td>
								           </tr>
								           <tr>
								             <td>1213213</td>
								             <td>购物消费</td>
								             <td class="szr"><i class="fa fa-minus fa-color2"></i></td>
								             <td><span class="rem-price-noc"><i class="fa fa-rmb"></i>399.<b class="pr-lit">99</b></span></td>
								             <td><a href="#" title="点击查看支付详情">123146546549</a></td>
								             <td>2014-11-14 15:27:09</td>
								             <td>无</td>
								           </tr>
								           <tr>
								             <td>1213213</td>
								             <td>购物消费</td>
								             <td class="szr"><i class="fa fa-minus fa-color2"></i></td>
								             <td><span class="rem-price-noc"><i class="fa fa-rmb"></i>399.<b class="pr-lit">99</b></span></td>
								             <td><a href="#" title="点击查看支付详情">123146546549</a></td>
								             <td>2014-11-14 15:27:09</td>
								             <td>无</td>
								           </tr>
								         </tbody>
								       </table>
								       <div class="tab-botm">
								         <p>收入总计：<span class="rem-price-noc fa-color1"><i class="fa fa-rmb"></i>399.<b class="pr-lit">99</b></span></p>
								         <p>支出总计：<span class="rem-price-noc fa-color2"><i class="fa fa-rmb"></i>399.<b class="pr-lit">99</b></span></p>
								         <p>虚拟账户余额：<span class="rem-price-noc fa-color1"><i class="fa fa-rmb"></i>399.<b class="pr-lit">99</b></span></p>
								       </div>
								       
								       如果没有任何记录的情况下 使用
										  <div class="alert alert-warning">
												暂时没有收支/充值/提现记录！
											</div>
								       
								       <div class="page-box clearfix">
									       <ul class="pagination"> <!--页码 star-->
														<li class="disabled"><a href="#"><i class="fa fa-chevron-left"></i></a></li>
														<li class="active"><a href="#">1</a></li>
														<li><a href="#">2</a></li>
														<li><a href="#">3</a></li>
														<li><a href="#">4</a></li>
														<li><a href="#"><i class="fa fa-chevron-right"></i></a></li>
												</ul><!--页码 end-->
											</div>
	
								     </div>
								     
								     <!-- 充值记录 -->
								     <div id="chongzhijilu" class="tab-pane">
								     	 <table class="table table-sorting table-striped table-hover" cellpadding="0" cellspacing="0" width="100%">
								         <thead>
								           <tr>
										         <th>充值时间</th>
										         <th>充值金额</th>
										         <th>充值来源</th>
										         <th>充值状态</th>
										       </tr>
								         </thead>
								         <tbody>
								           <tr>
								             <td>2014-11-14 15:50:32</td>
								             <td><span class="rem-price-noc"><i class="fa fa-rmb"></i>399.<b class="pr-lit">99</b></span></td>
								             <td>退货</td>
								             <td>充值完成</td>
								           </tr>
								           <tr>
								             <td>2014-11-14 15:50:32</td>
								             <td><span class="rem-price-noc"><i class="fa fa-rmb"></i>399.<b class="pr-lit">99</b></span></td>
								             <td>礼品卡支付</td>
								             <td>充值失败</td>
								           </tr>
								           <tr>
								             <td>2014-11-14 15:50:32</td>
								             <td><span class="rem-price-noc"><i class="fa fa-rmb"></i>399.<b class="pr-lit">99</b></span></td>
								             <td>工行支付</td>
								             <td>充值完成</td>
								           </tr>
								         </tbody>
								       </table>
								       <div class="page-box clearfix"><!--页码 star-->
									       <ul class="pagination"> 
														<li class="disabled"><a href="#"><i class="fa fa-chevron-left"></i></a></li>
														<li class="active"><a href="#">1</a></li>
														<li><a href="#">2</a></li>
														<li><a href="#">3</a></li>
														<li><a href="#">4</a></li>
														<li><a href="#"><i class="fa fa-chevron-right"></i></a></li>
												</ul>
											</div><!--页码 end-->
												
								     </div>
								     
								     <!-- 提现列表 -->
								     <div id="tixianjilu" class="tab-pane">
									      <div id="tixian">
									      	
									      </div>
							      	    
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