<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/import/ListImport.jsp" %>

</head>

<body>
<!-- 头部 -->
<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeHead.jsp"%>
<div class="container-fluid" id="content">
<!-- 左侧 -->
<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeLeft.jsp"%>
	<div id="main">	
		<div class="container-fluid">	
			<div class="page-header">
				<div class="pull-left">
					<h1><aebiz:showTitle titleId="invoice.moduleName_CN"/></h1>
				</div>
			</div>
			<div class="breadcrumbs">
				<ul>
					<li>
						<span><aebiz:showTitle titleId="storebondcharge.menuTwo"/></span>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<span><aebiz:showTitle titleId="invoice.menuTwo"/></span>
						<i class="fa fa-angle-right"></i>							
					</li>							
					<li>
						<span><aebiz:showTitle titleId="invoice.menuList"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
					</li>						
				</ul>				
			</div>				
								
			<div class="row">
					<div class="col-sm-12">
						<div class="box">
							<div class="box-content nopadding y_tableser">
								<ul class="tabs tabs-inline tabs-top border1-top mb_10">
									<li class='active'><a href="#needBuy"id="state1" data-toggle='tab'><i class="fa fa-user"></i> <aebiz:showTitle titleId="invoice.needInvoice.billAndOrder"/></a></li>
									<li><a href="#" id="state2" data-toggle='tab'><i class="fa fa-bullhorn"></i> <aebiz:showTitle titleId="invoice.needInvoice.servicefee"/></a></li>
								</ul>
								<div class="form-inline table_formnew">
									<div class="form-group">
											<label class="control-label"><aebiz:showTitle titleId="invoice.m.orderMainUuid" />：</label>
											<input type="text" name="orderMainUuid" id="orderMainUuid" class="form-control">
											<input type="hidden" name="orderMainUuid_q" id="orderMainUuid_q" class="form-control" value="EQ">
									</div>
									
									<div class="form-group">
										<label class="control-label"><aebiz:showTitle titleId="invoice.m.invoiceType" />：</label> 
										<select name="invoiceType" id="invoiceType" class='form-control'>
											<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose" />-</option>
											<c:forEach items="${invoiceType}" var="item">
												<option value="${item}">${item}</option>
											</c:forEach>
										</select>
										<input type="hidden" name="invoiceType_q" id="invoiceType_q" class="form-control" value="EQ">
									</div>	
									
									<div class="form-group">
											<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip">
												<aebiz:showTitle titleId="basebusiness.showmessage.query" />
											</button>
											<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip">
												<aebiz:showTitle titleId="basebusiness.showmessage.clear" />
											</button>
											<button class="btn" id="searchshow" title="<aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/>" rel="tooltip" data-toggle="modal" data-target="#modal-user">
												<aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery" />
											</button>		
									</div>
								<div>	
							</div>		
						</div>
						<%-- <div class="y_tablebtn">
							<a class="btn" href="${pageContext.servletContext.contextPath}/storeback/invoice/toAdd" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip">
									<aebiz:showTitle titleId="basebusiness.showmessage.newAdd" />
							</a>
						</div> --%>
					</div>
							<input type="hidden" name="needInvoice" id="needInvoice" value="2">
							<div class="tab-content y_tabdatable">
								<div id="needBuy" class="tab-pane active">
									<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
										<thead>		
											<tr>
												<th class='with-checkbox'><input type="checkbox" name="check_all" id="check_all"></th>
															<th><aebiz:showTitle titleId="invoice.m.invoiceNO" /></th>
															<th><aebiz:showTitle titleId="invoice.m.orderMainUuid" /></th>
															<th><aebiz:showTitle titleId="invoice.m.invoiceCate" /></th>
															<th><aebiz:showTitle titleId="invoice.m.invoiceType" /></th>
															<th><aebiz:showTitle titleId="invoice.m.invoiceTitle" /></th>
															<th><aebiz:showTitle titleId="invoice.m.createTime" /></th>
															<th><aebiz:showTitle titleId="invoice.m.saleType" /></th>
															<th><aebiz:showTitle titleId="invoice.m.invoiceState" /></th>
															<th><aebiz:showTitle titleId="invoice.m.invoiceTime" /></th>
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
													<label class="control-label"><aebiz:showTitle titleId="invoice.m.orderMainUuid" />：</label>
													<input type="text" name="orderMainUuid_s" id="orderMainUuid_s" class="form-control">
													<input type="hidden" name="orderMainUuid_q" id="orderMainUuid_q" class="form-control" value="EQ">
											</div>
										</div>
										<div class="col-sm-4">
											<div class="form-group">
												<label class="control-label"><aebiz:showTitle titleId="invoice.m.invoiceType" />：</label> 
												<select name="invoiceType_s" id="invoiceType_s" class='form-control'>
													<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose" />-</option>
													<c:forEach items="${invoiceType}" var="item">
														<option value="${item}">${item}</option>
													</c:forEach>
												</select>
												<input type="hidden" name="invoiceType_q" id="invoiceType_q" class="form-control" value="EQ">
											</div>
										</div>
										<div class="col-sm-4">
											<div class="form-group">
												<label class="control-label"><aebiz:showTitle titleId="invoice.m.invoiceState" />：</label> 
													<select name="invoiceState_s" id="invoiceState_s" class='form-control'>
														<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose" />-</option>
														<c:forEach items="${invoiceState}" var="item">
															<option value="${item.key}">${item.value}</option>
														</c:forEach>
													</select> 
													<input type="hidden" name="invoiceState_q" id="invoiceState_q" class="form-control" value="EQ">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-6">
											<div class="form-group">
												<label class="control-label"><aebiz:showTitle titleId="invoice.m.invoiceCate" />：</label> 
													<select name="invoiceCate_s" id="invoiceCate_s" class='form-control'>
														<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose" />-</option>
														<c:forEach items="${invoiceCateType}" var="item">
															<option value="${item}">${item}</option>
														</c:forEach>
													</select> 
													<input type="hidden" name="invoiceCate_q" id="invoiceCate_q" class="form-control" value="EQ">
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button class="btn moresearch btn-primary" data-dismiss="modal"><aebiz:showTitle titleId="basebusiness.showmessage.query" /></button>
									<button class="btn clearMoreSearch"><aebiz:showTitle titleId="basebusiness.showmessage.clear" /></button>
								</div>
							</div>
						</div>
					</div>
