<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/import/AddImport.jsp" %>
<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/validation/jquery.validate.min.js"></script>
<!-- 美化单选、复选框样式 -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/storeb	ack/css/plugins/icheck/all.css">
	<!-- 单选、复选框的美化js -->
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/icheck/jquery.icheck.min.js"></script>
	<!-- 单选复选框美化样式的调用js -->
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/aebiz/aebiz.checkbox.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/bootbox/jquery.bootbox.js"></script>
	<!-- CKEditor 富文本编辑器-->
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/ckeditor/ckeditor.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.validate.expand.js"></script>
		<!-- 调用validate表单验证的js -->
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/aebiz/aebiz.validate.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/storeback/js/ajaxfileupload.js"></script>
</head>

<body>
	<!-- 头部 -->
	<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeHead.jsp"%>
	<div class="container-fluid" id="content">
	<!-- 左侧 -->
	<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/storeLeft.jsp"%>
		<div id="main">
			<div class="container-fluid">
				<div class="page-header">
					<div class="pull-left">
						<h1><aebiz:showTitle titleId="productmain.moduleName_CN.topTitle" /></h1>
					</div>
					<div class="pull-right">
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
					</div>
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
				<form:form id="mainForm" action="${pageContext.servletContext.contextPath}/store/product/update" method="POST" commandName="m"  class='form-horizontal form-validate form-bordered'  >
				<div class="box box-bordered">
					<div class="box-title">
						<h3><i class="fa fa-list-ul"></i><aebiz:showTitle titleId="productmain.moduleName_CN.title1" /></h3>
					</div>
					<div class="box-content nopadding">
							<input type="hidden" name="productMain.categoryUuid"  value="${web.categoryUuid}" />
							<form:hidden path="productMain.uuid" id="productUuid"  />
							<input type="hidden" name="productImage.mainImage" />
							<input type="hidden" name="productMain.productType" value="${web.type}"/>
							<input type="hidden" name="isRoot" id="isRoot"/>
								<div class="form-group">
									<label for="firstname" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.categoryUuid" /></label>
									<div class="col-sm-10">
										<div class="y_selectlb">
											 ${web.categoryName}<a href="javascript:selectCategory();" >重新选择</a>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label for="firstname" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.productName" /></label>
									<div class="col-sm-10">
										<form:input path="productMain.productName" class='form-control' data-rule-required="true" data-rule-maxlength="50"  />
									</div>
								</div>

								<div class="form-group">
									<label for="anotherelem" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.productNo" /></label>
									<div class="col-sm-10">
										<div class="col-sm-5">
											<form:input path="productMain.productNo" class='form-control' data-rule-required="true" data-rule-maxlength="20" />
										</div>
									</div>
								</div>
								<div class="form-group">
									<label for="additionalfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.state" /></label>
									<div class="col-sm-10">
										<div class="check-line col-xs-4 col-sm-2">
											<form:radiobutton path="productMain.state" class='icheck-me' data-skin="minimal" value="1" />
											<label class='inline' for="c3"><aebiz:showTitle titleId="productmain.m.stateValue1" /></label>
										</div>
										<div class="check-line col-xs-4 col-sm-2">
											<form:radiobutton path="productMain.state" class='icheck-me' data-skin="minimal" value="0" />
											<label class='inline' for="c4"><aebiz:showTitle titleId="productmain.m.stateValue2" /></label>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmainbaseprice.m.marketPrice" /></label>
									<div class="col-sm-10">
										<div class="input-group col-sm-2">
											<span class="input-group-addon"><aebiz:showTitle titleId="pub.moneytype" /></span>
											<form:input path="productPrice.marketPrice" class='form-control' data-rule-required="true" data-rule-number="true" data-rule-maxlength="15" placeholder="0"  />
										</div>
									</div>
								</div>
								<div class="form-group">
									<label for="textfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmainbaseprice.m.shopPrice" /></label>
									<div class="col-sm-10">
										<div class="input-group col-sm-2">
											<span class="input-group-addon"><aebiz:showTitle titleId="pub.moneytype" /></span>
											<form:input path="productPrice.shopPrice" class='form-control' data-rule-required="true" data-rule-number="true" data-rule-maxlength="15" placeholder="0"  />
										</div>
									</div>
								</div>

								<div class="form-group" >
									<label for="additionalfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.productStock" /></label>
									<div class="col-sm-10">
										<div class="col-sm-2">
											<input name="stock" class='form-control' data-rule-required="true" data-rule-positivenum="true" data-rule-maxlength="5" value="${m.productMain.stock}" />
										</div>
									</div>
								</div>

								<div class="form-group" >
									<label for="additionalfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.integral" /></label>
									<div class="col-sm-10">
										<div class="col-sm-2">
											<form:input path="productMain.integral" class='form-control'  data-rule-required="true" data-rule-positivenum="true" data-rule-maxlength="10" />
										</div>
										<div class="col-sm-10">
											<p class="y_input-groupts font-green"><aebiz:showTitle titleId="productmain.m.integralNote" /></p>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label for="additionalfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.leastIntegral" /></label>
									<div class="col-sm-10">
										<div class="col-sm-2">
											<form:input path="productMain.leastIntegral" class='form-control'  data-rule-required="true" data-rule-positivenum="true" data-rule-maxlength="10" />
										</div>
										<div class="col-sm-10">
											<p class="y_input-groupts font-green"><aebiz:showTitle titleId="productmain.m.leastIntegralNote" /></p>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label for="text" class="control-label col-sm-2"><aebiz:showTitle titleId="productmain.m.productBrandUuid" /></label>
									<div class="col-sm-10">
										<div class="col-sm-2">
											<input type="text" name="additionalfield" id="additionalfield" class="form-control">
										</div>
										<label for="textfield" class="control-label col-sm-1 y_formgpbtn">
											<a class="btn btn-primary" data-toggle="modal" data-target="#myModal1">
													选择
											</a>
										</label>
									</div>
								</div>

					</div>
				</div>

				<c:if test="${ !empty web.canSpce}">
				<div class="box box-bordered" id="attribute">
					<div class="box-title">
						<h3><i class="fa fa-list-ul"></i>商品规格信息</h3>
					</div>
					<div class="box-content nopadding">
								<div class="form-group">
									<label class="control-label col-sm-2">字段一</label>
									<div class="col-sm-10">
										<input type="hidden" name="attributeUuids"  id="attributeUuids" value="${web.attributeUuids}" />
										<c:forEach items="${web.colorAttribute}" var="data" varStatus="num">
										<strong class="col-sm-12 mb_5">${data.attributeName}：</strong>
										<input type="hidden" name="attributeName0" id="attributeName0" value="${data.attributeUuid}-${data.attributeName}"/>
										<input type="hidden" name="attributeColorUuid" id="attributeColorUuid" value="${data.attributeUuid}"/>
										<div class="col-sm-12">
											<div class="addProduct">
												<ul>
													<c:forEach items="${data.specList}" var="mValue" varStatus="num">
													<li>
														<div class="checkbox">
															<input type="checkbox" name="${data.attributeUuid}selectValue" value="${mValue.uuid}" ${mValue.select} onclick="javascript:selectAttrValue('${data.attributeUuid}','0','color')" >
															<label style="background:#5d762a;"></label>
															<input type="text" name="${data.attributeUuid}${mValue.uuid}name" id="${data.attributeUuid}${mValue.uuid}name" value="${mValue.value}" onblur="javascript:selectAttrValue('${data.attributeUuid}','0','color')">
														</div>
													</li>
													</c:forEach>
												</ul>
											</div>
										</div>
										</c:forEach>
										<div class="col-sm-6 picPro" id="colorImage">

										</div>
										<input type="hidden" name="nameValue0" id="nameValue0"/>
										<input type="hidden" name="nameValue1" id="nameValue1"/>
										<input type="hidden" name="nameValue2" id="nameValue2"/>

										<input type="hidden" name="attributeNames" id="attributeNames"/>

										<c:forEach items="${web.specAttributes}" var="data" varStatus="num">
											<strong class="col-sm-12 mb_5">${data.attributeName}：</strong>
											<input type="hidden" name="attributeName${num.index+1}" id="attributeName${num.index+1}" value="${data.attributeUuid}-${data.attributeName}"/>
											<div class="col-sm-12">
												<div class="addProduct y_produsize">
													<ul>
														<c:forEach items="${data.specList}" var="mValue" varStatus="num1">
														<li>
															<div class="checkbox">
																<input type="checkbox" name="${data.attributeUuid}selectValue" value="${mValue.uuid}" ${mValue.select} onclick="javascript:selectAttrValue('${data.attributeUuid}','${num.index+1}')">
																<input type="text" name="${data.attributeUuid}${mValue.uuid}name" id="${data.attributeUuid}${mValue.uuid}name" value="${mValue.value}" onblur="javascript:selectAttrValue('${data.attributeUuid}','${num.index+1}')">
															</div>
														</li>
														</c:forEach>
													</ul>
												</div>
											</div>
										</c:forEach>
										<div class="col-sm-12 picPro" id="showTable">

										</div>
									</div>
								</div>
								<c:forEach items="${web.attributes}" var="data" varStatus="num">
									<input type="hidden" name="noAttributeUuid" value="${data.attributeUuid}" />	
									<input type="hidden" name="enName${data.attributeUuid}" value="${data.attributeEnName}" />
									<input type="hidden" name="zhName${data.attributeUuid}" value="${data.attributeName}" />
									<div  class="form-group " >
										<label for="additionalfield" class="control-label col-sm-2">${data.attributeName}</label>
										<div class="col-sm-10">
											<c:if test="${data.attributeType=='01'}">
												<!-- 文本框 -->
												<input type="text" name="name${data.attributeUuid}" class="form-control" value="${data.attributeValue}">
											</c:if>
											<c:if test="${data.attributeType=='02'}">
												<!-- 下拉框 -->
												<select name="name${data.attributeUuid}"  class="form-control">
													<option value="">-- 请选择 --</option>
													<c:forEach items="${data.specList}" var="data1" varStatus="num">
													<option value="${data1.value}" <c:if test="${data1.value==data.attributeValue}">selected</c:if>>${data1.value}</option>	
													</c:forEach>	
												</select>
											</c:if>
											<c:if test="${data.attributeType=='03'}">
												<!-- 单选框 -->
												<c:forEach items="${data.specList}" var="data1" varStatus="num">
													<label class="y_iptlabel"><input type="radio" name="name${data.attributeUuid}" value="${data1.value}" <c:if test="${data1.value==data.attributeValue}">checked</c:if>/>${data1.value}</label>
												</c:forEach>	
											</c:if>
											<c:if test="${data.attributeType=='04'}">
												<!-- 复选框 -->
												<c:forEach items="${data.specList}" var="data1" varStatus="num" >
													<label class="y_iptlabel"><input type="checkbox" name="name${data.attributeUuid}" value="${data1.value}" <c:if test="${data1.value==data.attributeValue}">checked</c:if> />${data1.value}</label>
												</c:forEach>	
											</c:if>
										</div>
									</div>
								</c:forEach>
					</div>
				</div>
				</c:if>
				<script>
					function showColorImage(attributeUuid){
						var checkIds="";
						$("input[name='"+attributeUuid+"selectValue']:checkbox").each(function() {
							if ($(this).is(":checked")) {
								checkIds += $(this).val() + "-"+ $("#"+attributeUuid+$(this).val()+"name").val()+",";
							}
						})
						var productUuid=$.trim($("#productUuid").val());
						//展示选中的属性值列表
						$.get(
							"${pageContext.servletContext.contextPath}/store/productcolorimage/showColorList",
							{"selectOne" : checkIds,
								"attributeUuid":attributeUuid,
								"productUuid":productUuid,
								ranNum : Math.random()
							},
							function(data) {
								  $("#colorImage").html(data);
						  }

						);
					}
					function initAttr(){
						var attributeUuids=$("#attributeUuids").val();
						if(attributeUuids==undefined || attributeUuids==""){
							return;
						}
						var attributeUuid=attributeUuids.split(",");

						for(var i=0;i<attributeUuid.length;i++){
							var checkIds="";
							$("input[name='"+attributeUuid[i]+"selectValue']:checkbox").each(function() {
								if ($(this).is(":checked")) {
									checkIds += $(this).val() + "-"+ $("#"+attributeUuid[i]+$(this).val()+"name").val()+";";
								}
							})

							$("#nameValue"+i).val(checkIds);

							$("#attributeNames").val("");
							if($.trim($("#nameValue0").val())!=""){
								$("#attributeNames").val($("#attributeName0").val()+",");
								var attributeColorUuid=$("#attributeColorUuid").val()
								showColorImage(attributeColorUuid);
							}

							if($.trim($("#nameValue1").val())!=""){
								$("#attributeNames").val($("#attributeNames").val()+$("#attributeName1").val()+",");
							}

							if($.trim($("#nameValue2").val())!=""){
								$("#attributeNames").val($("#attributeNames").val()+$("#attributeName2").val()+",");
							}
						}


						var dataParam = new Array();
						dataParam.push({
							"name" : "attributeNames",
							"value" : $.trim($("#attributeNames").val())
						});
						dataParam.push({
							"name" : "nameValue0",
							"value" : $.trim($("#nameValue0").val())
						});

						dataParam.push({
							"name" : "nameValue1",
							"value" : $.trim($("#nameValue1").val())
						});

						dataParam.push({
							"name" : "nameValue2",
							"value" : $.trim($("#nameValue2").val())
						});
						dataParam.push({
							"name" : "productUuid",
							"value" : $.trim($("#productUuid").val())
						});

						//展示选中的属性值列表
						$.get(
							"${pageContext.servletContext.contextPath}/store/product/showSpec",
							{aoData : JSON.stringify(dataParam)},
							function(data) {
								  $("#showTable").html(data);
						  }

						);
					}

					function selectAttrValue(attrUuid,index,type){
						var checkIds="";
						$("input[name='"+attrUuid+"selectValue']:checkbox").each(function() {
							if ($(this).is(":checked")) {
								checkIds += $(this).val() + "-"+ $("#"+attrUuid+$(this).val()+"name").val()+";";
							}
						})

						$("#nameValue"+index).val(checkIds);

						if(type=="color"){
							showColorImage(attrUuid);
						}

						$("#attributeNames").val("");
						if($.trim($("#nameValue0").val())!=""){
							$("#attributeNames").val($("#attributeName0").val()+",");
						}

						if($.trim($("#nameValue1").val())!=""){
							$("#attributeNames").val($("#attributeNames").val()+$("#attributeName1").val()+",");
						}

						if($.trim($("#nameValue2").val())!=""){
							$("#attributeNames").val($("#attributeNames").val()+$("#attributeName2").val()+",");
						}
						var dataParam = new Array();
						dataParam.push({
							"name" : "attributeNames",
							"value" : $.trim($("#attributeNames").val())
						});
						dataParam.push({
							"name" : "nameValue0",
							"value" : $.trim($("#nameValue0").val())
						});

						dataParam.push({
							"name" : "nameValue1",
							"value" : $.trim($("#nameValue1").val())
						});

						dataParam.push({
							"name" : "nameValue2",
							"value" : $.trim($("#nameValue2").val())
						});

						dataParam.push({
							"name" : "productUuid",
							"value" : $.trim($("#productUuid").val())
						});
						//展示选中的属性值列表
						$.get(
							"${pageContext.servletContext.contextPath}/store/product/showSpec",
							{aoData : JSON.stringify(dataParam)},
							function(data) {
								  $("#showTable").html(data);
						  }

						);
					}

				</script>

				<div class="box box-bordered">
					<div class="box-title">
						<h3><i class="fa fa-list-ul"></i><aebiz:showTitle titleId="productmaindescription.moduleName_CN" /></h3>
					</div>
					<div class="box-content nopadding">
						<div class="form-group">
									<label for="additionalfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmaintransfer.m.weight" /></label>
									<div class="col-sm-10">
										<div class="col-sm-2">
											<form:input path="productTransfer.weight"  data-rule-required="true" data-rule-number="true" data-rule-maxlength="10"  class='form-control'  />
											<span class="font-green">
												kg
											</span>
										</div>
									</div>
								</div>
								<div class="form-group" >
									<label for="additionalfield" class="control-label col-sm-2"><aebiz:showTitle titleId="productmaintransfer.m.volume" /></label>
									<div class="col-sm-10">
										<div class="col-sm-2 y_iptlabel">
											<form:input path="productTransfer.length"  data-rule-required="true" data-rule-number="true" data-rule-maxlength="10"  class='form-control'  />
											<span class="font-green">
												<aebiz:showTitle titleId="productmaintransfer.m.length" />/cm
											</span>
										</div>
										<div class="col-sm-2 y_iptlabel">
											<form:input path="productTransfer.width"  data-rule-required="true" data-rule-number="true" data-rule-maxlength="10"  class='form-control'  />
											<span class="font-green">
												<aebiz:showTitle titleId="productmaintransfer.m.width" />/cm
											</span>
										</div>
										<div class="col-sm-2 y_iptlabel">
											<form:input path="productTransfer.height"  data-rule-required="true" data-rule-number="true" data-rule-maxlength="10"  class='form-control'  />
											<span class="font-green">
												<aebiz:showTitle titleId="productmaintransfer.m.height" />/cm
											</span>
										</div>
									</div>
								</div>

								<div class="form-group " >
									<label for="text" class="control-label col-sm-2"><aebiz:showTitle titleId="productmaintransfer.m.transferTemplate" /></label>
									<div class="col-sm-10">
										<select name="transferTemplate" id="gend">
											<option value="">-- 请选择 --</option>
										</select>
									</div>
								</div>


					</div>
				</div>

				<div class="box box-bordered">
					<div class="box-title">
						<h3><i class="fa fa-list-ul"></i><aebiz:showTitle titleId="productmainimage.moduleName_CN" /></h3>
					</div>
					<div class="box-content nopadding">
								<div class="form-group">
									<label for="text" class="control-label col-sm-2"><aebiz:showTitle titleId="productmainimage.m.mainImage" /></label>
									<div class="col-sm-10">
										<div class="box">
											<div class="box-title">
												<ul class="tabs">
													<li class="active">
														<a href="#t7" data-toggle="tab"><aebiz:showTitle titleId="productmainimage.m.localSendImage" /></a>
													</li>
													<li>
														<a href="#t8" data-toggle="tab"><aebiz:showTitle titleId="productmainimage.m.selectImageSpace" /></a>
													</li>
												</ul>
											</div>
											<div class="box-content nopadding">
												<div class="tab-content">
													<div class="tab-pane active" id="t7">
														<dl class="select_pic">
															<dt><aebiz:showTitle titleId="productmainimage.m.selectLocalImage" />：</dt>
															<dd>
																<div class="fileinput fileinput-new" data-provides="fileinput">
																	<span class="btn btn-default btn-file">
																		<span class="fileinput-new"><i class="fa fa-cloud-upload"></i> <aebiz:showTitle titleId="productmainimage.m.fileUpload" /></span>
																	<span class="fileinput-exists">Change</span>
																	<input type="file" name="myfiles" id="uploadFiles" onchange="uploadImage()">
																	</span>
																	<span class="fileinput-filename"></span>
																	<a href="#" class="close fileinput-exists" data-dismiss="fileinput" style="float: none">&times;</a>
																</div>
																<div class="select_pic_ts">
																		<b><aebiz:showTitle titleId="productmainimage.m.fileUploadPrompt" />：</b><br />
																		1、<aebiz:showTitle titleId="productmainimage.m.fileUploadPrompt1" /><br />
																		2、<aebiz:showTitle titleId="productmainimage.m.fileUploadPrompt2" /><br />
																		3、<aebiz:showTitle titleId="productmainimage.m.fileUploadPrompt3" />
																	</div>
															</dd>
														</dl>

													</div>
													<div class="tab-pane" id="t8">

													</div>
													<div class="row w_piclist_row">
															<input type="hidden" name="count" id="count" value="${web.imageCount}" >
															<ul class="pro_pic_list" id="showPic">
																<c:if test="${!empty m.productImage}" >
																	<li class="test" name='1'>
																		<div class="w_has_img">
																			<img src="${m.productImage.bigImageUrl}" />
																			<input type="hidden" name="imgName1" value="${m.productImage.bigImage}" />
																			<div class="cz">
																				<i class="glyphicon-left_arrow" onclick="javascript:lastChange(this)"></i>
																				<i class="glyphicon-right_arrow" onclick="javascript:nextChange(this)"></i>
																				<i class="glyphicon-remove_2" onclick="javascript:removeChange(this)"></i>
																			</div>
																			<div class="bg"></div>
																	  </div>
																 </li>
																</c:if>
																<c:forEach items="${m.productMultiImage}" var="data" varStatus="num">
																	<li class="test" name='${num.index+2}'>
																		<div class="w_has_img">
																			<img src="${data.bigImageUrl}" />
																			<input type="hidden" name="imgName${num.index+2}" value="${data.bigImage}" />
																			<div class="cz">
																				<i class="glyphicon-left_arrow" onclick="javascript:lastChange(this)"></i>
																				<i class="glyphicon-right_arrow" onclick="javascript:nextChange(this)"></i>
																				<i class="glyphicon-remove_2" onclick="javascript:removeChange(this)"></i>
																			</div>
																			<div class="bg"></div>
																	  </div>
																 </li>
																</c:forEach>
															</ul>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label for="text" class="control-label col-sm-2"><aebiz:showTitle titleId="productmaindescription.m.note" /></label>
									<div class="col-sm-10">
										<form:textarea path="productDescription.note" class='form-control'  rows="7" placeholder="You can provide additional information in here..." data-rule-required="true" data-rule-maxlength="200" />
									</div>
								</div>
								<div class="form-group">
									<label for="text" class="control-label col-sm-2"><aebiz:showTitle titleId="productmaindescription.m.description" /></label>
									<div class="col-sm-10">
										<textarea name="productDescription.description" class='ckeditor2 span12' rows="5"></textarea>
									</div>
								</div>

								<div class="y_fixedbtn">
									<button type="submit" class="btn btn-primary btn-large a_size_1"><aebiz:showTitle titleId="productmain.m.release" /></button>
									<a href="${pageContext.request.contextPath}/store/product/toList" class="btn  btn-large a_size_1"><aebiz:showTitle titleId="basebusiness.showmessage.return" /></a>
								</div>

					</div>
				</div>
				</form:form>
				</div>
		</div>
	</div>

	<script>
		function selectCategory(){
			var uRl1="${pageContext.request.contextPath}/store/product/selectCategory?productType=${m.productMain.productType}&productUuid=${m.productMain.uuid}"; 
			window.location.href=uRl1;
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
			initAttr();

			//调用文本编辑器插件
			if ($(".ckeditor2").length > 0) {
		    CKEDITOR.replace("productDescription.description");
		  };
	    //添加商品 选择商品主分类时关闭事件
	    $(".y_selectlb").click(function(){
	    	$(".y_choselbbx").show();
	    })
		})
	</script>


</body>
</html>
