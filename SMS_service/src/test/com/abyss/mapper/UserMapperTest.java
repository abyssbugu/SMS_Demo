package com.abyss.mapper;

import com.abyss.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by Abyss on 2018/5/30.
 * description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class UserMapperTest {


    @Autowired
    private UserMapper userMapper;

    @Test
    public void queryUserById() {
        User user = userMapper.queryUserById(1L);
        System.out.println(user);
    }

//    @Test
//    public void addUser() {
//        User user = new User();
//        user.setUserName("eee");
//        user.setName("eee");
//        user.setAge(10);
//        user.setSex(1);
//        user.setPassword("123456");
//        user.setBirthday(new Date());
//        userMapper.addUser(user);
//    }

    @Test
    public void queryUserCount() {
        Long aLong = userMapper.queryUserCount();
        System.out.println("数量:" + aLong);
    }

    @Test
    public void queryUserList() {
        List<User> users = userMapper.queryUserList(0, 5);
        System.out.println(users);
    }
}