package com.ntt.work;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nett.work.NettworkApplication;
import com.nett.work.dto.User;
import com.nett.work.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NettworkApplication.class)
public class NettworkApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	public void testWrite() {
		User user = new User();
		user.setUid(19);
		user.setUserName("zhangsan");
		userService.insert(user);
	}

	@Test
	public void testRead() {
		for (int i = 0; i < 4; i++) {
			userService.selectAll();
		}
	}

	@Test
	public void testSave() {
		User user = new User();
		user.setUserName("wangwu");
		user.setUid(20);
		userService.save(user);
	}
//
//	@Test
//	public void testReadFromMaster() {
//		userService.getToken("1234");
//	}

}