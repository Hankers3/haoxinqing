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
				<h1><aebiz:showTitle titleId="customer.m.black"/></h1>
			</div>
		</div>
		
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="customer.menuOne"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="customer.menuTwo"/></span><i class="fa fa-angle-right"></i></li>							
				<li><span><aebiz:showTitle titleId="customer.m.black"/></span></li>						
			</ul>				
		</div>				
							
		<div class="row">
			<div class="col-sm-12">
				<div class="box">
					<div class="box-content nopadding y_tableser">
						<div class="y_clear">
							<div class="form-inline table_formnew">
							
								<input type="hidden" name="frozenState" id="frozenState"  value="1" class="form-control">
									
								<div class="form-group">
									<label class="control-label"><aebiz:showTitle titleId="customer.qmf.customerName"/>：</label>
									<input type="text" name="customerName" id="customerName" class="form-control">
									<input type="hidden" name="customerName_q" id="customerName_q" class="form-control" value="Like">
								</div>
								
							 	<div class="form-group">
							 		<label class="control-label"><aebiz:showTitle titleId="customer.m.mobile"/>：</label>
									<input type="text" name="mobile" id="mobile" class="form-control">
									<input type="hidden" name="mobile_q" id="mobile_q" value="Like">
								</div>
						
					

								<div class="form-group">
									<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
									<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
									<button class="btn" id="searchshow" title="<aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/>" rel="tooltip" data-toggle="modal" data-target="#modal-user"><aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/></button>
								</div>
							</div>
							
						</div>
					
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th class='with-checkbox'><input type="checkbox" name="check_all" id="check_all"></th>
									<th><aebiz:showTitle titleId="customer.m.customerName"/></th>
									<th><aebiz:showTitle titleId="customer.m.mobile"/></th>
									<th><aebiz:showTitle titleId="customer.m.frozenState"/></th>
									<th><aebiz:showTitle titleId="customer.m.activeState"/></th>
									<th><aebiz:showTitle titleId="customer.m.createTime"/></th>
									<th><aebiz:showTitle titleId="customerfreeze.m.reason"/></th>
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
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="customer.m.customerName"/></label>
								<input type="text" name="customerName_s" id="customerName_s" class="form-control">
								<input type="hidden" name="customerName_q" id="customerName_q" value="Like">
							</div>
						</div>
						
						<div class="col-sm-6">
						 	<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="customer.m.mobile"/></label>
								<input type="text" name="mobile_s" id="mobile_s" class="form-control">
								<input type="hidden" name="mobile_q" id="mobile_q" value="EQ">
							</div>
						</div>
					</div>
						
					<div class="row">
						<div class="col-sm-6">
							 <div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="customer.m.email"/></label>
								<input type="text" name="email_s" id="email_s" class="form-control">
								<input type="hidden" name="email_q" id="email_q" value="EQ">
							</div>
						</div>
					
					</div>
						
					<div class="row">
						<div class="col-sm-6">
							 <div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="customer.m.frozenState"/></label>
								<select name="frozenState_s" id="frozenState_s" class='form-control'>
									<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</option>
									<option value="1"><aebiz:showTitle titleId="customer.m.frozened"/></option>
									<option value="0"><aebiz:showTitle titleId="customer.m.unfrozened"/></option>
								</select>
								<input type="hidden" name="frozenState_q" id="frozenState_q" value="EQ">
							</div>
						</div>
						
						<div class="col-sm-6">
							 <div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="customer.m.frozenType"/></label>
								<select name="frozenType_s" id="frozenType_s" class='form-control'>								
									<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</option>
									<c:forEach items="${frozenTypeList}" var="dpp">	
										<option value="${dpp.name}" <c:if test="${m.frozenType==dpp.name}"> selected </c:if> >${dpp.value}</option>
									</c:forEach>											
								</select>
								<input type="hidden" name="frozenType_q" id="frozenType_q" value="EQ">
							</div>
						</div>
					</div>
					
					
						
					<div class="row">
						<div class="col-sm-6">
							 <div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="customer.m.createTime1"/></label>
								<input type="text" name="createTime_s" id="createTime_s" class="form-control datepick" >
								<input type="hidden" name="createTime_q" id="createTime_q" value="GE">
							</div>
						</div>
						
						<div class="col-sm-6">
						 	<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="customer.m.createTime2"/></label>
								<input type="text" name="createTime2_s" id="createTime2_s" class="form-control datepick" >
								<input type="hidden" name="createTime2_q" id="createTime2_q" value="LT">
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
	
	<!-- 账户冻结  -->
		<div id="modal-frozen" class="modal fade y_highserch">
			<form id="frozenForm" action="" method="post" class='form-validate1'>
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h4 id="user-infos"><aebiz:showTitle titleId="customer.m.frozen"/></h4>
						</div>
						
						<div class="modal-body">
							<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="customer.m.frozenType" /></label>
								<select id="frozenType" name="frozenType" class='form-control' data-rule-required="true">								
									<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</option>
									<c:forEach items="${frozenTypeList}" var="dpp">	
										<option value="${dpp.name}" <c:if test="${m.frozenType==dpp.name}"> selected </c:if> >${dpp.value}</option>
									</c:forEach>											
								</select>
							</div>
							<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerfrozenlog.m.note" /></label>
								<textarea id="frozenNote" name="frozenNote" rows="3" cols="" class="form-control" data-rule-required="true"></textarea>
							</div>
							
							<div class="modal-footer" style="text-align: right;">
								<input type="submit" class="btn frozen btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.confirm"/>'>
								<button class="btn" data-dismiss="modal">
									<aebiz:showTitle titleId="basebusiness.showmessage.cancel" />
								</button>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>	
	<!-- 账户冻结 -->
	
	<input type="hidden" id="customerUuid">
	
	<!-- 账户解冻  -->
		<div id="modal-unfrozen" class="modal fade y_highserch">
			<form id="unFrozenForm" action="" method="get" class='form-validate2'>
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h4 id="user-infos"><aebiz:showTitle titleId="customer.m.unfrozen"/></h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerfrozenlog.m.note" /></label>
								<textarea id="unFrozenNote" name="unFrozenNote" rows="3" cols="" class="form-control" data-rule-required="true"></textarea>
							</div>
							
							<div class="modal-footer" style="text-align: right;">
								<input type="submit" class="btn unFrozen btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.confirm"/>'>
								<button class="btn" data-dismiss="modal">
									<aebiz:showTitle titleId="basebusiness.showmessage.cancel" />
								</button>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>	
	<!-- 账户解冻 -->
	
		<!--重置密码  -->
		<div id="modal-resetPwd" class="modal fade y_highserch">
			<form id="resetPwdForm" action="" method="get" class='form-validate3'>
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h4 id="user-infos">
								<aebiz:showTitle titleId="customer.m.resetpassword"/>
							</h4>
						</div>
						
						<div class="modal-body">
							<div class="form-group y_clear">
								<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="customer.m.mobile"/></label>
								<div class="col-sm-10">
									<div class="y_validatainput y_clear mb_10">
										<div class="col-xs-4 check-line nopadding">
											<input type="radio" id="oldmobile" name="mobile" class='icheck-me' data-skin="square" data-color="blue" value="0" checked/>
											<label class='inline' for="oldmobile" id="mobileLabel"><aebiz:showTitle titleId="customer.m.regmobile"/></label>
										</div>
										<div class="col-xs-4 check-line nopadding">
											<input type="radio" id="newmobile" name="mobile" class='icheck-me' data-skin="square" data-color="blue" value="1" />
											<label class='inline' for="newmobile"><aebiz:showTitle titleId="customer.m.newmobile"/></label> 
										</div>
									</div>	
															
									<input type="hidden" id="customerMobile">
									<input type="text"  id="confirmMobile" name="confirmMobile" class='form-control' disabled data-rule-required="true" data-rule-mobilezh="true"/>
								</div>
							</div>
						</div>
						
						<div class="modal-footer">
							<input type="submit" class="btn resetPwd btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.confirm"/>'>
							<button class="btn" data-dismiss="modal" aria-hidden="true">
								<aebiz:showTitle titleId="basebusiness.showmessage.cancel" />
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	<!--重置密码  -->
	
