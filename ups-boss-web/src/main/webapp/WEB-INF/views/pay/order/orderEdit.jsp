<%-- 支付模块:订单管理:添加或修改页面 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/inc/taglib.jsp"%>
<div class="pageContent">
	<form id="form" method="post" action="${rc.contextPath}/pay/order/editOrder" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="58">
		    <input type="hidden" name="navTabId" value="listOrder">
			<input type="hidden" name="callbackType" value="closeCurrent">
			<input type="hidden" name="forwardUrl" value="">
			
			<input type="hidden" name="orderId" value="${orderDO.orderId}">
			<p>
				<label>ups流水号：</label>
				<label>${orderDO.upsTransNum}</label>
			</p>
			<p>
				<label>商户号：</label>
				<label>${orderDO.merchantCode}</label>
			</p>
			<p>
				<label>商户流水号：</label>
				<label>${orderDO.merchantTradeCode}</label>
			</p>
			<p>
				<label>交易金额(分)：</label>
				<label>${orderDO.tradeAmount}</label>
			</p>
			<p>
				<label>银行返回码：</label>
				<label>${orderDO.respCode}</label>
			</p>
			<p>
				<label>银行返回说明：</label>
				<label>${orderDO.codeMsg}</label>
			</p>
			<p>
				<label>状态：</label>
				<select name="status">
					<option value="10" <c:if test="${orderDO.status ne null and orderDO.status eq '10'}">selected="selected"</c:if>>受理成功</option>
					<option value="11" <c:if test="${orderDO.status ne null and orderDO.status eq '11'}">selected="selected"</c:if>>校验失败</option>
					<option value="20" <c:if test="${orderDO.status ne null and orderDO.status eq '20'}">selected="selected"</c:if>>受理失败</option>
					<option value="30" <c:if test="${orderDO.status ne null and orderDO.status eq '30'}">selected="selected"</c:if>>交易处理中</option>
					<option value="40" <c:if test="${orderDO.status ne null and orderDO.status eq '40'}">selected="selected"</c:if>>交易成功</option>
					<option value="50" <c:if test="${orderDO.status ne null and orderDO.status eq '50'}">selected="selected"</c:if>>交易失败</option>
					<option value="60" <c:if test="${orderDO.status ne null and orderDO.status eq '60'}">selected="selected"</c:if>>交易异常</option>
				</select>
			</p>
			<p>
				<label>支付通道：</label>
				<label>${orderDO.payGateway}</label>
			</p>
			<p>
				<label>支付方式：</label>
				<label>${orderDO.payType}</label>
			</p>
			<p>
				<label>银行卡号：</label>
				<label>${orderDO.bankAccount}</label>
			</p>
			<p>
				<label>银行卡户名：</label>
				<label>${orderDO.bankAccountName}</label>
			</p>
			<p>
				<label>身份证号：</label>
				<label>${orderDO.idCard}</label>
			</p>
			<p>
				<label>手机号：</label>
				<label>${orderDO.mobilePhone}</label>
			</p>
			<p>
				<label>验证码：</label>
				<label>${orderDO.validCode}</label>
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
