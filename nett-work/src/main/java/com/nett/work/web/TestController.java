package com.nett.work.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nett.work.common.annotation.ResponseBodyEncrypt;
import com.nett.work.dto.User;
import com.nett.work.service.UserService;
 
/**
 * @author lzh
 * create 2019-09-18-22:36
 */
@RestController
public class TestController {
 
    @Autowired
    private UserService userService;
 
    @RequestMapping("/queryAll")
    public List<User> queryAll(){
        List<User> lists = userService.queryAll();
        return lists;
    }
 
    @RequestMapping("/findUserById")
    public Map<String, Object> findUserById(@RequestParam int id){
        User user = userService.findUserById(id);
        Map<String, Object> result = new HashMap<>();
        result.put("uid", user.getUid());
        result.put("uname", user.getUserName());
        result.put("pass", user.getPassWord());
        result.put("salary", user.getSalary());
        result.put("data", user.getSalary()+user.getUserName());
        return result;
    }
    @RequestMapping("/findUserById2")
    @ResponseBodyEncrypt
    public Map<String, Object> findUserById2(@RequestParam int id){
        User user = userService.findUserById(id);
        Map<String, Object> result = new HashMap<>();
        result.put("uid", user.getUid());
        result.put("uname", user.getUserName());
        result.put("pass", user.getPassWord());
        result.put("salary", user.getSalary());
        result.put("data", user.getSalary()+user.getUserName());
        Map<String, Object> resultall = new HashMap<>();
        resultall.put("response", result);
        return resultall;
    }
    @RequestMapping("/findUserById3")
    public Map<String, Object> findUserById3(@RequestParam int id){
        User user = userService.findUserById2(id+"");
        Map<String, Object> result = new HashMap<>();
        result.put("uid", user.getUid());
        result.put("uname", user.getUserName());
        result.put("pass", user.getPassWord());
        result.put("salary", user.getSalary());
        result.put("data", user.getSalary()+user.getUserName());
        return result;
    }
 
    @RequestMapping("/updateUser")
    public String updateUser(){
        User user = new User();
        user.setUid(1);
        user.setUserName("cat");
        user.setPassWord("miaomiao");
        user.setSalary(4000);
 
        int result = userService.updateUser(user);
 
        if(result != 0){
            return "update user success";
        }
 
        return "fail";
    }
 
    @RequestMapping("/deleteUserById")
    public String deleteUserById(@RequestParam int id){
        int result = userService.deleteUserById(id);
        if(result != 0){
            return "delete success";
        }
        return "delete fail";
    }
}