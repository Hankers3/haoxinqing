<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="aebiz" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>           
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<table class="m_detailed_list">
		        	<tbody>
		        		<tr>
		        			<td width="33%" class="m_jx_td">接单率</td>
		        			<td width="33%">${performance.get("personalReceiveOrderPro")}</td>
		        			<td width="33%">平均${performance.get("avgReceiveOrderPro")}</td>
		        		</tr>
		        		<tr>
		        			<td class="m_jx_td">拒单率</td>
		        			<td>${performance.get("personalRefuseOrderPro")}</td>
		        			<td>平均${performance.get("avgRefuseOrderPro")}</td>
		        		</tr>
		        		<tr>
		        			<td class="m_jx_td">服务互评</td>
		        			<td>${performance.get("personalScore")}</td>
		        			<td>平均${performance.get("scoreByOthers")}</td>
		        		</tr>
		        		<tr>
		        			<td class="m_jx_td">服务评分</td>
		        			<td>${performance.get("personalServiceScore")}</td>
		        			<td>平均${performance.get("avgServiceScore")}</td>
		        		</tr>
		        		<tr>
		        			<td class="m_jx_td">空岗率</td>
		        			<td>${performance.get("personalNoWorkPro")}</td>
		        			<td>${performance.get("avgNoWorkPro")}</td>
		        		</tr>
		        		<tr>
		        			<td class="m_jx_td">抢单时间</td>
		        			<td>${performance.get("ordersTime")}</td>
		        			<td>排名${performance.get("orderRanking")}</td>
		        		</tr>
		          </tbody>
		        </table>
		        <div class="m_income m_incomein">
		        	接单总量： <label class="m_color">${performance.get("receiveOrderCount")}笔</label>
		        	<b>|</b>
		        	<label class="m_color">￥${performance.get("orderSum")}</label>
            </div>