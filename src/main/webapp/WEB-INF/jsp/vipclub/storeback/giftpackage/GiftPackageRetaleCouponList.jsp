<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/import/ListImport.jsp" %>

</head>

<body>
	<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeHead.jsp"%>
	<div class="container-fluid" id="content">
	<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeLeft.jsp"%>
		<div id="main">
			<div class="container-fluid">	
				<div class="page-header">
					<div class="pull-left">
						<h1><aebiz:showTitle titleId="giftpackage.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
					</div>
				</div>
				<div class="breadcrumbs">
					<ul>
						<li>
							<span><aebiz:showTitle titleId="giftpackage.menuOne"/></span>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<span><aebiz:showTitle titleId="giftpackage.menuTwo"/></span>
							<i class="fa fa-angle-right"></i>							
						</li>							
						<li>
							<span><aebiz:showTitle titleId="giftpackage.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
						</li>						
					</ul>				
				</div>				
									
				<div class="row">
						<div class="col-sm-12">
							<div class="box">
								<div class="box-content nopadding y_tableser">
									<div class="y_clear">
										<div class="form-inline table_formnew">
										
											<div  class="form-group">
												<label class="control-label"><aebiz:showTitle titleId="giftpackage.m.couponTypeName"/>：</label>
												<input type="text" name="couponTypeName" id="couponTypeName" class="form-control">
											</div>
											<div class="form-group">
												<button class="btn btn-primary search" id="queryCoupons" title="<aebiz:showTitle titleId='basebusiness.showmessage.query' />" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query" /></button>
												<button class="btn" onclick="javascript:clearQueryCoupons();" title="<aebiz:showTitle titleId='basebusiness.showmessage.clear' />" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear" /></button>
											</div>
										</div>
										
										<div class="y_tablebtn">
											<button type="button" class="btn btn-primary" id="searchCoupons" data-toggle="modal" data-target="#myModal1" ><aebiz:showTitle titleId="giftpackage.m.relateCoupon"/></button>
											<button type="button" class="btn" onclick="javascript:removes('');" title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></button>
											<button type="button" class="btn btn-danger" id="returnList" ><aebiz:showTitle titleId="basebusiness.showmessage.return"/></button>
										</div>
								</div>
								
								<table id="couponsTable" class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
									<thead>		
										<tr>
											<th class='with-checkbox'>
												<input type="checkbox" name="check_all" id="check_all">
											</th>
											<th><aebiz:showTitle titleId="giftpackage.m.couponTypeName"/></th>
											<th><aebiz:showTitle titleId="giftpackage.m.denomination"/></th>
											<th><aebiz:showTitle titleId="giftpackage.m.issuedNum"/></th>
											<th><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
										
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
		</div>
	</div>	
	
	
	<!--关联优惠券列表-->
	<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <h4 class="modal-title" id="myModalLabel"><aebiz:showTitle titleId="giftpackage.m.chooseCoupon"/></h4>
	      </div>
	      <div class="modal-body relation_brand nopadd_bott">
	      	<div class="y_clear">
		      	<div class="form-inline table_formnew">
							<div class="form-group">
								<label class="control-label"><aebiz:showTitle titleId="giftpackage.m.couponTypeName" />：</label>
								<input type="text" name="couponTypeName_s" id="couponTypeName_s" class="form-control">
							</div>
							<div class="form-group">
								<button class="btn btn-primary search" id="searchCoupon" title="<aebiz:showTitle titleId='basebusiness.showmessage.query' />" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query" /></button>
								<button class="btn" onclick="javascript:clearSearchCoupon();" title="<aebiz:showTitle titleId='basebusiness.showmessage.clear' />" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear" /></button>
							</div>
		      	</div>
	      	</div>
					<div class="box-content">
						<div class="tab-content y_tabdatable">
							<div class="tab-pane active select_pro">
								<table id="searchCouponsTable" class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
									<thead>
										<tr>
											<th class='with-checkbox'>
												<input type="checkbox" name="checkCoupon_all" id="checkCoupon_all">
											</th>
											<th><aebiz:showTitle titleId="giftpackage.m.couponTypeName"/></th>
											<th><aebiz:showTitle titleId="giftpackage.m.denomination"/></th>
											<th><aebiz:showTitle titleId="giftpackage.m.issuedNum"/></th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>
					</div>
  			</div>
  			<div class="modal-footer"><button type="button" class="btn btn-primary" data-dismiss="modal" id="chooseCouponIds"><aebiz:showTitle titleId="basebusiness.showmessage.confirm"/></button></div>
			</div>
		</div>
	</div>
	<!--关联优惠券列表-->
	
</body>

</html>

<script>
function retrieveQueryData(sSource,aoData,fnCallback) { 
		var searchParam = new Array();
		searchParam.push({"name" : "couponTypeName","value" : $.trim($("#couponTypeName").val())});

		$.ajax({
			"dataType" : 'json',
			"type" : "POST",
			"url" : sSource,
			"data" : {
				aoData : JSON.stringify(aoData),
				searchParam : JSON.stringify(searchParam)
			},
			"success" : fnCallback
		});
	} 
	
function retrieveSearchData(sSource,aoData,fnCallback) { 
		var searchParam = new Array();
		searchParam.push({"name" : "couponTypeName","value" : $.trim($("#couponTypeName_s").val())});

		$.ajax({
			"dataType" : 'json',
			"type" : "POST",
			"url" : sSource,
			"data" : {
				aoData : JSON.stringify(aoData),
				searchParam : JSON.stringify(searchParam)
			},
			"success" : fnCallback
		});
	} 

    
