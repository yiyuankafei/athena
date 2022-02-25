
    Selector.open()
    //对于linux环境来说，创建一个epoll对象，底层调用epoll_create系统调用

    sc.register(selector, SelectionKey.OP_CONNECT);

    //将一个fd注册到epoll对象上，并指定它要监听的事件类型，底层调用epoll_ctl系统调用
    //一般新建好的服务端socket，要先注册到selector上，并关注连接事件
    //有新的连接进来时，从就绪列表获取事件相关的fd，再次注册到selector上，并关注其读事件；写事件相关的流程一样
    //register方法返回一个SelectionKey，这个key和一个fd绑定，后续获取就绪队列遍历的时候，通过这个key可以拿到绑定的相关Channel，即fd
    //调用epoll_ctl后，相关的fd添加到一个红黑树上，便于新增、删除、查找指定节点，也就是连接
    //就绪队列rdlist是一个双向链表（待确认是双向链表还是跳表？？）
    channel.register(selector, 事件类型)

    SocketChannel sc = SocketChannel.open();
            sc.configureBlocking(false);
            Selector selector = Selector.open();
            sc.connect(new InetSocketAddress("127.0.0.1", 9000));
            sc.register(selector, SelectionKey.OP_CONNECT);

    //让epoll对象去获取要事件发生的fd，如果就绪列表为空，则阻塞，底层调用epoll_wait系统调用
    selector.select()

    //这种单线程多路复用的问题是，如果连接特别多，拿到就绪列表遍历的时间非常长，新的连接会进不来
    //Netty采用主从reactor模型，用一个Boss线程，专门处理连接请求，因为接收连接的速度是非常快的，接收到后，把它注册到其他线程的selector上，也就是worker线程
    //worker线程可以有多个，每个线程都有一个自己的selector对象，也就是epoll对象，专门处理读写事件

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

    //select
        // 每次通知内核去轮询，需要把用户空间相关的所有fd列表重置为0，拷贝到内核空间，select遍历后，把有事件发生的fd标记为1，再全量拷贝回用户空间
        // 用户进程并不知道哪个fd有事件发生，需要自己去遍历，遍历结束后，需要把fd全部置0，以便下次调用select
        // select使用一个bitmap来存储fd的标记，受限于数据结构，只能存储1024个fd，也就是它有能处理的最大连接数的限制（最大的fd编号为1024，也就是同时监听的fd数量可能还不到1024）
     //epoll
        //epoll用一个红黑树来维护需要监听的，也就是需要轮询的fd，只做增量维护，和epoll对象存在一起，所以不需要每次轮询都把fd从内核空间和用户空间之间来回拷贝
        //epoll使用一个就绪队列rdlist来存储已经发生了事件的fd列表，所以用户可以直接遍历这个列表处理事件，而不用遍历所有的fd
        //就绪队列的维护，依赖于操作系统的硬中断和软中断，以及相关的回调函数