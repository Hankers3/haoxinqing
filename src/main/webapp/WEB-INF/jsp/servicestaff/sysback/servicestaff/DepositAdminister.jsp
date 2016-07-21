<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<h1>押金管理</h1>
		</div>
		
		<!--<div class="pull-right">
			<ul class="minitiles">
				<li class='grey'>
					<a href="#">
						<i class="fa fa-cogs"></i>
					</a>
				</li>
				<li class='lightgrey'>
					<a href="#">
						<i class="fa fa-globe"></i>
					</a>
				</li>
			</ul>
			<ul class="stats">
				<li class='satgreen'>
					<i class="fa fa-money"></i>
					<div class="details">
						<span class="big">$324,12</span>
						<span>Balance</span>
					</div>
				</li>
				<li class='lightred'>
					<i class="fa fa-calendar"></i>
					<div class="details">
						<span class="big">February 22, 2013</span>
						<span>Wednesday, 13:56</span>
					</div>
				</li>
			</ul>
		</div>-->
		
		
	</div>
	<div class="breadcrumbs">
		<ul>
			<li>
				<span>财务系统</span>
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<span>财务管理</span>
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<span>押金管理</span>
			</li>
		</ul>
	</div>
	<ul class="tabs tabs-inline tabs-top border1-top mb_20">
		<li class='active'>
			<a href="#Product1" data-toggle='tab' id="tab1">应收人员</a>
		</li>
		<li>
			<a href="#Product2" data-toggle='tab' id="tab2">已收人员</a>
		</li>
		<li>
			<a href="#Product3" data-toggle='tab' id="tab3">已退人员</a>
		</li>
	</ul>		
		<!--冻结状态-->			
	<input type="hidden" name="frozenSate" id="frozenSate"  value="4" class="form-control" >	
	<input type="hidden" name="frozenSate_q" id="frozenSate_q" value="Like">
	<input type="hidden" name="depositState" id="depositState"  value="1" class="form-control" >	
	<input type="hidden" name="depositState_q" id="depositState_q" value="Like">
	<div class="tab-content y_tabdatable">
		<div class="tab-pane active" id="Product1">
		    <table id="user" class="table table-bordered table-hover table-striped dataTable dataTable-nosort dataTable-reorder" data-nosort="0">
				 <thead>		
					<tr>
						<th><aebiz:showTitle titleId="servicestaff.m.serviceStaffNO"/></th>
						<th><aebiz:showTitle titleId="servicestaffinfo.m.realName"/></th>
						<th><aebiz:showTitle titleId="servicestaffinfo.m.sex"/></th>
						<th><aebiz:showTitle titleId="servicestaffinfo.m.certCode"/></th>
						<th><aebiz:showTitle titleId="servicestaff.m.mobile"/></th>
						<th><aebiz:showTitle titleId="servicestafflevel.m.levelName"/></th>
						<th><aebiz:showTitle titleId="servicestaff.m.frozenTyp"/></th>
						<th><aebiz:showTitle titleId="servicestaff.m.createTime"/></th>
						<th class='hidden-480'><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
					</tr>
				</thead>
		  </table>
		</div>
		<div class="tab-pane" id="Product2">
			<table id="user" class="table table-bordered table-hover table-striped dataTable1 dataTable-nosort dataTable-reorder" data-nosort="0">
				<thead>		
					<tr>
						<th><aebiz:showTitle titleId="servicestaff.m.serviceStaffNO"/></th>
						<th><aebiz:showTitle titleId="servicestaffinfo.m.realName"/></th>
						<th><aebiz:showTitle titleId="servicestaffinfo.m.sex"/></th>
						<th><aebiz:showTitle titleId="servicestaffinfo.m.certCode"/></th>
						<th><aebiz:showTitle titleId="servicestaff.m.mobile"/></th>
						<th><aebiz:showTitle titleId="servicestafflevel.m.levelName"/></th>
						<th><aebiz:showTitle titleId="servicestaff.m.frozenTyp"/></th>
						<th><aebiz:showTitle titleId="servicestaff.m.createTime"/></th>
						<th class='hidden-480'><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
					</tr>
				</thead>
			</table>
		</div>
		<div class="tab-pane" id="Product3">
			<table id="user" class="table table-bordered table-hover table-striped dataTable2 dataTable-nosort dataTable-reorder" data-nosort="0">
				<thead>		
					<tr>
						<th><aebiz:showTitle titleId="servicestaff.m.serviceStaffNO"/></th>
						<th><aebiz:showTitle titleId="servicestaffinfo.m.realName"/></th>
						<th><aebiz:showTitle titleId="servicestaffinfo.m.sex"/></th>
						<th><aebiz:showTitle titleId="servicestaffinfo.m.certCode"/></th>
						<th><aebiz:showTitle titleId="servicestaff.m.mobile"/></th>
						<th><aebiz:showTitle titleId="servicestafflevel.m.levelName"/></th>
						<th><aebiz:showTitle titleId="servicestaff.m.frozenTyp"/></th>
						<th><aebiz:showTitle titleId="servicestaff.m.createTime"/></th>
						<th class='hidden-480'><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
					</tr>
				</thead>
				<!--<tbody>
					<tr>
						<td>0232342</td>
						<td>黄超超</td>
						<td>男</td>
						<td>340288199001022233</td>
						<td>15819399909</td>
						<td>高级</td>
						<td>家政员</td>
						<td>客服小a</td>
						<td>2013-12-11</td>
						<td>
							<a href='#' class='btn' rel='tooltip' title='查看' data-toggle="modal" data-target="#modal-user3">查看</a>
						</td>
					</tr>
					<tr>
						<td>0232342</td>
						<td>黄超超</td>
						<td>男</td>
						<td>340288199001022233</td>
						<td>15819399909</td>
						<td>高级</td>
						<td>家政员</td>
						<td>客服小a</td>
						<td>2013-12-11</td>
						<td>
							<a href='#' class='btn' rel='tooltip' title='查看' data-toggle="modal" data-target="#modal-user3">查看</a>
						</td>
					</tr>
					<tr>
						<td>0232342</td>
						<td>黄超超</td>
						<td>男</td>
						<td>340288199001022233</td>
						<td>15819399909</td>
						<td>高级</td>
						<td>家政员</td>
						<td>客服小a</td>
						<td>2013-12-11</td>
						<td>
							<a href='#' class='btn' rel='tooltip' title='查看' data-toggle="modal" data-target="#modal-user3">查看</a>
						</td>
					</tr>
				</tbody>-->
			</table>
		</div>
	</div>
