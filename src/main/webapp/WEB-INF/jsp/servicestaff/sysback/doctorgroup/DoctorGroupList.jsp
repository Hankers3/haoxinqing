<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>

</head>

<body>
	<div class="container-fluid">	
		<div class="page-header">
			<div class="pull-left">
				<h1>医生同行</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span>医生系统</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>同行系统</span>
					<i class="fa fa-angle-right"></i>							
				</li>							
								
			</ul>				
		</div>				
							
											<div class="row">
													<div class="col-sm-12">
														<div class="box">
															<div class="box-content nopadding y_tableser">
																<div class="y_clear">
																	<div class="form-inline table_formnew">
																	
																		<div  class="form-group">
																		<label class="control-label">医生姓名：</label>
																		<input type="text" name="doctorName" id="doctorName" class="form-control">
																		<input type="hidden" name="doctorName_q" id="doctorName_q" class="form-control" value="Like">
																		</div>
																		<div  class="form-group">
																		<label class="control-label">时间：</label>
																		<input type="text" name="createTime" id="createTime" class="form-control datepick">
																		<input type="hidden" name="createTime_q" id="createTime_q" class="form-control " value="Like">
																		</div>
										
																		
																		<div class="form-group">
																			<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
																			<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
																			
																		</div>
																	</div>
																	
																	
															</div>
															
															<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
																<thead>		
																	<tr>
																		<th class='with-checkbox'>
																			<input type="checkbox" name="check_all" id="check_all">
																		</th>
																		
																		<th><aebiz:showTitle titleId="doctorgroup.m.doctorName"/></th>
																		<th><aebiz:showTitle titleId="doctorgroup.m.createTime"/></th>
																		<th class='hidden-480'><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
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
									function retrieveData(sSource,aoData,fnCallback) { 
										var searchParam = new Array() ;
									
										searchParam.push({ "name": "doctorName", "value": $.trim($("#doctorName").val())});
										searchParam.push({ "name": "doctorName_q", "value": $.trim($("#doctorName_q").val())});
									
										searchParam.push({ "name": "createTime", "value": $.trim($("#createTime").val())});
										searchParam.push({ "name": "createTime_q", "value": $.trim($("#createTime_q").val())});
									
												
										
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
				                'aTargets': [0, 1]                
				            }],
				            "bSort": true, //排序功能            
					         	"aoColumns": [
										  {
										  	"mDataProp":"checkbox" ,
										  	"sDefaultContent":"",
										  	"fnRender": function(obj) {
										      var sReturn = "<input type='checkbox' name='check' value='"+obj.aData.uuid+"' />";						      
										      return sReturn;
										 		}
										  },
											
											{"mDataProp":"doctorName" ,"sDefaultContent" : "","sTitle": "医生姓名"},
											{"mDataProp":"createTime" ,"sDefaultContent" : "","sTitle": "<aebiz:showTitle titleId="doctorgroup.m.createTime"/>"},
										
		

										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 	"fnRender": function(obj) {	
											  		var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/doctorgroup/toView/"+obj.aData.doctorUuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'>查看详情</a>" ;
																											
											      return sReturn;
											 }			
										  }
										],
										
				            //'oColVis': {
				               // "buttonText": "Change columns <i class='icon-angle-down'></i>"
				            //},
				            'oTableTools': {
				                "sSwfPath": "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
				            },
				            "bRetrieve":true ,
				            "bProcessing":true,
				           	"bServerSide":true,                    //指定从服务器端获取数据
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/doctorgroup/queryList" ,
										"fnServerData":retrieveData,           //获取数据的处理函数  
				        };
				        
                		if ($(this).hasClass("dataTable-reorder")) {
		                    opt.sDom = "R" + opt.sDom;
		                }
		               
		                oTable = $(this).dataTable(opt);
                
        	});
   		}         
	}
		
										//刷新Datatable，会自动激发retrieveData  		
									  	if('inittable' != operact) {  			
											oTable.fnDraw(); 	
										}  
										 	  
									}
									
									$(".search").click(function(){	
										//普通搜索需要清空高级搜索的选项
										clearMoreSearch();
										doSearch('search') ;  
									})
									
									$(".moresearch").click(function(){
										//高级搜索需要清空普通搜索的选项
										clearSearch() ;
										doSearch('search') ;
									})
									
									function clearSearch() {
										$("#doctorName").val("") ;
										$("#createTime").val("") ;
										
									}
									
									//清空高级搜索的所有选项
									function clearMoreSearch() {
										$("#classifyUuid_s").val("") ;
										$("#classifyName_s").val("") ;
										$("#doctorUuid_s").val("") ;
										$("#doctorName_s").val("") ;
										$("#groupName_s").val("") ;
										$("#createTime_s").val("") ;
										$("#note_s").val("") ;
										$("#_s").val("") ;
										
									}
									
									
									
									$(document).ready(function() {
										
										 //初始化表格
									   doSearch('inittable') ;
									   
									   //复选框全选
										 $("#check_all").click(function(e) {
										 		$('input', oTable.fnGetNodes()).prop('checked', this.checked);
										 });       
											
										
										$(".clearMoreSearch").click(function(e) {    	
											clearMoreSearch() ;
										});
									}) 
</script>