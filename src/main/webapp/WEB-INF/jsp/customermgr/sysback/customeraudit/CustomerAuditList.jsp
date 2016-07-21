<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<!-- colorbox -->
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/colorbox/colorbox.css">
<!-- colorbox -->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/colorbox/jquery.colorbox-min.js"></script>


</head>

<body>
	<div class="container-fluid">	
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="customerauth.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="customerauth.menuOne"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="customerauth.menuTwo"/></span><i class="fa fa-angle-right"></i></li>							
				<li><span><aebiz:showTitle titleId="customerauth.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span></li>						
			</ul>				
		</div>				
							
		<div class="row">
				<div class="col-sm-12">
					<div class="box">
						<div class="box-content nopadding y_tableser">
							<div class="y_clear">
								<div class="form-inline table_formnew">
								
									<div  class="form-group">
										<label class="control-label"><aebiz:showTitle titleId="customerauth.qmf.realName"/>：</label>
										<input type="text" name="realName" id="realName" class="form-control">
										<input type="hidden" name="realName_q" id="realName_q" class="form-control" value="Like">
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
										<th><aebiz:showTitle titleId="customerauth.m.realName"/></th>
										<th><aebiz:showTitle titleId="customerauth.m.certType"/></th>
										<th><aebiz:showTitle titleId="customerauth.m.certCode"/></th>
										<th><aebiz:showTitle titleId="customerauth.m.certImage1"/></th>
										<th><aebiz:showTitle titleId="customerauth.m.certImage2"/></th>
										<th><aebiz:showTitle titleId="customerauth.m.authApplyTime"/></th>
										<th><aebiz:showTitle titleId="customerauth.m.authState"/></th>
										<th><aebiz:showTitle titleId="customerauth.m.auditReason"/></th>
										<th><aebiz:showTitle titleId="customerauth.m.auditTime"/></th>
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
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerauth.m.realName"/></label>
								<input type="text" name="realName_s" id="realName_s" class="form-control">
								<input type="hidden" name="realName_q" id="realName_q" value="Like">
							</div>
						</div>
						<div class="col-sm-6">
							 <div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerauth.m.certCode"/></label>
								<input type="text" name="certCode_s" id="certCode_s" class="form-control">
								<input type="hidden" name="certCode_q" id="certCode_q" value="EQ">
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-sm-6">
							 <div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerauth.m.authState"/></label>
								<select name="authState_s" id="authState_s" class='form-control'>
									<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</option>
									<option value="1"><aebiz:showTitle titleId="customer.m.authed"/></option>
									<option value="0"><aebiz:showTitle titleId="customer.m.unauthed"/></option>
								</select>
								<input type="hidden" name="authState_q" id="authState_q" value="EQ">
							</div>
						</div>
						
						<div class="col-sm-6">
							 <div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerauth.m.certType"/></label>
								<select name="certType_s" id="certType_s" class='form-control'>								
									<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</option>
									<c:forEach items="${certTypeList}" var="dpp">
										<option value="${dpp.name}">${dpp.value}</option>
									</c:forEach>											
								</select>
								<input type="hidden" name="certType_q" id="certType_q" value="EQ">
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
	
	<!-- 会员实名认证审核 -->	
	<input type="hidden" id="customerUuidHidden">
	<div id="modal-auditpass" class="modal fade y_highserch">
		<form id="passForm" action="" method="get" class='form-validate1'>
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 id="user-infos"><aebiz:showTitle titleId="customerauth.m.audit"/></h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerauth.m.auditReason"/></label>
							<textarea id="passReason" name="auditReason" rows="3" cols="" class="form-control" data-rule-required="true"></textarea>
						</div>
						
						<div class="modal-footer" style="text-align: right;">
							<input type="submit" class="btn auditpass btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.confirm"/>'>
							<button class="btn" data-dismiss="modal">
								<aebiz:showTitle titleId="basebusiness.showmessage.cancel" />
							</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>	
	<!-- 会员实名认证审核 -->	
	
	<!-- 会员实名认证审核 -->	
	<div id="modal-auditunpass" class="modal fade y_highserch">
		<form id="unPassForm" action="" method="get" class='form-validate2'>
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 id="user-infos"><aebiz:showTitle titleId="customerauth.m.audit"/></h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="textfield" class="control-label"><aebiz:showTitle titleId="customerauth.m.auditReason"/></label>
							<textarea id="unPassReason" name="auditReason" rows="3" cols="" class="form-control" data-rule-required="true"></textarea>
						</div>
						
						<div class="modal-footer" style="text-align: right;">
							<input type="submit" class="btn auditunpass btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.confirm"/>'>
							<button class="btn" data-dismiss="modal">
								<aebiz:showTitle titleId="basebusiness.showmessage.cancel" />
							</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>	
	<!-- 会员实名认证审核 -->	
	
