package athena.io.nio.application.test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SelectorServer {

    private static List<SocketChannel> channelList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(9001));
        ssc.configureBlocking(false);

        //打开selector处理channel，即创建epoll
        Selector selector = Selector.open();
        //把serverSocketChannel注册到selector上，并且标注它对客户端连接事件感兴趣
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("服务启动");
        while (true) {
            //阻塞等待需要处理的事件发生
            selector.select();

            //获取selector中注册的全部事件的实例
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            //遍历SelectionKey对事件进行处理
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    if (socketChannel != null) {
                        socketChannel.configureBlocking(false);
                        //新的连接，后续需要等待它的数据，所以这里注册读事件，表示对这些新连接的读事件感兴趣
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    }
                } else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel)key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int length = socketChannel.read(byteBuffer);
                    if (length > 0) {
                        System.out.println("收到客户端消息：" + new String(byteBuffer.array(), "UTF-8"));
                        /*iterator.remove();
                        socketChannel.close();*/
                    } else if (length == -1) {
                        iterator.remove();
                        System.out.println("客户端断开连接");
                    }
                }
            }

        }
    }

}
