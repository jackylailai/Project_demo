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
import org.example.service.sql.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("收到client" + ctx.channel().remoteAddress() + "訊息：" + byteBuf.toString(CharsetUtil.UTF_8));
        String clientRealMessage = byteBuf.toString(CharsetUtil.UTF_8);
        //處理channel需放入有的內容
        String channelInfo = getChannelGroupInfo(ctx, channelGroup1, channelGroup2);
        System.out.println(channelInfo);
        String clientmessage = channelInfo+"client"+ctx.channel().remoteAddress()+"的訊息:"+byteBuf.toString(CharsetUtil.UTF_8);
        System.out.println("\n" + "收到 client 端" + ctx.channel().remoteAddress() + "的消息：" + byteBuf.toString(CharsetUtil.UTF_8));
        switch (clientRealMessage) {
//            case "getonlineusers" -> sendOnlineUsers();
            case "channel1" -> {
                //有人想到chaneel1來 我先查看他有沒有註冊過map
                if (clientChannelMap.containsKey(ctx)) {
                    //看一下這個人ctx有沒有在map註冊過
                    ChannelGroup previousChannelGroup = clientChannelMap.get(ctx);
                    //用以確認此認註冊過的話，那是註冊哪裡?是1嗎?不是1的話我就幫你刪除之前的group
                    if (previousChannelGroup != channelGroup1) {
                        System.out.println("你原本是在" + previousChannelGroup + "不是1的話我就幫你刪除之前的group");
                        previousChannelGroup.remove(ctx.channel());
                        clientChannelMap.remove(ctx);
                    }
                }
                channelGroup1.add(ctx.channel());
                System.out.println("Client " + ctx.channel().remoteAddress() + " connected to Channel 1.");
                String clientAddMessage = "加入CHANNEL1";
                clientChannelMap.put(ctx, channelGroup1);
                ByteBuf responseByteBuf = ctx.alloc().buffer();
                responseByteBuf.writeBytes(clientAddMessage.getBytes(CharsetUtil.UTF_8));
                ctx.writeAndFlush(responseByteBuf);
            }
            case "channel2" -> {
                //有人想到channel2來
                if (clientChannelMap.containsKey(ctx)) {
                    //看一下這個人ctx有沒有在map註冊過
                    ChannelGroup previousChannelGroup = clientChannelMap.get(ctx);
                    if (previousChannelGroup != channelGroup2) {
                        //用以確認此認註冊過的話，那是註冊哪裡?是2嗎?
                        System.out.println("你原本是在" + previousChannelGroup + "不是2的話我就幫你刪除之前的group");
                        previousChannelGroup.remove(ctx.channel());
                        clientChannelMap.remove(ctx);
                    }
                }
                channelGroup2.add(ctx.channel());
                System.out.println("Client " + ctx.channel().remoteAddress() + " connected to Channel 2.");
                String clientAddMessage = "加入CHANNEL2";
                clientChannelMap.put(ctx, channelGroup2);
                ByteBuf responseByteBuf = ctx.alloc().buffer();
                responseByteBuf.writeBytes(clientAddMessage.getBytes(CharsetUtil.UTF_8));
                ctx.writeAndFlush(responseByteBuf);
            }
            default -> {
                //預設除了上述的話就是要傳訊息出來
                //如果你是群組1就去聽群組1的東西，如果妳都不是群組的人，那就在廣播裡面
                if (channelGroup1.contains(ctx.channel())) {
                    System.out.println("判斷是1");
                    processClientMessage(ctx, clientRealMessage, channelGroup1, "Channel 1");
                } else if (channelGroup2.contains(ctx.channel())) {
                    System.out.println("判斷是2");
                    processClient2Message(ctx, clientRealMessage, channelGroup2, "Channel 2");
                } else {
                    //將read收到的訊息傳送給大家(類似全頻聊天室)
                    for (ChannelHandlerContext clientContext : clientContextMap.values()) {
                        if (clientContext != ctx) {
                            String clientToAllMessage = clientmessage+" ";
                            ByteBuf responseByteBuf = clientContext.alloc().buffer();
                            responseByteBuf.writeBytes(clientToAllMessage.getBytes(CharsetUtil.UTF_8));
                            System.out.println("進入read map裡面的迴圈,準備開始傳送內容到各ip(如果你是本人我就不給你了)聊天室內容");
                            clientContext.writeAndFlush(responseByteBuf);
                        }
                    }
                }
            }
        }
