package com.baomidou.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.springmvc.model.system.User;
import com.baomidou.springmvc.service.system.IUserService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("entityWrapper")
@Controller
public class DemoEntityWrapper extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * 空参数构造方法
     */
    @RequestMapping("/construct_demo1")
    public void construct_demo1() {
        EntityWrapper entityWrapper = new EntityWrapper();
    }

    @RequestMapping("/getSqlSegment")
    public void getSqlSegment() {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("name", "张三");
     /*   String sqlSegment = entityWrapper.getSqlSegment();
        System.out.println(sqlSegment);*/

        List<User> userList = new User().selectList(entityWrapper);


        System.out.println(userList);
    }

    @RequestMapping("/where")
    public void where(User user) {
        EntityWrapper<User> entityWrapper = new EntityWrapper();
        entityWrapper.where("name={0}", "张三").where("age=15");
        entityWrapper.and("type={0}", 0).andNew("type={0}", 0);
        List<User> userList = userService.selectList(entityWrapper);
        System.out.println(userList);
    }

    /**
     * 参数是Map
     *
     * @param user:
     * @Description:
     * @Author: Forever丶诺
     * @Date: 14:38 2018/3/15
     */
    @RequestMapping("/allEq_demo2")
    public void allEq_demo2(User user) throws InvocationTargetException, IllegalAccessException {
        EntityWrapper<User> entityWrapper = new EntityWrapper();
        Map eqMap = new HashMap();
        eqMap.put("name", "张三");
        eqMap.put("age", 15);
        user.setName("张三");
        user.setAge(15);
        Map paramMap = JSON.parseObject(JSON.toJSONString(user), Map.class);
        entityWrapper.allEq(paramMap);
        List<User> userList = userService.selectList(entityWrapper);
        System.out.println(userList);
    }


    @RequestMapping("/noWhere")
    public void noWhere(User user) {
        EntityWrapper<User> entityWrapper = new EntityWrapper();
        List<User> userList = userService.selectList(entityWrapper);
        System.out.println(userList);
    }



}
