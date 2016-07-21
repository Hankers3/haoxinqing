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
				<h1><aebiz:showTitle titleId="servicestaffinterview.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="servicestaffinterview.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="servicestaffinterview.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span><aebiz:showTitle titleId="servicestaffinterview.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
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
										<label class="control-label"><aebiz:showTitle titleId="servicestaffinterview.qmf.serviceStatffUuid"/>：</label>
										<input type="text" name="serviceStatffUuid" id="serviceStatffUuid" class="form-control">
										<input type="hidden" name="serviceStatffUuid_q" id="serviceStatffUuid_q" class="form-control" value="EQ">
									</div>
									<div  class="form-group">
										<label class="control-label"><aebiz:showTitle titleId="servicestaffinterview.qmf.interviewUuid"/>：</label>
										<input type="text" name="interviewUuid" id="interviewUuid" class="form-control">
										<input type="hidden" name="interviewUuid_q" id="interviewUuid_q" class="form-control" value="EQ">
									</div>
									<div class="form-group">
										<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
										<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
										<button class="btn" id="searchshow" title="<aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/>" rel="tooltip" data-toggle="modal" data-target="#modal-user"><aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/></button>
									</div>
								</div>
								<div class="y_tablebtn">
									<a class="btn" href="${pageContext.servletContext.contextPath}/sysback/servicestaffinterview/toAdd" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/></a>
									<button class="btn" onclick="javascript:removes('');" title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></button>
								</div>
						</div>
						
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th class='with-checkbox'>
										<input type="checkbox" name="check_all" id="check_all">
									</th>
									<th><aebiz:showTitle titleId="servicestaffinterview.m.serviceStatffUuid"/></th>
									<th><aebiz:showTitle titleId="servicestaffinterview.m.competence"/></th>
									<th><aebiz:showTitle titleId="servicestaffinterview.m.experience"/></th>
									<th><aebiz:showTitle titleId="servicestaffinterview.m.comprehend"/></th>
									<th><aebiz:showTitle titleId="servicestaffinterview.m.learn"/></th>
									<th><aebiz:showTitle titleId="servicestaffinterview.m.personalImage"/></th>
									<th><aebiz:showTitle titleId="servicestaffinterview.m.remark"/></th>
									<th><aebiz:showTitle titleId="servicestaffinterview.m.interviewUuid"/></th>
	
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
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="servicestaffinterview.m.serviceStatffUuid"/></label>
								<input type="text" name="serviceStatffUuid_s" id="serviceStatffUuid_s" class="form-control">
								<input type="hidden" name="serviceStatffUuid_q" id="serviceStatffUuid_q" value="EQ">
							</div>
						</div>
						<div class="col-sm-6">
							 <div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="servicestaffinterview.m.competence"/></label>
								<input type="text" name="competence_s" id="competence_s" class="form-control">
								<input type="hidden" name="competence_q" id="competence_q" value="Like">
							</div>
						</div>
						<div class="col-sm-6">
							 <div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="servicestaffinterview.m.experience"/></label>
								<input type="text" name="experience_s" id="experience_s" class="form-control">
								<input type="hidden" name="experience_q" id="experience_q" value="Like">
							</div>
						</div>
						</div>
						<div class="row">
						<div class="col-sm-6">
							 <div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="servicestaffinterview.m.comprehend"/></label>
								<input type="text" name="comprehend_s" id="comprehend_s" class="form-control">
								<input type="hidden" name="comprehend_q" id="comprehend_q" value="Like">
							</div>
						</div>
						<div class="col-sm-6">
							 <div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="servicestaffinterview.m.learn"/></label>
								<input type="text" name="learn_s" id="learn_s" class="form-control">
								<input type="hidden" name="learn_q" id="learn_q" value="Like">
							</div>
						</div>
						<div class="col-sm-6">
							 <div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="servicestaffinterview.m.personalImage"/></label>
								<input type="text" name="personalImage_s" id="personalImage_s" class="form-control">
								<input type="hidden" name="personalImage_q" id="personalImage_q" value="Like">
							</div>
						</div>
						</div>
						<div class="row">
						<div class="col-sm-6">
						 <div class="form-group">
						<label for="textfield" class="control-label"><aebiz:showTitle titleId="servicestaffinterview.m.remark"/></label>
						<input type="text" name="remark_s" id="remark_s" class="form-control">
						<input type="hidden" name="remark_q" id="remark_q" value="Like">
						</div>
						</div>
						<div class="col-sm-6">
						 <div class="form-group">
						<label for="textfield" class="control-label"><aebiz:showTitle titleId="servicestaffinterview.m.interviewUuid"/></label>
						<input type="text" name="interviewUuid_s" id="interviewUuid_s" class="form-control">
						<input type="hidden" name="interviewUuid_q" id="interviewUuid_q" value="EQ">
						</div>
						</div>
						</div>
						<div class="row">
						<div class="col-sm-6">
						 <div class="form-group">
						<label for="textfield" class="control-label"><aebiz:showTitle titleId="servicestaffinterview.qm.id2"/></label>
						<input type="text" name="id2_s" id="id2_s" class="form-control">
						<input type="hidden" name="id2_q" id="id2_q" value="LT">
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
	
	
	
	<!--面试评估表-->
	<div id="modal-inter" class="modal fade">
		<div id="showInter"> </div>
	</div>
	<!--面试评估表-->
	
	
