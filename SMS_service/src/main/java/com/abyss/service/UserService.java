package com.abyss.service;

import com.abyss.pojo.EasyUIResult;
import com.abyss.pojo.User;

/**
 * Created by Abyss on 2018/5/30.
 * description:
 */
public interface UserService {

    EasyUIResult queryUserList(Integer page, Integer rows);

    void addUsers(User user1, User user2);

    Boolean addUser(User user);

    Boolean deleteByIds(Long[] ids);

    EasyUIResult queryEasyUIResult(Integer page, Integer rows);
}
