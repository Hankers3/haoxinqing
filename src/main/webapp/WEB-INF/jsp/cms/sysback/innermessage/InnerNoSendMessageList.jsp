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
				<h1>待发站内信<aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="innermessage.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="innermessage.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span><aebiz:showTitle titleId="innermessage.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
				</li>						
			</ul>				
		</div>				
							
		<div class="row">
				<div class="col-sm-12">
					<div class="box">
						<div class="box-content nopadding y_tableser">
							<div class="y_clear">
								<div class="form-inline table_formnew">
								<!-- 站内信未发送状态-->
								<input type="hidden" name="sendStatus" id="sendStatus"  value="0" class="form-control">
								<input type="hidden" name="sendStatus_q" id="sendStatus_q" class="form-control" value="EQ">
								
									<div  class="form-group">
										<label class="control-label"><aebiz:showTitle titleId="innermessage.qmf.title"/>：</label>
										<input type="text" name="title" id="title" class="form-control">
										<input type="hidden" name="title_q" id="title_q" class="form-control" value="Like">
									</div>
	
									
									<div class="form-group">
										<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
										<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
										<button class="btn" id="searchshow" title="<aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/>" rel="tooltip" data-toggle="modal" data-target="#modal-user"><aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/></button>
									</div>
								</div>
								
								<div class="y_tablebtn">
									<button class="btn" onclick="javascript:removes('');" title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></button>
								</div>
						</div>
						
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th class='with-checkbox'>
										<input type="checkbox" name="check_all" id="check_all">
									</th>
									<th><aebiz:showTitle titleId="innermessage.m.title"/></th>
									<!--<th><aebiz:showTitle titleId="innermessage.m.content"/></th>-->
									<th><aebiz:showTitle titleId="innermessage.m.sendUser"/></th>
									<th><aebiz:showTitle titleId="innermessage.m.sendTime"/></th>
									<th><aebiz:showTitle titleId="innermessage.m.readStatus"/></th>
									<th><aebiz:showTitle titleId="innermessage.m.sendStatus"/></th>
									<th><aebiz:showTitle titleId="innermessage.m.accountType"/></th>
										
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
	
	<!--高级搜索-->
		<div id="modal-user" class="modal fade y_highserch">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 id="user-infos"><aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/></h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-sm-6">
						 		<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="innermessage.m.title"/></label>
									<input type="text" name="title_s" id="title_s" class="form-control">
									<input type="hidden" name="title_q" id="title_q" value="Like">
								</div>
							</div>
							<div class="col-sm-6">
							  <div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="innermessage.m.content"/></label>
									<input type="text" name="content_s" id="content_s" class="form-control">
									<input type="hidden" name="content_q" id="content_q" value="Like">
							  </div>
							</div>
							<div class="col-sm-6">
						 		<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="innermessage.m.sendUser"/></label>
									<input type="text" name="sendUser_s" id="sendUser_s" class="form-control">
									<input type="hidden" name="sendUser_q" id="sendUser_q" value="EQ">
								</div>
							</div>
							<div class="col-sm-6">
							 	<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="innermessage.m.receiveUser"/></label>
									<input type="text" name="receiveUser_s" id="receiveUser_s" class="form-control">
									<input type="hidden" name="receiveUser_q" id="receiveUser_q" value="EQ">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
							 	<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="innermessage.m.sendTime"/></label>
									<input type="text" name="sendTime_s" id="sendTime_s" class="form-control">
									<input type="hidden" name="sendTime_q" id="sendTime_q" value="EQ">
								</div>
							</div>
							<div class="col-sm-6">
							 	<div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="innermessage.m.readStatus"/></label>
									<input type="text" name="readStatus_s" id="readStatus_s" class="form-control">
									<input type="hidden" name="readStatus_q" id="readStatus_q" value="EQ">
								</div>
							</div>
						</div>
					</div>													
					<div class="modal-footer">
						<button class="btn moresearch btn-primary" data-dismiss="modal"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
						<button class="btn clearMoreSearch" ><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
					</div>
				</div>
			</div>
		</div>
		<!--高级搜索-->
	
	
	
<!--推送 家政员-->
<div id="Remarks" class="modal fade">
	<div id="showSend">
		
	</div>
</div>
<!--推送 end-->
	
<!--推送 家政员-->
<div id="user" class="modal fade">
	<div id="showSenduser">
		
	</div>
</div>
<!--推送 end-->
	
</body>

</html>

