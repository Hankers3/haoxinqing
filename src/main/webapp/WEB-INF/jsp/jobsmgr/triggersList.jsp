<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/jsp/basebusiness/common/import/AddImport.jsp" %>

<body>
	<div class="container-fluid">	
		<div class="page-header">
			<div class="pull-left">
				<h1>触发器列表</h1>
			</div>
		</div>
		<div class="breadcrumbs">
			<ul>
				<li>
					<span>Trigger 名称</span>
					<i class="fa fa-angle-right"></i>
				</li>
				<li>
					<span>Trigger 分组</span>
					<i class="fa fa-angle-right"></i>							
				</li>							
				<li>
					<span>操作</span>
				</li>						
			</ul>				
		</div>				
							
		<div class="row">
				<div class="col-sm-12">
					<div class="box">
						<div class="box-content nopadding y_tableser">
							<div class="y_clear">
								<div class="form-inline table_formnew">
								
									<div  class="form-group">
									</div>
									
									<div class="form-group">
									</div>
								</div>
								
								<div class="y_tablebtn">
								</div>
						</div>
						
						<table class="table table-bordered table-hover table-nomargin table-striped dataTable dataTable-reorder">
							<thead>		
								<tr>
									<th>Trigger 名称</th>
									<th>Trigger 分组</th>
									<th>Trigger 状态</th>
									<th>下次执行时间</th>
									<th class='hidden-480'></th>
								</tr>
							</thead>
													
							<tbody>
								<c:forEach items="${wm.rows }" var="tm">
									<tr>
										<td>${tm.trigger_name }</td>
										<td>${tm.trigger_group }</td>
										<td>${tm.status }</td>
										<td>${tm.next_fire_timeStr }</td>
																						
										<th class='hidden-480'>
											<input type="button" id="pause" value="暂停" onclick="window.location.href='${pageContext.servletContext.contextPath}/jobsmgr/pauseTrigger?triggerName=${tm.trigger_name }&groupName=${tm.trigger_group }'">
											<input type="button" id="resume" value="恢复" onclick="window.location.href='${pageContext.servletContext.contextPath}/jobsmgr/resumeTrigger?triggerName=${tm.trigger_name }&groupName=${tm.trigger_group }'">
											<input type="button" id="remove" value="删除" onclick="window.location.href='${pageContext.servletContext.contextPath}/jobsmgr/removeTrigdger?triggerName=${tm.trigger_name }&groupName=${tm.trigger_group }'">						
											<input type="button" id="remove" value="运行一次" onclick="window.location.href='${pageContext.servletContext.contextPath}/newJobsmgr/triggerJob?triggerName=${tm.trigger_name }&groupName=${tm.trigger_group }'">						
										</th>
									</tr>
								</c:forEach>	
							</tbody>
						</table>
					</div>		
				</div>
			</div>	
		</div>
	</div>
	
	
</body>

</html>
