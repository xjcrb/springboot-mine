package com.netsense.cloudfilemanager.controller;

import com.netsense.cloudfilemanager.model.Result;
import com.netsense.cloudfilemanager.model.User;
import com.netsense.cloudfilemanager.service.LoginService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class LoginController {

    @Resource
    private LoginService loginService ;


    @PostMapping(value = "/login")
    public Result login(@RequestBody(required = false) User user) throws Exception {
        return loginService.login(user);
    }

    @PostMapping(value = "repassword")
    public Result repassword(@RequestBody(required = false) Map<String,Object> params) throws Exception{
        return loginService.repassword(params);
    }
}
