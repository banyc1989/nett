package com.nett.work.service;

import java.util.List;

import com.nett.work.dto.User;

public interface UserService {

	public boolean addUser(User user);

	public User getUser(int id);

	public List<User> getUsers();
}