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
				<h1><aebiz:showTitle titleId="customertemp.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle titleId="customertemp.menuOne"/></span><i class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle titleId="customertemp.menuTwo"/></span><i class="fa fa-angle-right"></i></li>							
				<li><span><aebiz:showTitle titleId="customertemp.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span></li>						
			</ul>				
		</div>				
							
		<div class="row">
				<div class="col-sm-12">
					<div class="box">
						<div class="box-content nopadding y_tableser">
							<div class="y_clear">
								<div class="form-inline table_formnew">
									<div  class="form-group">
										<label class="control-label"><aebiz:showTitle titleId="customertemp.qmf.customerName"/>：</label>
										<input type="text" name="customerName" id="customerName" class="form-control">
										<input type="hidden" name="customerName_q" id="customerName_q" class="form-control" value="Like">
									</div>
									
									<div class="form-group">
										<button class="btn btn-primary search" title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
										<button class="btn" onclick="javascript:clearSearch();" title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.clear"/></button>
										<button class="btn" id="searchshow" title="<aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/>" rel="tooltip" data-toggle="modal" data-target="#modal-user"><aebiz:showTitle titleId="basebusiness.showmessage.seniorQuery"/></button>
									</div>
								</div>
								
								<div class="y_tablebtn">
									<a class="btn" href="${pageContext.servletContext.contextPath}/sysback/customertemp/toAdd" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/></a>
									<button class="btn" onclick="javascript:removes('');" title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></button>
								</div>
							</div>
						
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th class='with-checkbox'><input type="checkbox" name="check_all" id="check_all"></th>
									<th><aebiz:showTitle titleId="customertemp.m.customerName"/></th>
									<th><aebiz:showTitle titleId="customertemp.m.mobile"/></th>
									<th><aebiz:showTitle titleId="customertemp.m.email"/></th>
									<th><aebiz:showTitle titleId="customertemp.m.nickName"/></th>
									<th><aebiz:showTitle titleId="customertemp.m.sex"/></th>
									<th><aebiz:showTitle titleId="customertemp.m.birthday"/></th>
									<th><aebiz:showTitle titleId="customertemp.m.hobby"/></th>
									<th><aebiz:showTitle titleId="customertemp.m.realName"/></th>
									<th><aebiz:showTitle titleId="customertemp.m.province"/></th>
									<th><aebiz:showTitle titleId="customertemp.m.city"/></th>
									<th><aebiz:showTitle titleId="customertemp.m.region"/></th>
									<th><aebiz:showTitle titleId="customertemp.m.address"/></th>
									<th><aebiz:showTitle titleId="customertemp.m.image"/></th>
									<th><aebiz:showTitle titleId="customertemp.m.marryState"/></th>
									<th><aebiz:showTitle titleId="customertemp.m.income"/></th>
									<th><aebiz:showTitle titleId="customertemp.m.certCode"/></th>
									<th><aebiz:showTitle titleId="customertemp.m.education"/></th>
									<th><aebiz:showTitle titleId="customertemp.m.industry"/></th>
									<th><aebiz:showTitle titleId="customertemp.m.zipCode"/></th>
									<th><aebiz:showTitle titleId="customertemp.m.certType"/></th>
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
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="customertemp.m.customerName"/></label>
					<input type="text" name="customerName_s" id="customerName_s" class="form-control">
					<input type="hidden" name="customerName_q" id="customerName_q" value="Like">
					</div>
					</div>
					
					<div class="col-sm-6">
					 <div class="form-group">
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="customertemp.m.mobile"/></label>
					<input type="text" name="mobile_s" id="mobile_s" class="form-control">
					<input type="hidden" name="mobile_q" id="mobile_q" value="EQ">
					</div>
					</div>
					
					<div class="col-sm-6">
					 <div class="form-group">
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="customertemp.m.email"/></label>
					<input type="text" name="email_s" id="email_s" class="form-control">
					<input type="hidden" name="email_q" id="email_q" value="EQ">
					</div>
					</div>
					</div>
					
					<div class="row">
					<div class="col-sm-6">
					 <div class="form-group">
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="customertemp.m.nickName"/></label>
					<input type="text" name="nickName_s" id="nickName_s" class="form-control">
					<input type="hidden" name="nickName_q" id="nickName_q" value="EQ">
					</div>
					</div>
					
					<div class="col-sm-6">
					 <div class="form-group">
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="customertemp.m.sex"/></label>
					<input type="text" name="sex_s" id="sex_s" class="form-control">
					<input type="hidden" name="sex_q" id="sex_q" value="EQ">
					</div>
					</div>
					
					<div class="col-sm-6">
					 <div class="form-group">
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="customertemp.m.birthday"/></label>
					<input type="text" name="birthday_s" id="birthday_s" class="form-control">
					<input type="hidden" name="birthday_q" id="birthday_q" value="EQ">
					</div>
					</div>
					</div>
					
					<div class="row">
					<div class="col-sm-6">
					 <div class="form-group">
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="customertemp.m.hobby"/></label>
					<input type="text" name="hobby_s" id="hobby_s" class="form-control">
					<input type="hidden" name="hobby_q" id="hobby_q" value="EQ">
					</div>
					</div>
					
					<div class="col-sm-6">
					 <div class="form-group">
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="customertemp.m.realName"/></label>
					<input type="text" name="realName_s" id="realName_s" class="form-control">
					<input type="hidden" name="realName_q" id="realName_q" value="EQ">
					</div>
					</div>
					
					<div class="col-sm-6">
					 <div class="form-group">
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="customertemp.m.province"/></label>
					<input type="text" name="province_s" id="province_s" class="form-control">
					<input type="hidden" name="province_q" id="province_q" value="EQ">
					</div>
					</div>
					</div>
					
					<div class="row">
					<div class="col-sm-6">
					 <div class="form-group">
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="customertemp.m.city"/></label>
					<input type="text" name="city_s" id="city_s" class="form-control">
					<input type="hidden" name="city_q" id="city_q" value="EQ">
					</div>
					</div>
					
					<div class="col-sm-6">
					 <div class="form-group">
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="customertemp.m.region"/></label>
					<input type="text" name="region_s" id="region_s" class="form-control">
					<input type="hidden" name="region_q" id="region_q" value="EQ">
					</div>
					</div>
					
					<div class="col-sm-6">
					 <div class="form-group">
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="customertemp.m.address"/></label>
					<input type="text" name="address_s" id="address_s" class="form-control">
					<input type="hidden" name="address_q" id="address_q" value="EQ">
					</div>
					</div>
					</div>
					
					<div class="row">
					<div class="col-sm-6">
					 <div class="form-group">
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="customertemp.m.image"/></label>
					<input type="text" name="image_s" id="image_s" class="form-control">
					<input type="hidden" name="image_q" id="image_q" value="EQ">
					</div>
					</div>
					
					<div class="col-sm-6">
					 <div class="form-group">
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="customertemp.m.marryState"/></label>
					<input type="text" name="marryState_s" id="marryState_s" class="form-control">
					<input type="hidden" name="marryState_q" id="marryState_q" value="EQ">
					</div>
					</div>
					
					<div class="col-sm-6">
					 <div class="form-group">
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="customertemp.m.income"/></label>
					<input type="text" name="income_s" id="income_s" class="form-control">
					<input type="hidden" name="income_q" id="income_q" value="EQ">
					</div>
					</div>
					</div>
					
					<div class="row">
					<div class="col-sm-6">
					 <div class="form-group">
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="customertemp.m.certCode"/></label>
					<input type="text" name="certCode_s" id="certCode_s" class="form-control">
					<input type="hidden" name="certCode_q" id="certCode_q" value="EQ">
					</div>
					</div>
					
					<div class="col-sm-6">
					 <div class="form-group">
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="customertemp.m.education"/></label>
					<input type="text" name="education_s" id="education_s" class="form-control">
					<input type="hidden" name="education_q" id="education_q" value="EQ">
					</div>
					</div>
					
					<div class="col-sm-6">
					 <div class="form-group">
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="customertemp.m.industry"/></label>
					<input type="text" name="industry_s" id="industry_s" class="form-control">
					<input type="hidden" name="industry_q" id="industry_q" value="EQ">
					</div>
					</div>
					</div>
					
					<div class="row">
					<div class="col-sm-6">
					 <div class="form-group">
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="customertemp.m.zipCode"/></label>
					<input type="text" name="zipCode_s" id="zipCode_s" class="form-control">
					<input type="hidden" name="zipCode_q" id="zipCode_q" value="EQ">
					</div>
					</div>
					
					<div class="col-sm-6">
					 <div class="form-group">
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="customertemp.m.certType"/></label>
					<input type="text" name="certType_s" id="certType_s" class="form-control">
					<input type="hidden" name="certType_q" id="certType_q" value="EQ">
					</div>
					</div>
					</div>
					
					<div class="row">
					<div class="col-sm-6">
					 <div class="form-group">
					<label for="textfield" class="control-label"><aebiz:showTitle titleId="customertemp.qm.createTime2"/></label>
					<input type="text" name="createTime2_s" id="createTime2_s" class="form-control">
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
	
	
</body>

