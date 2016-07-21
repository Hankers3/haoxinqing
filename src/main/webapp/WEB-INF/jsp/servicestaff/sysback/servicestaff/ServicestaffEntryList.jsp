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
				<h1><aebiz:showTitle titleId="servicestaff.moduleName_Entry_CN"/></h1>
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
					<span><aebiz:showTitle titleId="servicestaff.moduleName_Entry_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
				</li>						
			</ul>				
		</div>		
				
			<div class="row">
					<div class="col-sm-12">
					 <div class="box">
						<div class="box-content nopadding y_tableser">
							<div class="y_clear">
								<div class="form-inline table_formnew">
								<!--冻结状态-->			
									<input type="hidden" name="frozenSate" id="frozenSate"  value="4" class="form-control">
										
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
											<button class="btn" id="searchshow" title="<aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/>" rel="tooltip" data-toggle="modal" data-target="#modal-user"><aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/></button>
									</div>
							</div>
						</div>					
					 <table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
								<thead>		
									<tr>
										<th><aebiz:showTitle titleId="doctornotice.m.doctorName"/></th>
										<th><aebiz:showTitle titleId="servicestaffinfo.m.realName"/></th>
										<th><aebiz:showTitle titleId="servicestaffinfo.m.sex"/></th>
										<th><aebiz:showTitle titleId="servicestaffinfo.m.certCode"/></th>
										<th><aebiz:showTitle titleId="servicestaff.m.mobile"/></th>
										<th><aebiz:showTitle titleId="servicestaff.m.frozenTyp"/></th>
										<th><aebiz:showTitle titleId="doctor.m.frozenTime"/></th>
										<th><aebiz:showTitle titleId="doctor.m.frozenNote"/></th>
										<th><aebiz:showTitle titleId="servicestaff.m.createTime"/></th>
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
	
	
		
		<div id="modal-user" class="modal fade y_highserch">
			<div class="modal-dialog">
				<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
									<h4 id="user-infos"><aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/></h4>
								</div>
								
					<div class="modal-body">
							<div class="row">
							 <!--医生编号 -->
										<div class="col-sm-6">
										 <div class="form-group">
											<label for="textfield" class="control-label"><aebiz:showTitle titleId="servicestaffinfo.m.serviceStaffUuid"/></label>
											<input type="text" name="serviceStaffName_s" id="serviceStaffName_s" class="form-control">
											<input type="hidden" name="serviceStaffName_q" id="serviceStaffName_q" value="Like">
										</div>
										</div>
										 <!--医生名称 -->
										<div class="col-sm-6">
										 <div class="form-group">
											<label for="textfield" class="control-label"><aebiz:showTitle titleId="doctornotice.m.doctorName"/></label>
											<input type="text" name="serviceStaffName_s" id="serviceStaffName_s" class="form-control">
											<input type="hidden" name="serviceStaffName_q" id="serviceStaffName_q" value="Like">
										</div>
										</div>
								    <!--用户名 -->
										<div class="col-sm-6">
										 <div class="form-group">
											<label for="textfield" class="control-label"><aebiz:showTitle titleId="servicestaff.m.serviceStaffName"/></label>
											<input type="text" name="serviceStaffName_s" id="serviceStaffName_s" class="form-control">
											<input type="hidden" name="serviceStaffName_q" id="serviceStaffName_q" value="Like">
										</div>
										</div>
										<!--手机号-->
										<div class="col-sm-6">
										 <div class="form-group">
											<label for="textfield" class="control-label"><aebiz:showTitle titleId="servicestaff.m.mobile"/></label>
											<input type="text" name="mobile_s" id="mobile_s" class="form-control">
											<input type="hidden" name="mobile_q" id="mobile_q" value="Like">
										</div>
										</<!--冻结状态  -->
									<div class="col-sm-6">
									 <div class="form-group">
										<label for="textfield" class="control-label">&nbsp;</label>
										<input type="hidden" name="frozenSate_s" id="frozenSate_s" class="form-control">
										<input type="hidden" name="frozenSate_q" id="frozenSate_q" value="Like">
									 </div>
									</div>>
										
										<!--冻结时间  -->
										<div class="col-sm-6">
										 <div class="form-group">
											<label for="textfield" class="control-label"><aebiz:showTitle titleId="doctor.m.frozenTime"/></label>
											<input type="text" name="createTime_s" id="createTime_s" class="form-control datepick">
											<input type="hidden" name="createTime_q" id="createTime_q" value="Like">
										</div>
										</div>

										<!--冻结原因-->
										<div class="col-sm-6">
										 <div class="form-group">
											<label for="textfield" class="control-label"><aebiz:showTitle titleId="doctor.m.frozenNote"/></label>
											<input type="text" name="serviceStaffinfoCertCode" id="serviceStaffinfoCertCode" class="form-control">
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
		</div>
		</div>
		<!--高级搜索-->
	<!--入职信息-->
		<!--解冻 -->
		<input type="hidden" id="storeUuid" />
		<form id="frozen" action="${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/tochangefrozenSate" method="get" class='form-horizontal form-bordered form-validate2'>
		<div id="modal-freeze" class="modal fade">  
		<input type="hidden" id="uuid" name="uuid" />                   
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 id="user-infos">
							<aebiz:showTitle titleId="basebusiness.showmessage.unfreeze" />
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
						<input  class="btn freeze btn-primary" onclick='toSubmit()' value='<aebiz:showTitle titleId="basebusiness.showmessage.confirm"/>'>
						<button class="btn clearMoreSearch" data-dismiss="modal" aria-hidden="true">
							<aebiz:showTitle titleId="basebusiness.showmessage.cancel" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!--解冻 -->
	

