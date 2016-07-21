<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- colorbox  CSS -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/static/sysback/css/plugins/colorbox/colorbox.css">
	
	<!-- jQuery UI -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/jquery-ui/jquery.ui.core.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/jquery-ui/jquery.ui.widget.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/jquery-ui/jquery.ui.mouse.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/jquery-ui/jquery.ui.draggable.min.js"></script>
	<!-- slimScroll -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

	<!-- Colorbox -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/colorbox/jquery.colorbox-min.js"></script>
	<!-- 上传图片插件js -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/fileupload/bootstrap-fileupload.min.js"></script>
	<!-- 画廊与放大图的调用js -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/plugins/aebiz/aebiz.colorbox.js"></script>
	<!-- 上传图片插件js -->
	<script src="${pageContext.servletContext.contextPath}/static/sysback/js/ajaxfileupload.js"></script>
	
<div class="col-sm-9 y_classedit">
  <c:if test="${empty imageList }">
	<input type="hidden" id="nowPage" name="nowPage" value="1"/>
	<input type="hidden" id="pageShow" name="pageShow" value="8"/>
	</c:if>
  <c:if test="${!empty imageList }">
	<input type="hidden" id="nowPage" name="nowPage" value="${wm.nowPage }"/>
	<input type="hidden" id="pageShow" name="pageShow" value="${wm.pageShow }"/>
	</c:if>
	<div class="box">
		<div class="box-title">
			<h3><i class="fa fa-picture-o"></i><aebiz:showTitle titleId="plattimagelibrary.m.myImage"/></h3>
			<!--头部上传图片、新建文件夹、搜索-->
			<div class="col-sm-12 col-md-7 pull-right">
				<div class="row">
					<div class="col-xs-10"></div>
					<div class="col-xs-2 img-file">
						<button class="btn  btn-primary" id="uploadImage" data-toggle="modal" data-target="#myModal1"><i class="fa fa-cloud-upload"></i> <aebiz:showTitle titleId='plattimagelibrary.m.uploadImage'/></button>
					</div>
				</div>
			</div><!-- end 头部上传图片、新建文件夹、搜索-->
		</div>
		<div class="box-content">
			<!-- 大图小图切换 star-->
			<div class="edit-img y_clear">
	      <div class="y_w90 pull-right tab">
					<button type="button" class="btn btn-default" onclick="javascript:picList('0');"  data-toggle="tab" data-target="#t1" title="<aebiz:showTitle titleId='plattimagelibrary.m.bigImage'/>" rel="tooltip"><i class="glyphicon-show_big_thumbnails"></i></button>
					<button type="button" class="btn btn-default" onclick="javascript:picList('1');"  data-toggle="tab" data-target="#t2" title="<aebiz:showTitle titleId='plattimagelibrary.m.listImage'/>" rel="tooltip"><i class="glyphicon-show_thumbnails_with_lines"></i></button>
					<c:if test="${empty type }">
					<input type="hidden" id="type" value="0"/>
					</c:if>
					<c:if test="${!empty type }">
					<input type="hidden" id="type" value="${type }"/>
					</c:if>
				</div>
		  </div><!-- 大图小图切换 endr-->
			<!--中间图片列表、内容-->
			<div class="row">
				<!--图片列表切换-->
				<div class="col-sm-12  tab-content y_imgbox">
					<!--大图-->
					<c:if test="${!empty type && type=='0' }">
					 <div class="tab-pane active" id="t1">
					</c:if>
					<c:if test="${empty type || type=='1' }">
					 <div class="tab-pane" id="t1">
					</c:if>
			    	<div class="scrollable pr10" data-height="480" data-visible="true">
   	 	 			  <ul class="gallery">
   	 	 			   <c:if test="${empty imageList }">
   	 	 			   <aebiz:showTitle titleId="plattimagelibrary.m.noImage"/>
   	 	 			   </c:if>
   	 	 			   <c:forEach items="${imageList}" var="i">
   	 	 			   		 <li>
    	 	 			     <div class="item-img" url="${i.imagePath }" uuid="${i.uuid }">
    	 	 			       <div class="mit-box">
    	 	 			         <a class="small-img" href="#"><img src="${i.imagePath }" /></a>
    	 	 			         <div class="extras">
    	 	 			           <div class="extras-inner">
										<a href="${i.imagePath }" class='colorbox-image' rel="group-1" title="<aebiz:showTitle titleId='plattimagelibrary.m.bigImage'/>">
										<i class="fa fa-search"></i>
										</a>
										<a href="#" class="rename" title="<aebiz:showTitle titleId='plattimagelibrary.m.rename'/>">
										<i class="fa fa-pencil"></i>
										</a>
										<a href="javascript:deleteImage2('${i.uuid }');"   title="<aebiz:showTitle titleId='basebusiness.showmessage.delete'/>">
										<i class="fa fa-trash-o"></i>
										</a>
									</div>
    	 	 			         </div>
    	 	 			       </div>
    	 	 			       <div class="mit-name">
    	 	 			         <label class="label-control">${i.imageName }</label> 
    	 	 			         <p><input type="text" class="form-control" id="${i.uuid}name" maxlength="20" value="${i.imageName }" /><a href="javascript:rename('${i.uuid }');" class="ok"><i class="glyphicon-circle_ok"></i></a><a href="#" class="cancle"><i class="glyphicon-circle_remove"></i></a></p>
    	 	 			          <div class="img-attr"><span class="text-left">${i.imageSize }</span> <span class="text-right">${i.imageSpace }</span></div>
    	 	 			       </div>
    	 	 			     </div>
    	 	 			   </li>
   	 	 			   </c:forEach>

			    	  </ul>
			     </div>
			   </div><!--end 大图-->
			   <!--列表-->
			   <c:if test="${!empty type && type=='1' }">
			    <div class="tab-pane active" id="t2">
			   </c:if>
			   <c:if test="${empty type || type=='0' }">
			    <div class="tab-pane" id="t2">
			   </c:if>
		    		<div class="scrollable pr10" data-height="400" data-visible="true">
		    			<div class="table-responsive2">
			    	 		<table class="table table-hover table-bordered imgs-list">
			    	 			 <c:if test="${empty imageList }">
			   	 	 			   	<aebiz:showTitle titleId="plattimagelibrary.m.noImage"/>
			   	 	 			  </c:if>
			   	 	 			  <c:if test="${!empty imageList }">
			    	 			<thead>
										<tr>
											<th><aebiz:showTitle titleId="plattimagelibrary.m.image"/></th>
											<th><aebiz:showTitle titleId="plattimagelibrary.m.imageName"/></th>
											<th><aebiz:showTitle titleId="plattimagelibrary.m.suffix"/></th>
											<th><aebiz:showTitle titleId="plattimagelibrary.m.imageSize"/></th>
											<th><aebiz:showTitle titleId="plattimagelibrary.m.imageSpace"/></th>
											<th><aebiz:showTitle titleId="plattimagelibrary.m.createTime"/></th>
										</tr>
									</thead>
									 </c:if>
									<tbody>
										<c:forEach items="${imageList}" var="i">
											<tr>
												<td>
												 <div class="gallery">
													<div class="item-img ">
													  <div class="mit-box">
			    	 	 			         			<a  class="small-img" href="#"><img src="${i.imagePath }" /></a>
			    	 	 			        			 <div class="extras">
		    	 	 			          					 <div class="extras-inner">
																<a href="${i.imagePath }" class='colorbox-image' rel="group-1" title="查看放大图片">
																<i class="fa fa-search"></i>
																</a>
															</div>
			    	 	 			         			</div>
			    	 	 			      			 </div>
													</div>
												 </div>  
												</td>
												<td>${i.imageName }</td>
												<td>${i.suffix }</td>
												<td>${i.imageSize }</td>
												<td>${i.imageSpace }</td>
												<td>${i.upLoadTime }</td>
											</tr>
										</c:forEach>
									</tbody>
			    	 		</table>
		    	 		</div>
		    	  </div>
			    </div><!-- end 列表-->
			  </div>
				</div><!-- end 图片列表切换-->
				 <c:if test="${!empty imageList }">
			   	
				<!-- page star -->
				<aebiz:imagePage listPath="plattimagelibrary/toImageList/${categoryUuid}"></aebiz:imagePage>
				<!-- page end -->
				 </c:if>
			</div><!-- end 中间图片列表、内容-->
		</div>
