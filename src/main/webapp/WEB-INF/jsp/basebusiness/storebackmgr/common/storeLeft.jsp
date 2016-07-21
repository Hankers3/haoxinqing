<div id="left">
			<%-- <form action="search-results.html" method="GET" class='search-form'>
				<div class="search-pane">
					<input type="text" name="search" placeholder="Search here...">
					<button type="submit">
						<i class="fa fa-search"></i>
					</button>
				</div>
			</form>
			
			<div class="subnav">
				<div class="subnav-title">
					<a href="#" class='toggle-subnav'>
						<i class="fa fa-angle-down"></i>
						<span>商户管理</span>
					</a>
				</div>
				<ul class="subnav-menu">
					<li>
						<a href="${pageContext.servletContext.contextPath}/store/storeaccount/toInfo">商户资料管理 </a>
					</li>
					<li>
						<a href="${pageContext.servletContext.contextPath}/store/substore/toList" >门店管理</a>
					</li>
					<li>
						<a href="${pageContext.servletContext.contextPath}/store/storecomplex/toSettingStore">商户设置</a>
					</li>
					<li>
						<a href="${pageContext.servletContext.contextPath}/store/storebreakcontract/getBreakListByStoreUuid">商户违约记录</a>
					</li>
					<li>
						<a href="${pageContext.servletContext.contextPath}/store/storedomainapply/toDomainApplySearch">二级域名申请</a>
					</li>
				</ul>
				<div class="subnav-title">
					<a href="#" class='toggle-subnav'>
						<i class="fa fa-angle-down"></i>
						<span>账号管理</span>
					</a>
				</div>
				<ul class="subnav-menu">
					<li>
						<a href="${pageContext.servletContext.contextPath}/store/storecomplex/toSetting">安全设置</a>
					</li>
					<li>
						<a href="${pageContext.servletContext.contextPath}/store/storedept/toList">部门管理</a>
					</li>
					<li>
						<a href="${pageContext.servletContext.contextPath}/store/storerole/toList">角色管理</a>
					</li>
					<li>
						<a href="${pageContext.servletContext.contextPath}/store/storeemployee/toList">员工管理</a>
					</li>
				</ul>
			</div> --%>
</div>
<script>
$(function(){
	$.get("${pageContext.servletContext.contextPath}/store/getSubMenu",
			function(data) {
				$('#left').html(data);
			});
})
</script>