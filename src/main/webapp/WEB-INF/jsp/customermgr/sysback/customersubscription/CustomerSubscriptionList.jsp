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
				<h1><aebiz:showTitle titleId="customersubscription.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="customersubscription.menuOne"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="customersubscription.menuTwo"/></span><i class="fa fa-angle-right"></i></li>							
				<li><span><aebiz:showTitle titleId="customersubscription.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span></li>						
			</ul>				
		</div>				
							
		<div class="row">
			<div class="col-sm-12">
				<div class="box">
					<div class="box-content nopadding y_tableser">
						<div class="y_clear">
							<div class="form-inline table_formnew">
								<div  class="form-group">
									<label class="control-label"><aebiz:showTitle titleId="customer.m.customerName"/>：</label>
									<input type="text" name="customerName" id="customerName" class="form-control">
								</div>
								
								<div class="form-group">
									<label class="control-label"><aebiz:showTitle titleId="customersubscription.m.subscriptionUuid"/>：</label>
									<input type="text" name="subscriptionUuid" id="subscriptionUuid" class="form-control" data-rule-email="true">
									<input type="hidden" name="subscriptionUuid_q" id="subscriptionUuid_q" value="Like">
								</div>
								
								 <div class="form-group">
									<label for="textfield" class="control-label"><aebiz:showTitle titleId="customersubscription.m.subscriptionState"/>：</label>
									<select name="subscriptionState" id="subscriptionState" class="form-control">
										<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</option>
										<option value="1"><aebiz:showTitle titleId="customersubscription.m.subscribed"/></option>
										<option value="0"><aebiz:showTitle titleId="customersubscription.m.unsubscribed"/></option>
									</select>
									<input type="hidden" name="subscriptionState_q" id="subscriptionState_q" value="EQ">
								</div>
					
								<div class="form-group">
									<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
									<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
								</div>
							</div>
							
							<div class="y_tablebtn">
								<button class="btn">导入</button>
								<button class="btn">导出</button>
								<button class="btn">营销导入</button>
								<button class="btn cancelSubscribes" title="<aebiz:showTitle titleId="customersubscription.m.cancelSubscribe"/>" rel="tooltip"><aebiz:showTitle titleId="customersubscription.m.cancelSubscribe"/></button>
							</div>
						</div>
					
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th class='with-checkbox'><input type="checkbox" name="check_all" id="check_all"></th>
									<th><aebiz:showTitle titleId="customersubscription.m.customerUuid"/></th>
									<th><aebiz:showTitle titleId="customersubscription.m.subscriptionUuid"/></th>
									<th><aebiz:showTitle titleId="customersubscription.m.mobile"/></th>
									<th><aebiz:showTitle titleId="customersubscription.m.email"/></th>
									<th><aebiz:showTitle titleId="customersubscription.m.subscriptionState"/></th>
									<th><aebiz:showTitle titleId="customersubscription.m.opertionSend"/></th>
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
	
