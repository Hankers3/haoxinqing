//画廊与放大图调用的js
$(function(){
	if ($(".colorbox-image").length > 0) {
    $(".colorbox-image").colorbox({  //调用放大图插件
      maxWidth: "90%",
      maxHeight: "90%",
      rel: $(this).attr("rel")
    });
  };
  if ($(".gallery-dynamic").length > 0) {
    $(".gallery-dynamic").imagesLoaded(function() {
      $(".gallery-dynamic").masonry({  //调用瀑布流插件
        itemSelector: 'li',
        columnWidth: 201,
        isAnimated: true
      });
    });
	};
	$(".del-gallery-pic").click(function(e) {  //点击删除本张图片
    e.preventDefault();
    var $el = $(this);
    var $parent = $el.parents("li");
    $parent.fadeOut(400, function() {
    	$parent.remove();
    });
  });
});