<script>
function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
	searchParam.push({ "name": "realName", "value": $.trim($("#realName").val())});
	searchParam.push({ "name": "realName_s", "value": $.trim($("#realName_s").val())});
	searchParam.push({ "name": "realName_q", "value": $.trim($("#realName_q").val())});
	searchParam.push({ "name": "certCode_s", "value": $.trim($("#certCode_s").val())});
	searchParam.push({ "name": "certCode_q", "value": $.trim($("#certCode_q").val())});
	searchParam.push({ "name": "authState_s", "value": $.trim($("#authState_s").val())});
	searchParam.push({ "name": "authState_q", "value": $.trim($("#authState_q").val())});
	searchParam.push({ "name": "certType_s", "value": $.trim($("#certType_s").val())});
	searchParam.push({ "name": "certType_q", "value": $.trim($("#certType_q").val())});
	
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
				                'aTargets': [0]                
				            }],
				            "bSort": true, //排序功能            
					         	"aoColumns": [
										    {
											  	"mDataProp":"checkbox" ,
											  	"sWidth":"5%",
											  	"sDefaultContent":"",
											  	"fnRender": function(obj) {
											      var sReturn = "<input type='checkbox' name='check' value='"+obj.aData.uuid+"' />";						      
											      return sReturn;
											 	}
										    },
											{"mDataProp":"realName" ,"sWidth":"5%","sTitle": "<aebiz:showTitle titleId="customerauth.m.realName"/>"},
											{
											  	"mDataProp":"certType",
											  	"sWidth":"5%",
											  	"sDefaultContent":"",
											  	"sTitle": "<aebiz:showTitle titleId="customerauth.m.certType"/>" ,
											 	"fnRender": function(obj) {	
											 			var sReturn="";
												  		if(obj.aData.certType==1){
												  			sReturn ="<aebiz:showTitle titleId="customerinfo.certType.IDcard"/>";
												  		}else if(obj.aData.certType==2){
												  			sReturn ="<aebiz:showTitle titleId="customerinfo.certType.officerCard"/>"
												  		}															
												      return sReturn;
												 }			
											},
											{"mDataProp":"certCode" ,"sTitle": "<aebiz:showTitle titleId="customerauth.m.certCode"/>"},
											{
											  	"mDataProp":"certImage1",
											  	"sWidth":"15%",
											  	"sDefaultContent":"",
											  	"sTitle": "<aebiz:showTitle titleId="customer.view.certImage1"/>" ,
											 	"fnRender": function(obj) {	
													var sReturn="";
													if(obj.aData.certImage1Url!=""){
														sReturn="<a href='#'><img src='"+obj.aData.certImage1Url+"' width='80px' height='50px;' alt='logo'></a>";
														sReturn+="<div class='extras'>";
														sReturn+="<div class='extras-inner'>";
														sReturn+="<a href='"+obj.aData.certImage1Url+"' class='colorbox-image' rel='group-1'><i class='fa fa-search'></i></a>";
														sReturn+="</div>";
														sReturn+="</div>";
													}
												    return sReturn;
												 }			
											},
											{
											  	"mDataProp":"certImage2",
											  	"sWidth":"15%",
											  	"sDefaultContent":"",
											  	"sTitle": "<aebiz:showTitle titleId="customer.view.certImage2"/>" ,
											 	"fnRender": function(obj) {	
											 		var sReturn="";
													if(obj.aData.certImage2Url!=""){
														sReturn="<a href='#'><img src='"+obj.aData.certImage2Url+"' width='80px' height='50px;' alt='logo'></a>";
														sReturn+="<div class='extras'>";
														sReturn+="<div class='extras-inner'>";
														sReturn+="<a href='"+obj.aData.certImage2Url+"' class='colorbox-image' rel='group-1'><i class='fa fa-search'></i></a>";
														sReturn+="</div>";
														sReturn+="</div>";
													}
												    return sReturn;
												 }			
											},
											{"mDataProp":"authApplyTime" ,"sTitle": "<aebiz:showTitle titleId="customerauth.m.authApplyTime"/>"},
											{
											  	"mDataProp":"authState",
											  	"sWidth":"5%",
											  	"sDefaultContent":"",
											  	"sTitle": "<aebiz:showTitle titleId="customerauth.m.authState"/>" ,
											 	"fnRender": function(obj) {	
											 			var sReturn ="";
												  		if(obj.aData.authState==1){
												  			sReturn ="<aebiz:showTitle titleId="customer.m.authed"/>";
												  		}else{
												  			sReturn ="<aebiz:showTitle titleId="customer.m.unauthed"/>"
												  		}															
												      return sReturn;
												 }			
											},
											{"mDataProp":"auditReason" ,"sWidth":"15%","sTitle": "<aebiz:showTitle titleId="customerauth.m.auditReason"/>"},
											{"mDataProp":"auditTime" ,"sWidth":"10%","sTitle": "<aebiz:showTitle titleId="customerauth.m.auditTime"/>"},
										    {
											  	"mDataProp":"operate",
											  	"sWidth":"15%",
											  	"sDefaultContent":"",
											  	"sTitle": "<aebiz:showTitle titleId="customerauth.m.audit"/>" ,
											 	"fnRender": function(obj) {	
											 			var sReturn="";
											 			if(obj.aData.authState=="<aebiz:showTitle titleId="customer.m.authed"/>"){
											 				sReturn="<a onclick='javascript:toAudit(\""+obj.aData.customerUuid+"\")' class='btn' rel='tooltip' data-toggle='modal' data-target='#modal-auditunpass'><aebiz:showTitle titleId="customerauth.audit.unpass"/></a>";
											 			}else{
											 				sReturn ="<a onclick='javascript:toAudit(\""+obj.aData.customerUuid+"\")' class='btn' rel='tooltip' data-toggle='modal' data-target='#modal-auditpass'><aebiz:showTitle titleId="customerauth.audit.pass"/></a>";
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
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/customeraudit/queryList" ,
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
	$("#realName").val("");
}

//清空高级搜索的所有选项
function clearMoreSearch() {
	$("#realName_s").val("") ;
	$("#certCode_s").val("") ;
	$("#authState_s").val("") ;
	$("#certType_s").val("") ;
}

function toAudit(customerUuid){
	$("#customerUuidHidden").val(customerUuid);
}

function pass(){
	var customeUuid=$("#customerUuidHidden").val();
	var auditReason=$("#passReason").val();
	var url="${pageContext.servletContext.contextPath}/sysback/customeraudit/doAuditPass";
	$.get(url,{"customerUuid":customeUuid,"auditReason":auditReason,"ranNum":Math.random},function(data){
		if(data=="ture") {
  			//刷新
			doSearch('search') ;     	
	    }else{
	       	bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
	    }
	});	
}

function unpass(){
	var customeUuid=$("#customerUuidHidden").val();
	var auditReason=$("#unPassReason").val();
	var url="${pageContext.servletContext.contextPath}/sysback/customeraudit/doAuditUnPass";
	$.get(url,{"customerUuid":customeUuid,"auditReason":auditReason,"ranNum":Math.random},function(data){
		if(data=="ture") {
  			//刷新
			doSearch('search') ;     	
	    }else{
	       	bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
	    }
	});	
}

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
	            	  //审核通过
	                  pass();
	                  $("#modal-auditpass").modal('hide');//关闭弹出框
				  }
	          });
	      });
	  }	
});

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
             		   //审核不通过
	             	   unpass();
	                   $("#modal-auditunpass").modal('hide');//关闭弹出框
				  }
	          });
	      });
	  }
});


$(document).ready(function() {
	 //初始化表格
     doSearch('inittable') ;
 	  
     //复选框全选
	 $("#check_all").click(function(e) {
	 	$('input', oTable.fnGetNodes()).prop('checked', this.checked);
	 });       
	
	 //高级搜索弹出框
	 $("#searchshow").click(function(e) {    	
		e.preventDefault();
	    $("#modal-user").modal("show");
	 });
	
	 $(".clearMoreSearch").click(function(e) {    	
		clearMoreSearch() ;
	 });
}) 
</script>

<!-- 调用放大图插件的js -->
<script>
$(function(){
	$("body").on("click",".colorbox-image",function(){colorbox();});
	function colorbox(){
		$(".colorbox-image").colorbox({  
	      maxWidth: "90%",
	      maxHeight: "90%",
	      rel: $(this).attr("rel")
	    });
	}  
});
</script>
</body>
</html>