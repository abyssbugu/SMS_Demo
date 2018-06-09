package com.abyss.service.impl;

import com.abyss.mapper.UserMapper;
import com.abyss.pojo.EasyUIResult;
import com.abyss.pojo.User;
import com.abyss.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Abyss on 2018/5/30.
 * description:
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public EasyUIResult queryUserList(Integer page, Integer rows) {
        List<User> users = userMapper.queryUserList((page - 1) * rows, rows);
        Long count = userMapper.queryUserCount();
        EasyUIResult easyUIResult = new EasyUIResult();
        easyUIResult.setRows(users);
        easyUIResult.setTotal(count);
        return easyUIResult;
    }

    @Override
    public void addUsers(User user1, User user2) {
        userMapper.addUser(user1);
        int i = 5 / 0;
        userMapper.addUser(user2);
    }

    @Override
    public Boolean addUser(User user) {
        return userMapper.addUser(user) == 1;
    }

    @Override
    public Boolean deleteByIds(Long[] ids) {
        int i = userMapper.deleteUserByIds(ids);
        return i>0;
    }

    @Override
    public EasyUIResult queryEasyUIResult(Integer page, Integer rows) {

        //调用分页插件提供的静态方法,page:页码，rows:页面大小
        PageHelper.startPage(page, rows);
        //查询所有用户信息
        List<User> list = userMapper.queryUserAll();
        //利用分页插件的方法获取总记录数
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        //封装EasyUIResult
        EasyUIResult result = new EasyUIResult();
        result.setTotal(pageInfo.getTotal());//从pageInfo中获取总记录数并封装
        result.setRows(pageInfo.getList());//从pageInfo中获取分页数据并封装
        return result;
    }
}
