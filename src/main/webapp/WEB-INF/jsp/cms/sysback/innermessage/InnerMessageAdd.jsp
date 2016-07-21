<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>


</head>

<body>
	
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>发送消息</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="innermessage.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="innermessage.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>发送消息</span>
				</li>
			</ul>
		</div>		
		
		
		
							
							
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/innermessage/doAdd" method="post" class='form-horizontal form-bordered form-validate' enctype="multipart/form-data">						
					<input type="hidden" name="sendUser" value="平台">
					<input type="hidden" name="messageType" value="0">
					<input type="hidden" name="readStatus" value="0">
					<input type="hidden" name="sendStatus" value="0">
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="innermessage.m.title"/></label>
						<div class="col-sm-10">
							<input type="text" name="title" class="form-control" data-rule-required="true">
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="innermessage.m.accountType"/></label>
						<div class="col-sm-10">
								<!-- <div class="col-sm-1">
									<div class="check-line">
										<input type="radio" id="c5" name="accountType" class='icheck-me' data-skin="square" data-color="blue" value="0"/>
										<label for="c5">所有</label>
									</div>
								</div> -->
								
								<div class="col-sm-1">
									<div class="check-line">
										<input type="radio" id="c6" name="accountType" class='icheck-me' data-skin="square" data-color="blue" value="1" checked onclick="doctor();"/>
										<label for="c6">医生</label>
									</div>
								</div>
								<div class="col-sm-1">
									<div class="check-line">
										<input type="radio" id="c7" name="accountType" class='icheck-me' data-skin="square" data-color="blue" value="2" onclick="customer();"/>
										<label for="c7">患者</label>
									</div>
								</div>
							</div>
					</div>
					
					
					<div class="form-group" id='doctor' style="display: block;" >
						<label for="textfield" class="control-label col-sm-2">收件人ID</label>
						<div class="col-sm-10">
							<!--
							<input type="text" id="receiveUser" name="receiveUser" class='form-control'/>
							-->
							<a class="btn" id='searchshowDoc' rel="tooltip" data-toggle="modal" data-target="#modal-select"  title="选择医生" rel="tooltip">选择医生</a>
						</div>
					</div>
								
					<div class="form-group"  id='customer'  style="display: none;">
						<label for="textfield" class="control-label col-sm-2">收件人ID</label>
						<div class="col-sm-10">
							<!--
							<input type="text" id="receiveUser" name="receiveUser" class='form-control'/>
							-->
							<a class="btn" id='searchshowCus' rel="tooltip" data-toggle="modal" data-target="#modal-selectCus"  title="选择患者" rel="tooltip">选择患者</a>
						</div>
					</div>
					

					<!--
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="innermessage.m.image"/></label>
						<div class="col-sm-10">
							<div class="fileinput fileinput-new" data-provides="fileinput">
								<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 100px; height: 100px;"></div>
								<div>
									<span class="btn btn-default btn-file">
										<span class="fileinput-new">Select image</span>
										<span class="fileinput-exists">Change</span>
										<input type="file" name="files">
									</span>
									<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
								</div>
							</div>
						</div>
					</div>
					-->
					
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="innermessage.m.content"/></label>
						<div class="col-sm-10">
							<textarea name="content" rows="5" cols="20"  id="content"  class="form-control" data-rule-required="true"  data-rule-maxlength="2000" maxlength='2000'></textarea>
						</div>
					</div>
					
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.save"/>'>
						<!--
						<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
							-->
					</div>						
				</form>
			</div>
		</div>
	</div>
</body>





<!-- 医生选择框 -->
	<div id="modal-select" class="modal fade y_highserch">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 id="user-infos">
						选择医生
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>
								<tr>
									<th class='with-checkbox'><input type="checkbox" name="check_all" id="check_all" ></th>
									<th><aebiz:showTitle titleId="quizResultDocRel.m.serviceStaffInfo.name" /></th>
									<th><aebiz:showTitle titleId="quizResultDocRel.m.serviceStaffInfo.professional" /></th>
									<th><aebiz:showTitle titleId="quizResultDocRel.m.serviceStaffInfo.territory" /></th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
				
				<div class="modal-footer">
					<button class="btn moresearch btn-primary" data-dismiss="modal" id="chooseDoc">
						<aebiz:showTitle titleId="basebusiness.showmessage.confirm" />
					</button>
					<button class="btn" data-dismiss="modal" aria-hidden="true">
						<aebiz:showTitle titleId="basebusiness.showmessage.cancel" />
					</button>
				</div>
			</div>
		</div>
	</div>
