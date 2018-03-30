package com.baomidou.springmvc.mapper;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.springmvc.common.SuperMapper;
import com.baomidou.springmvc.model.system.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * User 表数据库控制层接口
 */
public interface UserMapper extends SuperMapper<User> {


    List<User> selectUserList(Pagination pagination, Integer age);



    List<User> selectMyPage(RowBounds rowBounds, @Param("ew") Wrapper<User> wrapper);

    int deleteUserById(Integer id);

}
