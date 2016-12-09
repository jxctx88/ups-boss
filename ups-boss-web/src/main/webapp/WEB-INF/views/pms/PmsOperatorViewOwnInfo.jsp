<%-- 操作员查看自己帐号信息 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/inc/taglib.jsp"%>
<div class="pageContent">
	<form>
		<div class="pageFormContent" layoutH="60">
			<p style="width:99%">
				<label>登录名：</label>
				${operator.loginname }
			</p>
			<p style="width:99%">
				<label>用户名称：</label>
				${operator.realname }
			</p>
			<p style="width:99%">
				<label>创建时间：</label>
				<fmt:formatDate value="${operator.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</p>
			<p style="width:99%">
				<label>手机号码：</label>
				${operator.mobileno }
			</p>
			<p style="width:99%">
				<label>状态：</label>
				<c:choose>
					<c:when test="${operator.status eq 100 }">激活</c:when>
					<c:when test="${operator.status eq 101 }">冻结</c:when>
					<c:otherwise>--</c:otherwise>
				</c:choose>
			</p>
			<p style="width:99%">
				<label>类型：</label>
				<c:choose>
					<c:when test="${operator.type eq 0 }">普通操作员</c:when>
					<c:when test="${operator.type eq 1 }">超级管理员</c:when>
					<c:otherwise>--</c:otherwise>
				</c:choose>
			</p>
			<p style="width:99%">
				<label>最后登录时间：</label>
				<fmt:formatDate value="${operator.lastlogintime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</p>
			<p style="width:99%">
				<label>最后输错密码时间：</label>
				<fmt:formatDate value="${operator.pwderrortime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</p>
			<p style="width:99%">
				<label>已更改过密码：</label>
				<c:choose>
					<c:when test="${operator.ischangedpwd eq 1}">是</c:when>
					<c:when test="${operator.ischangedpwd eq 0}">否</c:when>
					<c:otherwise>--</c:otherwise>
				</c:choose>
			</p>
			<p style="width:99%;height:50px;">
				<label>描述：</label>
				${operator.remark}
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>