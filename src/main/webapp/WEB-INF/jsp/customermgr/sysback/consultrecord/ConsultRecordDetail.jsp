<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>
<!--设置浮动按钮-->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.buttonfloat.js"></script>

</head>

<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>图文咨询详情</h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li><span>服务审核</span><i class="fa fa-angle-right"></i></li>
				<li><span>图文咨询管理</span><i class="fa fa-angle-right"></i></li>
				<li><span>图文咨询待审核</span><i class="fa fa-angle-right"></i></li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">	
				<div class="form-horizontal form-validate form-bordered">
					
					<!--患者基本信息-->	
					<div class="form-group">
						<div class="col-sm-12">
							<i class="fa fa-user"></i>患者基本信息
						</div>
					</div>
					<table class="table table-bordered mb_0">
		      <tbody>
							<!--<tr>
								<th width="15%">患者ID</th>
								<td width="85%">${customerInfoModel.customerUuid}</td>
							</tr>-->
							<tr>	
								<th width="15%" >用户名</th>
								<td width="85%">
									${m.customerName}
								</td>
							</tr>
							<tr>
								<th>姓名</th>
								<td>
									${customerInfoModel.realName}
								</td>
							</tr>
							<tr>
								<th>性别</th>
								<td>
									<c:if test="${customerInfoModel.sex==1}">
		   		         男
		   	          </c:if>
		            	<c:if test="${customerInfoModel.sex==2}">
		   	          女
		            	</c:if>
								</td>
							</tr>
							<tr>
								<th>出生日期</th>
								<td>
								 	${customerInfoModel.birthday}
								</td>
							</tr>
							<tr>
								<th>手机号</th>
								<td>
									${m.customerMobile}
								</td>
							</tr>
						</tbody>
					</table>	
					
					<!--医生基本信息-->	
					<div class="form-group">
						<div class="col-sm-12">
							<i class="fa fa-lock"></i>医生基本信息
							<a class="btn btn btn-primary" onclick="toAddServicestaff()"  title="绑定医生" rel="tooltip" data-toggle="modal" data-target="#modal-push" style="margin-left:20px;">绑定医生</a>
						</div>
					</div>
					<table class="table table-bordered mb_0">
			      <tbody>
								<tr>	
									<th  width="15%">姓名</th>
									<td width="85%">
										${servicestaffModel.realName}
									</td>
								</tr>
								<tr>
									<th>医院</th>
									<td>
									${servicestaffModel.hospitalName}
									</td>
								</tr>
									<tr>
									<th>科室</th>
									<td>
									${servicestaffModel.departmentName}
									</td>
								</tr>
								
								<tr>
									<th>联系方式</th>
									<td>
									${m.docoterMobile}
									</td>
								</tr>
								
								<tr>
									<th>擅长</th>
									<td>
									${servicestaffModel.serviceStaffTerritory}
									</td>
								</tr>
								<tr>
									<th>标签</th>
									<td>
											<c:forEach items="${servicestaffModel.doctorTags}" var="tag">
												 	<a href="javascript:;" class="btn " > ${tag.tagName}</a>
											</c:forEach>
									</td>
								</tr>
						</tbody>
					</table>
					
					<!--咨询内容-->	
					<div class="form-group">
						<div class="col-sm-12">
							<i class="fa fa-lock"></i>咨询内容
						</div>
					</div>
					<table class="table table-bordered mb_0">
			      <tbody>
			      	<c:forEach items="${list}" var="list">
			      		<c:if test="${!empty list.customerUuid}">
								<tr>
									<th width="15%">患者咨询内容</th>
									<td width="85%">${list.content} ${list.picUrl} </td>
								</tr>
								</c:if>
								
								<c:if test="${!empty list.doctorUuid}">
								<tr>
									<th width="15%">医生回复内容</th>
									<td width="85%">${list.content} ${list.picUrl} </td>
								</tr>
								</c:if>
								
								</c:forEach>
							
							
								<tr>
									<th width="15%">标签</th>
									<td width="85%">
										<c:forEach items="${tagList}" var="tag">
											<input type="hidden" name="tagname" >
									 		<a href="javascript:deleteTag('${m.uuid}','${tag.uuid}')" class="btn " > ${tag.tagName} <i class="del">x</i></a>
										</c:forEach>
										<a href="#" class="btn btn-primary a_size_1"  data-toggle="modal" data-target="#RemarkRefoud1">新增标签</a>
									</td>
								</tr>
						</tbody>
					</table>
					
				</div>
			</div>
		</div>
		
		<div class="y_fixedbtn">
		  <c:if test="${m.state==0}">	            	
				<button id="checkPass" type="submit" class="btn btn-primary btn-large a_size_1">通过</button>
				<button  data-toggle="modal" data-target="#RemarkRefoud" type="submit" class="btn btn-default  btn-large a_size_1">未通过</button>
			
			</c:if>
			<button type="button" class="btn cancel btn-large a_size_1">返回</button>
		</div>	
		
	</div>
	
