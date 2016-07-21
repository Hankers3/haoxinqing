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
					<aebiz:showTitle titleId="productManagerList.title.audit" /> 
				</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="productmain.menuOne" /></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="productmain.menuTwo" /></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="productList.title" /></span></li>
			</ul>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<div class="box">
					<div class="box-content nopadding y_tableser">
						<div class="y_clear">
							<div class="form-inline table_formnew">
									<input type="hidden" name="auditState" id="auditState" value="${web.auditState}">
								<div class="form-group">
									<label class="control-label"><aebiz:showTitle titleId="productmain.qmf.productName" />：</label>
									<input type="text" name="productName" id="productName" class="form-control">
								</div>
								
								<div class="form-group">
									<label class="control-label"><aebiz:showTitle titleId="productmain.m.productNo" />：</label>
									<input type="text" name="productNo" id="productNo" class="form-control">
								</div>
								
								<div class="form-group">
									<label class="control-label"> <aebiz:showTitle titleId="productmainbaseprice.m.price" />：</label>
									<input type="text" name="shopPrice" id="shopPrice" class="form-control y_jgipt">
									<label class="y_timeto">-</label>
									<input type="text" name="shopPrice1" id="shopPrice1" class="form-control y_jgipt">
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
								<button class="btn" onclick="javascript:removes('');" title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>" rel="tooltip">
									<aebiz:showTitle titleId="basebusiness.showmessage.delete" />
								</button>
							</div>
						</div>

						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>
								<tr>
									<th class='with-checkbox'><input type="checkbox" name="check_all" id="check_all"></th>
									<th><aebiz:showTitle titleId="productmain.m.productName" /></th>
									<th><aebiz:showTitle titleId="productmain.m.productNo" /></th>
									<th><aebiz:showTitle titleId="productmainbaseprice.m.shopPrice" /></th>
									<th><aebiz:showTitle titleId="productattrstock.m.stock" /></th>
									<th><aebiz:showTitle titleId="productmain.m.categoryUuid" /></th>
									<th><aebiz:showTitle titleId="productmain.m.shelvesTime" /></th>
									<th><aebiz:showTitle titleId="productSuit.m.status" /></th>
									<th><aebiz:showTitle titleId="productmainaudit.m.auditState" /></th>
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
	//下架功能
	function undercarriadge(delId){
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
			bootbox.alert("<aebiz:showTitle titleId="productList.noSelect.Undercarriadge"/>");
			return;
		}
		bootbox.confirm("<aebiz:showTitle titleId="productList.Undercarriadge.ok"/>",
		function(r) {
			if (r) {
				//删除
				$.getJSON(
					"${pageContext.servletContext.contextPath}/store/product/updateUnStateShelves",
					{
						"selectOne" : checkIds,
						ranNum : Math.random()
					},
					function(data) {
						if(data.product_error!=null){
							bootbox.alert(data.product_error);
							return;
						}
						
						if (data.success) {
							//刷新
							bootbox.alert('<aebiz:showTitle titleId="productList.Undercarriadge.success"/>') ;
							doSearch('search');
						} else {
							bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
						}
					});
			}
		});			
	}
	
	function retrieveData(sSource, aoData, fnCallback) {
		var searchParam = new Array();
		searchParam.push({
			"name" : "auditState",
			"value" : $.trim($("#auditState").val())
		});
		searchParam.push({
			"name" : "state",
			"value" : $.trim($("#state").val())
		});
		
	
		searchParam.push({
			"name" : "productName",
			"value" : $.trim($("#productName").val())
		});
		
		searchParam.push({	
			"name" : "productName_s",
			"value" : $.trim($("#productName_s").val())
		});
		
		searchParam.push({
			"name" : "shopPrice",
			"value" : $.trim($("#shopPrice").val())
		});
		
		searchParam.push({
			"name" : "shopPrice_s",
			"value" : $.trim($("#shopPrice_s").val())
		});
		
		searchParam.push({
			"name" : "shopPrice1",
			"value" : $.trim($("#shopPrice1").val())
		});
		searchParam.push({
			"name" : "shopPrice1_s",
			"value" : $.trim($("#shopPrice1_s").val())
		});
	
		searchParam.push({
			"name" : "productNo",
			"value" : $.trim($("#productNo").val())
		}); 
		

		searchParam.push({
			"name" : "productNo_s",
			"value" : $.trim($("#productNo_s").val())
		}); 
		
		searchParam.push({
			"name" : "shelvesTime",
			"value" : $.trim($("#shelvesTime").val())
		}); 

		
		searchParam.push({
			"name" : "shelvesTime1",
			"value" : $.trim($("#shelvesTime1").val())
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
											'aTargets' : [ 0, 1 ]
										} ],
										"bSort" : true, //排序功能            
										"aoColumns" : [
												{
													"mDataProp" : "checkbox",
													"sDefaultContent" : "",
													"fnRender" : function(obj) {
														var sReturn = "<input type='checkbox' name='check' value='"+obj.aData.productMain.uuid+"' />";
														return sReturn;
													}
												},
												{
													"mDataProp" : "productMain.productName",
													"sTitle" : "<aebiz:showTitle titleId="productmain.m.productName"/>"
												},
												{
													"mDataProp" : "productMain.productNo",
													"sTitle" : "<aebiz:showTitle titleId="productmain.m.productNo"/>"
												},
												{
													"mDataProp" : "productPrice.shopPrice",
													"sTitle" : "<aebiz:showTitle titleId="productmainbaseprice.m.shopPrice"/>"
												},
												{
													"mDataProp" : "productMain.stock",
													"sTitle" : "<aebiz:showTitle titleId="productattrstock.m.stock"/>"
												},
												{
													"mDataProp" : "productMain.categoryName",
													"sTitle" : "<aebiz:showTitle titleId="productmain.m.categoryUuid"/>"
												},
												{
													"mDataProp" : "productMain.shelvesTime",
													"sTitle" : "<aebiz:showTitle titleId="productmain.m.shelvesTime"/>"
												},
												{
													"mDataProp" : "productMain.stateName",
													"sTitle" : "<aebiz:showTitle titleId="productSuit.m.status"/>"
												},
												{
													"mDataProp" : "productMain.auditName",
													"sTitle" : "<aebiz:showTitle titleId="productmainaudit.m.auditState"/>"
												},
												{
													"mDataProp" : "operate",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>",
													"fnRender" : function(obj) {
														var sReturn = "<a href='${pageContext.servletContext.contextPath}/product/toAudit/"
																+ obj.aData.productMain.uuid
																+ "' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="productManagerList.button.audit"/>'><aebiz:showTitle titleId="productManagerList.button.audit"/></a>";
														sReturn += "<a href='${pageContext.servletContext.contextPath}/product/toView/"
																+ obj.aData.productMain.uuid
																+ "' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="productManagerList.button.view"/>'><aebiz:showTitle titleId="productManagerList.button.view"/></a>";
														return sReturn;
													}
												} ],

										//'oColVis': {
										// "buttonText": "Change columns <i class='icon-angle-down'></i>"
										//},
										'oTableTools' : {
											"sSwfPath" : "${pageContext.servletContext.contextPath}/static/storeback/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
										},
										"bRetrieve" : true,
										"bProcessing" : true,
										"bServerSide" : true, //指定从服务器端获取数据
										"sAjaxSource" : "${pageContext.servletContext.contextPath}/sysback/product/queryList",
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
		$("#productName").val("");
		$("#productNo").val("");
		$("#shopPrice").val("");
		$("#shopPrice1").val("");

	}

	//清空高级搜索的所有选项
	function clearMoreSearch() {
		$("#productName_s").val("");
		$("#productNo_s").val("");
		$("#shopPrice_s").val("");
		$("#shopPrice1_s").val("");
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
												"${pageContext.servletContext.contextPath}/store/productmain/deletes",
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