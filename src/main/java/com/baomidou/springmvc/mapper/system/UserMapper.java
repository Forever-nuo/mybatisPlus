package com.baomidou.springmvc.mapper.system;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.springmvc.common.SuperMapper;
import com.baomidou.springmvc.model.system.User;

import java.util.List;

/**
 * User 表数据库控制层接口
 */
public interface UserMapper extends SuperMapper<User> {


    List<User> selectUserList(Pagination pagination, Integer age);

    int deleteUserById(Integer id);

}
