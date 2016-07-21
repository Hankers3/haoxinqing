<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@ include file="/WEB-INF/jsp/basebusiness/storebackmgr/common/import/ListImport.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>

<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/storeback/css/plugins/icheck/all.css">
<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/icheck/jquery.icheck.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/static/storeback/js/plugins/aebiz/aebiz.checkbox.js"></script>
</head>

<body>
	<div id="content" class="container-fluid">
		<div id="main">
			<div class="container-fluid">
				<div class="page-header">
					<div class="pull-left">
						<h1>
							<aebiz:showTitle titleId="storeConsumerProtection.moduleName_CN" />
						</h1>
					</div>
				</div>
				<div class="breadcrumbs">
					<ul>
						<li>
							<span>
								<aebiz:showTitle titleId="storeConsumerProtection.menuOne" />
							</span> 
							<i class="fa fa-angle-right"></i></li>
						<li>
							<span>
								<aebiz:showTitle titleId="storeConsumerProtection.menuTwo" />
							</span> 
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<span>
								<aebiz:showTitle titleId="storeConsumerProtection.moduleName_CN" /> 
							</span>
						</li>
					</ul>
				</div>
				
			
				<div class="main-wrapper"><!-- main-wrapper star-->
          <div class="box guara-box">
          	<div class="guara-alert">
          	  <p class="alert alert-warning"><aebiz:showTitle titleId="storeConsumerProtection.msg" /></p>
          	</div>
            <div class="box-content sld-content guara-now">
          		<c:if test="${not empty m.list}">
   							<h3><aebiz:showTitle titleId="storeConsumerProtection.attain" /></h3>
	             	<div class="row">
		             	<c:forEach items="${m.list }" var="cp">
			              <div class="col-sm-6">
			                <div class="guara-item guara-itemed"> <!-- guara-itemed 选中下的类名-->
			                 	<dl>
				                  <dt><a href="#"><img src="${cp.iconUrl}"/></a></dt>
				                  <dd class="no-btn">
				                  	<div class="guara-tit">
				                  	  <h5>${cp.name }</h5>
				                  	 
				                  	  	<c:if test="${cp.needChooseProduct == 1 }">	
				                  	  		<span class="pro-deal">
				                  	  			<c:choose>
				                  	  				<c:when test="${cp.countProductrel == 0}">
				                  	  					<i class="pro-num">(${cp.countProductrel})</i>
				                  	  				</c:when>	
				                  	  				<c:otherwise>
						                  	  			<a href="${pageContext.servletContext.contextPath}/store/consumerprotection/ProtectionProductToDel/${cp.uuid}">
						                  	  				<i class="pro-num">(${cp.countProductrel})</i>
						                  	  			</a>
				                  	  				</c:otherwise>
				                  	  			</c:choose>
				                  	  			<aebiz:showTitle titleId="storeConsumerProtection.productAgre" />
				                  	  			<a href="${pageContext.servletContext.contextPath}/store/consumerprotection/ProtectionProductToAdd/${cp.uuid}">
				                  	  				<i class="pro-num"><aebiz:showTitle titleId="storeConsunerProtection.insertRelevancy" /></i>
				                  	  			</a>
				                  	  		</span>
				                  	  	</c:if>
				                  	</div>
				                  	<p class="guara-state">
				                  		<span class="fa-icon"><i class="fa fa-check-circle"></i><aebiz:showTitle titleId="storeConsumerProtection.added" /></span>
				                  		<c:if test="${cp.needAgreeProtocol == 1}">
				                  			<span class=" btn btn-see-deal" name="${cp.uuid}"><aebiz:showTitle titleId="storeConsumerProtection.viewDeal" /></span>
				                  		</c:if>
				                  	</p>
			                  	</dd>
			                 </dl>
			                </div>
			              </div>
			              
			            <!-- 查看服务协议模态框 star-->
										<div class="modal fade in see-sev-deal" id="${cp.uuid}">
										  <div class="modal-dialog">
										    <div class="modal-content">
										      <div class="modal-header">
										      	<button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
										      	<h4 class="modal-title"><aebiz:showTitle titleId="storeConsumerProtection.title" /></h4>
										      </div>
										      <div class="modal-body">
										       	<div class="add-deal">
										          ${cp.protocolContent }
										       	</div>
										      </div>
										      <div class="modal-footer">
										        <button type="button" class="btn btn-primary"  data-dismiss="modal"><aebiz:showTitle titleId="basebusiness.showmessage.confirm" /></button> 
										      </div>
										    </div>
										  </div>
										</div>
									<!-- 查看服务协议模态框 end-->
									</c:forEach>
								</div>      		
          		</c:if>
             	
             	<c:if test="${not empty m.noList}">
								<h3><aebiz:showTitle titleId="storeConsumerProtection.nonparticipant" /></h3>
								<div class="row">
									<c:forEach items="${m.noList }" var="cp">
				            <div class="col-sm-6">
				              <div class="guara-item">
				                <dl>
				                  <dt><a href="#"><img src="${cp.iconUrl}"/></a></dt>
				                  <dd>
				                  	<div class="guara-tit">
				                  	  <h5>${cp.name }</h5>
				                  	</div>
				                  	<P class="guara-state"><button type="button" class="btn btn-primary btn-add" name="${cp.uuid}" value="${cp.needAgreeProtocol}">加入</button></p>
				                  </dd>
				                </dl>
				              </div>
				             </div>
				            <!-- 加入模态框 star-->
									 	<div class="modal fade in add-box" id="${cp.uuid}">
									   	<div class="modal-dialog">
									    	<div class="modal-content">
									     		<div class="modal-header">
									      		<button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
									      		<h4 class="modal-title"><aebiz:showTitle titleId="storeConsumerProtection.title" /></h4>
									     		</div>
										     	<div class="modal-body">
										       	<div class="add-deal">
										         	${cp.protocolContent }
										       	</div>
										        <div class="check-line">
															<input name="agree" type="checkbox" id="agree${cp.uuid}" class='icheck-me' data-skin="square" data-color="blue">
															<label class='inline' for="${cp.uuid}"><aebiz:showTitle titleId="storeConsumerProtection.consent" /></label>
														</div>
										     	</div>
										     	<div class="modal-footer">
											       <button name="agreeSave" type="button" class="btn btn-primary disabled" value="${cp.uuid}"><aebiz:showTitle titleId="basebusiness.showmessage.confirm" /></button>
											       <button type="button" class="btn btn-default" data-dismiss="modal"><aebiz:showTitle titleId="basebusiness.showmessage.cancel" /></button>
										     	</div>
									    	</div>
									   	</div>
									  </div>
										<!-- 加入模态框 end-->
									</c:forEach>
	              </div>
	            </c:if>
            </div>
          </div>
				</div>
			</div>
		</div>
	</div>	
	     
