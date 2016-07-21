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
				<a href="#train1" data-toggle='tab'>课程设定</a>
			</li>
			<li>
				<a href="#train2" data-toggle='tab'>培训计划</a>
			</li>
			<li>
				<a href="#train3" data-toggle='tab'>培训记录</a>
			</li>
			<li>
				<a href="#train4" data-toggle='tab'>培训结果</a>
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
				<table id="user" class="table table-bordered table-hover table-striped dataTable1 dataTable-nosort dataTable-reorder" data-nosort="0,4">
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
				<table id="user" class="table table-bordered table-hover table-striped dataTable2 dataTable-nosort dataTable-reorder" data-nosort="0,7">
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
							<th>参与人员</th>
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
				
					<input type="hidden" name="state" id="state"  value="1" >
					<input type="hidden" name="state_q" id="state_q"  value="EQ" >
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
							<th>参与人员</th>
							<th class='hidden-480'><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
			</div>
			<!-- 新增培训计划end-->
			
			<!--培训记录查看 start-->
			<div class="tab-pane" id="train4">
				<div class="y_clear mb_20">
					<div class="col-sm-2">
						培训次数：${tpcont}次
					</div>
				<!--
					<div class="col-sm-2">
						培训总时间：17小时
					</div>-->
					<div class="col-sm-2">
						参加人数：${allnum}人
					</div>
					<div class="col-sm-2">
						合格率：${percet}%
					</div>
				</div>
				<table id="user" class="table table-bordered table-hover table-striped dataTable4 dataTable-nosort dataTable-reorder" data-nosort="0,7">
					
					
					<input type="hidden" name="addState" id="addState"  value="1" >
					<input type="hidden" name="addState_q" id="addState_q"  value="EQ" >
					<thead>		
						<tr>
							<th><aebiz:showTitle titleId="trainplan.m.trainPlanNo"/></th>
							<th><aebiz:showTitle titleId="trainplan.m.trainStartTime"/></th>
							<th><aebiz:showTitle titleId="trainplan.m.trainEndTime"/></th>
							<th><aebiz:showTitle titleId="trainplan.m.syllabusUuid"/></th>
							<th><aebiz:showTitle titleId="trainplan.m.trainAddress"/></th>
							<th><aebiz:showTitle titleId="trainplan.m.managerUuid"/></th>
							<th>参与人员</th>
							<th>培训记录查看</th>
							<th class='hidden-480'>培训合格率</th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
			</div>
			<!--培训记录查看 end-->
		</div>
		
	</div>
	
	
	
<!--培训参加人员列表-->
<div id="modal-user" class="modal fade">
	<div id="showTousu"> </div>
</div>
<!--培训参加人员列表-->	
	
	
<!--参加人员列表-->
<div id="modal-userh" class="modal fade">
	<div id="showTousuh"> </div>