<!--高级搜索-->
<script>
function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
	searchParam.push({"name" : "orderMainUuid","value" : $.trim($("#orderMainUuid").val())});
	searchParam.push({"name" : "orderMainUuid_s","value" : $.trim($("#orderMainUuid_s").val())});
  searchParam.push({"name" : "orderMainUuid_q","value" : $.trim($("#orderMainUuid_q").val())});
  searchParam.push({"name" : "invoiceType","value" : $.trim($("#invoiceType").val())});
  searchParam.push({"name" : "invoiceType_s","value" : $.trim($("#invoiceType_s").val())});
  searchParam.push({"name" : "invoiceType_q","value" : $.trim($("#invoiceType_q").val())});
	searchParam.push({"name" : "invoiceCate_q","value" : $.trim($("#invoiceCate_q").val())});
	searchParam.push({"name" : "invoiceCate_s","value" : $.trim($("#invoiceCate_s").val())});
	searchParam.push({"name" : "invoiceState_q","value" : $.trim($("#invoiceState_q").val())});
	searchParam.push({"name" : "invoiceState_s","value" : $.trim($("#invoiceState_s").val())});
	searchParam.push({"name" : "needInvoice","value" : $.trim($("#needInvoice").val())});
	
   $.ajax({    	        
  	"dataType": 'json', 
	  "type": "POST", 
	  "url": sSource, 
	  "data": {aoData:JSON.stringify(aoData),searchParam:JSON.stringify(searchParam)}, 
	  "success": fnCallback
  });    
} 
    
