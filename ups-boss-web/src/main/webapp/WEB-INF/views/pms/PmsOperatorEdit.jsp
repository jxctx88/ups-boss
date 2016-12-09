<%-- 权限模块:操作员管理:添加或修改页面 --%>
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
	<form id="form" method="post" action="${rc.contextPath}/pms/editPmsOperator" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="60">
		    <input type="hidden" name="navTabId" value="listPmsOperator">
			<input type="hidden" name="callbackType" value="closeCurrent">
			<input type="hidden" name="forwardUrl" value="">
			
			<input type="hidden" name="id" value="${pmsOperator.id}">
			<input type="hidden" name="selectVal" id="selectVal" value="${owenedRoleIds}">
			<p style="width:99%">
				<label>操作员姓名：</label>
				<input type="text" name="realname" value="${pmsOperator.realname}" >
			</p>
			<p style="width:99%">
				<label>操作员登录名：</label>
				<input type="text" name="loginname" value="${pmsOperator.loginname}" >
			</p>
			<p style="width:99%">
				<label>手机号码：</label>
				<input type="text" name="mobileno" value="${pmsOperator.mobileno}" >
			</p>
			<p style="width:99%">
				<label>状态：</label>
				<c:choose>
					<c:when test="${pmsOperator.status eq OperatorStatusEnum.ACTIVE.value}">激活</c:when>
					<c:when test="${pmsOperator.status eq OperatorStatusEnum.INACTIVE.value}">冻结</c:when>
					<c:otherwise>--</c:otherwise>
				</c:choose>
			</p>
			<p style="width:99%">
				<label>操作员类型：</label>
				<c:choose>
					<c:when test="${pmsOperator.type eq OperatorTypeEnum.USER.value }">普通操作员</c:when>
					<c:when test="${pmsOperator.type eq OperatorTypeEnum.ADMIN.value }">超级管理员</c:when>
					<c:otherwise>--</c:otherwise>
				</c:choose>
			</p>
			<p style="width:99%;height:50px;">
				<label>描述：</label>
				<textarea rows="3" cols="30" name="remark" maxlength="100">${pmsOperator.remark}</textarea>
			</p>
			
			<fieldset style="width:99%">
				<legend>选择角色<font color="red">*</font></legend>
				<c:forEach items="${rolesList}" var="pmsRoleDO" varStatus="st">
					<c:choose>
						<c:when test="${pmsRoleDO.roletype eq RoleTypeEnum.ADMIN.value && pmsOperator.type eq RoleTypeEnum.ADMIN.value}">
							<label>
								<input type="checkbox" <c:if test="${pmsOperator.type eq RoleTypeEnum.ADMIN.value}">disabled="disabled"</c:if> 
								class="selectOperatorRole" name="selectRole" id="roleId${pmsRoleDO.id }" value="${pmsRoleDO.id }">${pmsRoleDO.rolename }
							</label>
						</c:when>
						<c:when test="${pmsRoleDO.roletype eq RoleTypeEnum.USER.value}">
							<label>
								<input type="checkbox" <c:if test="${pmsOperator.type eq RoleTypeEnum.ADMIN.value}">disabled="disabled"</c:if> 
								class="selectOperatorRole" name="selectRole" id="roleId${pmsRoleDO.id }" value="${pmsRoleDO.id }">${pmsRoleDO.rolename }
							</label>
						</c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
				</c:forEach>
			</fieldset>
			
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
		var str = "${owenedRoleIds}";
		var array = new Array();
		array = str.split(",");
		for ( var i = 0; i < array.length; i++) {
			$("#roleId" + array[i]).attr("checked", "checked");
		}
	});

	function submitForm() {
		var str = "";
		$(":checkbox:checked").each(function() {
			if ($(this).hasClass('selectOperatorRole')){
				// 加样式判断，避免与其他复选框冲突
				str += $(this).val() + ",";
			}
		});
		if(str == null || str == ""){
			alertMsg.error("操作员关联的角色不能为空");
			return;
		}
		$("#selectVal").val(str);
		$("#form").submit();
	}
	
</script>