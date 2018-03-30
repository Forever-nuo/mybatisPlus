package com.baomidou.springmvc.controller;

import com.baomidou.springmvc.mapper.system.UserMapper;
import com.baomidou.springmvc.service.system.IUserService;
import com.fasterxml.jackson.databind.MapperFeature;
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

    @RequestMapping("/method1")
    public void  method(){



    }
}
