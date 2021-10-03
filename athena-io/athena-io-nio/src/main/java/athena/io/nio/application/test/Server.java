package athena.io.nio.application.test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Server {

    private static List<SocketChannel> channelList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(9000));
        ssc.configureBlocking(false);
        System.out.println("服务启动");
        while (true) {
            SocketChannel sc = ssc.accept();
            if (sc != null) {
                System.out.println("收到连接");
                sc.configureBlocking(false);
                channelList.add(sc);
            }

            Iterator<SocketChannel> iterator = channelList.iterator();
            while (iterator.hasNext()) {
                SocketChannel next = iterator.next();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int length = next.read(byteBuffer);
                if (length > 0) {
                    System.out.println("收到客户端消息：" + new String(byteBuffer.array(), "UTF-8"));
                    iterator.remove();
                    next.close();
                } else if (length == -1) {
                    iterator.remove();
                    System.out.println("客户端断开连接");
                }
            }

        }
    }

}
