<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/usercenter/js/ajaxfileupload.js"></script>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/datepicker/datepicker.css">
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.datepicker.js"></script>
</head>

<body>
	
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="platforminfo.moduleName_CN"/></h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span><aebiz:showTitle titleId="platforminfo.menuOne"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="platforminfo.menuTwo"/></span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/><aebiz:showTitle titleId="platforminfo.moduleName_CN"/></span>
				</li>
			</ul>
		</div>		
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">																
				<form id="mainForm" action="${pageContext.servletContext.contextPath}/sysback/platforminfo/doAdd" method="post" class='form-horizontal form-bordered form-validate' enctype="multipart/form-data">						
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="platforminfo.m.videoModel"/></label>
						<div class="col-sm-10">
							<input type="text" name="videoModel" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="platforminfo.m.videoIntroduction"/></label>
						<div class="col-sm-10">
							<input type="text" name="videoIntroduction" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="platforminfo.m.userName"/></label>
						<div class="col-sm-10">
							<input type="text" name="userName" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">主讲人简绍</label>
						<div class="col-sm-10">
							<input type="text" name="userIntroduce" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="platforminfo.m.videoType"/></label>
						<div class="col-sm-10">
							<input type="radio" name="videoType" class="" value="1" >医生端
							<input type="radio" name="videoType" class="" value="0" checked="checked">患者端
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="platforminfo.m.state"/></label>
						<div class="col-sm-10">
							<input type="radio" name="state" class="" value="1" checked>已发布
							<input type="radio" name="state" class="" value="0" >未发布
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">视频连接</label>
						<div class="col-sm-10">
							<input type="text" name="videoAddress" class="form-control">
						</div>
					</div>
					
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">上传视频</label>
						<div class="col-sm-10">
							<div class="fileinput fileinput-new r_fileinput" data-provides="fileinput">
								<div class="input-group">
									<div class="form-control" data-trigger="fileinput">
										<i class="glyphicon glyphicon-file fileinput-exists"></i>
										<span class="fileinput-filename"></span>
									</div>
									
									<label class="control-label">上传进度:</label>  
                  <div class="controls">  
                     <div  class="progress progress-success progress-striped" style="width:50%">  
                        <div  id = 'proBar' class="bar" style="width: 0%"></div>  
                     </div>  
                  </div>  




									
									<span class="input-group-addon btn btn-default btn-file">
										<span class="fileinput-new">选择视频</span>
										<span class="fileinput-exists">Change</span>
										<input type="file" value="" name="files" >
									</span>
									<a href="#" class="input-group-addon btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="platforminfo.m.image"/></label>
						<div class="col-sm-10">
							<div class="col-sm-5">
								<div class="fileinput fileinput-new" data-provides="fileinput"><input type="hidden">
									<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 100px; height: 100px; line-height: 100px;"></div>
										<div>
											<span class="btn btn-default btn-file">
											<span class="fileinput-new">选择上传图片</span>
											<span class="fileinput-exists">Change</span>
											<input type="file" name="filesImg" id="uploadFile">
											</span>
											<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
										</div>
								</div>
							</div>
						</div>
					</div>
					<!--
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="platforminfo.m.type"/></label>
						<div class="col-sm-10">
							<input type="radio" name="type" class="rad" value="1" >是
							<input type="radio" name="type" class="rad" value="0" checked>否
						</div>
					</div>
					-->	
					<div class="form-group">
						<label for="textfield" class="control-label col-sm-2">是否为热播视频</label>
						<div class="col-sm-10">
							<input type="radio" name="videoHot"  value="1" >是
							<input type="radio" name="videoHot"  value="0" checked>否
						</div>
					</div>
					
					<!--
					<div class="form-group" id="showDiv" style="display:none">
							<label for="textfield" class="control-label col-sm-2">课程开课时间：</label>
							<div class="col-sm-10">
							<input type="text" name="startTime" class="form-control datepick">
							</div>
					</div>
					-->	
					<div class="form-actions col-sm-offset-2 col-sm-10">
						<input type="submit" id ="subbut" class="btn btn-primary" value='<aebiz:showTitle titleId="basebusiness.showmessage.add"/>'>
						<button type="button" class="btn cancel"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
					</div>						
				</form>
			</div>
		</div>
	</div>
</body>

</html>


<script>
    $(document).ready(function() {
    	 $('#subbut').bind('click',  
            function(){  
               // $('#fForm').submit();  
                var eventFun = function(){  
                    $.ajax({  
                        type: 'GET',  
                        url: '${pageContext.servletContext.contextPath}/sysback/platforminfo/process',  
                        data: {},  
                        dataType: 'json',  
                        success : function(data){  
                                $('#proBar').css('width',data.rate+''+'%');  
                                $('#proBar').empty();  
                                $('#proBar').append(data.show);   
                                if(data.rate == 100){  
                                    window.clearInterval(intId);  
                                }     
                }});};  
                var intId = window.setInterval(eventFun,500);  
    });  
    	
    	
    	
    	
		$(".cancel").click(function(){
			history.go(-1) ;
		});
		
		 $(".rad").click(function(){
		  if($(this).attr("value")=="1")
		   $("#showDiv").show();
		  else
		   $("#showDiv").hide();
		   });
		
		
    });
    function uploadImage(){
		$.ajaxFileUpload({
   		url: '${pageContext.request.contextPath}/sysback/platforminfo/uploadFile', //用于文件上传的服务器端请求地址
    	secureuri: false, //是否需要安全协议，一般设置为false
    	fileElementId: "uploadFile", //文件上传域的ID
    	dataType: 'text', //返回值类型 一般设置为json
    	success: function (data){ 
    		if(data!=null){
    			var start = data.indexOf(">");
            if (start != -1) {
               var end = data.indexOf("<", start + 1);
                 if (end != -1) {
                    data = data.substring(start + 1, end);
                 }
            }
    			$("#image").val(data);
    		}
    	},
	      error: function (data){
	      	bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;//服务器响应失败处理函数            
	      }
	   })                    
	   return false;
	}

    $("#mainForm").validate({
    	rules: {
    		videoModel:{
				required : true,
			},
			videoIntroduction:{
				required : true,
			},
			userName:{
				required : true,
			},
			state:{
				required : true,
			},
			image:{
				required : true,
			},
			createTime:{
				required : true,
			},
			type:{
				required : true,
			},
			number:{
				required : true,
				digits:true,
			},
			startTime:{
				required : true,
			},
			endTime:{
				required : true,
			},
			
  		},
		errorElement: 'span',  //输入错误时的提示标签
		errorClass: 'help-block has-error',  //输入错误时的提示标签类名
		errorPlacement: function(error, element) {  //输入错误时的提示标签显示的位置
		 element.after(error);
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
		
		submitHandler:function(form){
	    	form.submit();
        }
    });
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>