</div>
	
	<input type="hidden" id="servicestaffUuid">
	<!--收取押金-->
	<div id="modal-user" class="modal fade y_highserch">
		<div class="modal-dialog" id="pp">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 id="user-infos">收取押金</h4>
				</div>
				<div class="modal-body" style="padding:50px;">							
					<div class="row">
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2 pt_5">押金金额</label>
							<div class="col-sm-5">
							 	<input type="text" name="deposit" id="deposit" class="form-control">
							</div>	
							<div class="col-sm-1 pt_5">
							 	元
							</div>									
						</div>					
					</div>
				</div>
				<div class="modal-footer">
					<!--<input type="submit" onclick="javascript:submit();" class="btn btn-primary" value='确认收取'>-->
					<!--<button class="btn btn-primary moresearch" data-dismiss="modal">确认收取</button>-->
					<input type="submit" onclick="javascript:submit();" class="btn btn-primary moresearch" data-dismiss="modal" value='确认收取'>
					<button class="btn clearMoreSearch"  data-dismiss="modal">取消</button>
					<!--
					<button class="btn" data-dismiss="modal">关闭</button>
					-->
				</div>
			</div>
		</div>
	</div>
	<!--收取押金-->
	<!--退押金-->
	<div id="modal-user2" class="modal fade y_highserch">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 id="user-infos">退押金</h4>
				</div>
				<div class="modal-body" style="padding:50px;">							
					<div class="row">
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2 pt_5">押金金额</label>
							<div class="col-sm-5">
								<input type="text" name="deposit1" id="deposit1" class="form-control" >
								
							</div>	
							<div class="col-sm-1 pt_5">
							 	元
							</div>									
						</div>					
					</div>
				</div>
				<div class="modal-footer">
					<!--<button class="btn btn-primary moresearch" data-dismiss="modal">确认退押金</button>-->
					<input type="submit" onclick="javascript:submit1();" class="btn btn-primary moresearch" data-dismiss="modal" value='确认退押金'>
					<button class="btn clearMoreSearch"  data-dismiss="modal">取消</button>
					<!--
					<button class="btn" data-dismiss="modal">关闭</button>
					-->
				</div>
			</div>
		</div>
	</div>
	<!--退押金-->
	
	<!--查看退押金-->
	<div id="modal-user3" class="modal fade y_highserch">
		<div id="showdeposity"></div>
	</div>
	<!--查看退押金-->
	
