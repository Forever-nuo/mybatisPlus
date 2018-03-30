package com.baomidou.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.springmvc.mapper.UserMapper;
import com.baomidou.springmvc.model.system.User;
import com.baomidou.springmvc.service.system.IUserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("entityWrapper")
@Controller
public class DemoEntityWrapper extends BaseController {

    @Autowired
    private IUserService userService;


    @Autowired
    private UserMapper userMapper;

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


    /**
     * 拼接sql语句2
     */
    @RequestMapping("/testCondition1")
    @ResponseBody
    public int testCondition1() {
        Wrapper<User> wrap = Condition.create().setSqlSelect("count(1) num").eq("type", 0);
        int count = userService.selectCount(wrap);
        System.out.println(count);
        Condition condition = Condition.create();
        return count;
    }


    /**
     * 查询全部(全部字段)
     */
    @RequestMapping("/selectAll")
    public void selectAll() {
        List<User> users = userService.selectList(new EntityWrapper<User>());
        System.out.println(users);
    }


    /**
     * 查询部分字段
     */
    @RequestMapping("/selectAll_someColumn")
    public void selectAll_someColumn() {
        EntityWrapper<User> ew = new EntityWrapper<>();
        ew.setSqlSelect("name","type");
        List<User> userList = userService.selectList(ew);
    }


    /**
     * 使用map映射结果集
     */
    @RequestMapping("/selectAllMap")
    public  void selectAllMap() {
        EntityWrapper<User> ew = new EntityWrapper<>();
        ew.setSqlSelect("name","ctime");
        List<Map<String, Object>> maps = userService.selectMaps(ew);
    }

    @RequestMapping("/selectAllObj")
    public  void selectAllObj() {
        EntityWrapper<User> ew = new EntityWrapper<>();
        ew.setSqlSelect("name","ctime");
        List<Object> objects = userService.selectObjs(ew);
        //返回了一个对象
    }


    @RequestMapping("/selectAllObj1")
    public  void selectAllObj1() {
        EntityWrapper<User> ew = new EntityWrapper<>();
        ew.setSqlSelect("name","ctime");
        //返回了一个对象
    }



    @RequestMapping("/selectMyPage")
    public  void selectMyPage() {
        userMapper.selectMyPage(new RowBounds(10,15),new EntityWrapper<User>());
        //返回了一个对象
    }
}
