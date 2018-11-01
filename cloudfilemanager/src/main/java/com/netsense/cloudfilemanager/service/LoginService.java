package com.netsense.cloudfilemanager.service;

import com.netsense.cloudfilemanager.model.Result;
import com.netsense.cloudfilemanager.model.User;

import java.util.Map;

/**
 * @Auther: rui
 * @Date: 2018/9/13 20:55
 * @Description:
 */
public interface LoginService {
    Result login(User user) throws Exception;
    Result repassword(Map<String,Object> params) throws Exception;
}
