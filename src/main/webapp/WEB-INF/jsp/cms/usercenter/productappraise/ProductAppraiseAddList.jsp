<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
	<!--[if IE 9 ]><html class="ie ie9" lang="en" class="no-js"> <![endif]-->
	<!--[if !(IE)]><!--><html lang="en" class="no-js"> <!--<![endif]-->
	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImport.jsp"%>	
		
</head>

<body>
	<!-- WRAPPER -->
	<div class="wrapper">
		<!-- TOP BAR 头部导航 -->
		<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterHead.jsp"%>
		<!-- /top -->
	
		<!-- BOTTOM: LEFT NAV AND RIGHT MAIN CONTENT -->
		<div class="bottom">
			<div class="container">
				<div class="row">
					<!-- left sidebar 左侧导航 -->
					<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterLeft.jsp"%>
					<!-- end left sidebar -->
					<!-- content-wrapper 右侧主体 -->
					<div class="col-xs-10 content-wrapper">
						<div class="row">
							<div class="col-sm-4 ">
								<ul class="breadcrumb">
									<li><i class="fa fa-home"></i><a href="#"><aebiz:showTitle titleId="productappraise.m.memberCenter"/></a></li>
									<li><a href="#"><aebiz:showTitle titleId="productappraise.m.orderManage"/></a></li> <li class="active"><aebiz:showTitle titleId="productappraise.m.orderApp"/></li>
								</ul>
							</div>
							<div class="col-sm-8 ">
								<div class="top-content">
									<ul class="list-inline quick-access">
										<li>
											<a href="charts-statistics-interactive.html">
												<span class="quick-access-item bg-color-green">
													<i class="fa fa-bar-chart-o"></i>
													<h5>CHARTS</h5><em>basic, interactive, real-time</em>
												</span>
											</a>
										</li>
										<li>
											<a href="page-inbox.html">
												<span class="quick-access-item bg-color-blue">
													<i class="fa fa-envelope"></i>
													<h5>INBOX</h5><em>inbox with gmail style</em>
												</span>
											</a>
										</li>
										<li>
											<a href="tables-dynamic-table.html">
												<span class="quick-access-item bg-color-orange">
													<i class="fa fa-table"></i>
													<h5>DYNAMIC TABLE</h5><em>tons of features and interactivity</em>
												</span>
											</a>
										</li>
									</ul>
								</div>
							</div>
						</div>
						
						<!-- main -->
						<div class="content">
							
							<div class="main-content">
								<div class="assesment-title">
									<aebiz:showTitle titleId="productappraise.m.appProduct"/>
								</div>
								<div class="widget">
									<div class="widget-header">
										<ul class="order-infor">
											<li class="order-time">${omm.orderTime} <span class="span-interval">|</span></li>
											<li class="order-number"><aebiz:showTitle titleId="productappraise.m.orderUuid"/>：${omm.orderId}  <span class="span-interval">|</span></li>
											<li class="store-name"><a href="#">${omm.storeName}</a></li>
											<li class="contact-service"><i class="fa fa-comments-o"></i></li>
										</ul>
									</div>
									<div class="widget-content assesment-container">
										
										<c:forEach items="${list}" var="webModel" varStatus="status">
										
											<div class="item-tr clearfix"><!-- item-tr star -->
												<div class="item-detial">
													<dl>
														<dt class="assesment-item-image"><a href="#"><img src="${webModel.productPicUrl}"/></a></dt>
														<dd class="assesment-item-detial">
															<p class="assesment-item-name"><a href="#">${webModel.odm.productName}</a></p>
															<p>
															   <span class="assesment-item-price"><i class="RMB-Symbol">￥</i>${webModel.odm.finalPrice}</span> 
															   <b class="assesment-item-multiple">x</b><span class="assesment-item-num">${webModel.odm.buyNum}</span>
															</p>
															<c:if test="${!empty webModel.odm.specList}">
																<p class="assesment-item-color">
																	<c:forEach items="${webModel.odm.specList}" var="specObj">
																		<span class="y_pdsta">${specObj.name}：${specObj.value}</span>
																	</c:forEach>																
																</p>
															</c:if>
														</dd>
													</dl>
												</div>
												
												
												<c:if test="${webModel.appraiseOrNo==0}">
													<div class="assesment-btn">
														<p class=""><button type="button" class="btn btn-primary btn-ms btn-toggle-expand1"><aebiz:showTitle titleId="productappraise.m.reportApp"/><i class="fa fa-chevron-up fa-chevron-down"></i></button></p>
													</div>
												</c:if>
												
												<c:if test="${webModel.appraiseOrNo==1}">
													<div class="deletedthis"><span><i class="fa fa-trash-o"></i></span></div>
													<div class="assesment-btn assesmented-btn">
														<p class="re-buy"><a class="btn btn-warning" href="${pageContext.request.contextPath}/usercenter/ordershow/toShare/${webModel.odm.uuid}"><aebiz:showTitle titleId="productappraise.m.reportOrderShow"/></a></p>
														<p class="see-assesmented"><span class="see-assesment btn-link btn-toggle-expand1"><aebiz:showTitle titleId="productappraise.m.viewApp"/><i class="fa fa-chevron-up fa-chevron-down"></i></span></p> 
													</div> 
												</c:if>	
													
												<c:if test="${webModel.appraiseOrNo==0}">
													<c:choose>
														<c:when  test="${status.index==0}">
															<div class="assesment-box assesbshow">
														</c:when >
														<c:otherwise>
															<div class="assesment-box">  
   													</c:otherwise>
   												</c:choose>   
													<div class="ascon-arrow-up"  id="loadProductApp${webModel.odm.uuid}"></div>
														<div class="assesment-content" id="removeProductApp${webModel.odm.uuid}">
															<div class="ff row shop-rating">
																<div class="col-mi-2 col-sm-2 text-right text-height"><aebiz:showTitle titleId="productappraise.m.appScore"/>:</div>
																<div class="col-mi-5 col-sm-5">
																	<ul class="rating-level" id="stars${status.index}">
																		<li><a class="one-star" star:value="1" href="#">1</a></li>
																		<li><a class="two-stars" star:value="2" href="#">2</a></li>
																		<li><a class="three-stars" star:value="3" href="#">3</a></li>
																		<li><a class="four-stars" star:value="4" href="#">4</a></li>
																		<li><a class="five-stars" star:value="5" href="#">5</a></li>
																	</ul>
																	<span class="result" id="stars${status.index}-tips"></span>
																	<input type="hidden" class="productScore${webModel.odm.uuid}" id="stars${status.index}-input" name="b" value="" size="2" />
																	<input type="hidden" name="starsAll" value="stars${status.index}" class="Stars${status.index}"/>
																</div> 
															</div>
															<div class="ff row ff-btm">
																<div class="col-mi-2 col-sm-2 text-right"><aebiz:showTitle titleId="productappraise.m.appTag"/>:</div>
																<div class="col-mi-9 col-sm-9">
																	<div class="simple-checkbox">
																		<c:forEach items="${webModel.tags}" var="tag" varStatus="idxStatus">
																		  <span class="simple-checkbox-box">
																				<input type="checkbox" name="checkbox${webModel.odm.uuid}" id="checkbox${idxStatus.index}" value="${tag.tagName}" class="parsley-validated">
																				<label for="checkbox${idxStatus.index}">${tag.tagName}</label>
																		  </span>
																	  </c:forEach>
																	  <span class="user-defined-label">
																	  	<span class="fa-icon"><i class="fa fa-edit"></i></span>
																			<div class="for1" name="userTags${webModel.odm.uuid}">
																			  <input type="text" name="textfield${status.index}" id="textfield${status.index}" class="tagsinput" value="">
																			</div>
																	  </span>
																	</div>
																</div>
															</div>
															<div class="ff row">
																<div class="col-mi-2 col-sm-2 text-right"><aebiz:showTitle titleId="productappraise.m.appContent"/>:</div>
																<div class="col-mi-9 col-sm-9">
																	<textarea name="content${webModel.odm.uuid}" id="content${webModel.odm.uuid}" maxlength="150" cols="110" rows="2" class="form-control textarea"></textarea>
																	<p class="text-right js-textarea-help"><span class="text-muted"></span></p>
																</div>
															</div>
															<div class="ff row">
																<div class="col-mi-2 col-sm-2 text-right"><!-- 提交按钮: --></div>
																<div class="col-mi-9 col-sm-9">
																	<button type="button" name="saveAppraise" id="${webModel.odm.uuid}" class="btn btn-primary"><aebiz:showTitle titleId="productappraise.m.submitApp"/></button>
																</div>
															</div>
														</div>
													</div>
												</c:if>
												
												<c:if test="${webModel.appraiseOrNo==1}">
													<c:choose>
														<c:when  test="${status.index==0}">
															<div class="assesment-box assesbshow">
														</c:when >
														<c:otherwise>
															<div class="assesment-box">  
   													</c:otherwise>
   												</c:choose>
													<div class="ascon-arrow-up"></div>
														<div class="assesment-content">
															<div class="assesment-innerbox clearfix">
																<div class="assesment-word">
																	<div class="custom-me">
																		<div class="title-photo">
																			<a href="#"><img src="${webModel.customerImage}"/></a>
																			<a href="#" class="custom-name">${webModel.pam.customerName}</a>
																		</div>
																		<div class="assesment-word">
																			<div class="grad-result">
																				<span class="result-text"><aebiz:showTitle titleId="productappraise.m.appScore"/>:</span>
																				<ul class="rating-level">
																					<li><a <c:if test="${webModel.pam.appScore==1}">class="actived1"</c:if> href="#">1</a></li>
																					<li><a <c:if test="${webModel.pam.appScore==2}">class="actived2"</c:if> href="#">2</a></li>
																					<li><a <c:if test="${webModel.pam.appScore==3}">class="actived3"</c:if> href="#">3</a></li>
																					<li><a <c:if test="${webModel.pam.appScore==4}">class="actived4"</c:if> href="#">4</a></li>
																					<li><a <c:if test="${webModel.pam.appScore==5}">class="actived5"</c:if> href="#">5</a></li>
																				</ul>	
																			</div>
																			<c:if test="${!empty m.tags}">	
																				<div class="label-result">
																				   <span class="result-text"><aebiz:showTitle titleId="productappraise.m.appTag"/>:</span>
																				   <div class="label-container btn-group">
																					   <c:forEach items="${webModel.pam.tags}" var="tag" >	
																				   	 		<button class="btn btn-success btn-xs" type="button">${tag}</button>
																				   	 </c:forEach>
																				   </div>
																				</div>
																			</c:if>	
																			<div class="text-reslut">
																				<p>${webModel.pam.appContent}</p>
																				<p class="write-time">${webModel.pam.appTime}</p>
																			</div>
																		</div>
																	</div>	<!-- end custom-me -->
																</div>
															</div>
														</div><!--  end assesment-content -->
													</div>	
												</c:if>
												
											</div><!-- item-tr end -->
										</c:forEach>
										
									</div> <!-- end assesment-container -->
								</div><!-- end widget -->
								
								<div class="assesment-title">
									<aebiz:showTitle titleId="productappraise.m.appStore"/>
								</div>
								<div class="widget widget-store">
									<div class="widget-header">
										<ul class="order-infor">
											<li class="store-name"><a href="#">${oawm.storeName}</a><span class="span-interval">|</span></li>
											<li class="order-number">${oawm.storeAddress} <span class="span-interval">|</span></li>
											<li class="contact-service"><i class="fa fa-comments-o"></i></li>
										</ul>
									</div>
									<div class="widget-content assesment-container">
										<div class="store-detials" id="loadOrderApp">
											<p class="assesment-store-image"><a href="#"><img src="${oawm.storeLogo}"/></a></p>
											<p class="assesment-store-name"><span class="store-detial-caption"><aebiz:showTitle titleId="productappraise.m.appStoreName"/>:</span><a href="#" class="store-detial-content">${oawm.storeName} </a></p>
											<p class="assesment-store-name"><span class="store-detial-caption"><aebiz:showTitle titleId="productappraise.m.appStoreScore"/>:</span><a href="#" class="store-detial-content">${averageScore}</a></p>
											<p class=""><span class="store-detial-caption"><aebiz:showTitle titleId="productappraise.m.appStoreMobile"/>:</span><span class="store-detial-content">${oawm.storeMobile}</span></p>
											<p class="collection-store"><button type="button" class="btn btn-primary btn-block"><i class="fa fa-heart"></i><aebiz:showTitle titleId="productappraise.m.collectionStore"/></button></p>
										</div>
										
										<c:if test="${empty oam}">
											<div class="store-assesment-content" id="removeOrderApp">
												<div class="store-ff">
													<div class="ff grad">
														<span class="ff-text"><aebiz:showTitle titleId="productappraise.m.storeScore"/>:</span>
														<div class="starbox">
														  <ul class="rating-level" id="starsS1">
																<li><a class="one-star" star:value="1" href="#">1</a></li>
																<li><a class="two-stars" star:value="2" href="#">2</a></li>
																<li><a class="three-stars" star:value="3" href="#">3</a></li>
																<li><a class="four-stars" star:value="4" href="#">4</a></li>
																<li><a class="five-stars" star:value="5" href="#">5</a></li>
															</ul>
															<span class="result" id="starsS1-tips"></span>
															<input type="hidden" id="starsS1-input" name="b" value="" size="2"/>
														</div>	
													</div>
													<div class="ff grad">
														<span class="ff-text"><aebiz:showTitle titleId="productappraise.m.speedScore"/>:</span>
														<div class="starbox">
														  <ul class="rating-level" id="starsS2">
																<li><a class="one-star" star:value="1" href="#">1</a></li>
																<li><a class="two-stars" star:value="2" href="#">2</a></li>
																<li><a class="three-stars" star:value="3" href="#">3</a></li>
																<li><a class="four-stars" star:value="4" href="#">4</a></li>
																<li><a class="five-stars" star:value="5" href="#">5</a></li>
															</ul>
															<span class="result" id="starsS2-tips"></span>
															<input type="hidden" id="starsS2-input" name="b" value="" size="2"/> 
														</div>
													</div>
												</div>
												<div  class="store-ff">
													<div class="ff grad">
														<span class="ff-text"><aebiz:showTitle titleId="productappraise.m.descScore"/>:</span>
														<div class="starbox">
														  <ul class="rating-level" id="starsS3">
																<li><a class="one-star" star:value="1" href="#">1</a></li>
																<li><a class="two-stars" star:value="2" href="#">2</a></li>
																<li><a class="three-stars" star:value="3" href="#">3</a></li>
																<li><a class="four-stars" star:value="4" href="#">4</a></li>
																<li><a class="five-stars" star:value="5" href="#">5</a></li>
															</ul>
															<span class="result" id="starsS3-tips"></span>
															<input type="hidden" id="starsS3-input" name="b" value="" size="2"/> 
														</div>	
													</div>
													<div class="ff grad">
														<span class="ff-text"><aebiz:showTitle titleId="productappraise.m.serviceScore"/>:</span>
														<div class="starbox">
														  <ul class="rating-level" id="starsS4">
																<li><a class="one-star" star:value="1" href="#">1</a></li>
																<li><a class="two-stars" star:value="2" href="#">2</a></li>
																<li><a class="three-stars" star:value="3" href="#">3</a></li>
																<li><a class="four-stars" star:value="4" href="#">4</a></li>
																<li><a class="five-stars" star:value="5" href="#">5</a></li>
															</ul>
															<span class="result" id="starsS4-tips"></span>
															<input type="hidden" id="starsS4-input" name="b" value="" size="2"/> 
														</div>
													</div>
												</div>
												<div class="store-ff">
													<div class="ff grad">
														<span class="ff-text"><aebiz:showTitle titleId="productappraise.m.appContent"/>:</span>
													</div>
													<div class="store-text">
														<textarea id="contentS" name="contentS" class="form-control" rows="3" cols="76" maxlength="99"></textarea>
														<p class="text-right"><span class="text-muted"><aebiz:showTitle titleId="productappraise.m.appContenLength"/></span></p>
													</div>
												</div>
												<p class="store-submit"><button type="button" id="orderApp" class="btn btn-primary"><aebiz:showTitle titleId="productappraise.m.submitApp"/></button></p>
											</div><!--  end store-assesment-content -->	
										</c:if>
										<c:if test="${!empty oam}">
											<div class="store-assesment-content">
												<div class="store-ff">
													<div class="ff grad">
														<span class="ff-text"><aebiz:showTitle titleId="productappraise.m.storeScore"/>:</span>
														<ul class="rating-level">
															<li><a <c:if test="${oam.storeScore==1}">class="actived1"</c:if> href="#">1</a></li>
															<li><a <c:if test="${oam.storeScore==2}">class="actived2"</c:if> href="#">2</a></li>
															<li><a <c:if test="${oam.storeScore==3}">class="actived3"</c:if> href="#">3</a></li>
															<li><a <c:if test="${oam.storeScore==4}">class="actived4"</c:if> href="#">4</a></li>
															<li><a <c:if test="${oam.storeScore==5}">class="actived5"</c:if> href="#">5</a></li>
														</ul>
													</div>
													<div class="ff grad">
														<span class="ff-text"><aebiz:showTitle titleId="productappraise.m.speedScore"/>:</span>
														<ul class="rating-level">
															<li><a <c:if test="${oam.speedScore==1}">class="actived1"</c:if> href="#">1</a></li>
															<li><a <c:if test="${oam.speedScore==2}">class="actived2"</c:if> href="#">2</a></li>
															<li><a <c:if test="${oam.speedScore==3}">class="actived3"</c:if> href="#">3</a></li>
															<li><a <c:if test="${oam.speedScore==4}">class="actived4"</c:if> href="#">4</a></li>
															<li><a <c:if test="${oam.speedScore==5}">class="actived5"</c:if> href="#">5</a></li>
														</ul>
													</div>
												</div>
												<div  class="store-ff">
													<div class="ff grad">
														<span class="ff-text"><aebiz:showTitle titleId="productappraise.m.descScore"/>:</span>
														<ul class="rating-level">
															<li><a <c:if test="${oam.descScore==1}">class="actived1"</c:if> href="#">1</a></li>
															<li><a <c:if test="${oam.descScore==2}">class="actived2"</c:if> href="#">2</a></li>
															<li><a <c:if test="${oam.descScore==3}">class="actived3"</c:if> href="#">3</a></li>
															<li><a <c:if test="${oam.descScore==4}">class="actived4"</c:if> href="#">4</a></li>
															<li><a <c:if test="${oam.descScore==5}">class="actived5"</c:if> href="#">5</a></li>
														</ul>
													</div>
													<div class="ff grad">
														<span class="ff-text"><aebiz:showTitle titleId="productappraise.m.serviceScore"/>:</span>
														<ul class="rating-level">
															<li><a <c:if test="${oam.serviceScore==1}">class="actived1"</c:if> href="#">1</a></li>
															<li><a <c:if test="${oam.serviceScore==2}">class="actived2"</c:if> href="#">2</a></li>
															<li><a <c:if test="${oam.serviceScore==3}">class="actived3"</c:if> href="#">3</a></li>
															<li><a <c:if test="${oam.serviceScore==4}">class="actived4"</c:if> href="#">4</a></li>
															<li><a <c:if test="${oam.serviceScore==5}">class="actived5"</c:if> href="#">5</a></li>
														</ul>
													</div>
												</div>
												<div class="assesment-store-result">
													<p class="reslut-words">${oam.content}</p>
													<p class="write-time text-right">${oam.appraiseTime}</p>
												</div>
											</div><!--  end store-assesment-content -->
										</c:if>	
											
									</div> <!-- end assesment-container -->
								</div> <!-- end widget -->
								
							</div><!-- /main-content -->
						</div><!-- /main -->
					</div><!-- /content-wrapper -->
				</div><!-- /row -->
			</div><!-- /container -->
		</div>
		<!-- END BOTTOM: LEFT NAV AND RIGHT MAIN CONTENT -->
		<div class="push-sticky-footer"></div>
	</div><!-- /wrapper -->

	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/import/ListImportJs.jsp"%>
	<%@ include file="/WEB-INF/jsp/basebusiness/usercenter/common/usercenterBottom.jsp"%>
	<script src="${pageContext.request.contextPath}/static/usercenter/js/jquery.colorbox-min.js"></script>
	<script src="${pageContext.request.contextPath}/static/usercenter/js/ajaxfileupload.js"></script>
	<script src="${pageContext.request.contextPath}/static/usercenter/js/validation/jquery.validate.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/usercenter/js/validation/aebiz.validate.js"></script>
  <script src="${pageContext.request.contextPath}/static/usercenter/js/jquery.tagsinput.min.js"></script>	
	<script type="text/javascript">
	  //调用放大图hover的效果代码!
	   $(".basked-img-container").hover(function(){
				$(this).find(".bask-img-mask").fadeIn();	
		 },function(){
			  $(this).find(".bask-img-mask").fadeOut();	
		 });
	  
	  //调用放大图插件
	  if ($(".colorbox-image").length > 0) {
		  $(".colorbox-image").colorbox({  
			maxWidth: "90%",
			maxHeight: "90%",
			rel: $(this).attr("rel")
		  });
		}	
		
	  function uploadImage() {
	  	 var uploadFilesCount = parseInt($("#nowCount").val());	 
   		 $.ajaxFileUpload({
       		url: '${pageContext.request.contextPath}/usercenter/ordershow/uploadFile/'+uploadFilesCount, //用于文件上传的服务器端请求地址
        	secureuri: false, //是否需要安全协议，一般设置为false
        	fileElementId: 'uploadFiles', //文件上传域的ID
        	dataType: 'text', //返回值类型 一般设置为json
        	success: function (data, status){
        		$("#showPics").append(data) ;
        		
        		var newCount = uploadFilesCount + 1;
        		
        		$("#nowCount").val(newCount);
        },
        error: function (data, status, e){
        	//服务器响应失败处理函数            
        }
       })                  
       
       return false;
	  }
	   	
	  function removeShow(nowCount) {
	  	if($("#show"+nowCount) != undefined) {
	   		$("#show"+nowCount).remove() ;	   				   				   				   				   				   				   				   			
	   	}
	  }
	  
	  $(document).ready(function() {
	  	
	  	$("button[name='saveAppraise']").click(function(){
	  		var orderDetailUuid = $(this).attr("id");	
	  		var appScore = $(".productScore"+orderDetailUuid).val();
	  		var appTags = "";
		  		$("input[name='checkbox"+orderDetailUuid+"']:checkbox").each(function() {
						if ($(this).is(":checked")) {
							appTags += $(this).val().trim()+",";
						}
					});
					$("div[name='userTags"+orderDetailUuid+"']").find(".tag").each(function(){
							appTags += $(this).find("span").text() + ",";
					});
				var content = $("#content"+orderDetailUuid).val();
				if(appScore==""){
					alert("<aebiz:showTitle titleId="productappraise.appScore.isEmpty"/>");
					return;
				}
				if(content.length>150){
					alert("<aebiz:showTitle titleId="productappraise.content.lengthError"/>");
					return;
				}
	  		var url = "${pageContext.servletContext.contextPath}/usercenter/productappraise/saveAppraise";
	  		$.post(url,{"orderDetailUuid":orderDetailUuid,"appScore":appScore,"appTags":appTags,"content":content,ranNum:Math.random()},function(data){
	  				$("#removeProductApp"+orderDetailUuid).remove();
	  				$("#loadProductApp"+orderDetailUuid).after(data);
	  		})
	  	});
	  	
	  	$("#orderApp").click(function(){
	  		var orderMainUuid = "${omm.uuid}";
	  		var storeScore = $('#starsS1-input').val();
	  		var speedScore = $('#starsS2-input').val();
	  		var descScore = $('#starsS3-input').val();
	  		var serviceScore =$('#starsS4-input').val();
	  		var content = $('#contentS').val();
	  		if(storeScore==""){
					alert("<aebiz:showTitle titleId="ordermainappraise.storeScore.isEmpty"/>");
					return;
				}
				if(speedScore==""){
					alert("<aebiz:showTitle titleId="ordermainappraise.speedScore.isEmpty"/>");
					return;
				}
				if(descScore==""){
					alert("<aebiz:showTitle titleId="ordermainappraise.descScore.isEmpty"/>");
					return;
				}
				if(serviceScore==""){
					alert("<aebiz:showTitle titleId="ordermainappraise.serviceScore.isEmpty"/>");
					return;
				}
				if(content.length > 150){
					alert("<aebiz:showTitle titleId="productappraise.content.lengthError"/>");
					return;
				}
	  		var url = "${pageContext.servletContext.contextPath}/usercenter/productappraise/saveOrderAppraise";	
	  		$.post(url,{"orderMainUuid":orderMainUuid,"storeScore":storeScore,"speedScore":speedScore,
	  										"descScore":descScore,"serviceScore":serviceScore,"content":content,ranNum:Math.random()
	  									},
	  							function(data){
						  				$("#removeOrderApp").remove();
	  									$("#loadOrderApp").after(data);
									})
	  	});
	  	
	  	$("#mainForm").on('click','input[name="frontCover"]',function(){
	  		var id = $(this).attr("id");
	  		$("input[name='"+id+"']").val("1");
	  		$("input[name='"+id+"']").parents(".bask-img-container").siblings(".bask-img-container").find(".frontCover-mark").val("0");
	  	})
	  });
	  
	</script>
	<script type="text/javascript">
		// widget toggle expand
		$(".assesment-container .assesment-btn .btn-toggle-expand1").click(function(){
			$(this).parent().parent().next('.assesment-box').slideToggle(300);
			$(this).find('i.fa-chevron-up').toggleClass('fa-chevron-down');
			$(this).parents(".item-tr").siblings(".item-tr").find(".assesment-box").slideUp();
	    });
		// 回复
		$('.host-replay .replay-toggle-expand').click(
			function(e) {
				e.preventDefault();
				$(this).parents('.replay-who').next('.replay-box').slideToggle(300);
			}
		);
		$('.host-replay .delete-toggle-expand').click(
			function(e) {
				e.preventDefault();
				$(this).parents('.replay-submit').parents('.replay-box').slideUp(500);
			}
		);	
	
		// 文本区域的字符计数
		var textMax = 150;
		$('.textarea').each(function(){
		  $(this).next('.js-textarea-help').find("span").html('您还可以输入' + textMax + '字');
		  $(this).keyup(function() {
			var textLength = $(this).val().length;
			var textRemaining = textMax - textLength;
			$(this).next('.js-textarea-help').find("span").html('您还可以输入' + textRemaining + ' 字');
		  });
		})
     //删除当前评价
	     $('.item-tr .deletedthis').find('span').click(function(){
		  $(this).parents('.item-tr').hide();
		 })
		//自定义标签调用的js
		 if ($(".tagsinput").length > 0) {   //.tagsinput类就是调用这个插件的类
	        $('.tagsinput').each(function(e) {
	            $(this).tagsInput({   //调用插件
	                width: 'auto',  //调用元素input的宽
	                height: 'auto'  //调用元素input的高
	            });
	        });
	    }	
	</script>
	<script>
		// 星级打分
		var Class = {
			create: function() {
				return function() { this.initialize.apply(this, arguments); }
			}
		}
		var Extend = function(destination, source) {
			for (var property in source) {
				destination[property] = source[property];
			}
		}
		function stopDefault( e ) {
			 if ( e && e.preventDefault ){
				e.preventDefault();
			}else{
				window.event.returnValue = false;
			}
			return false;
		} 

		var Stars = Class.create();
		Stars.prototype = {
			initialize: function(star,options) {
				this.SetOptions(options); //默认属性
				var flag = 999; //定义全局指针
				var isIE = (document.all) ? true : false; //IE?
				var starlist = document.getElementById(star).getElementsByTagName('a'); //星星列表
				var input = document.getElementById(this.options.Input) || document.getElementById(star+"-input"); // 输出结果
				var tips = document.getElementById(this.options.Tips) || document.getElementById(star+"-tips"); // 打印提示
				var nowClass = " " + this.options.nowClass; // 定义选中星星样式名
				var tipsTxt = this.options.tipsTxt; // 定义提示文案
				var len = starlist.length; //星星数量
				

				for(i=0;i<len;i++){ // 绑定事件 点击 鼠标滑过
					starlist[i].value = i;
					starlist[i].onclick = function(e){
						stopDefault(e);
						this.className = this.className + nowClass;
						flag = this.value;
						input.value = this.getAttribute("star:value");
						tips.innerHTML = tipsTxt[this.value]
					}
					starlist[i].onmouseover = function(){
						if (flag< 999){
							var reg = RegExp(nowClass,"g");
							starlist[flag].className = starlist[flag].className.replace(reg,"")
						}
					}
					starlist[i].onmouseout = function(){
						if (flag< 999){
							starlist[flag].className = starlist[flag].className + nowClass;
						}
					}
				};
				if (isIE){ //FIX IE下样式错误
					var li = document.getElementById(star).getElementsByTagName('li');
					for (var i = 0, len = li.length; i < len; i++) {
						var c = li[i];
						if (c) {
							c.className = c.getElementsByTagName('a')[0].className;
						}
					}
				}
			},
			//设置默认属性
			SetOptions: function(options) {
				this.options = {//默认值
					Input:			"",//设置触保存分数的INPUT
					Tips:			"",//设置提示文案容器
					nowClass:	"current-rating",//选中的样式名
					tipsTxt:		["1分-严重不合格","2分-不合格","3分-合格","4分-优秀","5分-完美"]//提示文案
				};
				Extend(this.options, options || {});
			}
		}
		
		$("input[name='starsAll']").each(function(){
			var name = $(this).attr("class");
			var value = $(this).attr("value");			
			name = new Stars(value);				
		});
		
		var StarsS1 = new Stars("starsS1");
		var StarsS2 = new Stars("starsS2");
		var StarsS3 = new Stars("starsS3");
		var StarsS4 = new Stars("starsS4");
		
	</script>
</body>
</html>