<!-- 医生选择框 -->

<!-- 患者选择框 -->
	<div id="modal-selectCus" class="modal fade y_highserch">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 id="user-infos">
						选择患者
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable1 dataTable-reorder">
							<thead>
								<tr>
									<th class='with-checkbox'><input type="checkbox" name="check_all" id="check_all" ></th>
									<th>患者ID</th>
									<th>患者名</th>
									<th>患者电话号</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
				
				<div class="modal-footer">
					<button class="btn moresearch btn-primary" data-dismiss="modal" id="chooseCus">
						<aebiz:showTitle titleId="basebusiness.showmessage.confirm" />
					</button>
					<button class="btn" data-dismiss="modal" aria-hidden="true">
						<aebiz:showTitle titleId="basebusiness.showmessage.cancel" />
					</button>
				</div>
			</div>
		</div>
	</div>
<!-- 患者选择框 -->



</html>


<script>
	      
  //选择医生
$("#chooseDoc").click(function(e) {
	var checkIds = "" ;
	$("input[name='check']:checkbox").each(function() {
		if ($(this).is(":checked")) {
			checkIds += $(this).val() + ",";
		}
	});
	// 先删除doctorUuids的标签
	
	// 添加标签
	$("#mainForm").append("<input type='hidden' name='doctorUuids' value='"+checkIds+"'>"); 
	

});

function retrieveData(sSource, aoData, fnCallback) {
	var searchParam = new Array();
	searchParam.push({"name":"sate", "value" : "1"});

	var docIds = "" ;	
	$("input[name='docIds']").each(function() {
		docIds += $(this).val() + ",";
	})

	$.ajax({
		"dataType" : 'json',
		"type" : "POST",
		"url" : sSource,
		"data" : {
			aoData : JSON.stringify(aoData),
			searchParam : JSON.stringify(searchParam),
			choosedIds:docIds
		},
		"success" : fnCallback
	});
}
    
//查询医生
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

					'sDom' : "rtlip",
					'aoColumnDefs' : [ {
						'bSortable' : false,
						'aTargets' : [ 0, 1 ]
					} ],
					"bSort" : true, //排序功能            
					"aoColumns" : [
						{
							"mDataProp" : "checkbox",
							"sDefaultContent" : "",
							"fnRender" : function(obj) {
								var sReturn = "<input type='checkbox' name='check' value='"+obj.aData.uuid+"'/>";
								return sReturn;
							}
						},
					  {"mDataProp":"realName" ,"sDefaultContent":"","sTitle": "医生姓名"},
						{
							"mDataProp" : "professional1",
							"sDefaultContent" : "",
							"fnRender": function(obj) {	
						  		var sReturn ="";
						  		if(obj.aData.professional==1){
						  			sReturn+="主任医师";
						  		}else if(obj.aData.professional==2){
						  			sReturn+="副主任医师";
						  		}else if(obj.aData.professional==3){
						  			sReturn+="主治医师";
						  		}else if(obj.aData.professional==4){
						  			sReturn+="住院医师";
						  		}else if(obj.aData.professional==5){
						  			sReturn+="助理医师";
						  		}else if(obj.aData.professional==6){
						  			sReturn+="实习医师";
						  		}else if(obj.aData.professional==7){
						  			sReturn+="护师（士）";
						  		}else if(obj.aData.professional==8){
						  			sReturn+="心理咨询师";
						  		}else if(obj.aData.professional==9){
						  			sReturn+="社工师";
						  		}else if(obj.aData.professional==10){
						  			sReturn+="药剂师";
						  		}else {
						  			sReturn+="";
						  		}
						      return sReturn;
							 }			
						},
						{"mDataProp":"serviceStaffTerritory" ,"sDefaultContent":""},
						],
					'oTableTools' : {
						"sSwfPath" : "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
					},
					"bRetrieve" : true,
					"bProcessing" : true,
					"bServerSide" : true, //指定从服务器端获取数据
					"sAjaxSource" : "${pageContext.servletContext.contextPath}/sysback/servicestaff/queryList",
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
		if ('inittable' == operact) {
			oTable.fnDraw();
		}
	}
	

	//点击添加，展示医生
	//选择题目
		$("#searchshowDoc").click(function(e) {		
			doSearch('inittable');
		});    
    
   	function doctor() {
   		$("#customer").hide();
   		$("#doctor").show();
   		//$("#customer").css('display','none'); 
   		//$("#doctor").css('display','block'); 
   	} 
   	     
