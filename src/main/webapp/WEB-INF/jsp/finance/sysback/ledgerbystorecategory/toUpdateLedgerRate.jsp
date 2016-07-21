<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Validation 表单验证插件-->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/validation/jquery.validate.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script>
	<div class="container-fluid">	
		<div class="row">
			<form:form class="form-horizontal form-validate2" method="POST"  action="#"  commandName="m" id="mainForm11">
			<input type="hidden" name="categoryUuid" id="categoryUuid" value="${webModel.categoryPlatform.uuid}" />
			<input type="hidden" name="accountUuid" id="accountUuid" value="${webModel.accountUuid}" />
			<div class="col-sm-12">
				<h5 class="sort-title box-bordered sort-titleLeft">分类信息</h5>
				<div class="form-input form-lable">
					<div class="col-sm-2">
						<label class="control-lable  pull-right"><aebiz:showTitle titleId="ledgerbystore.m.categoryName" />：</label>
					</div>
					<div class="col-sm-3">
						${webModel.categoryPlatform.categoryName}
					</div>
				</div>
				<div class="form-input form-lable">
					<div class="col-sm-2">
						<label class="control-lable  pull-right"><aebiz:showTitle titleId="ledgerbystore.m.categoryNo" />：</label>
					</div>
					<div class="col-sm-3">
						${webModel.categoryPlatform.categoryNote} 
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="form-input form-lable">
					<div class="col-sm-2">
						<label class="control-lable  pull-right"><aebiz:showTitle titleId="ledgerbystore.m.categoryNote" />：</label>
					</div>
					<div class="col-sm-3">
						${webModel.categoryPlatform.categoryNote} 
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<h5 class="sort-title box-bordered sort-titleLeft">分账设置</h5>
				<div class="row form-input form-lable">
					<div class="col-sm-2">
						<label class="control-lable  pull-right">分账比率：</label>
					</div>
					<div class="col-sm-3">
						<input name="ledgerRate" id="ledgerRate" class="form-control" data-rule-float="true" data-rule-required="true" value="${m.ledgerRate }"/>
					</div>
				</div>
			</div>
			<div class="form-actions col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-primary btn-large a_size_1"><aebiz:showTitle titleId="basebusiness.showmessage.save" /></button>
				<button type="button" class="btn cancel btn-large a_size_1"><aebiz:showTitle titleId="basebusiness.showmessage.return" /></button>
			</div>
		</form:form >	
		</div>
	</div>
<script>
 
$(function(){
	$(".cancel").click(function() {
		window.location.href = "${pageContext.servletContext.contextPath}/sysback/ledgerbystore/returnList";
	});
    if ($('.form-validate2').length > 0) {
        $('.form-validate2').each(function() {
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
                	setLedgerRate() ;  
				        }
            });
        });
    }	
  })
	 //设置分账比率
    function setLedgerRate() {
    	var ledgerRate = $("#ledgerRate").val();
    	var accountUuid =  $("#accountUuid").val();
    	var categoryUuid =$("#categoryUuid").val();
    		$.get("${pageContext.servletContext.contextPath}/sysback/ledgerbystorecategory/addOrUpdateLedgerRate",
    			{
    				"accountUuid":accountUuid,
    				"ledgerRate":ledgerRate,
    				"categoryUuid":categoryUuid,
    				ranNum : Math.random()
    			},
    			function(data) {
    				if (data == "success") {
    					//刷新
    					bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;
    					//doSearch('search');
    				} else {
    					bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
    				}
    			});
    }
</script>			
