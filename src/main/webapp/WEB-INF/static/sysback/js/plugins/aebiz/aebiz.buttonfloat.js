//js获取浮动按钮宽度
$(function(){
	if($(".y_fixedbtn").length > 0){
		fixedbtn();
		if($(".y_fixedbtn").attr("data-height")!=="no"){
			$(".y_fixedbtn").wrap("<div class='y_fixbtnbox'></div>");
		}
		$(window).resize(function(){
			fixedbtn();
		})
	};
})
	
function fixedbtn(){
	$(".y_fixedbtn").width($("body").width()-40);
}