</body>

</html>

<script>
function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
	searchParam.push({ "name": "serviceStatffUuid_s", "value": $.trim($("#serviceStatffUuid_s").val())});
	searchParam.push({ "name": "serviceStatffUuid_q", "value": $.trim($("#serviceStatffUuid_q").val())});
	searchParam.push({ "name": "competence_s", "value": $.trim($("#competence_s").val())});
	searchParam.push({ "name": "competence_q", "value": $.trim($("#competence_q").val())});
	searchParam.push({ "name": "experience_s", "value": $.trim($("#experience_s").val())});
	searchParam.push({ "name": "experience_q", "value": $.trim($("#experience_q").val())});
	searchParam.push({ "name": "comprehend_s", "value": $.trim($("#comprehend_s").val())});
	searchParam.push({ "name": "comprehend_q", "value": $.trim($("#comprehend_q").val())});
	searchParam.push({ "name": "learn_s", "value": $.trim($("#learn_s").val())});
	searchParam.push({ "name": "learn_q", "value": $.trim($("#learn_q").val())});
	searchParam.push({ "name": "personalImage_s", "value": $.trim($("#personalImage_s").val())});
	searchParam.push({ "name": "personalImage_q", "value": $.trim($("#personalImage_q").val())});
	searchParam.push({ "name": "remark_s", "value": $.trim($("#remark_s").val())});
	searchParam.push({ "name": "remark_q", "value": $.trim($("#remark_q").val())});
	searchParam.push({ "name": "interviewUuid_s", "value": $.trim($("#interviewUuid_s").val())});
	searchParam.push({ "name": "interviewUuid_q", "value": $.trim($("#interviewUuid_q").val())});
	searchParam.push({ "name": "id2_s", "value": $.trim($("#id2_s").val())});
	searchParam.push({ "name": "id2_q", "value": $.trim($("#id2_q").val())});
	searchParam.push({ "name": "serviceStatffUuid", "value": $.trim($("#serviceStatffUuid").val())});
			
	
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
											{"mDataProp":"serviceStatffUuid" ,"sTitle": "<aebiz:showTitle titleId="servicestaffinterview.m.serviceStatffUuid"/>"},
											{"mDataProp":"competence" ,"sTitle": "<aebiz:showTitle titleId="servicestaffinterview.m.competence"/>"},
											{"mDataProp":"experience" ,"sTitle": "<aebiz:showTitle titleId="servicestaffinterview.m.experience"/>"},
											{"mDataProp":"comprehend" ,"sTitle": "<aebiz:showTitle titleId="servicestaffinterview.m.comprehend"/>"},
											{"mDataProp":"learn" ,"sTitle": "<aebiz:showTitle titleId="servicestaffinterview.m.learn"/>"},
											{"mDataProp":"personalImage" ,"sTitle": "<aebiz:showTitle titleId="servicestaffinterview.m.personalImage"/>"},
											{"mDataProp":"remark" ,"sTitle": "<aebiz:showTitle titleId="servicestaffinterview.m.remark"/>"},
											{"mDataProp":"interviewUuid" ,"sTitle": "<aebiz:showTitle titleId="servicestaffinterview.m.interviewUuid"/>"},
		

										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 	"fnRender": function(obj) {	
											  		var sReturn = "<a  onclick='toInterView(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip'  data-toggle="modal" data-target="#modal-inter" title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>" ;
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
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/servicestaffinterview/queryList" ,
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
	$("#serviceStatffUuid").val("") ;
	$("#interviewUuid").val("") ;
	
}

//清空高级搜索的所有选项
function clearMoreSearch() {
	$("#serviceStatffUuid_s").val("") ;
	$("#competence_s").val("") ;
	$("#experience_s").val("") ;
	$("#comprehend_s").val("") ;
	$("#learn_s").val("") ;
	$("#personalImage_s").val("") ;
	$("#remark_s").val("") ;
	$("#interviewUuid_s").val("") ;
	$("#id2_s").val("") ;
	
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
			    	"${pageContext.servletContext.contextPath}/sysback/servicestaffinterview/deletes",
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


	function toInterView(uuid) {
		$.get(
		"${pageContext.servletContext.contextPath}/sysback/servicestaffinterview/toUpdate",
		{
			"uuid":uuid,
			ranNum : Math.random()
		},
		function(data) {
					$("#showInter").html(data);
		});
	}
</script>