</script>







<script>
	// 点击患者后，显示添加患者按钮
	function customer() {
   	   	$("#customer").show();
   			$("#doctor").hide();
	   		//$("#customer").css('display','none'); 
	   		//$("#doctor").css('display','block'); 
   	} 

	//点击添加，展示患者
	//选择题目
		$("#searchshowCus").click(function(e) {	
			// alert(1);	
			doSearch1('inittable1');
		}); 



	//查询患者
	var oTable = null;
	function doSearch1(operact) {	
	if(oTable == null) {		 
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
				            'sDom': "rtlip",
				            'aoColumnDefs': [{
				                'bSortable': false,
				                'aTargets': [0,1]                
				            }],
				            "bSort": true, //排序功能            
					         	"aoColumns": [	
										 	{
												"mDataProp" : "checkbox",
												"sDefaultContent" : "",
												"fnRender" : function(obj) {
													var sReturn = "<input type='checkbox' name='check' value='"+obj.aData.uuid+"'/>";
													return sReturn;
												}
											},						
											{"mDataProp":"customerId" ,	"sDefaultContent":"","sWidth":"10%","sTitle": "患者ID"},
										  {"mDataProp":"realName" ,"sDefaultContent":"","sWidth":"10%","sTitle": "患者姓名"},
											{"mDataProp":"mobile" ,"sDefaultContent":"","sWidth":"10%","sTitle": "<aebiz:showTitle titleId="customer.m.mobile"/>"}
										 ],
				            'oTableTools': {
				                "sSwfPath": "${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
				            },
				            "bRetrieve":true ,
				            "bProcessing":true,
				           	"bServerSide":true,                    //指定从服务器端获取数据
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/customer/queryList" ,
										"fnServerData":retrieveData1,           //获取数据的处理函数  
				        };
				        
                		if ($(this).hasClass("dataTable-reorder")) {
		                    opt.sDom = "R" + opt.sDom;
		                }
		                oTable = $(this).dataTable(opt);
        	});
   		}         
	}
		
	//刷新Datatable，会自动激发retrieveData  		
  	if('inittable1' != operact) {  			
		oTable.fnDraw(false); 	
	}  
}
		

function retrieveData1(sSource, aoData, fnCallback) {
	var searchParam = new Array();
	searchParam.push({"name":"sate", "value" : "1"});
	var docIds = "" ;	
	$("input[name='docIds']").each(function() {
		docIds += $(this).val() + ",";
	})

	$.ajax({
		"dataType" : 'json',
		"type" : "POST",
		"url" : sSource,
		"data" : {
			aoData : JSON.stringify(aoData),
			searchParam : JSON.stringify(searchParam),
			choosedIds:docIds
		},
		"success" : fnCallback
	});
}		
		
		
//选择患者
$("#chooseCus").click(function(e) {
	var checkIds = "" ;
	$("input[name='check']:checkbox").each(function() {
		if ($(this).is(":checked")) {
			checkIds += $(this).val() + ",";
		}
	});
	
	$("#mainForm").append("<input type='hidden' name='customerUuids' value='"+checkIds+"'>"); 
	

});		

</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>