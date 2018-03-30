package com.baomidou.springmvc.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springmvc.mapper.UserMapper;
import com.baomidou.springmvc.service.system.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("page")
public class PageController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper userMapper;


    /**
     * 翻页查询
     */
    @RequestMapping("/method1")
    public void  method(){
        Page page = new Page();


    }
}
