	// multiselect 双多选框调用js   
  $(function(){ 
    if ($('.multiselect').length > 0) {
      $(".multiselect").each(function() {
        var $el = $(this);
        var selectableHeader = $el.attr('data-selectableheader'),
          selectionHeader = $el.attr('data-selectionheader');
        if (selectableHeader != undefined) {
          selectableHeader = "<div class='multi-custom-header'>" + selectableHeader + "</div>"; //设置左侧选择框标题的结构和内容
        }
        if (selectionHeader != undefined) {
          selectionHeader = "<div class='multi-custom-header'>" + selectionHeader + "</div>"; //设置右侧选择框标题的结构和内容
        }
        $el.multiSelect({  //带参数的调用插件
          selectionHeader: selectionHeader,
          selectableHeader: selectableHeader
        });
      });
    }		
  });