<script>
function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
	searchParam.push({ "name": "frozenState", "value": $.trim($("#frozenState").val())});
	searchParam.push({ "name": "customerName", "value": $.trim($("#customerName").val())});
	searchParam.push({ "name": "customerName_s", "value": $.trim($("#customerName_s").val())});
	searchParam.push({ "name": "customerName_q", "value": $.trim($("#customerName_q").val())});
	searchParam.push({ "name": "mobile", "value": $.trim($("#mobile").val())});
	searchParam.push({ "name": "mobile_s", "value": $.trim($("#mobile_s").val())});
	searchParam.push({ "name": "mobile_q", "value": $.trim($("#mobile_q").val())});
	searchParam.push({ "name": "frozenState_s", "value": $.trim($("#frozenState_s").val())});
	searchParam.push({ "name": "frozenState_q", "value": $.trim($("#frozenState_q").val())});
	searchParam.push({ "name": "frozenType_s", "value": $.trim($("#frozenType_s").val())});
	searchParam.push({ "name": "frozenType_q", "value": $.trim($("#frozenType_q").val())});

	searchParam.push({ "name": "createTime_s", "value": $.trim($("#createTime_s").val())});
	searchParam.push({ "name": "createTime_q", "value": $.trim($("#createTime_q").val())});
	searchParam.push({ "name": "createTime2_s", "value": $.trim($("#createTime2_s").val())});
	searchParam.push({ "name": "createTime2_q", "value": $.trim($("#createTime2_q").val())});
	//searchParam.push({ "name": "customerShopLevelUuid_s", "value": $.trim($("#customerShopLevelUuid_s").val())});
	//searchParam.push({ "name": "customerShopLevelUuid_q", "value": $.trim($("#customerShopLevelUuid_q").val())});
				
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
				                'aTargets': [0,5]                
				            }],
				            "bSort": true, //排序功能            
					         	"aoColumns": [
										    {
										  	 "mDataProp":"checkbox" ,
										  	 "sDefaultContent":"",
										    "sWidth":"5%",
										  	 "fnRender": function(obj) {
												      var sReturn = "<input type='checkbox' name='check' value='"+obj.aData.uuid+"' />";						      
												      return sReturn;
										 	          }
										    },
											{"mDataProp":"customerName" ,"sWidth":"15%","sTitle": "<aebiz:showTitle titleId="customer.m.customerName"/>"},
											{"mDataProp":"mobile" ,"sWidth":"15%","sTitle": "<aebiz:showTitle titleId="customer.m.mobile"/>"},
											{
											  	"mDataProp":"frozenState1",
											  	"sDefaultContent":"",
											  	"sWidth":"8%",
											  	"sTitle": "<aebiz:showTitle titleId="customer.m.frozenState"/>" ,
											 	"fnRender": function(obj) {	
												  		var sReturn ="";
												  		if(obj.aData.frozenState==1){
												  			sReturn="<aebiz:showTitle titleId="customer.m.frozened"/>";
												  		}else if(obj.aData.frozenState==0){
												  			sReturn="<aebiz:showTitle titleId="customer.m.unfrozened"/>";
												  		}else{
												  			sReturn="";
												  		}
												      return sReturn;
												 }			
											},
											{
											  	"mDataProp":"activeState",
											  	"sDefaultContent":"",
											  	"sTitle": "<aebiz:showTitle titleId="customer.m.activeState"/>" ,
											  	"sWidth":"5%",
											 	"fnRender": function(obj) {	
												  		var sReturn ="";
												  		if(obj.aData.activeState==1){
												  			sReturn="<aebiz:showTitle titleId="customer.m.actived"/>";
												  		}else{
												  			sReturn="<aebiz:showTitle titleId="customer.m.unactived"/>";
												  		}
												      return sReturn;
												 }			
											 },
											{"mDataProp":"createTime" ,"sWidth":"15%","sTitle": "<aebiz:showTitle titleId="customer.m.createTime"/>"},

											{"mDataProp":"frozenTypeShowName" ,"sWidth":"15%","sTitle": "<aebiz:showTitle titleId="customerfreeze.m.reason"/>"},

											
										    {
											 "mDataProp":"operate",
											 "sWidth":"10%",
											 "sDefaultContent":"",
											 "sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
											 "fnRender": function(obj) {	
											  		var sReturn = "" ;
										  			if(obj.aData.frozenState==1){
										  				//frozenState: 1表示冻结，0表示未冻结
										  				sReturn +="<a onclick='javascript:toUnFrozen(\""+obj.aData.uuid+"\")' class='btn' rel='tooltip' data-toggle='modal' data-target='#modal-unfrozen' title='<aebiz:showTitle titleId="customer.m.unfrozen"/>'><aebiz:showTitle titleId="customer.m.unfrozen"/></a>";
										  			}else{
										  				sReturn +="<a onclick='javascript:toFrozen(\""+obj.aData.uuid+"\")' class='btn' rel='tooltip' data-toggle='modal' data-target='#modal-frozen' title='<aebiz:showTitle titleId="customer.m.frozen"/>'><aebiz:showTitle titleId="customer.m.frozen"/></a>";
										  			}
											      return sReturn;
											 }			
										   }
										 ],
				            'oTableTools': {
				                "sSwfPath": "${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
				            },
				            "bRetrieve":true ,
				            "bProcessing":true,
				           	"bServerSide":true,                    //指定从服务器端获取数据
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/customer/queryList" ,
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
		oTable.fnDraw(false); 	
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

