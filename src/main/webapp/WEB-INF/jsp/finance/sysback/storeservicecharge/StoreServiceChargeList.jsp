<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>

</head>

<body>
	<div class="container-fluid">	
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="storeservicecharge.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="storeservicecharge.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="storeservicecharge.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span><aebiz:showTitle titleId="storeservicecharge.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
				</li>						
			</ul>				
		</div>				
			
		<input type="hidden" name="payStatus" id="payStatus" value="0">
		<input type="hidden" name="payStatus_q" id="payStatus_q" class="form-control" value="EQ">				
		<div class="row">
				<div class="col-sm-12">
					<div class="box">
						<div class="box-content nopadding y_tableser">
							<div class="y_clear">
								<div class="form-inline table_formnew">
								
									<div  class="form-group">
										<label class="control-label"><aebiz:showTitle titleId="storebondcharge.qmf.storeNameOrNo"/>：</label>
										<input type="text" name="storeNameOrNo" id="storeNameOrNo" class="form-control">
									</div>
									<div class="form-group">
										<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
										<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
										<button class="btn" id="searchshow" title="<aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/>" rel="tooltip" data-toggle="modal" data-target="#modal-user"><aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/></button>
									</div>
								</div>
								
								<div class="y_tablebtn">
									<a class="btn" href="${pageContext.servletContext.contextPath}/sysback/storeservicecharge/toAdd" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/></a>
									<button class="btn" onclick="javascript:removes('');" title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></button>
								</div>
						</div>
						
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th class='with-checkbox'>
										<input type="checkbox" name="check_all" id="check_all">
									</th>
									<th><aebiz:showTitle titleId="storebondcharge.m.accountNo" /></th>
									<th><aebiz:showTitle titleId="storebondcharge.m.accountName" /></th>
									<th><aebiz:showTitle titleId="storeservicecharge.m.contractUuid"/></th>
									<th><aebiz:showTitle titleId="storeservicecharge.m.bondUuid"/></th>
									<th><aebiz:showTitle titleId="storeservicecharge.m.payAmount"/></th>
									<th><aebiz:showTitle titleId="storeservicecharge.m.payType"/></th>
									<th><aebiz:showTitle titleId="storeservicecharge.m.payStatus"/></th>
									<th><aebiz:showTitle titleId="storeservicecharge.m.createTime"/></th>
	
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
	
	<!--高级搜索-->
		<div id="modal-user" class="modal fade y_highserch">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 id="user-infos"><aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/></h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-sm-4">
								 <div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="storeservicecharge.m.contractUuid"/></label>
									<input type="text" name="contractUuid_s" id="contractUuid_s" class="form-control">
									<input type="hidden" name="contractUuid_q" id="contractUuid_q" value="EQ">
								</div>
							</div>
							<div class="col-sm-4">
								 <div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="storeservicecharge.m.bondUuid"/></label>
									<input type="text" name="bondUuid_s" id="bondUuid_s" class="form-control">
									<input type="hidden" name="bondUuid_q" id="bondUuid_q" value="EQ">
								</div>
							</div>
							<div class="col-sm-4">
								 <div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="storeservicecharge.m.payType"/></label>
									<input type="text" name="payType_s" id="payType_s" class="form-control">
									<input type="hidden" name="payType_q" id="payType_q" value="EQ">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-4">
								 <div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="storebondcharge.qmf.storeNameOrNo"/></label>
									<input type="text" name="storeNameOrNo_s" id="storeNameOrNo_s" class="form-control">
								</div>
							</div>
							<div class="col-sm-4">
								 <div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="storeservicecharge.m.createTimeGE"/></label>
									<input type="text" name="createTime_s" id="createTime_s" class="form-control datepick">
									<input type="hidden" name="createTime_q" id="createTime_q" value="EQ">
								</div>
							</div>
							<div class="col-sm-4">
								 <div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="storeservicecharge.qm.createTime2"/></label>
									<input type="text" name="createTime2_s" id="createTime2_s" class="form-control datepick">
									<input type="hidden" name="createTime2_q" id="createTime2_q" value="LT">
								</div>
							</div>
						</div>
	
					</div>													
					<div class="modal-footer">
						<button class="btn moresearch btn-primary" data-dismiss="modal"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
						<button class="btn clearMoreSearch" ><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
					</div>
				</div>
			</div>
		</div>
		<!--高级搜索-->
	
	
</body>

</html>

