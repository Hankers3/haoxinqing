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
				<h1><aebiz:showTitle titleId="doctornotice.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="doctornotice.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="doctornotice.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span><aebiz:showTitle titleId="doctornotice.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
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
										<label for="textfield" class="control-label">医生姓名</label>
										<input type="text" name="doctorName" id="doctorName" class="form-control">
										<input type="hidden" name="doctorName_q" id="doctorName_q" class="form-control" value="Like">
										
										<label for="textfield" class="control-label">手机号码</label>
										<input type="text" name="mobile" id="mobile"  class="form-control" value="">
									
										
									  <label for="textfield" class="control-label"><aebiz:showTitle titleId="doctornotice.m.line"/></label>
										<input type="text" name="line" id="line" class="form-control">
										<input type="hidden" name="line_q" id="line_q" class="form-control" value="Like">
									</div>
	
									
									<div class="form-group">
										<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
										<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
										<!--高级搜索按钮<button class="btn" id="searchshow" title="<aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/>" rel="tooltip" data-toggle="modal" data-target="#modal-user"><aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/></button>-->
									</div>
								</div>
								<!--
								<div class="y_tablebtn">
									<a class="btn" href="${pageContext.servletContext.contextPath}/sysback/doctornotice/toAdd" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/></a>
									<button class="btn" onclick="javascript:removes('');" title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></button>
								</div>
								-->
						</div>
						
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th class='with-checkbox'>
										<input type="checkbox" name="check_all" id="check_all">
									</th>
									<%-- <th><aebiz:showTitle titleId="doctornotice.m.serviceStatffUuid"/></th> --%>
									<th><aebiz:showTitle titleId="doctornotice.m.doctorName"/></th>
									<th><aebiz:showTitle titleId="doctornotice.m.line"/></th>
									<!--<th><aebiz:showTitle titleId="doctornotice.m.content"/></th>-->
									<th><aebiz:showTitle titleId="doctornotice.m.createTime"/></th>
	
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
								searchParam.push({ "name": "doctorName_q", "value": $.trim($("#doctorName_q").val())}); //这两个条件都要加
								searchParam.push({ "name": "line", "value": $.trim($("#line").val())});
								searchParam.push({ "name": "line_q", "value": $.trim($("#line_q").val())});
								searchParam.push({ "name": "mobile", "value": $.trim($("#mobile").val())});
								//searchParam.push({ "name": "mobile_q", "value": $.trim($("#mobile_q").val())});
								
								/*查询时用的条件 不一定全用的到
								searchParam.push({ "name": "serviceStatffUuid_s", "value": $.trim($("#serviceStatffUuid_s").val())});
								searchParam.push({ "name": "serviceStatffUuid_q", "value": $.trim($("#serviceStatffUuid_q").val())});
								searchParam.push({ "name": "doctorName_s", "value": $.trim($("#doctorName_s").val())});
								searchParam.push({ "name": "doctorName_q", "value": $.trim($("#doctorName_q").val())});
								searchParam.push({ "name": "line_s", "value": $.trim($("#line_s").val())});
								searchParam.push({ "name": "line_q", "value": $.trim($("#line_q").val())});
								searchParam.push({ "name": "content_s", "value": $.trim($("#content_s").val())});
								searchParam.push({ "name": "content_q", "value": $.trim($("#content_q").val())});
								searchParam.push({ "name": "createTime_s", "value": $.trim($("#createTime_s").val())});
								searchParam.push({ "name": "createTime_q", "value": $.trim($("#createTime_q").val())});
								searchParam.push({ "name": "", "value": $.trim($("#").val())});
								*/
										
								
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
				                'aTargets': [0, 1,2,4]                
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
											//{"mDataProp":"serviceStatffUuid" ,"sTitle": "<aebiz:showTitle titleId="doctornotice.m.serviceStatffUuid"/>"},
											{"mDataProp":"doctorName" ,"sTitle": "医生姓名"},
											{"mDataProp":"line" ,"sTitle": "<aebiz:showTitle titleId="doctornotice.m.line"/>"},
											//{"mDataProp":"content" ,"sTitle": "<aebiz:showTitle titleId="doctornotice.m.content"/>"},
											
											{"mDataProp":"createTime" ,"sDefaultContent" : "", "sTitle": "<aebiz:showTitle titleId="doctornotice.m.createTime"/>"},
		

										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 	"fnRender": function(obj) {	  //挂删除修改功能                                              /toUpdate
											  		var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/doctornotice/toView/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='查看详情'>查看详情</a>" ;
														//sReturn += "<a href='javascript:removes(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>'><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></a>" ;															
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
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/doctornotice/queryList" ,
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
	$("#line").val("") ;
	$("#mobile").val("") ;
}

//清空高级搜索的所有选项
function clearMoreSearch() {
	$("#serviceStatffUuid_s").val("") ;
	$("#doctorName_s").val("") ;
	$("#line_s").val("") ;
	$("#content_s").val("") ;
	$("#createTime_s").val("") ;
	$("#_s").val("") ;
	
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
			    	"${pageContext.servletContext.contextPath}/sysback/doctornotice/deletes",
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