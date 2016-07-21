//页面头部公共js
$(function(){
	//点击折叠左侧边栏
  $('.toggle-nav').click(function(e) {
      e.preventDefault();
      hideNav();
  });
});