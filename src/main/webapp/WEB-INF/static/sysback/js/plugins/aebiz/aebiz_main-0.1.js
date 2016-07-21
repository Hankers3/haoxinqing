//页面右侧主体公共js
$(function(){
	//.scrollable调用美化滚动条插件
	if ($(".scrollable").length > 0) {
	    $('.scrollable').each(function() {
	        var $el = $(this);
	        var height = parseInt($el.attr('data-height')),
	            vis = ($el.attr("data-visible") == "true") ? true : false,
	            start = ($el.attr("data-start") == "bottom") ? "bottom" : "top";
	        var opt = {
	            height: height,
	            color: "#666",
	            start: start,
	            allowPageScroll: true
	        };
	        if (vis) {
	            opt.alwaysVisible = true;
	            opt.disabledFadeOut = true;
	        }
	        $el.slimScroll(opt);
	    });
	};
	    
	//调用bootstrap的popover组件
	$("[rel=popover]").popover();
	
	// Tooltips (only for desktop) (bootstrap tooltips) 调用bootstrap的title美化插件tooltip
	var mobile = false,
	    tooltipOnlyForDesktop = true,
	    notifyActivatedSelector = 'button-active';
	if (/Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.userAgent)) {
	    mobile = true;
	}
	if (tooltipOnlyForDesktop) {
	    if (!mobile) {
	        $('[rel=tooltip]').tooltip();
	    }
	};
	 //点击页面触发关闭左侧弹出框事件
  $('body').click(function(){
  	if($('.cloned',parent.document).length>0){
    	$(window.parent.document).find("body").trigger("click");
    }
  });
  //.box模块 点击收起、展开(slideToggle)事件
  $(".content-slideUp").click(function(e) {
      e.preventDefault();
      var $el = $(this),
          content = $el.parents('.box').find(".box-content");
      content.slideToggle('fast', function() {
          $el.find("i").toggleClass('icon-angle-up').toggleClass("icon-angle-down");
          if (!$el.find("i").hasClass("icon-angle-up")) {
              if (content.hasClass('scrollable')) slimScrollUpdate(content);
          } else {
              if (content.hasClass('scrollable')) destroySlimscroll(content);
          }
      });
  });
});

$(window).resize(function(){
	 //页面宽度变化时，重新设置datatable的宽度
	 if ($('.dataTable').length > 0) {
        var table = $.fn.dataTable.fnTables(true); //this gets all visible dataTables...  --add by ddy      
        if (table.length > 0) {
            $(table).each(function() {
                if ($(this).hasClass("dataTable-scroller")) {
                    $(this).dataTable().fnDraw();
                }
                $(this).css("width", '100%');
            });
            $(table).dataTable().fnAdjustColumnSizing(); //this  adjusts a given dataTable --add by ddy
        }
    }
    
		//页面宽度变化时，重新设置calendar文本编辑器的宽度
    if ($(".calendar").length > 0) {
        $(".calendar").fullCalendar("render");
    }	
});

