<%-- 权限模块:操作员管理:添加或修改页面 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/inc/taglib.jsp"%>
<style>

/* .pageFormContent fieldset label{
	width: 200px;
} */

</style>
<div class="pageContent">
	<form>
		<div class="pageFormContent" layoutH="60">
			
			<p style="width:99%">
				<label>操作员姓名：</label>
				<label>${pmsOperator.realname}</label>
			</p>
			<p style="width:99%">
				<label>操作员登录名：</label>
				<label>${pmsOperator.loginname}</label>
			</p>
			<p style="width:99%">
				<label>创建时间：</label>
				<fmt:formatDate value="${pmsOperator.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</p>
			<p style="width:99%">
				<label>手机号码：</label>
				<label>${pmsOperator.mobileno}</label>
			</p>
			<p style="width:99%">
				<label>状态：</label>
				<c:choose>
					<c:when test="${pmsOperator.status eq 100 }">激活</c:when>
					<c:when test="${pmsOperator.status eq 101 }">冻结</c:when>
					<c:otherwise>--</c:otherwise>
				</c:choose>
			</p>
			<p style="width:99%">
				<label>类型：</label>
				<c:choose>
					<c:when test="${pmsOperator.type eq 0 }">普通操作员</c:when>
					<c:when test="${pmsOperator.type eq 1 }">超级管理员</c:when>
					<c:otherwise>--</c:otherwise>
				</c:choose>
			</p>
			<p style="width:99%;height:50px;">
				<label>描述：</label>
				<label>${pmsOperator.remark}</label>
			</p>
			<p style="width:99%">
				<label>输错密码次数：</label>
				<label>${pmsOperator.pwderrorcount}</label>
			</p>
			<p style="width:99%">
				<label>最后输错密码时间：</label>
				<fmt:formatDate value="${pmsOperator.pwderrortime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</p>
			<p style="width:99%">
				<label>是否已更改过密码：</label>
				<c:if test="${pmsOperator.ischangedpwd eq 1}">是</c:if>
				<c:if test="${pmsOperator.ischangedpwd eq 0}">否</c:if>
			</p>
			
			<fieldset style="width:99%">
				<legend>关联的角色</legend>
				<c:forEach items="${rolesList}" var="pmsRoleDO"  varStatus="st">
					<c:choose>
						<c:when test="${pmsRoleDO.roletype eq 1 && pmsOperator.type eq 1}">
							<label>
								<input type="checkbox" <c:if test="${pmsOperator.type eq 1}">disabled="disabled"</c:if> cssClass="required" name="selectRole" id="${pmsRoleDO.id }">${pmsRoleDO.rolename }
							</label>
						</c:when>
						<c:when test="${pmsRoleDO.roletype eq 0}">
							<label>
								<input type="checkbox" disabled="disabled" cssClass="required" name="selectRole" id="${pmsRoleDO.id }">${pmsRoleDO.rolename }
							</label>
						</c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
				</c:forEach>
			</fieldset>
			
		</div>
		<div class="formBar">
			<ul>
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
			$("#" + array[i]).attr("checked", "checked");
		}
	});
</script>