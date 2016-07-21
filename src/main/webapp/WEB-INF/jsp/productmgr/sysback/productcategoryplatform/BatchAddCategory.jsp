<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/bootstrap.min.js"></script>
 <script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/bootbox/jquery.bootbox.js"></script>
<!-- 设置浮动按钮的js -->
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.buttonfloat.js"></script>	
<!-- Validation 表单验证插件-->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/validation/jquery.validate.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script>
 

	<div class="box">
		<div class="box-title"><h3><i class="fa fa-edit"></i><aebiz:showTitle titleId="productcategoryplatform.title.add" /></h3></div>
		<div class="box-content">
			<form class="form-horizontal y_addpdzfl form-validate1" method="POST" action="#" id="mainForm">
				<div class="form-group" id="categoryNameError1">
					<label class="control-label col-sm-3"><span class="label label-default">1</span><aebiz:showTitle titleId="productcategoryplatform.m.categoryName" />：</label>
					<div class="col-sm-3">
						<input type="text" name="categoryName1" onblur="checkName('categoryName1');" id="categoryName1" data-rule-required="true" class="form-control" maxlength="20" placeholder="<aebiz:showTitle titleId="productcategoryplatform.categoryName.hint" />">
					</div>
					<label class="control-label col-sm-2"><aebiz:showTitle titleId="productcategoryplatform.m.categoryNo" />：</label>
					<div class="col-sm-3">
						<input type="text" name="categoryNo1" id="categoryNo1" data-rule-required="true"  class="form-control" maxlength="20" placeholder="<aebiz:showTitle titleId="productcategoryplatform.categoryNo.hint" />">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3"><span class="label label-default">2</span><aebiz:showTitle titleId="productcategoryplatform.m.categoryName" />：</label>
					<div class="col-sm-3">
						<input type="text" name="categoryName2" onblur="checkName('categoryName2');" id="categoryName2" class="form-control" maxlength="20"  placeholder="<aebiz:showTitle titleId="productcategoryplatform.categoryName.hint" />">
					</div>
					<label class="control-label col-sm-2"><aebiz:showTitle titleId="productcategoryplatform.m.categoryNo" />：</label>
					<div class="col-sm-3">
						<input type="text" name="categoryNo2" id="categoryNo2" class="form-control" maxlength="20" placeholder="<aebiz:showTitle titleId="productcategoryplatform.categoryNo.hint" />">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3"><span class="label label-default">3</span><aebiz:showTitle titleId="productcategoryplatform.m.categoryName" />：</label>
					<div class="col-sm-3">
						<input type="text" name="categoryName3"  onblur="checkName('categoryName3');" id="categoryName3" class="form-control" maxlength="20" placeholder="<aebiz:showTitle titleId="productcategoryplatform.categoryName.hint" />">
					</div>
					<label class="control-label col-sm-2"><aebiz:showTitle titleId="productcategoryplatform.m.categoryNo" />：</label>
					<div class="col-sm-3">
						<input type="text" name="categoryNo3" id="categoryNo3" class="form-control" maxlength="20" placeholder="<aebiz:showTitle titleId="productcategoryplatform.categoryNo.hint" />">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3"><span class="label label-default">4</span><aebiz:showTitle titleId="productcategoryplatform.m.categoryName" />：</label>
					<div class="col-sm-3">
						<input type="text" name="categoryName4" onblur="checkName('categoryName4');" id="categoryName4" class="form-control" maxlength="20" placeholder="<aebiz:showTitle titleId="productcategoryplatform.categoryName.hint" />">
					</div>
					<label class="control-label col-sm-2"><aebiz:showTitle titleId="productcategoryplatform.m.categoryNo" />：</label>
					<div class="col-sm-3">
						<input type="text" name="categoryNo4" id="categoryNo4" class="form-control" maxlength="20" placeholder="<aebiz:showTitle titleId="productcategoryplatform.categoryNo.hint" />">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3"><span class="label label-default">5</span><aebiz:showTitle titleId="productcategoryplatform.m.categoryName" />：</label>
					<div class="col-sm-3">
						<input type="text" name="categoryName5" onblur="checkName('categoryName5');" id="categoryName5" class="form-control" maxlength="20"  placeholder="<aebiz:showTitle titleId="productcategoryplatform.categoryName.hint" />">
					</div>
					<label class="control-label col-sm-2"><aebiz:showTitle titleId="productcategoryplatform.m.categoryNo" />：</label>
					<div class="col-sm-3">
						<input type="text" name="categoryNo5" id="categoryNo5" class="form-control" maxlength="20" placeholder="<aebiz:showTitle titleId="productcategoryplatform.categoryNo.hint" />">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3"><span class="label label-default">6</span><aebiz:showTitle titleId="productcategoryplatform.m.categoryName" />：</label>
					<div class="col-sm-3">
						<input type="text" name="categoryName6" id="categoryName6" onblur="checkName('categoryName6');" class="form-control" maxlength="20" placeholder="<aebiz:showTitle titleId="productcategoryplatform.categoryName.hint" />">
					</div>
					<label class="control-label col-sm-2"><aebiz:showTitle titleId="productcategoryplatform.m.categoryNo" />：</label>
					<div class="col-sm-3">
						<input type="text" name="categoryNo6" id="categoryNo6" class="form-control" maxlength="20" placeholder="<aebiz:showTitle titleId="productcategoryplatform.categoryNo.hint" />">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3"><span class="label label-default">7</span><aebiz:showTitle titleId="productcategoryplatform.m.categoryName" />：</label>
					<div class="col-sm-3">
						<input type="text" name="categoryName7" id="categoryName7"  onblur="checkName('categoryName7');" class="form-control" maxlength="20" placeholder="<aebiz:showTitle titleId="productcategoryplatform.categoryName.hint" />">
					</div>
					<label class="control-label col-sm-2"><aebiz:showTitle titleId="productcategoryplatform.m.categoryNo" />：</label>
					<div class="col-sm-3">
						<input type="text" name="categoryNo7" id="categoryNo7" class="form-control"  maxlength="20" placeholder="<aebiz:showTitle titleId="productcategoryplatform.categoryNo.hint" />">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3"><span class="label label-default">8</span><aebiz:showTitle titleId="productcategoryplatform.m.categoryName" />：</label>
					<div class="col-sm-3">
						<input type="text" name="categoryName8" id="categoryName8" onblur="checkName('categoryName8');" class="form-control" maxlength="20" placeholder="<aebiz:showTitle titleId="productcategoryplatform.categoryName.hint" />">
					</div>
					<label class="control-label col-sm-2"><aebiz:showTitle titleId="productcategoryplatform.m.categoryNo" />：</label>
					<div class="col-sm-3">
						<input type="text" name="categoryNo8" id="categoryNo8" class="form-control" maxlength="20" placeholder="<aebiz:showTitle titleId="productcategoryplatform.categoryNo.hint" />">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3"><span class="label label-default">9</span><aebiz:showTitle titleId="productcategoryplatform.m.categoryName" />：</label>
					<div class="col-sm-3">
						<input type="text" name="categoryName9" id="categoryName9" onblur="checkName('categoryName9');" class="form-control" maxlength="20" placeholder="<aebiz:showTitle titleId="productcategoryplatform.categoryName.hint" />">
					</div>
					<label class="control-label col-sm-2"><aebiz:showTitle titleId="productcategoryplatform.m.categoryNo" />：</label>
					<div class="col-sm-3">
						<input type="text" name="categoryNo9" id="categoryNo9" class="form-control" maxlength="20" placeholder="<aebiz:showTitle titleId="productcategoryplatform.categoryNo.hint" />">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3"><span class="label label-default">10</span><aebiz:showTitle titleId="productcategoryplatform.m.categoryName" />：</label>
					<div class="col-sm-3">
						<input type="text" name="categoryName10" id="categoryName10" onblur="checkName('categoryName10');" class="form-control" maxlength="20" placeholder="<aebiz:showTitle titleId="productcategoryplatform.categoryName.hint" />">
					</div>
					<label class="control-label col-sm-2"><aebiz:showTitle titleId="productcategoryplatform.m.categoryNo" />：</label>
					<div class="col-sm-3">
						<input type="text" name="categoryNo10" id="categoryNo10" class="form-control" maxlength="20" placeholder="<aebiz:showTitle titleId="productcategoryplatform.categoryNo.hint" />">
					</div>
				</div>
				<div class="y_fixedbtn" data-height="no">
					<button type="button" class="btn btn-primary btn-large a_size_1"><aebiz:showTitle titleId="productcategoryplatform.button.addBatch" /></button>			
					<a onclick="javascript:clearAdd();" class="btn  btn-large a_size_1"><aebiz:showTitle titleId="productcategoryplatform.button.addClear" /></a>
				</div>
			</form>
		</div>
	</div>
