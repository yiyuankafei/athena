package athena.io.bio.application;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static final Integer PORT = 8862;
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("server start");
			HandlerExecutorPool pool = new HandlerExecutorPool(50, 1000);
			while (true) {
				//進行阻塞
				Socket socket = serverSocket.accept();
				//新線程中處理
				//new Thread(new ServerHandler(socket)).start();
				pool.execute(new ServerHandler(socket));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			serverSocket = null;
		}
	}

}
