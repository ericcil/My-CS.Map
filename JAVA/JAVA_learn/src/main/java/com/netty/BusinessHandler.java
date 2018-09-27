package com.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.util.CharsetUtil;

public abstract class BusinessHandler extends ChannelInboundHandlerAdapter{
	private volatile String httpResult = "";
	//private BusinessHandler h = this;
	
	@Override
	public final void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		if(msg instanceof HttpContent){
            HttpContent content = (HttpContent)msg;
            ByteBuf buf = content.content();
            ChannelFuture future = ctx.newSucceededFuture();
            final String s = buf.toString(CharsetUtil.UTF_8);
            
            future.addListener(new ChannelFutureListener() {
            	
				public void operationComplete(ChannelFuture future) throws Exception {
            		//synchronized (h) {
            			httpResult = s;
					//}
					businessProcess(s);
				}
			});
        }
		ctx.close();
	}
	
	
	abstract public void businessProcess(String httpContent);


	public String getHttpResult() {
		return httpResult;
	}
}
