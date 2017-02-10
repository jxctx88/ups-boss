<%-- 支付模块:批量出款管理:添加或修改页面 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/inc/taglib.jsp"%>
<div class="pageContent">
	<form id="form" method="post" action="${rc.contextPath}/pay/batchOutcome/editBatchOutcome" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="58">
		    <input type="hidden" name="navTabId" value="listBatchOutcome">
			<input type="hidden" name="callbackType" value="closeCurrent">
			<input type="hidden" name="forwardUrl" value="">
			
			<input type="hidden" name="id" value="${batchOutcomeDO.id}">
			<p>
				<label>批次号：</label>
				<label>${batchOutcomeDO.batchId}</label>
			</p>
			<p>
				<label>渠道号：</label>
				<label>${batchOutcomeDO.channelId}</label>
			</p>
			<p>
				<label>发送文件名：</label>
				<label>${batchOutcomeDO.sendFilename}</label>
			</p>
			<p>
				<label>状态：</label>
				<select name="status">
					<option value="10" <c:if test="${batchOutcomeDO.status ne null and batchOutcomeDO.status eq '10'}">selected="selected"</c:if>>初始</option>
					<option value="20" <c:if test="${batchOutcomeDO.status ne null and batchOutcomeDO.status eq '20'}">selected="selected"</c:if>>已发送文件</option>
					<option value="30" <c:if test="${batchOutcomeDO.status ne null and batchOutcomeDO.status eq '30'}">selected="selected"</c:if>>处理中</option>
					<option value="40" <c:if test="${batchOutcomeDO.status ne null and batchOutcomeDO.status eq '40'}">selected="selected"</c:if>>批次明细存在非终态</option>
					<option value="50" <c:if test="${batchOutcomeDO.status ne null and batchOutcomeDO.status eq '50'}">selected="selected"</c:if>>终态</option>
					<option value="60" <c:if test="${batchOutcomeDO.status ne null and batchOutcomeDO.status eq '60'}">selected="selected"</c:if>>失败</option>
				</select>
			</p>
			<p>
				<label>返回结果文件名：</label>
				<label>${batchOutcomeDO.respFilename}</label>
			</p>
			<p>
				<label>创建时间：</label>
				<label><fmt:formatDate value="${batchOutcomeDO.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></label>
			</p>
			<p>
				<label>最后修改时间：</label>
				<label><fmt:formatDate value="${batchOutcomeDO.lastUpdateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></label>
			</p>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
