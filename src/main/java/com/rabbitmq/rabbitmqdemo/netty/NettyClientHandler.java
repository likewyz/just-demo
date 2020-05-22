package com.rabbitmq.rabbitmqdemo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author wyz
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;
        System.out.println("服务器端发来的消息 ： " + byteBuf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 通道就绪事件
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client : " + ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("老板，还钱吧", CharsetUtil.UTF_8));
    }
}
