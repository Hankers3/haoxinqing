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
				<h1><aebiz:showTitle titleId="trainstaff.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="trainstaff.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="trainstaff.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span><aebiz:showTitle titleId="trainstaff.moduleName_CN"/><aebiz:showTitle titleId="basebusiness.showmessage.manager"/></span>
				</li>						
			</ul>				
		</div>				
						
						
	
				
		<div class="row">
				<div class="col-sm-12">
					<div class="box">
						<div class="box-content nopadding y_tableser">
				
						
						<input type="hidden" name="trainPlanUuid" id="trainPlanUuid" value="${tm.uuid}">
						<input type="hidden" name="trainPlanUuid_q" id="trainPlanUuid_q"  value="EQ">

							<table class="table table-bordered table-hover table-striped dataTable-reorder">
								<thead>		
									<tr>
										<th>培训计划编号</th>
										<th>培训时间</th>
										<th>培训课程</th>
										<th>培训地点</th>
										<th>主讲人</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${tm.trainPlanNo}</td>
										<td>${tm.trainStartTime}-${tm.trainEndTime}</td>
										<td>${tm.syllabusName}</td>
										<td>${tm.trainAddress}</td>
										<td>${tm.managerName}</td>
									</tr>
								</tbody>
							</table>
			
							<div class="y_clear">
								<div class="y_tablebtn">
									<!--
									<a class="btn  btn-primary" href="${pageContext.servletContext.contextPath}/syllabus/toAdd" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip">批量合格</a>
									<a class="btn" href="${pageContext.servletContext.contextPath}/syllabus/toAdd" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip">批量不合格</a>
									-->
									<button class="btn  btn-primary" onclick="javascript:bathUpdatePosion('');" title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>" rel="tooltip">批量保存</button>
								</div>
							</div>
						
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th class='with-checkbox'>
										<input type="checkbox" name="check_all" id="check_all">
									</th>
									<th><aebiz:showTitle titleId="trainstaff.m.serviceStaffUuid"/></th>
									<th><aebiz:showTitle titleId="trainstaff.m.score"/></th>
									<th><aebiz:showTitle titleId="trainstaff.m.state"/></th>
									<!--
									<th class='hidden-480'><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
									-->
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
	searchParam.push({ "name": "trainPlanUuid", "value": $.trim($("#trainPlanUuid").val())});
	searchParam.push({ "name": "trainPlanUuid_q", "value": $.trim($("#trainPlanUuid_q").val())});
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
											{"mDataProp":"staffName" ,"sDefaultContent":"", "sTitle": "<aebiz:showTitle titleId="trainstaff.m.serviceStaffUuid"/>"},
											{
												"mDataProp":"score" ,
												"sDefaultContent":"", 
												"sTitle": "<aebiz:showTitle titleId="trainstaff.m.score"/>",
												"fnRender" : function(obj) {
														var sReturn = "<input type='text' id='position_"+obj.aData.uuid+"' name='check' value='"+obj.aData.score+"' class='form-control'/>";
														return sReturn;
												}
											},
											{
												"mDataProp":"state" ,
												"sDefaultContent":"", 
												"sTitle": "<aebiz:showTitle titleId="trainstaff.m.state"/>",
												"fnRender" : function(obj) {
														var sReturn = "";
														if(obj.aData.state=='0'){
															sReturn+="不合格";
														}else{
															sReturn+="合格";
														}
														return sReturn;
												}
											},
										  /*{
										  	"mDataProp":"operate",
										  	"sDefaultContent":"",
										  	"sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>" ,
										 		"fnRender": function(obj) {	
											  		var sReturn = "<a href='javascript:void(0)' onclick='updateState(\""+obj.aData.uuid+"\");' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.edit"/>'>置合格</a>" ;
											      return sReturn;
											 }			
										  }*/
										],
				            'oTableTools': {
				                "sSwfPath": "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
				            },
				            "bRetrieve":true ,
				            "bProcessing":true,
				           	"bServerSide":true,                    //指定从服务器端获取数据
				            "sAjaxSource":"${pageContext.servletContext.contextPath}/sysback/trainstaff/querySList" ,
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
	$("#trainPlanUuid").val("") ;
	
}

//清空高级搜索的所有选项
function clearMoreSearch() {
	$("#trainPlanUuid_s").val("") ;
	$("#serviceStaffUuid_s").val("") ;
	$("#score_s").val("") ;
	$("#state_s").val("") ;
	$("#_s").val("") ;
	
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
			    	"${pageContext.servletContext.contextPath}/sysback/trainstaff/deletes",
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


//
function updateState(uuId) {
		var checkIds="";
		var planUuid="${tm.uuid}";
		if( uuId.trim() != "") {
			checkIds = uuId ;
		}else{
			$("input[name='check']:checkbox").each(function(){				
        if($(this).is(":checked")){        	
            checkIds += $(this).val()+"," ;  
        }
    	})	
		}
		
		if(checkIds.trim() == "") {
				//提示为空
				bootbox.alert("请选择操作的数据") ;
				return ;
		} 
		
		bootbox.confirm("确定操作选择的数据", function(r){
        	if(r) {
						$.post(
					    	"${pageContext.servletContext.contextPath}/sysback/trainstaff/updateStates",
					    	{
					    		"selectOne":checkIds,
					    		"planUuid":planUuid,
					    		ranNum:Math.random()},	
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





//批量修改分数
	function bathUpdatePosion(delId) {
		var checkIds = "";
		var planUuid="${tm.uuid}";
		$("input[name='check']:checkbox").each(function() {
			if ($(this).is(":checked")) {
				var positon =  "position_"+$(this).val() ;
				var postion_value = $("#"+positon).val();
				checkIds += $(this).val() +"-"+postion_value +",";
			}
		})
		if (checkIds.trim() == "") {
			//提示为空
			bootbox.alert('请选择操作的数据');
			return;
		}
		bootbox.confirm("确定操作选择的数据",function(r) {
			if (r) {
				//删除
				$.post(
						"${pageContext.servletContext.contextPath}/sysback/trainstaff/updatePosition",
						{
							"selectOne" : checkIds,
							"planUuid":planUuid,
							ranNum : Math.random()
						},
						function(data) {
							if (data=="success") {
								//刷新
								bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;
								doSearch('search');
							} else {
								bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
							}
						});
				}
		});
	}
</script>