package com.galive.logic.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

import com.galive.logic.config.ApplicationConfig;
import com.galive.logic.dao.UserCache;
import com.galive.logic.dao.UserCacheImpl;
import com.galive.logic.dao.UserDao;
import com.galive.logic.dao.UserDaoImpl;
import com.galive.logic.exception.LogicException;
import com.galive.logic.helper.LogicHelper;
import com.galive.logic.model.User;
import com.galive.logic.model.User.UserGender;
import com.galive.logic.model.User.UserOnlineState;
import com.galive.logic.network.socket.ChannelManager;

public class UserServiceImpl extends BaseService implements UserService {

	
	public UserServiceImpl() {
		super();
		appendLog("UserServiceImpl");
	}
	
	private UserDao userDao = new UserDaoImpl();
	private UserCache userCache = new UserCacheImpl();

	@Override
	public User register(String username, String password, String nickname, String avatar, UserGender gender, String profile) throws LogicException {
		User u = userDao.findByUsername(username);
		if (u != null) {
			String error = "用户名" + username + "已存在。";
			appendLog(error);
			throw new LogicException(error);
		}
		u = new User();
		if (StringUtils.isBlank(username)) {
			String error = "用户名不能为空。";
			appendLog(error);
			throw new LogicException(error);
		}
		if (StringUtils.isBlank(password)) {
			String error = "密码不能为空。";
			appendLog(error);
			throw new LogicException(error);
		}
		if (StringUtils.isBlank(nickname)) {
			String error = "昵称不能为空。";
			appendLog(error);
			throw new LogicException(error);
		}
		int usernameLen = StringUtils.length(username);
		if (usernameLen < 6 || usernameLen > 20) {
			String error = "用户名必须为6~20位。";
			appendLog(error);
			throw new LogicException(error);
		}
		int passwordLen = StringUtils.length(password);
		if (passwordLen < 6 || passwordLen > 20) {
			String error = "密码必须为6~20位。";
			appendLog(error);
			throw new LogicException(error);
		}
		int nicknameLen = StringUtils.length(nickname);
		if (nicknameLen > 20) {
			String error = "昵称过长，最大为20位。";
			appendLog(error);
			throw new LogicException(error);
		}
		String delimiter = ApplicationConfig.getInstance().getSocketConfig().getParamsDelimiter();
		if (StringUtils.contains(username, delimiter)) {
			String error = "用户名" + username + "包含特殊字符:" + delimiter;
			appendLog(error);
			throw new LogicException(error);
		}
		if (StringUtils.isEmpty(avatar)) {
			String error = "头像为空";
			appendLog(error);
			throw new LogicException(error);
		}
		appendLog("保存用户");
		u.setUsername(username);
		u.setGender(gender);
		u.setPassword(password);
		u.setNickname(nickname);
		u.setAvatar(avatar);
		u.setProfile(profile);
		u = userDao.saveOrUpdate(u);
		
		appendLog("更新最后登录时间");
		userCache.updateLatestLogin(u.getSid());
		
		return u;
	}

	@Override
	public User login(String username, String password) throws LogicException {
		User u = userDao.findByUsername(username);
		if (u == null) {
			String error = "用户不存在";
			appendLog(error);
			throw new LogicException(error);
		} else {
			if (!u.getPassword().equals(password)) {
				String error = "密码错误";
				appendLog(error);
				throw new LogicException(error);
			}
		}
		userCache.updateLatestLogin(u.getSid());
		return u;
	}

	@Override
	public User findUserBySid(String userSid) throws LogicException {
		User u = userDao.find(userSid);
		if (u == null) {
			String error = "用户不存在。";
			appendLog(error);
			throw new LogicException(error);
		}
		return u;
	}

	@Override
	public List<User> listByLatestLogin(int index, int size) {
		List<User> users = new ArrayList<>();
		List<String> userSids = userCache.listByLatestLogin(index, index + size - 1);
		for (String sid : userSids) {
			User u = userDao.find(sid);
			if (u != null) {
				users.add(u);
			}
		}
		return users;
	}

	@Override
	public void updateDeviceToken(String userSid, String deviceToken) throws LogicException {
		// TODO 新逻辑
//		User u = userDao.find(userSid);
//		if (u == null) {
//			String error = "用户不存在";
//			appendLog(error);
//			throw new LogicException(error);
//		}
//		if (StringUtils.isBlank(deviceToken)) {
//			String error = "无效的deviceToken";
//			appendLog(error);
//			throw new LogicException(error);
//		}
		userCache.saveDeviceToken(userSid, deviceToken);
	}
	
	@Override
	public void deleteDeviceToken(String deviceToken) {
		userCache.deleteDeviceToken(deviceToken);
	}
	
	@Override
	public String findDeviceToken(String userSid) {
		return userCache.findDeviceToken(userSid);
	}

	@Override
	public boolean verifyToken(String userSid, String token) {
		String existToken = userCache.findUserToken(userSid);
		appendLog("exist token:" + existToken);
		appendLog("verify token:" + token);
		if (StringUtils.isBlank(existToken) || !existToken.equals(token)) {
			appendLog("token验证失败。");
			return false;
		}
		return true;
	}

	@Override
	public String createToken(String userSid) throws LogicException {
		String token = LogicHelper.generateRandomMd5();
		appendLog("生成token:" + token + " 绑定用户:" + userSid);
		userCache.saveUserToken(userSid, token);
		return token;
	}

	@Override
	public boolean isOnline(String userSid) {
		boolean online = ChannelManager.getInstance().getOnlineState(userSid) == UserOnlineState.Online;
		return online;
	}

	

	

}
