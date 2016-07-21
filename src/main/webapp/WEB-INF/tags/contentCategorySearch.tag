<%@tag pageEncoding="UTF-8" description="分页" %>
<%@ attribute name="categoryUuid" type="java.lang.String" required="true" description="最后选中的分类uuid" %>
<%@ attribute name="inputName" type="java.lang.String" required="true" description="表单提交input名称" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="aebiz" tagdir="/WEB-INF/tags" %>
<input type="hidden" name="${inputName}" id="selectCategoryUuid" value="${categoryUuid}" />



<div id="categoryId" class="fl">

</div>	

<script type="text/javascript">
function selectChange(level,parentUuid){
	 $("#selectCategoryUuid").val(parentUuid);


	 if($("#"+level).length==0){
		$("#categoryId").append("<div id='"+level+"'></div>");
	 }
	 if($("#selectlevel0").val()==""){
			$("#illId").hide();
		 	$("#entityId").hide();
		 	$("#sympId").hide();
		 	
	 		$("#level1").html("");
	 		$("#level2").html("");
	 }
	 if($("#selectlevel1").val()==""){
		 	$("#illId").hide();
		 	$("#entityId").hide();
		 	$("#sympId").hide();
		 	
	 		$("#level2").html("");
	 }
	 
	 //by 20151216 xl 
	 if(parentUuid=="1"||parentUuid=="2"||parentUuid==""||parentUuid=="HJ"){
	 	$("#illId").hide();
	 	$("#entityId").hide();
	 	$("#sympId").hide();
	 }
	 
	 if(parentUuid=="G"||parentUuid=="HJ"){
		 $("#level1").html("");
	 }
	 
	 if(parentUuid=="A"||parentUuid=="D"||parentUuid=="E"||parentUuid=="F"){
	 		$("#illId").show();
	 		$("#entityId").hide();
	 		$("#sympId").hide();
	 }
	 
	 if(parentUuid=="B"){
	 		$("#entityId").show();
	 		$("#illId").hide();
	 		$("#sympId").hide();
	 }
	 if(parentUuid=="C"){
	 		$("#sympId").show();
	 		$("#entityId").hide();
	 		$("#illId").hide();
	 }
	 //by 20151216 xl  end 
	  
	  
	 $.getJSON(
		"${pageContext.servletContext.contextPath}/sysback/contentcategory/changeSubCategory",
		{
			"parentUuid" : parentUuid,
			"level":level,
			ranNum : Math.random()
		},
		function(data) {
			var addHtml="";
			if(data.rsp){
				var listShow1=data.showList ;
				var nextLevel=data.level ;
				var addHtml="";
				if(listShow1!=null && listShow1.length >0){
					addHtml+="<select class='form-control' id='select"+level+"' onchange='javascript:selectChange(\""+nextLevel+"\",this.value);'>";
						addHtml+="<option value=''>-<aebiz:showTitle titleId='basebusiness.showmessage.pleasechoose'/>-</option>";
					for(var i in listShow1){
						addHtml+="<option value='"+listShow1[i].categoryUuid+"'>"+listShow1[i].categoryName+"</option>";
					}				
					addHtml+="</select>";
				}
				$("#"+level).html(addHtml);
			}
			
		});
}

function showSelect(){
	 var categoryUuid=$("#selectCategoryUuid").val();
	 
	 //by 20151216 xl 
	 if(categoryUuid=="1"||categoryUuid=="2"){
	 	$("#illId").hide();
	 	$("#entityId").hide();
	 	$("#sympId").hide();
	 }
	 
	 if(categoryUuid=="A"||categoryUuid=="D"||categoryUuid=="E"||categoryUuid=="F"){
	 		$("#illId").show();
	 		$("#entityId").hide();
	 		$("#sympId").hide();
	 }
	 
	 if(categoryUuid=="B"){
	 		$("#entityId").show();
	 		$("#illId").hide();
	 		$("#sympId").hide();
	 }
	 if(categoryUuid=="C"){
	 		$("#sympId").show();
	 		$("#entityId").hide();
	 		$("#illId").hide();
	 }
	 //by 20151216 xl  end 

	 $.getJSON(
		"${pageContext.servletContext.contextPath}/sysback/contentcategory/showSubCategory",
		{
			"leafUuid" : categoryUuid,
			ranNum : Math.random()
		},
		function(data) {
			var addHtml="";
			if(data.rsp){
				
				var addHtml="";
				var listShow1=data.level0 ;
				if(listShow1!=null &&listShow1.length > 0){
					addHtml+="<div id='level0' class='fl'>";
						addHtml+="<select class='form-control'  id='selectlevel0' onchange='javascript:selectChange(\"level1\",this.value);'>";
						addHtml+="<option value=''>-<aebiz:showTitle titleId='basebusiness.showmessage.pleasechoose'/>-</option>";
					for(var i in listShow1){
						addHtml+="<option value='"+listShow1[i].categoryUuid+"' "+listShow1[i].selected+">"+listShow1[i].categoryName+"</option>";
					}				
						addHtml+="</select>";
					addHtml+="</div>";
					
				}
				
				var listShow1=data.level1 ;
				if(listShow1!=null &&listShow1.length > 0){
					addHtml+="<div id='level1' class='fl'>";
						addHtml+="<select class='form-control' id='selectlevel1' onchange='javascript:selectChange(\"level2\",this.value);'>";
						addHtml+="<option value=''>-<aebiz:showTitle titleId='basebusiness.showmessage.pleasechoose'/>-</option>";
					for(var i in listShow1){
						addHtml+="<option value='"+listShow1[i].categoryUuid+"' "+listShow1[i].selected+">"+listShow1[i].categoryName+"</option>";
					}				
						addHtml+="</select>";
					addHtml+="</div>";
					
				}
				
				var listShow1=data.level2 ;
				if(listShow1!=null &&listShow1.length > 0){
					addHtml+="<div id='level2' class='fl'>";
						addHtml+="<select class='form-control'  id='selectlevel2' onchange='javascript:selectChange(\"level3\",this.value);'>";
						addHtml+="<option value=''>-<aebiz:showTitle titleId='basebusiness.showmessage.pleasechoose'/>-</option>";
					for(var i in listShow1){
						addHtml+="<option value='"+listShow1[i].categoryUuid+"' "+listShow1[i].selected+">"+listShow1[i].categoryName+"</option>";
					}				
						addHtml+="</select>";
					addHtml+="</div>";
					
				}
				
				var listShow1=data.level3 ;
				if(listShow1!=null &&listShow1.length > 0){
					addHtml+="<div id='level3' class='fl'>";
						addHtml+="<select class='form-control'  id='selectlevel3' onchange='javascript:selectChange(\"level4\",this.value);'>";
						addHtml+="<option value=''>-<aebiz:showTitle titleId='basebusiness.showmessage.pleasechoose'/>-</option>";
					for(var i in listShow1){
						addHtml+="<option value='"+listShow1[i].categoryUuid+"' "+listShow1[i].selected+">"+listShow1[i].categoryName+"</option>";
					}				
						addHtml+="</select>";
					addHtml+="</div>";
					
				}
				
				
				$("#categoryId").html(addHtml);
			}
			
		});
}

$(document).ready(function() {
	showSelect();
})
	 
</script>









