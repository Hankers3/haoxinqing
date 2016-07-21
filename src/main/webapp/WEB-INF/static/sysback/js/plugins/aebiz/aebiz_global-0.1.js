// FLAT Theme v2.0

//设置页面布局为响应固定宽度布局
function versionFixed() {
    if ($(window).width() >= 1200) {
        $("#content").addClass("container").removeClass("container-fluid");
        $("#navigation .container-fluid").addClass("container").removeClass("container-fluid");
        if ($("#left").hasClass("sidebar-fixed")) {
            $("#left").css("left", $("#content").offset().left);
        }
    }
}

//设置页面布局为全屏宽度布局
function versionFluid() {
    $("#content").addClass("container-fluid").removeClass("container");
    $("#navigation .container").addClass("container-fluid").removeClass("container");
    if ($("body").hasClass("sidebar-right")) {
        $("#left").css("right", 0);
    } else {
        $("#left").css("left", 0);
    }
}

//设置左侧边栏状态
function checkLeftNav() {
    var $w = $(window),
        $content = $("#content"),
        $left = $("#left");
    if ($w.width() <= 840) {  // 当window宽度<=840时执行事件
        if (!$left.hasClass("mobile-show")) {  //如果左侧#left没有.mobile-show类则隐藏#left。
            $left.hide();
            $("#main").css("margin-left", 0);
        }
        if ($(".toggle-mobile").length == 0) {  //如果页面没有.toggle-mobile类，则创建这个元素(点击显示、隐藏手机下导航)
            $("#navigation .user").before('<a href="#" class="toggle-mobile"><i class="fa fa-bars"></i></a>');
        }

        if ($(".mobile-nav").length == 0) {  //如果页面没有.mobile-nav(手机导航)元素，则创建一个
            createSubNav();
        }
    } else {		// 当window宽度>840时执行事件
        if (!$left.is(":visible") && !$left.hasClass("forced-hide") && !$("#content").hasClass("nav-hidden")) {  //满足条件时显示#left
            $left.show();
            $("#main").css("margin-left", $left.width());
        }

        $(".toggle-mobile").remove();
        $(".mobile-nav").removeClass("open");

        if ($content.hasClass("forced-fixed")) {
            $content.removeClass("nav-fixed");
            $("#navigation").removeClass("navbar-fixed-top");
        }

        if ($w.width() < 1200) {
            if ($("#navigation .container").length > 0) {
                versionFluid();
                $('body').addClass("forced-fluid");
            }
        } else {
            if ($('body').hasClass("forced-fluid")) {
                versionFixed();
            }
        }
    }
}

//设置左侧边栏宽度拖拽条的高度
function resizeHandlerHeight() {
    $("#left .ui-resizable-handle").height($(window).height()-40);
}

//切换手机导航的显示、隐藏
function toggleMobileNav() {
    var mobileNav = $(".mobile-nav");
    mobileNav.toggleClass("open");
    mobileNav.find(".open").removeClass("open");
}

//创建页面元素（用在了创建手机导航）
function getNavElement(current) {
    var currentText = $.trim(current.find(">a").text()),
        element = "";
    element += "<li><a href='" + current.find(">a").attr("href") + "'>" + currentText + "</a>";
    if (current.find(">.dropdown-menu").length > 0) {
        element += getNav(current.find(">.dropdown-menu"));
    }
    element += "</li>";
    return element;
}

var nav = "";

//创建页面元素（用在了创建手机导航）
function getNav(current) {
    var currentNav = "";
    currentNav += "<ul>";
    current.find(">li").each(function() {
        currentNav += getNavElement($(this));
    });
    currentNav += "</ul>";
    nav = currentNav;
    return currentNav;
}

//如果页面没有手机端的导航.mobile-nav，则创建一个和头部导航相同结构的手机导航，并添加点击事件。
function createSubNav() {
    if ($(".mobile-nav").length == 0) {
        var original = $("#navigation .main-nav");
        // loop
        var current = original;
        getNav(current);
        $("#navigation").append(nav);
        $("#navigation > ul").last().addClass("mobile-nav");

        $(".mobile-nav > li > a").click(function(e) {
            var el = $(this);
            $("#navigation").getNiceScroll().resize().show();
            if (el.next().length !== 0) {
                e.preventDefault();

                var sub = el.next();
                el.parents(".mobile-nav").find(".open").not(sub).each(function() {
                    var t = $(this);
                    t.removeClass("open");
                    t.prev().find("i").removeClass("icon-angle-down").addClass("icon-angle-left");
                });
                sub.toggleClass("open");
                el.find("i").toggleClass('icon-angle-left').toggleClass("icon-angle-down");
            }
        });
    }
}

//切换左侧边栏显示、隐藏
function hideNav() {
    $("#left").toggle().toggleClass("forced-hide");
    if ($("#left").is(":visible")) {
        $("#main").css("margin-left", $("#left").width());
    } else {
        $("#main").css("margin-left", 0);
    }
}

//设置#content的高度和#left的状态
function resizeContent() {
	  var height = 40;
	  if ($("#footer").length > 0) {
	      height += $("#footer").outerHeight();
	  }
	  
	  $("#content").css({
	      "min-height": "auto",
	      "height": $(window).height() - height
	  });
}

