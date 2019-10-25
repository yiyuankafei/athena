package athena.io.nio.application;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class ServerCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, Server>{

	/**
	 * 客户端连接成功
	 * AsynchronousSocketChannel  客户端的通道
	 */
	@Override
	public void completed(AsynchronousSocketChannel asc, Server attachement) {
		// 当有下一个客户端接入的时候，直接调用Server的accept方法， 这样反复执行下去， 保证多个客户端都可以阻塞
		attachement.assc.accept(attachement, this);
		read(asc);
		
	}

	/**
	 * 客户端连接失败
	 */
	@Override
	public void failed(Throwable exc, Server attachment) {
		exc.printStackTrace();
	}
	
	private void read(final AsynchronousSocketChannel asc) {
		//读取数据
		ByteBuffer buf = ByteBuffer.allocate(1024);
		asc.read(buf, buf, new CompletionHandler<Integer, ByteBuffer>() {
			@Override
			public void completed(Integer resultSize, ByteBuffer attachment) {
				//进行读取后，重置标识位
				attachment.flip();
				//获取读取的字节数
				System.out.println("Server -> 收到客户端数据长度为：" + resultSize);
				//获取读取的数据
				String resultData = new String(attachment.array()).trim();
				System.out.println("Server -> 收到客户端数据信息为：" + resultData);
				String response = "服务器响应, 收到了客户端发送的数据：" + resultData;
				write(asc, response);
			}
			
			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
				exc.printStackTrace();
			}
			
		});
	}
	
	private void write(final AsynchronousSocketChannel asc, String response) {
		try {
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			buffer.put(response.getBytes());
			buffer.flip();
			asc.write(buffer).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
