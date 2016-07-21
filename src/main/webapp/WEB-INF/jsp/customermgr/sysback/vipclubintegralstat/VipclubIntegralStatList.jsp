<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<%@include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp"%>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>
					<aebiz:showTitle titleId="vipclubintegrallog.ntegralRegulator" /> 
					
				</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="vipclubintegralstat.menuOne" /></span> <i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="vipclubintegralstat.menuTwo" /></span> <i class="fa fa-angle-right"></i></li>
				<li><aebiz:showTitle titleId="vipclubintegrallog.ntegralRegulator" /> </span></li>
			</ul>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<div class="box">
					<div class="box-content nopadding y_tableser">
						<div class="y_clear">
							<div class="form-inline table_formnew">
								<div class="form-group">
									<label class="control-label"><aebiz:showTitle titleId="vipclubintegralstat.qm.customerName" />：</label>
										 <input type="text" name="customerName" id="customerName" class="form-control"> 
										 <input type="hidden" name="customerName_q" id="customerName_q" class="form-control" value="Like">
								</div>
								<div class="form-group">
									<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip">
										<aebiz:showTitle titleId="basebusiness.showmessage.query" />
									</button>
									<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip">
										<aebiz:showTitle titleId="basebusiness.showmessage.clear" />
									</button>
								</div>
							</div>
							<div class="y_tablebtn">
								<a class="btn" href="${pageContext.servletContext.contextPath}/sysback/vipclubintegrallog/toAdd" title="<aebiz:showTitle titleId="vipclubintegralstat.message.add"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/></a>
							<!-- <button class="btn" onclick="javascript:adds('');" title="<aebiz:showTitle titleId="vipclubintegralstat.message.adds"/>" rel="tooltip"><aebiz:showTitle titleId="vipclubintegralstat.message.adds"/></button> -->	
							</div>
						</div>
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>
								<tr>
									<th class='with-checkbox'><input type="checkbox" name="check_all" id="check_all"></th>
									<th><aebiz:showTitle titleId="vipclubintegralstat.qm.customerName" /></th>
									<th><aebiz:showTitle titleId="vipclubintegralstat.intergralType.usable" /></th>
									<th><aebiz:showTitle titleId="vipclubintegralstat.intergralType.used" /></th>
									<th><aebiz:showTitle titleId="vipclubintegralstat.intergralType.total" /></th>
									<th><aebiz:showTitle titleId="vipclubintegralstat.intergralType.get_by_shop" /></th>
									<th><aebiz:showTitle titleId="vipclubintegralstat.intergralType.get_by_behaviour" /></th>
									<th><aebiz:showTitle titleId="vipclubintegralstat.intergralType.get_by_reward" /></th>
									<th class='hidden-480'><aebiz:showTitle titleId="basebusiness.showmessage.operate" /></th>
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
						<div class="col-sm-6">
							<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle
										titleId="vipclubintegralstat.m.customerUuid" /></label> <input
									type="text" name="customerUuid_s" id="customerUuid_s"
									class="form-control"> <input type="hidden"
									name="customerUuid_q" id="customerUuid_q" value="EQ">
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle
										titleId="vipclubintegralstat.m.intergralType" /></label> <input
									type="text" name="intergralType_s" id="intergralType_s"
									class="form-control"> <input type="hidden"
									name="intergralType_q" id="intergralType_q" value="Like">
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="vipclubintegralstat.m.intergralCount" /></label> 
									<input type="text" name="intergralCount_s" id="intergralCount_s" class="form-control"> 
									<input type="hidden" name="intergralCount_q" id="intergralCount_q" value="Like">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="vipclubintegralstat.qm.customerName" /></label> 
									<input type="text" name="customerName_s" id="customerName_s" class="form-control"> 
									<input type="hidden" name="customerName_q" id="customerName_q" value="Like">
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="vipclubintegralstat.qm.intergralMin" /></label> 
									<input type="text" name="intergralMin_s" id="intergralMin_s" class="form-control"> 
									<input type="hidden" name="intergralMin_q" id="intergralMin_q" value="GT">
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="vipclubintegralstat.qm.intergralMax" /></label> 
									<input type="text" name="intergralMax_s" id="intergralMax_s"class="form-control"> 
									<input type="hidden" name="intergralMax_q" id="intergralMax_q" value="LT">
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


</body>

</html>

<script>
	function retrieveData(sSource, aoData, fnCallback) {
		var searchParam = new Array();
		searchParam.push({ "name": "customerName", "value": $.trim($("#customerName").val())});
		searchParam.push({ "name": "customerName_q", "value": $.trim($("#customerName_q").val())});

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

						
										'sDom' : "rtlip",
										'aoColumnDefs' : [ {
											'bSortable' : false,
											'aTargets' : [ 0, 1 ]
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
													"mDataProp" : "customerName",
													"sWidth":"15%",
													"sTitle" : "<aebiz:showTitle titleId="vipclubintegralstat.qm.customerName"/>"
												},
												{
													"mDataProp" : "usableIntegral",
													"sTitle" : "<aebiz:showTitle titleId="vipclubintegralstat.intergralType.usable"/>"
												},
												{
													"mDataProp" : "usedIntegral",
													"sTitle" : "<aebiz:showTitle titleId="vipclubintegralstat.intergralType.used"/>"
												},
												{
													"mDataProp" : "totalIntegral",
													"sTitle" : "<aebiz:showTitle titleId="vipclubintegralstat.intergralType.total"/>"
												},
												{
													"mDataProp" : "shopIntegral",
													"sTitle" : "<aebiz:showTitle titleId="vipclubintegralstat.intergralType.get_by_shop"/>"
												},
												{
													"mDataProp" : "behaviourIntegral",
													"sTitle" : "<aebiz:showTitle titleId="vipclubintegralstat.intergralType.get_by_behaviour"/>"
												},
												{
													"mDataProp" : "rewardIntegral",
													"sTitle" : "<aebiz:showTitle titleId="vipclubintegralstat.intergralType.get_by_reward"/>"
												},

												{
													"mDataProp" : "operate",
													"sDefaultContent" : "",
													"sWidth":"12%",
													"sTitle" : "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>",
													"fnRender" : function(obj) {
														var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/vipclubintegrallog/toLogList/"
																+ obj.aData.customerUuid
																+ "' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="vipclubintegralstat.message.view"/>'><aebiz:showTitle titleId="vipclubintegralstat.message.view"/></a>";
														sReturn += "<a href='${pageContext.servletContext.contextPath}/sysback/vipclubintegrallog/toAdapt/"
																+ obj.aData.customerUuid
																+ "' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="vipclubintegralstat.message.adapt"/>'><aebiz:showTitle titleId="vipclubintegralstat.message.adapt"/></a>";
														return sReturn;
													}
												} ],

										'oTableTools' : {
											"sSwfPath" : "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
										},
										"bRetrieve" : true,
										"bProcessing" : true,
										"bServerSide" : true, //指定从服务器端获取数据
										"sAjaxSource" : "${pageContext.servletContext.contextPath}/sysback/vipclubintegralstat/queryList",
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
		$("#customerName").val("");

	}

	//清空高级搜索的所有选项
	function clearMoreSearch() {
		$("#customerUuid_s").val("");
		$("#intergralType_s").val("");
		$("#intergralCount_s").val("");
		$("#customerName_s").val("");
		$("#intergralMin_s").val("");
		$("#intergralMax_s").val("");

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
												"${pageContext.servletContext.contextPath}/sysback/vipclubintegralstat/deletes",
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