//清空普通搜索
function clearSearch() {
	$("#customerName").val("") ;
	$("#mobile").val("");
	$("#email").val("");
}

//清空高级搜索
function clearMoreSearch() {
	$("#customerName_s").val("") ;
	$("#mobile_s").val("") ;
	$("#email_s").val("") ;
	$("#frozenState_s").val("") ;
	$("#frozenType_s").val("") ;
	$("#activeState_s").val("") ;
	$("#authState_s").val("") ;
	$("#createTime_s").val("") ;
	$("#createTime2_s").val("") ;
	$("#customerShopLevelUuid_s").val("") ;
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
    		});	
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
			    	"${pageContext.servletContext.contextPath}/sysback/customer/deletes",
			    	{"selectOne":checkIds,ranNum:Math.random()},	
				    function(data) {
				       var result = eval("("+data+")") ;	       
				       if(result.rsp) {
				  			//刷新
							doSearch('search') ;     	
				       }else{
				       		bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
				       }
				    }
				);		
        	}   
    });   
}

function toReset(uuid,mobile){
	$("#confirmMobile").val(mobile);
	$("#customerUuid").val(uuid);
	$("#customerMobile").val(mobile);
	$("#oldmobile").iCheck('check');
	$("#newmobile").iCheck('uncheck');
}

