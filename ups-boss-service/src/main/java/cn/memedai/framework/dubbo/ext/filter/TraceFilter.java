package cn.memedai.framework.dubbo.ext.filter;

import lombok.extern.slf4j.Slf4j;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
@Slf4j
@Activate(group = {Constants.PROVIDER, Constants.CONSUMER})
public class TraceFilter implements Filter{

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation)
			throws RpcException {
		log.info("\n___________开始调用dubbo__________");
		Result result = invoker.invoke(invocation);
		return result;
	}

}
