package athena.io.bio.application;

import java.io.*;
import java.net.Socket;

public class Client {
	
	private static final String ADDRESS = "127.0.0.1";
	
	private static final Integer PORT = 9001;
	
	public static void main(String[] args) {
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		
		try {
			socket = new Socket(ADDRESS,PORT);
			OutputStream os = socket.getOutputStream();
			os.write("hello from client111-aaa313333333333333333333333333666778".getBytes());
			os.flush();
			InputStream is = socket.getInputStream();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] bytes = new byte[1024];
			int length = 0;
			while ((length = is.read(bytes)) >= 0) {
				bos.write(bytes);
			}
			System.out.println("收到响应：" + new String(bos.toByteArray(), "UTF-8"));

			/*in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			//向服務器發送數據
			out.println("接受客戶端的請求數據...");
			String response = in.readLine();
			System.out.println("client:" + response);*/
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			socket = null;
		}
	}
}
