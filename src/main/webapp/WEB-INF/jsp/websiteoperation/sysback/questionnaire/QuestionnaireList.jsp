<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<%@ include
	file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp"%>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="questionnaire.moduleName_CN" /><aebiz:showTitle titleId="basebusiness.showmessage.manager" /></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="questionnaire.menuOne" /></span> <i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="questionnaire.menuTwo" /></span> <i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="questionnaire.moduleName_CN" /><aebiz:showTitle titleId="basebusiness.showmessage.manager" /></span></li>
			</ul>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<div class="box">
					<div class="box-content nopadding y_tableser">
						<div class="y_clear">
							<div class="form-inline table_formnew">
								<div class="form-group">
									<label class="control-label"><aebiz:showTitle titleId="questionnaire.qmf.title" />：</label> 
									<input type="text" name="title" id="title" class="form-control">
									<input type="hidden" name="title_q" id="title_q" class="form-control" value="Like">
								</div>
								
								<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="questionnaire.m.state" />：</label> 
									<select name="state" id="state" class='form-control'>
										<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"></aebiz:showTitle>-</option>
										<option value="1"><aebiz:showTitle titleId="basebusiness.showmessage.yes"></aebiz:showTitle></option>
										<option value="0"><aebiz:showTitle titleId="basebusiness.showmessage.no"></aebiz:showTitle></option>
									</select>
									 <input type="hidden" name="state_q" id="state_q" value="EQ">
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
								<a class="btn" href="${pageContext.servletContext.contextPath}/sysback/questionnaire/toAdd" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.newAdd" /></a>
								<button class="btn" onclick="javascript:removes('');" title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>" rel="tooltip">
									<aebiz:showTitle titleId="basebusiness.showmessage.delete" />
								</button>
							</div>
						</div>

						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>
								<tr>
									<th class='with-checkbox'><input type="checkbox" name="check_all" id="check_all"></th>
									<th><aebiz:showTitle titleId="questionnaire.m.title" /></th>
									<th><aebiz:showTitle titleId="questionnaire.m.state" /></th>
									<th><aebiz:showTitle titleId="questionnaire.m.introduce" /></th>
									<th><aebiz:showTitle titleId="questionnaire.m.note" /></th>
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
	
		searchParam.push({"name" : "title_q","value" : $.trim($("#title_q").val())});
		searchParam.push({"name" : "state_q","value" : $.trim($("#state_q").val())});
		searchParam.push({"name" : "state",  "value" : $.trim($("#state").val())});
		searchParam.push({"name" : "title","value" : $.trim($("#title").val())});

		$.ajax({
			"dataType" : 'json',
			"type" : "POST",
			"url" : sSource,
			"data" : {aoData : JSON.stringify(aoData),searchParam : JSON.stringify(searchParam)},
			"success" : fnCallback
		});
	}

	var oTable = null;
	function doSearch(operact) {
		if (oTable == null) {
			if ($('.dataTable').length > 0) {$('.dataTable').each(
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
							'aTargets' : [ 0,5 ]
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
									"mDataProp" : "title",
									"sWidth" : "20%",
									"sTitle" : "<aebiz:showTitle titleId="questionnaire.m.title"/>"
								},
								{
									"mDataProp" : "state",
									"sWidth" : "5%",
									"sDefaultContent" : "",
									"sTitle" : "<aebiz:showTitle titleId="questionnaire.m.state"/>",
									"fnRender" : function(obj) {
										var sReturn = "";
										if (obj.aData.state == "1") {
											sReturn = "<aebiz:showTitle titleId="basebusiness.showmessage.yes"/>";
										} else if(obj.aData.state=="0"){
											sReturn = "<aebiz:showTitle titleId="basebusiness.showmessage.no"/>";
										}else{
											sReturn="";
										}
										return sReturn;
									}
								},
								{
									"mDataProp" : "introduce",
									"sWidth" : "35%",
									"sTitle" : "<aebiz:showTitle titleId="questionnaire.m.introduce"/>"
								},
								{
									"mDataProp" : "note",
									"sWidth" : "20%",
									"sTitle" : "<aebiz:showTitle titleId="questionnaire.m.note"/>"
								},
								{
									"mDataProp" : "operate",
									"sWidth" : "15%",
									"sDefaultContent" : "",
									"sTitle" : "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>",
									"fnRender" : function(obj) {
										var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/questionnaire/toUpdate/"
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
						"sAjaxSource" : "${pageContext.servletContext.contextPath}/sysback/questionnaire/queryList",
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
		$("#title").val("");
		$("#state").val("");
	}

	function removes(delId) {
		var checkIds="";
		if( delId.trim() != "") {
			checkIds = delId ;
		}else{
			$("input[name='check']:checkbox").each(function(){				
        if($(this).is(":checked")){        	
            checkIds += $(this).val()+"," ;  
        }
    	})	
		}
		
		if(checkIds.trim() == "") {
				//提示为空
				bootbox.alert("<aebiz:showTitle titleId="basebusiness.showmessage.chooseDeleteData"/>") ;
				return ;
		} 
		
		bootbox.confirm("<aebiz:showTitle titleId="basebusiness.showmessage.confirmDelete"/>", function(r){
        	if(r) {
        		//删除
				$.post("${pageContext.servletContext.contextPath}/sysback/questionnaire/deletes",{"selectOne":checkIds,ranNum:Math.random()},	
				    function(data) {	       
				       var result = eval("("+data+")") ;	       
				       if(result.rsp) {
				  			//刷新
							doSearch('search') ;     	
				       }else{
				       		bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
				       }
				    }
				);		
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