<script>
	function retrieveData(sSource,aoData,fnCallback) { 
		var searchParam = new Array() ;
		searchParam.push({ "name": "customerName", "value": $.trim($("#customerName").val())});
		searchParam.push({ "name": "subscriptionUuid", "value": $.trim($("#subscriptionUuid").val())});
		searchParam.push({ "name": "subscriptionUuid_q", "value": $.trim($("#subscriptionUuid_q").val())});
		searchParam.push({ "name": "subscriptionState", "value": $.trim($("#subscriptionState").val())});
		searchParam.push({ "name": "subscriptionState_q", "value": $.trim($("#subscriptionState_q").val())});
		
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
					                'aTargets': [0, 7]                
					            }],
					            "bSort": true,           
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
												{"mDataProp":"customerName" ,"sWidth":"15%","sTitle": "<aebiz:showTitle titleId="customer.m.customerName"/>"},
												{"mDataProp":"subscriptionUuid" ,"sWidth":"15%","sTitle": "<aebiz:showTitle titleId="customersubscription.m.subscriptionUuid"/>"},
												{"mDataProp":"mobile" ,"sWidth":"15%","sTitle": "<aebiz:showTitle titleId="customersubscription.m.mobile"/>"},
												{"mDataProp":"email" ,"sWidth":"15%","sTitle": "<aebiz:showTitle titleId="customersubscription.m.email"/>"},
												{
												  	"mDataProp":"scribtionState",
												  	"sWidth":"8%",
												  	"sDefaultContent":"",
												  	"sTitle": "<aebiz:showTitle titleId="customersubscription.m.subscriptionState"/>" ,
												 	"fnRender": function(obj) {	
													  		var sReturn = "";
															if(obj.aData.subscriptionState==1){
																sReturn="<aebiz:showTitle titleId="customersubscription.m.subscribed"/>";
															}else if(obj.aData.subscriptionState==0){
																sReturn="<aebiz:showTitle titleId="customersubscription.m.unsubscribed"/>";
															}else{
																sReturn="";
															}													
													      return sReturn;
													 }			
												  },
												{
												  	"mDataProp":"opertionSend",
												  	"sWidth":"7%",
												  	"sDefaultContent":"",
												  	"sTitle": "<aebiz:showTitle titleId="customersubscription.m.opertionSend"/>" ,
												 	"fnRender": function(obj) {	
													  		var sReturn = "";
															if(obj.aData.opertionSend==1){
																sReturn="<aebiz:showTitle titleId="basebusiness.showmessage.yes"/>";
															}else if(obj.aData.opertionSend==0){
																sReturn="<aebiz:showTitle titleId="basebusiness.showmessage.no"/>";
															}else{
																sReturn="";
															}													
													      return sReturn;
													 }			
												  },
											  {
											  	"mDataProp":"operate",
											  	"sWidth":"20%",
											  	"sDefaultContent":"",
											  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
											 	"fnRender": function(obj) {	
												  		var sReturn =" <a href='${pageContext.servletContext.contextPath}/sysback/customersubscriptionlog/toDetail/"+obj.aData.customerUuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="customersubscribecontent.m.seedetail"/>'><aebiz:showTitle titleId="customersubscribecontent.m.seedetail"/></a>";
															sReturn += "<a href='javascript:updateState(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="customersubscription.m.cancelSubscribe"/>'><aebiz:showTitle titleId="customersubscription.m.cancelSubscribe"/></a>" ;
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
					            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/customersubscription/queryList" ,
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
	//普通搜索
	$(".search").click(function(){	
		doSearch('search') ;  
	})
	
	function clearSearch() {
		$("#customerName").val("") ;
		$("#subscriptionUuid").val("");
		$("#subscriptionState").val("");
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
				    	"${pageContext.servletContext.contextPath}/sysback/customersubscription/deletes",
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
	
	//取消订阅
	$(".cancelSubscribes").click(function(){	
		updateState('') ;  
	})
	
	function updateState(updateId) {
		var checkIds="";
		if( updateId.trim() != "") {
			checkIds = updateId ;
		}else{
			$("input[name='check']:checkbox").each(function(){				
		    if($(this).is(":checked")){        	
		        checkIds += $(this).val()+"," ;  
		    }
			})	
		}
		
		if(checkIds.trim() == "") {
				//提示为空
				bootbox.alert("<aebiz:showTitle titleId="customersubscription.m.chooseUpdateData"/>") ;
				return ;
		} 
		
		bootbox.confirm("<aebiz:showTitle titleId="customersubscription.m.confirmUpdateData"/>", function(r){
	    	if(r) {
	    		//删除
				$.getJSON(
			    	"${pageContext.servletContext.contextPath}/sysback/customersubscription/updateState",
			    	{"selectOne":checkIds,ranNum:Math.random()},	
				    function(data) {	       
				      	       
				       if(data.rsp) {
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
	
	$(document).ready(function() {
		 //初始化表格
	   doSearch('inittable') ;
	   
	   //复选框全选
		 $("#check_all").click(function(e) {
		 		$('input', oTable.fnGetNodes()).prop('checked', this.checked);
		 });       
	}) 
</script>

</body>

</html>