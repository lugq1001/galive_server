package com.galive.logic.network.socket.handler;

import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.galive.common.protocol.Command;
import com.galive.common.protocol.PageParams;
import com.galive.common.protocol.PageCommandOut;
import com.galive.logic.model.User;
import com.galive.logic.network.model.RespUser;
import com.galive.logic.network.socket.SocketRequestHandler;
import com.galive.logic.service.LiveService;
import com.galive.logic.service.LiveServiceImpl;

@SocketRequestHandler(desc = "直播观众列表", command = Command.LIVE_AUDIENCE_LIST)
public class LiveAudienceListHandler extends SocketBaseHandler  {
	
	private LiveService liveService = new LiveServiceImpl();
	
	@Override
	public String handle(String userSid, String reqData) throws Exception {
		appendLog("--LiveAudienceListHandler(直播观众列表)--");
		LiveAudienceListIn in = JSON.parseObject(reqData, LiveAudienceListIn.class);
		
		String liveSid = in.liveSid;
		int index = in.index;
		int size = in.size;
		appendLog("直播id(liveSid):" + liveSid);
		appendLog("起始游标(index):" + index);
		appendLog("分页数量(size):" + size);
		
		List<User> users = liveService.listAudiences(liveSid, index, size);
		List<RespUser> respUsers = new ArrayList<>();
		for (User u : users) {
			RespUser respUser = new RespUser();
			respUser.convert(u);
			respUsers.add(respUser);
		}
		PageCommandOut<RespUser> out = new PageCommandOut<>(Command.LIVE_AUDIENCE_LIST, in);
		out.setData(respUsers);
		String resp = out.socketResp();
		return resp;
	}
	
	public static class LiveAudienceListIn extends PageParams {
		public String liveSid = "";
	}
}