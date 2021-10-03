package athena.io.bio.application;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static final Integer PORT = 8862;
	
	public static void main(String[] args) throws Exception {

	   ServerSocket serverSocket = null;
         /*while (true) {
            Socket socket = serverSocket.accept();
            byte[] bytes = new byte[1024];

        }*/

		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("server start");
			while (true) {
                System.out.println("等待请求");
                Socket socket = serverSocket.accept();
                System.out.println("收到连接");
                InputStream inputStream = socket.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] bytes = new byte[1024];
                int length = 0;
                while ((length = inputStream.read(bytes)) != -1) {
                    bos.write(bytes);
                }
                System.out.println("客户端请求：" + new String(bos.toByteArray(), "UTF-8"));

                OutputStream os = socket.getOutputStream();
                os.write("收到".getBytes());
                os.flush();
            }
			/*HandlerExecutorPool pool = new HandlerExecutorPool(50, 1000);
			while (true) {
				//進行阻塞
				Socket socket = serverSocket.accept();
				//新線程中處理
				//new Thread(new ServerHandler(socket)).start();
				pool.execute(new ServerHandler(socket));
				
			}*/
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
