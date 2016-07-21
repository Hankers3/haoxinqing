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
						<h1><aebiz:showTitle titleId="servicestaff.moduleName_Interview_CN"/></h1>
					</div>
				</div>
				
				<div class="breadcrumbs">
					<ul>
						<li>
							<span><aebiz:showTitle titleId="servicestaff.menuOne"/></span>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<span><aebiz:showTitle titleId="servicestaff.menuTwo"/></span>
							<i class="fa fa-angle-right"></i>							
						</li>							
						<li>
							<span><aebiz:showTitle titleId="servicestaff.moduleName_Interview_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
						</li>						
					</ul>				
				</div>		
					
			
									
				<div class="row">
						<div class="col-sm-12">
					<div class="box">
								<div class="box-content nopadding y_tableser">
									<div class="y_clear">
										<div class="form-inline table_formnew">
								
										<!--审核状态-->
										<input type="hidden" name="sate" id="sate"  value="1" class="form-control" >	
										<input type="hidden" name="sate_q" id="sate_q" value="Like">	
					
									<div  class="form-group">
										<label class="control-label"><aebiz:showTitle titleId="servicestaff.qmf.serviceStaffName"/>：</label>
										<input type="text" name="serviceStaffName" id="serviceStaffName" class="form-control">
										<input type="hidden" name="serviceStaffName_q" id="serviceStaffName_q" class="form-control" value="Like">
					     		</div>
			     						
									<div  class="form-group">
										<label class="control-label"><aebiz:showTitle titleId="servicestaff.qmf.mobile"/>：</label>
										<input type="text" name="mobile" id="mobile" class="form-control">
										<input type="hidden" name="mobile_q" id="mobile_q" class="form-control" value="Like">
									</div>
			
											
									<div class="form-group">
										<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
										<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
									</div>
					</div>
										
			</div>
													
			 <table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
						<thead>		
							<tr>
								<th><aebiz:showTitle titleId="servicestaff.m.serviceStaffName"/></th>
								<th><aebiz:showTitle titleId="servicestaffinfo.m.realName"/></th>
								<th><aebiz:showTitle titleId="servicestaffinfo.m.certType"/></th>
								<th><aebiz:showTitle titleId="servicestaffinfo.m.certCode"/></th>
								<th><aebiz:showTitle titleId="servicestaff.m.certificationTyp"/></th>
								<th><aebiz:showTitle titleId="servicestaffinfo.m.certImage"/></th>
								
								<th><aebiz:showTitle titleId="servicestaff.m.certificationApplyTime"/></th>
								<th><aebiz:showTitle titleId="servicestaff.m.certificationNote"/></th>
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
							<label for="textfield" class="control-label"><aebiz:showTitle titleId="servicestaff.m.serviceStaffName"/></label>
							<input type="text" name="serviceStaffName_s" id="serviceStaffName_s" class="form-control">
							<input type="hidden" name="serviceStaffName_q" id="serviceStaffName_q" value="Like">
							</div>
							</div>
							
							<div class="col-sm-6">
							 <div class="form-group">
							<label for="textfield" class="control-label"><aebiz:showTitle titleId="servicestaff.m.mobile"/></label>
							<input type="text" name="mobile_s" id="mobile_s" class="form-control">
							<input type="hidden" name="mobile_q" id="mobile_q" value="Like">
							</div>
							</div>
							
							<div class="col-sm-6">
							 <div class="form-group">
							<label for="textfield" class="control-label"><aebiz:showTitle titleId="servicestaff.m.email"/></label>
							<input type="text" name="email_s" id="email_s" class="form-control">
							<input type="hidden" name="email_q" id="email_q" value="EQ">
							</div>
							</div>
						</div>
						<div class="row">
								<div class="col-sm-6">
								 <div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="servicestaff.m.password"/></label>
								<input type="text" name="password_s" id="password_s" class="form-control">
								<input type="hidden" name="password_q" id="password_q" value="EQ">
								</div>
								</div>
								
								
								<div class="col-sm-6">
								 <div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="servicestaff.m.frozenSate"/></label>
								<input type="text" name="frozenSate_s" id="frozenSate_s" class="form-control">
								<input type="hidden" name="frozenSate_q" id="frozenSate_q" value="EQ">
								</div>
								</div>
								
							
								<div class="col-sm-6">
								 <div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="servicestaff.m.frozenTyp"/></label>
								<input type="text" name="frozenTyp_s" id="frozenTyp_s" class="form-control">
								<input type="hidden" name="frozenTyp_q" id="frozenTyp_q" value="Like">
								</div>
								</div>
							</div>
							<div class="row">
									<div class="col-sm-6">
									 <div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="servicestaff.m.lastWrongLoginTime"/></label>
									<input type="text" name="lastWrongLoginTime_s" id="lastWrongLoginTime_s" class="form-control">
									<input type="hidden" name="lastWrongLoginTime_q" id="lastWrongLoginTime_q" value="Like">
									</div>
									</div>
									
									<div class="col-sm-6">
									 <div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="servicestaff.m.activeCode"/></label>
									<input type="text" name="activeCode_s" id="activeCode_s" class="form-control">
									<input type="hidden" name="activeCode_q" id="activeCode_q" value="Like">
									</div>
									</div>
									
									<div class="col-sm-6">
									 <div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="servicestaff.m.loginErrorTimes"/></label>
									<input type="text" name="loginErrorTimes_s" id="loginErrorTimes_s" class="form-control">
									<input type="hidden" name="loginErrorTimes_q" id="loginErrorTimes_q" value="Like">
									</div>
									</div>
							</div>
							<div class="row">
									<div class="col-sm-6">
									 <div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="servicestaff.m.createTime"/></label>
									<input type="text" name="createTime_s" id="createTime_s" class="form-control">
									<input type="hidden" name="createTime_q" id="createTime_q" value="Like">
									</div>
									</div>
									
									<div class="col-sm-6">
									 <div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="servicestaff.m.serviceStaffLevel"/></label>
									<input type="text" name="serviceStaffLevel_s" id="serviceStaffLevel_s" class="form-control">
									<input type="hidden" name="serviceStaffLevel_q" id="serviceStaffLevel_q" value="Like">
									</div>
									</div>
									
									<div class="col-sm-6">
									 <div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="servicestaff.m.deviceToken"/></label>
									<input type="text" name="deviceToken_s" id="deviceToken_s" class="form-control">
									<input type="hidden" name="deviceToken_q" id="deviceToken_q" value="Like">
									</div>
									</div>
							</div>
							<div class="row">
									<div class="col-sm-6">
									 <div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="servicestaff.qm.id2"/></label>
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
		
		<!--认证 -->
		<input type="hidden" id="storeUuid" />
		<form id="frozen" action="${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/tochangecertificationSate" method="get" class='form-horizontal form-bordered form-validate2'>
		<input type="hidden" id="state" name ="state" />
		<input type="hidden" id="uuid" name ="uuid" />
		<div id="modal-inter" class="modal fade y_highserch">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 id="user-infos">
							<aebiz:showTitle titleId="basebusiness.showmessage.interview" />
						</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="form-group">
								<label for="textfield" class="control-label"><aebiz:showTitle titleId="doctorcategory.m.note" /></label>
								<textarea name="note" id="note" class="form-control" data-rule-maxlength="500"  data-rule-required="true"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer" style="text-align: right;">
					<!--认证通过-->
						<input  class="btn freeze btn-primary" onclick="toSubmit('1')" value='<aebiz:showTitle titleId="basebusiness.showmessage.pass"/>'>
					<!--认证不通过-->
					<input  class="btn freeze btn-primary" onclick="toSubmit('2')" value='<aebiz:showTitle titleId="basebusiness.showmessage.nopass"/>'>
						<button class="btn clearMoreSearch" data-dismiss="modal" aria-hidden="true">
							<aebiz:showTitle titleId="basebusiness.showmessage.cancel" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!--认证 -->
		
