<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/inc/taglib.jsp"%>

<script type="text/javascript" src="${rc.contextPath}/js/common.js"></script>
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="${rc.contextPath}/pay/order/listOrder" method="post">
	<!-- 分页表单参数 -->
    <%@include file="/WEB-INF/views/inc/pageForm.jsp"%>
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					ups流水号：<input type="text" name="upsTransNum" value="${paramMap.upsTransNum}" size="15" alt="精确查询"  />
				</td>
				<td>
					商户号：
					<select name="merchantCode">
						<option value="">-请选择-</option>
						<option value="0000" <c:if test="${paramMap.merchantCode ne null and paramMap.merchantCode eq '0000'}">selected="selected"</c:if>>么么贷公众号</option>
						<option value="0001" <c:if test="${paramMap.merchantCode ne null and paramMap.merchantCode eq '0001'}">selected="selected"</c:if>>遛米</option>
						<option value="0002" <c:if test="${paramMap.merchantCode ne null and paramMap.merchantCode eq '0002'}">selected="selected"</c:if>>分期网关</option>
						<option value="0003" <c:if test="${paramMap.merchantCode ne null and paramMap.merchantCode eq '0003'}">selected="selected"</c:if>>么理财</option>
						<option value="0004" <c:if test="${paramMap.merchantCode ne null and paramMap.merchantCode eq '0004'}">selected="selected"</c:if>>么么钱包</option>
						<option value="0005" <c:if test="${paramMap.merchantCode ne null and paramMap.merchantCode eq '0005'}">selected="selected"</c:if>>商户商品管理系统</option>
						<option value="0006" <c:if test="${paramMap.merchantCode ne null and paramMap.merchantCode eq '0006'}">selected="selected"</c:if>>现金贷</option>
						<option value="0007" <c:if test="${paramMap.merchantCode ne null and paramMap.merchantCode eq '0007'}">selected="selected"</c:if>>统一打款系统</option>
					
				</td>
				<td>
					商户流水号：<input type="text" name="merchantTradeCode" value="${paramMap.merchantTradeCode}" size="15" alt="精确查询"  />
				</td>
				<td>
					银行卡号：<input type="text" name="bankAccount" value="${paramMap.bankAccount}" size="15" alt="精确查询"  />
				</td>
				<td>
					银行卡户名：<input type="text" name="bankAccountName" value="${paramMap.bankAccountName}" size="15" alt="精确查询"  />
				</td>
			</tr>
			<tr>
				<td>
					身份证号：<input type="text" name="idCard" value="${paramMap.idCard}" size="15" alt="精确查询"  />
				</td>
				<td>
					手机号：<input type="text" name="mobilePhone" value="${paramMap.mobilePhone}" size="15" alt="精确查询"  />
				</td>
				<td>
					支付通道：
					<select name="payGateway">
						<option value="">-请选择-</option>
						<option value="000" <c:if test="${paramMap.payGateway ne null and paramMap.payGateway eq '000'}">selected="selected"</c:if>>快钱</option>
						<option value="001" <c:if test="${paramMap.payGateway ne null and paramMap.payGateway eq '001'}">selected="selected"</c:if>>联动优势</option>
						<option value="002" <c:if test="${paramMap.payGateway ne null and paramMap.payGateway eq '002'}">selected="selected"</c:if>>渤海</option>
						<option value="003" <c:if test="${paramMap.payGateway ne null and paramMap.payGateway eq '003'}">selected="selected"</c:if>>易宝</option>
					</select>
				</td>
				<td>
					支付方式：
					<select name="payType">
						<option value="">-请选择-</option>
						<option value="01" <c:if test="${paramMap.payType ne null and paramMap.payType eq '01'}">selected="selected"</c:if>>快捷</option>
						<option value="02" <c:if test="${paramMap.payType ne null and paramMap.payType eq '02'}">selected="selected"</c:if>>一键支付</option>
					</select>
				</td>
				<td>
					状态：
					<select name="status">
						<option value="">-请选择-</option>
						<option value="10" <c:if test="${paramMap.status ne null and paramMap.status eq '10'}">selected="selected"</c:if>>受理成功</option>
						<option value="11" <c:if test="${paramMap.status ne null and paramMap.status eq '11'}">selected="selected"</c:if>>校验失败</option>
						<option value="20" <c:if test="${paramMap.status ne null and paramMap.status eq '20'}">selected="selected"</c:if>>受理失败</option>
						<option value="30" <c:if test="${paramMap.status ne null and paramMap.status eq '30'}">selected="selected"</c:if>>交易处理中</option>
						<option value="40" <c:if test="${paramMap.status ne null and paramMap.status eq '40'}">selected="selected"</c:if>>交易成功</option>
						<option value="50" <c:if test="${paramMap.status ne null and paramMap.status eq '50'}">selected="selected"</c:if>>交易失败</option>
						<option value="60" <c:if test="${paramMap.status ne null and paramMap.status eq '60'}">selected="selected"</c:if>>交易异常</option>
					</select>
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
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" targetType="navTab" asc="asc" desc="desc" width="100%" layoutH="130">
		<thead>
			<tr>
				<th>序号</th>
				<th>状态</th>
				<th>商户号</th>
				<th>商户流水号</th>
				<th>ups流水号</th>
				<th>交易金额(分)</th>
				<th>银行返回码</th>
				<th>银行返回信息</th>
				<th>支付通道</th>
				<th>银行卡号</th>
				<th>银行卡户名</th>
				<th>身份证号</th>
				<th>手机号</th>
				<th>支付方式</th>
				<th>创建时间</th>
				<th>最后修改时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		    <c:forEach  items="${pageInfo.list}" var="orderDO" varStatus="st">
				<tr target="sid_user" rel="${orderDO.orderId}">
				    <td>${st.index+1}</td>
				    <td>
				    	<c:if test="${orderDO.status eq '10'}">受理成功</c:if>
				    	<c:if test="${orderDO.status eq '11'}">校验失败</c:if>
				    	<c:if test="${orderDO.status eq '20'}">受理失败</c:if>
				    	<c:if test="${orderDO.status eq '30'}">交易处理中</c:if>
				    	<c:if test="${orderDO.status eq '40'}">交易成功</c:if>
				    	<c:if test="${orderDO.status eq '50'}">交易失败</c:if>
				    	<c:if test="${orderDO.status eq '60'}">交易异常</c:if>
					</td>
				    <td>
				    	<c:if test="${orderDO.merchantCode eq '0000'}">么么贷公众号</c:if>
				    	<c:if test="${orderDO.merchantCode eq '0001'}">遛米</c:if>
				    	<c:if test="${orderDO.merchantCode eq '0002'}">分期网关</c:if>
				    	<c:if test="${orderDO.merchantCode eq '0003'}">么理财</c:if>
				    	<c:if test="${orderDO.merchantCode eq '0004'}">么么钱包</c:if>
				    	<c:if test="${orderDO.merchantCode eq '0005'}">商户商品管理系统</c:if>
				    	<c:if test="${orderDO.merchantCode eq '0006'}">现金贷</c:if>
				    	<c:if test="${orderDO.merchantCode eq '0007'}">统一打款系统</c:if>
				    </td>
				    <td>${orderDO.merchantTradeCode}</td>
				    <td>${orderDO.upsTransNum}</td>
				    <td>${orderDO.tradeAmount}</td>
				    <td>${orderDO.respCode}</td>
				    <td>${orderDO.codeMsg}</td>
					<td>
						<c:if test="${orderDO.payGateway eq '000'}">快钱</c:if>
						<c:if test="${orderDO.payGateway eq '001'}">联动优势</c:if>
						<c:if test="${orderDO.payGateway eq '002'}">渤海</c:if>
						<c:if test="${orderDO.payGateway eq '003'}">易宝</c:if>
					</td>
					<td>${orderDO.bankAccount}</td>
					<td>${orderDO.bankAccountName}</td>
					<td>${orderDO.idCard}</td>
					<td>${orderDO.mobilePhone}</td>
					<td>
						<c:if test="${orderDO.payType eq '01'}">快捷支付</c:if>
						<c:if test="${orderDO.payType eq '02'}">一键支付 </c:if>
						<c:if test="${orderDO.payType eq '03'}">出款</c:if>
					</td>
					<td>${orderDO.createTeim}</td>
					<td>${orderDO.lastUpdateTime}</td>
					<td>
						<z:permission value="pay:order:edit">
							[<a href="${rc.contextPath}/pay/order/editOrderUI?orderId=${orderDO.orderId}" title="修改订单" target="dialog" width="850" height="500" rel="input"  style="color:blue">修改</a>]
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
	$(':input',"form[action='${rc.contextPath}/payQuery/listPayQuery']")
	 .not(':button, :submit, :reset, :hidden')  
	 .val('')  
	 .removeAttr('checked')  
	 .removeAttr('selected');  
}

</script>

    