<!-订单审核不通过备注 end-->
<div id="RemarkRefoud" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="user-infos">拒绝原因</h3>
			</div>
			<div class="modal-body">
				<div class="box">	
					<textarea name="note" id="note" class="form-control" rows="5"></textarea>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn moresearch" data-dismiss="modal">关闭</button>
				<button id="checkUnPass" class="btn btn-primary" data-dismiss="modal">提交</button>
			</div>
		</div>
	</div>
</div>
<!--订单审核不通过 end-->
<!--绑定医生弹出框-->
<div id="modal-push" class="modal fade">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-body">
				<div id="pushFamousServicestaff" ></div>
			</div>													
		</div>
	</div>
</div>

<!--标签库 end-->
<div id="RemarkRefoud1" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3 id="user-infos">标签库</h3>
			</div>
			<div class="modal-body">
				<c:forEach items="${tags}" var="tag">
						<input type="button"  id="${tag.uuid}" class="btn" value="${tag.tagName}">
				</c:forEach>
				<input type="hidden"  id="tagId"  value="">
			</div>
			
			<div class="modal-footer">
				<button class="btn moresearch" data-dismiss="modal">关闭</button>
				<button onclick="javascript:addTag('${m.uuid}');" class="btn btn-primary" data-dismiss="modal">提交</button>
			</div>
		</div>
	</div>
</div>
<!--标签库 end-->
	
</body>
</html>
<script>
    $(document).ready(function() {
    	$("#checkPass").click(function(){
    			$.get("${pageContext.servletContext.contextPath}/sysback/consultrecord/updateState/${m.uuid}",
			    	{"state":"1",refuseReason:"",ranNum:Math.random()},	
				    function(data) {  
			    	   var result = eval("("+data+")") ;	       
					   if(result.rsp) {
				       		alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;
				       		location.href="${pageContext.servletContext.contextPath}/sysback/consultrecord/toOlineList";	
				       }else{
				       		alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
				       }
				    }
				);
    	});
    	
    	$("#checkUnPass").click(function(){
    		var note = $("#note").val();
    		if(note ==''){
    			alert("请填写审核不通过原因");
    			return;
				}
  			$.get("${pageContext.servletContext.contextPath}/sysback/consultrecord/updateState/${m.uuid}",
		    	{"state":"2",refuseReason:note,ranNum:Math.random()},	
			    function(data) {    
		    		var result = eval("("+data+")") ;	       
					if(result.rsp) {
			       		alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;   	
			       		location.href="${pageContext.servletContext.contextPath}/sysback/consultrecord/toOlineList";
			       }else{
			       		alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;
			       }
			    }
				);
    	});
    	
			$(".cancel").click(function(){
				history.go(-1) ;
			});	    	
    });
    
    
    
     $(".modal-body input").click(function(){
		  	var tagUuids="";
		  	var alength =0;
		  	$(this).toggleClass("btn-primary");
		  	$(this).parent().find('.btn-primary').each(function(){
		  		var _btnl = $(this).parent().find('.btn-primary').length;
		  		var _tagl = $("input[name='tagname']").length;
		  		alength = _btnl + _tagl;
		  		var tagUuid = $(this).attr('id');
		  		tagUuids = tagUuid+";"+tagUuids
		  	});
		    if(alength>5){
		  			alert("最多拥有5个标签");
		  			return;
		  	}
		  	$("#tagId").val(tagUuids);
		  	
		});	
    
    //跳转到绑定医生页面
		function toAddServicestaff(){
			var url="${pageContext.servletContext.contextPath}/sysback/servicestaff/toBindServicestaffList";
				$.post(url,
				{  
					"consultUuid":"${m.uuid}",
					ranNum : Math.random()
				},
				function(data){
						$("#pushFamousServicestaff").html(data);
				}
			);
		}
		
		  function deleteTag(uuid ,tagUuid){
    		$.get("${pageContext.servletContext.contextPath}/sysback/consultrecord/toDeleteTag",
				{
					"uuid":uuid,
					"tagUuid":tagUuid,
					ranNum : Math.random()
				},
				function(data) {
					if (data == "success") {
						//刷新 当前页面
						location.reload();
					} else {
						bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
					}
				});
    }
    
       
        function addTag(uuid){
    	  var tagId = $("#tagId").val();
    		$.get("${pageContext.servletContext.contextPath}/sysback/consultrecord/addTag",
				{
					"uuid":uuid,
					"tagId":tagId,
					ranNum : Math.random()
				},
				function(data) {
					if (data == "success") {
						//刷新 当前页面
						location.reload();
					} else {
						bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
					}					})
    }
		
		
    
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>