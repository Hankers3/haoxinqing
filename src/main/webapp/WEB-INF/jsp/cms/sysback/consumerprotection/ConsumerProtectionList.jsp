<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp"%>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>
</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>
					<aebiz:showTitle titleId="consumerprotection.moduleName_CN" />
				</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span>
						<aebiz:showTitle titleId="consumerprotection.menuOne" />
					</span> 
					<i class="fa fa-angle-right"></i></li>
				<li>
					<span>
						<aebiz:showTitle titleId="consumerprotection.menuTwo" />
					</span> 
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>
						<aebiz:showTitle titleId="consumerprotection.moduleName_CN" /> 
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
										<aebiz:showTitle titleId="consumerprotection.qmf.name" />：
									</label> 
									<input type="text" name="name" id="name" class="form-control"> 
									<input type="hidden" name="name_q" id="name_q" class="form-control" value="Like">
								</div>

								<div class="form-group">
									<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip">
										<aebiz:showTitle titleId="basebusiness.showmessage.query" />
									</button>
									<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip">
										<aebiz:showTitle titleId="basebusiness.showmessage.clear" />
									</button>
									<button class="btn" id="searchshow" title="<aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/>" rel="tooltip" data-toggle="modal" data-target="#modal-user">
										<aebiz:showTitle
											titleId="basebusiness.showmessage.seniorQuery" />
									</button>
								</div>
							</div>

							<div class="y_tablebtn">
								<a class="btn" href="${pageContext.servletContext.contextPath}/sysback/consumerprotection/toAdd" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.newAdd" /></a>
								<button class="btn" onclick="javascript:removes('');" title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>" rel="tooltip">
									<aebiz:showTitle titleId="basebusiness.showmessage.delete" />
								</button>
							</div>
						</div>

						<table
							class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>
								<tr>
									<th class='with-checkbox'><input type="checkbox" name="check_all" id="check_all"></th>
									<th><aebiz:showTitle titleId="consumerprotection.m.name" /></th>
									<th><aebiz:showTitle titleId="consumerprotection.m.icon" /></th>
									<th><aebiz:showTitle titleId="consumerprotection.m.needChooseProduct" /></th>
									<th><aebiz:showTitle titleId="consumerprotection.m.needAgreeProtocol" /></th>
									<th><aebiz:showTitle titleId="consumerprotection.m.protocolContent" /></th>
									<th><aebiz:showTitle titleId="consumerprotection.m.note" /></th>
									<th><aebiz:showTitle titleId="consumerprotection.m.state" /></th>
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
								<label for="textfield" class="control-label">
									<aebiz:showTitle titleId="consumerprotection.m.name" />
								</label> 
								<input type="text" name="name_s" id="name_s" class="form-control"> 
								<input type="hidden" name="name_q" id="name_q" value="Like">
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="consumerprotection.m.needChooseProduct" /></label> 
								
							<select id="needChooseProduct_s" name="needChooseProduct_s" class="form-control">
								<option value=""><aebiz:showTitle titleId="consumerprotection.m.select"/></option>
								<option value="0"><aebiz:showTitle titleId="basebusiness.showmessage.no"/></option>
								<option value="1"><aebiz:showTitle titleId="basebusiness.showmessage.yes"/></option>
							</select>
							<input type="hidden" name="needChooseProduct_q" id="needChooseProduct_q" value="EQ">
							</div>
						</div>
						
					</div>
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<label for="textfield" class="control-label">
									<aebiz:showTitle titleId="consumerprotection.m.needAgreeProtocol" />
								</label> 
								<select id="needAgreeProtocol_s" name="needAgreeProtocol_s" class="form-control">
									<option value=""><aebiz:showTitle titleId="consumerprotection.m.select"/></option>
									<option value="0"><aebiz:showTitle titleId="basebusiness.showmessage.no"/></option>
									<option value="1"><aebiz:showTitle titleId="basebusiness.showmessage.yes"/></option>
								</select> 
								<input type="hidden" name="needAgreeProtocol_q" id="needAgreeProtocol_q" value="EQ">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<label for="textfield" class="control-label">
									<aebiz:showTitle titleId="consumerprotection.m.state" />
								</label> 
								<select id="state_s" name="state_s" class="form-control">
									<option value=""><aebiz:showTitle titleId="consumerprotection.m.select"/></option>
									<option value="0"><aebiz:showTitle titleId="basebusiness.showmessage.no"/></option>
									<option value="1"><aebiz:showTitle titleId="basebusiness.showmessage.yes"/></option>
								</select>
								<input type="hidden" name="state_q" id="state_q" value="EQ">
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
		searchParam.push({
			"name" : "name_s",
			"value" : $.trim($("#name_s").val())
		});
		searchParam.push({
			"name" : "name_q",
			"value" : $.trim($("#name_q").val())
		});
		searchParam.push({
			"name" : "needChooseProduct_s",
			"value" : $.trim($("#needChooseProduct_s").val())
		});
		searchParam.push({
			"name" : "needChooseProduct_q",
			"value" : $.trim($("#needChooseProduct_q").val())
		});
		searchParam.push({
			"name" : "needAgreeProtocol_s",
			"value" : $.trim($("#needAgreeProtocol_s").val())
		});
		searchParam.push({
			"name" : "needAgreeProtocol_q",
			"value" : $.trim($("#needAgreeProtocol_q").val())
		});
		searchParam.push({
			"name" : "state_s",
			"value" : $.trim($("#state_s").val())
		});
		searchParam.push({
			"name" : "state_q",
			"value" : $.trim($("#state_q").val())
		});
		searchParam.push({
			"name" : "name",
			"value" : $.trim($("#name").val())
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
							'aTargets' : [ 0, 2,5,6,8 ]
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
							"mDataProp" : "name",
							"sTitle" : "<aebiz:showTitle titleId="consumerprotection.m.name"/>"
						},
						{
							"mDataProp" : "iconUrl",
							"sWidth" : "13%",
							"sDefaultContent" : "",
							"sTitle" : "<aebiz:showTitle titleId="consumerprotection.m.icon"/>",
							"fnRender" : function(obj) {
								var sReturn = "";
								if (obj.aData.iconUrl != "") {
									sReturn = "<img src='"+obj.aData.iconUrl+"' width='75px' height='75px;' />";
								}
								return sReturn;
							}
						},
						{
							"mDataProp" : "needChooseProduct",
							"sDefaultContent" : "",
							"sTitle" : "<aebiz:showTitle titleId="consumerprotection.m.needChooseProduct"/>",
							"fnRender" : function(obj) {
								var sReturn = "";
								if(obj.aData.needChooseProduct==0){
									sReturn +="<aebiz:showTitle titleId="basebusiness.showmessage.no"/>"
								}else{
									sReturn +="<aebiz:showTitle titleId="basebusiness.showmessage.yes"/>"
								}
								return sReturn;
							}
						},
						{
							"mDataProp" : "needAgreeProtocol",
							"sDefaultContent" : "",
							"sTitle" : "<aebiz:showTitle titleId="consumerprotection.m.needAgreeProtocol"/>",
							"fnRender" : function(obj) {
								var sReturn = "";
								if(obj.aData.needAgreeProtocol==0){
									sReturn +="<aebiz:showTitle titleId="basebusiness.showmessage.no"/>"
								}else{
									sReturn +="<aebiz:showTitle titleId="basebusiness.showmessage.yes"/>"
								}
								return sReturn;
							}
						},
						{
							"mDataProp" : "protocolContent",
							"sTitle" : "<aebiz:showTitle titleId="consumerprotection.m.protocolContent"/>"
						},
						{
							"mDataProp" : "note",
							"sTitle" : "<aebiz:showTitle titleId="consumerprotection.m.note"/>"
						},
						{
							"mDataProp" : "state",
							"sDefaultContent" : "",
							"sTitle" : "<aebiz:showTitle titleId="consumerprotection.m.state"/>",
							"fnRender" : function(obj) {
								var sReturn = "";
								if(obj.aData.state==0){
									sReturn +="<aebiz:showTitle titleId="basebusiness.showmessage.no"/>"
								}else{
									sReturn +="<aebiz:showTitle titleId="basebusiness.showmessage.yes"/>"
								}
								return sReturn;
							}
						},
						{
							"mDataProp" : "operate",
							"sDefaultContent" : "",
							"sTitle" : "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>",
							"fnRender" : function(obj) {
								var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/consumerprotection/toUpdate/"
										+ obj.aData.uuid
										+ "' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>";
								sReturn += "<a href='javascript:removes(\""
										+ obj.aData.uuid
										+ "\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>'><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></a>";
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
						"sAjaxSource" : "${pageContext.servletContext.contextPath}/sysback/consumerprotection/queryList",
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
		$("#name").val("");
	}

	//清空高级搜索的所有选项
	function clearMoreSearch() {
		$("#name_s").val("");
		$("#needChooseProduct_s").val("");
		$("#needAgreeProtocol_s").val("");
		$("#state_s").val("");
		$("#_s").val("");
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
			bootbox.alert("<aebiz:showTitle titleId="basebusiness.showmessage.chooseDeleteData"/>");
			return;
		}

		bootbox.confirm(
			"<aebiz:showTitle titleId="basebusiness.showmessage.confirmDelete"/>",
		function(r) {
			if (r) {
				//删除
				$.post(
					"${pageContext.servletContext.contextPath}/sysback/consumerprotection/deletes",
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
							bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
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