<!--商品分类管理 end -->
<script>
	$(function(){
		//批量添加子分类输入框有内容时加深标签背景色
		$(".y_addpdzfl .form-group").each(function(){
			var $listen = $(this).find(".form-control:eq(0)");
			$listen.keyup(function(){
				if($(this).val()!==""){
					$(this).parents(".form-group").addClass("active");	
				}else{
					$(this).parents(".form-group").removeClass("active");	
				};
			});
		});
	});
	
	
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
                	batchAdd() ;  
				        //    form.submit(); //没有这一句表单不会提交
				        }
            });
        });
    }	
  })
  
  
  function batchAdd(){
  	var isTrue="true";
  	for(var i=2;i<=10; i++){
	  	var categoryName=$.trim($("#categoryName"+i).val());
	  	var categoryNo=$.trim($("#categoryNo"+i).val());
	  	
	  	if(categoryName!=""&& categoryNo==""){		
				 bootbox.alert('<aebiz:showTitle titleId="productcategoryplatform.alert.please" />'+i+'<aebiz:showTitle titleId="productcategoryplatform.alert.pleaseName" />');
				 isTrue="false";
				 break;
			}
			
			if(categoryName==""&& categoryNo!=""){
				 bootbox.alert('<aebiz:showTitle titleId="productcategoryplatform.alert.please" />'+i+'<aebiz:showTitle titleId="productcategoryplatform.alert.pleaseNo" />');
				 isTrue="false";
				 break;
			}
		}
		if(isTrue=="false"){
			return;
		}
		var dataParam = new Array();
		for(var j=1;j<=10; j++){
			var temp1= $.trim($("#categoryName"+j).val());
			var temp2= $.trim($("#categoryNo"+j).val());
			dataParam.push({
				"name" : "categoryName"+j,
				"value" :temp1
			});

			dataParam.push({
				"name" : "categoryNo"+j,
				"value" : temp2
			});
		}
			 
			var selectUuid=$.trim($("#selectUuid").val())
			
			if(selectUuid==""){
				bootbox.alert('<aebiz:showTitle titleId="productcategoryplatform.alert.selectCategory"/>');
			}
			dataParam.push({
				"name" : "parentUuid",
				"value" : selectUuid
			});
			$.getJSON("${pageContext.servletContext.contextPath}/sysback/productcategoryplatform/addBatch",
								{aoData : JSON.stringify(dataParam),ranNum : Math.random()},
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

										$("#editPage").load("${pageContext.servletContext.contextPath}/sysback/productcategoryplatform/toBatchAdd?time="+Math.random());
									} else {
										bootbox.alert('<aebiz:showTitle titleId="productcategoryplatform.alert.fail"/>');
									}
			});
		
		
  }
  
  
  
  function clearAdd(){
			$("#categoryName1").val("");
			$("#categoryNo1").val("");
			$("#categoryName2").val("");
			$("#categoryNo2").val("");
			$("#categoryName3").val("");
			$("#categoryNo3").val("");
			$("#categoryName4").val("");
			$("#categoryNo4").val("");
			$("#categoryName5").val("");
			$("#categoryNo5").val("");
			$("#categoryName6").val("");
			$("#categoryNo6").val("");
			$("#categoryName7").val("");
			$("#categoryNo7").val("");
			$("#categoryName8").val("");
			$("#categoryNo8").val("");
			$("#categoryName9").val("");
			$("#categoryNo9").val("");
			$("#categoryName10").val("");
			$("#categoryNo10").val("");
	}
  
  
	function checkName(id){
		var categoryName=$("#"+id).val();
		var nameReg=/^[\u4E00-\u9FA5\uF900-\uFA2D\w]{1,20}$/;
		if(categoryName != ""){
			if(categoryName.replace(/([\u4E00-\u9FA5\uf900-\ufa2d])/g,'aa').length>20 ||categoryName.replace(/([\u4E00-\u9FA5\uf900-\ufa2d])/g,'aa').length<1 ){
				 $("#salePriceSpan"+id).remove();
				 $("#"+id).css("border","1px solid #A94442");     
				 $("#"+id).after("<span  id='salePriceSpan"+id+"'><font color='red'><aebiz:showTitle titleId="productcategoryplatform.categoryName.hint" /></font></span>");
				 $("#salePriceSpan"+id).after("<input type='hidden' value='true'  id='namevalidate"+id+"'/>");
				 $("#namevalidate"+id).val("false");
				return ;
			}else{
				$("#salePriceSpan"+id).remove();
				 $("#"+id).css("border","1px solid #2B542C");     
				 $("#namevalidate"+id).val("true");
			}
		}
	}
	
	 $(".btn-primary").click(function(){
 		var n1 =  $("#namevalidatecategoryName1").val();
 		var n2 =  $("#namevalidatecategoryName2").val();
 		var n3 =  $("#namevalidatecategoryName3").val();
 		var n4 =  $("#namevalidatecategoryName4").val();
 		var n5 =  $("#namevalidatecategoryName5").val();
 		var n6 =  $("#namevalidatecategoryName6").val();
 		var n7 =  $("#namevalidatecategoryName7").val();
 		var n8 =  $("#namevalidatecategoryName8").val();
 		var n9 =  $("#namevalidatecategoryName9").val();
 		var n10 =  $("#namevalidatecategoryName10").val();
 		if(n1=="false" || n2=="false"|| n3=="false" || n4=="false" || n5=="false" || n6=="false" || n7=="false" || n8=="false" || n9=="false" || n10=="false" ){
			return;
		}
			$("#mainForm").submit();
		 });
</script>
