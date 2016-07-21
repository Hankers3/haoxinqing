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
				<h1><aebiz:showTitle titleId="trainplan.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="trainplan.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="trainplan.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span><aebiz:showTitle titleId="trainplan.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
				</li>						
			</ul>				
		</div>				
		<ul class="tabs tabs-inline tabs-top border1-top">
			<li class='active'>
				<a href="#train1" id="train1" data-toggle='tab'>课程设定</a>
			</li>
			<li>
				<a href="#train2" id="train2" data-toggle='tab'>培训计划</a>
			</li>
			<li>
				<a href="#train3" id="train3" data-toggle='tab'>培训记录</a>
			</li>
			<li>
				<a href="#train4" id="train4" data-toggle='tab'>培训结果</a>
			</li>
		</ul>
		
		<div class="tab-content y_tabdatable">
			<!--课程设定开始-->
			<div class="tab-pane active" id="train1">
				<div class="y_clear">
					<div class="y_tablebtn">
						<a href="${pageContext.servletContext.contextPath}/sysback/syllabus/toAdd" class="btn" title="增加课程" rel="tooltip">增加课程</a>
						<a href="" class="btn" title="删除" rel="tooltip">删除</a>
					</div>  
				</div>
				<table  class="table table-bordered table-hover table-striped dataTable1 dataTable-nosort dataTable-reorder" >
					<thead>		
						<tr>
							<th class='with-checkbox'>
								<input type="checkbox" name="check_all" id="check_all">
							</th>
							<th><aebiz:showTitle titleId="syllabus.m.syllabusNo"/></th>
							<th><aebiz:showTitle titleId="syllabus.m.syllabusName"/></th>
							<th><aebiz:showTitle titleId="syllabus.m.syllabusNote"/></th>
							<th><aebiz:showTitle titleId="syllabus.m.syllabusHandouts"/></th>
							<th><aebiz:showTitle titleId="syllabus.m.managerUuid"/></th>
							<th class='hidden-480'><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
			</div>
			<!--课程设定结束-->
			
			<!--增加培训计划开始 -->
			<div class="tab-pane" id="train2">
				<div class="y_clear">
					<div class="y_tablebtn">
						<a href="${pageContext.servletContext.contextPath}/sysback/trainplan/toAdd" class="btn" title="增加培训计划" rel="tooltip">增加培训计划</a>
						<a href="" class="btn" title="删除" rel="tooltip">删除</a>
					</div>  
				</div>
				<table  class="table table-bordered table-hover table-striped dataTable2 dataTable-nosort dataTable-reorder" >
					<thead>		
						<tr>
							<th class='with-checkbox'>
								<input type="checkbox" name="check_all" id="check_all">
							</th>
							<th><aebiz:showTitle titleId="trainplan.m.trainPlanNo"/></th>
							<th><aebiz:showTitle titleId="trainplan.m.trainStartTime"/></th>
							<th><aebiz:showTitle titleId="trainplan.m.trainEndTime"/></th>
							<th><aebiz:showTitle titleId="trainplan.m.syllabusUuid"/></th>
							<th><aebiz:showTitle titleId="trainplan.m.trainAddress"/></th>
							<th><aebiz:showTitle titleId="trainplan.m.managerUuid"/></th>
							<th class='hidden-480'><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
			</div>
			<!--增加培训计划开始 -->
			
			<!-- 新增培训计划-->
			<div class="tab-pane" id="train3">
				<table id="user" class="table table-bordered table-hover table-striped dataTable3 dataTable-nosort dataTable-reorder mt30" data-nosort="0,7">
					<thead>		
						<tr>
							<th class='with-checkbox'>
								<input type="checkbox" name="check_all" id="check_all">
							</th>
							<th>培训计划编号</th>
							<th>培训时间</th>
							<th>培训课程</th>
							<th>培训地点</th>
							<th>主讲人</th>
							<th>参加人员</th>
							<th class='hidden-480'>操作</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="with-checkbox">
								<input type="checkbox" name="check" value="1">
							</td>
							<td>pxjh0012</td>
							<td>2014-11-28</td>
							<td>深度清洁</td>
							<td>海淀区大钟寺14号院1号楼202室</td>
							<td>段先生</td>
							<td>
								<a id="searchshow" title="点击查看" rel="tooltip" data-toggle="modal" data-target="#modal-user">点击查看</a>
							</td>
							<td >
								<a href='trainingRecordsAdd.html' class='btn' rel='tooltip' title='新增培训记录'>新增培训记录</a>
							</td>
						</tr>
						<tr>
							<td class="with-checkbox">
								<input type="checkbox" name="check" value="1">
							</td>
							<td>pxjh0012</td>
							<td>2014-11-28</td>
							<td>深度清洁</td>
							<td>海淀区大钟寺14号院1号楼202室</td>
							<td>段先生</td>
							<td><a id="searchshow" title="点击查看" rel="tooltip" data-toggle="modal" data-target="#modal-user">点击查看</a></td>
							<td >
								<a href='#' class='btn' rel='tooltip' title='修改培训记录'>修改</a>
								<a href='trainingRecordsList.html' class='btn' rel='tooltip' title='查看培训记录'>查看</a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- 新增培训计划end-->
			
			<!--培训记录查看 start-->
			<div class="tab-pane" id="train4">
				<div class="y_clear mb_20">
					<div class="col-sm-2">
						培训次数：7次
					</div>
					<div class="col-sm-2">
						培训总时间：17小时
					</div>
					<div class="col-sm-2">
						参加人数：111人
					</div>
					<div class="col-sm-2">
						合格率：90%
					</div>
				</div>
				<table id="user" class="table table-bordered table-hover table-striped dataTable4 dataTable-nosort dataTable-reorder" data-nosort="0,7">
					<thead>		
						<tr>
							<th class='with-checkbox'>
								<input type="checkbox" name="check_all" id="check_all">
							</th>
							<th>培训计划编号</th>
							<th>培训时间</th>
							<th>培训课程</th>
							<th>培训地点</th>
							<th>主讲人</th>
							<th>参加人员</th>
							<th>培训记录查看</th>
							<th class='hidden-480'>培训合格率</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="with-checkbox">
								<input type="checkbox" name="check" value="1">
							</td>
							<td>pxjh0012</td>
							<td>2014-11-28</td>
							<td>深度清洁</td>
							<td>海淀区大钟寺14号院1号楼202室</td>
							<td>段先生</td>
							<td>
								<a id="searchshow" title="点击查看" rel="tooltip" data-toggle="modal" data-target="#modal-user">点击查看</a>
							</td>
							<td><a href="trainingRecordsList.html">培训记录查看</a></td>
							<td>80%</td>
						</tr>
						<tr>
							<td class="with-checkbox">
								<input type="checkbox" name="check" value="1">
							</td>
							<td>pxjh0012</td>
							<td>2014-11-28</td>
							<td>深度清洁</td>
							<td>海淀区大钟寺14号院1号楼202室</td>
							<td>段先生</td>
							<td><a id="searchshow" title="点击查看" rel="tooltip" data-toggle="modal" data-target="#modal-user">点击查看</a></td>
							<td><a href="trainingRecordsList.html">培训记录查看</a></td>
							<td>89%</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!--培训记录查看 end-->
		</div>
		
		
	</div>
	
	
