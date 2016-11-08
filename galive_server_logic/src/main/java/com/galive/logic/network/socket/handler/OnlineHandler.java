package com.galive.logic.network.socket.handler;

import org.apache.commons.lang.StringUtils;

import com.galive.common.protocol.Command;
import com.galive.common.protocol.CommandOut;
import com.galive.logic.model.Meeting;
import com.galive.logic.network.socket.SocketRequestHandler;
import com.galive.logic.service.MeetingService;
import com.galive.logic.service.MeetingServiceImpl;

@SocketRequestHandler(desc = "用户上线", command = Command.ONLINE)
public class OnlineHandler extends SocketBaseHandler {

	private MeetingService meetingService = new MeetingServiceImpl();

	@Override
	public CommandOut handle(String account, String reqData) throws Exception {
		appendLog("--OnlineHandler(用户上线)--");

		OnlineOut out = new OnlineOut();
		// 返回会议信息
		Meeting meeting = meetingService.findMeeting(null, account, null);
		if (!StringUtils.isEmpty(meeting.getRoom())) {
			out.meeting = meeting;
		} else {
			meeting = meetingService.findMeeting(null, null, account);
			if (meeting != null) {
				out.meeting = meeting;
			}
		}

		return out;
	}
	
	public class OnlineOut extends CommandOut {

		public Meeting meeting;
		
		public OnlineOut() {
			super(Command.ONLINE);
			
		}
		
	}

}
