<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp"%>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>
					<aebiz:showTitle titleId="storebondcharge.store" /><aebiz:showTitle titleId="storeservicecharge.moduleName_CN" /><aebiz:showTitle titleId="basebusiness.showmessage.manager" />
				</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="storebondcharge.menuOne" /></span> <i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="storebondcharge.store" /><aebiz:showTitle titleId="storebondcharge.menuTwo" /></span> <i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="storebondcharge.store" /><aebiz:showTitle titleId="storeservicecharge.moduleName_CN" /><aebiz:showTitle titleId="basebusiness.showmessage.manager" /></span></li>
			</ul>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<div class="box">
					<div class="box-content nopadding y_tableser">
						<div class="y_clear">
							<div class="form-inline table_formnew">
								<div class="form-group">
									<label class="control-label"><aebiz:showTitle titleId="storebondcharge.m.accountNo" />/<aebiz:showTitle titleId="storebondcharge.m.accountName" />：</label>
									 <input type="text" name="storeNoOrStoreName" id="storeNoOrStoreName" class="form-control">
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
								<button class="btn" id="export" title="<aebiz:showTitle titleId="basebusiness.showmessage.export"/>" rel="tooltip">
									<aebiz:showTitle titleId="basebusiness.showmessage.export" />
								</button>
							</div>
						</div>

						<div class="table-responsive2">
							<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
								<thead>
									<tr>
										<th class='with-checkbox'><input type="checkbox" name="check_all" id="check_all"></th>
										<th><aebiz:showTitle titleId="storebondcharge.m.accountNo" /></th>
										<th><aebiz:showTitle titleId="storebondcharge.m.accountName"/></th>

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
	</div>


<script>
	function retrieveData(sSource, aoData, fnCallback) {
		var searchParam = new Array();
		searchParam.push({ "name": "storeNoOrStoreName", "value": $.trim($("#storeNoOrStoreName").val())});
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
				$('.dataTable').each(
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
								'aTargets' : [ 0,1,2,3]
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
										"mDataProp" : "storeUuid",
										"sTitle" : "<aebiz:showTitle titleId="storebondcharge.m.accountNo"/>"
									},
									{
										"mDataProp" : "storeName",
										"sTitle" : "<aebiz:showTitle titleId="storebondcharge.m.accountName"/>"
									},

									{
										"mDataProp" : "operate",
										"sDefaultContent" : "",
										"sTitle" : "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>",
										"fnRender" : function(obj) {
											var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/storeservicecharge/toSuccessList/"
													+ obj.aData.uuid
													+ "' class='btn btn-primary' rel='tooltip' title='<aebiz:showTitle titleId="storebondcharge.m.detail"/>'><aebiz:showTitle titleId="storebondcharge.m.detail"/></a>";
											return sReturn;
										}
									} ],

							'oTableTools' : {
								"sSwfPath" : "${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
							},
							"bRetrieve" : true,
							"bProcessing" : true,
							"bServerSide" : true, //指定从服务器端获取数据
							"sAjaxSource" : "${pageContext.servletContext.contextPath}/sysback/storeservicecharge/queryStoreList",
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
		doSearch('search');
	})

	
	//导出
	$("#export").click(function() {
		alert(11111111111111);
	})
	
	function clearSearch() {
		$("#storeNoOrStoreName").val("");

	}
	

	$(document).ready(function() {

		//初始化表格
		doSearch('inittable');

		//复选框全选
		$("#check_all").click(function(e) {
			$('input', oTable.fnGetNodes()).prop('checked', this.checked);
		});

	})
	
	
</script>
</body>
</html>
