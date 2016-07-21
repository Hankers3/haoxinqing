	// dataTables 动态数据表的全部调用
$(function(){
  if ($('.dataTable').length > 0) {
      $('.dataTable').each(function() {
          if (!$(this).hasClass("dataTable-custom")) {
              var opt = {
                  "sPaginationType": "full_numbers",
                  "oLanguage": {
                      "sSearch": "<span>Search:</span> ",
                      "sInfo": "Showing <span>_START_</span> to <span>_END_</span> of <span>_TOTAL_</span> entries",
                      "sLengthMenu": "_MENU_ <span>entries per page</span>"
                  },
                  //'sDom': "lfrtip"
                  'sDom': "rtlip"
              };
              if ($(this).hasClass("dataTable-noheader")) {
                  opt.bFilter = false;
                  opt.bLengthChange = false;
              }
              if ($(this).hasClass("dataTable-nofooter")) {
                  opt.bInfo = false;
                  opt.bPaginate = false;
              }
              if ($(this).hasClass("dataTable-nosort")) {
                  var column = $(this).attr('data-nosort');
                  column = column.split(',');
                  for (var i = 0; i < column.length; i++) {
                      column[i] = parseInt(column[i]);
                  };
                  opt.aoColumnDefs = [{
                      'bSortable': false,
                      'aTargets': column
                  }];
              }
              if ($(this).hasClass("dataTable-scroll-x")) {
                  opt.sScrollX = "100%";
                  opt.bScrollCollapse = true;
                  $(window).resize(function() {
                      oTable.fnAdjustColumnSizing();
                  });
              }
              if ($(this).hasClass("dataTable-scroll-y")) {
                  opt.sScrollY = "300px";
                  opt.bPaginate = false;
                  opt.bScrollCollapse = true;
                  $(window).resize(function() {
                      oTable.fnAdjustColumnSizing();
                  });
              }
              if ($(this).hasClass("dataTable-reorder")) {
                  opt.sDom = "R" + opt.sDom;
              }
              if ($(this).hasClass("dataTable-colvis")) {
                  opt.sDom = "C" + opt.sDom;
                  opt.oColVis = {
                      "buttonText": "Change columns <i class='fa fa-angle-down'></i>"
                  };
              }
              if ($(this).hasClass('dataTable-tools')) {
                  opt.sDom = "T" + opt.sDom;
                  opt.oTableTools = {
                      "sSwfPath": "js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
                  };
              }
              if ($(this).hasClass("dataTable-scroller")) {
                  opt.sScrollY = "300px";
                  opt.bDeferRender = true;
                  if ($(this).hasClass("dataTable-tools")) {
                      opt.sDom = 'TfrtiS';
                  } else {
                      opt.sDom = 'frtiS';
                  }
                  opt.sAjaxSource = "js/plugins/datatable/demo.txt";
              }
              if ($(this).hasClass("dataTable-grouping") && $(this).attr("data-grouping") == "expandable") {
                  opt.bLengthChange = false;
                  opt.bPaginate = false;
              }

              var oTable = $(this).dataTable(opt);
              $(this).css("width", '100%');
              $('.dataTables_filter input').attr("placeholder", "Search here...").addClass("form-control input-medium");
              $(".dataTables_length select").wrap("<div class='input-mini'></div>").chosen({
                  disable_search_threshold: 9999999
              });
              $("#check_all").click(function(e) {
                  $('input', oTable.fnGetNodes()).prop('checked', this.checked);
              });
              if ($(this).hasClass("dataTable-fixedcolumn")) {
                  new FixedColumns(oTable);
              }
              if ($(this).hasClass("dataTable-columnfilter")) {
                  oTable.columnFilter({
                      "sPlaceHolder": "head:after"
                  });
              }
              if ($(this).hasClass("dataTable-grouping")) {
                  var rowOpt = {};

                  if ($(this).attr("data-grouping") == 'expandable') {
                      rowOpt.bExpandableGrouping = true;
                  }
                  oTable.rowGrouping(rowOpt);
              }

              oTable.fnDraw();
              oTable.fnAdjustColumnSizing();
          }
      });
  }
});