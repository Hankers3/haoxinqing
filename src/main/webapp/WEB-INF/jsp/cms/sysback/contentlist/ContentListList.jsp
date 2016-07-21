<%@ page contentType="text/html; charset=utf-8" %>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags" %>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>
<div class="container-fluid">
    <div class="page-header">
        <div class="pull-left">
            <h1>获取资讯文章列表</h1>
        </div>
    </div>
    <div class="breadcrumbs">
        <ul>
            <li>
                <span>讲坛咨询系统</span>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <span>咨询管理</span>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <span>获取咨询文章列表</span>
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
                                <label class="control-label">医生姓名：</label>
                                <input type="text" name="doctorName" id="doctorName" class="form-control">

                                <label class="control-label">手机号码：</label>
                                <input type="text" name="doctorMobile" id="doctorMobile" class="form-control">
                                <input type="hidden" name="doctorMobile_q" id="doctorMobile_q" class="form-control"
                                       value="Like">
                                <label class="control-label">资讯名称：</label>
                                <input type="text" name="contentName" id="contentName" class="form-control">
                                <label class="control-label">咨询类别：</label>
                                <select name="contentCategoryUuid" id="contentCategoryUuid" class='form-control'>
                                    <option value="">全部</option>
                                    <c:forEach items="${contentList}" var="item">
                                        <option value="${item.uuid}">${item.categoryName}</option>
                                    </c:forEach>
                                </select>
                                <input type="hidden" name="contentCategoryUuid_q" id="contentCategoryUuid_q"
                                       class="form-control" value="EQ">

                            </div>
                            </br>
                            <div class="form-group">
                                <label class="control-label">索取时间：</label>
                                <input type="text" name="beginTime" id="beginTime" class="form-control datepick">
                                <label class="control-label">至</label>
                                <input type="text" name="endTime" id="endTime" class="form-control datepick">
                            </div>

                            <div class="form-group">
                                <button class="btn btn-primary search"
                                        title="<aebiz:showTitle titleId="basebusiness.showmessage.query"/>"
                                        rel="tooltip"><aebiz:showTitle
                                        titleId="basebusiness.showmessage.query"/></button>
                                <button class="btn" onclick="javascript:clearSearch();"
                                        title="<aebiz:showTitle titleId="basebusiness.showmessage.clear"/>"
                                        rel="tooltip"><aebiz:showTitle
                                        titleId="basebusiness.showmessage.clear"/></button>
                            </div>
                        </div>

                    </div>

                    <table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
                        <thead>
                        <tr>

                            <th>医生姓名</th>
                            <th>手机号码</th>
                            <th>文献名称</th>

                            <th>索取时间</th>
                            <th>邮箱</th>

                            <th class='hidden-480'><aebiz:showTitle titleId="basebusiness.showmessage.operate"/></th>
                        </tr>
                        </thead>

                        <tbody>
                        </tbody>
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
        searchParam.push({"name": "contentName", "value": $.trim($("#contentName").val())});
        searchParam.push({"name": "beginTime", "value": $.trim($("#beginTime").val())});
        searchParam.push({"name": "endTime", "value": $.trim($("#endTime").val())});

        searchParam.push({"name": "doctorMobile", "value": $.trim($("#doctorMobile").val())});
        searchParam.push({"name": "doctorMobile_q", "value": $.trim($("#doctorMobile_q").val())});

        searchParam.push({"name": "contentCategoryUuid", "value": $.trim($("#contentCategoryUuid").val())});
        searchParam.push({"name": "contentCategoryUuid_q", "value": $.trim($("#contentCategoryUuid_q").val())});


        $.ajax({
            "dataType": 'json',
            "type": "POST",
            "url": sSource,
            "data": {aoData: JSON.stringify(aoData), searchParam: JSON.stringify(searchParam)},
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

                        //'sDom': "lfrtip",
                        'sDom': "rtlip",
                        //"sDom": '<"top"l>rt<"bottom"ip><"clear">',
                        'aoColumnDefs': [{
                            'bSortable': false,
                            'aTargets': [0, 1]
                        }],
                        "aaSorting": [[3, "desc"]],
                        "bSort": true, //排序功能
                        "aoColumns": [

                            {"mDataProp": "doctorRealName", "sTitle": "医生姓名"},
                            {"mDataProp": "doctorMobile", "sTitle": "电话"},
                            {"mDataProp": "contentTitle", "sTitle": "文献名称"},

                            {"mDataProp": "createTime", "sTitle": "索取时间"},
                            {"mDataProp": "email", "sTitle": "索取人的邮箱"},


                            {
                                "mDataProp": "operate",
                                "sDefaultContent": "",
                                "sTitle": "<aebiz:showTitle titleId="basebusiness.showmessage.operate"/>",
                                "fnRender": function (obj) {
                                    var sReturn = "";
                                    if (obj.aData.state != "1") {
                                        sReturn += "<a href='${pageContext.servletContext.contextPath}/sysback/contentlist/updateState/"
                                                + obj.aData.uuid
                                                + "' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.check"/>'>未发送</a>";
                                    }
                                    if (obj.aData.state != "0") {
                                        sReturn += "<a href='#"
                                                + obj.aData.uuid
                                                + "' class='btn' rel='tooltip' title='<aebiz:showTitle titleId="basebusiness.showmessage.check"/>'>已发送</a>";
                                    }

                                    return sReturn;
                                }
                            }
                        ],

                        //'oColVis': {
                        // "buttonText": "Change columns <i class='icon-angle-down'></i>"
                        //},
                        'oTableTools': {
                            "sSwfPath": "${pageContext.servletContext.contextPath}/static/js/plugins/datatable/swf/copy_csv_xls_pdf.swf"
                        },
                        "bRetrieve": true,
                        "bProcessing": true,
                        "bServerSide": true,                    //指定从服务器端获取数据
                        "sAjaxSource": "${pageContext.servletContext.contextPath}/sysback/contentlist/queryList",
                        "fnServerData": retrieveData,           //获取数据的处理函数
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
    })


    function clearSearch() {
        $("#doctorName").val("");
        $("#contentName").val("");
        $("#beginTime").val("");
        $("#endTime").val("");

    }


    //删除
    function removes(delId) {
        var checkIds = "";
        if (delId.trim() != "") {
            checkIds = delId;
        } else {
            $("input[name='check']:checkbox").each(function () {
                if ($(this).is(":checked")) {
                    checkIds += $(this).val() + ",";
                }
            })
        }

        if (checkIds.trim() == "") {
            //提示为空
            bootbox.alert("<aebiz:showTitle titleId="basebusiness.showmessage.chooseDeleteData"/>");
            return;
        }

        bootbox.confirm("<aebiz:showTitle titleId="basebusiness.showmessage.confirmDelete"/>", function (r) {
            if (r) {
                //删除
                $.post(
                        "${pageContext.servletContext.contextPath}/sysback/contentlist/deletes",
                        {"selectOne": checkIds, ranNum: Math.random()},
                        function (data) {
                            var result = eval("(" + data + ")");
                            if (result.rsp) {
                                //刷新
                                //bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeOk"/>') ;
                                doSearch('search');
                            } else {
                                bootbox.alert('<aebiz:showTitle titleId="basebusiness.showmessage.opeFailed"/>');
                            }
                        }
                );
            }
        });
    }

    $(document).ready(function () {

        //初始化表格
        doSearch('inittable');

        //复选框全选
        $("#check_all").click(function (e) {
            $('input', oTable.fnGetNodes()).prop('checked', this.checked);
        });


        $(".clearMoreSearch").click(function (e) {
            clearMoreSearch();
        });
    })
</script>