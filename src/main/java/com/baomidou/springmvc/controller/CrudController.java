package com.baomidou.springmvc.controller;

import com.baomidou.springmvc.model.system.User;
import com.baomidou.springmvc.service.system.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("crud")
public class CrudController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/list")
    public List<User> getAllUser() {
        return userService.selectList(null);
    }

}
