<%-- 权限模块:权限（功能点）管理:添加或修改页面 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/inc/taglib.jsp"%>
<div class="pageContent">
	<form id="form" method="post" action="${rc.contextPath}/pms/editPmsAction" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="58">
		    <input type="hidden" name="navTabId" value="listPmsAction">
			<input type="hidden" name="callbackType" value="closeCurrent">
			<input type="hidden" name="forwardUrl" value="">
			
			<input type="hidden" name="actionId" value="${pmsActionDO.id}">
			
			<p>
				<label>权限名称：</label>
				<input type="text" name="actionName" value="${pmsActionDO.actionname}" size="30" maxlength="50" />
			</p>
			<p>
				<label>权限标识：</label>
				<input type="text" name="action" value="${pmsActionDO.action }" readonly="readonly" size="30" maxlength="50" />
			</p>
			<p style="height:50px;">
				<label>权限描述：</label>
				<textarea rows="3" cols="27" name="desc" class="required" minlength="3" maxlength="60">${pmsActionDO.remark}</textarea>
			</p>
			<p>
				<label>关联菜单：</label>
				<input type="text" value="${pmsActionDO.menuName}" class="required" readonly="true" >
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
