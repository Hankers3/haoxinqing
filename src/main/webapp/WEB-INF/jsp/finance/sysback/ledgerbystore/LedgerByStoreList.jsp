<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<!-- XEditable -->
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/storeback/css/plugins/xeditable/bootstrap-editable.css">
<!-- XEditable -->
<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/momentjs/jquery.moment.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/mockjax/jquery.mockjax.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/xeditable/bootstrap-editable.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/xeditable/address.js"></script>
<!-- 单选和多选 -->
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<!-- Validation 表单验证插件-->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/validation/jquery.validate.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script>
<!-- 调用validate表单验证的js -->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.js"></script>
</head>

<body>
	<div class="container-fluid">	
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="ledgerbystore.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="ledgerbystore.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="ledgerbystore.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span><aebiz:showTitle titleId="ledgerbystore.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
				</li>						
			</ul>				
		</div>				
							
		<div class="row">
					<div class="col-sm-12">
						<div class="box border1-top">
							<div class="box-content nopadding y_tableser">
							<c:if test="${type == 3 }">
								<ul class="tabs tabs-inline tabs-top">
									<li>
										<a href="#profile" data-toggle='tab'>
											<i class="fa fa-user"></i> <aebiz:showTitle titleId="ledgerbystore.m.ledgerByPlat"/></a>
									</li>
									
									<li  class='active'>
										<a href="#storeCatagory" data-toggle='tab'>
											<i class="fa fa-lock"></i> <aebiz:showTitle titleId="ledgerbystore.m.ledgerByStoreCategory"/></a>
									</li>
									
									<li>
										<a href="#security" data-toggle='tab'>
											<i class="fa fa-lock"></i> <aebiz:showTitle titleId="servicestaff.m.mgr"/></a>
									</li>
								</ul>
							</c:if>
							<c:if test="${ empty type}">
								<ul class="tabs tabs-inline tabs-top">
									<li   class='active'>
										<a href="#profile" data-toggle='tab'>
											<i class="fa fa-user"></i> <aebiz:showTitle titleId="ledgerbystore.m.ledgerByPlat"/></a>
									</li>
									
									<li>
										<a href="#storeCatagory" data-toggle='tab'>
											<i class="fa fa-lock"></i> 按队长分账</a>
									</li>
									
									<li>
										<a href="#security" data-toggle='tab'>
											<i class="fa fa-lock"></i> <aebiz:showTitle titleId="servicestaff.m.mgr"/></a>
									</li>
								</ul>
							</c:if>
		
								<div class="tab-content padding tab-content-inline tab-content-bottom y_tabdatable">
								<c:if test="${type == 3 }">
									<div class="tab-pane" id="profile">
								</c:if>
								<c:if test="${ empty type}">
									<div class="tab-pane active" id="profile">
								</c:if>
										<div class="row">
											<div class="col-sm-12">
												<div class="box">							
														<table id="user" class="table table-bordered table-striped table-force-topborder" style="clear: both">
															<tbody>
																<tr>
																	<td width="15%"><aebiz:showTitle titleId="ledgerbystore.m.defaultLedgerRate"/></td>
																	<td width="35%">
																		<a class="y_editab pricedit" id="rounting" href="#" data-type="text" data-pk="1" data-original-title="Enter rounting">${webModel.routingValue }</a>
																	</td>
																	
																	<td width="15%"><aebiz:showTitle titleId="ledgerbystore.m.freightJoinRouting"/></td>
																	<td width="35%">
																	<div class="y_shipttre y_clear">
																		<div class="check-line y_mhfour nopadding">
																			<input type="radio" name="joinRouting" id="freightJoinRoutingValue1" value="1" class='icheck-me' data-skin="square" data-color="blue" <c:if test="${webModel.freightJoinRoutingValue == '1' }">checked="checked"</c:if> />
																			<label class='inline' for="freightJoinRoutingValue1"><aebiz:showTitle titleId="ledgerbystore.m.freightJoinRouting" /></label>
																		</div>
																		<div class="check-line y_mhfour nopadding">
																			<input type="radio" name="joinRouting" id="freightJoinRoutingValue2" value="0" class='icheck-me' data-skin="square" data-color="blue" <c:if test="${webModel.freightJoinRoutingValue == '0' }">checked="checked"</c:if>/>
																			<label class='inline' for="freightJoinRoutingValue2"><aebiz:showTitle titleId="ledgerbystore.m.freightNotJoinRouting" /></label>
																		</div>
																	</div>
																	</td>	
																</tr>																	
															</tbody>
														</table>
												</div>
											</div>
										</div>
									</div>
									
									<div class="tab-pane" id="security">
										
										<div class="box-content nopadding y_tableser">
											<div class="y_clear">
												<div class="form-inline table_formnew">
													<div class="form-group">
														
													</div>
												</div>
											</div>
					
											<div class="table-responsive2">
												<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
													<thead>
														<tr>
															<th class='with-checkbox'><input type="checkbox" name="check_all" id="check_all"></th>
															<th><aebiz:showTitle titleId="servicestaff.m.levelName"/></th>
															<th><aebiz:showTitle titleId="ledgerbystore.m.ledgerRate"/></th>
															<th><aebiz:showTitle titleId="ledgerbystore.m.freightJoinRouting"/></th>
															<th><aebiz:showTitle titleId="basebusiness.showmessage.operate" /></th>
														</tr>
													</thead>
					
													<tbody>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									
								<c:if test="${type == 3 }">
									<div class="tab-pane active" id="storeCatagory">
								</c:if>
								<c:if test="${ empty type}">
									<div class="tab-pane" id="storeCatagory">
								</c:if>
										<div class="row">
											<div class="col-sm-12">
												<div class="box">							
														<table id="user" class="table table-bordered table-striped table-force-topborder" style="clear: both">
															<tbody>
																<tr>
																	<td width="15%"><aebiz:showTitle titleId="ledgerbystore.m.defaultLedgerRate"/></td>
																	<td width="35%">
																		<a class="y_editab pricedit" id="rounting1" href="#" data-type="text" data-pk="1" data-original-title="Enter rounting">${webModel.captainValue }</a>
																	</td>
																	<td width="15%"><aebiz:showTitle titleId="ledgerbystore.m.freightJoinRouting"/></td>
																	<td width="35%">
																	<div class="y_shipttre y_clear">
																		<div class="check-line y_mhfour nopadding">
																			<input type="radio" name="joinRouting1" id="freightJoinRoutingValue1" value="1" class='icheck-me' data-skin="square" data-color="blue" <c:if test="${webModel.freightCaptainValue == '1' }">checked="checked"</c:if> />
																			<label class='inline' for="freightJoinRoutingValue1"><aebiz:showTitle titleId="ledgerbystore.m.freightJoinRouting" /></label>
																		</div>
																		<div class="check-line y_mhfour nopadding">
																			<input type="radio" name="joinRouting1" id="freightJoinRoutingValue2" value="0" class='icheck-me' data-skin="square" data-color="blue" <c:if test="${webModel.freightCaptainValue == '0' }">checked="checked"</c:if>/>
																			<label class='inline' for="freightJoinRoutingValue2"><aebiz:showTitle titleId="ledgerbystore.m.freightNotJoinRouting" /></label>
																		</div>
																	</div>
																	</td>	
																</tr>																	
															</tbody>
														</table>
												</div>
											</div>
										</div>
									</div>
									
									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
		<!-- 设置商户的分账比率 -->
		<input type="hidden" id="accountUuid" />
		<form id="setLedgerRate" action="" method="get" class='form-horizontal form-bordered form-validate2'>		
			<div id="modal-setLedgerRate" class="modal fade y_highserch">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h4 id="user-infos">
								<aebiz:showTitle titleId="ledgerbystore.m.setLedgerRate"/>
							</h4>
						</div>
						<div class="modal-body">
								<div class="form-group">
									<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="ledgerbystore.m.ledgerRate" /></label>
										<div class="col-sm-10">
											<div class="col-xs-4">
												<input type="text" name="ledgerRate" id="ledgerRate" class="form-control" data-rule-required="true" data-rule-float="true" data-rule-maxlength="10" onblur="checkRate();"/>
												<input type="hidden" name="ledgerRate_validate" id="ledgerRate_validate" value="true">
											</div>
										</div>
								</div>
								<div class="form-group">
									<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="ledgerbystore.m.freightJoinRouting"/></label>
									<div class="col-sm-10">
										<div class="y_validatainput y_clear">
											<div class="check-line col-xs-4 ">
												<input type="radio" id="freightJoinRouting1" name="freightJoinRouting" class='icheck-me' data-skin="square" data-color="blue" value="0"/>
												<label class='inline' for="freightJoinRouting1" id="mobileLabel"><aebiz:showTitle titleId="ledgerbystore.m.freightNotJoinRouting"/></label>
											</div>
											<div class="check-line col-xs-4 ">
												<input type="radio" id="freightJoinRouting2" name="freightJoinRouting" class='icheck-me' data-skin="square" data-color="blue" value="1" />
												<label class='inline' for="freightJoinRouting2"><aebiz:showTitle titleId="ledgerbystore.m.freightJoinRouting"/></label> 
											</div>
										</div>
									</div>
							</div>
						</div>
					<div class="modal-footer" style="text-align: right;">
						<input type="button" class="btn setLedgerRate btn-primary"  value='<aebiz:showTitle titleId="basebusiness.showmessage.confirm"/>'>			
						<button class="btn clearMoreSearch" data-dismiss="modal" aria-hidden="true">
							<aebiz:showTitle titleId="basebusiness.showmessage.cancel" />
						</button>
					</div>
				</div>
					</div>
				</div>
			</form>

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
	if (oTable == null) {
		if ($('.dataTable').length > 0) {
			$('.dataTable').each(
				function() {
					var opt = {
						"sPaginationType" : "full_numbers",
						"oLanguage" : {
							"sProcessing" : "<aebiz:showTitle titleId="basebusiness.showmessage.loading"/>",
							"sSearch" : "<span><aebiz:showTitle titleId="basebusiness.showmessage.query"/>:</span> ",
							"sInfo" : "<aebiz:showTitle titleId="basebusiness.showmessage.pageFrom"/> <span>_START_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageTo"/> <span>_END_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageItem"/>，<aebiz:showTitle titleId="basebusiness.showmessage.totalCount"/>： <span>_TOTAL_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageItem"/>",
							"sLengthMenu" : "_MENU_ <span><aebiz:showTitle titleId="basebusiness.showmessage.pageShow"/></span>",
							"oPaginate" : {
								"sFirst" : "<aebiz:showTitle titleId="basebusiness.showmessage.firstPage"/>",
								"sPrevious" : "<aebiz:showTitle titleId="basebusiness.showmessage.prePage"/>",
								"sNext" : "<aebiz:showTitle titleId="basebusiness.showmessage.nextPage"/>",
								"sLast" : "<aebiz:showTitle titleId="basebusiness.showmessage.lastPage"/>"
							}
						},

						//'sDom': "lfrtip",
						'sDom' : "rtlip",
						//"sDom": '<"top"l>rt<"bottom"ip><"clear">',            
						'aoColumnDefs' : [ {
							'bSortable' : false,
							'aTargets' : [ 0,1,2,3,4]
						} ],
						"bSort" : true, //排序功能            
						"aoColumns" : [
								{
									"mDataProp" : "checkbox",
									"sDefaultContent" : "",
									"fnRender" : function(obj) {
										var sReturn = "<input type='checkbox' name='check' value='"+obj.aData.uuid+"' />";
										return sReturn;
									}
								},
								{
									"mDataProp" : "staffentry.levelName",
									"sTitle" : "<aebiz:showTitle titleId="servicestaff.m.levelName"/>"
								},
								{
									"mDataProp" : "ledgerRate1",
									"sDefaultContent" : "",
									"sTitle" : "<aebiz:showTitle titleId="ledgerbystore.m.ledgerRate"/>",
									"fnRender" : function(obj) {
										var sReturn = '<a class="y_editab pricedit" href="#" data-type="text" data-pk="1" data-original-title="Enter price">'
												+ obj.aData.ledgerRate
												+ '</a>';
										return sReturn;
									}
								},
								{
									"mDataProp" : "freightJoinRouting1",
									"sDefaultContent" : "",
									"sTitle" : "<aebiz:showTitle titleId="ledgerbystore.m.freightJoinRouting"/>",
									"fnRender" : function(obj) {
										if(obj.aData.freightJoinRouting == "0"){
											var sReturn = "<aebiz:showTitle titleId="ledgerbystore.m.freightNotJoinRouting"/>";
										}else if(obj.aData.freightJoinRouting == "1"){
											var sReturn = "<aebiz:showTitle titleId="ledgerbystore.m.freightJoinRouting"/>";
										}
										return sReturn;
									}
								},
								{
									"mDataProp" : "operate",
									"sDefaultContent" : "",
									"sTitle" : "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>",
									"fnRender" : function(obj) {
										var sReturn = "<button class='btn'  onClick='javascript:getNowStoreUuid(\""+obj.aData.staffentry.uuid+"\",\""+obj.aData.ledgerRate+"\",\""+obj.aData.freightJoinRouting +"\")' title='<aebiz:showTitle titleId="ledgerbystore.m.setLedgerRate"/>' rel='tooltip' data-toggle='modal' data-target='#modal-setLedgerRate'><aebiz:showTitle titleId="ledgerbystore.m.setLedgerRate"/></button>" ;
										return sReturn;
									}
								}
								],

						'oTableTools' : {
							"sSwfPath" : "${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
						},
						"bRetrieve" : true,
						"bProcessing" : true,
						"bServerSide" : true, //指定从服务器端获取数据
						"sAjaxSource" : "${pageContext.servletContext.contextPath}/sysback/ledgerbystore/queryStaffLevelList",
						"fnServerData" : retrieveData, //获取数据的处理函数  
					};

					if ($(this).hasClass("dataTable-reorder")) {
						opt.sDom = "R" + opt.sDom;
					}

					oTable = $(this).dataTable(opt);

				});
		}
	}

	//刷新Datatable，会自动激发retrieveData  		
	if ('inittable' != operact) {
		oTable.fnDraw();
	}

}



