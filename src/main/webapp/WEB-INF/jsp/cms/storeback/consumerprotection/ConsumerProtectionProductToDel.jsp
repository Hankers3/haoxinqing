<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/import/ListImport.jsp"%>


<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/storeback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/aebiz/aebiz.checkbox.js"></script>
</head>

<body>
	<div id="content" class="container-fluid">
		<div id="main">
			<div class="container-fluid">
				<div class="page-header">
					<div class="pull-left">
						<h1>
							<aebiz:showTitle titleId="storeConsumerProtection.moduleName_CN3" />
						</h1>
					</div>
				</div>
				<div class="breadcrumbs">
					<ul>
						<li>
							<span>
								<aebiz:showTitle titleId="storeConsumerProtection.menuOne" />
							</span> 
							<i class="fa fa-angle-right"></i></li>
						<li>
							<span>
								<aebiz:showTitle titleId="storeConsumerProtection.menuTwo" />
							</span> 
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<span>
								<aebiz:showTitle titleId="storeConsumerProtection.moduleName_CN3" /> 
							</span>
						</li>
					</ul>
				</div>
		
				<div class="row">
					<div class="col-sm-12">
						<div class="box">
							<div class="box-content nopadding y_tableser">
								<div class="y_clear">
									<div class="form-inline table_formnew">
										<div class="form-group">
											
											<label class="control-label">
												<aebiz:showTitle titleId="storeConsumerProtection.productNo" />：
											</label> 
											<input type="text" name="productNo" id="productNo" class="form-control"> 
											<input type="hidden" name="productNo_q" id="productNo_q" class="form-control" value="Like">
											
											<label class="control-label">
												<aebiz:showTitle titleId="storeConsumerProtection.productName" />：
											</label> 
											<input type="text" name="productName" id="productName" class="form-control"> 
											<input type="hidden" name="productName_q" id="productName_q" class="form-control" value="Like">
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
		 						<div class="y_tablebtn">
									<a class="btn" href="${pageContext.servletContext.contextPath}/store/consumerprotection/searchStoreConProtect" title="<aebiz:showTitle titleId="basebusiness.showmessage.return"/>" rel="tooltip">
										<aebiz:showTitle titleId="basebusiness.showmessage.return"/>
									</a>
								</div>
								<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
									<thead>
										<tr>
											<th class='with-checkbox'><input type="checkbox" name="check_all" id="check_all"></th>
											<th><aebiz:showTitle titleId="storeConsumerProtection.productNo" /></th>
											<th><aebiz:showTitle titleId="storeConsumerProtection.productName" /></th>
											<th><aebiz:showTitle titleId="storeConsumerProtection.shopPrice" /></th>
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
		</div>
	</div>
	
</body>

</html>

<script>
	function retrieveData(sSource, aoData, fnCallback) {
		var searchParam = new Array();
		searchParam.push({
			"name" : "productNo",
			"value" : $.trim($("#productNo").val())
		});
		searchParam.push({
			"name" : "productNo_q",
			"value" : $.trim($("#productNo_q").val())
		});
		searchParam.push({
			"name" : "productName",
			"value" : $.trim($("#productName").val())
		});
		searchParam.push({
			"name" : "productName_q",
			"value" : $.trim($("#productName_q").val())
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
							'aTargets' : [0,1,2,3,4]
						} ],
						"bSort" : true, //排序功能            
						"aoColumns" : [
						{
							"mDataProp" : "checkbox",
							"sDefaultContent" : "",
							"fnRender" : function(obj) {
								var sReturn = "<input type='checkbox' name='check' value='"+obj.aData.productUuid+"' />";
								return sReturn;
							}
						},
						{
							"mDataProp" : "productNo",
							"sTitle" : "<aebiz:showTitle titleId="storeConsumerProtection.productNo"/>"
						},
						{
							"mDataProp" : "productName",
							"sTitle" : "<aebiz:showTitle titleId="storeConsumerProtection.productName"/>"
						},
						{
							"mDataProp" : "shopPrice",
							"sTitle" : "<aebiz:showTitle titleId="storeConsumerProtection.shopPrice"/>"
						},
						{
							"mDataProp" : "operate",
							"sDefaultContent" : "",
							"sTitle" : "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>",
							"fnRender" : function(obj) {
								var sReturn = "<a href='javascript:removeProduct(\""
								+ obj.aData.productUuid + 
								"\");' class='btn' rel='tooltip' id='"+obj.aData.productUuid+"' title='<aebiz:showTitle titleId="storeConsumerProtection.remove" />'><aebiz:showTitle titleId="storeConsumerProtection.remove"/></a>";
								return sReturn;
							}
						}],

						//'oColVis': {
						// "buttonText": "Change columns <i class='icon-angle-down'></i>"
						//},
						'oTableTools' : {
							"sSwfPath" : "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
						},
						"bRetrieve" : true,
						"bProcessing" : true,
						"bServerSide" : true, //指定从服务器端获取数据
						"sAjaxSource" : "${pageContext.servletContext.contextPath}/store/consumerprotection/protectionProductDelQueryList/${protectionUuid}",
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

	function clearSearch() {
		$("#productName").val("");
		$("#productNo").val("");
	}



	//关联商品
	function removeProduct(productUuid){
		var queryUrl="${pageContext.servletContext.contextPath}/store/consumerprotection/consumerProtectionProductDel";
		 	$.getJSON(queryUrl,{"productUuid":productUuid,"protectionUuid":"${protectionUuid}",ranNum:Math.random()},function(data){
		 		if(data == "success"){
		 			$("#"+productUuid).html('<aebiz:showTitle titleId="storeConsumerProtection.removed"/>')
		 			$("#"+productUuid).attr("href","###");
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