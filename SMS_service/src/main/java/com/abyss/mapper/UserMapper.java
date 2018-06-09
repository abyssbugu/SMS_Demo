package com.abyss.mapper;

import com.abyss.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Abyss on 2018/5/30.
 * description:
 */
public interface UserMapper {
    User queryUserById(Long id);
    int addUser(@Param("user") User user);
    Long queryUserCount();
    List<User> queryUserList(@Param("pageNum")Integer pageNum, @Param("pageSize") Integer pageSize);
    int deleteUserByIds(@Param("ids") Long[] ids);


     List<User> queryUserAll();
}
