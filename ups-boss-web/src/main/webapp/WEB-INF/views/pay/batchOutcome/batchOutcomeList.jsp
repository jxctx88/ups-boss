<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/inc/taglib.jsp"%>

<script type="text/javascript" src="${rc.contextPath}/js/common.js"></script>
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="${rc.contextPath}/pay/batchOutcome/listBatchOutcome" method="post">
	<!-- 分页表单参数 -->
    <%@include file="/WEB-INF/views/inc/pageForm.jsp"%>
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					批次号：<input type="text" name="batchId" value="${paramMap.batchId}" size="15" alt="精确查询"  />
				</td>
				<td>
					状态：
					<select name="status">
						<option value="">-请选择-</option>
						<option value="10" <c:if test="${paramMap.status ne null and paramMap.status eq '10'}">selected="selected"</c:if>>初始</option>
						<option value="20" <c:if test="${paramMap.status ne null and paramMap.status eq '20'}">selected="selected"</c:if>>已发送文件</option>
						<option value="30" <c:if test="${paramMap.status ne null and paramMap.status eq '30'}">selected="selected"</c:if>>处理中</option>
						<option value="40" <c:if test="${paramMap.status ne null and paramMap.status eq '40'}">selected="selected"</c:if>>批次明细存在非终态</option>
						<option value="50" <c:if test="${paramMap.status ne null and paramMap.status eq '50'}">selected="selected"</c:if>>终态</option>
						<option value="60" <c:if test="${paramMap.status ne null and paramMap.status eq '60'}">selected="selected"</c:if>>失败</option>
					
				</td>
				<td>
					发送文件名：<input type="text" name="sendFilename" value="${paramMap.sendFilename}" size="15" alt="精确查询"  />
				</td>
				<td>
					返回结果文件名：<input type="text" name="respFilename" value="${paramMap.respFilename}" size="15" alt="精确查询"  />
				</td>
				<td>
					<div class="subBar">
						<ul>
							<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
							<li><div class="buttonActive"><div class="buttonContent"><button type="button"  onclick="clearFormOperatorLogList()">清空条件</button></div></div></li>
						</ul>
					</div>
				</td>
			</tr>
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" targetType="navTab" asc="asc" desc="desc" width="100%" layoutH="130">
		<thead>
			<tr>
				<th>序号</th>
				<th>批次号</th>
				<th>渠道号</th>
				<th>发送文件名</th>
				<th>状态</th>
				<th>返回结果文件名</th>
				<th>创建时间</th>
				<th>最后修改时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		    <c:forEach  items="${pageInfo.list}" var="batchOutcomeDO" varStatus="st">
				<tr target="sid_user" rel="${batchOutcomeDO.id}">
				    <td>${st.index+1}</td>
				    <td>${batchOutcomeDO.batchId}</td>
				    <td>
						<c:if test="${batchOutcomeDO.channelId eq '000'}">快钱</c:if>
						<c:if test="${batchOutcomeDO.channelId eq '001'}">联动优势</c:if>
						<c:if test="${batchOutcomeDO.channelId eq '002'}">渤海</c:if>
						<c:if test="${batchOutcomeDO.channelId eq '003'}">易宝</c:if>
					</td>
					<td>${batchOutcomeDO.sendFilename}</td>
				    <td>
				    	<c:if test="${batchOutcomeDO.status eq '10'}">初始</c:if>
				    	<c:if test="${batchOutcomeDO.status eq '20'}">已发送文件</c:if>
				    	<c:if test="${batchOutcomeDO.status eq '30'}">处理中</c:if>
				    	<c:if test="${batchOutcomeDO.status eq '40'}">批次明细存在非终态</c:if>
				    	<c:if test="${batchOutcomeDO.status eq '50'}">终态</c:if>
				    	<c:if test="${batchOutcomeDO.status eq '60'}">失败</c:if>
					</td>
				    <td>${batchOutcomeDO.respFilename}</td>
					<td>
						<fmt:formatDate value="${batchOutcomeDO.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<fmt:formatDate value="${batchOutcomeDO.lastUpdateTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<z:permission value="pay:batctOutcome:edit">
							[<a href="${rc.contextPath}/pay/batchOutcome/editBatchOutcomeUI?id=${batchOutcomeDO.id}" title="修改订单" target="dialog" width="850" height="500" rel="input"  style="color:blue">修改</a>]
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
	$(':input',"form[action='${rc.contextPath}/pay/batchOutcome/listBatchOutcome']")
	 .not(':button, :submit, :reset, :hidden')  
	 .val('')  
	 .removeAttr('checked')  
	 .removeAttr('selected');  
}

</script>

    