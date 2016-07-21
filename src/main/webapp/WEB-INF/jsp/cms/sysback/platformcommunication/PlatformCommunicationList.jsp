<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include
	file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp"%>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>
					<aebiz:showTitle titleId="platformcommunication.moduleName_CN" />
					<aebiz:showTitle titleId="basebusiness.showmessage.manager" />
				</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle
							titleId="platformcommunication.menuOne" /></span> <i
					class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle
							titleId="platformcommunication.menuTwo" /></span> <i
					class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle
							titleId="platformcommunication.moduleName_CN" />
						<aebiz:showTitle titleId="basebusiness.showmessage.manager" /></span></li>
			</ul>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<div class="box">
					<div class="box-content nopadding y_tableser">
						<div class="y_clear">
							<div class="form-inline table_formnew">
							<!-- 视频名称 -->
								<div class="form-group">
										<label class="control-label"><aebiz:showTitle titleId="platformcommunication.qmf.platformrName" />：</label> 
										<input type="text" name="platformrName" id="platformrName"class="form-control">
									  <input type="hidden"	name="platformrName_q" id="platformrName_q"class="form-control" value="Like">
								</div>
								
								<!-- 提问人-->
								<div class="form-group">
									<label class="control-label"><aebiz:showTitle titleId="platformcommunication.m.questionerName" />：</label>
										 <input type="text" name="questionerName" id="questionerName" class="form-control"> 
										 <input type="hidden"name="questionerName_q" id="questionerName_q" class="form-control" value="EQ">
								  </div>

								<!-- 查询-->
								<div class="form-group">
									<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>"rel="tooltip">
										<aebiz:showTitle titleId="basebusiness.showmessage.query" />
									</button>
									
									<!-- 清空查询-->
									<button class="btn" onclick="javascript:clearSearch();"
										title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>"
										rel="tooltip">
										<aebiz:showTitle titleId="basebusiness.showmessage.clear" />
									</button>
								</div>
							</div>

						</div>

						<table
							class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>
								<tr>
									<th class='with-checkbox'><input type="checkbox"
										name="check_all" id="check_all"></th>
									
									<th><aebiz:showTitle titleId="platformcommunication.m.platformrName" /></th>
								
									<th><aebiz:showTitle
											titleId="platformcommunication.m.questionerName" /></th>
									<th><aebiz:showTitle
											titleId="platformcommunication.m.userType" /></th>
							
									<th><aebiz:showTitle
											titleId="platformcommunication.m.createTime" /></th>
									<th><aebiz:showTitle
											titleId="platformcommunication.m.conftimeState" /></th>
									<%-- <th><aebiz:showTitle
											titleId="platformcommunication.m.admin" /></th>
									<th><aebiz:showTitle
											titleId="platformcommunication.m.replyMessage" /></th> --%>
									<%-- <th><aebiz:showTitle
											titleId="platformcommunication.m.replyTime" /></th> --%>
									<%-- <th><aebiz:showTitle
											titleId="platformcommunication.m.conftime" /></th> --%>
									<%-- <th><aebiz:showTitle
											titleId="platformcommunication.m.remark" /></th> --%>

									<th class='hidden-480'><aebiz:showTitle
											titleId="basebusiness.showmessage.operate" /></th>
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
		searchParam.push({ "name": "platformrName", "value": $.trim($("#platformrName").val())});
		searchParam.push({ "name": "platformrName_q", "value": $.trim($("#platformrName_q").val())});
		
		searchParam.push({ "name": "questionerName", "value": $.trim($("#questionerName").val())});
		searchParam.push({ "name": "questionerName_q", "value": $.trim($("#questionerName_q").val())});
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
											"sSearch" : "<span><aebiz:showTitle titleId="basebusiness.showmessage.check"/>:</span> ",
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
														var sReturn = "<input type='checkbox' name='check' value='"+obj.aData.uuid+"' />";
														return sReturn;
													}
												},
												{
													"mDataProp" : "platformrName",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="platformcommunication.m.platformrName"/>"
												},
												{
													"mDataProp" : "questionerName",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="platformcommunication.m.questionerName"/>"
												},
												{
													"mDataProp" : "userType1",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="platformcommunication.m.userType"/>",
														"fnRender" : function(obj) {
														var sReturn = ""
														if(obj.aData.userType=="1"){
															sReturn="医生"
														}else if(obj.aData.userType=="2"){
															sReturn="患者"
														}
														return sReturn;
													}
												},
									
												{
													"mDataProp" : "createTime",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="platformcommunication.m.createTime"/>"
												},
												 {
													"mDataProp" : "conftimeState1",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="platformcommunication.m.conftimeState"/>",
													"fnRender" : function(obj) {
														var sReturn = ""
														if(obj.aData.conftimeState=="1"){
															sReturn="通过"
														}else if(obj.aData.conftimeState=="2"){
															sReturn="不通过"
														}else if(obj.aData.conftimeState=="0"){
															sReturn="待审核"
														}
														return sReturn;
													}
												}, 
												
												{
													"mDataProp" : "operate",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>",
													"fnRender" : function(obj) {
														var sReturn = "";
														sReturn += "<a href='${pageContext.servletContext.contextPath}/sysback/platformcommunication/toInfo/"
																+ obj.aData.uuid
																+ "' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.check"/>'><aebiz:showTitle titleId="basebusiness.showmessage.check"/></a>";
												
														if(obj.aData.replyState !="1"){
														sReturn += "<a href='${pageContext.servletContext.contextPath}/sysback/platformcommunication/toView/"
															+ obj.aData.uuid
															+ "' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.response"/>'><aebiz:showTitle titleId="basebusiness.showmessage.response"/></a>";
														}
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
										"sAjaxSource" : "${pageContext.servletContext.contextPath}/sysback/platformcommunication/queryList",
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

	$(".moresearch").click(function() {
		//高级搜索需要清空普通搜索的选项
		clearSearch();
		doSearch('search');
	})

	function clearSearch() {
		$("#platformrName").val("");
		$("#questionerName").val("");

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