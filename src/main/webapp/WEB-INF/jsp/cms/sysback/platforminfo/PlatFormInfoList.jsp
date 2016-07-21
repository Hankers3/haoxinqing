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
				<h1><aebiz:showTitle titleId="platforminfo.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="platforminfo.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="platforminfo.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span><aebiz:showTitle titleId="platforminfo.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
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
										<label class="control-label"><aebiz:showTitle titleId="platforminfo.qmf.videoModel"/>：</label>
										<input type="text" name="videoModel" id="videoModel" class="form-control">
										<input type="hidden" name="videoModel_q" id="videoModel_q" class="form-control" value="Like">
										</div>
										<div  class="form-group">
										<label class="control-label"><aebiz:showTitle titleId="platforminfo.qmf.state"/>：</label>
										<select name="state" id="state" class='select2-me form-control' >								
										<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</option>
										<option value="0">未发布</option>
										<option value="1">已发布</option>
										</select>
										<input type="hidden" name="state_q" id="state_q" class="form-control" value="EQ">
										</div>
										<div  class="form-group">
										<label class="control-label"><aebiz:showTitle titleId="platforminfo.qmf.createTime"/>：</label>
										<input type="text" name="createTime2" id="createTime2" class="form-control datepick">
									</div>
									
									<div class="form-group">
										<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
										<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
									</div>
								</div>
								
								<div class="y_tablebtn">
									<a class="btn" href="${pageContext.servletContext.contextPath}/sysback/platforminfo/toAdd" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/></a>
									<button class="btn" onclick="javascript:removes('');" title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></button>
								</div>
						</div>
						
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th class='with-checkbox'>
										<input type="checkbox" name="check_all" id="check_all">
									</th>
									<th><aebiz:showTitle titleId="platforminfo.m.videoModel"/></th>
									<th><aebiz:showTitle titleId="platforminfo.m.userName"/></th>
									<th><aebiz:showTitle titleId="platforminfo.m.createTime"/></th>
									<th><aebiz:showTitle titleId="platforminfo.m.state"/></th>
									<th>是否为热播视频</th>
									<!--<th><aebiz:showTitle titleId="platforminfo.m.number"/></th>
									<th><aebiz:showTitle titleId="platforminfo.m.startTime"/></th>
									<th><aebiz:showTitle titleId="platforminfo.m.endTime"/></th>-->
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
	searchParam.push({ "name": "videoModel", "value": $.trim($("#videoModel").val())});
	searchParam.push({ "name": "videoModel_q", "value": $.trim($("#videoModel_q").val())});
	
	searchParam.push({ "name": "state", "value": $.trim($("#state").val())});
	searchParam.push({ "name": "state_q", "value": $.trim($("#state_q").val())});
	
	searchParam.push({ "name": "createTime2", "value": $.trim($("#createTime2").val())});
	/*searchParam.push({ "name": "type_s", "value": $.trim($("#type_s").val())});
	searchParam.push({ "name": "type_q", "value": $.trim($("#type_q").val())});
	searchParam.push({ "name": "number_s", "value": $.trim($("#number_s").val())});
	searchParam.push({ "name": "number_q", "value": $.trim($("#number_q").val())});
	searchParam.push({ "name": "startTime_s", "value": $.trim($("#startTime_s").val())});
	searchParam.push({ "name": "startTime_q", "value": $.trim($("#startTime_q").val())});
	searchParam.push({ "name": "endTime_s", "value": $.trim($("#endTime_s").val())});
	searchParam.push({ "name": "endTime_q", "value": $.trim($("#endTime_q").val())});
	searchParam.push({ "name": "createTime2_s", "value": $.trim($("#createTime2_s").val())});
	searchParam.push({ "name": "createTime2_q", "value": $.trim($("#createTime2_q").val())});
	searchParam.push({ "name": "videoModel", "value": $.trim($("#videoModel").val())});
	searchParam.push({ "name": "state", "value": $.trim($("#state").val())});
	searchParam.push({ "name": "createTime", "value": $.trim($("#createTime").val())});*/
			
	
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
				            "aaSorting": [[3, "desc" ]],
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
											{"mDataProp":"videoModel" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="platforminfo.m.videoModel"/>"},
											
											{"mDataProp":"userName" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="platforminfo.m.userName"/>"},
											{"mDataProp":"createTime" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="platforminfo.m.createTime"/>"},
											{"mDataProp":"videoHot" ,"sTitle": "是否为热播视频",
											"fnRender":function(obj){
												if(obj.aData.videoHot==1) {return "是" ;}else{return "否" ;}
											}},
											/*{"mDataProp":"number" ,"sTitle": "<aebiz:showTitle titleId="platforminfo.m.number"/>"},
											{"mDataProp":"startTime" ,"sTitle": "<aebiz:showTitle titleId="platforminfo.m.startTime"/>"},
											{"mDataProp":"endTime" ,"sTitle": "<aebiz:showTitle titleId="platforminfo.m.endTime"/>"},
											*/
											{
												"mDataProp":"state" ,
												"sDefaultContent":"",
												"sTitle": "<aebiz:showTitle titleId="platforminfo.m.state"/>",
											  "fnRender":function(obj){
													if(obj.aData.state==1) {return "已发布" ;}else{return "未发布" ;}
											  }
											},
										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 	"fnRender": function(obj) {	
											  		var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/platforminfo/toUpdate/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>" ;
														sReturn += "<a href='javascript:removes(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>'><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></a>" ;															
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
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/platforminfo/queryList" ,
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
	$("#videoModel").val("") ;
	$("#state").val("") ;
	$("#createTime2").val("") ;
	
}

//清空高级搜索的所有选项
function clearMoreSearch() {
	$("#videoModel_s").val("") ;
	$("#videoIntroduction_s").val("") ;
	$("#state_s").val("") ;
	$("#videoAddress_s").val("") ;
	$("#userName_s").val("") ;
	$("#image_s").val("") ;
	$("#createTime_s").val("") ;
	$("#type_s").val("") ;
	$("#number_s").val("") ;
	$("#startTime_s").val("") ;
	$("#endTime_s").val("") ;
	$("#createTime2_s").val("") ;
	
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
			    	"${pageContext.servletContext.contextPath}/sysback/platforminfo/deletes",
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