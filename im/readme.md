## 使用Netty实现IM

### ChannelHandler生命周期

![ChannelHandler生命周期](./src/main/resources/images/ChannelHandler生命周期.png)

1. `handlerAdded()`当检测到新连接之后，调用`ch.pipeline().addLast(new LifeCycleTestHnalder());`之后的回调，
表示在当前的channel中，已经成功添加一个handler处理器；
2. `channelRegistered`回调方法表示当前channel的所有的逻辑处理已经和某个NIO线程【NioEventLoop】建立了绑定关系；
3. `channelActive`当channel的所有的业务逻辑准备完毕以及绑定好NIO线程之后，会回调此方法；
4. `channelRead`客户端向服务端发来数据，每次都会回调此方法，表示有数据可读；
5. `channelReadComplete`服务端每次读完一次完整的数据之后，回调该方法，表示数据读取完毕。
6. `channelInactive`连接关闭，连接在TCP层面不是ESTABLISH状态；
7. `channelUNregistered`连接关闭，与此连接绑定的线程移除对连接的处理；
8. `handlerRemoved`连接关闭，添加到连接上的所有业务逻辑处理器都给移除掉。


