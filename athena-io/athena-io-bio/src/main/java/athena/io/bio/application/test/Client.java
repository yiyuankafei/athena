package athena.io.bio.application.test;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("127.0.0.1", 8862);
        OutputStream os = s.getOutputStream();
        os.write("holidy".getBytes());
        s.shutdownOutput();
        byte[] bytes = new byte[1024];
        int length = -1;
        InputStream is = s.getInputStream();
        ByteOutputStream bos = new ByteOutputStream();
        while ((length = is.read(bytes)) > 0) {
            bos.write(bytes);
        }
        System.out.println("收到响应：" + new String(bos.getBytes(), "UTF-8"));

        s.close();
    }
}