var oTable = null; 
function doSearch(operact) {	
	if(oTable == null) {		 
    if ($('.dataTable').length > 0) {
        $('.dataTable').each(function() {            
                var opt = {
				            "sPaginationType": "full_numbers",
				            "oLanguage": {
				            		"sProcessing": "<aebiz:showTitle titleId="basebusiness.showmessage.loading"/>",
				                "sSearch": "<span><aebiz:showTitle titleId="basebusiness.showmessage.query"/>:</span> ",
				                "sInfo": "<aebiz:showTitle titleId="basebusiness.showmessage.pageFrom"/> <span>_START_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageTo"/> <span>_END_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageItem"/>，<aebiz:showTitle titleId="basebusiness.showmessage.totalCount"/>： <span>_TOTAL_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageItem"/>",
				                "sLengthMenu": "_MENU_ <span><aebiz:showTitle titleId="basebusiness.showmessage.pageShow"/></span>" ,
				                "oPaginate": {
				                 	"sFirst": "<aebiz:showTitle titleId="basebusiness.showmessage.firstPage"/>",
				                  "sPrevious": "<aebiz:showTitle titleId="basebusiness.showmessage.prePage"/>",
				                  "sNext": "<aebiz:showTitle titleId="basebusiness.showmessage.nextPage"/>",
				                  "sLast": "<aebiz:showTitle titleId="basebusiness.showmessage.lastPage"/>"
				                }
				            },
				            
				            //'sDom': "lfrtip",
				            'sDom': "rtlip",
				            //"sDom": '<"top"l>rt<"bottom"ip><"clear">',            
				            'aoColumnDefs': [{
				                'bSortable': false,
				                'aTargets': [0,1,2,3,4,5,7,8,10]                
				            }],
				            "bSort": true, //排序功能            
					         	"aoColumns": [
											{
																"mDataProp" : "checkbox",
																"sDefaultContent" : "",
																"fnRender" : function(obj) {
																	var sReturn = "<input type='checkbox' name='check' value='"+obj.aData.uuid+"' />";
																	return sReturn;
																}
															},
															{"mDataProp" : "invoiceNO","sTitle" : "<aebiz:showTitle titleId="invoice.m.invoiceNO"/>"},
															{"mDataProp" : "orderMainUuid","sTitle" : "<aebiz:showTitle titleId="invoice.m.orderMainUuid"/>"},
															{"mDataProp" : "invoiceCate","sTitle" : "<aebiz:showTitle titleId="invoice.m.invoiceCate"/>"},
															{"mDataProp" : "invoiceType","sTitle" : "<aebiz:showTitle titleId="invoice.m.invoiceType"/>"},
															{"mDataProp" : "invoiceTitle","sTitle" : "<aebiz:showTitle titleId="invoice.m.invoiceTitle"/>"},
															{"mDataProp" : "createTime","sWidth":"15%","sTitle" : "<aebiz:showTitle titleId="invoice.m.createTime"/>"},
															{"mDataProp" : "saleType","sTitle" : "<aebiz:showTitle titleId="invoice.m.saleType"/>"},
															{"mDataProp" : "invoiceState","sTitle" : "<aebiz:showTitle titleId="invoice.m.invoiceState"/>"},
															{"mDataProp" : "invoiceTime","sWidth":"15%","sTitle" : "<aebiz:showTitle titleId="invoice.m.invoiceTime"/>"},
															{"mDataProp" : "operate",
																"sDefaultContent" : "",
																"sTitle" : "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>",
																"fnRender" : function(obj) {
																	if (obj.aData.invoiceState == "<aebiz:showTitle titleId="invoice.invoiceState.uninvoiced"/>"	|| obj.aData.invoiceNO == null) {
																		var sReturn = "<a href='${pageContext.servletContext.contextPath}/storeback/invoice/toInvoice/"+ obj.aData.uuid+ "' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="invoice.operate.goinvoice"/>'><aebiz:showTitle titleId="invoice.operate.goinvoice"/></a>";
																		return sReturn;
																	}
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
										"sAjaxSource" : "${pageContext.servletContext.contextPath}/storeback/invoice/queryList",
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
		$("#orderMainUuid").val("");
		$("#saleType").val("");
		$("#invoiceType").val("");
		$("#invoiceCate").val("");
		$("#invoiceState").val("");
	}
	function clearMoreSearch() {
		$("#invoiceCate_s").val("");
		$("#invoiceState_s").val("");
		$("#orderMainUuid_s").val("");
		$("#invoiceType_s").val("");
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

		$("#state1").click(function(e) {
			$("#needInvoice").val("2");
			doSearch("search");
		});

		$("#state2").click(function(e) {
			$("#needInvoice").val("4");
			doSearch("search");
		});

	})
</script>

	
</body>

</html>