</body>
</html>
<script>
	
	$(document).ready(function() {
		//alert("	alert	alert	alert	alert	alert	alert	alert	alert	alert	alert");
	 //初始化表格
		doSearch('inittable') ;
	});
 
	
	
	
	
	function retrieveData(sSource,aoData,fnCallback) { 
		//alert("进入retrieveData方法！");
	var searchParam = new Array() ;
	searchParam.push({ "name": "frozenSate", "value": $.trim($("#frozenSate").val())});
  searchParam.push({ "name": "frozenSate_q", "value": $.trim($("#frozenSate_q").val())});
	//searchParam.push({ "name": "depositState", "value": $.trim($("#depositState").val())});
	searchParam.push({ "name": "depositState", "value":"1"});
  searchParam.push({ "name": "depositState_q", "value": $.trim($("#depositState_q").val())});
	
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
	//alert("进入doSearch方法！");
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
											{"mDataProp":"uuid" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaff.m.serviceStaffNO"/>"},
											{"mDataProp":"serviceStaffinfoRealName" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaffinfo.m.realName"/>"},
											{
											  	"mDataProp":"serviceStaffinfoSex",
											  	"sDefaultContent":"",
											  	"sWidth":"8%",
											  	"sTitle": "<aebiz:showTitle titleId="servicestaffinfo.m.sex"/>" ,
											 	"fnRender": function(obj) {	
												  		var sReturn ="";
												  		if(obj.aData.serviceStaffinfoSex==1){
												  			sReturn+="<aebiz:showTitle titleId="servicestaffinfo.m.female"/>";
												  		}else if(obj.aData.serviceStaffinfoSex==2){
												  			sReturn+="<aebiz:showTitle titleId="servicestaffinfo.m.male"/>";
												  		}else if(obj.aData.serviceStaffinfoSex==3){
												  			sReturn+="<aebiz:showTitle titleId="servicestaffinfo.m.secret"/>";
												  		}else{
												  			sReturn+="";
												  		}
												      return sReturn;
												 }			
											},
											
											{"mDataProp":"serviceStaffinfoCertCode" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaffinfo.m.certCode"/>"},
											{"mDataProp":"mobile" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaff.m.mobile"/>"},
											{"mDataProp":"serviceStaffLevelName" ,"sDefaultContent":"","sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestafflevel.m.levelName"/>"},
											
											{
											  	"mDataProp":"frozenSate",
											  	"sDefaultContent":"",
											  	"sWidth":"8%",
											  	"sTitle": "<aebiz:showTitle titleId="servicestaff.m.frozenSate"/>" ,
											 		"fnRender": function(obj) {	
												  		var sReturn ="";
												  		if(obj.aData.frozenSate==1){
												  			sReturn+="<aebiz:showTitle titleId="servicestaff.m.frozened"/>";
												  		}else if(obj.aData.frozenSate==0){
												  			sReturn+="<aebiz:showTitle titleId="servicestaff.m.unfrozened"/>";
												  		}else if(obj.aData.frozenSate==2){
												  			sReturn+="<aebiz:showTitle titleId="servicestaff.m.waitChoose"/>";
												  		}else if(obj.aData.frozenSate==3){
												  			sReturn+="<aebiz:showTitle titleId="servicestaff.m.waitInterview"/>";
												  		}else if(obj.aData.frozenSate==4){
												  			sReturn+="<aebiz:showTitle titleId="servicestaff.m.trained"/>";
												  		}else{
												  			sReturn+="";
												  		}
												      return sReturn;
												 }			
											},
											
											{"mDataProp":"createTime" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaff.m.createTime"/>"},
										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 		"fnRender": 
										 		function(obj) {	
										 		
										 		//var sReturn = "<a href='${pageContext.servletContext.contextPath}/servicestaffcomb/toServicestaffView/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.view"/>'><aebiz:showTitle titleId="basebusiness.showmessage.view"/></a>" ;
											  		//sReturn +="<a href='${pageContext.servletContext.contextPath}/servicestaffcomb/toUpdateServicestaff/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>" ;
														//sReturn +="<a href='${pageContext.servletContext.contextPath}/servicestaff/frozen/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="servicestaff.m.frozened1"/>'><aebiz:showTitle titleId="servicestaff.m.frozened1"/></a>" ;
														//sReturn += "<a href='javascript:removes(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>'><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></a>" ;															
											     // sReturn += "<button class='btn' id='searchshow' onClick='javascript:getNowServiceStaff(\""+obj.aData.uuid+"\")' title='<aebiz:showTitle titleId="servicestaff.m.pwdreset"/>' rel='tooltip' data-toggle='modal' data-target='#modal-user1'><aebiz:showTitle titleId="servicestaff.m.pwdreset"/></button>" ;
											    var sReturn = "<a onclick='javascript:setUuid(\""+obj.aData.uuid+"\")' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="servicestaff.m.getdeposit"/>'  data-toggle='modal' data-target='#modal-user'  ><aebiz:showTitle titleId="servicestaff.m.getdeposit"/></a>" ;
											      return sReturn;
											 }			
										  },
										  
										],
									
				            'oTableTools': {
				                "sSwfPath": "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
				            },
				            "bRetrieve":true ,
				            "bProcessing":true,
				           	"bServerSide":true,                    //指定从服务器端获取数据
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/servicestaff/queryList" ,
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


	function retrieveData1(sSource,aoData,fnCallback) { 
			//alert("进入retrieveData1方法！");
	var searchParam = new Array() ;
	searchParam.push({ "name": "frozenSate", "value": $.trim($("#frozenSate").val())});
  searchParam.push({ "name": "frozenSate_q", "value": $.trim($("#frozenSate_q").val())});
	searchParam.push({ "name": "depositState", "value":"2"});
  searchParam.push({ "name": "depositState_q", "value": $.trim($("#depositState_q").val())});
	
   $.ajax({    	        
  	"dataType": 'json', 
	  "type": "POST", 
	  "url": sSource, 
	  "data": {aoData:JSON.stringify(aoData),searchParam:JSON.stringify(searchParam)}, 
	  "success": fnCallback
  });    
} 
    
