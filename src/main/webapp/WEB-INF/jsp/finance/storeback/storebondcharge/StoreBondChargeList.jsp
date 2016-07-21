<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/import/ListImport.jsp" %>
<!-- 日期插件 -->
<link  rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/storeback/css/plugins/datepicker/datepicker.css">
<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/datepicker/bootstrap-datepicker.js"></script>

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
					<h1><aebiz:showTitle titleId="storebondcharge.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
				</div>
			</div>
			<div class="breadcrumbs">
				<ul>
					<li>
						<span><aebiz:showTitle titleId="storebondcharge.menuOne"/></span>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<span><aebiz:showTitle titleId="storebondcharge.menuTwo"/></span>
						<i class="fa fa-angle-right"></i>							
					</li>							
					<li>
						<span><aebiz:showTitle titleId="storebondcharge.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
					</li>						
				</ul>				
			</div>				
								
			<div class="row">
					<div class="col-sm-12">
						<div class="box">
							<div class="box-content nopadding y_tableser">
								<div class="row">
									<div class="col-sm-12">
										<div class="alert alert-info alert_box">
											<b><aebiz:showTitle titleId="storebondcharge.m.bondBalance"/>：</b><span class="font-red"><aebiz:showTitle titleId="pub.moneytype"/>${account.bondMoney }</span>  <br />
											<b><aebiz:showTitle titleId="storebondcharge.m.freezeBondCharge"/>：</b><span class="font-red"><aebiz:showTitle titleId="pub.moneytype"/>${account.freezeBondMoney }</span> 										
											<a class="btn btn-green y_mr1l2" href="${pageContext.servletContext.contextPath}/storeback/storebondcharge/toAdd"><aebiz:showTitle titleId="storebondcharge.m.pay"/></a><a class="btn btn-lightgrey" href="${pageContext.servletContext.contextPath}/store/storewithdrawapply/doWithdrawApplyMargin">申请提现</a><br />
											<label><aebiz:showTitle titleId="storebondcharge.m.bondNote"/></label>
										</div>
									</div>
									<div class="col-sm-1">
										<div style="margin-top:2px;">
											
										</div>	
										<div style="margin-top:10px;">
											
										</div>	
									</div>
								</div>
							<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
								<thead>		
									<tr>
										<th><aebiz:showTitle titleId="storebondcharge.m.bondUuid"/></th>
										<th><aebiz:showTitle titleId="storebondcharge.m.operType"/></th>
										<th><aebiz:showTitle titleId="storebondcharge.m.operAmount"/></th>
										<th><aebiz:showTitle titleId="storebondcharge.m.operState"/></th>
										<th><aebiz:showTitle titleId="storebondcharge.m.fromSource"/></th>
										<th><aebiz:showTitle titleId="storebondcharge.m.payType"/></th>
										<th><aebiz:showTitle titleId="storebondcharge.m.note"/></th>
										<th><aebiz:showTitle titleId="storebondcharge.m.createTime"/></th>
										<th><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
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
</div>
	
