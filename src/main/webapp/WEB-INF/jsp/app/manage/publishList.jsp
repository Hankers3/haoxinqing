<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/app/taglibs.jsp"%>
<!doctype html>
<html>
  <%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp" %>
</head>
<body>
  <div class="container-fluid">
  	<!-- navigation start -->
    <div class="breadcrumbs">
      <ul>
        <li><span>前端APP管理</span><i class="fa fa-angle-right"></i></li>
        <li><span>版本发布管理</span></li>
	  </ul>
    </div>
	<!-- navigation end -->

    <div class="row">
      <div class="col-sm-12">
        <div class="box">
          <div class="box-content nopadding y_tableser">

			<!-- search condition start -->
            <div class="y_clear">
              <div class="form-inline table_formnew">
              <form id="form1" name="form1" method="post" action="${CONTEXT_PATH}/app/manage/publish/search">
                <div class="form-group">
                  <label class="control-label">类型</label>
                  <select name="type" class="form-control">
                    <option value="">全部</option><aebiz:option data="enum:0=医生端安卓,2=患者端安卓,1=医生端苹果,3=患者端苹果,9=基础数据" selected="${type}"/>
                  </select>
                  <label class="control-label">版本</label>
                  <input name="version" value="${version}" type="text" class="form-control"/>
                  <label class="control-label">状态</label>
                  <select name="flag" class="form-control">
                    <option value="">全部</option><aebiz:option data="enum:0=有效,1=失效" selected="${flag}"/>
                  </select>
                  <label class="control-label">发布时间</label>
                  <input name="publishDateFrom" value="${publishDateFrom}" type="text" class="form-control datepick"/>
                  <label class="y_timeto">-</label>
                  <input name="publishDateTo" value="${publishDateTo}" type="text" class="form-control datepick"/>
                </div>
                <div class="form-group">
                  <button class="btn btn-primary search" title="<aebiz:showTitle titleId='basebusiness.showmessage.query'/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.query"/></button>
                </div>
              </form>
              </div>
              <div class="y_tablebtn">
                <a class="btn" href="${CONTEXT_PATH}/app/manage/publish/add" title="<aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.newAdd"/></a>
                <button class="btn" onclick="javascript:delRow();" title="<aebiz:showTitle titleId="basebusiness.showmessage.delete"/>" rel="tooltip"><aebiz:showTitle titleId="basebusiness.showmessage.delete"/></button>
              </div>
            </div>
            <!-- search condition end -->

            <div class="dataTables_wrapper">
              <table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder" style="width: 100%;">
                <thead>
                  <tr role="row">
                    <th class="with-checkbox sorting_disabled">&#160;</th>
                    <th class="sorting_disabled">类型</th>
                    <th class="sorting">版本</th>
                    <th class="sorting_disabled">状态</th>
                    <th class="sorting">发布时间</th>
                  </tr>
                </thead>

                <!-- search result start -->
                <tbody>
                  <form id="form2" name="form2" method="post" action="${CONTEXT_PATH}/app/manage/publish/delete">
                  <c:forEach items="${requestScope[RESULT_LIST]}" var="item" varStatus="status">
                    <c:choose>
                      <c:when test="${status.index%2==0}"><tr class="odd"></c:when>
                      <c:otherwise><tr class="even"></c:otherwise>
                    </c:choose>
                    <td class="sorting_1"><input name="pk" value="${item['uuid']}" type="radio"></td>
                    <td>
                      <a href="${CONTEXT_PATH}/app/manage/publish/edit/${item['uuid']}">
                        <aebiz:basecode code="${item['type']}" source="enum:0=医生端安卓,2=患者端安卓,1=医生端苹果,3=患者端苹果,9=基础数据"/>
                      </a>
                    </td>
                    <td><c:out value="${item['version']}"/></td>
                    <td><aebiz:basecode code="${item['flag']}" source="enum:0=有效,1=失效"/></td>
                    <td><fmt:formatDate value="${item['publish_date']}" type="date" pattern="yyyy-MM-dd"/></td>
                  </tr>
                  </c:forEach>
                  </form>
                </tbody>
              </table>
              <!-- search result end -->

              <!-- paging start -->
              <div class="dataTables_length">
                <label><select name="${PAGE_SIZE}" size="1" aria-controls="DataTables_Table_0">
                  <aebiz:option data="enum:20=20,30=30,40=40,50=50" selected="${requestScope[PAGE_SIZE]}"/>
                </select><span>条/页</span></label>
              </div>
              <div class="dataTables_info"><aebiz:pageinfo/></div>
              <div class="dataTables_paginate paging_full_numbers"><aebiz:paging url="${CONTEXT_PATH}/app/manage/publish/search"/></div>
              <!-- paging end -->

            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript">
$(document).ready(function() {
	var msg ="${message}";
	if(msg && msg.length > 0) alert(msg);
});
function delRow() {
	var rows = $(":input:radio[name='pk']:checked");
	if(rows.length < 1) {
		alert("请选择要删除的记录！");
	} else {
		if(confirm('删除操作不可恢复，要继续吗？')) {$("#form2").submit();}
	}
}
</script>
</html>