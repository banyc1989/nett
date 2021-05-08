package com.nett.work.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nett.work.common.Master;
import com.nett.work.dao.UserDao;
import com.nett.work.dto.User;
import com.nett.work.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Transactional
	@Override
	public int insert(User user) {
		return userDao.insert(user);
	}

//	@Master
	@Override
	public int save(User user) {
		return userDao.insert(user);
	}

	@Master
	@Override
	public List<User> selectAll() {
		return userDao.queryAll();
	}

	@Master
	@Override
	public String getToken(String appId) {
		// 有些读操作必须读主数据库
		// 比如，获取微信access_token，因为高峰时期主从同步可能延迟
		// 这种情况下就必须强制从主数据读
		return null;
	}

}
