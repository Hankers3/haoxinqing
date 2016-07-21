<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<%@ include file="/WEB-INF/jsp/basebusiness/common/import/UpdateImport.jsp" %>
</head>
<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>直播视频详情</h1>
			</div>
		</div>
				
		<div class="breadcrumbs">
			<ul>
				<li>
					<span>直播视频</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>直播视频管理</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>直播视频详情</span>
				</li>
			</ul>
		</div>
				
		<div class="box box-bordered bordered-top">
			<div class="box-content nopadding">	
					<div class='form-horizontal form-validate form-bordered' >	
						<div class="form-group" style="border-right: 1px solid #ddd;">
					    <div class="col-sm-12">
						   <i class="fa fa-user"></i>直播视频信息
						 </div>
			     	</div>
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2">直播视频名称</label>
							<div class="col-sm-10">
								${m.videoName}
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2">讲者</label>
							<div class="col-sm-10">
								${m.userName}
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2">讲者介绍</label>
							<div class="col-sm-10">
								${m.userIntroduce}
							</div>
						</div>
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2">导航图片</label>
							<div class="col-sm-10">
								<li><img src="${m.imageUrl}" alt="logo" width="200px" height="200px"></li>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2">发布状态</label>
							<div class="col-sm-10">
								<c:choose>
										<c:when test="${m.state eq '1'}">
											&nbsp;已发布
										</c:when>
										<c:when test="${m.state eq '0'}">
											&nbsp;未发布
										</c:when>
								</c:choose>
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2">直播地址</label>
							<div class="col-sm-10">
								${m.videoAddress}
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2">开始时间</label>
							<div class="col-sm-10">
								${m.createTime}
							</div>
						</div>
						
						<div class="form-group">
							<label for="textfield" class="control-label col-sm-2">视频简介</label>
							<div class="col-sm-10">
								${m.videoIntroduction}
							</div>
						</div>
					
				 		<div class="y_fixedbtn">
				 			<button type="button" class="btn btn-primary cancel btn-large a_size_1"><aebiz:showTitle titleId="basebusiness.showmessage.return"/></button>
			   		</div>	
				</div>	
			</div>
		</div>
	</div>
</body>

</html>


<script>
    $(document).ready(function() {
		$(".cancel").click(function(){
			history.go(-1) ;
		});	    	
    });
</script>

<aebiz:showErrorMsg></aebiz:showErrorMsg>