function toFrozen(uuid){
	$("#customerUuid").val(uuid);
}

function toUnFrozen(uuid){
	$("#customerUuid").val(uuid);
}

// 会员冻结调用表单验证
//重置密码
$(".resetPassword").click(function() {
	resetPassword();
})

function resetPassword() {
	var customerUuid = $("#customerUuid").val();
	var mobile = $("#confirmMobile").val();
	//重置密码
	$.get("${pageContext.servletContext.contextPath}/sysback/customer/restPassword",
		{
			"customerUuid":customerUuid,
			"confirmMobile":mobile,
			 ranNum : Math.random()
		},
		function(data) {
			if (data == "true") {
				//刷新
				bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;
				doSearch('search');
			} else {
				bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
			}
		});
}

// Validation 调用表单验证插件的js
$(function(){
  if ($('.form-validate1').length > 0) {
      $('.form-validate1').each(function() {
          var id = $(this).attr('id');  //获取表单的id
          $("#" + id).validate({   //验证表单
              errorElement: 'span',  //输入错误时的提示标签
              errorClass: 'help-block has-error',  //输入错误时的提示标签类名
              errorPlacement: function(error, element) {  //输入错误时的提示标签显示的位置
                  if(element.parents(".input-group").length > 0){
                  		element.parents(".input-group").after(error);
                  }else if(element.parents(".y_validatainput").length > 0){
                  		element.parents(".y_validatainput").after(error);
                  }
                  else if(element.parents("label").length > 0) {
                      element.parents("label").after(error);
                  }else {
                      element.after(error);
                  }
              },
              highlight: function(label) {   //输入错误时执行的事件
                  $(label).closest('.form-group').removeClass('has-error has-success').addClass('has-error');
              },
              success: function(label) {   //输入正确时执行的事件
                  label.addClass('valid').closest('.form-group').removeClass('has-error has-success').addClass('has-success');
              },
              onkeyup: function(element) {   //验证元素输入值时按钮松开执行的事件
                  $(element).valid();
              },
              onfocusout: function(element) {   //验证元素失去焦点时进行验证
                  $(element).valid();
              },                
              submitHandler: function(form){
            	  frozen(); 
            	  $("#modal-frozen").modal('hide');//关闭弹出框
			  }
          });
      });
  }	
})