</html>

<script>
function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
	searchParam.push({ "name": "customerName_s", "value": $.trim($("#customerName_s").val())});
	searchParam.push({ "name": "customerName_q", "value": $.trim($("#customerName_q").val())});
	searchParam.push({ "name": "mobile_s", "value": $.trim($("#mobile_s").val())});
	searchParam.push({ "name": "mobile_q", "value": $.trim($("#mobile_q").val())});
	searchParam.push({ "name": "email_s", "value": $.trim($("#email_s").val())});
	searchParam.push({ "name": "email_q", "value": $.trim($("#email_q").val())});
	searchParam.push({ "name": "nickName_s", "value": $.trim($("#nickName_s").val())});
	searchParam.push({ "name": "nickName_q", "value": $.trim($("#nickName_q").val())});
	searchParam.push({ "name": "sex_s", "value": $.trim($("#sex_s").val())});
	searchParam.push({ "name": "sex_q", "value": $.trim($("#sex_q").val())});
	searchParam.push({ "name": "birthday_s", "value": $.trim($("#birthday_s").val())});
	searchParam.push({ "name": "birthday_q", "value": $.trim($("#birthday_q").val())});
	searchParam.push({ "name": "hobby_s", "value": $.trim($("#hobby_s").val())});
	searchParam.push({ "name": "hobby_q", "value": $.trim($("#hobby_q").val())});
	searchParam.push({ "name": "realName_s", "value": $.trim($("#realName_s").val())});
	searchParam.push({ "name": "realName_q", "value": $.trim($("#realName_q").val())});
	searchParam.push({ "name": "province_s", "value": $.trim($("#province_s").val())});
	searchParam.push({ "name": "province_q", "value": $.trim($("#province_q").val())});
	searchParam.push({ "name": "city_s", "value": $.trim($("#city_s").val())});
	searchParam.push({ "name": "city_q", "value": $.trim($("#city_q").val())});
	searchParam.push({ "name": "region_s", "value": $.trim($("#region_s").val())});
	searchParam.push({ "name": "region_q", "value": $.trim($("#region_q").val())});
	searchParam.push({ "name": "address_s", "value": $.trim($("#address_s").val())});
	searchParam.push({ "name": "address_q", "value": $.trim($("#address_q").val())});
	searchParam.push({ "name": "image_s", "value": $.trim($("#image_s").val())});
	searchParam.push({ "name": "image_q", "value": $.trim($("#image_q").val())});
	searchParam.push({ "name": "marryState_s", "value": $.trim($("#marryState_s").val())});
	searchParam.push({ "name": "marryState_q", "value": $.trim($("#marryState_q").val())});
	searchParam.push({ "name": "income_s", "value": $.trim($("#income_s").val())});
	searchParam.push({ "name": "income_q", "value": $.trim($("#income_q").val())});
	searchParam.push({ "name": "certCode_s", "value": $.trim($("#certCode_s").val())});
	searchParam.push({ "name": "certCode_q", "value": $.trim($("#certCode_q").val())});
	searchParam.push({ "name": "education_s", "value": $.trim($("#education_s").val())});
	searchParam.push({ "name": "education_q", "value": $.trim($("#education_q").val())});
	searchParam.push({ "name": "industry_s", "value": $.trim($("#industry_s").val())});
	searchParam.push({ "name": "industry_q", "value": $.trim($("#industry_q").val())});
	searchParam.push({ "name": "zipCode_s", "value": $.trim($("#zipCode_s").val())});
	searchParam.push({ "name": "zipCode_q", "value": $.trim($("#zipCode_q").val())});
	searchParam.push({ "name": "certType_s", "value": $.trim($("#certType_s").val())});
	searchParam.push({ "name": "certType_q", "value": $.trim($("#certType_q").val())});
	searchParam.push({ "name": "createTime2_s", "value": $.trim($("#createTime2_s").val())});
	searchParam.push({ "name": "createTime2_q", "value": $.trim($("#createTime2_q").val())});
	searchParam.push({ "name": "customerName", "value": $.trim($("#customerName").val())});
			
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
										{"mDataProp":"customerName" ,"sTitle": "<aebiz:showTitle titleId="customertemp.m.customerName"/>"},
										{"mDataProp":"mobile" ,"sTitle": "<aebiz:showTitle titleId="customertemp.m.mobile"/>"},
										{"mDataProp":"email" ,"sTitle": "<aebiz:showTitle titleId="customertemp.m.email"/>"},
										{"mDataProp":"nickName" ,"sTitle": "<aebiz:showTitle titleId="customertemp.m.nickName"/>"},
										{"mDataProp":"sex" ,"sTitle": "<aebiz:showTitle titleId="customertemp.m.sex"/>"},
										{"mDataProp":"birthday" ,"sTitle": "<aebiz:showTitle titleId="customertemp.m.birthday"/>"},
										{"mDataProp":"hobby" ,"sTitle": "<aebiz:showTitle titleId="customertemp.m.hobby"/>"},
										{"mDataProp":"realName" ,"sTitle": "<aebiz:showTitle titleId="customertemp.m.realName"/>"},
										{"mDataProp":"province" ,"sTitle": "<aebiz:showTitle titleId="customertemp.m.province"/>"},
										{"mDataProp":"city" ,"sTitle": "<aebiz:showTitle titleId="customertemp.m.city"/>"},
										{"mDataProp":"region" ,"sTitle": "<aebiz:showTitle titleId="customertemp.m.region"/>"},
										{"mDataProp":"address" ,"sTitle": "<aebiz:showTitle titleId="customertemp.m.address"/>"},
										{"mDataProp":"image" ,"sTitle": "<aebiz:showTitle titleId="customertemp.m.image"/>"},
										{"mDataProp":"marryState" ,"sTitle": "<aebiz:showTitle titleId="customertemp.m.marryState"/>"},
										{"mDataProp":"income" ,"sTitle": "<aebiz:showTitle titleId="customertemp.m.income"/>"},
										{"mDataProp":"certCode" ,"sTitle": "<aebiz:showTitle titleId="customertemp.m.certCode"/>"},
										{"mDataProp":"education" ,"sTitle": "<aebiz:showTitle titleId="customertemp.m.education"/>"},
										{"mDataProp":"industry" ,"sTitle": "<aebiz:showTitle titleId="customertemp.m.industry"/>"},
										{"mDataProp":"zipCode" ,"sTitle": "<aebiz:showTitle titleId="customertemp.m.zipCode"/>"},
										{"mDataProp":"certType" ,"sTitle": "<aebiz:showTitle titleId="customertemp.m.certType"/>"},
		

										  {
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 	"fnRender": function(obj) {	
											  		var sReturn = "<a href='${pageContext.servletContext.contextPath}/sysback/customertemp/toUpdate/"+obj.aData.uuid+"' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></a>" ;
														sReturn += "<a href='javascript:removes(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>'><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></a>" ;															
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
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/customertemp/queryList" ,
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
	$("#customerName").val("") ;
	
}

//清空高级搜索的所有选项
function clearMoreSearch() {
	$("#customerName_s").val("") ;
	$("#mobile_s").val("") ;
	$("#email_s").val("") ;
	$("#nickName_s").val("") ;
	$("#sex_s").val("") ;
	$("#birthday_s").val("") ;
	$("#hobby_s").val("") ;
	$("#realName_s").val("") ;
	$("#province_s").val("") ;
	$("#city_s").val("") ;
	$("#region_s").val("") ;
	$("#address_s").val("") ;
	$("#image_s").val("") ;
	$("#marryState_s").val("") ;
	$("#income_s").val("") ;
	$("#certCode_s").val("") ;
	$("#education_s").val("") ;
	$("#industry_s").val("") ;
	$("#zipCode_s").val("") ;
	$("#certType_s").val("") ;
	$("#createTime2_s").val("") ;
	
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
			    	"${pageContext.servletContext.contextPath}/sysback/customertemp/deletes",
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