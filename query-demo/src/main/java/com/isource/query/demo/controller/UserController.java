package com.isource.query.demo.controller;

import com.isource.query.Query;
import com.isource.query.demo.entity.User;
import com.isource.query.demo.service.UserService;
import com.isource.query.entity.DataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("findUser")
    public DataSet<User> findUser(String key){
        Query query = new Query();
        // 拼装查询条件
        query.where().field("account").like(key);
        return userService.findList(query);
    }
}