</div>

	<!-- 选择上传图片模态框 start -->
<form id="upload" action="${pageContext.servletContext.contextPath}/sysback/plattimagelibrary/uploadFile" enctype="multipart/form-data"  method="post" class='form-horizontal form-bordered form-validate'>
	<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <h4 class="modal-title" id="myModalLabel"><aebiz:showTitle titleId="platadimagerel.m.choose"/></h4>
	      </div>
	      <div class="modal-body">
					<div class="fileinput fileinput-new col-sm-12" data-provides="fileinput"><input type="hidden">
						<div class="input-group">
							<div class="form-control" data-trigger="fileinput">
								<i class="glyphicon glyphicon-file fileinput-exists"></i>
								<span class="fileinput-filename"></span>
							</div>
							<span class="input-group-addon btn btn-default btn-file">
								<span class="fileinput-new"><aebiz:showTitle titleId="platadimagerel.m.chooseImage"/></span>
							<span class="fileinput-exists">Change</span>
							<input type="file" name="myfiles" id="uploadFiles"   >
							</span>
							<a href="#" class="input-group-addon btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a>
						</div>
						 
					</div>
					
					<input type="hidden" name="imageName" id="imageName" />
					<input type="hidden" name="imageSize" id="imageSize" />
					<input type="hidden" name="imageSpace" id="imageSpace" />
					<div class="clearfix"></div>
					<div class="alert alert-warning ">
					  <strong><aebiz:showTitle titleId="platadimagerel.m.note"/></strong>
					  <ul>
					     <li><aebiz:showTitle titleId="platadimagerel.m.note1"/> </li>
						 <li><aebiz:showTitle titleId="platadimagerel.m.note2"/></li>
						 <li><aebiz:showTitle titleId="platadimagerel.m.note3"/></li>
					  </ul>
					</div>
	      </div>
	      <div class="modal-footer">
	      	<button type="button" class="btn btn-primary" id="uploadImages"><aebiz:showTitle titleId="basebusiness.showmessage.confirm"/></button>
	        <button type="button" class="btn btn-default" data-dismiss="modal"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></button>
	      </div>
	    </div>
	  </div>
	</div>
