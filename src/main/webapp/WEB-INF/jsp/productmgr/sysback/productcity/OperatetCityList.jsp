<%@ page contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>

<%@include file="/WEB-INF/jsp/basebusiness/common/import/ListImport.jsp"%>

</head>

<body>
<div class="container-fluid">
	<div class="page-header">
		<div class="pull-left">
			<h1>经营城市</h1>
		</div>
		<div class="pull-right">
			<ul class="minitiles">
				<li class='grey'>
					<a href="#">
						<i class="fa fa-cogs"></i>
					</a>
				</li>
				<li class='lightgrey'>
					<a href="#">
						<i class="fa fa-globe"></i>
					</a>
				</li>
			</ul>
			<ul class="stats">
				<li class='satgreen'>
					<i class="fa fa-money"></i>
					<div class="details">
						<span class="big">$324,12</span>
						<span>Balance</span>
					</div>
				</li>
				<li class='lightred'>
					<i class="fa fa-calendar"></i>
					<div class="details">
						<span class="big">February 22, 2013</span>
						<span>Wednesday, 13:56</span>
					</div>
				</li>
			</ul>
		</div>
	</div>
	<div class="breadcrumbs">
		<ul>
			<li>
				<span>商品系统</span>
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<span>商品管理</span>
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<span>经营城市</span>
			</li>
		</ul>
	</div>
	<!--
	<ul class="tabs tabs-inline tabs-top border1-top">
		<li class='active'>
			<a href="#Product1" data-toggle='tab'>家政商品</a>
		</li>
		<li>
			<a href="#Product2" data-toggle='tab'>月嫂商品</a>
		</li>
	</ul>-->
	
	<div class="tab-content y_tabdatable">
		<div class="tab-pane active" id="Product1">
			<div class="y_clear">
				<div class="form-inline table_formnew">
					<div class="form-group">	
						<label class="control-label">城市名称：</label>
						<input type="text" name="id" id="id" class="form-control ">
						<input type="hidden" name="id_q" id="id_q" class="form-control" value="EQ">
					</div>
					<div class="form-group">
						<button class="btn btn-primary search" title="查询" rel="tooltip">查询</button>
					</div>
				</div>
			</div>
			<table id="user" class="table table-bordered table-hover table-striped dataTable dataTable-reorder">
				<thead>		
					<tr>
						<th>城市</th>
						<th>商品</th>
						<th>价格</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td rowspan="5">北京</td>
						<td>厨房</td>
						<td><a href="#" class="username" data-type="text" data-pk="1" data-original-title="Enter username">8元</a>/m<sup>2</sup></td>
					</tr>
					<tr>
						<td>卫生间</td>
						<td><a href="#" class="username" data-type="text" data-pk="1" data-original-title="Enter username">8元</a>/m<sup>2</sup></td>
					</tr>
					<tr>
						<td>其他房间</td>
						<td><a href="#" class="username" data-type="text" data-pk="1" data-original-title="Enter username">8元</a>/m<sup>2</sup></td>
					</tr>
					<tr>
						<td>抽烟机</td>
						<td><a href="#" class="username" data-type="text" data-pk="1" data-original-title="Enter username">8元</a>/m<sup>2</sup></td>
					</tr>
					<tr>
						<td>冰箱</td>
						<td><a href="#" class="username" data-type="text" data-pk="1" data-original-title="Enter username">8元</a>/m<sup>2</sup></td>
					</tr>
				</tbody>	
				<tbody>
					<tr>
						<td rowspan="5">北京</td>
						<td>厨房</td>
						<td><a href="#" class="username" data-type="text" data-pk="1" data-original-title="Enter username">8元</a>/m<sup>2</sup></td>
					</tr>
					<tr>
						<td>卫生间</td>
						<td><a href="#" class="username" data-type="text" data-pk="1" data-original-title="Enter username">8元</a>/m<sup>2</sup></td>
					</tr>
					<tr>
						<td>其他房间</td>
						<td><a href="#" class="username" data-type="text" data-pk="1" data-original-title="Enter username">8元</a>/m<sup>2</sup></td>
					</tr>
					<tr>
						<td>抽烟机</td>
						<td><a href="#" class="username" data-type="text" data-pk="1" data-original-title="Enter username">8元</a>/m<sup>2</sup></td>
					</tr>
					<tr>
						<td>冰箱</td>
						<td><a href="#" class="username" data-type="text" data-pk="1" data-original-title="Enter username">8元</a>/m<sup>2</sup></td>
					</tr>
				</tbody>	
			</table>
		</div>
		<div class="tab-pane" id="Product2">
			<div class="y_clear">
				<div class="form-inline table_formnew">
					<div class="form-group">	
						<label class="control-label">城市名称：</label>
						<input type="text" name="id" id="id" class="form-control ">
						<input type="hidden" name="id_q" id="id_q" class="form-control" value="EQ">
					</div>
					<div class="form-group">
						<button class="btn btn-primary search" title="查询" rel="tooltip">查询</button>
					</div>
				</div>
			</div>
			<table id="user" class="table table-bordered table-hover table-striped dataTable dataTable-nosort dataTable-reorder" data-nosort="0">
				<thead>		
					<tr>
						<th>城市</th>
						<th>月嫂</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>北京</td>
						<td><a href="#" class="username" data-type="text" data-pk="1" data-original-title="Enter username">8000元</a>/月</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
	
	<!--高级搜索-->
	<div id="modal-user" class="modal fade y_highserch">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 id="user-infos">高级查询</h4>
				</div>
				<div class="modal-body">							
					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
								<label for="textfield" class="control-label">商品名称</label>
							 	<input type="text" name="id_s" id="id_s" class="form-control">
							</div>											
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label for="textfield" class="control-label">所属分类：</label>
								<select name="select" id="select" class='form-control'>
									<option value="">--所有--</option>
									<option value="1">家政商品</option>
									<option value="2">月嫂商品</option>
								</select>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label for="textfield" class="control-label">所属城市：</label>
								<select name="select" id="select" class='form-control'>
									<option value="">--所有--</option>
									<option value="1">北京</option>
									<option value="2">上海</option>
								</select>
							</div>
						</div>
						 
						<div class="col-sm-4">
							<div class="form-group">
								<label for="textfield" class="control-label">创建时间</label>
								<input type="text" id="textfield10" name="textfield10" class="datepick form-control">
							</div>
						</div>
					
						<div class="col-sm-4">
							<div class="form-group">
								<label for="textfield" class="control-label">商品价格</label>
								<div class="y_twoiptbx y_clear">
									<div class="y_twoipt"><input type="text" name="oper_s" id="oper_s" class="form-control"></div>
									<div class="y_twosp">-</div>
									<div class="y_twoipt"><input type="text" name="oper_2s" id="oper_2s" class="form-control"></div>
								</div>
							</div>
						</div>
						
						<div class="col-sm-4">
							<div class="form-group">
								<label class="control-label" for="textfield">商品状态</label>
								<div class="y_hiserrd">
									<label class="radio-inline">
										<input name="y_prostate" type="radio">所有
									</label>
									<label class="radio-inline">
										<input name="y_prostate" type="radio">通过
									</label>
									<label class="radio-inline">
										<input name="y_prostate" type="radio">未通过
									</label>
								</div>
							</div>											
						</div> 						
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary moresearch" data-dismiss="modal">搜索</button>
					<button class="btn clearMoreSearch" >清空</button>
					<!--
					<button class="btn" data-dismiss="modal">关闭</button>
					-->
				</div>
			</div>
		</div>
	</div>
	<!--高级搜索-->
	<script>
		if ($("#user").length > 0) {
        //ajax mocks
        $.mockjaxSettings.responseTime = 500;

        $.mockjax({
            url: '/post',
            response: function(settings) {}
        });

        $.mockjax({
            url: '/error',
            status: 400,
            statusText: 'Bad Request',
            response: function(settings) {
                this.responseText = 'Please input correct value';
            }
        });

        $.mockjax({
            url: '/status',
            status: 500,
            response: function(settings) {
                this.responseText = 'Internal Server Error';
            }
        });

        $.mockjax({
            url: '/groups',
            response: function(settings) {
                this.responseText = [{
                    value: 0,
                    text: 'Guest'
                }, {
                    value: 1,
                    text: 'Service'
                }, {
                    value: 2,
                    text: 'Customer'
                }, {
                    value: 3,
                    text: 'Operator'
                }, {
                    value: 4,
                    text: 'Support'
                }, {
                    value: 5,
                    text: 'Admin'
                }];
            }
        });
    }	
	</script>
</body>
</html>
