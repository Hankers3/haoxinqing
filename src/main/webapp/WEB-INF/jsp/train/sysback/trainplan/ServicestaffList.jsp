<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
</head>

<body>
	<div class="modal-dialog modal_1000">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<font size="3">添加人员</font>
			</div>
			
			<div class="modal-body">
				<div class="box">
					<!--状态/冻结状态-->			
					<input type="hidden" name="planUuid" id="planUuid"  value="${uuid}" >
					<input type="hidden" name="frozenSate" id="frozenSate"  value="5" >
					<input type="hidden" name="frozenSate_q" id="frozenSate_q"  value="EQ" >
					<input type="hidden" name="sate" id="sate"  value="0" >	
					<input type="hidden" name="sate_q" id="sate_q" value="EQ">
					
					<div class="y_clear">
						<div class="table_form">	
							<label class="control-label"><aebiz:showTitle titleId="servicestaff.qmf.serviceStaffName"/>：</label>
							<div class="col-sm-2 y_tbsrmr">
								<input type="text" name="serviceStaffName" id="serviceStaffName" class="form-control">
								<input type="hidden" name="serviceStaffName_q" id="serviceStaffName_q" class="form-control" value="Like">
			     		</div>
							
							<label class="control-label"><aebiz:showTitle titleId="servicestaff.qmf.mobile"/>：</label>
							<div class="col-sm-2 y_tbsrmr">
								<input type="text" name="mobile" id="mobile" class="form-control">
								<input type="hidden" name="mobile_q" id="mobile_q" class="form-control" value="EQ">
							</div>
								
							<!--
							<label class="control-label">身份证号码：</label>
							<div class="col-sm-2 y_tbsrmr">
								<input type="text" class="form-control ">
							</div>-->
						
							<p>
								<button class="btn btn-primary search" title="查询" rel="tooltip">查询</button>
								<button class="btn" onclick="javascript:clearSearch();" title="清空" rel="tooltip">清空</button>
							</p>
						</div>
						<div class="y_tablebtn">
							<button class="btn" onclick="toAddStaff('${uuid}','')" title="批量添加" rel="tooltip">批量添加</button>
						</div>
					</div>
					
					
					<div class="table-responsive2 no_leftBox">
						<table class="table table-bordered table-hover table-striped dataTablex dataTable-nosort dataTable-reorder" data-nosort="0,10">
							<thead>		
								<tr>
									<th class='with-checkbox'>
										<input type="checkbox" name="check_all" id="check_allx">
									</th>
									<th><aebiz:showTitle titleId="servicestaff.m.serviceStaffName"/></th>
									<th><aebiz:showTitle titleId="servicestaffinfo.m.sex"/></th>
									<th><aebiz:showTitle titleId="servicestaffinfo.m.certCode"/></th>
									<th><aebiz:showTitle titleId="servicestaff.m.mobile"/></th>
									<th><aebiz:showTitle titleId="servicestafflevel.m.levelName"/></th>
									<th><aebiz:showTitle titleId="servicestaff.m.sate"/></th>
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
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
	
</body>

</html>

<script>
function retrieveDatax(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
	
	searchParam.push({ "name": "frozenSate", "value": $.trim($("#frozenSate").val())});
  searchParam.push({ "name": "frozenSate_q", "value": $.trim($("#frozenSate_q").val())});
 	searchParam.push({ "name": "sate", "value":"0"});
  searchParam.push({ "name": "sate_q", "value": $.trim($("#sate_q").val())});
  searchParam.push({ "name": "serviceStaffName", "value": $.trim($("#serviceStaffName").val())});
  searchParam.push({ "name": "serviceStaffName_q", "value": $.trim($("#serviceStaffName_q").val())});

	searchParam.push({ "name": "mobile_s", "value": $.trim($("#mobile_s").val())});
	searchParam.push({ "name": "mobile_q", "value": $.trim($("#mobile_q").val())});
	
	searchParam.push({ "name": "planUuid", "value": $.trim($("#planUuid").val())});

   $.ajax({    	        
  	"dataType": 'json', 
	  "type": "POST", 
	  "url": sSource, 
	  "data": {aoData:JSON.stringify(aoData),searchParam:JSON.stringify(searchParam)}, 
	  "success": fnCallback
  });    
} 
    
var oTablex = null; 
function doSearchx(operact) {	
	if(oTablex == null) {
    if ($('.dataTablex').length > 0) {
        $('.dataTablex').each(function() {            
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
										  
											{"mDataProp":"serviceStaffName" ,"sDefaultContent":"","sTitle": "<aebiz:showTitle titleId="servicestaff.m.serviceStaffName"/>"},										
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
											  	"sTitle": "<aebiz:showTitle titleId="servicestaff.m.sate"/>" ,
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
												  		}else if(obj.aData.frozenSate==5){
												  			sReturn+="<aebiz:showTitle titleId="servicestaff.m.formal"/>";
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
										 			var sReturn="";
										 			if(obj.aData.isPlan=='1'){
										 			 sReturn = "<a href='#'  class='btn' rel='tooltip' title='已添加'>已添加</a>" ;
										 			}else{
										 			 sReturn = "<a href='#' onclick='javascript:toAddStaff(\""+'${uuid}'+"\",\""+obj.aData.uuid+"\")' class='btn' rel='tooltip' title='添加'>添加</a>" ;
										 			}
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
										"fnServerData":retrieveDatax,           //获取数据的处理函数  
				        };
				        
                		if ($(this).hasClass("dataTable-reorder")) {
		                    opt.sDom = "R" + opt.sDom;
		                }
		               
		                oTablex = $(this).dataTable(opt);
                
        	});
   		}         
	}
		
	//刷新Datatable，会自动激发retrieveData  		
  	if('inittablex' != operact) {  			
		oTablex.fnDraw(); 	
	}  
	 	  
}

$(".search").click(function(){	
	//普通搜索需要清空高级搜索的选项
	doSearchx('search') ;  
})
function clearSearch() {
	$("#serviceStaffName").val("") ;
	$("#mobile").val("") ;
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
   doSearchx('inittablex') ;
   //复选框全选
	 $("#check_allx").click(function(e) {
	 		$('input', oTablex.fnGetNodes()).prop('checked', this.checked);
	 });       

}) 



$(document).ready(function() {	
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
                	doPwdReset() ;  
				        //    form.submit(); //没有这一句表单不会提交
				        }
            });
        });
    }	
  })
}) 



</script>