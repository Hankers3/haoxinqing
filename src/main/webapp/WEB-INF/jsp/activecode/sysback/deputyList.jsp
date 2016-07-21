<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<%@ include
	file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	//封装请求
	function retrieveData(sSource, aoData, fnCallback) {
		var searchParam = new Array();
		var beginTime = new Array();
		var endTime = new Array();
		beginTime = $("#beginTime").val();
		endTime = $("#endTime").val();

		searchParam.push({
			"name" : "doctorName",
			"value" : $.trim($("#doctorName").val())
		});
		searchParam.push({
			"name" : "activecode",
			"value" : $.trim($("#activecode").val())
		});

		searchParam.push({
			"name" : "beginTime",
			"value" : beginTime
		});
		searchParam.push({
			"name" : "endTime",
			"value" : endTime
		});

		$.ajax({
			"dataType" : 'json',
			"type" : "GET",
			"url" : sSource,
			"data" : {
				aoData : JSON.stringify(aoData),
				searchParam : JSON.stringify(searchParam)
			},
			"success" : fnCallback
		});
	}

	//向后台请求
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
											'aTargets' : [ 0, 1, 7 ]
										} ],
										"aaSorting" : [ [ 2, "desc" ] ],
										"bSort" : true, //排序功能            
										"aoColumns" : [

												{
													"mDataProp" : "usr",
													"sTitle" : "代表姓名"
												},
												{
													"mDataProp" : "activeCode",
													"sTitle" : "邀请码"
												},
												{
													"mDataProp" : "allnum",
													"sTitle" : "邀请总人数"
												},

												{
													"mDataProp" : "aNumber",
													"sTitle" : "A类（主任、副主任医师）"
												},
												{
													"mDataProp" : "bNumber",
													"sTitle" : "B类（主治、住院医师）"
												},
												{
													"mDataProp" : "cNumber",
													"sTitle" : "C类（助理、实习医师）"
												},
												{
													"mDataProp" : "dNumber",
													"sTitle" : "D类（护师（士）、心理咨询师）"
												},
												{
													"mDataProp" : "eNumber",
													"sTitle" : "E类（社工师、药剂师）"
												},
												{
													"mDataProp" : "fNumber",
													"sTitle" : "F类（未认证、其他类别）"
												},
												{
													"mDataProp" : "operate",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>",
													"fnRender" : function(obj) {
														var sReturn = "<a onclick='javascript:getDetail(\""+obj.aData.usr+"\",\""+obj.aData.activeCode+"\",\""+obj.aData.allnum+"\")'"
														+"class='btn' rel='tooltip' title='<aebiz:showTitle titleId='basebusiness.showmessage.opeDetail'/>'>查看详情</a>";
														return sReturn;
													}
												} ],

										'oTableTools' : {
											"sSwfPath" : "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
										},
										"bRetrieve" : true,
										"bProcessing" : true,
										"bServerSide" : true, //指定从服务器端获取数据
										"sAjaxSource" : "${pageContext.servletContext.contextPath}/sysback/activecode/queryList",
										"fnServerData" : retrieveData, //获取数据的处理函数  
									};

									if ($(this).hasClass("dataTable-reorder")) {
										opt.sDom = "R" + opt.sDom;
									}

									oTable = $(this).dataTable(opt);

								});
			}

		}
		if ('inittable' != operact) {
			oTable.fnDraw();
		}
	}

	function getDetail(usr,code,allnum){
		$("#usrs").val(usr);
		$("#codes").val(code);
		$("#allnums").val(allnum);
		$("#subForm").submit();
	}
	
	$(document).ready(function() {
		//初始化表格
		doSearch('inittable');

	})

	function clearSearch() {
		$("#doctorName").val("");
		$("#activecode").val("");
		$("#beginTime").val("");
		$("#endTime").val("");

	}
	function Search() {
		doSearch('search');
	}
	$(".search").click(function() {
		//普通搜索需要清空高级搜索的选项
		doSearch('search');
	})
	function importactivecode () {
		window.location.href="${pageContext.servletContext.contextPath}/sysback/activecode/toImportactivecode"; 
		
	}
</script>
</head>
<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>代表管理</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li><span>邀请码管理</span> <i class="fa fa-angle-right"></i></li>
				<li><span>代表管理</span> <i class="fa fa-angle-right"></i></li>

			</ul>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<div class="box">
					<div class="box-content nopadding y_tableser">
						<div class="y_clear">
							<div class="form-inline table_formnew">

								<div class="form-group">
									<label class="control-label">姓名：</label> <input type="text"
										name="doctorName" id="doctorName" class="form-control">

									<label class="control-label">邀请码：</label> <input type="text"
										name="activecode" id="activecode" class="form-control">
									<input type="hidden" name="activecode_q" id="activecode_q"
										class="form-control" value="Like"> <label
										class="control-label">医生注册时间：</label> <input type="text"
										name="beginTime" id="beginTime" class="form-control datepick">
									<label class="control-label">至</label> <input type="text"
										name="endTime" id="endTime" class="form-control datepick">
									<button class="btn btn-primary search"
										onclick="javascript:Search();"
										title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>"
										rel="tooltip">
										<aebiz:showTitle titleId="basebusiness.showmessage.query" />
									</button>
									<button class="btn" onclick="javascript:clearSearch();"
										title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>"
										rel="tooltip">
										<aebiz:showTitle titleId="basebusiness.showmessage.clear" />
									</button>
									<button class="btn btn-primary search" onclick="javascript:importactivecode();"
									title="<aebiz:showTitle titleId="basebusiness.showmessage.importactivecode"/>"
										rel="tooltip">
										<aebiz:showTitle titleId="basebusiness.showmessage.importactivecode" />
									</button>
								</div>

							</div>
							<form action="${pageContext.servletContext.contextPath}/sysback/activecode/toDetailList" method="POST" id="subForm">
									<input type="hidden" name="usrs" id="usrs">
									<input type="hidden" name="codes" id="codes">
									<input type="hidden" name="allnums" id="allnums">
								</form>
							<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
								<thead>
									<tr>
										<th>代表姓名</th>
										<th>邀请码</th>
										<th>邀请总人数</th>
										<th>A类（主任、副主任医师）</th>
										<th>B类（主治、住院医师）</th>
										<th>C类（助理、实习医师）</th>
										<th>D类（护师（士）、心理咨询师）</th>
										<th>E类（社工师）</th>
										<th>F类（未认证、其他类别）</th>
										<th class='hidden-480'>
										<aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>