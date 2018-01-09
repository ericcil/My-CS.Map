package com.netty;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpVersion;

public class HttpClient {

	
	
private final static EventLoopGroup workerGroup = new NioEventLoopGroup(5);
	
	public DefaultFullHttpRequest createRequest(URI uri,String host,String msg) throws URISyntaxException, UnsupportedEncodingException{
        DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET,
                uri.toASCIIString(), Unpooled.wrappedBuffer(msg.getBytes("UTF-8")));

        // 构建http请求
        request.headers().set(HttpHeaderNames.HOST, host);
        request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        request.headers().set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
        request.headers().set(HttpHeaderNames.CONTENT_TYPE,HttpHeaderValues.APPLICATION_JSON);
        request.setMethod(HttpMethod.POST);
        return request;
	}
	
	public ChannelFuture connect(String host, int port,final BusinessHandler lastReadHandler) throws Exception {
        Bootstrap b = new Bootstrap();
        b.group(workerGroup);
        b.channel(NioSocketChannel.class);
        b.option(ChannelOption.SO_KEEPALIVE, true);
        //b.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000);
        
        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
            	ch.pipeline().addLast(new HttpResponseDecoder());//会将httpresponse拆分
            	ch.pipeline().addLast(new HttpObjectAggregator(1024*1024*64));
                ch.pipeline().addLast(lastReadHandler);
                // 客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
                ch.pipeline().addLast(new HttpRequestEncoder());
            }
        });
        // Start the client.
        ChannelFuture connectFuture = b.connect(host, port);
        return connectFuture;
    }
	
	public String syncHttp(String url,String msg) throws Exception{
		URI uri = new URI(url);
		int port = uri.getPort();
		String host = uri.getHost();
		HttpContentStore t = new HttpContentStore();
		ChannelFuture future = connect(host,port,t);
		DefaultFullHttpRequest request = createRequest(uri,host,msg);
		future.sync();//不在请求连接时等待连接完成
		future.channel().writeAndFlush(request).sync();
		future.channel().closeFuture().sync();
		return t.getHttpResult();
	}
	
	final class HttpContentStore extends BusinessHandler{
		@Override
		public void businessProcess(String httpContent) {
			//do nothing;
		}
		
	}
	
	
	
	static class Test extends Thread{

		@Override
		public void run() {
			String name = Thread.currentThread().getName();
			try {
				cb.await();
			} catch (InterruptedException | BrokenBarrierException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int times = 10;
			Long total = 0L;
			for(int i=0;i<times;i++) {
				
				Long begin = System.currentTimeMillis();
				String url = "http://127.0.0.1:8081/miaofen-api-shop/home/main/banner.json";
				//String url = "http://test1.miaofun.com:80/api/home/main/banner.json";
				String msg = "{\"local\":\"\",\"city\":\""+name+i+"\"}";
				HttpClient t = new HttpClient();
				String str = "";
				try {
					str = t.syncHttp(url, msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				//String str = HttpClientUtil.request(url, "POST", msg);
				Long time = System.currentTimeMillis()-begin;
				System.out.println("线程："+name+i+"，执行时间："+time+"ms;"+str);
				//System.out.println();
				total +=time;
			}
			//System.out.println("线程："+name+"平均时长："+(total/times)+"ms");
		}
		
	}
	
	private static final CyclicBarrier cb = new CyclicBarrier(10);
	
	public static void main(String[] args) throws Exception {
		ExecutorService etor = Executors.newFixedThreadPool(10);
		
		for(int i =0;i<10;i++) {
			Test t = new Test();
			etor.execute(t);
		}
		/*Long total = 0L;
		int times = 100;
		for(int i=0;i<times;i++) {
		
			Long begin = System.currentTimeMillis();
			//String url = "http://127.0.0.1:8081/miaofen-api-shop/home/main/banner.json";
			String url = "http://test1.miaofun.com:80/api/home/main/banner.json";
			String msg = "{\"local\":\"\",\"city\":\"wwww\"}";
			ChannelT t = new ChannelT();
			//String str = t.syncHttp(url, msg);
			String str = HttpClientUtil.request(url, "POST", msg);
			Long time = System.currentTimeMillis()-begin;
			System.out.println("执行时间："+time+"ms;"+str);
			System.out.println();
			total +=time;
		}
		System.out.println("平均时长："+(total/times)+"ms");*/
	}
	//665 X100
	//664 X100
}
