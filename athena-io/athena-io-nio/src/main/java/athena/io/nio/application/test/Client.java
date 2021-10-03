package athena.io.nio.application.test;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Client {
    public static void main(String[] args) throws Exception {

        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        Selector selector = Selector.open();
        sc.connect(new InetSocketAddress("127.0.0.1", 9000));
        sc.register(selector, SelectionKey.OP_CONNECT);

        //需要另外启线程注册，这里demo不再详细写了
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //删除已选的key，防止重复处理
                iterator.remove();
                if (key.isConnectable()) {

                } else if (key.isReadable()) {

                }
            }
        }

    }
}
