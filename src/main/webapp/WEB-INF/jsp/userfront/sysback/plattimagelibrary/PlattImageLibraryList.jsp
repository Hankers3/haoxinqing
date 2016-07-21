<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
	<!-- dynatree CSS -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/dynatree/ui.dynatree.css">	
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/jquery-ui/jquery.ui.core.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/jquery-ui/jquery.ui.widget.min.js"></script>
	<!-- dynatree -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/dynatree/jquery.dynatree.js"></script>
	<!-- 分类树结构的调用js -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.dynatree.js"></script>
	<!-- Colorbox -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/colorbox/jquery.colorbox-min.js"></script>
		<!-- 上传图片插件js -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>
		<!-- 上传图片插件js -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/ajaxfileupload.js"></script>
	<!--ie8及以下支持响应式的js-->
	<!--[if lte IE 8]>
    <script src="${pageContext.servletContext.contextPath}/static/sysback/js/respond.min.js"></script>
  <![endif]-->
	<!-- Favicon -->
	<link rel="shortcut icon" href="img/favicon.ico"/>
	<!-- Apple devices Homescreen icon -->
	<link rel="apple-touch-icon-precomposed" href="${pageContext.servletContext.contextPath}/static/sysback/img/apple-touch-icon-precomposed.png"/>
</head>
<body>

<div class="container-fluid" id="content">
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="plattimagelibrary.menuTwo"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
				<span><aebiz:showTitle titleId="plattimagelibrary.menuOne"/></span>
				<i class="fa fa-angle-right"></i>
				</li>
				<li>
				<span><aebiz:showTitle titleId="plattimagelibrary.menuTwo"/></span>
				<i class="fa fa-angle-right"></i>
				</li>
				<li>
				<span><aebiz:showTitle titleId="plattimagelibrary.menuTwo"/></span>
				</li>
			</ul>
		</div>
		
		<!--图片管理页面主体-->
		<div class="row y_classmage">
			<!--左侧文件夹目录-->
			<div class="col-sm-3 y_classtre w_tre1020">
				<div class="box">
					<div class="box-title"><h3><i class="fa fa-file"></i><aebiz:showTitle titleId="platimagecategory.moduleName_CN"/></h3></div>
					<div class="box-content">
					<input type="hidden" name="selectUuid" id="selectUuid" />	
						<button class="btn btn-primary" data-toggle="modal" data-target="#myModal" id="createRoot" ><i class="fa fa-folder-open"></i><aebiz:showTitle titleId="platimagecategory.m.addRoot"/></button>
						<button class="btn btn-primary" data-toggle="modal" data-target="#myModal" id="create"><i class="fa fa-folder-open"></i><aebiz:showTitle titleId="platimagecategory.m.add"/></button>
					  <button class="btn btn-deflaut dele" data-toggle="modal" data-target="#updateCategory" ><aebiz:showTitle titleId="basebusiness.showmessage.edit"/></button>
					  <button class="btn btn-deflaut dele"  id="deleteCategory"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></button>
						<div class="filetree">
							<div id="tree">
									  	
							</div>
						</div>
					</div>
				</div>
			</div><!-- end 左侧文件夹目录-->
			
			<div id="list">
			</div>
			
		</div>
	</div><!-- end 图片管理页面主体-->
