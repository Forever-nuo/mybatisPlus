package com.baomidou.springmvc.service.system;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.springmvc.model.system.User;

import java.util.List;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface IUserService extends IService<User> {

    void getUserList();
}