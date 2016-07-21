<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>

</head>

<body>
	<div class="container-fluid">	
		<div class="page-header">
			<div class="pull-left">
				<h1>意见反馈</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span>客服系统</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>意见反馈</span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span>意见反馈</span>
				</li>						
			</ul>				
		</div>	
				
	
		
		
		<div class="tab-pane" id="Product2">
			<div class="y_clear">
				<div class="form-inline table_formnew">
					<div  class="form-group">
						<label class="control-label">会员姓名：</label>
						<input type="text" name="customerName1" id="customerName1" class="form-control">
					</div>
					
					<div  class="form-group">
						<label class="control-label">会员电话：</label>
						<input type="text" name="customerMobile" id="customerMobile" class="form-control">
						<input type="hidden" name="customerMobile_q" id="customerMobile_q" class="form-control" value="Like">
					</div>
					
					<div class="form-group">	
						<label class="control-label">会员类型：</label>									
						<select name="type" id="type" class='form-control'>
							<option value="">--全部--</option>
							<option value="2">患者</option>
							<option value="1">医生</option>
						</select>
						<input type="hidden" name="type_q" id="type_q" class="form-control" value="EQ">

					</div>
					
					<div class="form-group">
						<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
						<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
						
					</div>
				</div>
			</div>
				
	<div class="tab-content y_tabdatable">
			<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
				<thead>		
					<tr>
						<th>会员姓名</th>
						<th>会员手机号</th>
						<th>会员类型</th>
						<th>邮箱</th>
						<th>QQ号</th>
						<th>反馈时间</th>
						<th>反馈内容</th>
					</tr>
				</thead>
				
			</table>

	</div>
</div>
	
	<!--处理意见-->
	<div id="modal-user" class="modal fade y_highserch">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h3 id="user-infos">处理回馈意见</h3>
				</div>
				<div class="modal-body">
					<div class="box">	
						<textarea name="textarea" id="refundConten" name="refundConten" class="form-control" rows="5"></textarea>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn moresearch" data-dismiss="modal">关闭</button>
					<input type="submit" onclick="javascript:submit();" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>
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
	searchParam.push({"name" : "customerName1","value" : $.trim($("#customerName1").val())});
	searchParam.push({"name" : "customerMobile","value" : $.trim($("#customerMobile").val())});
	searchParam.push({"name" : "customerMobile_q","value" : $.trim($("#customerMobile_q").val())});
	searchParam.push({"name" : "type","value" : $.trim($("#type").val())});
	searchParam.push({"name" : "type_q","value" : $.trim($("#type_q").val())});

	
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
				            
				            'sDom': "rtlip",
				            'aoColumnDefs': [{
				                'bSortable': false,
				                'aTargets': [0, 1]                
				            }],
				            "aaSorting": [[ 5, "desc" ]],
				            "bSort": true, //排序功能            
					         	"aoColumns": [
										  {"mDataProp":"customerName" ,"sWidth":"13%","sDefaultContent":"","sTitle": "会员姓名"},
										  {"mDataProp":"customerMobile" ,"sWidth":"13%","sDefaultContent":"","sTitle": "会员手机号"},
											{"mDataProp":"type" ,"sWidth":"13%","sDefaultContent":"","sTitle": "会员类型",
												"fnRender" : function(obj) {
													var sReturn = ""
													if(obj.aData.type=="1"){
														sReturn="医生"
													}else if(obj.aData.type=="2"){
														sReturn="患者"
													}
													return sReturn;
												}	
												},
											{"mDataProp":"customerEmail" ,"sWidth":"13%","sDefaultContent":"","sTitle": "邮箱"},
											{"mDataProp":"customerQQ" ,"sWidth":"13%","sDefaultContent":"","sTitle": "QQ号码"},
											{"mDataProp":"createTime" ,"sWidth":"13%","sDefaultContent":"","sTitle": "反馈时间"},
											{"mDataProp":"adviceContent" ,"sWidth":"22%","sDefaultContent":"","sTitle": "反馈内容"},
										 // {
										  //	"mDataProp":"operate",
										 // 	"sWidth":"20%",
										 // 	"sDefaultContent":"",
										 // 	"sTitle": "<aebiz:showTitle titleId="customeradvice.m.customerLookUser"/>" ,
										 //		"fnRender": function(obj) {	
										//	  		var sReturn = "<a href='${pageContext.servletContext.contextPath}/customeradvice/toUpdate/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="customeradvice.m.customerLookUser"/></a>" ;
														
										//	      return sReturn;
										//	 }				
										//  }
										],
									
				            'oTableTools': {
				                "sSwfPath": "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
				            },
				            "bRetrieve":true ,
				            "bProcessing":true,
				           	"bServerSide":true,                    //指定从服务器端获取数据
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/customeradvice/queryList" ,
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
	doSearch('search') ;  
})




function clearSearch() {
	$("#customerName1").val("") ;
	$("#customerMobile").val("") ;
	$("#type").val("") ;
}










$(document).ready(function() {
	
	 //初始化表格
   doSearch('inittable') ;
     
}) 
</script>