var oTable1 = null; 
function doSearch(operact) {	
	if(oTable1 == null) {		 
    if ($('#couponsTable').length > 0) {
        $('#couponsTable').each(function() {            
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
				                'aTargets': [0,4]                
				            }],
				            "bSort": true, //排序功能            
					         	"aoColumns": [
										  {
										  	"mDataProp":"checkbox" ,
										  	"sDefaultContent":"",
										  	"sWidth":"3%",
										  	"fnRender": function(obj) {
										      var sReturn = "<input type='checkbox' name='check' value='"+obj.aData.uuid+"' />";						      
										      return sReturn;
										 		}
										  },
											{
													"mDataProp" : "couponTypeName",
													"sTitle" : "<aebiz:showTitle titleId="giftpackage.m.couponTypeName"/>"
												},
												{
													"mDataProp" : "denomination",
													"sTitle" : "<aebiz:showTitle titleId="giftpackage.m.denomination"/>"
												},
												{
													"mDataProp" : "issuedNum",
													"sTitle" : "<aebiz:showTitle titleId="giftpackage.m.issuedNum"/>"
												},
												{
											  	"mDataProp":"operate",
											  	"sDefaultContent":"",
											  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
											 		"fnRender": function(obj) {	
												  		var sReturn = "<a name='chooseId' value='"+obj.aData.uuid+"' href='javascript:removes(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>'><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></a>" ;
												      return sReturn;
												 }			
											  }
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
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/storeback/giftpackage/queryCouponList/${m.uuid}",
										"fnServerData":retrieveQueryData,           //获取数据的处理函数  
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

var oTable2 = null;
	function doSearchCoupon(operact) {
		if (oTable2 == null) {
			if ($('#searchCouponsTable').length > 0) {
				$('#searchCouponsTable')
						.each(
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
										'sDom' : "rtlip",  
										'aoColumnDefs' : [ {
											'bSortable' : false,
											'aTargets' : [ 0 ]
										} ],
										"bSort" : true, //排序功能            
										"aoColumns" : [
												{
													"mDataProp" : "checkbox",
													"sDefaultContent" : "",
													"fnRender" : function(obj) {
														var sReturn = "<input type='checkbox' name='checkCoupon' value='"+obj.aData.uuid+"' />";
														return sReturn;
													}
												},
												{
													"mDataProp" : "couponTypeName",
													"sTitle" : "<aebiz:showTitle titleId="giftpackage.m.couponTypeName"/>"
												},
												{
													"mDataProp" : "denomination",
													"sTitle" : "<aebiz:showTitle titleId="giftpackage.m.denomination"/>"
												},
												{
													"mDataProp" : "issuedNum",
													"sTitle" : "<aebiz:showTitle titleId="giftpackage.m.issuedNum"/>"
												}
												],
										'oTableTools' : {
											"sSwfPath" : "${pageContext.servletContext.contextPath}/static/storeback/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
										},
										"bRetrieve" : true,
										"bProcessing" : true,
										"bServerSide" : true, //指定从服务器端获取数据
										"sAjaxSource" : "${pageContext.servletContext.contextPath}/storeback/giftpackage/searchCouponList/${m.uuid}",
										"fnServerData" : retrieveSearchData, //获取数据的处理函数  
									};

									if ($(this).hasClass("dataTable-reorder")) {
										opt.sDom = "R" + opt.sDom;
									}

									oTable2 = $(this).dataTable(opt);

								});
			}
		}
		
		//刷新Datatable，会自动激发retrieveData  		
		if ('inittable' != operact) {
			oTable2.fnDraw();
		}
	}


function clearQueryCoupons() {
	$("#couponTypeName").val("");
}

function clearSearchCoupon() {
	$("#couponTypeName_s").val("");
}


//删除
function removes(delId) {
		var uuid = "${m.uuid}";
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
				$.getJSON(
			    	"${pageContext.servletContext.contextPath}/storeback/giftpackage/removeRelate",
			    	{"uuid":uuid,"selectOne":checkIds,ranNum:Math.random()},	
				    function(data) {	             
				       if(data=="success") {
				  			//刷新
								bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;
								doSearch('search') ;     	
				       }else{
				       		bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
				       }
				    }
				);		
        	}   
    });   
}

function saveChooseCoupon(){
		var uuid = "${m.uuid}";
		var checkIds = "" ;
		$("input[name='checkCoupon']:checkbox").each(function() {
			if ($(this).is(":checked")) {
				checkIds += $(this).val() + ",";
			}
		});
		var checkUrl = "${pageContext.servletContext.contextPath}/storeback/giftpackage/saveChooseCoupon" ;
		$.getJSON(checkUrl,{uuid:uuid,checkIds:checkIds,ranNum:Math.random()} ,function(data){
	  	if(data=="success"){
	  		bootbox.alert("<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>");
	  		doSearch('search') ;
	  	}else{
	  		bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
	  	}
	  });		
}

$(document).ready(function() {
	
	 //初始化表格
   doSearch('search') ;
   
   $("#queryCoupons").click(function(){
   		doSearch('search') ;
   });
      
   $("#searchCoupons").click(function(){
    	doSearchCoupon('search');
   });
   $("#searchCoupon").click(function(){
    	doSearchCoupon('search');
   });
   $("#chooseCouponIds").click(function(){
   		saveChooseCoupon();
   })
   
   $("#returnList").click(function(){
   		window.location.href="${pageContext.servletContext.contextPath}/storeback/giftpackage/toList";
   });
   
   //复选框全选
	 $("#check_all").click(function(e) {
	    $('input', oTable1.fnGetNodes()).prop('checked', this.checked);
	 });
	 //复选框全选
	 $("#checkCoupon_all").click(function(e) {
	    $('input', oTable2.fnGetNodes()).prop('checked', this.checked);
	 });       
		
}) 
</script>