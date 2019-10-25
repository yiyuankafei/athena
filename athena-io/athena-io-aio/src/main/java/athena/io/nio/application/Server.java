package athena.io.nio.application;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	//线程池
	private ExecutorService executorService;
	//线程组
	private AsynchronousChannelGroup threadGroup;
	//服务器通道
	public AsynchronousServerSocketChannel assc;
	
	public Server(int port) {
		try {
			executorService = Executors.newCachedThreadPool();
			threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);
			assc = AsynchronousServerSocketChannel.open(threadGroup);
			//绑定
			assc.bind(new InetSocketAddress(port));
			
			System.out.println("server start, port:" + port);
			
			//进行阻塞(第一个参数可以传null)
			assc.accept(this, new ServerCompletionHandler());
			//一直阻塞 不让服务器停止
			Thread.sleep(Integer.MAX_VALUE);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Server server = new Server(8862);
	}

}