</div>
	
	<!-- 新建文件夹模态框 start -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <h4 class="modal-title" id="myModalLabel"><aebiz:showTitle titleId="platimagecategory.m.add"/> </h4>
	      </div>
	      <div class="modal-body">
	        <input type="text" name="categoryName" id="categoryName" maxlength="10" class="form-control" />
	      </div>
	      <div class="modal-footer">
	      	<button type="button" class="btn btn-primary" id="saveCategory"  data-dismiss="modal"><aebiz:showTitle titleId="basebusiness.showmessage.confirm"/></button>
	        <button type="button" class="btn btn-default" data-dismiss="modal"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
	      </div>
	    </div>
	  </div>
	</div>
	<!-- 新建文件夹模态框 end -->
	
	<!-- 编辑分类 -->
	<div class="modal fade" id="updateCategory" tabindex="-1" role="dialog" aria-labelledby="myModalLabelUpdate" >
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <h4 class="modal-title" id="myModalLabelUpdate"><aebiz:showTitle titleId="platimagecategory.m.edit"/> </h4>
	      </div>
	      <div class="modal-body">
	        <input type="text" name="categoryName2" id="categoryName2" maxlength="10" class="form-control" />
	      </div>
	      <div class="modal-footer">
	      	<button type="button" class="btn updateCategory btn-primary"  data-dismiss="modal"><aebiz:showTitle titleId="basebusiness.showmessage.confirm"/></button>
	        <button type="button" class="btn btn-default" data-dismiss="modal"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
	      </div>
	    </div>
	  </div>
	</div>
	<input type="hidden" name="categoryUuid" id="categoryUuid" />	
	<script>
		
	/*新建跟分类时需要把id置为空*/
	$("#createRoot").click(function(){
		$("#selectUuid").val("");
	});
	
	$("#create").click(function(){
		$("#categoryName").val("");
	});
	
		$("#saveCategory").click(function(){
			var selectUuid=$.trim($("#selectUuid").val());
			var categoryName =$.trim($("#categoryName").val());
			if(categoryName == null || categoryName == ""){
				bootbox.alert("<aebiz:showTitle titleId='platimagecategory.m.enterName'/>");
				return;
			}
			$.getJSON("${pageContext.servletContext.contextPath}/sysback/platimagecategory/save",
					{"categoryName":categoryName,"parentUuid":selectUuid},
					function(data) {
						if (data.rsp) {
							//刷新
							//bootbox.alert('添加成功');
							$("#myModal").modal('hide');
							
							 var tree=$("#tree").dynatree("getTree");
							 tree.reload();
							/*  if(selectUuid == ""){
								 tree.reload();
							 }else{
							 var dtnode=tree.getNodeByKey(selectUuid);
							 if(dtnode==null){
								 tree.reload();
							 }else{
								dtnode.appendAjax({
									type: 'GET',
									url: "${pageContext.servletContext.contextPath}/sysback/productimagecategory/getNodes",
									data: {key:selectUuid,time:Math.random()},
									dataType: "json",
									contentType: 'application/json; charset=utf-8'
								});
							 }
							//window.location.href="${pageContext.servletContext.contextPath}/sysback/plattimagelibrary/toList";
							 } */
						} else {
							bootbox.alert("<aebiz:showTitle titleId='platimagecategory.m.saveFail'/>");
							$("myModal").modal('hide');
						}
			});
		
		});
		
		//编辑分类
		$(".updateCategory").click(function(){
			var selectUuid=$.trim($("#selectUuid").val());
			var categoryName =$("#categoryName2").val();
			if(categoryName == null || categoryName == ""){
				bootbox.alert("<aebiz:showTitle titleId='platimagecategory.m.enterName'/>");
				return;
			}
			$.getJSON("${pageContext.servletContext.contextPath}/sysback/platimagecategory/updateCategory",
				{"categoryName":categoryName,"selectUuid":selectUuid},
				function(data) {
					if (data.rsp) {
						//刷新
						//bootbox.alert('修改成功');
						$("#updateCategory").modal('hide');

						 var tree=$("#tree").dynatree("getTree");
						 var dtnode=tree.getNodeByKey(selectUuid);
						 dtnode.setTitle(categoryName);
						 //tree.reload();
						 dtnode.activate();
						 /**
						 alert(selectUuid);
						   if(selectUuid == ""){
								 tree.reload();
							 }else{
							 var dtnode=tree.getNodeByKey(selectUuid);
							 if(dtnode==null){
								 tree.reload();
							 }else{
								dtnode.appendAjax({
									type: 'GET',
									url: "${pageContext.servletContext.contextPath}/sysback/productimagecategory/getNodes",
									data: {key:selectUuid,time:Math.random()},
									dataType: "json",
									contentType: 'application/json; charset=utf-8'
								});
							 }
							 } 
						//window.location.href="${pageContext.servletContext.contextPath}/sysback/plattimagelibrary/toList";
						**/
					} else {
						bootbox.alert('<aebiz:showTitle titleId='platimagecategory.m.saveFail'/>');
						$("#updateCategory").modal('hide');
					}
			});
			
			
		
		});
		
		
		$("#deleteCategory").click(function(){
			var selectUuid=$.trim($("#selectUuid").val());
			if(selectUuid == null || selectUuid == ""){
				bootbox.alert("<aebiz:showTitle titleId='platimagecategory.m.chooseCategory'/>");
				return;
			}
			bootbox.confirm("<aebiz:showTitle titleId='platimagecategory.m.confirm'/>", 
			function(r){
	        if(r) {
			$.get("${pageContext.servletContext.contextPath}/sysback/platimagecategory/deleteCategory",
				{"selectUuid":selectUuid},
				function(data) {
					if (data == "success") {
						//刷新
						//bootbox.alert('成功');
						var tree=$("#tree").dynatree("getTree");
						 tree.reload();
						//window.location.href="${pageContext.servletContext.contextPath}/sysback/plattimagelibrary/toList";
					} else if(data == "hasImage"){
						bootbox.alert("<aebiz:showTitle titleId='platimagecategory.m.hasSub'/>");
					}else {
						bootbox.alert("<aebiz:showTitle titleId='plattimagelibrary.m.deletefail'/>");
					}
			});
	        }   
		    });   
		});
		
		
		function openSendImage(){
		 	var selectUuid=$.trim($("#selectUuid").val())
			if (selectUuid == "") {
				//提示为空
				$("#btn-openSendImage").removeAttr("data-target", "#myModal1");
				$("#btn-openSendImage").removeAttr("data-toggle", "modal");
				bootbox.alert("<aebiz:showTitle titleId='platimagecategory.m.chooseCategory'/>");
				return;
			}
			 
			$("#btn-openSendIamge").attr("data-target", "#myModal1");
			$("#btn-openSendIamge").attr("data-toggle", "modal");
			
		}
		
		
		function uploadImage() {	
			var categoryUuid = $("#categoryUuid").val();
			var nowPage = $("#nowPage").val();
			//$("#upload").submit();
			//$("#list").load("${pageContext.servletContext.contextPath}/sysback/plattimagelibrary/toImage/"+categoryUuid+"?time="+Math.random());
   		 $.ajaxFileUpload({
       		url: '${pageContext.request.contextPath}/sysback/plattimagelibrary/uploadFile/'+categoryUuid, //用于文件上传的服务器端请求地址
        	secureuri: false, //是否需要安全协议，一般设置为false
        	fileElementId: 'uploadFiles', //文件上传域的ID
        	dataType: 'json', //返回值类型 一般设置为json
        	success: function (data, status){ 
      		 	//bootbox.alert("上传成功!");
      		 	$.colorbox.remove();
      		 	$("#list").load("${pageContext.servletContext.contextPath}/sysback/plattimagelibrary/toImage/"+categoryUuid+"/"+nowPage+"/8/0?time="+Math.random());
      		 	$("#myModal1").modal('hide');
				$("body").removeClass("modal-open");
				$(".modal-backdrop").remove();
        	},
	        error: function (data, status, e){
	        	//服务器响应失败处理函数   
	        	bootbox.alert("<aebiz:showTitle titleId='plattimagelibrary.m.uploadfail'/>"+e);
	        	$("#myModal1").modal('hide');
				$("body").removeClass("modal-open");
				$(".modal-backdrop").remove();
      		 	//$("#list").load("${pageContext.servletContext.contextPath}/sysback/plattimagelibrary/toImage/"+categoryUuid+"?time="+Math.random());

	        }
       })                  
       
       return false;
	  }
		
		
		$(function(){
			drawTree();
			/* var t = $("#tree").dynatree("getSelectedNodes").length;
			if(t===0){
				alert(1);
			}else{
				alert(2);
			} */
 		 });
		
		
		function drawTree(){
			 $("#tree").dynatree({
					title: "Lazy Tree",
					rootVisible: true,
					autoFocus:true,
					fx: { height: "toggle", duration: 100 },
					isLazy:true,
					initAjax: {
						type: 'GET',
						url: "${pageContext.servletContext.contextPath}/sysback/platimagecategory/getNodes",
						data: {key:""},
						dataType: "json",
						contentType: 'application/json; charset=utf-8'
					
					},
					onFocus:function(dtnode){
						$("#selectUuid").val(dtnode.data.key);
						$("#categoryUuid").val(dtnode.data.key);
						$("#categoryName2").val(dtnode.data.title);
						$.colorbox.remove();
						$("#list").load("${pageContext.servletContext.contextPath}/sysback/plattimagelibrary/toImage/"+dtnode.data.key+"/1/8/0?time="+Math.random());
					},
					onActivate: function (dtnode) {
						
					}
					
					, onLazyRead: function (dtnode) {
						var key=dtnode.data.key;
						dtnode.appendAjax({
							type: 'GET',
							url: "${pageContext.servletContext.contextPath}/sysback/platimagecategory/getNodes",
							data: {key:key},
							dataType: "json",
							contentType: 'application/json; charset=utf-8'
						});
					}
				});
		}
		
		function imagePage(nowPage,pageShow){
			var categoryUuid = $("#selectUuid").val();
			var type = $("#type").val();
			$.colorbox.remove();
			$("#list").load("${pageContext.servletContext.contextPath}/sysback/plattimagelibrary/toImage/"+categoryUuid+"/"+nowPage+"/"+pageShow+"/"+type+"?time="+Math.random());
		}
		
	</script>
</body>
</html>