//        原先預設處理client

//        if("username3".equals(clientMessage)){
//            System.out.println("準備丟給spring boot");
//            System.out.println("userservice:"+userService);
//            User user = userService.findByUsername(clientMessage);
//            System.out.println("user3:"+user);
//            UserDataResponse userDataResponse = new UserDataResponse();
//            userDataResponse.setName(user.getName());
//            userDataResponse.setGrade(user.getGrade());
//            String clientRemoteAddress = ctx.channel().remoteAddress().toString();
//            System.out.println("userDataResponse:"+userDataResponse);
//            System.out.println("username:"+userDataResponse.getName()+"\nip:"+clientRemoteAddress);
//            clientInfo.put(clientRemoteAddress,userDataResponse.getName());
//        } else if ("username5".equals(clientMessage)) {
//            System.out.println("準備丟給spring boot");
//            System.out.println("userservice:"+userService);
//            User user = userService.findByUsername(clientMessage);
//            System.out.println("user5:"+user);
//            UserDataResponse userDataResponse = new UserDataResponse();
//            userDataResponse.setName(user.getName());
//            userDataResponse.setGrade(user.getGrade());
//            String clientRemoteAddress = ctx.channel().remoteAddress().toString();
//            System.out.println("userDataResponse:"+userDataResponse);
//            System.out.println("username:"+userDataResponse.getName()+"\nip:"+clientRemoteAddress);
//            clientInfo.put(clientRemoteAddress,userDataResponse.getName());
//        }
    }

    private void processClientMessage(ChannelHandlerContext ctx,String clientRealMessage, ChannelGroup channelGroup1, String s) {
        String clientToChannel1Message = s + ctx.channel().remoteAddress()+" : "+ clientRealMessage;
        System.out.println("C1 group"+channelGroup1);
        System.out.println(clientToChannel1Message);
        for (Map.Entry<ChannelHandlerContext, ChannelGroup> entry : clientChannelMap.entrySet()) {
            ChannelHandlerContext key = entry.getKey();
            ChannelGroup value = entry.getValue();
            if(key!=ctx && value.equals(channelGroup1)){
                System.out.println("進入channel1迴圈，準備把內容給予channel1的人(除了本人)"+value);
                ByteBuf responseByteBuf = key.alloc().buffer();
                responseByteBuf.writeBytes(clientToChannel1Message.getBytes(CharsetUtil.UTF_8));
                key.writeAndFlush(responseByteBuf);
            }
        }
//                channelGroup.writeAndFlush(Unpooled.copiedBuffer(clientToChannel1Message, CharsetUtil.UTF_8));
    }
    private void processClient2Message(ChannelHandlerContext ctx, String clientRealMessage, ChannelGroup channelGroup2, String s) {
        String clientToChannel2Message = s + ctx.channel().remoteAddress()+" : "+ clientRealMessage;
        System.out.println("C2 group"+channelGroup2);
        System.out.println(clientToChannel2Message);
        for (Map.Entry<ChannelHandlerContext, ChannelGroup> entry : clientChannelMap.entrySet()) {
            ChannelHandlerContext key = entry.getKey();
            ChannelGroup value = entry.getValue();
            if(key!=ctx && value.equals(channelGroup2)){
                System.out.println("進入channel2迴圈，準備把內容給予channel2的人(除了本人)"+value);
                ByteBuf responseByteBuf = key.alloc().buffer();
                responseByteBuf.writeBytes(clientToChannel2Message.getBytes(CharsetUtil.UTF_8));
                key.writeAndFlush(responseByteBuf);
            }
        }
//                channelGroup.writeAndFlush(Unpooled.copiedBuffer(clientToChannel2Message, CharsetUtil.UTF_8));
    }
    private String getChannelGroupInfo(ChannelHandlerContext ctx, ChannelGroup channelGroup1, ChannelGroup channelGroup2) {
        if (channelGroup1.contains(ctx.channel())) {
            return "Channel group1:";
        } else if (channelGroup2.contains(ctx.channel())) {
            return "Channel group2:";
        } else {
            return "Channel open group:";
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