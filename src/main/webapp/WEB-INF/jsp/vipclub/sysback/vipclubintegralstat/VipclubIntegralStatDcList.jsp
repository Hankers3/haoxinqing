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
					医生积分管理
				</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li><span>医生系统</span> <i class="fa fa-angle-right"></i></li>
				<li><span>医生积分管理</span> <i class="fa fa-angle-right"></i></li>
				<li><span>医生积分<aebiz:showTitle titleId="basebusiness.showmessage.manager" /></span></li>
			</ul>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<div class="box">
					<div class="box-content nopadding y_tableser">
						<div class="y_clear">
							<div class="form-inline table_formnew">
								<input type="hidden" name="userType" id="userType"  value="2"> 
								<input type="hidden" name="userType_q" id="userType_q"  value="EQ">

								<div class="form-group">
									<label class="control-label">医生姓名：</label>
										 <input type="text" name="customerName" id="customerName" class="form-control"> 
										 <input type="hidden" name="customerName_q" id="customerName_q" class="form-control" value="Like">
								</div>
								
								<div class="form-group">
									<label class="control-label">电话号码：</label>
										 <input type="text" name="" id="" class="form-control"> 
										 <input type="hidden" name="_q" id="customerName_q" class="form-control" value="Like">
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
						</div>
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>
								<tr>
									<th class='with-checkbox'><input type="checkbox" name="check_all" id="check_all"></th>
									<th><aebiz:showTitle titleId="vipclubintegralstat.qm.customerName" /></th>
									<th><aebiz:showTitle titleId="vipclubintegralstat.intergralType.usable" /></th>
									<th><aebiz:showTitle titleId="vipclubintegralstat.intergralType.used" /></th>
									<th><aebiz:showTitle titleId="vipclubintegralstat.intergralType.total" /></th>
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
</body>

</html>

<script>
	function retrieveData(sSource, aoData, fnCallback) {
		var searchParam = new Array();
		searchParam.push({ "name": "userType", "value": $.trim($("#userType").val())});
		searchParam.push({ "name": "userType_q", "value": $.trim($("#userType_q").val())});
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

										//'sDom': "lfrtip",
										'sDom' : "rtlip",
										//"sDom": '<"top"l>rt<"bottom"ip><"clear">',            
										'aoColumnDefs' : [ {
											'bSortable' : false,
											'aTargets' : [0, 1]
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
													"mDataProp" : "doctorName",
													"sTitle" : "医生姓名"
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
													"sWidth" : "15%",
													"mDataProp" : "operate",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>",
													"fnRender" : function(obj) {
														var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/vipclubintegrallog/toLogList/"
																+ obj.aData.doctorUuid
																+ "' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="vipclubintegralstat.message.view"/>'><aebiz:showTitle titleId="vipclubintegralstat.message.view"/></a>";
														
														sReturn += "<a href='${pageContext.servletContext.contextPath}/sysback/vipclubintegrallog/toAdapt/"
																+ obj.aData.doctorUuid
																+ "' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="vipclubintegralstat.message.adapt"/>'><aebiz:showTitle titleId="vipclubintegralstat.message.adapt"/></a>";
														
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
										"sAjaxSource" : "${pageContext.servletContext.contextPath}/sysback/vipclubintegralstat/queryDList",
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
												"${pageContext.servletContext.contextPath}/vipclubintegralstat/deletes",
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