<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/inc/taglib.jsp"%>
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="${rc.contextPath}/channel/listChannel" method="post">
	<%@include file="/WEB-INF/views/inc/pageForm.jsp"%>
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					<span style="width: 50px">支付渠道：</span>
					<select name="channelId" style="width: 158px; ">
						<option value="" selected="selected">--全部状态--</option>
					    <option value="000">快钱</option>
						<option value="001">联动优势</option>
						<option value="002">渤海</option>
						<option value="003">易宝</option>
					</select>
				</td>
				<td>
					<span style="width: 50px">银行名称：</span>
					<input type="text" name="bankName" value="${bankName}" style="width: 158px; " size="15" alt="模糊搜索" />
				</td>	
			</tr>
			<tr>
				<td>
					<span style="width: 50px">支付方式：</span>
					<select name="payType" style="width: 158px; ">
						<option value="" selected="selected">--全部状态--</option>
					    <option value="01">快捷支付</option>
						<option value="02">一键支付</option>
					</select>
				</td>
				<td>
					<span style="width: 50px">状态：</span>
					<select name="status"  style="width: 158px; ">
						<option value="">--全部状态--</option>
						<option value="10">启用</option>
						<option value="20">停用</option>
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
	<z:permission value="boss:cardBin:add">
		<ul class="toolBar">
			<li><a class="add" href="cardBin_addCardBinUI.action" target="dialog" rel="input"  title="添加卡Bin"><span>添加</span></a></li>
		</ul>
	</z:permission>
	</div>
<table class="table" targetType="navTab" asc="asc" desc="desc" width="100%" layoutH="160">
		<thead>
			<tr>
				<th>支付渠道</th>
				<th>银行名称</th>
				<th>单笔限额(元)</th>
				<th>当日限额(元)</th>
				<th>支付方式</th>
				<th>状态</th>
				<th>创建时间</th>
				<th>最后修改时间</th>
				<th>操作</th><!-- 图标列不能居中 -->
			</tr>
		</thead>
		<tbody>
		   <c:forEach  items="${pageInfo.list}" var="pmsOperatorLogDO" varStatus="st">
				<tr target="sid_user" rel="${id}">
					<td>${st.index+1}</td>
				    <td>${cardBin}</td>
				    <td>${bankCode}</td>
				    <td>${bankName}</td>
					<td>${cardName }</td>
					<td>
						<c:forEach items="${cardKindEnum }" var="help">
							<c:if test="${help.value eq cardKind }">${help.desc }</c:if>
						</c:forEach>
					</td>
					<td>${cardLength}</td>
					<td>
						<c:forEach items="${cardBinStatusEnum }" var="sim">
							<c:if test="${sim.value eq status }">${sim.desc }</c:if>
						</c:forEach>
					</td>
					<td>${lastUpdatorName}</td>
					<td><s:date name="lastUpdateTime" format="yyyy-MM-dd" /></td>
					<td>
					<z:permission value="boss:cardBin:edit">
						<a href="cardBin_editCardBinUI.action?id=${id}" title="修改卡Bin" target="dialog" style="color:blue">修改</a>
					</z:permission>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
  <!-- 分页条 -->
    <%@include file="/WEB-INF/views/inc/pageBar.jsp"%>
</div>
<script type="text/javascript">
	function cbChange(){
		$("form[name='cbForm']").submit();
	}
	
	function openExcel() {
		$.pdialog.open("cardBin_importExcelUI.action", "improtCardBin","导入卡bin信息", {
				max : false,
				mask : true,
				mixable : false,
				minable : false,
				resizable : false,
				drawable : true,
				fresh : true,
				close : "function"
		});
	}

</script>