//会员解冻调用表单验证
$(function(){
	  if ($('.form-validate2').length > 0) {
	      $('.form-validate2').each(function() {
	          var id = $(this).attr('id');  //获取表单的id
	          $("#" + id).validate({   //验证表单
	              errorElement: 'span',  //输入错误时的提示标签
	              errorClass: 'help-block has-error',  //输入错误时的提示标签类名
	              errorPlacement: function(error, element) {  //输入错误时的提示标签显示的位置
	                  if(element.parents(".input-group").length > 0){
	                  		element.parents(".input-group").after(error);
	                  }else if(element.parents(".y_validatainput").length > 0){
	                  		element.parents(".y_validatainput").after(error);
	                  }
	                  else if(element.parents("label").length > 0) {
	                      element.parents("label").after(error);
	                  }else {
	                      element.after(error);
	                  }
	              },
	              highlight: function(label) {   //输入错误时执行的事件
	                  $(label).closest('.form-group').removeClass('has-error has-success').addClass('has-error');
	              },
	              success: function(label) {   //输入正确时执行的事件
	                  label.addClass('valid').closest('.form-group').removeClass('has-error has-success').addClass('has-success');
	              },
	              onkeyup: function(element) {   //验证元素输入值时按钮松开执行的事件
	                  $(element).valid();
	              },
	              onfocusout: function(element) {   //验证元素失去焦点时进行验证
	                  $(element).valid();
	              },
	              submitHandler: function(form){
	                	unFrozen() ;  
	                    $("#modal-unfrozen").modal('hide');//关闭弹出框
				  }
	          });
	      });
	  }	
	});
	
//会员重置密码调用表单验证	
$(function(){
	  if ($('.form-validate3').length > 0) {
	      $('.form-validate3').each(function() {
	          var id = $(this).attr('id');  //获取表单的id
	          $("#" + id).validate({   //验证表单
	              errorElement: 'span',  //输入错误时的提示标签
	              errorClass: 'help-block has-error',  //输入错误时的提示标签类名
	              errorPlacement: function(error, element) {  //输入错误时的提示标签显示的位置
	                  if(element.parents(".input-group").length > 0){
	                  		element.parents(".input-group").after(error);
	                  }else if(element.parents(".y_validatainput").length > 0){
	                  		element.parents(".y_validatainput").after(error);
	                  }
	                  else if(element.parents("label").length > 0) {
	                      element.parents("label").after(error);
	                  }else {
	                      element.after(error);
	                  }
	              },
	              highlight: function(label) {   //输入错误时执行的事件
	                  $(label).closest('.form-group').removeClass('has-error has-success').addClass('has-error');
	              },
	              success: function(label) {   //输入正确时执行的事件
	                  label.addClass('valid').closest('.form-group').removeClass('has-error has-success').addClass('has-success');
	              },
	              onkeyup: function(element) {   //验证元素输入值时按钮松开执行的事件
	                  $(element).valid();
	              },
	              onfocusout: function(element) {   //验证元素失去焦点时进行验证
	                  $(element).valid();
	              },
	              submitHandler: function(form){
	                	resetPwd() ;  
	                    $("#modal-resetPwd").modal('hide');//关闭弹出框
				  }
	          });
	      });
	  }	
});
	
//会员冻结
function frozen(){
	var customerUuid=$("#customerUuid").val();
	var frozenNote=$("#frozenNote").val();
	var frozenType=$("#frozenType").val();
	$.get("${pageContext.servletContext.contextPath}/sysback/customer/frozen",{"customerUuid":customerUuid,"frozenNote":frozenNote,"frozenType":frozenType,ranNum:Math.random()},function(data) {	
	    	if(data == "true") {
		  			//刷新
					doSearch('search') ;     	
		     }else{
		       		bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
		      }
	    });		
}	

//会员解冻
function unFrozen(){
	var customerUuid = $("#customerUuid").val();
	var note = $("#unFrozenNote").val();
	$.get("${pageContext.servletContext.contextPath}/sysback/customer/unfrozen",{"customerUuid":customerUuid,"unFrozenNote":note,ranNum:Math.random()},function(data){	
		  if(data == "true") {
	  			//刷新
				doSearch('search') ;     	
	      }else{
	       		bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
	      }
	});	
}
//重置密码
function resetPwd() {
	var customerUuid = $("#customerUuid").val();
	var mobile = $("#confirmMobile").val();
	//重置密码
	$.get("${pageContext.servletContext.contextPath}/sysback/customer/resetPassword",{"customerUuid":customerUuid,"confirmMobile":mobile,ranNum : Math.random()},function(data) {
		if (data == "true") {
			//刷新
			doSearch('search');
		} else {
			bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
		}
	});
}


$(document).ready(function() {
	$("#oldmobile").on('ifChecked', function(){
   	$("#confirmMobile").val($("#customerMobile").val());
		$("#confirmMobile").attr("disabled","disabled");
	});
	$("#newmobile").on('ifChecked', function(){
   	$("#confirmMobile").val("");
		$("#confirmMobile").removeAttr("disabled");
	});
	
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
</body>

</html>

