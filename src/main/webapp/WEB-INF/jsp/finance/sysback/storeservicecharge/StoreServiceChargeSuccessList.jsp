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
				<h1><aebiz:showTitle titleId="storeservicecharge.m.serviceChargeDetail"/></h1>
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
					<span><aebiz:showTitle titleId="storeservicecharge.m.serviceChargeDetail"/></span>
				</li>						
			</ul>				
		</div>				
							
		<input type="hidden" name="payStatus" id="payStatus" value="1">
		<input type="hidden" name="payStatus_q" id="payStatus_q" class="form-control" value="EQ">
		<!-- 将关联的商户的uuid传到queryModel中 -->
		<input type="hidden" name="storeUuid" id="storeUuid" value="${accountUuid}"/>
		<input type="hidden" name="storeUuid_q" id="storeUuid_q" value="EQ"/>
		<div class="row">
				<div class="col-sm-12">
					<div class="box">
						<div class="box-content nopadding y_tableser">
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th class='with-checkbox'>
										<input type="checkbox" name="check_all" id="check_all">
									</th>
									<th><aebiz:showTitle titleId="storeservicecharge.m.contractUuid"/></th>
									<th><aebiz:showTitle titleId="storeservicecharge.m.bondUuid"/></th>
									<th><aebiz:showTitle titleId="storeservicecharge.m.payAmount"/></th>
									<th><aebiz:showTitle titleId="storeservicecharge.m.payType"/></th>
									<th><aebiz:showTitle titleId="storeservicecharge.m.payTime"/></th>
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
	
	
	
</body>

</html>

<script>
function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
	
	searchParam.push({
			"name" : "payStatus_q",
			"value" : $.trim($("#payStatus_q").val())
		});
		searchParam.push({
			"name" : "payStatus",
			"value" : $.trim($("#payStatus").val())
		});
		searchParam.push({
			"name" : "accountUuid",
			"value" : $.trim($("#storeUuid").val())
		});
		searchParam.push({
			"name" : "accountUuid_q",
			"value" : $.trim($("#storeUuid_q").val())
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

	var oTable = null;
	function doSearch(operact) {
		if (oTable == null) {
			if ($('.dataTable').length > 0) {
				$('.dataTable')
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
											'aTargets' : [ 0, 1, 2, 4 ]
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
														if (obj.aData.payType == "1") {
															sReturn = "<aebiz:showTitle titleId="storebondcharge.m.online"/>";
														} else if (obj.aData.payType == "2") {
															sReturn = "<aebiz:showTitle titleId="storebondcharge.m.offline"/>";
														}
														return sReturn;
													}
												},
												{
													"mDataProp" : "payTime",
													"sTitle" : "<aebiz:showTitle titleId="storeservicecharge.m.payTime"/>"
												}

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