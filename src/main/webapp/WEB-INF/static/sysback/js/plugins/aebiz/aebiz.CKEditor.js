//调用文本编辑器插件
$(function(){
	if ($(".ckeditor2").length > 0) {
    CKEDITOR.replace("ck");
  };
});