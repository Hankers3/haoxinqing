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
<div class="box">
	<div class="box-title"><h3><i class="fa fa-edit"></i><aebiz:showTitle titleId="productcategoryplatform.title.add" /></h3></div>
	<div class="box-content">
		<form  method="POST" action="#"  name="mainForm" class="form-horizontal form-validate1" id="mainForm">
			<div class="form-group">
				<label class="control-label col-sm-2"><aebiz:showTitle titleId="productcategoryplatform.m.categoryName" />：</label>
				<div class="col-sm-8">
					<!--此处做了最大输入长度限制-->
					<input type="text" name="categoryName" id="categoryName" class="form-control" data-rule-required="true" data-rule-maxlength="50" maxlength="20" onblur="checkName()">
					<input type="hidden" name="categoryName_value" value="true"/>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2"><aebiz:showTitle titleId="productcategoryplatform.m.categoryNo" />：</label>
				<div class="col-sm-4">
					<input type="text" name="categoryNo" id="categoryNo" class="form-control"  data-rule-required="true" data-rule-maxlength="20" onblur="checkCategoryNo();">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2"><aebiz:showTitle titleId="productcategoryplatform.m.position" />：</label>
				<div class="col-sm-2">
					<input type="text" name="position" id="position" class="form-control text-center"  data-rule-required="true" data-rule-positivenum="true" data-rule-maxlength="4">
				</div>
				<label class="control-label col-sm-2"><aebiz:showTitle titleId="productcategoryplatform.m.state" />：</label>
				<div class="col-sm-4">
					<div class="check-line y_mhtwo">
						<input type="radio" id="state" name="state" class='icheck-me' data-skin="square" data-color="blue" checked>
						<label class='inline' for="showyes"><aebiz:showTitle titleId="basebusiness.showmessage.yes" /></label>
					</div>
					<div class="check-line y_mhtwo">
						<input type="radio" id="state" name="state" class='icheck-me' data-skin="square" data-color="blue">
						<label class='inline' for="showno"><aebiz:showTitle titleId="basebusiness.showmessage.no" /></label>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2"><aebiz:showTitle titleId="productcategoryplatform.m.categoryNote" />：</label>
				<div class="col-sm-8">
					 <textarea name="categoryNote" id="categoryNote" rows="5" class="form-control" placeholder="<aebiz:showTitle titleId="productcategoryplatform.categoryNote.note" />"  data-rule-required="true" data-rule-maxlength="200"></textarea>
					 <span class="star-red"><aebiz:showTitle titleId="productcategoryplatform.categoryNote.hint" /></span>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2"><aebiz:showTitle titleId="productcategoryplatform.m.categoryUrl" />：</label>
				<div class="col-sm-8">
					<input type="text" name="categoryUrl" id="categoryUrl" class="form-control" data-rule-url="true">
				</div>
			</div>
			<div class="y_fixedbtn" data-height="no">
				<button type="button" class="btn btn-primary btn-large a_size_1" id="addData"><aebiz:showTitle titleId="basebusiness.showmessage.save" /></button>
			</div>
		</form>
	</div>
</div>
<script>
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
			
			var selectUuid=$.trim($("#selectUuid").val())
			if(selectUuid==""){
				bootbox.alert('<aebiz:showTitle titleId="productcategoryplatform.alert.selectCategory"/>');
			}
			dataParam.push({
				"name" : "parentUuid",
				"value" : selectUuid
			});
			$.getJSON("${pageContext.servletContext.contextPath}/sysback/productcategoryplatform/save",
								{aoData : JSON.stringify(dataParam)},
								function(data) {
									if (data.rsp) {
										//刷新
										bootbox.alert('<aebiz:showTitle titleId="productcategoryplatform.alert.success"/>');
										var tree=$("#tree").dynatree("getTree");

										var dtnode=tree.getNodeByKey(selectUuid);
										dtnode.appendAjax({
											type: 'GET',
											url: "${pageContext.servletContext.contextPath}/sysback/productcategoryplatform/getNodes",
											data: {key:selectUuid,time:Math.random()},
											dataType: "json",
											contentType: 'application/json; charset=utf-8'
										});

										$("#editPage").load("${pageContext.servletContext.contextPath}/sysback/productcategoryplatform/toAdd");
									} else {
										bootbox.alert('<aebiz:showTitle titleId="productcategoryplatform.alert.fail"/>');
									}
			});
		}

		 	
		function checkName(){
			var categoryName=$("input[name='categoryName']").val();
			var nameReg=/^[\u4E00-\u9FA5\uF900-\uFA2D\w]{1,20}$/;
			if(categoryName != ""){
				if(categoryName.replace(/([\u4E00-\u9FA5\uf900-\ufa2d])/g,'aa').length>20 ||categoryName.replace(/([\u4E00-\u9FA5\uf900-\ufa2d])/g,'aa').length<1 ){
					 $("span[name='salePriceSpan']").remove();
					 $('input[name="categoryName"]').css("border","1px solid #A94442");     
					 $('input[name="categoryName"]').after("<span  name='salePriceSpan'><font color='red'><aebiz:showTitle titleId="productcategoryplatform.categoryName.hint" /></font></span>");
					 $("input[name='categoryName_value']").val("false");
					return ;
				}else{
					 $("span[name='salePriceSpan']").remove();
					 $('input[name="categoryName"]').css("border","1px solid #2B542C");     
					 $("input[name='categoryName_value']").val("true");
				}
			}
		}
		
		 $(".btn-primary").click(function(){
			 	var nameError=$("input[name='categoryName_value']").val();
				if(nameError=="true"){
					$("#mainForm").submit();
				}
			 });
</script>