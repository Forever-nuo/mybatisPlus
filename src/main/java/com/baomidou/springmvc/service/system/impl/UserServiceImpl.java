package com.baomidou.springmvc.service.system.impl;

import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.springmvc.mapper.UserMapper;
import com.baomidou.springmvc.model.system.User;
import com.baomidou.springmvc.service.system.IUserService;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private  UserMapper userMapper;

    @Override
    public void getUserList(){
        Page<User> pageUser = new Page<>(2,2);
        Page<User> userPage = pageUser.setRecords(userMapper.selectUserList(pageUser, 15));
        System.out.println(userPage);


    }

}