<script>

function toSubmit(){
	$("#frozen").submit();
}


function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
	searchParam.push({ "name": "serviceStaffName_s", "value": $.trim($("#serviceStaffName_s").val())});
	searchParam.push({ "name": "serviceStaffName_q", "value": $.trim($("#serviceStaffName_q").val())});
	searchParam.push({ "name": "mobile_s", "value": $.trim($("#mobile_s").val())});
	searchParam.push({ "name": "mobile_q", "value": $.trim($("#mobile_q").val())});
	searchParam.push({ "name": "frozenSate_s", "value": $.trim($("#frozenSate_s").val())});
	searchParam.push({ "name": "createTime_s", "value": $.trim($("#createTime_s").val())});
	searchParam.push({ "name": "createTime_q", "value": $.trim($("#createTime_q").val())});
	
	searchParam.push({ "name": "serviceStaffinfoCertCode", "value": $.trim($("#serviceStaffinfoCertCode").val())});
	searchParam.push({ "name": "serviceStaffLevelName", "value": $.trim($("#serviceStaffLevelName").val())});
	searchParam.push({ "name": "serviceStaffinfoSex", "value": $.trim($("#serviceStaffinfoSex").val())});
	
	searchParam.push({ "name": "frozenSate", "value": "1"});
  searchParam.push({ "name": "frozenSate_q", "value": $.trim($("#frozenSate_q").val())});

	searchParam.push({ "name": "serviceStaffName", "value": $.trim($("#serviceStaffName").val())});

	searchParam.push({ "name": "mobile", "value": $.trim($("#mobile").val())});
	
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
// 										  {
// 										  	"mDataProp":"checkbox" ,
// 										  	"sDefaultContent":"",
// 										  	"fnRender": function(obj) {
// 										      var sReturn = "<input type='checkbox' name='check' value='"+obj.aData.uuid+"' />";						      
// 										      return sReturn;
// 										 		}
// 										  },
											{"mDataProp":"serviceStaffName" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="doctornotice.m.doctorName"/>"},
											{"mDataProp":"serviceStaffName" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaff.m.serviceStaffName"/>"},
											{"mDataProp":"serviceStaffRealName" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaffinfo.m.realName"/>"},
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
												  		}else{
												  			sReturn+="";
												  		}
												      return sReturn;
												 }			
											},
											{"mDataProp":"frozenTime" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="doctor.m.frozenTime"/>"},
											{"mDataProp":"frozenNote" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="doctor.m.frozenNote"/>"},
		
										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 		"fnRender": function(obj) {	
											  		var sReturn = "<a   onclick='toInterView(\""+obj.aData.uuid+"\");'   class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.unfreeze"/>'  data-toggle='modal' data-target='#modal-freeze'><aebiz:showTitle titleId="basebusiness.showmessage.unfreeze"/></a>" ;
											  		
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

function setModel(uuid){
		$.get("${pageContext.servletContext.contextPath}/sysback/servicestaffentry/deletes",
	    	{"uuid":uuid,ranNum:Math.random()},	
		    function(data) {	       
		       $("#modal-userSee").html(data);   
		       $("#modal-userSee").modal('show');    
		    }
		);		
}



function toUpdateStaff(uuid){	

		$("#showServiceStaffEntry").html(data);
	
	
}

function toInterView(uuid) {
	
	$("#uuid").val(uuid);

}


</script>
</body>

</html>

