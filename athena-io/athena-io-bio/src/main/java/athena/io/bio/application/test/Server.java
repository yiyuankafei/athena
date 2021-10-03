package athena.io.bio.application.test;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket sc = new ServerSocket(8862);
        while (true) {
            System.out.println("等待连接...");
            Socket s = sc.accept();
            System.out.println("收到客户端连接...");
            byte[] bytes = new byte[1024];
            int length = -1;
            InputStream is = s.getInputStream();
            ByteOutputStream bos = new ByteOutputStream();
            while ((length = is.read(bytes)) > 0) {
                bos.write(bytes);
            }
            System.out.println("收到请求：" + new String(bos.getBytes(), "UTF-8"));


            OutputStream os = s.getOutputStream();
            os.write("hello client".getBytes());
            os.flush();
            s.shutdownOutput();
        }

    }
}
