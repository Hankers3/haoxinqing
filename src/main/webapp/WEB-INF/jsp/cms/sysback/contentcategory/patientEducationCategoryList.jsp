<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>

<head>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>

</head>


<body>
	<div class="container-fluid">
		<div class="page-header">
			<div class="pull-left">
				<h1>患教库分类管理</h1>
			</div>
			
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<a href="more-login.html">运营系统</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="tables-basic.html">资讯分类</a>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<a href="tables-dynamic.html">患教库分类</a>
				</li>
			</ul>
			
		</div>
		<!--内容分类管理-->
		<div class="row y_classmage">
			<div class="col-sm-3 y_classtre">
				<div class="box">
					<div class="box-title"><h3><i class="fa fa-file"></i>患教库内容分类管理</h3></div>
					<div class="box-content">
						<p class="y_prdcumanp">
							<a class="btn btn-primary mb_10" id="categoryAdd"><aebiz:showTitle titleId="contentcategory.title.addCategory"/></a>
						</p>
						 <div id="tree"> </div>
					</div>
				</div>
				<input type="hidden" name="selectUuid" id="selectUuid" />
				
			</div>
			<div class="col-sm-9 y_classedit" id="editPage">
				<div class="fl_ts_pic"></div>
			</div>
		</div>
		<!--商品分类管理 end -->
	</div>
	
	<script>
		
		$("#categoryBatchAdd").click(function(){
			if($("#selectUuid").val()==""){
				bootbox.alert('<aebiz:showTitle titleId="contentcategory.alert.selectCategory"/>');
				return;
			}	
			$("#editPage").load("${pageContext.servletContext.contextPath}/sysback/contentcategory/toBatchAdd?time="+Math.random());
			var tree=$("#tree").dynatree("getTree");
			node=tree.getActiveNode();
			node.deactivate();
		})
		
		$("#categoryAdd").click(function(){
			
			if($("#selectUuid").val()==""){
				bootbox.alert('<aebiz:showTitle titleId="contentcategory.alert.selectCategory"/>');
				return;
			}
			
			$("#editPage").load("${pageContext.servletContext.contextPath}/sysback/contentcategory/toAddPatientEducationCategory?time="+Math.random());
			
			var tree=$("#tree").dynatree("getTree");
			node=tree.getActiveNode();
			node.deactivate();
		
		});
	 
		
		$(function(){
    
	    $("#tree").dynatree({
				title: "Lazy Tree",
				rootVisible: true,
				fx: { height: "toggle", duration: 300 },
				initAjax: {
					type: 'GET',
					url: "${pageContext.servletContext.contextPath}/sysback/contentcategory/getNodes",
					data: {key:"",categoryType:"1"},
					dataType: "json",
					contentType: 'application/json; charset=utf-8'
				
				},
				onFocus:function(dtnode){
				$("#selectUuid").val(dtnode.data.key);
					
					$("#selectParentUuid").val(dtnode.data.parentUuid);
					
					$("#editPage").load("${pageContext.servletContext.contextPath}/sysback/contentcategory/toUpdatePatientEducationCategory/"+dtnode.data.key+"?time="+Math.random());
				},
				onActivate: function (dtnode) {
					
				}
				
				, onLazyRead: function (dtnode) {
					var key=dtnode.data.key;
					dtnode.appendAjax({
						type: 'GET',
						url: "${pageContext.servletContext.contextPath}/sysback/contentcategory/getNodes",
						data: {key:key,categoryType:""},
						dataType: "json",
						contentType: 'application/json; charset=utf-8'
					});
				}
		
			
			
		});
    
    
    $("#tree1").dynatree({
      // Pass an array of nodes (and child nodes)
      children: [
        {title: "Folder 2", key: "folder2" ,isFolder: true,children:[{}]}
      ],
      onActivate: function(dtnode) {
        // This function is called, when a node is clicked
        // A DynaTreeNode object is passed as argument.
        alert("You activated " + dtnode.data.title);
      }
    });
  });
	</script>
</body>
</html>