</form>
	<!-- 选择上传图片模态框 end -->
<script>
//大图显示图片选中状态
$(".gallery .item-img .mit-name").click(function(){
	 $(this).parent().toggleClass('active');
	
	 var url = $(this).parent().attr("url");
	 var uuid = $(this).parent().attr("uuid");
		
	 $(".y_pic_list").append('<li class="image"><div class="w_has_img"><img src="'+url+'"/><div class="cz"><i class="glyphicon-left_arrow"></i><i class="glyphicon-right_arrow"></i><i class="glyphicon-remove_2"></i></div><div class="bg"></div> </div><div class="y_imgms"><input type="hidden" value="'+uuid+'" id="uuids" name="uuids"/><aebiz:showTitle titleId="platadimagerel.m.url"/>:<input type="text" id="urls" name="urls"  data-rule-maxlength="100" class="form-control"><span class="font-999"><aebiz:showTitle titleId="platadimagerel.m.urlRule"/></span></div></li>');
	});
	
	//列表显示时选择图片
$(".choose").click(function(){
	
	 var url = $(this).attr("url");
	 var uuid = $(this).attr("uuid");
		
	 $(".y_pic_list").append('<li class="image"><div class="w_has_img"><img src="'+url+'"/><div class="cz"><i class="glyphicon-left_arrow"></i><i class="glyphicon-right_arrow"></i><i class="glyphicon-remove_2"></i></div><div class="bg"></div> </div><div class="y_imgms"><input type="hidden" value="'+uuid+'" id="uuids" name="uuids"/><aebiz:showTitle titleId="platadimagerel.m.url"/>:<input type="text" id="urls" name="urls"  data-rule-maxlength="100" class="form-control"><span class="font-999"><aebiz:showTitle titleId="platadimagerel.m.urlRule"/></span></div></li>');
	});
	
$("#uploadImages").click(function(){
	uploadImage();
})


	
   //删除
	function deleteImage2(uuid){
		var nowPage = $("#nowPage").val();
		var url = "${pageContext.servletContext.contextPath}/sysback/plattimagelibrary/deleteImage";
		$.get(url,
			{
				uuid: uuid,
				ranNum : Math.random()
			},
			function(data) {
				if (data == 'success') {
					var categoryUuid = $("#selectUuid").val();
					$("#list").load("${pageContext.servletContext.contextPath}/sysback/plattimagelibrary/toImageList/"+categoryUuid+"/"+nowPage+"/8/0?time="+Math.random());
				}else {
					bootbox.alert("<aebiz:showTitle titleId='plattimagelibrary.m.deletefail'/>");
				}
			});
	}
	
	//重命名
	function rename(uuid){
		var nowPage = $("#nowPage").val();
		var newName  = $("#"+uuid+"name").val();
		var url = "${pageContext.servletContext.contextPath}/sysback/plattimagelibrary/rename";
		$.get(url,
			{
				uuid: uuid,
				newName: newName,
				ranNum : Math.random()
			},
			function(data) {
				if (data == 'success') {
					var categoryUuid = $("#selectUuid").val();
					$.colorbox.remove();
					$("#list").load("${pageContext.servletContext.contextPath}/sysback/plattimagelibrary/toImageList/"+categoryUuid+"/"+nowPage+"/8/0?time="+Math.random());
				}else {
					bootbox.alert("<aebiz:showTitle titleId='plattimagelibrary.m.renamefail'/>");
				}
			});
	}
	
	 //图片重命名状态
    $('.item-img .rename').click(function(){
    	 $(this).parents('.item-img').addClass('has-input');
    	  return false;
    });
    $('.cancle').click(function(){
    	$('.item-img .rename').parents('.item-img').removeClass('has-input');
    	  return false;
    });
    $('.item-img .mit-name p').click(function(event){
    	  event.stopPropagation(); 
    });
    function picList(type){
    	//列表
    	if(type =="1"){
    		$("#type").val("1");
    	}
    	//大图
    	else{
    		$("#type").val("0");
    	}
    }
	</script>
