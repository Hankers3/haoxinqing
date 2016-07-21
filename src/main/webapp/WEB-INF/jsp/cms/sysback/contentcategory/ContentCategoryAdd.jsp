<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 设置浮动按钮的js -->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.buttonfloat.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/validation/jquery.validate.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script>
	<!-- 美化单选、复选框样式 -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/icheck/all.css">
	<!-- 单选、复选框的美化js -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
	<!-- 单选复选框美化样式的调用js -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/bootbox/jquery.bootbox.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/ajaxfileupload.js"></script>
<div class="box">
	<div class="box-title"><h3><i class="fa fa-edit"></i><aebiz:showTitle titleId="contentcategory.title.add" /></h3></div>
	<div class="box-content">
		<form  method="POST" action="#"  name="mainForm" class="form-horizontal form-validate1" id="mainForm">
			<div class="form-group">
				<label class="control-label col-sm-2"><aebiz:showTitle titleId="contentcategory.m.categoryName" />：</label>
				<div class="col-sm-8">
					<input type="text" name="categoryName" id="categoryName" class="form-control" maxlength="20" data-rule-required="true" data-rule-maxlength="20">
					<span class="star-red"><aebiz:showTitle titleId="contentcategory.categoryName.hint" /></span>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2"><aebiz:showTitle titleId="contentcategory.m.categoryNo" />：</label>
				<div class="col-sm-4">
					<input type="text" name="categoryNo" id="categoryNo" class="form-control" maxlength="20" data-rule-required="true" data-rule-maxlength="20">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><aebiz:showTitle titleId="contentcategory.m.position" />：</label>
				<div class="col-sm-2">
					<input type="text" name="position" id="position" class="form-control text-center" maxlength="2" data-rule-required="true" data-rule-positivenum="true" data-rule-maxlength="2">
				</div>
				<label class="control-label col-sm-2"><aebiz:showTitle titleId="contentcategory.m.state" />：</label>
				<div class="col-sm-4">
					<div class="check-line y_mhtwo">
						<input type="radio" id="state" name="state" class='icheck-me' data-skin="square" value="1" data-color="blue" checked>
						<label class='inline' for="showyes"><aebiz:showTitle titleId="basebusiness.showmessage.yes" /></label>
					</div>
					<div class="check-line y_mhtwo">
						<input type="radio" id="state" name="state" class='icheck-me' data-skin="square" value="0" data-color="blue">
						<label class='inline' for="showno"><aebiz:showTitle titleId="basebusiness.showmessage.no" /></label>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2">导航图片：</label>
				<div class="col-sm-5">
					<div class="fileinput fileinput-new" data-provides="fileinput"><input type="hidden">
						<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 100px; height: 100px; line-height: 100px;"></div>
							<div>
								<span class="btn btn-default btn-file">
								<span class="fileinput-new">选择上传图片</span>
								<span class="fileinput-exists">Change</span>
								<input type="file" name="myfiles" id="uploadFile" onchange="javascript:uploadImage();" >
								<input type="hidden" name="icon" value="" id="icon">
								</span>
								<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
							</div>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2"><aebiz:showTitle titleId="contentcategory.m.categoryNote" />：</label>
				<div class="col-sm-8">
					 <textarea name="categoryNote" id="categoryNote" rows="5" class="form-control" placeholder="<aebiz:showTitle titleId="contentcategory.categoryNote.note" />"  data-rule-required="true" data-rule-maxlength="200"></textarea>
					 <span class="star-red"><aebiz:showTitle titleId="contentcategory.categoryNote.hint" /></span>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2"><aebiz:showTitle titleId="contentcategory.m.categoryUrl" />：</label>
				<div class="col-sm-8">
					<input type="text" name="categoryUrl" id="categoryUrl" class="form-control" data-rule-url="true">
				</div>
			</div>
			<div class="y_fixedbtn" data-height="no">
				<button type="submit" class="btn btn-primary btn-large a_size_1" id="addData"><aebiz:showTitle titleId="basebusiness.showmessage.save" /></button>
			</div>
		</form>
	</div>
</div>
<script>
	function uploadImage(){
		$.ajaxFileUpload({
   		url: '${pageContext.request.contextPath}/sysback/contentcategory/uploadFile', //用于文件上传的服务器端请求地址
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
    			$("#icon").val(data);
    		}
    	},
      error: function (data){
      	bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>') ;//服务器响应失败处理函数            
      }
   })                    
   return false;
	}
	
	// Validation 调用表单验证插件的js
  $(function(){
    if ($('.form-validate1').length > 0) {
        $('.form-validate1').each(function() {
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
                	saveCategory() ;  
				        //    form.submit(); //没有这一句表单不会提交
				        }
            });
        });
    }	
  })
	
	
	
		function saveCategory(){
			var dataParam = new Array();
			dataParam.push({
				"name" : "categoryName",
				"value" : $.trim($("#categoryName").val())
			});

			dataParam.push({
				"name" : "categoryNo",
				"value" : $.trim($("#categoryNo").val())
			});
			
			dataParam.push({
				"name" : "position",
				"value" : $.trim($("#position").val())
			});
			
			dataParam.push({
				"name" : "categoryNote",
				"value" : $.trim($("#categoryNote").val())
			});
			
			dataParam.push({
				"name" : "categoryUrl",
				"value" : $.trim($("#categoryUrl").val())
			});
			 
			var stateValue=$('input[name="state"]:checked').val()
			
			dataParam.push({
				"name" : "state",
				"value" : stateValue
			});
			
			dataParam.push({"name" : "icon","value" : $.trim($("#icon").val())});
			dataParam.push({"name" : "categoryType","value" :"2"});
			var selectUuid=$.trim($("#selectUuid").val())
			if(selectUuid==""){
				bootbox.alert('<aebiz:showTitle titleId="contentcategory.alert.selectCategory"/>');
			}
			dataParam.push({
				"name" : "parentUuid",
				"value" : selectUuid
			});
			$.getJSON("${pageContext.servletContext.contextPath}/sysback/contentcategory/save",
								{aoData : JSON.stringify(dataParam)},
								function(data) {
									if (data.rsp) {
										//刷新
										bootbox.alert('<aebiz:showTitle titleId="contentcategory.alert.success"/>');
										var tree=$("#tree").dynatree("getTree");

										var dtnode=tree.getNodeByKey(selectUuid);
										dtnode.appendAjax({
											type: 'GET',
											url: "${pageContext.servletContext.contextPath}/sysback/contentcategory/getNodes",
											data: {key:selectUuid,categoryType:"",time:Math.random()},
											dataType: "json",
											contentType: 'application/json; charset=utf-8'
										});

										$("#editPage").load("${pageContext.servletContext.contextPath}/sysback/contentcategory/toAdd");
									} else {
										bootbox.alert('<aebiz:showTitle titleId="contentcategory.alert.fail"/>');
									}
			});
		}

</script>