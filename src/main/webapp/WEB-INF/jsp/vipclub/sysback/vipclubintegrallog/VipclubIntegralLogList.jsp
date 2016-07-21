<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@ taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp"%>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>
					积分<aebiz:showTitle titleId="basebusiness.showmessage.manager" />
				</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li><span>积分管理</span> <i class="fa fa-angle-right"></i></li>
				<li><span>积分管理</span> <i class="fa fa-angle-right"></i></li>
			</ul>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<div class="box">
					<div class="box-content nopadding y_tableser">
						<div class="y_clear">
							<div class="form-inline table_formnew">

								<div class="form-group">
									<label class="control-label"><aebiz:showTitle titleId="vipclubintegrallog.m.intergralType" />：</label>
									 <select name="intergralType" id="intergralType" class='form-control' >
										<option value="">-请选择-</option>
										<c:forEach items="${integralType}" var="item">
											<option value="${item.value}">${item.name}</option>
										</c:forEach>
									 </select>
									
									<input type="hidden" name="customerUuid" id="customerUuid" class="form-control" value="${customerUuid}"> 
									<input type="hidden" name="customerUuid_q" id="customerUuid_q" class="form-control" value="EQ"> 
									<input type="hidden" name="intergralType_q" id="intergralType_q" class="form-control" value="EQ"> 
									
									<!--
									<label class="control-label"><aebiz:showTitle titleId="vipclubintegrallog.m.overdueTime" />：</label> 
									<input type="text" name="minTime" id="minTime" class="form-control datepick">-
									<input type="hidden" name="minTime_q" id="minTime_q" class="form-control" value="GT"> 
									<input type="text" name="maxTime" id="maxTime" class="form-control datepick"> 
									<input type="hidden" name="maxTime_q" id="maxTime_q" class="form-control" value="LT">-->
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
							<!--
							<div class="y_tablebtn">
									<a class="btn" href="${pageContext.servletContext.contextPath}/sysback/vipclubintegralstat/toList" title="<aebiz:showTitle titleId="basebusiness.showmessage.return"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.return"/></a>
								</div>-->
						</div>

						<table
							class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>
								<tr>
									<th class='with-checkbox'><input type="checkbox" name="check_all" id="check_all"></th>
									<th>名称</th>
									<th><aebiz:showTitle titleId="vipclubintegrallog.m.intergralType" /></th>
									<th><aebiz:showTitle titleId="vipclubintegrallog.m.intergralCount" /></th>
									<th><aebiz:showTitle titleId="vipclubintegrallog.m.nowIntegral" /></th>
									<th><aebiz:showTitle titleId="vipclubintegrallog.m.description" /></th>
									<th><aebiz:showTitle titleId="vipclubintegrallog.m.createTime" /></th>
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
		searchParam.push({"name" : "customerUuid", "value" : $.trim($("#customerUuid").val())});
		searchParam.push({"name" : "customerUuid_q", "value" : $.trim($("#customerUuid_q").val())});
		searchParam.push({"name" : "intergralType_q","value" : $.trim($("#intergralType_q").val())});
		searchParam.push({"name" : "intergralType","value" : $.trim($("#intergralType").val())});
		//searchParam.push({"name" : "minTime","value" : $.trim($("#minTime").val())});
		//searchParam.push({"name" : "minTime_q","value" : $.trim($("#minTime_q").val())});
		//searchParam.push({"name" : "maxTime","value" : $.trim($("#maxTime").val())});
		//searchParam.push({"name" : "maxTime_q","value" : $.trim($("#maxTime_q").val())});
		
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
													"sWidth" : "5%",
													"sDefaultContent" : "",
													"fnRender" : function(obj) {
														var sReturn = "<input type='checkbox' name='check' value='"+obj.aData.uuid+"' />";
														return sReturn;
													}
												},
												{
													"mDataProp" : "customerName",
													"sWidth" : "10%",
													"sTitle" : "名称"
												},
												{
													"mDataProp" : "typeName",
													"sWidth" : "10%",
													"sTitle" : "<aebiz:showTitle titleId="vipclubintegrallog.m.intergralType"/>"
													
												},
												{
													"mDataProp" : "intergralCount",
													"sWidth" : "5%",
													"sTitle" : "<aebiz:showTitle titleId="vipclubintegrallog.m.intergralCount"/>"
												},
												{
													"mDataProp" : "nowIntegral",
													"sWidth" : "5%",
													"sTitle" : "<aebiz:showTitle titleId="vipclubintegrallog.m.nowIntegral"/>"
												},
												{
													"mDataProp" : "description",
												
													"sTitle" : "<aebiz:showTitle titleId="vipclubintegrallog.m.description"/>",
													"fnRender": function(obj) {	
												  		var sReturn = "<div style='word-break:break-all'>"+obj.aData.description+"</div>" ;														
												      	return sReturn;
													}
												},
												{
													"mDataProp" : "createTime",
													"sWidth" : "15%",
													"sTitle" : "<aebiz:showTitle titleId="vipclubintegrallog.m.createTime"/>"
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
										"sAjaxSource" : "${pageContext.servletContext.contextPath}/sysback/vipclubintegrallog/queryList",
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
		$("#intergralType").val("");
		$("#minTime").val("");
		$("#maxTime").val("");
	}

	//清空高级搜索的所有选项
	function clearMoreSearch() {
		$("#customerUuid_s").val("");
		$("#intergralType_s").val("");
		$("#intergralCount_s").val("");
		$("#nowIntegral_s").val("");
		$("#orderMainUuid_s").val("");
		$("#description_s").val("");
		$("#overdueTime_s").val("");
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
												"${pageContext.servletContext.contextPath}/sysback/vipclubintegrallog/deletes",
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

		if ($('.datepick').length > 0) {
			$('.datepick').datepicker(); //调用日历插件
		}

	})
</script>