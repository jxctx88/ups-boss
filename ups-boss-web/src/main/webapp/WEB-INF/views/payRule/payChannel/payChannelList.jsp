<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/inc/taglib.jsp"%>

<script type="text/javascript" src="${rc.contextPath}/js/common.js"></script>
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="${rc.contextPath}/payChannel/listPayChannel" method="post">
	<!-- 分页表单参数 -->
    <%@include file="/WEB-INF/views/inc/pageForm.jsp"%>
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					支付通道：
				</td>
				<td>
					<select name="channel">
						<option value="">-请选择-</option>
						<option value="000" <c:if test="${paramMap.channel ne null and paramMap.channel eq '000'}">selected="selected"</c:if>>快钱</option>
						<option value="001" <c:if test="${paramMap.channel ne null and paramMap.channel eq '001'}">selected="selected"</c:if>>联动优势</option>
						<option value="002" <c:if test="${paramMap.channel ne null and paramMap.channel eq '002'}">selected="selected"</c:if>>渤海</option>
						<option value="003" <c:if test="${paramMap.channel ne null and paramMap.channel eq '003'}">selected="selected"</c:if>>易宝</option>
					</select>
				</td>
				<td>
					支付方式：
				</td>
				<td>
					<select name="payType">
						<option value="">-请选择-</option>
						<option value="01" <c:if test="${paramMap.payType ne null and paramMap.payType eq '01'}">selected="selected"</c:if>>快捷</option>
						<option value="02" <c:if test="${paramMap.payType ne null and paramMap.payType eq '02'}">selected="selected"</c:if>>一键支付</option>
					</select>
				</td>
				<td>状态：</td>
				<td>
					<select name="status">
						<option value="">-请选择-</option>
						<option value="10" <c:if test="${paramMap.status ne null and paramMap.status eq '10'}">selected="selected"</c:if>>启用</option>
						<option value="20" <c:if test="${paramMap.status ne null and paramMap.status eq '20'}">selected="selected"</c:if>>停用</option>
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
			<z:permission value="pay:payChannel:add">
				<li><a class="add" href="${rc.contextPath}/pay/payChannel/addPayChannelUI" target="dialog" rel="input" title="添加支付渠道"><span>添加支付渠道</span></a></li>
			</z:permission>
		</ul>
	</div>
	<table class="table" targetType="navTab" asc="asc" desc="desc" width="100%" layoutH="130">
		<thead>
			<tr>
				<th>序号</th>
				<th>支付通道</th>
				<th>银行编码</th>
				<th>银行简称</th>
				<th>单笔限额(分)</th>
				<th>当日限额(分)</th>
				<th>支付方式</th>
				<th>状态</th>
				<th>创建时间</th>
				<th>最后修改时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		    <c:forEach  items="${pageInfo.list}" var="bankLimitDO" varStatus="st">
				<tr target="sid_user" rel="${bankLimitDO.id}">
				    <td>${st.index+1}</td>
				    
					<td>
						<c:if test="${bankLimitDO.channelId eq '000'}">
							快钱
						</c:if>
						<c:if test="${bankLimitDO.channelId eq '001'}">
							联动优势
						</c:if>
						<c:if test="${bankLimitDO.channelId eq '002'}">
							渤海
						</c:if>
						<c:if test="${bankLimitDO.channelId eq '003'}">
							易宝
						</c:if>
					</td>
					<td>${bankLimitDO.bankId}</td>
					<td>${bankLimitDO.bankName}</td>
					<td>${bankLimitDO.singleLimit}</td>
					<td>${bankLimitDO.dateLimit}</td>
					<td>
						<c:if test="${bankLimitDO.payType eq '01'}">
							快捷支付
						</c:if>
						<c:if test="${bankLimitDO.payType eq '02'}">
							一键支付 
						</c:if>
						<c:if test="${bankLimitDO.payType eq '03'}">
							出款
						</c:if>
					</td>
					<td>
						<c:if test="${bankLimitDO.status eq '10'}">
							启用
						</c:if>
						<c:if test="${bankLimitDO.status eq '20'}">
							停用
						</c:if>
					</td>
					<td>
				    	<fmt:formatDate value="${bankLimitDO.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				    </td>
					<td>
				    	<fmt:formatDate value="${bankLimitDO.lastUpdateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				    </td>
					<td>
						<z:permission value="pay:payChannel:edit">
							&nbsp;[<a href="${rc.contextPath}/payChannel/editPayChannelUI?id=${bankLimitDO.id}" title="修改" target="dialog" rel="operatorUpdate" style="color:blue">修改</a>]
						</z:permission>
						<z:permission value="pay:payChannel:changestatus">
							<c:if test="${PmsOperatorDO.type eq OperatorTypeEnum.USER.value && bankLimitDO.status=='20'}">
							&nbsp;[<a href="${rc.contextPath}/payChannel/changePayChannelStatus?id=${bankLimitDO.id}" title="启用" target="ajaxTodo" style="color:blue">启用</a>]
							</c:if>
							<c:if test="${PmsOperatorDO.type eq OperatorTypeEnum.USER.value && bankLimitDO.status=='10'}">
							&nbsp;[<a href="${rc.contextPath}/payChannel/changePayChannelStatus?id=${bankLimitDO.id}" title="停用" target="ajaxTodo" style="color:blue">停用</a>]
							</c:if>
						</z:permission>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
     <!-- 分页条 -->
    <%@include file="/WEB-INF/views/inc/pageBar.jsp"%>
</div>

<script>
// 清空表单
function clearFormOperatorLogList(){
	$(':input',"form[action='${rc.contextPath}/pmsOperatorLog/listPmsOperatorLog']")
	 .not(':button, :submit, :reset, :hidden')  
	 .val('')  
	 .removeAttr('checked')  
	 .removeAttr('selected');  
}

</script>

    