<script>
function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
	
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
				                'aTargets': [0,1,2,3,4,5,7,8]                
				            }],
				            "bSort": true, //排序功能            
					         	"aoColumns": [
											{"mDataProp":"bondUuid" ,"sTitle": "<aebiz:showTitle titleId="storebondcharge.m.bondUuid"/>"},
											{
													"mDataProp" : "operType",
													"sDefaultContent" : "",
													"sWidth":"8%",
													"sTitle" : "<aebiz:showTitle titleId="storebondcharge.m.operType"/>",
													"fnRender" : function(obj) {
														var sReturn = "";
														if(obj.aData.operState == "0"){
															sReturn = "<aebiz:showTitle titleId="basebusiness.showmessage.add"/>";
														}else if(obj.aData.operState == "1"){
															sReturn = "<aebiz:showTitle titleId="basebusiness.showmessage.rebate"/>";
														}
														return sReturn;
													}
												},
												{
													"mDataProp" : "operAmount",
													"sTitle" : "<aebiz:showTitle titleId="storebondcharge.m.operAmount"/>"
												},

												{
													"mDataProp" : "operState1",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="storebondcharge.m.operState"/>",
													"fnRender" : function(obj) {
														var sReturn = "";
														if(obj.aData.operState == "0"){
															sReturn = "<aebiz:showTitle titleId="storebondcharge.m.underPay"/>";
														}else if(obj.aData.operState == "1"){
															sReturn = "<aebiz:showTitle titleId="storebondcharge.m.paySuccess"/>";
														}else if(obj.aData.operState == "2"){
															sReturn = "<aebiz:showTitle titleId="storebondcharge.m.payFail"/>";
														}
														return sReturn;
													}
												},
												{
													"mDataProp" : "fromSource",
													"sDefaultContent" : "",
													"sWidth":"10%",
													"sTitle" : "<aebiz:showTitle titleId="storebondcharge.m.fromSource"/>",
													"fnRender" : function(obj) {
														var sReturn = "";
														if(obj.aData.fromSource == "0"){
															sReturn = "<aebiz:showTitle titleId="storebondcharge.m.fromContract"/>";
														}else if(obj.aData.fromSource == "1"){
															sReturn = "<aebiz:showTitle titleId="storebondcharge.m.fromBreak"/>";
														}else if(obj.aData.fromSource == "2"){
															sReturn = "<aebiz:showTitle titleId="storebondcharge.m.normalPay"/>";
														}
														return sReturn;
													}
												},
												{
													"mDataProp" : "payType",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="storebondcharge.m.payType"/>",
													"fnRender" : function(obj) {
														var sReturn = "";
														if(obj.aData.payType == "1"){
															sReturn = "<aebiz:showTitle titleId="storebondcharge.m.online"/>";
														}else if(obj.aData.payType == "2"){
															sReturn = "<aebiz:showTitle titleId="storebondcharge.m.offline"/>";
														}
														return sReturn;
													}
												},
												{
													"mDataProp" : "note",
													"sTitle" : "<aebiz:showTitle titleId="storebondcharge.m.note"/>"
												},
												{
													"mDataProp" : "createTime",
													"sTitle" : "<aebiz:showTitle titleId="storebondcharge.m.createTime"/>"
												},

												{
													"mDataProp" : "operate",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>",
													"fnRender" : function(obj) {
														var sReturn ="";
														if(obj.aData.operState == "0"){
														 sReturn = "<a href='${pageContext.servletContext.contextPath}/storeback/storebackcharge/toUpdate/"
																+ obj.aData.uuid
																+ "' class='btn btn-primary' rel='tooltip' title='<aebiz:showTitle titleId="storebondcharge.m.toPay"/>'><aebiz:showTitle titleId="storebondcharge.m.toPay"/></a>";
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
										"sAjaxSource" : "${pageContext.servletContext.contextPath}/storeback/storebondcharge/queryList",
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

	$(".search").click(function() {
		//普通搜索需要清空高级搜索的选项
		clearMoreSearch();
		doSearch('search');
	})

	$(".moresearch").click(function() {
		//高级搜索需要清空普通搜索的选项
		clearSearch();
		doSearch('search');
	})

	function clearSearch() {
		$("#accountUuid").val("");
		$("#operState").val("");

	}

	//清空高级搜索的所有选项
	function clearMoreSearch() {
		$("#contractUuid_s").val("");
		$("#bondUuid_s").val("");
		$("#operAmount_s").val("");
		$("#operType_s").val("");
		$("#operState_s").val("");
		$("#fromSource_s").val("");
		$("#note_s").val("");
		$("#accountUuid_s").val("");
		$("#bondBalance_s").val("");
		$("#createTime_s").val("");
		$("#createTime2_s").val("");

	}

	$(document).ready(function() {

		//初始化表格
		doSearch('inittable');

		//复选框全选
		$("#check_all").click(function(e) {
			$('input', oTable.fnGetNodes()).prop('checked', this.checked);
		});

		$(".clearMoreSearch").click(function(e) {
			clearMoreSearch();
		});
	})
</script>

	
</body>

</html>