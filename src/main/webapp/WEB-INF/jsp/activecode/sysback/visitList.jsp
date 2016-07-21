<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags" %>
<%@include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>
<div class="container-fluid">
    <div class="page-header">
        <div class="pull-left">
            <h1>邀请医生详情</h1>
        </div>
    </div>
    <div class="breadcrumbs">
        <ul>
            <li>
                <span>邀请码管理</span>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <span>代表管理</span>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <span>邀请医生详情</span>
            </li>
        </ul>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <div class="box">
                <div class="box-content nopadding y_tableser">
                    <div class="y_clear">
                        <div class="form-inline table_formnew">

                            <div class="form-group">
                                <label class="control-label">医疗代表姓名：${usr}</label>
                                <label class="control-label">邀请码：${activeCode}</label>               
                                <label class="control-label">邀请人数（年）：${allnum}</label>
                            </div>
                            <br>

                            <div class="form-group">
                                <label class="control-label">医生姓名：</label>
                                <input type="text" name="doctorName" id="doctorName" class="form-control">
                                <label class="control-label">医生注册时间：</label>
                                <input type="text" name="beginTime" id="beginTime" class="form-control datepick">
                                <label class="control-label">年</label>
                                <input type="text" name="endTime" id="endTime" class="form-control datepick">
                                <label class="control-label">月</label>
                                <label class="control-label">医师类型：</label>
                                <select id="tag" name="tag" class="form-control">
                                    <option value="">全部</option>
                                    <option value="A">A类医师</option>
                                    <option value="B">B类医师</option>
                                    <option value="C">C类医师</option>
                                    <option value="D">D类医师</option>
                                </select>
                                <button class="btn btn-primary search"
                                        title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>"
                                        rel="tooltip"><aebiz:showTitle
                                        titleId="basebusiness.showmessage.query"/></button>
                            </div>
                            <br>
                        </div>
                    </div>
                    <table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
                        <thead>
                        <tr>
                            <th>医生姓名</th>
                            <th>所在城市</th>
                            <th>医院名称</th>
                            <th>科室</th>
                            <th>职称</th>
                            <th>科室电话</th>
                            <th>医生注册时间</th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script>
    function retrieveData(sSource, aoData, fnCallback) {
        var searchParam = new Array();

        searchParam.push({"name": "doctorName", "value": $.trim($("#doctorName").val())});
        searchParam.push({"name": "beginTime", "value": $.trim($("#beginTime").val())});
        searchParam.push({"name": "endTime", "value": $.trim($("#endTime").val())});
        searchParam.push({"name": "tag","value": $.trim($("#tag").val())});

        $.ajax({
            "dataType": 'json',
            "type": "POST",
            "url": sSource,
            "data": {
                aoData: JSON.stringify(aoData),
                searchParam: JSON.stringify(searchParam)
            },
            "success": fnCallback
        });
    }

    var oTable = null;
    function doSearch(operact) {
        if (oTable == null) {
            if ($('.dataTable').length > 0) {
                $('.dataTable').each(function () {
                    var opt = {
                        "sPaginationType": "full_numbers",
                        "oLanguage": {
                            "sProcessing": "<aebiz:showTitle titleId="basebusiness.showmessage.loading"/>",
                            "sSearch": "<span><aebiz:showTitle titleId="basebusiness.showmessage.query"/>:</span> ",
                            "sInfo": "<aebiz:showTitle titleId="basebusiness.showmessage.pageFrom"/> <span>_START_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageTo"/> <span>_END_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageItem"/>，<aebiz:showTitle titleId="basebusiness.showmessage.totalCount"/>： <span>_TOTAL_</span> <aebiz:showTitle titleId="basebusiness.showmessage.pageItem"/>",
                            "sLengthMenu": "_MENU_ <span><aebiz:showTitle titleId="basebusiness.showmessage.pageShow"/></span>",
                            "oPaginate": {
                                "sFirst": "<aebiz:showTitle titleId="basebusiness.showmessage.firstPage"/>",
                                "sPrevious": "<aebiz:showTitle titleId="basebusiness.showmessage.prePage"/>",
                                "sNext": "<aebiz:showTitle titleId="basebusiness.showmessage.nextPage"/>",
                                "sLast": "<aebiz:showTitle titleId="basebusiness.showmessage.lastPage"/>"
                            }
                        },

                        'sDom': "rtlip",
                        'aoColumnDefs': [{
                            'bSortable': false,
                            'aTargets': [0, 1]
                        }],
                        "aaSorting": [[3, "desc"]],
                        "bSort": false, //排序功能
                        "aoColumns": [

                            {"mDataProp": "realName", "sTitle": "医生姓名"},
                            {"mDataProp": "city", "sTitle": "所在城市"},
                            {"mDataProp": "hospital", "sTitle": "医院名称"},

                            {"mDataProp": "department", "sTitle": "科室"},
                            {"mDataProp": "professional", "sTitle": "职称"},
                            {"mDataProp": "departmentLine", "sTitle": "科室电话"},
                            {"mDataProp": "createTime", "sTitle": "医生注册时间"}

                        ],

                        'oTableTools': {
                            "sSwfPath": "${pageContext.servletContext.contextPath}/sysback/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
                        },
                        "bRetrieve": true,
                        "bProcessing": true,
                        "bServerSide": true,                    //指定从服务器端获取数据
                        "sAjaxSource": "${pageContext.servletContext.contextPath}/sysback/activecode/details/${activeCode}",
                        "fnServerData": retrieveData        //获取数据的处理函数
                    };

                    if ($(this).hasClass("dataTable-reorder")) {
                        opt.sDom = "R" + opt.sDom;
                    }

                    oTable = $(this).dataTable(opt);
                });
            }
        }

        //刷新Datatable，会自动激发retrieveData
        if ('inittable' != operact) {
            oTable.fnDraw();
        }
    }

    $(".search").click(function () {
        //普通搜索需要清空高级搜索的选项
        doSearch('search');
    });

    $(document).ready(function () {
        //初始化表格
        doSearch('inittable');
    })
</script>
