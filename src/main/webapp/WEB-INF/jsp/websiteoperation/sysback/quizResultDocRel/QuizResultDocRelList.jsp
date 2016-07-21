<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include
	file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp"%>
<%-- <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.passwdcheck.js"></script>    --%>
</head>

<body>
	<div class="container-fluid">

		<div class="page-header">
			<div class="pull-left">
				<h1>
					<aebiz:showTitle titleId="quizResultDocRel.moduleName_CN" />
					<aebiz:showTitle titleId="basebusiness.showmessage.manager" />
				</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li><span><aebiz:showTitle
							titleId="quizResultDocRel.menuOne" /></span> <i
					class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle
							titleId="quizResultDocRel.menuTwo" /></span> <i
					class="fa fa-angle-right"></i></li>
				<li><span><aebiz:showTitle
							titleId="quizResultDocRel.moduleName_CN" />
						<aebiz:showTitle titleId="basebusiness.showmessage.manager" /></span></li>
			</ul>
		</div>


		<div class="row">
			<div class="col-sm-12">
				<div class="box">
					<div class="box-content nopadding y_tableser">
						<div class="y_clear">
							<div class="form-inline table_formnew">
								<!--审核状态 -->
								<input type="hidden" name="sate" id="sate" value="1"
									class="form-control"> <input type="hidden"
									name="sate_q" id="sate_q" value="EQ">
								<!--医生真实姓名 -->
								<div class="form-group">
									<label class="control-label">医生姓名：</label> <input type="text"
										name="realName" id="realName" class="form-control"> <input
										type="hidden" name="realName_q" id="realName_q"
										class="form-control" value="Like">
								</div>


								<div class="form-group">
									<button class="btn btn-primary search"
										title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>"
										rel="tooltip">
										<aebiz:showTitle titleId="basebusiness.showmessage.query" />
									</button>
									<button class="btn" onclick="javascript:clearSearch();"
										title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>"
										rel="tooltip">
										<aebiz:showTitle titleId="basebusiness.showmessage.clear" />
									</button>
								</div>
							</div>
							<div class="y_tablebtn">
								<!--<a class="btn" href="${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/toAddServicestaff" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/></a>
											-->
								<a class="btn"
									href="${pageContext.servletContext.contextPath}/sysback/servicestaffcomb/toAddserviceStaff"
									title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>"
									rel="tooltip"><aebiz:showTitle
										titleId="basebusiness.showmessage.newAdd" /></a>
								<%-- <button class="btn" onclick="javascript:removes('');" title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></button> --%>
							</div>
						</div>
						<table
							class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>
								<tr>

									<th><aebiz:showTitle
											titleId="quizResultDocRel.m.serviceStaffInfo.name" /></th>
									<th><aebiz:showTitle
											titleId="quizResultDocRel.m.serviceStaffInfo.professional" /></th>
									<th><aebiz:showTitle
											titleId="quizResultDocRel.m.serviceStaffInfo.territory" /></th>
									<th class='hidden-480'><aebiz:showTitle
											titleId="basebusiness.showmessage.operate" /></th>

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

		
</body>

</html>

<script>
function retrieveData(sSource,aoData,fnCallback) { 
	var searchParam = new Array() ;
	searchParam.push({ "name": "realName", "value": $.trim($("#realName").val())});
	searchParam.push({ "name": "sate", "value":$.trim($("#sate").val())});
	
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
		alert("liuyang  test");
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
				                'aTargets': [3]                
				            }],
				            "aaSorting": [[ 0, "desc" ]],
				            "bSort": true, //排序功能            
					         	"aoColumns": [
					         	     		{
												"mDataProp" : "realName",
												"sWidth" : "15%",
												"sDefaultContent" : "",
												"sTitle" : "医生姓名"
											},
											{
												"mDataProp" : "professional",
												"sWidth" : "18%",
												"sDefaultContent" : "",
												"sTitle" : "职称"
											},
											{
												"mDataProp" : "serviceStaffTerritory",
												"sWidth" : "18%",
												"sDefaultContent" : "",
												"sTitle" : "医学领域"
											},

											{
												"mDataProp" : "operate",
												"sWidth" : "18%",
												"sDefaultContent" : "",
												"sTitle" : "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>",
												"fnRender" : function(obj) {
													var sReturn = "<a href='javascript:removes(\""
															+ obj.aData.uuid
															+ "\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>'><i class='fa fa-trash-o'></i></a>";
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
		oTable.fnDraw(false); 	
	}  
	 	  
}

$(".search").click(function(){	
	doSearch('search') ;  
})



function clearSearch() {
	$("#realName").val("") ;

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

</script>