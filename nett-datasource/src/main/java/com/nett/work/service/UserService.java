package com.nett.work.service;

import java.util.List;

import com.nett.work.dto.User;

public interface UserService {

	public int insert(User user);

	public int save(User user);

	public List<User> selectAll();

	public String getToken(String appId);
}