</body>

</html>

<script>

function toSubmit(s){
	$("#state").val(s);
	$("#frozen").submit();
}

function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
	searchParam.push({ "name": "serviceStaffName", "value": $.trim($("#serviceStaffName").val())});
	searchParam.push({ "name": "serviceStaffName_q", "value": $.trim($("#serviceStaffName_q").val())});
	searchParam.push({ "name": "mobile", "value": $.trim($("#mobile").val())});
	searchParam.push({ "name": "mobile_q", "value": $.trim($("#mobile_q").val())});
	searchParam.push({ "name": "certificationType", "value":"1"});
	searchParam.push({ "name": "sate", "value": $.trim($("#sate").val())});
  searchParam.push({ "name": "sate_q", "value": $.trim($("#sate_q").val())});
	
	
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
											{"mDataProp":"serviceStaffName" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaff.m.serviceStaffName"/>"},
											{"mDataProp":"serviceStaffRealName" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaffinfo.m.realName"/>"},
											{"mDataProp":"serviceStaffinfoCertCode" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaffinfo.m.certType"/>"},
											{"mDataProp":"serviceStaffinfoCertCode" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaffinfo.m.certCode"/>"},

											{
											  	"mDataProp":"serviceStaffinfoSex",
											  	"sDefaultContent":"",
											  	"sWidth":"8%",
											  	"sTitle": "<aebiz:showTitle titleId="servicestaff.m.certificationTyp"/>" ,
											 		"fnRender": function(obj) {	
												  		var sReturn ="";
												  		if(obj.aData.serviceStaffinfoSex==0){
												  			sReturn+="<aebiz:showTitle titleId="doctorinfo.m.certificationwait"/>";
												  		}else if(obj.aData.serviceStaffinfoSex==1){
												  			sReturn+="<aebiz:showTitle titleId="doctorinfo.m.certificationsuccess"/>";
												  		}else if(obj.aData.serviceStaffinfoSex==2){
												  			sReturn+="<aebiz:showTitle titleId="doctorinfo.m.certificationfailure"/>";
												  		}else{
												  			sReturn+="";
												  		}
												      return sReturn;
												 }			
											},
											{"mDataProp":"serviceStaffinfoCertCode" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaffinfo.m.certImage"/>"},
											{"mDataProp":"createTime" ,"sTitle": "<aebiz:showTitle titleId="servicestaff.m.certificationApplyTime"/>"},
											{"mDataProp":"createTime" ,"sTitle": "<aebiz:showTitle titleId="servicestaff.m.certificationNote"/>"},
		

										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 	"fnRender": function(obj) {	
											  		var sReturn = "<a onclick='toInterView(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip'  data-toggle='modal' data-target='#modal-inter'  title='<aebiz:showTitle titleId="basebusiness.showmessage.interview"/>'><aebiz:showTitle titleId="basebusiness.showmessage.interview"/></a>" ;
																									
											      return sReturn;
											 }			
										  },
										  
										  
										  
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
	$("#serviceStaffName").val("") ;
	$("#mobile").val("") ;
	
}

//清空高级搜索的所有选项
function clearMoreSearch() {
	$("#serviceStaffName_s").val("") ;
	$("#mobile_s").val("") ;
	$("#email_s").val("") ;
	$("#password_s").val("") ;
	$("#frozenSate_s").val("") ;
	$("#frozenTyp_s").val("") ;
	$("#lastWrongLoginTime_s").val("") ;
	$("#activeCode_s").val("") ;
	$("#loginErrorTimes_s").val("") ;
	$("#createTime_s").val("") ;
	$("#serviceStaffLevel_s").val("") ;
	$("#deviceToken_s").val("") ;
	$("#id2_s").val("") ;
	$("#serviceStaffLevelName").val("") ;
  $("#serviceStaffinfoCertCode").val("") ;
	$("#serviceStaffinfoSex").val("") ;
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
			    	"${pageContext.servletContext.contextPath}/sysback/servicestaff/deletes",
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

		$("#uuid").val(uuid);
		$("#showInter").html(data);
	
	}
</script>