<script>
	//加入服务保障同意协议时，保存服务协议编号和商户编号
	 $(document).ready(function() {
	 		$("button[name='agreeSave']").click(function(){
	 			var id = $(this).attr("value");	
	 			saveProtectionStore(id);
	 		});
			$(".cancel").click(function(){
				history.go(-1) ;
			});
    });
	
	 //服务项目信息介绍 下拉收缩效果
	 $('.guara-deti .fa-icon').click(function(){
	 	$(this).find('.fa').toggleClass('fa-angle-double-down').toggleClass('fa-angle-double-up');
	  $(this).parents('.guara-deti').toggleClass('has-more');
	 })
   // 加入模态框的调用
    $('.btn-add').click(function(){
        var name = $(this).attr("name");
        var value = $(this).attr("value");
        if(value == 1){//1为有协议的，0为无协议的
        	$('#'+name).modal();
        	return;
        }
        saveProtectionStore(name);
    })
   //查看服务协议模态框 的调用
    $('.btn-see-deal').click(function(){
    	 var name = $(this).attr("name");
    	 $('#'+name).modal();
    }) 
   
   //加入模态框的选择同意，确定按钮颜色变化
   		$("input[name='agree']").on('ifChecked', function(){
   			var id = $(this).attr("id");
   			$("#"+id).parents(".modal-body").next(".modal-footer").find(".btn-primary").removeClass('disabled');
			});
   		$("input[name='agree']").on("ifUnchecked",function(){
   			var id = $(this).attr("id");
   			$("#"+id).parents(".modal-body").next(".modal-footer").find(".btn-primary").addClass('disabled');
   		}); 
   		
   	//通过权益的uuid保存服务保障信息。
   	function saveProtectionStore(protectionUuid){
			var queryUrl="${pageContext.servletContext.contextPath}/store/consumerprotection/saveProtectionStore";
		 	var subStr = "";
		 	$.getJSON(queryUrl,{"protectionUuid":protectionUuid,ranNum:Math.random()},function(data){
				if(data == "success"){
					alert("<aebiz:showTitle titleId='basebusiness.showmessage.opeOk' />");
					window.location ="${pageContext.servletContext.contextPath}/store/consumerprotection/searchStoreConProtect";
				}else{
					alert("<aebiz:showTitle titleId='basebusiness.showmessage.opeFailed' />");
				}
			});	
		}
	</script>
</body>
</html>
