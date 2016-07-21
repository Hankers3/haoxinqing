<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/import/ListImport.jsp" %>

</head>
   
<body>
<!-- 头部 -->
<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeHead.jsp"%>
<div class="container-fluid" id="content">
<!-- 左侧 -->
<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeLeft.jsp"%>
	<div id="main">	
		<div class="container-fluid">	
			<div class="page-header">
				<div class="pull-left">
					<h1><aebiz:showTitle titleId="virtualaccountcustomercharge.m.myVirtual"/></h1>
				</div>
			</div>
				<div class="breadcrumbs">
					<ul>
						<li>
							<span><aebiz:showTitle titleId="virtualaccountcustomerlog.menuOne"/></span>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<span><aebiz:showTitle titleId="withdrawapply.menuTwo"/></span>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<span><aebiz:showTitle titleId="virtualaccountcustomercharge.m.myVirtual"/></span>							
						</li>
					</ul>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="alert alert-info alert_box">
							<b><aebiz:showTitle titleId="virtualaccountstorecharge.m.balance"/>：</b><span class="font-red"><aebiz:showTitle titleId="pub.moneytype"/>${account.virtualMoney }</span><a class="btn btn-green y_mr1l2" href="${pageContext.servletContext.contextPath}/storeback/virtualaccountstorecharge/toAdd"><aebiz:showTitle titleId="withdrawapply.m.pay"/></a><a class="btn btn-lightgrey" href="${pageContext.servletContext.contextPath}/storeback/virtualaccountstorecharge/toAddStoreWithDrawApply"><aebiz:showTitle titleId="virtualaccountstorecharge.m.apply"/></a><br />
							<label><aebiz:showTitle titleId="virtualaccountstorecharge.m.notice"/></label>
						</div>
					</div>
				</div>
				<ul class="tabs tabs-inline tabs-top border1-top">
					<li class='active'>
						<a href="#shouzhi" data-toggle='tab'><aebiz:showTitle titleId="withdrawapply.m.detail"/></a>
					</li>
					<li>
						<a href="#chongzhi" data-toggle='tab'><aebiz:showTitle titleId="withdrawapply.m.paylog"/></a>
					</li>
					<li>
						<a href="#tixian" data-toggle='tab'><aebiz:showTitle titleId="withdrawapply.m.withdralog"/></a>
					</li>
				</ul>	
						
				<div class="tab-content y_tabdatable">
					<div class="tab-pane active" id="shouzhi">
						<div class="y_clear">
							<div class="form-inline table_formnew">
								<div class="form-group">
									<label class="control-label"><aebiz:showTitle titleId="virtualaccountstorelog.m.orderMainUuid"/>:</label>
									<input type="text" name="orderMainUuid" id="orderMainUuid" class="form-control ">
									<input type="hidden" name="orderMainUuid_q" id="orderMainUuid_q" class="form-control" value="EQ">
								</div>
								<div class="form-group">
									<label class="control-label"><aebiz:showTitle titleId="virtualaccountstorelog.m.operType"/>:</label>
									<select name="operType" id="operType" class="form-control ">
										<option value="">--<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>--</option>
										<option value="0"><aebiz:showTitle titleId="virtualaccountstorelog.m.in"/></option>
										<option value="1"><aebiz:showTitle titleId="virtualaccountstorelog.m.out"/></option>
									</select>
									<input type="hidden" name="operType_q" id="operType_q" class="form-control" value="EQ">
								</div>
								<div class="form-group">
									<button class="btn btn-primary search" title="<aebiz:showTitle titleId='basebusiness.showmessage.query'/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
									<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId='basebusiness.showmessage.clear'/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
								</div>
							</div>
						</div>
						<div class="table-responsive2">
							<table id="user" class="table table-bordered table-hover table-striped dataTable dataTable-nosort dataTable-reorder" data-nosort="0">
								<thead>
								<tr>
									<th><aebiz:showTitle titleId="virtualaccountstorelog.m.operType"/></th>
									<th><aebiz:showTitle titleId="virtualaccountstorelog.m.operAmount"/></th>
									<th><aebiz:showTitle titleId="virtualaccountstorelog.m.nowBalance"/></th>
									<th><aebiz:showTitle titleId="virtualaccountstorelog.m.orderMainUuid"/></th>
									<th><aebiz:showTitle titleId="virtualaccountstorelog.m.description"/></th>
								</tr>
								</thead>
								
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
					<div class="tab-pane" id="chongzhi">
						<div class="y_clear">
							<div class="form-inline table_formnew">
								<div class="form-group">
									<label class="control-label"><aebiz:showTitle titleId="virtualaccountstorecharge.m.chargeState"/>:</label>
									<select name="chargeState" id="chargeState" class="form-control ">
										<option value="">--<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>--</option>
										<option value="0"><aebiz:showTitle titleId="virtualaccountstorecharge.m.underPay"/></option>
										<option value="1"><aebiz:showTitle titleId="virtualaccountstorecharge.m.paySuccess"/></option>
										<option value="2"><aebiz:showTitle titleId="virtualaccountstorecharge.m.payFail"/></option>
									</select>
									<input type="hidden" name="chargeState_q" id="chargeState_q" class="form-control" value="EQ">
								</div>
								<div class="form-group">
									<button class="btn btn-primary search1" title="<aebiz:showTitle titleId='basebusiness.showmessage.query'/>" rel="tooltip"><aebiz:showTitle titleId='basebusiness.showmessage.query'/></button>
									<button class="btn" onclick="javascript:clearSearch1();" title="<aebiz:showTitle titleId='basebusiness.showmessage.clear'/>" rel="tooltip"><aebiz:showTitle titleId='basebusiness.showmessage.clear'/></button>
								</div>
							</div>
						</div>
						<div class="table-responsive2">
							<table class="table table-bordered table-hover table-striped dataTable2 dataTable-nosort dataTable-reorder dataTable-colvis" data-nosort="0">
								<thead>		
								<tr>
									<th><aebiz:showTitle titleId="virtualaccountstorecharge.m.chargeNo"/></th>
									<th><aebiz:showTitle titleId="virtualaccountstorecharge.m.operAmount"/></th>
									<th><aebiz:showTitle titleId="virtualaccountstorecharge.m.chargeState"/></th>
									<th><aebiz:showTitle titleId="virtualaccountstorecharge.m.createTime"/></th>
									<th><aebiz:showTitle titleId="virtualaccountstorecharge.m.note"/></th>
	
									<th><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
								</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
					<div class="tab-pane" id="tixian">
						<div class="y_clear">
							<div class="form-inline table_formnew">
								<div class="form-group">
									<label class="control-label"><aebiz:showTitle titleId="virtualaccountstorecharge.m.timeType"/>:</label>
									<select  id="timeType" name="timeType"  class="form-control" >
										<option value="">--<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>--</option>
										<option value="1"  <c:if test="${timeType==1}">selected=selected</c:if>><aebiz:showTitle titleId="virtualaccountcustomercharge.m.trimesterin"/></option>
										<option value="2"  <c:if test="${timeType==2}">selected=selected</c:if>><aebiz:showTitle titleId="virtualaccountcustomercharge.m.trimesterout"/></option>
										<option value="3"  <c:if test="${timeType==3}">selected=selected</c:if>><aebiz:showTitle titleId="virtualaccountcustomercharge.m.yearin"/></option>
									</select>
								</div>
								<div class="form-group">
									<button class="btn btn-primary search3" title="<aebiz:showTitle titleId='basebusiness.showmessage.query'/>" rel="tooltip"><aebiz:showTitle titleId='basebusiness.showmessage.query'/></button>
									<button class="btn" onclick="javascript:clearSearch3();" title="<aebiz:showTitle titleId='basebusiness.showmessage.clear'/>" rel="tooltip"><aebiz:showTitle titleId='basebusiness.showmessage.clear'/></button>
								</div>
							</div>
						</div>
						<div class="table-responsive2">
							<table class="table table-bordered table-hover table-striped dataTable3 dataTable-nosort dataTable-reorder dataTable-colvis" data-nosort="0">
								<thead>		
									<tr>
										<th class='with-checkbox'>
											<input type="checkbox" name="check_all" id="check_all">
										</th>
										<th><aebiz:showTitle titleId="withdrawapply.m.moneyType"/></th>
										<th><aebiz:showTitle titleId="withdrawapply.qm.applyMoney"/></th> 
										<th><aebiz:showTitle titleId="withdrawapply.qm.applyType"/></th>
										<th><aebiz:showTitle titleId="withdrawapply.qm.successTime"/></th>
										<th><aebiz:showTitle titleId="withdrawapply.m.bankName"/></th>
										<th><aebiz:showTitle titleId="withdrawapply.m.bankNo"/></th>
										<th><aebiz:showTitle titleId="withdrawapply.m.state"/></th>
										<th><aebiz:showTitle titleId="withdrawapply.m.note"/></th>
									</tr>
								</thead>
								
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				
			
			</div>
		</div>
	</div>
	
