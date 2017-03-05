package cn.memedai.framework.dubbo.ext.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import lombok.extern.slf4j.Slf4j;

import cn.memedai.ups.boss.utils.StrUtil;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
@Slf4j
@Activate(group = {Constants.PROVIDER, Constants.CONSUMER})
public class TraceFilter implements Filter{

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation)
			throws RpcException {
		log.info("\n___________开始调用dubbo__________");
		
		String traceId = (String) invocation.getAttachments().get("THREAD_ID");//, MDC.get("THREAD_ID")
		if(StringUtils.isNotBlank(traceId)){
			MDC.put("THREAD_ID",traceId);
		}else{
			traceId = MDC.get("THREAD_ID");
			if(StringUtils.isNotBlank(traceId)){
				//RpcContext.getContext().set("THREAD_ID", traceId);
				invocation.getAttachments().put("THREAD_ID", traceId);
			}else{
				MDC.put("THREAD_ID",StrUtil.get32UUID());
				invocation.getAttachments().put("THREAD_ID", StrUtil.get32UUID());
			}
		}
		Result result = invoker.invoke(invocation);
		return result;
	}

}