<script>
function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
	searchParam.push({
		"name" : "storeNameOrNo",
		"value" : $.trim($("#storeNameOrNo").val())
	});
	searchParam.push({
		"name" : "storeNameOrNo_s",
		"value" : $.trim($("#storeNameOrNo_s").val())
	});
	searchParam.push({ "name": "contractUuid_s", "value": $.trim($("#contractUuid_s").val())});
	searchParam.push({ "name": "contractUuid_q", "value": $.trim($("#contractUuid_q").val())});
	searchParam.push({ "name": "bondUuid_s", "value": $.trim($("#bondUuid_s").val())});
	searchParam.push({ "name": "bondUuid_q", "value": $.trim($("#bondUuid_q").val())});
	searchParam.push({ "name": "payType_s", "value": $.trim($("#payType_s").val())});
	searchParam.push({ "name": "payType_q", "value": $.trim($("#payType_q").val())});
	searchParam.push({ "name": "payStatus_q", "value": $.trim($("#payStatus_q").val())});
	searchParam.push({ "name": "createTime_s", "value": $.trim($("#createTime_s").val())});
	searchParam.push({ "name": "createTime_q", "value": $.trim($("#createTime_q").val())});
	searchParam.push({ "name": "createTime2_s", "value": $.trim($("#createTime2_s").val())});
	searchParam.push({ "name": "createTime2_q", "value": $.trim($("#createTime2_q").val())});
	searchParam.push({ "name": "accountUuid", "value": $.trim($("#accountUuid").val())});
	searchParam.push({ "name": "payStatus", "value": $.trim($("#payStatus").val())});
			
	
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
				                'aTargets': [0, 1,2,3,4,9]                
				            }],
				            "bSort": true, //排序功能            
					         	"aoColumns": [
										  {
										  	"mDataProp":"checkbox" ,
										  	"sDefaultContent":"",
										  	"fnRender": function(obj) {
										      var sReturn = "<input type='checkbox' name='check' value='"+obj.aData.uuid+"' />";						      
										      return sReturn;
										 		}
										  },
											{
												"mDataProp" : "storeNo",
												"sTitle" : "<aebiz:showTitle titleId="storebondcharge.m.accountNo"/>"
											},
											{
												"mDataProp" : "storeName",
												"sTitle" : "<aebiz:showTitle titleId="storebondcharge.m.accountName"/>"
											},
											
												{
													"mDataProp" : "contractNo",
													"sTitle" : "<aebiz:showTitle titleId="storeservicecharge.m.contractUuid"/>"
												},
												{
													"mDataProp" : "bondUuid",
													"sTitle" : "<aebiz:showTitle titleId="storeservicecharge.m.bondUuid"/>"
												},
												{
													"mDataProp" : "payAmount",
													"sTitle" : "<aebiz:showTitle titleId="storeservicecharge.m.payAmount"/>"
												},
												{
													"mDataProp" : "payType",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="storeservicecharge.m.payType"/>",
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
													"mDataProp" : "payStatus",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="storeservicecharge.m.payStatus"/>",
													"fnRender" : function(obj) {
														var sReturn = "";
														if(obj.aData.payStatus == "0"){
															sReturn = "<aebiz:showTitle titleId="storebondcharge.m.underPay"/>";
														}else if(obj.aData.payStatus == "1"){
															sReturn = "<aebiz:showTitle titleId="storebondcharge.m.paySuccess"/>";
														}else if(obj.aData.payStatus == "2"){
															sReturn = "<aebiz:showTitle titleId="storebondcharge.m.payFail"/>";
														}
														return sReturn;
													}
												},
												{
													"mDataProp" : "createTime",
													"sTitle" : "<aebiz:showTitle titleId="storeservicecharge.m.createTime"/>"
												},

												{
													"mDataProp" : "operate",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>",
													"fnRender" : function(obj) {
														var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/storeservicecharge/updateServiceChargeToSuccess/"
																+ obj.aData.uuid
																+ "' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="storebondcharge.m.toSuccess"/>'><aebiz:showTitle titleId="storebondcharge.m.toSuccess"/></a>";
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
										"sAjaxSource" : "${pageContext.servletContext.contextPath}/sysback/storeservicecharge/queryList",
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
		$("#payStatus").val("");

	}

	//清空高级搜索的所有选项
	function clearMoreSearch() {
		$("#contractUuid_s").val("");
		$("#bondUuid_s").val("");
		$("#payAmount_s").val("");
		$("#payType_s").val("");
		$("#payStatus_s").val("");
		$("#payTime_s").val("");
		$("#accountUuid_s").val("");
		$("#createTime_s").val("");
		$("#createTime2_s").val("");

	}

	//删除
	function removes(delId) {
		var checkIds = "";
		if (delId.trim() != "") {
			checkIds = delId;
		} else {
			$("input[name='check']:checkbox").each(function() {
				if ($(this).is(":checked")) {
					checkIds += $(this).val() + ",";
				}
			})
		}

		if (checkIds.trim() == "") {
			//提示为空
			bootbox
					.alert("<aebiz:showTitle titleId="basebusiness.showmessage.chooseDeleteData"/>");
			return;
		}

		bootbox
				.confirm(
						"<aebiz:showTitle titleId="basebusiness.showmessage.confirmDelete"/>",
						function(r) {
							if (r) {
								//删除
								$
										.post(
												"${pageContext.servletContext.contextPath}/sysback/storeservicecharge/deletes",
												{
													"selectOne" : checkIds,
													ranNum : Math.random()
												},
												function(data) {
													var result = eval("("
															+ data + ")");
													if (result.rsp) {
														//刷新
														//bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;
														doSearch('search');
													} else {
														bootbox
																.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
													}
												});
							}
						});
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