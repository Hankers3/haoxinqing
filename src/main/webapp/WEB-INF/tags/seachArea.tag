<%@tag pageEncoding="UTF-8" description="分页" %>
<%@ attribute name="checkProvince" type="java.lang.String" required="true" description="需要选中的省" %>
<%@ attribute name="checkCity" type="java.lang.String" required="true" description="需要选中的市" %>
<%@ attribute name="checkRegion" type="java.lang.String" required="true" description="地区" %>
<%@ attribute name="hospitalId" type="java.lang.String" required="false" description="医院下拉控件" %>
<%@ attribute name="checkHospital" type="java.lang.String" required="true" description="需要选中的医院" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="aebiz" tagdir="/WEB-INF/tags" %>
<div class="form-control" style="border: none; height: auto; display: inline-block; width: auto; margin-right: 0;padding-right: 0;">
	<div class="fl" id="DivProvince">
		<select name='province' id='provinceId' class='select2-me form-control'>
			<option value=''>--请选择--</option>
		</select>
	</div>
	<div class="fl" id="DivCity">
		<select name='city' id='cityId' class='select2-me form-control'>
			<option value=''>--请选择--</option>
		</select>
	</div>
	<div class="fl" id="DivRegion">
		<select name='region' id='region' class='select2-me form-control'>
			<option value=''>--请选择--</option>
		</select>
	</div>
</div>
<script>
	var provinces = new Array();
	function Province(){}
	function Area() {
		this.id="";
		this.parentId = "";
		this.name="";
	}
	Province.prototype.add = function(id,provinceName ,parendId){
		var area = new Area();
		area.id = id;
		area.name = provinceName;
		area.parentId = parendId;
		provinces.push(area);
	}
	var province = new Province();
	province.add('110000','北京市','');province.add('120000','天津市','');province.add('130000','河北省','');province.add('140000','山西省','');province.add('150000','内蒙古自治区','');
	province.add('210000','辽宁省','');province.add('220000','吉林省','');province.add('230000','黑龙江省','');
	province.add('310000','上海市','');province.add('320000','江苏省','');province.add('330000','浙江省','');province.add('340000','安徽省','');province.add('350000','福建省','');province.add('360000','江西省','');province.add('370000','山东省','');
	province.add('410000','河南省','');province.add('420000','湖北省','');province.add('430000','湖南省','');province.add('440000','广东省','');province.add('450000','广西壮族自治区','');province.add('460000','海南省','');
	province.add('500000','重庆市','');province.add('510000','四川省','');province.add('520000','贵州省','');province.add('530000','云南省','');province.add('540000','西藏自治区','');
	province.add('610000','陕西省','');province.add('620000','甘肃省','');province.add('630000','青海省','');province.add('640000','宁夏回族自治区','');province.add('650000','新疆维吾尔自治区','');
	province.add('710000','台湾省','');province.add('810000','香港特别行政区','');province.add('820000','澳门特别行政区','');

	var checkProvince = "${checkProvince}";
	var checkCity = "${checkCity}";
	var checkRegion = "${checkRegion}";
	var checkHospital = "${checkHospital}";

	$(document).ready(function() {
		for(var i=0;i<provinces.length;i++) {
			var province = provinces[i];
			var checked = "";
			if(province.id==checkProvince) {
				checked = " selected";
			}
			$("#provinceId").append("<option value='"+province.id+"'"+checked+">"+province.name+"</option>");
		}
		$("#DivProvince").show();
		$("#DivCity").show();
		$("#DivRegion").show();

		if(""!=checkCity) {
			showCity(checkProvince ,checkCity);
		}

		if(""!=checkRegion) {
			showRegion(checkCity ,checkRegion);
		}

		if(""!=checkHospital) {
			showHospital(checkProvince, checkCity, checkRegion, checkHospital);
		}

		$("#provinceId").change(function(){
			var id = $(this).val();
			if(id != ""){
				showCity(id ,"");
				$("#region").empty();
				$("#region").append("<option value=''>--请选择--</option>");
				showHospital(id, "", "", "");
			}else{ //
				$("#cityId").empty();
				$("#cityId").append("<option value=''>--请选择--</option>");
				$("#region").empty();
				$("#region").append("<option value=''>--请选择--</option>");
				
				var hospitalId = "${hospitalId}";
				if(hospitalId==""){
					return;
				}else{
					$(hospitalId).empty();
					$(hospitalId).append("<option value=''>--请选择--</option>");
				}
			}
		})

		$("#cityId").change(function(){
			var id = $(this).val();
			if(id != ""){
				showRegion(id ,"");
				showHospital($("#provinceId").val(), id, "", "");
			}else{
				$("#region").empty();
				$("#region").append("<option value=''>--请选择--</option>");
				showHospital($("#provinceId").val(), "", "", "");
			}
		})

		$("#region").change(function(){
			var id = $(this).val();
			if(id != ""){
				showHospital($("#provinceId").val(), $("#cityId").val(), id, "");
			}else{
				showHospital($("#provinceId").val(), $("#cityId").val(), "", "");
			}
		})

		function showCity(provinceId ,checkCity) {
			$("#cityId").empty();
			$("#cityId").append("<option value=''>--请选择--</option>");
			$.getJSON("${pageContext.servletContext.contextPath}/app/service/doctor/2.0/getCity",
		        	{provinceUuid:provinceId}, function(r) {
		        		if(r.code==200) {
		        			var rows = r.value;
		        			for(var i=0;i<rows.length;i++) {
		        				var item = rows[i];
		        				var checked = item.code != checkCity ? "" : " selected";
								$("#cityId").append("<option value='"+item.code+"'"+checked+">"+item.cityName+"</option>");
		        			}
		        		}
		        	}
		        );
		}

		function showRegion(cityId ,checkRegion) {
			$("#region").empty();
			$("#region").append("<option value=''>--请选择--</option>");
	        $.getJSON("${pageContext.servletContext.contextPath}/app/service/doctor/2.0/getRegion",
		        {cityUuid:cityId}, function(r) {
	        		if(r.code==200) {
	        			var rows = r.value;
	        			for(var i=0;i<rows.length;i++) {
	        				var item = rows[i];
	        				var checked = item.code != checkRegion ? "" : " selected";
							$("#region").append("<option value='"+item.code+"'"+checked+">"+item.regionName+"</option>");
	        			}
	        		}
	        	}
	        );
		}

		function showHospital(provinceId, cityId, regionID, checkHospital) {
			var hospitalId = "${hospitalId}";
			if(hospitalId=="") return;
			$(hospitalId).empty();
			$(hospitalId).append("<option value=''>--请选择--</option>");
	        $.getJSON("${pageContext.servletContext.contextPath}/app/service/doctor/2.0/getHospital",
		        {provinceUuid:provinceId,cityUuid:cityId,regionUuid:regionID}, function(r) {
	        		if(r.code==200) {
	        			var rows = r.value;
	        			for(var i=0;i<rows.length;i++) {
	        				var item = rows[i];
	        				var checked = item.id != checkHospital ? "" : " selected";
							$(hospitalId).append("<option value='"+item.id+"'"+checked+">"+item.hospitalName+"</option>");
	        			}
	        		}
	        	}
	        );
		}
	})
</script>