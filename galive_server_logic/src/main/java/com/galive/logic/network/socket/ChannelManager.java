package com.galive.logic.network.socket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.galive.logic.config.ApplicationConfig;
import com.galive.logic.model.User.UserOnlineState;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

public class ChannelManager {

	private Map<String, ChannelHandlerContext> clientChannels = new ConcurrentHashMap<>();
	
	public static final AttributeKey<String> USER_SID_KEY = AttributeKey.valueOf("userSid"); 
	
	private static ChannelManager instance = null;
	
	private String delimiter = "";
	
	public static ChannelManager getInstance() {
		if (instance == null) {
			instance = new ChannelManager();
			instance.delimiter = ApplicationConfig.getInstance().getSocketConfig().getMessageDelimiter();
		}
		return instance;
	}
	
	public ChannelHandlerContext findChannel(String userSid) {
		return clientChannels.get(userSid);
	}
	
	public void addChannel(String userSid, ChannelHandlerContext channel) {
		channel.attr(USER_SID_KEY).set(userSid);
		clientChannels.put(userSid, channel);
	}
	
	public void removeChannel(String userSid) {
		clientChannels.remove(userSid);
	}
	
	public void closeAndRemoveChannel(String userSid) {
		ChannelHandlerContext channel = clientChannels.get(userSid);
		closeChannel(channel);
		clientChannels.remove(userSid);
	}
	
	public long channelCount() {
		return clientChannels.size();
	}
	
	public void sendMessage(String userSid, String message) {
		if (message != null) {
			ChannelHandlerContext channel = clientChannels.get(userSid);
			if (channel != null && channel.channel().isActive()) {
				message += delimiter;
				channel.writeAndFlush(message);
			}
		}
	}
	
	public static void closeChannel(ChannelHandlerContext ctx) {
		if (ctx != null) {
			ctx.flush();
			ctx.close();
		}
	}
	
	public UserOnlineState getOnlineState(String userSid) {
		ChannelHandlerContext channel = findChannel(userSid);
		if (channel != null && channel.channel().isActive()) {
			return UserOnlineState.Online;
		}
		return UserOnlineState.Offline;
	}
}