</div>
<!--参加人员列表-->	

	
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
											{"mDataProp":"syllabusNo" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="syllabus.m.syllabusNo"/>"},
											{"mDataProp":"syllabusName" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="syllabus.m.syllabusName"/>"},
											{"mDataProp":"syllabusNote" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="syllabus.m.syllabusNote"/>"},
											{"mDataProp":"syllabusHandouts" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="syllabus.m.syllabusHandouts"/>"},
											{
												"mDataProp":"managerName" ,
												"sDefaultContent":"",
												"sTitle": "<aebiz:showTitle titleId="syllabus.m.managerUuid"/>"
											},

										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 		"fnRender": function(obj) {	
											  		var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/syllabus/toUpdate/"+obj.aData.uuid+"' class='btn btn-primary ' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>" ;
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
			    	"${pageContext.servletContext.contextPath}/sysback/syllabus/deletes",
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
										{"mDataProp":"trainPlanNo" ,"sWidth":"8%","sTitle": "<aebiz:showTitle titleId="trainplan.m.trainPlanNo"/>"},
										{"mDataProp":"trainStartTime","sWidth":"8%" ,"sTitle": "<aebiz:showTitle titleId="trainplan.m.trainStartTime"/>"},
										{"mDataProp":"trainEndTime" ,"sWidth":"8%","sTitle": "<aebiz:showTitle titleId="trainplan.m.trainEndTime"/>"},
										{"mDataProp":"syllabusName" ,"sTitle": "<aebiz:showTitle titleId="trainplan.m.syllabusUuid"/>"},
										{"mDataProp":"trainAddress" ,"sTitle": "<aebiz:showTitle titleId="trainplan.m.trainAddress"/>"},
										{
											"mDataProp":"managerName" ,
											"sDefaultContent":"",
											"sTitle": "<aebiz:showTitle titleId="syllabus.m.managerUuid"/>"
										},
										{	
											"mDataProp":"managerUuid1" ,
											"sDefaultContent":"",
											"sTitle": "参与人员",
											"fnRender": function(obj) {	
												var sReturn = "";
												sReturn+="<a href='#'  onclick='javascript:toChoseStaff(\""+obj.aData.uuid+"\");'   data-toggle='modal' data-target='#modal-user' title='关联培训人员'>关联培训人员</a>";
										    return sReturn;
											 }
										},
									  {
									  	"mDataProp":"operate",
									  	"sDefaultContent":"",
									  	"sWidth":"18%",
									  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
									 		"fnRender": function(obj) {	
										  		var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/trainplan/toUpdate/"+obj.aData.uuid+"' class='btn btn-primary ' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>" ;
													sReturn += "<a href='javascript:removes(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>'><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></a>" ;															
										      
										      sReturn+="<a href='javascript:toSendPlan(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip' title='推送培训计划'>推送培训计划</a>";
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
  	if('inittable2' != operact) {  			
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
					    	"${pageContext.servletContext.contextPath}/sysback/trainplan/deletes",
					    	{"selectOne":checkIds,ranNum:Math.random()},	
						    function(data) {	       
						       var result = eval("("+data+")") ;	       
						       if(result.rsp) {
						  			//刷新
									//bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;
									doSearchx('search') ;     	
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
   doSearch2('inittable2');
   doSearch3('inittable3');
   doSearch4('inittable4');
   //复选框全选
	 $("#check_all").click(function(e) {
	 		$('input', oTable.fnGetNodes()).prop('checked', this.checked);
	 });  
}) 
</script>


<script>
function retrieveData3(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
 	searchParam.push({ "name": "state", "value": $.trim($("#state").val())});
  searchParam.push({ "name": "state_q", "value": $.trim($("#state_q").val())});
   $.ajax({    	        
  	"dataType": 'json', 
	  "type": "POST", 
	  "url": sSource, 
	  "data": {aoData:JSON.stringify(aoData),searchParam:JSON.stringify(searchParam)}, 
	  "success": fnCallback
  });    
} 
    
var oTable3 = null; 
function doSearch3(operact) {	
	if(oTable3 == null) {		 
    if ($('.dataTable3').length > 0) {
        $('.dataTable3').each(function() {            
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
										{"mDataProp":"trainPlanNo" ,"sWidth":"8%","sTitle": "<aebiz:showTitle titleId="trainplan.m.trainPlanNo"/>"},
										{"mDataProp":"trainStartTime","sWidth":"8%" ,"sTitle": "<aebiz:showTitle titleId="trainplan.m.trainStartTime"/>"},
										{"mDataProp":"trainEndTime" ,"sWidth":"8%","sTitle": "<aebiz:showTitle titleId="trainplan.m.trainEndTime"/>"},
										{"mDataProp":"syllabusName" ,"sTitle": "<aebiz:showTitle titleId="trainplan.m.syllabusUuid"/>"},
										{"mDataProp":"trainAddress" ,"sTitle": "<aebiz:showTitle titleId="trainplan.m.trainAddress"/>"},
										{
											"mDataProp":"managerName" ,
											"sDefaultContent":"",
											"sTitle": "<aebiz:showTitle titleId="syllabus.m.managerUuid"/>"
										},
										{	
											"mDataProp":"managerUuid1" ,
											"sDefaultContent":"",
											"sTitle": "参与人员",
											"fnRender": function(obj) {	
												var sReturn = "";
												sReturn+="<a href='#'  onclick='javascript:toHasChoseStaff(\""+obj.aData.uuid+"\");'   data-toggle='modal' data-target='#modal-userh' title='点击查看'>点击查看</a>";
										    return sReturn;
											 }
										},
									  {
									  	"mDataProp":"operate",
									  	"sDefaultContent":"",
									  	"sWidth":"18%",
									  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
									 		"fnRender": function(obj) {	
										  		var sReturn = "" ;
										  		if(obj.aData.addState=='0'){
										  				sReturn+="<a href='${pageContext.servletContext.contextPath}/sysback/trainstaff/toNewAdd/"+obj.aData.uuid+"' class='btn btn-primary ' rel='tooltip' title='新增培训记录'>新增培训记录</a>";
										  		}else if(obj.aData.addState=='1'){
										  				sReturn+= "<a href='${pageContext.servletContext.contextPath}/sysback/trainplan/toUpdate/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>" ;
										  				sReturn+= "<a href='${pageContext.servletContext.contextPath}/sysback/trainstaff/toNewView/"+obj.aData.uuid+"' class='btn btn-primary ' rel='tooltip' title='查看'>查看</a>" ;	
										  		}
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
									"fnServerData":retrieveData3,           //获取数据的处理函数  
			        };
			        
          		if ($(this).hasClass("dataTable-reorder")) {
                  opt.sDom = "R" + opt.sDom;
              }
              oTable3 = $(this).dataTable(opt);
        	});
   		}         
	}
		
	//刷新Datatable，会自动激发retrieveData  		
  	if('inittable3' != operact) {  			
		oTable3.fnDraw(); 	
	}  
	 	  
}

</script>


<script>
function retrieveData4(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
	searchParam.push({ "name": "addState", "value": $.trim($("#addState").val())});
  searchParam.push({ "name": "addState_q", "value": $.trim($("#addState_q").val())});
	
   $.ajax({    	        
  	"dataType": 'json', 
	  "type": "POST", 
	  "url": sSource, 
	  "data": {aoData:JSON.stringify(aoData),searchParam:JSON.stringify(searchParam)}, 
	  "success": fnCallback
  });    
} 
    
var oTable4 = null; 
function doSearch4(operact) {	
	if(oTable4 == null) {		 
    if ($('.dataTable4').length > 0) {
        $('.dataTable4').each(function() {            
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
								
										{"mDataProp":"trainPlanNo" ,"sWidth":"8%","sTitle": "<aebiz:showTitle titleId="trainplan.m.trainPlanNo"/>"},
										{"mDataProp":"trainStartTime","sWidth":"8%" ,"sTitle": "<aebiz:showTitle titleId="trainplan.m.trainStartTime"/>"},
										{"mDataProp":"trainEndTime" ,"sWidth":"8%","sTitle": "<aebiz:showTitle titleId="trainplan.m.trainEndTime"/>"},
										{"mDataProp":"syllabusName" ,"sTitle": "<aebiz:showTitle titleId="trainplan.m.syllabusUuid"/>"},
										{"mDataProp":"trainAddress" ,"sTitle": "<aebiz:showTitle titleId="trainplan.m.trainAddress"/>"},
										{
											"mDataProp":"managerName" ,
											"sDefaultContent":"",
											"sTitle": "<aebiz:showTitle titleId="syllabus.m.managerUuid"/>"
										},
										{	
											"mDataProp":"managerUuid1" ,
											"sDefaultContent":"",
											"sTitle": "参与人员",
											"fnRender": function(obj) {	
												var sReturn = "";
												sReturn+="<a href='#'  onclick='javascript:toChoseStaff(\""+obj.aData.uuid+"\");'   data-toggle='modal' data-target='#modal-user' title='点击查看'>点击查看</a>";
										    return sReturn;
											 }
										},
										{	
											"mDataProp":"managerUuid2" ,
											"sDefaultContent":"",
											"sTitle": "培训记录查看",
											"fnRender": function(obj) {	
												var sReturn = "";
												sReturn+="<a href='${pageContext.servletContext.contextPath}/sysback/trainstaff/toNewView/"+obj.aData.uuid+"'  onclick='javascript:toChoseStaff(\""+obj.aData.uuid+"\");'   title='培训记录查看'>培训记录查看</a>";
										    return sReturn;
											 }
										},
									  {
									  	"mDataProp":"percent",
									  	"sDefaultContent":"",
									  	"sWidth":"10%",	
									  	"sTitle": "培训合格率" ,
									 		"fnRender": function(obj) {	
										  		var sReturn = obj.aData.percent+"%";
										      
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
									"fnServerData":retrieveData4,           //获取数据的处理函数  
			        };
			        
          		if ($(this).hasClass("dataTable-reorder")) {
                  opt.sDom = "R" + opt.sDom;
              }
              oTable4 = $(this).dataTable(opt);
        	});
   		}         
	}
		
	//刷新Datatable，会自动激发retrieveData  		
  	if('inittable4' != operact) {  			
		oTable4.fnDraw(); 	
	}  
	 	  
}

</script>



<script>


//跳转到选择家政员信息
function toChoseStaff(uuid){
	var url="${pageContext.servletContext.contextPath}/sysback/trainplan/toChoseStaff/"+uuid;
	$.post(url,
	{
		ranNum : Math.random()
	},
	function(data){
			$("#showTousu").html(data);
	}
	);
}


//添加家政员
function toAddStaff(planUuid,staffUuid){
		var checkIds="";
		if(staffUuid.trim() != "") {
			checkIds = staffUuid ;
		}else{
			$("input[name='check']:checkbox").each(function(){				
        if($(this).is(":checked")){        	
            checkIds += $(this).val()+"," ;  
        }
    	})	
		}
		
		if(checkIds.trim() == "") {
				//提示为空
				bootbox.alert("请选择要添加的家政员") ;
				return ;
		} 
		bootbox.confirm("确定选择添加的家政员", function(r){
       if(r) {
        //添加
				$.post(
			    	"${pageContext.servletContext.contextPath}/sysback/trainplan/toAddStaff/"+planUuid,
			    	{
			    		"selectOne":checkIds,
			    		ranNum:Math.random()
			    	},	
				    function(data) {	       
				       var result = eval("("+data+")") ;	       
				       if(result.rsp) {
				  			//刷新
								doSearchx('search') ;     	
				       }else{
				       		bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
				       }
				    }
				);		
         }   
    });   
}

//跳转已选择家政员信息
function toHasChoseStaff(uuid){
	var url="${pageContext.servletContext.contextPath}/sysback/trainplan/toHasChoseStaff/"+uuid;
	$.post(url,
	{
		ranNum : Math.random()
	},
	function(data){
			$("#showTousuh").html(data);
	}
	);
}


//跳转已选择家政员信息
function toSendPlan(uuid){
	var url="${pageContext.servletContext.contextPath}/sysback/trainplan/toSendPlan/"+uuid;
	$.post(url,
	{
		ranNum : Math.random()
	},
	function(data){
			if(data=="push_success"){
					bootbox.alert('操作成功') ;
					location.reload();
			}else if(data=="no_staff"){
					bootbox.alert('请关联培训参与人员') ;
			}else{
					bootbox.alert('推送培训计划失败，请重新尝试！') ;
			}
	}
	);
}


</script>