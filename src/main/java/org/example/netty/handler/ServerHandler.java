package org.example.netty.handler;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    private static final ChannelGroup channelOnlineGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);//查看上線
    private static final ChannelGroup channelGroup1 = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static final ChannelGroup channelGroup2 = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static HashMap<String, ChannelHandlerContext> clientContextMap = new HashMap<>();//先改成public看看
    private static HashMap<ChannelHandlerContext, ChannelGroup> clientChannelMap = new HashMap<>();//更改channel的時候可以操作
    private static List<Channel> connectedClients = new ArrayList<>();
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //获取客户端发送过来的消息
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("收到客户端" + ctx.channel().remoteAddress() + "发送的消息：" + byteBuf.toString(CharsetUtil.UTF_8));
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        this.ctx=ctx;
        System.out.println("Active:" + ctx);
//        channelGroup.add(ctx.channel());
        connectedClients.add(ctx.channel()); // 将新连接的客户端通道加入列表
        addClientName();
        //這邊要處理登入之後的邏輯
        String clientRemoteAddress = ctx.channel().remoteAddress().toString();//把ip轉成字串準備放Map
        clientContextMap.put(clientRemoteAddress, ctx);//ip及ctx加入map

        channelOnlineGroup.add(ctx.channel());

        System.out.println("Client " + ctx.channel().remoteAddress() + " connected.");
        //寫給客戶端可以開始輸入訊息!
        System.out.println("channelonlinegroup" + channelOnlineGroup);
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello world",CharsetUtil.UTF_8));
    }

    private void addClientName() {

    }



    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Server收到訊息", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}