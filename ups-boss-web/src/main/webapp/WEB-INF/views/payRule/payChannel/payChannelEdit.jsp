<%-- 核心管理模块:支付渠道管理:添加或修改页面 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/inc/taglib.jsp"%>
<style>
<!--
.pageFormContent fieldset label{
	width: 200px;
}
-->
</style>
<div class="pageContent">
	<form id="form" method="post" action="${rc.contextPath}/payChannel/editPayChannel" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="60">
		    <input type="hidden" name="navTabId" value="listPayChannel">
			<input type="hidden" name="callbackType" value="closeCurrent">
			<input type="hidden" name="forwardUrl" value="">
			
			<input type="hidden" name="id" value="${bankLimitDO.id}">
			<p style="width:99%">
				<label>支付通道：</label>
				<select name="channelId">
					<option value="" >-请选择-</option>
					<option value="000" <c:if test="${bankLimitDO.channelId ne null and bankLimitDO.channelId eq '000'}">selected="selected"</c:if>>快钱</option>
					<option value="001" <c:if test="${bankLimitDO.channelId ne null and bankLimitDO.channelId eq '001'}">selected="selected"</c:if>>联动优势</option>
					<option value="002" <c:if test="${bankLimitDO.channelId ne null and bankLimitDO.channelId eq '002'}">selected="selected"</c:if>>渤海</option>
					<option value="003" <c:if test="${bankLimitDO.channelId ne null and bankLimitDO.channelId eq '003'}">selected="selected"</c:if>>易宝</option>
				</select>
			</p>
			<p style="width:99%">
				<label>银行编码：</label>
				<input type="text" name="bankId" value="${bankLimitDO.bankId}" readonly="readonly">
			</p>
			<p style="width:99%">
				<label>银行简称：</label>
				<input type="text" name="bankName" value="${bankLimitDO.bankName}" readonly="readonly">
			</p>
			<p style="width:99%">
				<label>单笔限额(分)：</label>
				<input type="text" name="singleLimit" value="${bankLimitDO.singleLimit}" >
			</p>
			<p style="width:99%">
				<label>当日限额(分)：</label>
				<input type="text" name="dateLimit" value="${bankLimitDO.dateLimit}" >
			</p>
			<p style="width:99%">
				<label>支付方式：</label>
				<input type="text" name="payType" value="${bankLimitDO.payType}" readonly="readonly">
			</p>
			<p style="width:99%">
				<label>状态：</label>
				<c:choose>
					<c:when test="${bankLimitDO.status eq '10'}">启用</c:when>
					<c:when test="${bankLimitDO.status eq '20'}">停用</c:when>
					<c:otherwise>--</c:otherwise>
				</c:choose>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="submitForm()">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript">
	//回显
	$(document).ready(function() {
	});

	function submitForm() {
		var str = "";
		$("#form").submit();
	}
	
</script>