<script>
function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
	searchParam.push({ "name": "sendStatus", "value": $.trim($("#sendStatus").val())});
	searchParam.push({ "name": "sendStatus_q", "value": $.trim($("#sendStatus_q").val())});
	
	searchParam.push({ "name": "title_s", "value": $.trim($("#title_s").val())});
	searchParam.push({ "name": "title_q", "value": $.trim($("#title_q").val())});
	searchParam.push({ "name": "content_s", "value": $.trim($("#content_s").val())});
	searchParam.push({ "name": "content_q", "value": $.trim($("#content_q").val())});
	searchParam.push({ "name": "sendUser_s", "value": $.trim($("#sendUser_s").val())});
	searchParam.push({ "name": "sendUser_q", "value": $.trim($("#sendUser_q").val())});
	searchParam.push({ "name": "sendTime_s", "value": $.trim($("#sendTime_s").val())});
	searchParam.push({ "name": "sendTime_q", "value": $.trim($("#sendTime_q").val())});
	searchParam.push({ "name": "readStatus_s", "value": $.trim($("#readStatus_s").val())});
	searchParam.push({ "name": "readStatus_q", "value": $.trim($("#readStatus_q").val())});
	
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
				                'aTargets': [0, 6]                
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
											{"mDataProp":"title" ,"sTitle": "<aebiz:showTitle titleId="innermessage.m.title"/>"},
											//{"mDataProp":"content" ,"sTitle": "<aebiz:showTitle titleId="innermessage.m.content"/>"},
											{"mDataProp":"sendUser" ,"sTitle": "<aebiz:showTitle titleId="innermessage.m.sendUser"/>"},
											{"mDataProp":"sendTime" ,"sWidth":"15%","sTitle": "<aebiz:showTitle titleId="innermessage.m.sendTime"/>"},
											{"mDataProp":"readStatusName" ,"sTitle": "<aebiz:showTitle titleId="innermessage.m.readStatus"/>"},
											{
												"mDataProp":"sendStatus" ,
												"sDefaultContent":"",
												"sTitle": "<aebiz:showTitle titleId="innermessage.m.sendStatus"/>",
												"fnRender": function(obj) {
														var sReturn = "";
														if(obj.aData.sendStatus=='0'){
															sReturn+="未发送";
														}else{
															sReturn+="已发送";
														}
														return sReturn;
												}
											},
											
											{
												"mDataProp":"messageType" ,
												"sDefaultContent":"",
												"sTitle": "<aebiz:showTitle titleId="innermessage.m.accountType"/>",
												"fnRender": function(obj) {
														var sReturn = "";
														if(obj.aData.accountType=='0'){
															sReturn+="所有";
														}else if(obj.aData.accountType=='1'){
															sReturn+="家政员";
														}else{
															sReturn+="会员";
														}
														return sReturn;
												}
											},
											
										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 		"fnRender": function(obj) {	
											  		var sReturn = "";
											  		if(obj.aData.accountType=='1'){
											  				sReturn+="<a href='#' onclick='javascript:toPushInnerMessage(\""+obj.aData.uuid+"\");'  class='btn' rel='tooltip'  data-toggle='modal' data-target='#Remarks' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'>推送家政员</a>" ;
											  		}else if(obj.aData.accountType=='2'){
											  				sReturn+="<a href='#' onclick='javascript:toPushUserInnerMessage(\""+obj.aData.uuid+"\");'  class='btn' rel='tooltip'  data-toggle='modal' data-target='#user' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'>推送会员</a>" ;
											  		}
											  		sReturn+="<a href='${pageContext.servletContext.contextPath}/sysback/innermessage/toUpdate/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>" ;
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
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/innermessage/queryList" ,
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
	$("#title").val("") ;
	
}

//清空高级搜索的所有选项
function clearMoreSearch() {
	$("#title_s").val("") ;
	$("#content_s").val("") ;
	$("#sendUser_s").val("") ;
	$("#receiveUser_s").val("") ;
	$("#sendTime_s").val("") ;
	$("#fjDelStatus_s").val("") ;
	$("#readStatus_s").val("") ;
	$("#sjDelStatus_s").val("") ;
	$("#messageType_s").val("") ;
	$("#accountType_s").val("") ;
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
			    	"${pageContext.servletContext.contextPath}/sysback/innermessage/deletes",
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

//内容推送家政员
function toPushInnerMessage(uuid){
	var url="${pageContext.servletContext.contextPath}/sysback/innermessage/toPushInnerMessage/"+uuid;
		$.post(url,
		{
			ranNum : Math.random()
		},
		function(data){
				$("#showSend").html(data);
		}
	);
}

function bgToPushInner(uuid){
	var levelId = $("#levelId").val();
	var url="${pageContext.servletContext.contextPath}/sysback/innermessage/pushInnerMessage/"+uuid;
	$.post(url,
		{
			"levelUuid":levelId,
			ranNum : Math.random()
		},
		function(data){
				if("push_success"==data){
					location.reload();
					alert("站内信已发送！");
				}else if("push_null"==data){
					location.reload();
					alert("无此等级用户！");
				}else if("no_user"==data){
					location.reload();
					alert("暂无登陆用户！");
				}else{
					location.reload();
					alert("站内信发送失败！");
				}
		}
		);
}


//内容推送会员
function toPushUserInnerMessage(uuid){
	var url="${pageContext.servletContext.contextPath}/sysback/innermessage/toPushUserInnerMessage/"+uuid;
		$.post(url,
		{
			ranNum : Math.random()
		},
		function(data){
				$("#showSenduser").html(data);
		}
	);
}

function bgToPushUserInner(uuid){
	var levelId = $("#levelId").val();
	var url="${pageContext.servletContext.contextPath}/sysback/innermessage/pushUserInnerMessage/"+uuid;
	$.post(url,
		{
			"levelUuid":levelId,
			ranNum : Math.random()
		},
		function(data){
				if("push_success"==data){
					location.reload();
					alert("站内信已发送！");
				}else if("push_null"==data){
					location.reload();
					alert("无此等级用户！");
				}else if("no_user"==data){
					location.reload();
					alert("暂无登陆用户！");
				}else{
					location.reload();
					alert("站内信发送失败！");
				}
		}
		);
}

</script>