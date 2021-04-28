package com.ntt.work;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nett.work.NettworkApplication;
import com.nett.work.dao.UserDao;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {NettworkApplication.class})
class NttworkApplicationTests {
	@Autowired
	UserDao userdao;

    @Test
    void contextLoads() {
    	
    	System.out.println(userdao.getUser(1).toString());
    }

}
