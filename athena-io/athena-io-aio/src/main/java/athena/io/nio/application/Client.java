package athena.io.nio.application;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

public class Client implements Runnable {
	
	private AsynchronousSocketChannel asc;
	
	public Client() throws Exception {
		asc = AsynchronousSocketChannel.open();
	}
	
	public void connect () {
		asc.connect(new InetSocketAddress("127.0.0.1", 8862));
	}
	
	public void write(String request) {
		try {
			asc.write(ByteBuffer.wrap(request.getBytes())).get();
			read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void read() {
		ByteBuffer buffer = ByteBuffer.allocate(1204);
		try {
			asc.read(buffer).get();
			buffer.flip();
			byte[] responseBytes = new byte[buffer.remaining()];
			buffer.get(responseBytes);
			System.out.println(new String(responseBytes, "UTF-8").trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		Client c1 = new Client();
		c1.connect();
		
		Client c2 = new Client();
		c2.connect();
		
		Client c3 = new Client();
		c3.connect();
		
		new Thread(c1, "c1").start();
		new Thread(c2, "c2").start();
		new Thread(c3, "c3").start();
		
		Thread.sleep(1000);
		
		c1.write("c1 message: aaa");
		c2.write("c2 message: bbb");
		c3.write("c3 message: ccccccc");
		
	}

}
