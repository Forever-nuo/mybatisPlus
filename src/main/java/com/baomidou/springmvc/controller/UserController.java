package com.baomidou.springmvc.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.springmvc.model.enums.TypeEnum;
import com.baomidou.springmvc.model.system.User;
import com.baomidou.springmvc.service.system.IUserService;

import java.util.List;

/**
 * Author: D.Yang
 * Email: koyangslash@gmail.com
 * Date: 16/10/9
 * Time: 上午11:58
 * Describe: 用户控制器
 * <p>
 * 代码生成器，参考源码测试用例：
 * <p>
 * /mybatis-plus/src/test/java/com/baomidou/mybatisplus/test/generator/MysqlGenerator.java
 */
@Controller
public class UserController extends BaseController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        modelAndView.addObject("userList", userService.selectList(null));
        return modelAndView;
    }

    @RequestMapping("/preSave")
    public ModelAndView preSave(ModelAndView modelAndView, @RequestParam(value = "id", required = false) Long id) {
        modelAndView.setViewName("save");
        if (id != null) {
            modelAndView.addObject("user", userService.selectById(id));
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("save")
    public Object save(User user) {
        user.setType(TypeEnum.DISABLED);
        if (user.getId() == null) {
            return userService.insert(user) ? renderSuccess("添加成功") : renderError("添加失败");
        } else {
            return userService.updateById(user) ? renderSuccess("修改成功") : renderError("修改失败");
        }
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(@RequestParam(value = "id", required = false) Long id) {
        return userService.deleteById(id) ? renderSuccess("删除成功") : renderError("删除失败");
    }

    /**
     * 根据Id查询某条数据
     *
     * @param :
     * @Description:
     * @Author: Forever丶诺
     * @Date: 10:29 2018/3/15
     */
    @RequestMapping("/getById")
    public void getUserById() {
        Long id = new Long("974103358733910017");
        User user = userService.selectById(id);
        System.out.println(user);
    }

    /**
     * EntityWrapper对象的使用
     */

    @RequestMapping(value = "/getDataByCondition", method = RequestMethod.GET)
    public void getDataByCondition() {
        List<User> userList = userService.selectList(new EntityWrapper<User>().eq("name", "张三"));
        System.out.println(userList);
    }

    @RequestMapping(value = "/entityWrapper/", method = RequestMethod.GET)
    public void entityWrapper() {
        List<User> userList = userService.selectList(new EntityWrapper<User>().eq("name", "张三"));
        System.out.println(userList);
    }

    /**
     * 分页查询
     */
    @RequestMapping(value = "/page/demo1", method = RequestMethod.GET)
    public void page_demo1() {
        //参数1:是当前页,每页显示的个数
        Page<User> userList = userService.selectPage(new Page<User>(2, 2));
        System.out.println(userList);
    }

    /**
     * 分页查询
     */
    @RequestMapping(value = "/page/demo2", method = RequestMethod.GET)
    public void page_demo2() {
        //参数1:是当前页,每页显示的个数
        Page<User> userList = userService.selectPage(new Page<User>(2, 2, "id"), new EntityWrapper<User>());
        System.out.println(userList);
    }

    /**
     * 分页查询
     */
    @RequestMapping(value = "/page/demo3", method = RequestMethod.GET)
    public void page_demo3() {
        //参数1:是当前页,每页显示的个数
        Page<User> userList = userService.selectPage(new Page<User>(2, 2, "id"), new EntityWrapper<User>());
        System.out.println(userList);
    }
    @RequestMapping(value = "/page/demo4", method = RequestMethod.GET)
    public void page_demo4(Page<User> userPage) {
        userService.getUserList();
    }


    @RequestMapping(value = "/page/demo5", method = RequestMethod.GET)
    public void page_demo5(Page<User> userPage) {

        userService.getUserList();
    }


}
