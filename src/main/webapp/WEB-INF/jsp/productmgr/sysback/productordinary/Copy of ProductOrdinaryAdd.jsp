<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!doctype html>
<html>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>

<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script> 
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/datatable/jquery.dataTables.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.passwdcheck.js"></script> 

</head>
<body>

			<div class="container-fluid">
				<div class="page-header">
					<div class="pull-left">
						<h1><aebiz:showTitle titleId="productmain.moduleName_CN.topTitle" /></h1>
					</div>
					<!--<div class="pull-right">
						<ul class="minitiles">
							<li class='grey'>
								<a href="#">
									<i class="fa fa-cogs"></i>
								</a>
							</li>
							<li class='lightgrey'>
								<a href="#">
									<i class="fa fa-globe"></i>
								</a>
							</li>
						</ul>
						<ul class="stats">
							<li class='satgreen'>
								<i class="fa fa-money"></i>
								<div class="details">
									<span class="big">$324,12</span>
									<span>Balance</span>
								</div>
							</li>
							<li class='lightred'>
								<i class="fa fa-calendar"></i>
								<div class="details">
									<span class="big">February 22, 2013</span>
									<span>Wednesday, 13:56</span>
								</div>
							</li>
						</ul>
					</div>-->
					
				</div>
				<div class="breadcrumbs">
					<ul>
						<li>
							<span><aebiz:showTitle titleId="productmain.menuOne" /></span>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<span><aebiz:showTitle titleId="productmain.moduleName_CN.topTitle1" /></span>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<span><aebiz:showTitle titleId="productmain.moduleName_CN" /></span>
						</li>
					</ul>
				</div>
				<form action="${pageContext.servletContext.contextPath}/sysback/product/save" method="POST" class='form-horizontal form-validate form-bordered' id="mainForm">
				<div class="box box-bordered">
					<div class="box-title">
						<h3><i class="fa fa-list-ul"></i><aebiz:showTitle titleId="productmain.moduleName_CN.title1" /></h3>
					</div>
					<div class="box-content nopadding">
							<input type="hidden" name="productMain.categoryUuid"  value="${web.categoryUuid}" />
							<input type="hidden" name="productImage.mainImage" />
							<input type="hidden" name="productMain.productType" value="${web.type}"/>
							<input type="hidden" name="isRoot" id="isRoot"/>
								<div class="form-group">
									<label for="firstname" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.categoryUuid" /></label>
									<div class="col-sm-10">
										<div class="y_selectlb">
											 ${web.categoryName}<a href="javascript:selectCategory();" >&nbsp;&nbsp;&nbsp;重新选择</a>
										</div>
									</div>
								</div>
								
								<div class="form-group">
									<label for="firstname" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.productName" /></label>
									<div class="col-sm-10">
										<input type="text" name="productMain.productName"  data-rule-required="true" data-rule-maxlength="50" class="form-control">
									</div>
								</div>
								
								<div class="form-group">
									<label for="anotherelem" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.productNo" /></label>
									<div class="col-sm-10">
										<div class="col-sm-5">
											<input type="text" name="productMain.productNo"  data-rule-required="true" data-rule-maxlength="20" id="anotherelem" class="form-control">
											
											<input type="hidden" name="nameHidden" value="true">
										
										</div>
									</div>
								</div>
								<div class="form-group">
									<label for="additionalfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.state" /></label>
									<div class="col-sm-10">
										<div class="check-line col-xs-4 col-sm-2">
											<input type="radio" id="c3" class='icheck-me' name="productMain.state" data-skin="minimal" value="1"  checked >
											<label class='inline' for="c3"><aebiz:showTitle titleId="productmain.m.stateValue1" /></label>
										</div>
										<div class="check-line col-xs-4 col-sm-2">
											<input type="radio" id="c4" class='icheck-me checked' name="productMain.state" data-skin="minimal"  value="0">
											<label class='inline' for="c4"><aebiz:showTitle titleId="productmain.m.stateValue2" /></label>
										</div>												
									</div>										
								</div>
								
									<div class="form-group">
									<label for="text" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.scope" /></label>
									<div class="col-sm-10">
										<div class="check-line col-sm-2">
											<input type="checkbox" id="c5" class='icheck-me' name="productMain.scope" value="01" data-skin="square" data-color="blue">
											<label class='inline' for="c5"><aebiz:showTitle titleId="productmain.scope.app" /></label>
										</div>
										<div class="check-line col-sm-2">
											<input type="checkbox" id="c6" class='icheck-me' name="productMain.scope"   value="02" data-skin="square" data-color="blue" checked>
											<label class='inline' for="c6"><aebiz:showTitle titleId="productmain.scope.web" /></label>
										</div>
									</div>
								</div>
								
								<div class="form-group">
									<label for="firstname" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.chargetype" /></label>
									<div class="col-sm-10">
										<div class="col-sm-3">
											<select name="productPrice.chargetype" class='select2-me form-control' data-rule-required="true">								
												<option value="">-<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>-</option>
												<c:forEach items="${chargeTypes}" var="chargeType">
													<option value="${chargeType.key}">${chargeType.value}</option>
												</c:forEach>										
											</select>	
										</div>
									</div>
								</div>
								<div class="form-group">
									<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmainbaseprice.m.shopPrice" /></label>
									<div class="col-sm-10">
										<div class="input-group col-sm-2">
											<span class="input-group-addon"><aebiz:showTitle titleId="pub.moneytype" /></span>
											<input type="text" name="productPrice.shopPrice" data-rule-required="true" data-rule-number="true" data-rule-maxlength="15" placeholder="0" class='form-control'>
										</div>
									</div>
								</div>
								
								<%-- <div class="form-group" >
									<label for="additionalfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.integral" /></label>
									<div class="col-sm-10">
										<div class="col-sm-2">
											<input type="text" name="productMain.integral" data-rule-required="true" data-rule-positivenum="true" data-rule-maxlength="10" class="form-control">
										</div>
										<div class="col-sm-10">
											<p class="y_input-groupts font-green"><aebiz:showTitle titleId="productmain.m.integralNote" /></p>
										</div>
									</div>
								</div> --%>
					</div>
			
				
				
				<script>
					function showColorImage(attributeUuid){
						var checkIds="";
						$("input[name='"+attributeUuid+"selectValue']:checkbox").each(function() {
							if ($(this).is(":checked")) {
								checkIds += $(this).val() + "-"+ $("#"+attributeUuid+$(this).val()+"name").val()+",";
							}
						})
						
						//展示选中的属性值列表
						$.get(
							"${pageContext.servletContext.contextPath}/store/productcolorimage/showColorList",
							{"selectOne" : checkIds,
								"attributeUuid":attributeUuid,
								"productUuid":"",
								ranNum : Math.random()
							},
							function(data) {
								  $("#colorImage").html(data);
						  }
							 
						);
					}
					
					
					$("input[name='productMain.productNo']").blur(function(){
						$("span[name='namespan']").remove();
						var productNo=$("input[name='productMain.productNo']").val();
						var nameReg = /^[0-9a-zA-Z]*$/g;
						if(productNo!=''&&nameReg.test(productNo)){
							$("#nameTip").empty();
							var url="${pageContext.servletContext.contextPath}/sysback/product/checkProductNo";
							$.get(url,{checkProductNo:productNo,ranNum:Math.random()},function(data){
								if(data=="true"){
									$("input[name='productMain.productNo']").after("<span name='namespan'><font color='red'><aebiz:showTitle titleId="productMain.productNo.existed" /></font></span>");
									$("input[name='nameHidden']").val("false");
								}else{
									$("input[name='nameHidden']").val("true");
									$("span[name='namespan']").remove();
								}
							});
						}else{
							$("input[name='productMain.productNo']").after("<span name='namespan'><font color='red'><aebiz:showTitle titleId="productMain.productNo.error" /></font></span>");
							$("input[name='nameHidden']").val("false");
						}
					});
				</script>
				
				
				<div class="box box-bordered">
					<div class="box-title">
						<h3><i class="fa fa-list-ul"></i><aebiz:showTitle titleId="productmainimage.moduleName_CN" /></h3>
					</div>
					<div class="box-content nopadding">
								<div class="form-group">
									<label for="text" class="control-label col-sm-2"><aebiz:showTitle titleId="productmaindescription.m.note" /></label>
									<div class="col-sm-10">
										<textarea name="productMain.note" class="form-control" rows="7" placeholder="You can provide additional information in here..."></textarea>
									</div>
								</div>
								
								<div class="y_fixedbtn">
									<button  type="button"  class="btn submit btn-primary btn-large a_size_1"><aebiz:showTitle titleId="productmain.m.release" /></button>		
									<a href="${pageContext.request.contextPath}/sysback/product/toStep1" class="btn  btn-large a_size_1"><aebiz:showTitle titleId="basebusiness.showmessage.return" /></a>												
								</div>
					</div>
				</div>
				</form>
				</div>
		</div>
	</div>
	
	<script>
		
		function selectCategory(){
			var uRl1="${pageContext.request.contextPath}/sysback/product/toStep1"; 
			window.location.href=uRl1;
		}
		
		function removeImage(attrValueUuid){
			$("#"+attrValueUuid+"image").html("");
		}
		function uploadColorImage(attrValueUuid ) {	 
   		 $.ajaxFileUpload({
       		url: '${pageContext.request.contextPath}/store/productcolorimage/uploadFile/'+attrValueUuid, //用于文件上传的服务器端请求地址
        	secureuri: false, //是否需要安全协议，一般设置为false
        	fileElementId: 'uploadColorFiles'+attrValueUuid, //文件上传域的ID
        	dataType: 'text', //返回值类型 一般设置为json
        	success: function (data, status){ 
        		$("#"+attrValueUuid+"image").html(data);
      		 	$("input[name='colorImage']").attr("name","colorImage"+attrValueUuid);
        	},
	        error: function (data, status, e){
	        	//服务器响应失败处理函数            
	        }
       })                  
       
       return false;
	  }
	   	
		
		function sortUl () {
            var divTestJQ = $("#showPic"); //取得容器对象
            var divJQ = $(">li.test", divTestJQ); //取容器需要重排的对象
            var EntityList = []; //定义一个数组用于存放要排序的对象
            divJQ.each(function () {
                var thisJQ = $(this);
                EntityList.push({ Name: parseInt(thisJQ.attr("name"), 10), JQ: thisJQ }); //把要排序的对象和排序的值一起放到一个新的对象里，并存入到数组
            });
            EntityList.sort(function (a, b) { //利用数组的排序方法重新排序对象
                //return b.Name - a.Name; //从大到小
               return a.Name - b.Name;
            });
            for (var i = 0; i < EntityList.length; i++) {
                EntityList[i].JQ.appendTo(divTestJQ); //把排序完的对象重新插入到容器对象
            };
    } 
		
		function uploadImage() {	 
   		 $.ajaxFileUpload({
       		url: '${pageContext.request.contextPath}/store/product/uploadFile', //用于文件上传的服务器端请求地址
        	secureuri: false, //是否需要安全协议，一般设置为false
        	fileElementId: 'uploadFiles', //文件上传域的ID
        	dataType: 'text', //返回值类型 一般设置为json
        	success: function (data, status){ 
      		 	$("#showPic").append(data);
      		 	var count = $("#count").val();
      		 	$("#count").val(++count);
      		 	$("li[name='0']").attr("name",$("#count").val());
      		 	$("input[name='imgName']").attr("name","imgName"+$("#count").val());
        	},
	        error: function (data, status, e){
	        	//服务器响应失败处理函数            
	        }
       })                  
       
       return false;
	  }
	   	
		function lastChange(obj){
	  	 var thisValue=$(obj).parents(".test").attr("name");
	  	 if( thisValue==0){
	  	 	return;
	  	 }
	  	 var lastObj= $("li[name='"+(parseInt(thisValue)-1)+"']");
	  	 var thisObj= $("li[name='"+(thisValue)+"']");
	  	 var lastInput= $("input[name='imgName"+(parseInt(thisValue)-1)+"']");
	  	 var thisInput= $("input[name='imgName"+thisValue+"']");
	  	 
	  	 lastObj.attr("name",thisValue);
	  	 thisObj.attr("name",parseInt(thisValue)-1);
	  	 lastInput.attr("name","imgName"+thisValue);
	  	 thisInput.attr("name","imgName"+(parseInt(thisValue)-1));
	  	 sortUl();
		}
		function removeChange(obj){
			 var thisValue=$(obj).parents(".test").attr("name");
			 $("li[name='"+(thisValue)+"']").remove();
			 var max=$("#count").val();
			 $("#count").val(max-1);
			 if(thisValue==max){
			 		return ;
			}
			 for(var i=thisValue;i<=max;i++){
			 	 var nextObj= $("li[name='"+(parseInt(i)+1)+"']");
			 	 var nextInput= $("input[name='imgName"+(parseInt(i)+1)+"']");
			 	 
			 	 nextObj.attr("name",(parseInt(i)));
	    	 nextInput.attr("name","imgName"+i);
			 }
			 
			 
		}
		function nextChange(obj){
	    	 var max=$("#count").val();
	    	 var thisValue=$(obj).parents(".test").attr("name");
	    	 if(max==thisValue){
	    		return;
	    	 }
	    	 
	    	 var nextObj= $("li[name='"+(parseInt(thisValue)+1)+"']");
	    	 var thisObj= $("li[name='"+(thisValue)+"']");
	    	 var nextInput= $("input[name='imgName"+(parseInt(thisValue)+1)+"']");
	    	 var thisInput= $("input[name='imgName"+thisValue+"']");
	    	 
	    	 
	    	 nextObj.attr("name",thisValue);
	    	 thisObj.attr("name",(parseInt(thisValue)+1));
	    	 nextInput.attr("name","imgName"+thisValue);
	    	 thisInput.attr("name","imgName"+(parseInt(thisValue)+1));
	    	 sortUl();
		
		}
		
		 
		$(function(){
			//调用文本编辑器插件
			if ($(".ckeditor2").length > 0) {
		    CKEDITOR.replace("productDescription.description");
		  };
	    //添加商品 选择商品主分类时关闭事件
	    $(".y_selectlb").click(function(){
	    	$(".y_choselbbx").show();	
	    })
		})	
		
		
		
		 $(".submit").click(function(){
		 	var nameError=$("input[name='nameHidden']").val();
			if(nameError=="true"){
				$("#mainForm").submit();
			}
		 });
	</script>
	
	 
</body>
</html>
