package com.nett.work.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nett.work.dto.User;

@RestController
public class UserConsumerController {
	private static String REST_URL_PREFIX = "http://localhost:8002";

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/add")
	public boolean addUser(User user) {
		String a= "aaa";
		Boolean flag = restTemplate.postForObject(REST_URL_PREFIX + "/add", user, Boolean.class);
		return flag;
	}

	@RequestMapping(value = "/get/{id}")
	public User get(@PathVariable("id") int id) {
		User user = restTemplate.getForObject(REST_URL_PREFIX + "/get/"+id, User.class);
		return user;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/list")
	public List<User> getList() {
		List list = restTemplate.getForObject(REST_URL_PREFIX + "/getUser/list", List.class);
		return list;
	}
}