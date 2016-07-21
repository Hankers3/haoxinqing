<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp"%>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="websiteoperate.moduleName_CN" /><aebiz:showTitle titleId="basebusiness.showmessage.manager" /></h1>
			</div>
		</div>
		
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="websiteoperate.menuOne" /></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="websiteoperate.menuTwo" /></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="websiteoperate.moduleName_CN" /><aebiz:showTitle titleId="basebusiness.showmessage.manager" /></span></li>
			</ul>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<div class="box">
					<div class="box-content nopadding y_tableser">
						<div class="y_clear">
							<div class="form-inline table_formnew">
								<div class="form-group">
									<label class="control-label"><aebiz:showTitle titleId="websiteoperate.qmf.moduleName" />：</label> 
									<select name="moduleName" id="moduleName" class='select2-me form-control'>								
										<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</option>
										<c:forEach items="${moduleList}" var="dpp">	
											<option value="${dpp.name}">${dpp.value}</option>
										</c:forEach>										
									</select>
									<input type="hidden" name="moduleName_q" id="moduleName_q" class="form-control" value="EQ">
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
								<a class="btn" href="${pageContext.servletContext.contextPath}/sysback/websiteoperate/toAdd" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>"rel="tooltip">
									<aebiz:showTitle titleId="basebusiness.showmessage.newAdd" />
								</a>
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
									<th><aebiz:showTitle titleId="websiteoperate.m.moduleName" /></th>
									<th><aebiz:showTitle titleId="websiteoperate.m.resourceName" /></th>
									<th><aebiz:showTitle titleId="websiteoperate.m.paramValue" /></th>
									<th><aebiz:showTitle titleId="websiteoperate.m.note" /></th>
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

<script>
	function retrieveData(sSource, aoData, fnCallback) {
		var searchParam = new Array();
		searchParam.push({"name" : "moduleName","value" : $.trim($("#moduleName").val())});
		searchParam.push({"name" : "moduleName_q","value" : $.trim($("#moduleName_q").val())});
		
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
				$('.dataTable').each(function() {
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
											'aTargets' : [ 0 ]
										} ],
										"bSort" : true, //排序功能            
										"aoColumns" : [
												{
													"mDataProp" : "checkbox",
													"sWidth" : "5%",
													"sDefaultContent" : "",
													"fnRender" : function(obj) {
														var sReturn = "<input type='checkbox' name='check' value='"+obj.aData.uuid+"' />";
														return sReturn;
													}
												},
												{
													"mDataProp" : "moduleShowName",
													"sWidth" : "15%",
													"sTitle" : "<aebiz:showTitle titleId="websiteoperate.m.moduleName"/>"
												},
												{
													"mDataProp" : "resourceName",
													"sWidth" : "20%",
													"sTitle" : "<aebiz:showTitle titleId="websiteoperate.m.resourceName"/>"
												},
												{
													"mDataProp" : "paramValueShowName",
													"sWidth" : "20%",
													"sTitle" : "<aebiz:showTitle titleId="websiteoperate.m.paramValue"/>"
												},
												{
													"mDataProp" : "note",
													"sWidth" : "25%",
													"sTitle" : "<aebiz:showTitle titleId="websiteoperate.m.note"/>"
												},
												{
													"mDataProp" : "operate",
													"sWidth" : "15%",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>",
													"fnRender" : function(obj) {
														var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/websiteoperate/toUpdate/"
																+ obj.aData.uuid
																+ "' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>";
														sReturn += "<a href='javascript:removes(\""
																+ obj.aData.uuid
																+ "\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>'><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></a>";
														return sReturn;
													}
												} ],

										'oTableTools' : {
											"sSwfPath" : "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
										},
										"bRetrieve" : true,
										"bProcessing" : true,
										"bServerSide" : true, //指定从服务器端获取数据
										"sAjaxSource" : "${pageContext.servletContext.contextPath}/sysback/websiteoperate/queryList",
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
		doSearch('search');
	})

	function clearSearch() {
		$("#moduleName").val("");
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
							"${pageContext.servletContext.contextPath}/sysback/websiteoperate/deletes",
							{
								"selectOne" : checkIds,
								ranNum : Math.random()
							},
							function(data) {
								var result = eval("("
										+ data + ")");
								if (result.rsp) {
									//刷新
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
	})
</script>

</body>
</html>