<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/app/taglibs.jsp"%>
<!doctype html>
<html>
  <%@ include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp"%>
  <link rel="stylesheet" href="${CONTEXT_PATH}/static/sysback/css/plugins/icheck/all.css">
  <script src="${CONTEXT_PATH}/static/sysback/js/plugins/icheck/jquery.icheck.min.js"></script>
  <script src="${CONTEXT_PATH}/static/sysback/js/plugins/aebiz/aebiz.checkbox.js"></script>
</head>
<body>
  <div class="container-fluid">
    <!-- navigation start -->
    <div class="breadcrumbs">
      <ul>
        <li><span>前端APP管理</span><i class="fa fa-angle-right"></i></li>
        <li><span>版本${empty po.id?'发布':'修改'}</span></li>
      </ul>
    </div>
	<!-- navigation end -->

    <div class="box box-bordered bordered-top">
      <div class="box-content nopadding">
        <form id="form1" action="${CONTEXT_PATH}/app/manage/publish/save" method="post" class="form-horizontal form-bordered form-validate" novalidate="novalidate">
          <c:if test="${not empty po.id}">
            <input name="id" value="${po.id}" type="hidden"/>
            <input name="_method" value="put" type="hidden"/>
          </c:if>
          <div class="form-group">
            <label for="textfield" class="control-label col-sm-2">类型</label>
            <div class="col-sm-10">
              <select name="type" class="select2-me form-control">
                <option value="">--<aebiz:showTitle titleId="basebusiness.showmessage.pleasechoose"/>--</option>
                <aebiz:option data="enum:0=医生端安卓,2=患者端安卓,1=医生端苹果,3=患者端苹果,9=基础数据" selected="${po.type}"/>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label for="textfield" class="control-label col-sm-2">版本</label>
            <div class="col-sm-10"><input name="version" id="version" value="${po.version}" type="text" class="form-control" data-rule-required="true" data-rule-minlength="2" data-rule-maxlength="20"/></div>
          </div>
          <div class="form-group">
            <label for="textfield" class="control-label col-sm-2">下载地址</label>
            <div class="col-sm-10"><input name="url" id="url" value="${po.url}" type="text" class="form-control" data-rule-required="true"/></div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-2">发布文件</label>
            <div class="col-sm-10">
              <div class="fileinput-new" data-provides="fileinput">
                <div class="form-control" data-trigger="fileinput">
                  <input id="fileToUpload" type="file" onchange="uploadFile();"/><span id="percentage"></span>
                </div>
              </div>
            </div>
          </div>
          <div class="form-group">
            <label for="textfield" class="control-label col-sm-2">状态</label>
            <div class="col-sm-10">
              <div class="check-line col-xs-4 col-sm-1">
                <input name="flag" value="0" type="radio" <c:if test="${(empty po.flag) or (po.flag=='0')}">checked="checked"</c:if> class='icheck-me' data-skin="square" data-color="blue"/><label class="inline" for="sjshjs1">有效</label>
              </div>
              <div class="check-line col-xs-4 col-sm-1">
                <input name="flag" value="1" type="radio" <c:if test="${po.flag=='1'}">checked="checked"</c:if> class='icheck-me' data-skin="square" data-color="blue"/><label class="inline" for="sjshjs2">失效</label>
              </div>
            </div>
          </div>
          <div class="form-group">
            <label for="textfield" class="control-label col-sm-2">发布时间</label>
            <div class="col-sm-10"><input name="publishDate" value="${po.publishDate}" type="text" class="form-control datepick"/></div>
          </div>
          <div class="form-actions col-sm-offset-2 col-sm-10">
            <input type="submit" class="btn btn-primary" value="${empty po.id?'发布':'修改'}" onclick="submitForm();"/>
            <a class="btn cancel" href="${CONTEXT_PATH}/app/manage/publish/search"><aebiz:showTitle titleId="basebusiness.showmessage.cancel"/></a>
          </div>
        </form>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript">
var xhr;// XMLHttpRequest 对象
$(document).ready(function() {
	var msg ="${message}";
	if(msg && msg.length > 0) alert(msg);
	xhr = new XMLHttpRequest();
});
function uploadFile() {
    var fileObj = document.getElementById("fileToUpload").files[0];// js 获取文件对象
	if(fileObj) {
		var form = new FormData();// FormData 对象
		form.append("file", fileObj);// 文件对象
		//监听事件
		xhr.upload.addEventListener("progress", progressFunction, false);
		xhr.addEventListener("load", uploadComplete, false);
		xhr.addEventListener("error", uploadFailed, false);
		xhr.addEventListener("abort", uploadCanceled, false);
		xhr.open("POST", "${CONTEXT_PATH}/app/manage/publish/upload", true);
		xhr.setRequestHeader("X_FILENAME", fileObj.name);
		xhr.send(form);
	}
}
//取消上传
function cancleUploadFile(){
	xhr.abort();
}
//显示上传进度
function progressFunction(evt) {
    var percentageDiv = document.getElementById("percentage");
    if (evt.lengthComputable && percentageDiv) {
        percentageDiv.innerHTML = Math.round(evt.loaded / evt.total * 100) + "%";
    } else {
    	percentageDiv.innerHTML = "文件上传没有正常结束！";
    }
}
//上传成功响应
function uploadComplete(evt) {
	var json = eval('('+evt.target.responseText+')');
	if(json.value) {
		$("#url").val(json.message);
	} else {
		alert(json.message);
	}
}
//上传失败
function uploadFailed(evt) {
	alert(evt.target.responseText);
}
//取消上传
function uploadCanceled(evt) {
	alert("您取消了本次上传！");
}
function validateForm() {
	trimForm(document.getElementById("form1"));
	var msg = "";
	if(!($("#version").val())) msg += "版本不能为空！\r\n";
	if(!($("#url").val())) msg += "下载地址不能为空！\r\n";
	if(msg.length > 0) alert(msg);
	return (msg.length < 1);
}
function submitForm() {
	if(validateForm()) {$("#form1").submit();}
}
</script>
</html>