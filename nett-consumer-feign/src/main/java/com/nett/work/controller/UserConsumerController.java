package com.nett.work.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nett.work.Aop.Log;
import com.nett.work.dto.User;
import com.nett.work.feignservice.ConsumerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "用户管理")
public class UserConsumerController {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	ConsumerService consumerService;
	@Autowired
	ConsumerService consumerService2;

//	@RequestMapping(value="/consumer/add")
	@GetMapping("/consumer/add")
    @ApiOperation(value = "添加用戶", notes = "添加用戶")
	public boolean addUser(User user){
		Boolean flag = consumerService.add(user);
		return flag;
	}
	
	@GetMapping(value="/consumer/get/{id}")
	@Log(operateType="get查询:",operateExplain="查询用户数据")  //这里使用的自定义注解
	public User get(@PathVariable("id") int id){
		User user = consumerService.get(id);
		return user;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(value="/consumer/list")
	@ApiOperation(value = "查詢用戶列表", notes = "查詢用戶列表")
	public List<User> getList(){
		List list = consumerService.getAll();
		return list;
	}
}