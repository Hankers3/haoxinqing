<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<%@include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp"%>
	<title>已关联标签</title>
</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>
					<aebiz:showTitle titleId="tagscategoyrel.moduleName_CN" /><aebiz:showTitle titleId="basebusiness.showmessage.manager" />
				</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="tagscategoyrel.menuOne" /></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="tagscategoyrel.menuTwo" /></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="tagscategoyrel.moduleName_CN" /><aebiz:showTitle titleId="basebusiness.showmessage.manager" /></span>
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
									<label class="control-label"><aebiz:showTitle titleId="tagscategoyrel.qmf.tagName" />：</label>
									<input type="text" name="tagName" id="tagName" class="form-control">
									<input type="hidden" name="categoryUuid" id="categoryUuid" class="form-control" value="${web.categoryUuid}">
									<input type="hidden" name="categoryUuid_q" id="categoryUuid_q" class="form-control" value="EQ">
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
								<button class="btn"  title="<aebiz:showTitle titleId="tagscategoyrel.button.back"/>" rel="tooltip" id="goBack">
									<aebiz:showTitle titleId="tagscategoyrel.button.back" />
								</button>
								<button class="btn" title="<aebiz:showTitle titleId="tagscategoyrel.button.batchUpdate"/>" rel="tooltip" id="batchUpdate">
									<aebiz:showTitle titleId="tagscategoyrel.button.batchUpdate" />
								</button>
								<button class="btn" onclick="javascript:removes('');" title="<aebiz:showTitle titleId="tagscategoyrel.button.delete"/>" rel="tooltip">
									<aebiz:showTitle titleId="tagscategoyrel.button.delete" />
								</button>
								<a class="btn btn-primary" data-toggle="modal"  data-target="#myModal" id="refresh"><aebiz:showTitle titleId="tagscategoyrel.button.rel" /></a>
							</div>
						</div>

						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder" id="tags">
							<thead>
								<tr>
									<th class='with-checkbox'><input type="checkbox" name="check_all" id="check_all"></th>
									<th><aebiz:showTitle titleId="tagscategoyrel.m.tagName" /></th>
									<th><aebiz:showTitle titleId="tagscategoyrel.m.categoryName" /></th>
									<th><aebiz:showTitle titleId="tagscategoyrel.m.introduce" /></th>
									<th><aebiz:showTitle titleId="tagscategoyrel.m.position" /></th>
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

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 id="user-infos">
						<aebiz:showTitle titleId="tagscategoyrel.title.name"/>
					</h4>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="box">
							<div class="box-content y_tableser">
								<div class="y_clear">
									<div class="form-inline table_formnew">
										<div class="form-group">
											<label class="control-label"><aebiz:showTitle titleId="tags.qmf.tagName" />：</label>
											<input type="text" name="tagName2" id="tagName2" class="form-control">
											<input type="hidden" name="tagName_q2" id="tagName_q2" class="form-control" value="Like">
										</div>
										<div class="form-group">
											<button class="btn btn-primary search2" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip">
												<aebiz:showTitle titleId="basebusiness.showmessage.query" />
											</button>
											<button class="btn" onclick="javascript:clearSearch2();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip">
												<aebiz:showTitle titleId="basebusiness.showmessage.clear" />
											</button>
										</div>
									</div>
								</div>
								<div class="max_hei250">
									<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder" id="tags2">
										<thead>
											<tr>
												<th class='with-checkbox'><input type="checkbox" name="check_all2" id="check_all2"></th>
												<th><aebiz:showTitle titleId="tags.m.tagName" /></th>
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
				<div class="modal-footer">
					<button class="btn moresearch btn-primary"  id="batchRel">
						<aebiz:showTitle titleId="tagscategoyrel.button.batch" />
					</button>
					<button class="btn cancel" data-dismiss="modal">
						<aebiz:showTitle titleId="basebusiness.showmessage.cancel" />
					</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