var oTable1 = null; 
function doSearch1(operact) {	
	//alert("进入doSearch1方法！");
	if(oTable1 == null) {		 
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
											{"mDataProp":"uuid" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaff.m.serviceStaffNO"/>"},
											{"mDataProp":"serviceStaffinfoRealName" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaffinfo.m.realName"/>"},
											{
											  	"mDataProp":"serviceStaffinfoSex",
											  	"sDefaultContent":"",
											  	"sWidth":"8%",
											  	"sTitle": "<aebiz:showTitle titleId="servicestaffinfo.m.sex"/>" ,
											 	"fnRender": function(obj) {	
												  		var sReturn ="";
												  		if(obj.aData.serviceStaffinfoSex==1){
												  			sReturn+="<aebiz:showTitle titleId="servicestaffinfo.m.female"/>";
												  		}else if(obj.aData.serviceStaffinfoSex==2){
												  			sReturn+="<aebiz:showTitle titleId="servicestaffinfo.m.male"/>";
												  		}else if(obj.aData.serviceStaffinfoSex==3){
												  			sReturn+="<aebiz:showTitle titleId="servicestaffinfo.m.secret"/>";
												  		}else{
												  			sReturn+="";
												  		}
												      return sReturn;
												 }			
											},
											
											{"mDataProp":"serviceStaffinfoCertCode" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaffinfo.m.certCode"/>"},
											{"mDataProp":"mobile" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaff.m.mobile"/>"},
											{"mDataProp":"serviceStaffLevelName" ,"sDefaultContent":"","sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestafflevel.m.levelName"/>"},
											
											{
											  	"mDataProp":"frozenSate",
											  	"sDefaultContent":"",
											  	"sWidth":"8%",
											  	"sTitle": "<aebiz:showTitle titleId="servicestaff.m.frozenSate"/>" ,
											 		"fnRender": function(obj) {	
												  		var sReturn ="";
												  		if(obj.aData.frozenSate==1){
												  			sReturn+="<aebiz:showTitle titleId="servicestaff.m.frozened"/>";
												  		}else if(obj.aData.frozenSate==0){
												  			sReturn+="<aebiz:showTitle titleId="servicestaff.m.unfrozened"/>";
												  		}else if(obj.aData.frozenSate==2){
												  			sReturn+="<aebiz:showTitle titleId="servicestaff.m.waitChoose"/>";
												  		}else if(obj.aData.frozenSate==3){
												  			sReturn+="<aebiz:showTitle titleId="servicestaff.m.waitInterview"/>";
												  		}else if(obj.aData.frozenSate==4){
												  			sReturn+="<aebiz:showTitle titleId="servicestaff.m.trained"/>";
												  		}else{
												  			sReturn+="";
												  		}
												      return sReturn;
												 }			
											},
											
											{"mDataProp":"createTime" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaff.m.createTime"/>"},
										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 		"fnRender": function(obj) {	
										 		
										 		  // var sReturn = "<a href='${pageContext.servletContext.contextPath}/servicestaffcomb/toServicestaffView/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.view"/>'><aebiz:showTitle titleId="basebusiness.showmessage.view"/></a>" ;
											  	//	sReturn +="<a href='${pageContext.servletContext.contextPath}/servicestaffcomb/toUpdateServicestaff/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>" ;
													//	sReturn +="<a href='${pageContext.servletContext.contextPath}/servicestaff/frozen/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="servicestaff.m.frozened1"/>'><aebiz:showTitle titleId="servicestaff.m.frozened1"/></a>" ;
													//	sReturn += "<a href='javascript:removes(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>'><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></a>" ;															
											     // sReturn += "<button class='btn' id='searchshow' onClick='javascript:getNowServiceStaff(\""+obj.aData.uuid+"\")' title='<aebiz:showTitle titleId="servicestaff.m.pwdreset"/>' rel='tooltip' data-toggle='modal' data-target='#modal-user1'><aebiz:showTitle titleId="servicestaff.m.pwdreset"/></button>" ;
											     var sReturn = "<a onclick='javascript:setUuid(\""+obj.aData.uuid+"\")' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="servicestaff.m.outdeposit"/>'  data-toggle='modal' data-target='#modal-user2'  ><aebiz:showTitle titleId="servicestaff.m.outdeposit"/></a>" ;
											      return sReturn;
											 }			
										  },
										  
										],
									
				            'oTableTools': {
				                "sSwfPath": "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
				            },
				            "bRetrieve":true ,
				            "bProcessing":true,
				           	"bServerSide":true,                    //指定从服务器端获取数据
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/servicestaff/queryList" ,
										"fnServerData":retrieveData1,           //获取数据的处理函数  
				        };
				        
                		if ($(this).hasClass("dataTable-reorder")) {
		                    opt.sDom = "R" + opt.sDom;
		                }
		               
		                oTable1 = $(this).dataTable(opt);
                
        	});
   		}         
	}
		
	//刷新Datatable，会自动激发retrieveData  		
  	if('inittable' != operact) {  			
		oTable1.fnDraw(); 	
	}  
	 	  
}


	function retrieveData2(sSource,aoData,fnCallback) { 
		//alert("进入retrieveData2方法！");
	var searchParam = new Array() ;
	searchParam.push({ "name": "frozenSate", "value": $.trim($("#frozenSate").val())});
  searchParam.push({ "name": "frozenSate_q", "value": $.trim($("#frozenSate_q").val())});
	searchParam.push({ "name": "depositState", "value":"3"});
  searchParam.push({ "name": "depositState_q", "value": $.trim($("#depositState_q").val())});
	
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
	//alert("进入doSearch2方法！");
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
				            
				            //'sDom': "lfrtip",
				            'sDom': "rtlip",
				            //"sDom": '<"top"l>rt<"bottom"ip><"clear">',            
				            'aoColumnDefs': [{
				                'bSortable': false,
				                'aTargets': [0, 1]                
				            }],
				            "bSort": true, //排序功能            
					         	"aoColumns": [
											{"mDataProp":"uuid" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaff.m.serviceStaffNO"/>"},
											{"mDataProp":"serviceStaffinfoRealName" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaffinfo.m.realName"/>"},
											{
											  	"mDataProp":"serviceStaffinfoSex",
											  	"sDefaultContent":"",
											  	"sWidth":"8%",
											  	"sTitle": "<aebiz:showTitle titleId="servicestaffinfo.m.sex"/>" ,
											 	"fnRender": function(obj) {	
												  		var sReturn ="";
												  		if(obj.aData.serviceStaffinfoSex==1){
												  			sReturn+="<aebiz:showTitle titleId="servicestaffinfo.m.female"/>";
												  		}else if(obj.aData.serviceStaffinfoSex==2){
												  			sReturn+="<aebiz:showTitle titleId="servicestaffinfo.m.male"/>";
												  		}else if(obj.aData.serviceStaffinfoSex==3){
												  			sReturn+="<aebiz:showTitle titleId="servicestaffinfo.m.secret"/>";
												  		}else{
												  			sReturn+="";
												  		}
												      return sReturn;
												 }			
											},
											
											{"mDataProp":"serviceStaffinfoCertCode" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaffinfo.m.certCode"/>"},
											{"mDataProp":"mobile" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaff.m.mobile"/>"},
											{"mDataProp":"serviceStaffLevelName" ,"sDefaultContent":"","sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestafflevel.m.levelName"/>"},
											
											{
											  	"mDataProp":"frozenSate",
											  	"sDefaultContent":"",
											  	"sWidth":"8%",
											  	"sTitle": "<aebiz:showTitle titleId="servicestaff.m.frozenSate"/>" ,
											 		"fnRender": function(obj) {	
												  		var sReturn ="";
												  		if(obj.aData.frozenSate==1){
												  			sReturn+="<aebiz:showTitle titleId="servicestaff.m.frozened"/>";
												  		}else if(obj.aData.frozenSate==0){
												  			sReturn+="<aebiz:showTitle titleId="servicestaff.m.unfrozened"/>";
												  		}else if(obj.aData.frozenSate==2){
												  			sReturn+="<aebiz:showTitle titleId="servicestaff.m.waitChoose"/>";
												  		}else if(obj.aData.frozenSate==3){
												  			sReturn+="<aebiz:showTitle titleId="servicestaff.m.waitInterview"/>";
												  		}else if(obj.aData.frozenSate==4){
												  			sReturn+="<aebiz:showTitle titleId="servicestaff.m.trained"/>";
												  		}else{
												  			sReturn+="";
												  		}
												      return sReturn;
												 }			
											},
											
											{"mDataProp":"createTime" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaff.m.createTime"/>"},
										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 		"fnRender": function(obj) {	
										 		
										 		   //var sReturn = "<a href='${pageContext.servletContext.contextPath}/servicestaffcomb/toServicestaffView/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.view"/>'><aebiz:showTitle titleId="basebusiness.showmessage.view"/></a>" ;
											  		//sReturn +="<a href='${pageContext.servletContext.contextPath}/servicestaffcomb/toUpdateServicestaff/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>" ;
														//sReturn +="<a href='${pageContext.servletContext.contextPath}/servicestaff/frozen/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="servicestaff.m.frozened1"/>'><aebiz:showTitle titleId="servicestaff.m.frozened1"/></a>" ;
														//sReturn += "<a href='javascript:removes(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>'><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></a>" ;															
											     // sReturn += "<button class='btn' id='searchshow' onClick='javascript:getNowServiceStaff(\""+obj.aData.uuid+"\")' title='<aebiz:showTitle titleId="servicestaff.m.pwdreset"/>' rel='tooltip' data-toggle='modal' data-target='#modal-user1'><aebiz:showTitle titleId="servicestaff.m.pwdreset"/></button>" ;
											     var sReturn = "<a onclick='javascript:toView(\""+obj.aData.uuid+"\")' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.view"/>'  data-toggle='modal' data-target='#modal-user3'  ><aebiz:showTitle titleId="basebusiness.showmessage.view"/></a>" ;
											      return sReturn;
											 }			
										  },
										  
										],
									
				            'oTableTools': {
				                "sSwfPath": "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
				            },
				            "bRetrieve":true ,
				            "bProcessing":true,
				           	"bServerSide":true,                    //指定从服务器端获取数据
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/servicestaff/queryList" ,
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
$("#tab1").click(function(){
	$("#depositState").val("1");
	doSearch('search') ;
});

$("#tab2").click(function(){
	//alert("进入tab2方法！");
	$("#depositState").val("2");
	doSearch1('search') ;
});

$("#tab3").click(function(){
	//alert("进入tab3方法！");
	$("#depositState").val("3");
	doSearch2('search') ;
});

function setUuid(uuid){
	//alert(uuid);
	$("#servicestaffUuid").val(uuid);
}


function submit(){
	var servicestaffUuid = $("#servicestaffUuid").val();
	var deposit = $("#deposit").val();
	//alert("submit"+servicestaffUuid);
	//alert("submit"+deposit);
	//处理押金事宜
//	alert(deposit);
	if(deposit!=null &&deposit!="" ){
	$.get("${pageContext.servletContext.contextPath}/sysback/servicestaff/unpdateDepositState",
	{
		"servicestaffUuid":servicestaffUuid,
		"deposit":deposit,
		"depositState":"2",
		ranNum : Math.random()},
		function(data) {
		if (data == "true") {
			
			$("#deposit").val("");
			//刷新
			doSearch('search');
			
		} else {
			bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
		}
	});

	}else{
	alert("请输入押金金额！");
	
	}
	
}


function submit1(){
	var servicestaffUuid = $("#servicestaffUuid").val();
	var deposit1 = $("#deposit1").val();
if(deposit1!=null &&deposit1!="" ){
	//处理押金事宜
	$.get("${pageContext.servletContext.contextPath}/sysback/servicestaff/unpdateDepositState",
	{
		"servicestaffUuid":servicestaffUuid,
		"deposit":deposit1,
		"depositState":"3",
		ranNum : Math.random()},
		function(data) {
		if (data == "true") {
			//刷新
			doSearch1('search');
			
			$("#deposit1").val("");
		} else {
			bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
		}
	});
	}else{
	alert("请输入押金金额！");
	
	}
}



function toView(uuid){	
	alert(uuid);
	var url="${pageContext.servletContext.contextPath}/sysback/servicestaff/getServicestaffModelByUuid/"+uuid;
	$.post(url,
	{
		ranNum : Math.random()
	},
	function(data){
		$("#showdeposity").html(data);
	}
	);
	
}
</script>