<script>
//账户日志记录
function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
	searchParam.push({ "name": "operType_q", "value": $.trim($("#operType_q").val())});
	searchParam.push({ "name": "operType", "value": $.trim($("#operType").val())});
	searchParam.push({ "name": "orderMainUuid", "value": $.trim($("#orderMainUuid").val())});
	searchParam.push({ "name": "orderMainUuid_q", "value": $.trim($("#orderMainUuid_q").val())});
	
   $.ajax({    	        
  	"dataType": 'json', 
	  "type": "POST", 
	  "url": sSource, 
	  "data": {aoData:JSON.stringify(aoData),searchParam:JSON.stringify(searchParam)}, 
	  "success": fnCallback
  });    
} 
    
var oTable = null; 
function doSearch(operact) {	
	if(oTable == null) {		 
    if ($('.dataTable').length > 0) {
        $('.dataTable').each(function() {            
                var opt = {
				            "sPaginationType": "full_numbers",
				            "oLanguage": {
				            		"sProcessing": "<aebiz:showTitle titleId="basebusiness.showmessage.loading"/>",
				                "sSearch": "<span><aebiz:showTitle titleId="basebusiness.showmessage.query"/>:</span> ",
				                "sInfo": "<aebiz:showTitle titleId="basebusiness.showmessage.pageFrom"/> <span>_START_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageTo"/> <span>_END_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageItem"/>，<aebiz:showTitle titleId="basebusiness.showmessage.totalCount"/>： <span>_TOTAL_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageItem"/>",
				                "sLengthMenu": "_MENU_ <span><aebiz:showTitle titleId="basebusiness.showmessage.pageShow"/></span>" ,
				                "oPaginate": {
				                 	"sFirst": "<aebiz:showTitle titleId="basebusiness.showmessage.firstPage"/>",
				                  "sPrevious": "<aebiz:showTitle titleId="basebusiness.showmessage.prePage"/>",
				                  "sNext": "<aebiz:showTitle titleId="basebusiness.showmessage.nextPage"/>",
				                  "sLast": "<aebiz:showTitle titleId="basebusiness.showmessage.lastPage"/>"
				                }
				            },
				            
				            //'sDom': "lfrtip",
				            'sDom': "rtlip",
				            //"sDom": '<"top"l>rt<"bottom"ip><"clear">',            
				            'aoColumnDefs': [{
				                'bSortable': false,
				                'aTargets': [3,4]                
				            }],
				            "bSort": true, //排序功能            
					         	"aoColumns": [
											
												{
													"mDataProp" : "operType",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="virtualaccountstorelog.m.operType"/>",
													"fnRender" : function(obj) {
														var sReturn = "";
														if("0" == obj.aData.operType){
															sReturn = "<aebiz:showTitle titleId='virtualaccountstorelog.m.in'/>";
														}else if("1" == obj.aData.operType){
															sReturn = "<aebiz:showTitle titleId='virtualaccountstorelog.m.out'/>";
														}
														return sReturn;
													}
												},
												{
													"mDataProp" : "operAmount",
													"sTitle" : "<aebiz:showTitle titleId="virtualaccountstorelog.m.operAmount"/>"
												},
												{
													"mDataProp" : "nowBalance",
													"sTitle" : "<aebiz:showTitle titleId="virtualaccountstorelog.m.nowBalance"/>"
												},
												{
													"mDataProp" : "orderMainUuid",
													"sTitle" : "<aebiz:showTitle titleId="virtualaccountstorelog.m.orderMainUuid"/>"
												},
												{
													"mDataProp" : "description",
													"sTitle" : "<aebiz:showTitle titleId="virtualaccountstorelog.m.description"/>"
												},

										],

										//'oColVis': {
										// "buttonText": "Change columns <i class='icon-angle-down'></i>"
										//},
										'oTableTools' : {
											"sSwfPath" : "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
										},
										"bRetrieve" : true,
										"bProcessing" : true,
										"bServerSide" : true, //指定从服务器端获取数据
										"sAjaxSource" : "${pageContext.servletContext.contextPath}/storeback/virtualaccountstorelog/queryList",
										"fnServerData" : retrieveData, //获取数据的处理函数  
									};

									if ($(this).hasClass("dataTable-reorder")) {
										opt.sDom = "R" + opt.sDom;
									}

									oTable = $(this).dataTable(opt);

								});
			}
		}

		//刷新Datatable，会自动激发retrieveData  		
		if ('inittable' != operact) {
			oTable.fnDraw();
		}

	}

	//充值记录
	function retrieveData2(sSource, aoData, fnCallback) {
		var searchParam = new Array();
		searchParam.push({
			"name" : "chargeState_q",
			"value" : $.trim($("#chargeState_q").val())
		});
		searchParam.push({
			"name" : "chargeState",
			"value" : $.trim($("#chargeState").val())
		});

		$.ajax({
			"dataType" : 'json',
			"type" : "POST",
			"url" : sSource,
			"data" : {
				aoData : JSON.stringify(aoData),
				searchParam : JSON.stringify(searchParam)
			},
			"success" : fnCallback
		});
	}

	var oTable2 = null;
	function doSearch2(operact) {
		if (oTable2 == null) {
			if ($('.dataTable2').length > 0) {
				$('.dataTable2')
						.each(
								function() {
									var opt = {
										"sPaginationType" : "full_numbers",
										"oLanguage" : {
											"sProcessing" : "<aebiz:showTitle titleId="basebusiness.showmessage.loading"/>",
											"sSearch" : "<span><aebiz:showTitle titleId="basebusiness.showmessage.query"/>:</span> ",
											"sInfo" : "<aebiz:showTitle titleId="basebusiness.showmessage.pageFrom"/> <span>_START_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageTo"/> <span>_END_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageItem"/>，<aebiz:showTitle titleId="basebusiness.showmessage.totalCount"/>： <span>_TOTAL_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageItem"/>",
											"sLengthMenu" : "_MENU_ <span><aebiz:showTitle titleId="basebusiness.showmessage.pageShow"/></span>",
											"oPaginate" : {
												"sFirst" : "<aebiz:showTitle titleId="basebusiness.showmessage.firstPage"/>",
												"sPrevious" : "<aebiz:showTitle titleId="basebusiness.showmessage.prePage"/>",
												"sNext" : "<aebiz:showTitle titleId="basebusiness.showmessage.nextPage"/>",
												"sLast" : "<aebiz:showTitle titleId="basebusiness.showmessage.lastPage"/>"
											}
										},

										//'sDom': "lfrtip",
										'sDom' : "rtlip",
										//"sDom": '<"top"l>rt<"bottom"ip><"clear">',            
										'aoColumnDefs' : [ {
											'bSortable' : false,
											'aTargets' : [ 0,4,5 ]
										} ],
										"bSort" : true, //排序功能            
										"aoColumns" : [
												{
													"mDataProp" : "chargeNo",
													"sTitle" : "<aebiz:showTitle titleId="virtualaccountstorecharge.m.chargeNo"/>"
												},
												{
													"mDataProp" : "operAmount",
													"sTitle" : "<aebiz:showTitle titleId="virtualaccountstorecharge.m.operAmount"/>"
												},
												{
													"mDataProp" : "chargeState1",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="virtualaccountstorecharge.m.chargeState"/>",
													"fnRender" : function(obj) {
														var sReturn = "";
														if("0" == obj.aData.chargeState){
															sReturn = "<aebiz:showTitle titleId='virtualaccountstorecharge.m.underPay'/>";
														}else if("1" == obj.aData.chargeState){
															sReturn = "<aebiz:showTitle titleId='virtualaccountstorecharge.m.paySuccess'/>";
														}else if("2" == obj.aData.chargeState){
															sReturn = "<aebiz:showTitle titleId='virtualaccountstorecharge.m.payFail'/>";
														}
														return sReturn;
													}
												},
												{
													"mDataProp" : "createTime",
													"sTitle" : "<aebiz:showTitle titleId="virtualaccountstorecharge.m.createTime"/>"
												},
												{
													"mDataProp" : "note",
													"sTitle" : "<aebiz:showTitle titleId="virtualaccountstorecharge.m.note"/>"
												},

												{
													"mDataProp" : "operate",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>",
													"fnRender" : function(obj) {
														var sReturn = "";
														if("0" == obj.aData.chargeState){
															sReturn = "<a href='#' class='btn btn-primary' rel='tooltip' title='<aebiz:showTitle titleId="virtualaccountstorecharge.m.gotoPay"/>'><aebiz:showTitle titleId='virtualaccountstorecharge.m.gotoPay'/></a>";
														}
														return sReturn;
													}
												} ],

										//'oColVis': {
										// "buttonText": "Change columns <i class='icon-angle-down'></i>"
										//},
										'oTableTools' : {
											"sSwfPath" : "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
										},
										"bRetrieve" : true,
										"bProcessing" : true,
										"bServerSide" : true, //指定从服务器端获取数据
										"sAjaxSource" : "${pageContext.servletContext.contextPath}/storeback/virtualaccountstorecharge/queryList",
										"fnServerData" : retrieveData2, //获取数据的处理函数  
									};

									if ($(this).hasClass("dataTable-reorder")) {
										opt.sDom = "R" + opt.sDom;
									}

									oTable2 = $(this).dataTable(opt);

								});
			}
		}

		//刷新Datatable，会自动激发retrieveData  		
		if ('inittable' != operact) {
			oTable2.fnDraw();
		}

	}

	//体现记录
	function retrieveData3(sSource, aoData, fnCallback) {
		var searchParam = new Array();
		searchParam.push({ "name": "timeType", "value": $.trim($("#timeType").val())});
		$.ajax({
			"dataType" : 'json',
			"type" : "POST",
			"url" : sSource,
			"data" : {
				aoData : JSON.stringify(aoData),
				searchParam : JSON.stringify(searchParam)
			},
			"success" : fnCallback
		});
	}

	var oTable3 = null;
	function doSearch3(operact) {
		if (oTable3 == null) {
			if ($('.dataTable3').length > 0) {
				$('.dataTable3')
						.each(
								function() {
									var opt = {
										"sPaginationType" : "full_numbers",
										"oLanguage" : {
											"sProcessing" : "<aebiz:showTitle titleId="basebusiness.showmessage.loading"/>",
											"sSearch" : "<span><aebiz:showTitle titleId="basebusiness.showmessage.query"/>:</span> ",
											"sInfo" : "<aebiz:showTitle titleId="basebusiness.showmessage.pageFrom"/> <span>_START_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageTo"/> <span>_END_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageItem"/>，<aebiz:showTitle titleId="basebusiness.showmessage.totalCount"/>： <span>_TOTAL_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageItem"/>",
											"sLengthMenu" : "_MENU_ <span><aebiz:showTitle titleId="basebusiness.showmessage.pageShow"/></span>",
											"oPaginate" : {
												"sFirst" : "<aebiz:showTitle titleId="basebusiness.showmessage.firstPage"/>",
												"sPrevious" : "<aebiz:showTitle titleId="basebusiness.showmessage.prePage"/>",
												"sNext" : "<aebiz:showTitle titleId="basebusiness.showmessage.nextPage"/>",
												"sLast" : "<aebiz:showTitle titleId="basebusiness.showmessage.lastPage"/>"
											}
										},

										//'sDom': "lfrtip",
										'sDom' : "rtlip",
										//"sDom": '<"top"l>rt<"bottom"ip><"clear">',            
										'aoColumnDefs' : [ {
											'bSortable' : false,
											'aTargets' : [ 0,5,6, 7, 8 ]
										} ],
										"bSort" : true, //排序功能            
										"aoColumns" : [
												{
													"mDataProp" : "checkbox",
													"sDefaultContent" : "",
													"fnRender" : function(obj) {
														var sReturn = "<input type='checkbox' name='check' value='"+obj.aData.uuid+"' />";
														return sReturn;
													}
												},
												{
													"mDataProp" : "moneyType",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="withdrawapply.m.moneyType"/>",
													"fnRender" : function(obj) {
														var sReturn = ""
														if (obj.aData.moneyType == 1) {//商户余额
															sReturn += "<aebiz:showTitle titleId="withdrawapply.qm.merchantBalance"/>";
														} else if (obj.aData.moneyType == 2) {//会员余额
															sReturn += "<aebiz:showTitle titleId="withdrawapply.qm.memberBalance"/>";
														} else {//商户保证金
															sReturn += "<aebiz:showTitle titleId="withdrawapply.qm.merchantMargin"/>";
														}
														return sReturn;
													}
												},
												{
													"mDataProp" : "applyMoney",
													"sTitle" : "<aebiz:showTitle titleId="withdrawapply.qm.applyMoney"/>"
												},
												{
													"mDataProp" : "applyTime",
													"sTitle" : "<aebiz:showTitle titleId="withdrawapply.qm.applyType"/>"
												},
												{
													"mDataProp" : "successTime",
													"sTitle" : "<aebiz:showTitle titleId="withdrawapply.qm.successTime"/>"
												},

												{
													"mDataProp" : "bankNo",
													"sTitle" : "<aebiz:showTitle titleId="withdrawapply.m.bankNo"/>"
												},
												{
													"mDataProp" : "bankName",
													"sTitle" : "<aebiz:showTitle titleId="withdrawapply.m.bankName"/>"
												},

												{
													"mDataProp" : "note",
													"sTitle" : "<aebiz:showTitle titleId="withdrawapply.m.note"/>"
												},
												{
													"mDataProp" : "operate",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>",
													"fnRender" : function(obj) {
														var sReturn = ""
														if (obj.aData.state == 0) {
															sReturn = "<aebiz:showTitle titleId="withdrawapply.qm.untreated"/>";
														} else if (obj.aData.state == 1) {
															sReturn = "<aebiz:showTitle titleId="withdrawapply.qm.withdrawSuccess"/>";
														} else if (obj.aData.state == 2) {
															sReturn = "<a href='javascript:aReject(\""
																	+ obj.aData.failReason
																	+ "\");' rel='tooltip' title='<aebiz:showTitle titleId="withdrawapply.qm.withdrawFail"/>'><aebiz:showTitle titleId="withdrawapply.qm.withdrawFail"/></a>";
														}
														return sReturn;
													}
												} ],

										//'oColVis': {
										// "buttonText": "Change columns <i class='icon-angle-down'></i>"
										//},
										'oTableTools' : {
											"sSwfPath" : "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
										},
										"bRetrieve" : true,
										"bProcessing" : true,
										"bServerSide" : true, //指定从服务器端获取数据
										"sAjaxSource" : "${pageContext.servletContext.contextPath}/storeback/virtualaccountstorecharge/withdrawapplyList",
										"fnServerData" : retrieveData3, //获取数据的处理函数  
									};
									if ($(this).hasClass("dataTable-reorder")) {
										opt.sDom = "R" + opt.sDom;
									}

									oTable3 = $(this).dataTable(opt);

								});
			}
		}

		//刷新Datatable，会自动激发retrieveData  		
		if ('inittable' != operact) {
			oTable3.fnDraw();
		}
	}

	$(".search").click(function() {
		doSearch('search');
	})
	$(".search1").click(function() {
		doSearch2('search');
	})
	$(".search3").click(function() {
		doSearch3('search');
	})

	function clearSearch() {
		$("#orderMainUuid").val("");
		$("#operType").val("");
	}

	function clearSearch1() {
		$("#chargeState").val("");
	}
	function clearSearch3() {
		$("#timeType").val("");
	}

	$(document).ready(function() {

		//初始化表格
		doSearch('inittable');
		doSearch2('inittable');
		doSearch3('inittable');

		$(".clearMoreSearch").click(function(e) {
			clearMoreSearch();
		});
	})

	//驳回
	function aReject(failReason) {
		$("#textarea").val(failReason);
		$("#modal-bohui").modal("show");
	}
</script>	
	
</body>
</html>
