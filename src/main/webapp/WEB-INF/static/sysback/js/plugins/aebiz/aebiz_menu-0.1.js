//页面左侧边栏公共js

$(function(){
	//设置左侧边栏调用美化滚动条的样式
  $("#left-box").niceScroll({
    cursorborder: 0,
    cursorcolor: '#999',
    railoffset: {
        top: 0,
        left: -2
    },
    autohidemode: false,
    horizrailenabled: false
	});
	
	//左侧边栏的子级li如果有data-trigger="hover"属性，则鼠标移上时就显示其二级菜单
  $('.subnav-menu > li').hover(function() {
      if ($(this).attr("data-trigger") == "hover") {
          if ($(this).parents(".subnav-menu").length > 0) {
              $(this).find(">a").trigger("click");
          } else {
              $(this).closest('.dropdown-menu').stop(true, true).show();
              $(this).addClass('open');
          }
      }
  }, function() {
      if ($(this).attr("data-trigger") == "hover") {
          $(this).closest('.dropdown-menu').stop(true, true).hide();
          $(this).removeClass('open');
      }
  });
  
  //隐藏左侧边栏通过点击新添加到body的二级菜单
	function scrolledClone($el, $cloned) {
	    $cloned.remove();
	    $el.parent().removeClass("open");
	}
  
  //左侧边栏有data-toggle=dropdown属性的a标签(有二级菜单的)的点击事件
  $(".subnav-menu > li > a[data-toggle=dropdown]").click(function() {
  		
      // Clone dropdown menu to body
      var $el = $(this);
      // Remove open clones
      $('.cloned',parent.document).remove();
      var $ulToClone = $el.next();
      var offset = $el.offset();
      var bdheight= $("body").height();
      var uiheight= $ulToClone.height();
      var uitop;
      if(!$el.parent().is(".open")){
      	if(uiheight+offset.top > bdheight){
      		uitop = offset.top+55-uiheight;
      	}else{
      		uitop = offset.top+40;
      	}
      	var $cloned = $ulToClone.clone().css({
          top: uitop,
          left: offset.left + $("#left-box").width()
	      }).show().addClass("cloned");
	      $('body',parent.document).append($cloned);
      }
      $ulToClone.hide();
      // if($("#left").hasClass("mobile-show")){
      // close when clicked
      $("body").click(function(e) {
		  	if($('.cloned',parent.document).length>0){
		      var target = $(e.target);
		      if (target.parents(".cloned").length == 0 && target.attr("data-toggle") != "dropdown") {
		          // close all
		          $(".subnav-menu > li").removeClass("open");
		          $('.cloned',parent.document).remove();
		      }
		    }
		  });
      // }
			//隐藏左侧边栏通过点击新添加到body的二级菜单
			//function scrolledClone($el, $cloned) {
			//    $cloned.remove();
			//    $el.parent().removeClass("open");
			//}
      // $("body").on("mouseleave", '.cloned', function(){
      //     $el.parent().removeClass("open");
      //     $cloned.remove();
      // });
  });
  
	
	//如果元素有.subnav-hidden类，则默认隐藏其子级（用在左侧边栏的最后一个子菜单）
  $(".subnav-hidden").each(function() {
      if ($(this).find(".subnav-menu").is(":visible")) $(this).find(".subnav-menu").hide();
  });
  
  //页面左侧侧边栏 点击标题 展开、隐藏子分类
  $(".toggle-subnav").click(function(e) {
      e.preventDefault();
      var $el = $(this);
      $el.parents(".subnav").toggleClass("subnav-hidden").find('.subnav-menu,.subnav-content').slideToggle("fast");
      $el.find("i").toggleClass("icon-angle-down").toggleClass("icon-angle-right");
      $("#left").getNiceScroll().resize().show();
  });
  //页面左侧侧边栏分类调用可拖拽插件 
  $("#left-box").sortable({  //调用插件,调用的选择器为可拖拽元素的祖先元素，会给该元素加上类ui-sortable
      items: ".subnav", //items 拖拽元素选择器
      placeholder: "widget-placeholder",  //拖拽时产生的占位元素(蓝色背景虚线框的那个)的类名
      forcePlaceholderSize: true,  //占位元素的高度是否与拖拽元素保持一致,为false时没有高度，可在css中定义
      axis: "y",  //可拖拽的轴，为y时则是在y轴上可拖拽，为x时则是在x轴上可拖拽，其他值则是可任意拖拽
      handle: ".subnav-title",  //鼠标拖动哪个选择器可以拖拽items拖拽元素
      tolerance: "pointer"
  });
  
  
});	
