<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp"%>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>
					<aebiz:showTitle titleId="storebondcharge.m.bondChargeDetail" />
				</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="storebondcharge.menuOne" /></span> <i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="storebondcharge.store" /><aebiz:showTitle titleId="storebondcharge.menuTwo" /></span> <i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="storebondcharge.m.bondChargeDetail" /></span></li>
			</ul>
		</div>

		<input type="hidden" name="operState" id="operState" value="0"/>
		<div class="row">
			<div class="col-sm-12">
				<div class="box">
					<div class="box-content nopadding y_tableser">
						<div class="y_clear">
							<div class="form-inline table_formnew">
							<!-- 将关联的商户的uuid传到queryModel中 -->
								<input type="hidden" name="storeUuid" id="storeUuid" value="${accountUuid}"/>
								<input type="hidden" name="storeUuid_q" id="storeUuid_q" value="EQ"/>
								<div class="form-group">
									<label class="control-label"><aebiz:showTitle titleId="storebondcharge.qmf.storeNameOrNo" />：</label> 
									<input type="text" name="storeNameOrNo" id="storeNameOrNo" class="form-control">
								</div>
								<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="storebondcharge.m.contractUuid" /></label> 
									<input type="text" name="contractUuid" id="contractUuid" class="form-control">
									 <input type="hidden" name="contractUuid_q" id="contractUuid_q" value="EQ">
								</div>

								<div class="form-group">
									<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip">
										<aebiz:showTitle titleId="basebusiness.showmessage.query" />
									</button>
									<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip">
										<aebiz:showTitle titleId="basebusiness.showmessage.clear" />
									</button>
									<button class="btn" id="searchshow" title="<aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/>" rel="tooltip"
										data-toggle="modal" data-target="#modal-user">
										<aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery" />
									</button>
								</div>
							</div>

						</div>

						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>
								<tr>
									<th class='with-checkbox'><input type="checkbox" name="check_all" id="check_all"></th>
									<th><aebiz:showTitle titleId="storebondcharge.m.accountNo" /></th>
									<th><aebiz:showTitle titleId="storebondcharge.m.accountName" /></th>
									<th><aebiz:showTitle titleId="storebondcharge.m.operAmount" /></th>
									<th><aebiz:showTitle titleId="storebondcharge.m.operState" /></th>
									<th><aebiz:showTitle titleId="storebondcharge.m.fromSource" /></th>
									<th><aebiz:showTitle titleId="storebondcharge.m.payType" /></th>
									<th><aebiz:showTitle titleId="storebondcharge.m.contractUuid" /></th>
									<th><aebiz:showTitle titleId="storebondcharge.m.createTime" /></th>

									<th><aebiz:showTitle titleId="basebusiness.showmessage.operate" /></th>
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
					<h4 id="user-infos">
						<aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery" />
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
								<label class="control-label"><aebiz:showTitle titleId="storebondcharge.qmf.storeNameOrNo" />：</label> 
									<input type="text" name="storeNameOrNo_s" id="storeNameOrNo_s" class="form-control">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="storebondcharge.m.contractUuid" /></label> 
								<input type="text" name="contractUuid_s" id="contractUuid_s" class="form-control">
								 <input type="hidden" name="contractUuid_q" id="contractUuid_q" value="EQ">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="storebondcharge.m.bondUuid" /></label> <input type="text"
									name="bondUuid_s" id="bondUuid_s" class="form-control"> <input type="hidden" name="bondUuid_q" id="bondUuid_q" value="EQ">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="storebondcharge.m.operType" /></label>
								<select name="operType_s" id="operType_s" class='select2-me form-control'>								
									<option value="">--<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>--</option>
									<option value="0"><aebiz:showTitle titleId="basebusiness.showmessage.add"/></option>
									<option value="1"><aebiz:showTitle titleId="basebusiness.showmessage.rebate"/></option>
								</select>	
								<input type="hidden" name="operType_q" id="operType_q" value="EQ">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="storebondcharge.m.operState" /></label>
								<select name="operState_s" id="operState_s" class="form-control">
									<option value="">--<aebiz:showTitle titleId="basebusiness.showmessage.all" />--</option>
									<c:forEach items="${webModel.stateList}" var="state">
										<option value="${state.value }">${state.name }</option>
									</c:forEach>
								</select>
								 <input type="hidden" name="operState_q" id="operState_q" value="EQ">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="storebondcharge.m.fromSource" /></label> 
								<select name="fromSource_s" id="fromSource_s" class="form-control">
									<option value="">--<aebiz:showTitle titleId="basebusiness.showmessage.all" />--</option>
									<c:forEach items="${webModel.sourceList}" var="source">
										<option value="${source.value }">${source.name }</option>
									</c:forEach>
								</select>
								<input type="hidden" name="fromSource_q" id="fromSource_q" value="EQ">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="storebondcharge.m.createTimeGE" /></label> 
								<input type="text" name="createTime_s" id="createTime_s" class="form-control  datepick">
							    <input type="hidden" name="createTime_q" id="createTime_q" value="GE">
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="storebondcharge.qm.createTime2" /></label>
								 <input type="text" name="createTime2_s" id="createTime2_s" class="form-control  datepick"> 
								 <input type="hidden" name="createTime2_q" id="createTime2_q" value="LT">
							</div>
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button class="btn moresearch btn-primary" data-dismiss="modal">
						<aebiz:showTitle titleId="basebusiness.showmessage.query" />
					</button>
					<button class="btn clearMoreSearch">
						<aebiz:showTitle titleId="basebusiness.showmessage.clear" />
					</button>
				</div>
			</div>
		</div>
	</div>
	<!--高级搜索-->