<script>
	function retrieveData(sSource, aoData, fnCallback) {
		var searchParam = new Array();
		searchParam.push({ "name": "tagName", "value": $.trim($("#tagName").val())});
		
		searchParam.push({ "name": "categoryUuid_q", "value": $.trim($("#categoryUuid_q").val())});
		searchParam.push({ "name": "categoryUuid", "value": $.trim($("#categoryUuid").val())});
		
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
			if ($('#tags').length > 0) {
				$('#tags')
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
											'aTargets' : [ 0,2,3,5 ]
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
													"mDataProp" : "tagName",
													"sTitle" : "<aebiz:showTitle titleId="tagscategoyrel.m.tagName"/>"
												},
												{
													"mDataProp" : "categoryName",
													"sTitle" : "<aebiz:showTitle titleId="tagscategoyrel.m.categoryName"/>"
												},
												
												{	//位置
													"mDataProp" : "introduce",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="tagscategoyrel.m.introduce"/>",
													"fnRender" : function(obj) {
														var checkedNo="";
														var checkedYes="";
														if("0"==obj.aData.introduce){
															checkedNo="checked";
														}else{
															checkedYes="checked";
														}
														var sReturn = "";
																sReturn += "<input type='radio' name='"+obj.aData.uuid+"introduce' value='0' "+checkedNo+" /><aebiz:showTitle titleId='basebusiness.showmessage.no' /> "
																sReturn += "<input type='radio' name='"+obj.aData.uuid+"introduce' value='1' "+checkedYes+" /><aebiz:showTitle titleId='basebusiness.showmessage.yes' />"
														return sReturn;
													}
												},
												{	//位置
													"mDataProp" : "position",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="tagscategoyrel.m.position"/>",
													"fnRender" : function(obj) {
														var sReturn = "<input class='form-control' maxlength=3 style='width:60px;' type='text' name='"+obj.aData.uuid+"position' value='"+obj.aData.position+"'>"
														return sReturn;
													}
												}
												,

												{
													"mDataProp" : "operate",
													"sDefaultContent" : "",
													"sTitle" : "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>",
													"fnRender" : function(obj) {
														var sReturn = "<a href='javascript:batchUpdate(\""+ obj.aData.uuid+ "\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="tagscategoyrel.button.update"/>'><aebiz:showTitle titleId="tagscategoyrel.button.update"/></a>";
														sReturn += "<a href='javascript:removes(\""+ obj.aData.uuid+ "\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="tagscategoyrel.button.delete"/>'><aebiz:showTitle titleId="tagscategoyrel.button.delete"/></a>";
														return sReturn;
													}
												} ],

										//'oColVis': {
										// "buttonText": "Change columns <i class='icon-angle-down'></i>"
										//},
										'oTableTools' : {
											"sSwfPath" : "${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
										},
										"bRetrieve" : true,
										"bProcessing" : true,
										"bServerSide" : true, //指定从服务器端获取数据
										"sAjaxSource" : "${pageContext.servletContext.contextPath}/tagscategoyrel/queryList",
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
		$("#tagName").val("");

	}

	//清空高级搜索的所有选项
	function clearMoreSearch() {
		$("#tagUuid_s").val("");
		$("#tagName_s").val("");
		$("#categoryUuid_s").val("");
		$("#categoryName_s").val("");
		$("#introduce_s").val("");
		$("#position_s").val("");
		$("#_s").val("");

	}
	//返回分类列表
	$('#goBack').click(function() {
		window.location.href = "${pageContext.servletContext.contextPath}/tagscategory/toList";
	})
	//批量更新
	$('#batchUpdate').click(function() {
		batchUpdate('');
	})
	
	function batchUpdate(selectId) {
		var checkIds = "";
		if (selectId.trim() != "") {
			checkIds = selectId+"|";
			checkIds += $("input[name='"+ selectId +"position']").val()+"|";
			checkIds += $("input[name='"+ selectId +"introduce']:checked").val();
		} else {
			$("input[name='check']:checkbox").each(function() {
				if ($(this).is(":checked")) {
					checkIds += $(this).val() + "|";
					checkIds += $("input[name='"+ $(this).val() +"position']").val()+"|";
					checkIds += $("input[name='"+$(this).val()+"introduce']:checked").val()+",";
				}
			})
		}
		if (checkIds.trim() == "") {
			//提示为空
			bootbox.alert("<aebiz:showTitle titleId="tagscategoyrel.alert.noSelect1"/>");
			return;
		}

		bootbox.confirm("<aebiz:showTitle titleId="tagscategoyrel.alert.confirm1"/>",
						function(r) {
							if (r) {
								$.getJSON(
												"${pageContext.servletContext.contextPath}/tagscategoyrel/batchUpdate",
												{"selectOne" : checkIds,ranNum : Math.random()},
												function(data) {
													if (data.rsp) {
														//刷新
														doSearch('search');
														bootbox.alert('<aebiz:showTitle titleId="tagscategoyrel.alert.success1"/>');
													} else {
														bootbox.alert('<aebiz:showTitle titleId="tagscategoyrel.alert.fail1"/>');
													}
												});
							}
						});
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

		bootbox.confirm("<aebiz:showTitle titleId="basebusiness.showmessage.confirmDelete"/>",
						function(r) {
							if (r) {
								//删除
								$.post(
												"${pageContext.servletContext.contextPath}/tagscategoyrel/deletes",
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
	

/*弹出框的js*/
$("#refresh").click(function() {
	doSearch2('search');
})
function retrieveData2(sSource,aoData,fnCallback) { 
	
	var searchParam = new Array() ;
	searchParam.push({ "name": "tagName_q", "value": $.trim($("#tagName_q2").val())});
	searchParam.push({ "name": "tagName", "value": $.trim($("#tagName2").val())});
			
	
   $.ajax({    	        
  	"dataType": 'json', 
	  "type": "POST", 
	  "url": sSource, 
	  "data": {aoData:JSON.stringify(aoData),searchParam:JSON.stringify(searchParam)}, 
	  "success": fnCallback
  });    
} 
    
var oTable2 = null; 
function doSearch2(operact) {	
	if(oTable2 == null) {		 
    if ($('#tags2').length > 0) {
        $('#tags2').each(function() {            
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
				                'aTargets': [0, 2]                
				            }],
				            "bSort": true, //排序功能            
					         	"aoColumns": [
										  {
										  	"mDataProp":"checkbox" ,
										  	"sDefaultContent":"",
										  	"fnRender": function(obj) {
										      var sReturn = "<input type='checkbox' name='tagsCheck' value='"+obj.aData.uuid+"' />";						      
										      return sReturn;
										 	}
										  },
											{"mDataProp":"tagName" ,"sTitle": "<aebiz:showTitle titleId="tags.m.tagName"/>"},
		

										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
											 	"fnRender": function(obj) {	
															var sReturn = "<a href='javascript:relation(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>'>关联</a>" ;															
												      return sReturn;
												 }			
										  }
										],
										
				            //'oColVis': {
				               // "buttonText": "Change columns <i class='icon-angle-down'></i>"
				            //},
				            'oTableTools': {
				                "sSwfPath": "${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
				            },
				            "bRetrieve":true ,
				            "bProcessing":true,
				           	"bServerSide":true,                    //指定从服务器端获取数据
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/tags/noExistList/${web.categoryUuid}" ,
										"fnServerData":retrieveData2,           //获取数据的处理函数  
				        };
				        
                		if ($(this).hasClass("dataTable-reorder")) {
		                    opt.sDom = "R" + opt.sDom;
		                }
		               
		                oTable2 = $(this).dataTable(opt);
                
        	});
   		}         
	}
		
	//刷新Datatable，会自动激发retrieveData  		
  	if('inittable' != operact) {  			
		oTable2.fnDraw(); 	
	}  
	 	  
}

$(".search2").click(function(){	
	//普通搜索需要清空高级搜索的选项
	doSearch2('search') ;  
})


function clearSearch2() {
	$("#tagName2").val("") ;
	
}

//批量关联
$("#batchRel").click(function(){	
	relation('');
})

//关联标签
function relation(tagsId) {
		var checkIds="";
		if( tagsId.trim() != "") {
			checkIds = tagsId ;
		}else{
			$("input[name='tagsCheck']:checkbox").each(function(){				
        if($(this).is(":checked")){        	
            checkIds += $(this).val()+"," ;  
        }
    	})	
		}
		
		if(checkIds.trim() == "") {
				//提示为空
				bootbox.alert("<aebiz:showTitle titleId="tagscategoyrel.alert.noSelect"/>") ;
				return ;
		} 
	
		bootbox.confirm("<aebiz:showTitle titleId="tagscategoyrel.alert.confirm"/>", function(r){
        	if(r) {
						$.getJSON(
					    	"${pageContext.servletContext.contextPath}/tagscategoyrel/relation",
					    	{"selectOne":checkIds,"categoryUuid":"${web.categoryUuid}",ranNum:Math.random()},	
						    function(data) {	       
						       if(data.rsp) {
						  			//刷新
										doSearch('search') ;  
										doSearch2('search') ; 
										bootbox.alert('<aebiz:showTitle titleId="tagscategoyrel.alert.success"/>');  	
						       }else{
						       		bootbox.alert('<aebiz:showTitle titleId="tagscategoyrel.alert.fail"/>') ;
						       }
						    }
						);		
        	}   
    });   
}


	$(document).ready(function() {
		//初始化表格
		doSearch('inittable');
		//弹出框 初始化表格
		doSearch2('inittable');

		//复选框全选
		$("#check_all").click(function(e) {
			$('input', oTable.fnGetNodes()).prop('checked', this.checked);
		});
		
		//弹出框 复选框全选
		$("#check_all2").click(function(e) {
			$('input', oTable2.fnGetNodes()).prop('checked', this.checked);
		});

		$(".clearMoreSearch").click(function(e) {
			clearMoreSearch();
		});
	})
</script>