$(".search").click(function(){	
	doSearch('search') ;  
})

$(".searchCategory").click(function(){	
	doSearchCategory('search') ;  
})

function clearSearch() {
	$("#storeNoOrStoreName").val("") ;
	
}

function clearSearchCategory() {
	$("#storeNoOrStoreNameCategory").val("") ;
	
}

function checkRate(){
	var rate = $("#ledgerRate").val();
	if(rate == "" || rate >1){
		 $("span[name='salePriceSpan']").remove();
		 $('input[name="ledgerRate"]').css("border","1px solid #A94442");     
		 $('input[name="ledgerRate"]').after("<span  name='salePriceSpan'><font color='red'><aebiz:showTitle titleId="ledgerbystore.m.LedgerRateTtooLarge"/></font></span>");
		 $("input[name='ledgerRate_validate']").val("false");
	 }else{
		 $("span[name='salePriceSpan']").remove();
		 $('input[name="ledgerRate"]').css("border","1px solid #2B542C");     
		 $("input[name='ledgerRate_validate']").val("true");
	 }
}

//设置分账比率
$(".btn-primary").click(function setLedgerRate() {
	var ledgerRate = $("#ledgerRate").val();
	var accountUuid =  $("#accountUuid").val();
	var rate_validate = $("#ledgerRate_validate").val();
	if(rate_validate == "false"){
		return ;
	}
	var freightJoinRouting =$("input[name='freightJoinRouting']:checked").val();
		$.get("${pageContext.servletContext.contextPath}/sysback/ledgerbystore/addOrUpdateLedgerRate",
			{
				"accountUuid":accountUuid,
				"ledgerRate":ledgerRate,
				"freightJoinRouting":freightJoinRouting,
				ranNum : Math.random()
			},
			function(data) {
				if (data == "success") {
					//刷新
					bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;
					$("#modal-setLedgerRate").modal('hide');//关闭模态框
					doSearch('search');
				} else {
					bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
				}
			});
});

	//获取当前的商户uuid,分账比率
	function getNowStoreUuid(accountUuid,ledgerRate,freightJoinRouting){
		 $("#ledgerRate").val(ledgerRate);
		 $("#accountUuid").val(accountUuid);
		 if(freightJoinRouting == "0"){
			 $('#freightJoinRouting1').iCheck('check');
			 $('freightJoinRouting2').iCheck('uncheck');
		 }else if(freightJoinRouting == "1"){
			 $('#freightJoinRouting2').iCheck('check');
			 $('#freightJoinRouting1').iCheck('uncheck');
		 }
	}

	 $('#rounting').editable({
         url: '${pageContext.servletContext.contextPath}/sysback/ledgerbystore/updatePlatLedger',
         type: 'text',
         validate: function(value) {
        	 var v = /^[0-9]+([.]{1}[0-9]+){0,1}$/;   
	            if(value == '') return '<aebiz:showTitle titleId="ledgerbystore.m.notNull"/>'; 
	            if(value >1) return '<aebiz:showTitle titleId="ledgerbystore.m.LedgerRateTtooLarge"/>'; 
	            if(value.length > 4) return '<aebiz:showTitle titleId="ledgerbystore.m.noMoreThan4"/>'; 
	            if(!v.test(value)) return '<aebiz:showTitle titleId="ledgerbystore.m.float"/>';
	      },
         title: 'Enter username',
         ajaxOptions: {
	        	//data:"introduction="+ $('#introduction').text(),
	            dataType: 'json',
	            type:'get'
	        },
	        success: function(response, newValue) {
	            if(!response) {
	                return "Unknown error!";
	            }          
	            
	            if(response.success === false) {
	                 return response.msg;
	            }
	        }
 	 });
	 
	 //按队长设置分账比率
	 $('#rounting1').editable({
         url: '${pageContext.servletContext.contextPath}/sysback/ledgerbystore/updateCaptainPlatLedger',
         type: 'text',
         validate: function(value) {
        	 var v = /^[0-9]+([.]{1}[0-9]+){0,1}$/;   
	            if(value == '') return '<aebiz:showTitle titleId="ledgerbystore.m.notNull"/>'; 
	            if(value >1) return '<aebiz:showTitle titleId="ledgerbystore.m.LedgerRateTtooLarge"/>'; 
	            if(value.length > 4) return '<aebiz:showTitle titleId="ledgerbystore.m.noMoreThan4"/>'; 
	            if(!v.test(value)) return '<aebiz:showTitle titleId="ledgerbystore.m.float"/>';
	      },
         title: 'Enter username',
         ajaxOptions: {
	        	//data:"introduction="+ $('#introduction').text(),
	            dataType: 'json',
	            type:'get'
	        },
	        success: function(response, newValue) {
	            if(!response) {
	                return "Unknown error!";
	            }          
	            
	            if(response.success === false) {
	                 return response.msg;
	            }
	        }
 	 });
	 
	 $('input[name="joinRouting"]').on('ifChecked', function(event){
		 $.get("${pageContext.servletContext.contextPath}/sysback/ledgerbystore/updateFreightJoinRoutingValue",
					{
						"freightJoinRoutingValue":$(this).val(),
						ranNum : Math.random()
					},
					function(data) {
						if (data == "success") {
							//刷新
							//bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;
						} else {
							bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
						}
					});
	});

$(document).ready(function() {
	
	 //初始化表格
   doSearch('inittable') ;
   doSearchCategory('search') ; 
   
   //复选框全选
	 $("#check_all").click(function(e) {
	 		$('input', oTable.fnGetNodes()).prop('checked', this.checked);
	 });       
		
	
	$(".clearMoreSearch").click(function(e) {    	
		clearMoreSearch() ;
	});
	
	//设置分账比率
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
		                	setLedgerRate(); 
		                	$("#modal-setLedgerRate").modal('hide');//关闭模态框
						        //    form.submit(); //没有这一句表单不会提交
						        }
		            });
		        });
		    }	
		  });
	
	 
}) 
</script>
	
</body>

</html>