package com.galive.logic.network.http.handler;

import com.galive.common.protocol.Command;
import com.galive.common.protocol.CommandOut;
import com.galive.logic.network.http.HttpRequestHandler;
import com.galive.logic.service.AccountService;
import com.galive.logic.service.AccountServiceImpl;
import com.galive.logic.service.MeetingService;
import com.galive.logic.service.MeetingServiceImpl;

@HttpRequestHandler(desc = "用户登出", command = Command.USR_LOGOUT)
public class LogoutHandler extends HttpBaseHandler {
	
	private AccountService accountService = new AccountServiceImpl();
	private MeetingService meetingService = new MeetingServiceImpl();
	
	@Override
	public CommandOut handle(String account, String reqData) throws Exception {
		appendLog("--LogoutHandler(用户登出)--");
		meetingService.leaveMeeting(account);
		meetingService.stopMeeting(account);
		accountService.logout(account);
		CommandOut out = new CommandOut(Command.USR_LOGOUT);
		return out;
	}
	
	
}
