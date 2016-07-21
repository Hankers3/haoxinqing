<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script>  
</head>

<body>
	
	<div class="container-fluid">	
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="ordermaincomment.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs mb_20">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="ordermaincomment.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="orderrevisit.menuTwo-1"/></span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span><aebiz:showTitle titleId="ordermaincomment.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
				</li>						
			</ul>				
		</div>				
		<div class="tab-content y_tabdatable">	
 	    <table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
				<thead>		
					<tr>
						<th class='with-checkbox'>
							<input type="checkbox" name="check_all" id="check_all">
						</th>
						<th><aebiz:showTitle titleId="ordermaincomment.m.orderMainUuid"/></th>
						<th><aebiz:showTitle titleId="ordermaincomment.m.customerUuid"/></th>
						<th><aebiz:showTitle titleId="ordermaincomment.m.Mobile"/></th>
						<th><aebiz:showTitle titleId="ordermaincomment.m.commentTime"/></th>
						<th><aebiz:showTitle titleId="ordermaincomment.m.content"/></th>
						<th><aebiz:showTitle titleId="ordermaincomment.m.serviceScore"/></th>
						<!--<th><aebiz:showTitle titleId="ordermaincomment.m.conductIdea"/></th>-->
					</tr>
				</thead>
				
				<tbody>
				</tbody>
			</table>	
		</div>
	</div>
	
	      <input type="hidden" id="customerUuid1">
				<!--处理意见-->
				<div id="modal-user" class="modal fade y_highserch">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
								<h3 id="user-infos"><th><aebiz:showTitle titleId="ordermaincomment.m.conductIdea1"/></th></h3>
							</div>
							<div class="modal-body">
								<div class="box">	
									<textarea name="textarea" id="refundConten" name="conductIdea" class="form-control" rows="5"></textarea>
								</div>
							</div>
							<div class="modal-footer">
								<button class="btn moresearch" data-dismiss="modal"><aebiz:showTitle titleId="ordermaincomment.m.close"/></button>
								<input type="submit" onclick="javascript:submit();" class="btn btn-primary" data-dismiss="modal" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>
							</div>
						</div>
					</div>
				</div>
				<!--处理意见-->
	
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
											{"mDataProp":"orderId" ,"sWidth":"20%" ,"sDefaultContent":"", "sTitle": "<aebiz:showTitle titleId="ordermaincomment.m.orderMainUuid"/>"},
											{"mDataProp":"customerName" ,"sWidth":"15%" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="ordermaincomment.m.customerUuid"/>"},
											{"mDataProp":"customerMobile" ,"sWidth":"15%" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="ordermaincomment.m.Mobile"/>"},
											{"mDataProp":"commentTime" ,"sDefaultContent":"","sWidth":"18%" ,"sTitle": "<aebiz:showTitle titleId="ordermaincomment.m.commentTime"/>"},
											{"mDataProp":"content" ,"sWidth":"25%" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="ordermaincomment.m.content"/>"},
											{"mDataProp":"serviceScore" ,"sWidth":"5%" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="ordermaincomment.m.serviceScore"/>"},
											
										],
										
				            'oTableTools': {
				                "sSwfPath": "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
				            },
				            "bRetrieve":true ,
				            "bProcessing":true,
				           	"bServerSide":true,                    //指定从服务器端获取数据
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/ordermaincomment/queryList" ,
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
	$("#orderMainUuid").val("") ;
	$("#conductState").val("") ;
	
}

//清空高级搜索的所有选项
function clearMoreSearch() {
	$("#orderMainUuid_s").val("") ;
	$("#customerUuid_s").val("") ;
	$("#commentTime_s").val("") ;
	$("#content_s").val("") ;
	$("#serviceScore_s").val("") ;
	$("#conductIdea_s").val("") ;
	$("#conductState_s").val("") ;
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
			    	"${pageContext.servletContext.contextPath}/sysback/ordermaincomment/deletes",
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

var uuid="";
function setUuid(uuid){
	
	$("#customerUuid1").val(uuid);
}

function submit(){
	var customerUuid = $("#customerUuid1").val();
	var refundConten = $("#refundConten").val();
	

	//保存回馈意见的回复(保存处理意见)
	$.get("${pageContext.servletContext.contextPath}/sysback/ordermaincomment/updateconductIdea",
	{
		"uuid":customerUuid,
		"conductIdea":refundConten,
		ranNum : Math.random()},function(data) {
			
		if (data== "true") {
			//刷新
      // doSearch('inittable') ;
       //this.location.reload();
       doSearch('search');
       
		} else {
			bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
		}
	});
}
</script>