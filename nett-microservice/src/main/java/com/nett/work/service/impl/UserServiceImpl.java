package com.nett.work.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nett.work.dao.UserDao;
import com.nett.work.dto.User;
import com.nett.work.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public boolean addUser(User user) {
		boolean flag;
		flag = userDao.addUser(user);
		return flag;
	}

	@Override
	public User getUser(int id) {
		User user = userDao.getUser(id);
		return user;
	}

	@Override
	public List<User> getUsers() {
		List<User> users = userDao.getUsers();
		return users;
	}

}
