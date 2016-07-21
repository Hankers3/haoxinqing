<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<!-- 日期插件 -->
<script src="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/datepicker/datepicker.css"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datepicker/bootstrap-datepicker.js"></script>
</head>

<body>
	<div class="container-fluid">	
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="withdrawapply.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="withdrawapply.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="withdrawapply.apply.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span><aebiz:showTitle titleId="withdrawapply.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
				</li>						
			</ul>				
		</div>				
							
		<div class="row">
				<div class="col-sm-12">
					<div class="box">
						<div class="box-content nopadding y_tableser">
							<div class="y_clear">
								
								<!--
								<ul class="tabs tabs-inline tabs-top">
									<li class='active'>
										<a href="#profile" data-toggle='tab'>
											<i class="fa fa-user"></i><aebiz:showTitle titleId="withdrawapply.qm.untreated"/></a>
									</li>
									<li>
										<a href="${pageContext.servletContext.contextPath}/sysback/withdrawapply/WithdrawApplySuccess">
											<i class="fa fa-lock"></i><aebiz:showTitle titleId="withdrawapply.qm.withdrawSuccess"/></a>
									</li>
									<li>
										<a href="${pageContext.servletContext.contextPath}/sysback/withdrawapply/WithdrawApplyFail">
											<i class="fa fa-lock"></i><aebiz:showTitle titleId="withdrawapply.qm.withdrawFail"/></a>
									</li>
								</ul>
								-->
								<div class="y_tablebtn">
									<!--<a class="btn" href="" title="<aebiz:showTitle titleId="withdrawapply.qm.eaport"/>" rel="tooltip"><i class="fa"><aebiz:showTitle titleId="withdrawapply.qm.eaport"/></i></a>-->
								</div>
								
								<input type="hidden" name="state" id="state" value="0">
								<input type="hidden" name="state_q" id="state_q" class="form-control" value="Like">
								
								<div class="form-inline table_formnew">
									<div  class="form-group">
										<label class="control-label">医生姓名：</label>
										<input type="text" name="doctorNameq" id="doctorNameq" class="form-control">	
																		
										<label class="control-label">手机号码：</label>
										<input type="text" name="doctorMobileq" id="doctorMobileq" class="form-control">
										


									</div>
									
									<div class="form-group">
										<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
										<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
									</div>
								</div>
							</div>
						
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder" id="tablePending">
							<thead>		
								<tr>
									<th>医生姓名</th>
									<th>手机号码</th>
									<th>医院名称</th>
									<th>当前余额</th>
									<th>提现金额</th>
									<th>申请时间</th>
									<th>申请状态</th>
									<th>操作</th>
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
	
	

	
<!-- 驳回原因 -->
	<div id="modal-bohui" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h3 id="user-infos"><aebiz:showTitle titleId="withdrawapply.qm.mesg"/></h3>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-12">
							<div class="box">	
								<div class="box-content">									
										<div class="row">
											<div class="col-sm-12">
												<div class="form-group">
													<label for="textfield" class="control-label"><aebiz:showTitle titleId="withdrawapply.qm.mesg"/></label>
													<textarea name="textarea" id="textarea" class="form-control"></textarea>
												</div>											
											</div>
										</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn moresearch" data-dismiss="modal" id="butText"><aebiz:showTitle titleId="basebusiness.showmessage.save"/></button>
					<button class="btn clearMoreSearch" id="clearText"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
					<button class="btn" data-dismiss="modal"><aebiz:showTitle titleId="withdrawapply.qm.close"/></button>
				</div>
			</div>
		</div>
	</div>
	
</body>

</html>

<script>
function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;

	searchParam.push({ "name": "doctorNameq", "value": $.trim($("#doctorNameq").val())});
	searchParam.push({ "name": "doctorMobileq", "value": $.trim($("#doctorMobileq").val())});

	
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
                'aTargets': [0,1,2,6,7]                
            }],
            "bSort": true, //排序功能            
	         	"aoColumns": [
	         		
						  {"mDataProp":"doctorName" ,"sDefaultContent":"","sTitle": "医生姓名"},
						  {"mDataProp":"doctorMobile" ,"sDefaultContent":"","sTitle": "手机号码"},
						  {"mDataProp":"doctorHospital" ,"sDefaultContent":"","sTitle": "所在医院"},
						  {"mDataProp":"accountAmount" ,"sDefaultContent":"","sTitle": "当前余额"},
						  {"mDataProp":"applyMoney" ,"sDefaultContent":"","sTitle": "提现金额"},
						  {"mDataProp":"applyTime" ,"sDefaultContent":"","sTitle": "申请时间"},
						  {
						  	"mDataProp":"state" ,
						  	"sDefaultContent":"",
						  	"sTitle": "申请状态",
						  	"fnRender": function(obj) {
						  		var sReturn ="";
						  		if(obj.aData.state == "0"){
						  			sReturn+="未处理"; 
						  		}else if(obj.aData.state == "1"){
										sReturn+="已转账";
									}else{
									 	sReturn+="驳回";
									}
									return sReturn;
						  	}
						  	
						  },						
						  {
						  	"mDataProp":"operate",
						  	"sDefaultContent":"",
						  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
						 		"fnRender": function(obj) {	
							  		var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/withdrawapply/toApplyView/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='查看详细'>查看详细</a>";
										//sReturn += "<a href='javascript:transfer(\""+ obj.aData.uuid + "\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="withdrawapply.qm.transfer"/>'><aebiz:showTitle titleId="withdrawapply.qm.transfer"/></a>";															
										//sReturn +="<a href='${pageContext.servletContext.contextPath}/sysback/withdrawapply/toApplyView/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="withdrawapply.qm.display"/>'><aebiz:showTitle titleId="withdrawapply.qm.display"/></a>";
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
            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/withdrawapply/queryList" ,
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
	$("#doctorNameq").val("") ;
	$("#doctorMobileq").val("") ;
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

//驳回
function aReject(uuid){
	$("#textarea").val("");
	$("#modal-bohui").modal("show");
	
	$("#butText").click(function(){
		var value = $("#textarea").val();
		var reUrl = "${pageContext.servletContext.contextPath}/sysback/withdrawapply/reject"
		$.post(
			reUrl,
			{
				"uuid" : uuid,
				"failReason":value,
				ranNum : Math.random()
			},
			function(data) {
				if(data == "success"){
					$("#state").attr("value","0");
					clearMoreSearch() ;
					clearSearch();
					//初始化表格
					doSearch('search') ; 
				}else{
					alert("<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>");	
				}
		});
	});
	
	$("#clearText").click(function(){
			$("#textarea").val("");
	});
}
//转账
function transfer(uuid){
	var reUrl = "${pageContext.servletContext.contextPath}/sysback/withdrawapply/transfer"
	$.post(
		reUrl,
		{
			"uuid" : uuid,
			ranNum : Math.random()
		},
		function(data) {
			if(data == "success"){
				alert("<aebiz:showTitle titleId="basebusiness.showmessage.opeOkTitle"/>");
				$("#state").attr("value","0");
				clearMoreSearch() ;
				clearSearch();
				//初始化表格
				doSearch('search') ; 
			}else{
				alert("<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>");	
			}
	});
}

</script>