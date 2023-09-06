package org.example.netty.handler;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.example.entity.User;
import org.example.model.UserDataResponse;
import org.example.service.SHAService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Component
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {
    private final UserService userService;
    @Autowired
    public ServerHandler(UserService userService) {
        this.userService = userService;
        System.out.println("userService in constructor: "+userService);
    }

    private static final ChannelGroup channelOnlineGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);//查看上線
    private static final ChannelGroup channelGroup1 = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static final ChannelGroup channelGroup2 = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static HashMap<String, ChannelHandlerContext> clientContextMap = new HashMap<>();//先改成public看看
    private static HashMap<ChannelHandlerContext, ChannelGroup> clientChannelMap = new HashMap<>();//更改channel的時候可以操作
    private static HashMap<String, String> clientInfo = new HashMap<>();
    private static List<Channel> connectedClients = new ArrayList<>();
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //获取客户端发送过来的消息
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("收到client" + ctx.channel().remoteAddress() + "訊息：" + byteBuf.toString(CharsetUtil.UTF_8));
        String clientMessage=byteBuf.toString(CharsetUtil.UTF_8);
        if("username3".equals(clientMessage)){
            System.out.println("準備丟給spring boot");
            System.out.println("userservice:"+userService);
            User user = userService.findByUsername(clientMessage);
            System.out.println("user3:"+user);
            UserDataResponse userDataResponse = new UserDataResponse();
            userDataResponse.setName(user.getName());
            userDataResponse.setGrade(user.getGrade());
            String clientRemoteAddress = ctx.channel().remoteAddress().toString();
            System.out.println("userDataResponse:"+userDataResponse);
            System.out.println("username:"+userDataResponse.getName()+"\nip:"+clientRemoteAddress);
            clientInfo.put(clientRemoteAddress,userDataResponse.getName());
        } else if ("username5".equals(clientMessage)) {
            System.out.println("準備丟給spring boot");
            System.out.println("userservice:"+userService);
            User user = userService.findByUsername(clientMessage);
            System.out.println("user5:"+user);
            UserDataResponse userDataResponse = new UserDataResponse();
            userDataResponse.setName(user.getName());
            userDataResponse.setGrade(user.getGrade());
            String clientRemoteAddress = ctx.channel().remoteAddress().toString();
            System.out.println("userDataResponse:"+userDataResponse);
            System.out.println("username:"+userDataResponse.getName()+"\nip:"+clientRemoteAddress);
            clientInfo.put(clientRemoteAddress,userDataResponse.getName());
        }
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