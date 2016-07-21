<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div class="box-title">
		<h3><i class="fa fa-list-ul"></i>商品规格信息</h3>
	</div>
	<div class="box-content nopadding">
		<form action="post.php" method="POST" class='form-horizontal form-bordered' id="ss">
				<div class="form-group">
					<label class="control-label col-sm-2">字段一</label>
					<div class="col-sm-10">
						<div class="addProduct">
							<span>颜色分类：</span>
							<ul>
								<li>
									<div class="checkbox">
										<input type="checkbox" name="" value="">
										<label style="background:#5d762a;"></label>			
										<input type="text" name="" value="军绿色">		
									</div>
								</li>
								<li>
									<div class="checkbox">
										<input type="checkbox" name="" value="">
										<label style="background:#5d762a;"></label>			
										<input type="text" name="" value="军绿色">		
									</div>
								</li>
								<li>
									<div class="checkbox">
										<input type="checkbox" name="" value="">
										<label style="background:#5d762a;"></label>			
										<input type="text" name="" value="军绿色">		
									</div>
								</li>
								<li>
									<div class="checkbox">
										<input type="checkbox" name="" value="">
										<label style="background:#5d762a;"></label>			
										<input type="text" name="" value="军绿色">		
									</div>
								</li>
								<li>
									<div class="checkbox">
										<input type="checkbox" name="" value="">
										<label style="background:#5d762a;"></label>			
										<input type="text" name="" value="军绿色">		
									</div>
								</li>
								<li>
									<div class="checkbox">
										<input type="checkbox" name="" value="">
										<label style="background:#5d762a;"></label>			
										<input type="text" class="editInput" name="" value="军绿色">		
									</div>
								</li>
								<li>
									<div class="checkbox">
										<input type="checkbox" name="" value="">
										<label style="background:#5d762a;"></label>			
										<input type="text" name="" value="军绿色">		
									</div>
								</li>
								<li>
									<div class="checkbox">
										<input type="checkbox" name="" value="">
										<label style="background:#5d762a;"></label>			
										<input type="text" name="" value="军绿色">		
									</div>
								</li>
							</ul>
						</div>
						<div class="col-sm-6 picPro">
							<table class="table table-hover table-nomargin">
								<thead>
									<tr>
										<th>颜色分类</th>
										<th>图片（无图片可不填）</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											<label style="background:#5d762a;"></label>			
											军绿色
										</td>
										<td>
											<span>当前无图片</span>
											<button class="btn btn-small">本地上传</button>
											<button class="btn btn-small">空间图片</button>
										</td>
									</tr>
									<tr>
										<td>
											<label style="background:#5d762a;"></label>			
											军绿色
										</td>
										<td>
											<span>
												<img src="../img/demo/user-avatar.jpg" alt="">
												<b>删除</b>
											</span>
											<button class="btn btn-small">本地上传</button>
											<button class="btn btn-small">空间图片</button>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="col-sm-12 picPro">
							<table class="table table-hover table-nomargin">
								<thead>
									<tr>
										<th>颜色分类</th>
										<th>价格<b>*</b></th>
										<th>数量<b>*</b></th>
										<th>商家编码</th>
										<th>批量操作</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											军绿色
										</td>
										<td>
											<input type="text" name="" value="158.00">	
										</td>
										<td>
											<input type="text" name="" value="158">	
										</td>
										<td>
											<input type="text" class="inp_w" name="" value="">	
										</td>
										<td>
											<button class="btn">
												<i class="fa fa-cog"></i>
											</button>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				
				<div class="form-group " id="ziduan1">
					<label for="additionalfield" class="control-label col-sm-2">字段二</label>
					<div class="col-sm-9">
						<input type="text" name="additionalfield" id="additionalfield" class="form-control">
					</div>
					<label for="textfield" class="control-label col-sm-1 y_formgpbtn">
						<a class="btn btn-primary"  href="javascript:showInput('2')">
							显示
						</a>
					</label>
				</div>
				
				<div  class="form-group y_prdxxgl" id="ziduan2">
					<label for="additionalfield" class="control-label col-sm-2">字段三</label>
					<div class="col-sm-10">
						<input type="text" name="additionalfield" id="additionalfield" class="form-control">
					</div>
				</div>
				
				<div  class="form-group y_prdxxgl" id="ziduan3">
					<label for="additionalfield" class="control-label col-sm-2">字段四</label>
					<div class="col-sm-10">
						<input type="text" name="additionalfield" id="additionalfield" class="form-control">
					</div>
				</div>
				
				<div  class="form-group y_prdxxgl" id="ziduan4">
					<label for="additionalfield" class="control-label col-sm-2">字段五</label>
					<div class="col-sm-10">
						<input type="text" name="additionalfield" id="additionalfield" class="form-control">
					</div>
				</div>
				
				<div  class="form-group y_prdxxgl" id="ziduan5">
					<label for="additionalfield" class="control-label col-sm-2">字段六</label>
					<div class="col-sm-10">
						<input type="text" name="additionalfield" id="additionalfield" class="form-control">
					</div>
				</div>
		</form>
	</div>
