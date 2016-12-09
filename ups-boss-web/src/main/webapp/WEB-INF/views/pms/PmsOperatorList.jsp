<%-- 权限模块:操作员管理:分页查看页面 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/inc/taglib.jsp"%>
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="${rc.contextPath}/pms/listPmsOperator" method="post">
	<!-- 分页表单参数 -->
    <%@include file="/WEB-INF/views/inc/pageForm.jsp"%>
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					操作员登录名：<input type="text" name="loginName" value="${paramMap.loginName}" size="30" alt="精确查询"  />
				</td>
				<td>
					操作员姓名：<input type="text" name="realName" value="${paramMap.realName}" size="30" alt="模糊查询"  />
				</td>
				<td>状态：</td>
				<td>
					<select name="status" class="combox" >
						<option value="">-全部-</option>
						<c:forEach items="${OperatorStatusEnumList}" var="operatorStatus">
						<option value="${operatorStatus.value}"
							<c:if test="${paramMap.status ne null and paramMap.status eq operatorStatus.value}">selected="selected"</c:if>>
							${operatorStatus.desc}
						</option>
					</c:forEach>
					</select>
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
			<z:permission value="pms:operator:add">
				<li><a class="add" href="${rc.contextPath}/pms/addPmsOperatorUI" target="dialog" rel="input" title="添加操作员"><span>添加操作员</span></a></li>
			</z:permission>
		</ul>
	</div>
	
	<table class="table" targetType="navTab" asc="asc" desc="desc" width="100%" layoutH="130">
		<thead>
			<tr>
				<th>序号</th>
				<th>操作员登录名</th>
				<th>操作员姓名</th>
				<th>手机号码</th>
				<th>状态</th>
				<th>类型</th>
				<th>操作</th><!-- 图标列不能居中 -->
			</tr>
		</thead>
		<tbody>
		    <c:forEach  items="${pageInfo.list}" var="PmsOperatorDO" varStatus="st">
		    	<%-- 普通操作员看不到超级管理员信息 --%>
		    	<c:if test="${(PmsOperatorDO.type eq OperatorTypeEnum.ADMIN.value && PmsOperatorDO.type eq OperatorTypeEnum.ADMIN.value) || (PmsOperatorDO.type eq OperatorTypeEnum.USER.value)}">
				<tr target="sid_user" rel="${PmsOperatorDO.id}">
				    <td>${st.index+1}</td>
					<td>${PmsOperatorDO.loginname }</td>
					<td>${PmsOperatorDO.realname }</td>
					<td>${PmsOperatorDO.mobileno }</td>
					<td>
						<c:forEach items="${OperatorStatusEnumList}" var="operatorStatus">
							<c:if test="${PmsOperatorDO.status ne null and PmsOperatorDO.status eq operatorStatus.value}">${operatorStatus.desc}</c:if>
						</c:forEach>
					</td>
					<td>
						<c:forEach items="${OperatorTypeEnumList}" var="operatorType">
							<c:if test="${PmsOperatorDO.type ne null and PmsOperatorDO.type eq operatorType.value}">${operatorType.desc}</c:if>
						</c:forEach>
					</td>
					<td>
						<z:permission value="pms:operator:view">
							[<a href="${rc.contextPath}/pms/viewPmsOperatorUI?id=${PmsOperatorDO.id}" title="查看【${PmsOperatorDO.loginname }】详情" target="dialog" style="color:blue">查看</a>]
						</z:permission>
						<c:if test="${PmsOperatorDO.type eq OperatorTypeEnum.USER.value }">
							<z:permission value="pms:operator:edit">
								&nbsp;[<a href="${rc.contextPath}/pms/editPmsOperatorUI?id=${PmsOperatorDO.id}" title="修改【${PmsOperatorDO.loginname }】" target="dialog" rel="operatorUpdate" style="color:blue">修改</a>]
							</z:permission>
							<z:permission value="pms:operator:resetpwd">
								&nbsp;[<a href="${rc.contextPath}/pms/resetOperatorPwdUI?id=${PmsOperatorDO.id}" title="重置【${PmsOperatorDO.loginname }】的密码" target="dialog" width="550" height="300" style="color:blue">重置密码</a>]
							</z:permission>
							<z:permission value="pms:operator:changestatus">
								<c:if test="${PmsOperatorDO.type eq OperatorTypeEnum.USER.value && PmsOperatorDO.status==OperatorStatusEnum.ACTIVE.value}">
								&nbsp;[<a href="${rc.contextPath}/pms/changeOperatorStatus?id=${PmsOperatorDO.id}" title="冻结【${PmsOperatorDO.loginname }】" target="ajaxTodo" style="color:blue">冻结</a>]
								</c:if>
								<c:if test="${PmsOperatorDO.type eq OperatorTypeEnum.USER.value && PmsOperatorDO.status==OperatorStatusEnum.INACTIVE.value}">
								&nbsp;[<a href="${rc.contextPath}/pms/changeOperatorStatus?id=${PmsOperatorDO.id}" title="激活【${PmsOperatorDO.loginname }】" target="ajaxTodo" style="color:blue">激活</a>]
								</c:if>
							</z:permission>
							<z:permission value="pms:operator:delete">
								<c:if test="${PmsOperatorDO.type eq OperatorTypeEnum.USER.value }">
								&nbsp;[<a href="${rc.contextPath}/pms/deleteOperatorStatus?id=${PmsOperatorDO.id}" target="ajaxTodo" title="确定要删除吗？" style="color:blue">删除</a>]
								</c:if>
							</z:permission>
						</c:if>
					</td>
				</tr>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
     <!-- 分页条 -->
    <%@include file="/WEB-INF/views/inc/pageBar.jsp"%>
</div>