//页面加载时调用事件，实现页面的初始化设置
$(document).ready(function() {

    resizeContent(); //设置#content的高度和#left的状态
		
		//如果body的data-mobile-sidebar属性=slide,则触摸左右滑动时切换左侧边栏
    if ($("body").attr("data-mobile-sidebar") == "slide") {
        $("body").touchwipe({
            wipeRight: function() {
                $("#left").show().addClass("mobile-show");
            },
            wipeLeft: function() {
                $("#left").hide().removeClass("mobile-show");
            },
            preventDefaultEvents: false
        });
    }
		
		//如果body的data-mobile-sidebar属性=button,则点击按钮切换左侧边栏
    if ($("body").attr("data-mobile-sidebar") == "button") {
        $(".mobile-sidebar-toggle").click(function(e) {
            e.preventDefault();
            $("#left").toggle().toggleClass("mobile-show");
        });
    }
		
		//头部的子级li如果有data-trigger="hover"属性，则鼠标移上时就显示其二级菜单
    $('.main-nav > li').hover(function() {
        if ($(this).attr("data-trigger") == "hover") {
            $(this).closest('.dropdown-menu').stop(true, true).show();
            $(this).addClass('open');
        }
    }, function() {
        if ($(this).attr("data-trigger") == "hover") {
            $(this).closest('.dropdown-menu').stop(true, true).hide();
            $(this).removeClass('open');
        }
    });
		

    createSubNav();
   
		//给手机导航切换按钮绑定点击事件
    $("#navigation").on('click', '.toggle-mobile', function(e) {
        e.preventDefault();
        toggleMobileNav();
    });
		
		//页面左侧侧边栏宽度可拖动变大小
    if (!$("#left").hasClass("no-resize")) {  //判断#left是否有类no-resize ，如果有则不执行
        $("#left").resizable({
            minWidth: 60,    //可拖动元素的最小宽度
            maxWidth: 400,  //可拖动元素的最大宽度，高度同样也可设置
            handles: "e",    //可拖动方向 e代表东边也就是元素右边，可选n/s/e/w/se/sw/nw/ne,如果有多个值，中间用逗号隔开
            resize: function(event, ui) {   //拖动改变宽度时执行事件
                var searchInput = $('.search-form .search-pane input[type=text]'),
                    content = $("#main");
                searchInput.css({
                    width: ui.size.width - 55   //宽度改变时，左侧搜索框的宽度也随之改变为 #left宽度-55px
                });
                if (Math.abs(200 - ui.size.width) <= 20) {  //如果200-左侧宽度的绝对值小于等于20 左侧宽度按200px算
                    $("#left").css("width", 200);
                    searchInput.css("width", 145);
                    content.css("margin-left", 200);
                } else {
                    content.css("margin-left", $("#left").width());
                }

            },
            stop: function() {
                $("#left .ui-resizable-handle").css("background", "none");   //停止时事件
            },
            start: function() {
                $("#left .ui-resizable-handle").css("background", "#aaa");   //执行时事件
            }
        });
    }
		
		//如果#content元素有.nav-hidden类，则默认隐藏侧边栏
    if ($("#content").hasClass("nav-hidden")) {
        hideNav();
    }

    // set resize handler to corret height
    resizeHandlerHeight();

		//头部换肤的js
    $(".theme-colors > li > span").hover(function(e) {
        var $el = $(this),
            body = $('body');
        body.attr("class", "").addClass("theme-" + $el.attr("class"));
    }, function() {
        var $el = $(this),
            body = $('body');
        if (body.attr("data-theme") !== undefined) {
            body.attr("class", "").addClass(body.attr("data-theme"));
        } else {
            body.attr("class", "");
        }
    }).click(function() {
        var $el = $(this);
        $("body").addClass("theme-" + $el.attr("class")).attr("data-theme", "theme-" + $el.attr("class"));
    });
		
		//头部按钮点击切换页面布局方式(全屏/响应固定宽度)
    $(".version-toggle > a").click(function(e) {
        e.preventDefault();
        e.stopPropagation();
        var $el = $(this);
        var parent = $el.parent();
        if (!$el.hasClass("active")) {
            parent.find(".active").removeClass("active");
            $el.addClass("active");
        }

        if ($el.hasClass("set-fixed")) {
            versionFixed();
        } else {
            versionFluid();
        }
    });
		
    checkLeftNav();

    // check layout 如果body有如下属性则调用相应的事件，改变页面的默认布局
    if ($("body").attr("data-layout") == "fixed") {
        versionFixed();
    }

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
    }
    
    //点击页面非左侧浮出框区域关闭左侧浮出框
    $('body').click(function(e){
    	if($('.cloned').length>0){
	      var target = $(e.target);
	      if (!target.is(".cloned")) {
	        $(window.frames["menuFrame"].document).find(".subnav-menu > li").removeClass("open");
	    		$('.cloned').remove();
	      }
	    }
	  });
    
});

$.fn.scrollBottom = function() {
    return $(document).height() - this.scrollTop() - this.height();
};

//页面大小变化调用的事件
$(window).resize(function(e) {
    checkLeftNav();
    resizeContent();
    resizeHandlerHeight();
});
