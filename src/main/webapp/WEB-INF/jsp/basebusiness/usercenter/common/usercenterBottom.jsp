<!-- FOOTER 底部 -->
	<div class="footer">
		京ICP备11047843号-1 版权所有 2005-2012 北京全网数商科技股份有限公司<br/>技术支持：全网数商
	</div>
	<!-- END FOOTER -->

	<!-- STYLE SWITCHER 右侧更换皮肤 -->
	<div class="del-style-switcher">
		<div class="del-switcher-toggle toggle-hide"></div>
		<form>
			<div class="del-section del-section-skin">
				<h5 class="del-switcher-header">Choose Skins:</h5>

				<ul>
					<li><a href="#" title="Slate Gray" class="switch-skin slategray" data-skin="${pageContext.request.contextPath }/static/usercenter/css/skins/slategray.css">Slate Gray</a></li>
					<li><a href="#" title="Dark Blue" class="switch-skin darkblue" data-skin="${pageContext.request.contextPath }/static/usercenter/css/skins/darkblue.css">Dark Blue</a></li>
					<li><a href="#" title="Dark Brown" class="switch-skin darkbrown" data-skin="${pageContext.request.contextPath }/static/usercenter/css/skins/darkbrown.css">Dark Brown</a></li>
					<li><a href="#" title="Light Green" class="switch-skin lightgreen" data-skin="${pageContext.request.contextPath }/static/usercenter/css/skins/lightgreen.css">Light Green</a></li>
					<li><a href="#" title="Orange" class="switch-skin orange" data-skin="${pageContext.request.contextPath }/static/usercenter/css/skins/orange.css">Orange</a></li>
					<li><a href="#" title="Red" class="switch-skin red" data-skin="${pageContext.request.contextPath }/static/usercenter/css/skins/red.css">Red</a></li>
					<li><a href="#" title="Teal" class="switch-skin teal" data-skin="${pageContext.request.contextPath }/static/usercenter/css/skins/teal.css">Teal</a></li>
					<li><a href="#" title="Yellow" class="switch-skin yellow" data-skin="${pageContext.request.contextPath }/static/usercenter/css/skins/yellow.css">Yellow</a></li>
				</ul>

				<button type="button" class="switch-skin-full fulldark" data-skin="${pageContext.request.contextPath }/static/usercenter/css/skins/fulldark.css">Full Dark</button>
				<button type="button" class="switch-skin-full fullbright" data-skin="${pageContext.request.contextPath }/static/usercenter/css/skins/fullbright.css">Full Bright</button>
			</div>
			
			<p><a href="#" title="Reset Style" class="del-reset-style">Reset Style</a></p>
		</form>
	</div>
	<!-- END STYLE SWITCHER -->
	
<script>
$(function(){
	$.get("${pageContext.servletContext.contextPath}/customer/getHead",function(data){
		$("#top").html(data);
	});
});
</script>