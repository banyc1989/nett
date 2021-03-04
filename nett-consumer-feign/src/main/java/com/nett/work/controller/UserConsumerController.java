package com.nett.work.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nett.work.dto.User;
import com.nett.work.feignservice.ConsumerService;

@RestController
public class UserConsumerController {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	ConsumerService consumerService;

	@RequestMapping(value="/consumer/add")
	public boolean addUser(User user){
		Boolean flag = consumerService.add(user);
		return flag;
	}
	
	@RequestMapping(value="/consumer/get/{id}")
	public User get(@PathVariable("id") int id){
		User user = consumerService.get(id);
		return user;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/consumer/list")
	public List<User> getList(){
		List list = consumerService.getAll();
		return list;
	}
}