</body>

</html>

<script>
function retrieveData(sSource,aoData,fnCallback) { 
	
	var searchParam = new Array() ;
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
    if ($('.dataTable1').length > 0) {
        $('.dataTable1').each(function() {            
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
											{"mDataProp":"syllabusNo" ,"sTitle": "<aebiz:showTitle titleId="syllabus.m.syllabusNo"/>"},
											{"mDataProp":"syllabusName" ,"sTitle": "<aebiz:showTitle titleId="syllabus.m.syllabusName"/>"},
											{"mDataProp":"syllabusNote" ,"sTitle": "<aebiz:showTitle titleId="syllabus.m.syllabusNote"/>"},
											{"mDataProp":"syllabusHandouts" ,"sTitle": "<aebiz:showTitle titleId="syllabus.m.syllabusHandouts"/>"},
											{"mDataProp":"managerUuid" ,"sTitle": "<aebiz:showTitle titleId="syllabus.m.managerUuid"/>"},

										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 	"fnRender": function(obj) {	
											  		var sReturn = "<a href='${pageContext.servletContext.contextPath}/syllabus/toUpdate/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>" ;
														sReturn += "<a href='javascript:removes(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>'><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></a>" ;															
											      return sReturn;
											 }			
										  }
										],
										
				          
				            'oTableTools': {
				                "sSwfPath": "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
				            },
				            "bRetrieve":true ,
				            "bProcessing":true,
				           	"bServerSide":true,                    //指定从服务器端获取数据
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/syllabus/queryList" ,
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



//删除
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
				$.post(
			    	"${pageContext.servletContext.contextPath}/syllabus/deletes",
			    	{"selectOne":checkIds,ranNum:Math.random()},	
				    function(data) {	       
				       var result = eval("("+data+")") ;	       
				       if(result.rsp) {
				  			//刷新
							//bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;
							doSearch('search') ;     	
				       }else{
				       		bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
				       }
				    }
				);		
        	}   
    });   
}

