<%-- 权限模块:角色管理:分页查看页面 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/inc/taglib.jsp"%>
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="${rc.contextPath}/pms/listPmsRole" method="post">
	<!-- 分页表单参数 -->
    <%@include file="/WEB-INF/views/inc/pageForm.jsp"%>
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					角色名称：<input type="text" name="roleName" value="${roleName}" size="30" alt="模糊查询"  />
				</td>
				<td>
					<div class="subBar">
						<ul>
							<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
						</ul>
					</div>
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">

	<div class="panelBar">
		<ul class="toolBar">
			<z:permission value="pms:role:add">
				<li><a class="add" href="${rc.contextPath}/pms/addPmsRoleUI" target="dialog" width="550" height="300" rel="input" title="添加角色"><span>添加角色</span></a></li>
			</z:permission>
		</ul>
	</div>
	
	<table class="table" targetType="navTab" asc="asc" desc="desc" width="100%" layoutH="131">
		<thead>
			<tr>
				<th>序号</th>
				<th>角色名称</th>
				<th>角色类型</th>
				<th>描述</th>
				<th>创建时间</th>
				<th>操作</th><!-- 图标列不能居中 -->
			</tr>
		</thead>
		<tbody>
		    <c:forEach items="${pageInfo.list}" var="pmsRoleDO" varStatus="st" >
		    	<%-- 普通操作员看不到超级管理员角色 --%>
		    	<c:if test="${(pmsRoleDO.roletype eq RoleTypeEnum.ADMIN.value && type eq OperatorTypeEnum.ADMIN.value) || (pmsRoleDO.roletype eq RoleTypeEnum.USER.value)}">
				<tr target="sid_user" rel="${pmsRoleDO.id}">
				    <td>${st.index+1}</td>
					<td>${pmsRoleDO.rolename }</td>
					<td>
						<c:forEach items="${RoleTypeEnumList}" var="roleTypeEnum">
							<c:if test="${pmsRoleDO.roletype ne null and pmsRoleDO.roletype eq roleTypeEnum.value}">${roleTypeEnum.desc}</c:if>
						</c:forEach>
					</td>
					<td>${pmsRoleDO.remark}</td>
					<td><fmt:formatDate value="${pmsRoleDO.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<z:permission value="pms:role:assignpermission">
							[<a href="${rc.contextPath}/pms/assignPermissionUI?roleId=${pmsRoleDO.id}" title="为角色【${pmsRoleDO.rolename}】分配权限" target="dialog" width="950" style="color:blue">分配权限</a>]
						</z:permission>
						<z:permission value="pms:role:edit">
							&nbsp;[<a href="${rc.contextPath}/pms/editPmsRoleUI?roleId=${pmsRoleDO.id}" title="修改角色【${pmsRoleDO.rolename}】" target="dialog" width="550" height="300" rel="input" style="color:blue">修改</a>]
						</z:permission>
						<z:permission value="pms:role:delete">
							<c:if test="${pmsRoleDO.roletype eq RoleTypeEnum.USER.value}">
							&nbsp;[<a href="${rc.contextPath}/pms/deletePmsRole?roleId=${pmsRoleDO.id}" title="删除角色【${pmsRoleDO.rolename}】" target="ajaxTodo" style="color:blue">删除</a>]
							</c:if>
						</z:permission>
					</td>
				</tr>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
    <!-- 分页条 -->
    <%@include file="/WEB-INF/views/inc/pageBar.jsp"%>
</div>
    