<script>
	function retrieveData(sSource, aoData, fnCallback) {
		var searchParam = new Array();
		searchParam.push({
			"name" : "accountUuid",
			"value" : $.trim($("#storeUuid").val())
		});
		/* 待支付状态的 */
		searchParam.push({
			"name" : "operState",
			"value" : $.trim($("#operState").val())
		});
		searchParam.push({
			"name" : "accountUuid_q",
			"value" : $.trim($("#storeUuid_q").val())
		});
		searchParam.push({
			"name" : "contractUuid_s",
			"value" : $.trim($("#contractUuid_s").val())
		});
		searchParam.push({
			"name" : "contractUuid_q",
			"value" : $.trim($("#contractUuid_q").val())
		});
		searchParam.push({
			"name" : "bondUuid_s",
			"value" : $.trim($("#bondUuid_s").val())
		});
		searchParam.push({
			"name" : "bondUuid_q",
			"value" : $.trim($("#bondUuid_q").val())
		});
		searchParam.push({
			"name" : "operType_s",
			"value" : $.trim($("#operType_s").val())
		});
		searchParam.push({
			"name" : "operType_q",
			"value" : $.trim($("#operType_q").val())
		});
		searchParam.push({
			"name" : "operState_s",
			"value" : $.trim($("#operState_s").val())
		});
		searchParam.push({
			"name" : "operState_q",
			"value" : $.trim($("#operState_q").val())
		});
		searchParam.push({
			"name" : "fromSource_s",
			"value" : $.trim($("#fromSource_s").val())
		});
		searchParam.push({
			"name" : "fromSource_q",
			"value" : $.trim($("#fromSource_q").val())
		});
		searchParam.push({
			"name" : "storeNameOrNo",
			"value" : $.trim($("#storeNameOrNo").val())
		});
		searchParam.push({
			"name" : "storeNameOrNo_s",
			"value" : $.trim($("#storeNameOrNo_s").val())
		});
		searchParam.push({
			"name" : "createTime_s",
			"value" : $.trim($("#createTime_s").val())
		});
		searchParam.push({
			"name" : "createTime_q",
			"value" : $.trim($("#createTime_q").val())
		});
		searchParam.push({
			"name" : "createTime2_s",
			"value" : $.trim($("#createTime2_s").val())
		});
		searchParam.push({
			"name" : "createTime2_q",
			"value" : $.trim($("#createTime2_q").val())
		});
		searchParam.push({
			"name" : "storeNameOrNo",
			"value" : $.trim($("#storeNameOrNo").val())
		});
		searchParam.push({
			"name" : "contractUuid",
			"value" : $.trim($("#contractUuid").val())
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
											'aTargets' : [ 0, 1 ,2,5,6,7,9]
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
													"mDataProp" : "storeNo",
													"sTitle" : "<aebiz:showTitle titleId="storebondcharge.m.accountNo"/>"
												},
												{
													"mDataProp" : "storeName",
													"sTitle" : "<aebiz:showTitle titleId="storebondcharge.m.accountName"/>"
												},
												{
													"mDataProp" : "operAmount",
													"sTitle" : "<aebiz:showTitle titleId="storebondcharge.m.operAmount"/>"
												},
												{
													"mDataProp" : "operState",
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
													"mDataProp" : "contractNo",
													"sTitle" : "<aebiz:showTitle titleId="storebondcharge.m.contractUuid"/>"
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
														var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/storebondcharge/updateBondChargeToSuccess/"
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
										"sAjaxSource" : "${pageContext.servletContext.contextPath}/sysback/storebondcharge/queryList",
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
		$("#storeNameOrNo").val("");
		$("#contractUuid").val("");

	}

	//清空高级搜索的所有选项
	function clearMoreSearch() {
		$("#contractUuid_s").val("");
		$("#bondUuid_s").val("");
		$("#storeNameOrNo_s").val("");
		$("#operType_s").val("");
		$("#operState_s").val("");
		$("#fromSource_s").val("");
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
												"${pageContext.servletContext.contextPath}/sysback/storebondcharge/deletes",
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



</body>

</html>