</script>


<script>
function retrieveData2(sSource,aoData,fnCallback) { 
	
	var searchParam = new Array() ;
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
    if ($('.dataTable2').length > 0) {
        $('.dataTable2').each(function() {            
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
			            
			            'sDom': "rtlip",
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
										{"mDataProp":"trainPlanNo" ,"sTitle": "<aebiz:showTitle titleId="trainplan.m.trainPlanNo"/>"},
										{"mDataProp":"trainStartTime" ,"sTitle": "<aebiz:showTitle titleId="trainplan.m.trainStartTime"/>"},
										{"mDataProp":"trainEndTime" ,"sTitle": "<aebiz:showTitle titleId="trainplan.m.trainEndTime"/>"},
										{"mDataProp":"syllabusUuid" ,"sTitle": "<aebiz:showTitle titleId="trainplan.m.syllabusUuid"/>"},
										{"mDataProp":"trainAddress" ,"sTitle": "<aebiz:showTitle titleId="trainplan.m.trainAddress"/>"},
										{"mDataProp":"managerUuid" ,"sTitle": "<aebiz:showTitle titleId="trainplan.m.managerUuid"/>"},
									  {
									  	"mDataProp":"operate",
									  	"sDefaultContent":"",
									  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
									 		"fnRender": function(obj) {	
										  		var sReturn = "<a href='${pageContext.servletContext.contextPath}/trainplan/toUpdate/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>" ;
													sReturn += "<a href='javascript:removes(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>'><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></a>" ;															
										      return sReturn;
										 }			
									  }
									],
							
			            'oTableTools': {
			                "sSwfPath": "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
			            },
			            "bRetrieve":true ,
			            "bProcessing":true,
			           	"bServerSide":true,                    //指定从服务器端获取数据
			            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/trainplan/queryList" ,
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


//删除
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
						$.post(
					    	"${pageContext.servletContext.contextPath}/trainplan/deletes",
					    	{"selectOne":checkIds,ranNum:Math.random()},	
						    function(data) {	       
						       var result = eval("("+data+")") ;	       
						       if(result.rsp) {
						  			//刷新
									//bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;
									doSearch('search') ;     	
						       }else{
						       		bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
						       }
						    }
						);		
        	}   
    });   
}



	$("#train1").click(function(){
		doSearch('search') ;
	});
	
	$("#train2").click(function(){
		doSearch2('search') ;
	});
	
	$("#train3").click(function(){
		doSearch3('search') ;
	});
	
	$("#train4").click(function(){
		doSearch4('search') ;
	});


$(document).ready(function() {
	    //初始化表格
   //doSearch('inittable') ;
   doSearch2('inittable') ;
   //复选框全选
	 $("#check_all").click(function(e) {
	 		$('input', oTable.fnGetNodes()).prop('checked', this.checked);
	 });       

}) 



</script>



