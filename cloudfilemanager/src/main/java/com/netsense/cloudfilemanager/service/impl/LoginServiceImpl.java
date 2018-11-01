package com.netsense.cloudfilemanager.service.impl;

import com.netsense.cloudfilemanager.enums.ResultEnum;
import com.netsense.cloudfilemanager.exception.RunException;
import com.netsense.cloudfilemanager.mapper.UserMapper;
import com.netsense.cloudfilemanager.model.Result;
import com.netsense.cloudfilemanager.model.User;
import com.netsense.cloudfilemanager.service.LoginService;
import com.netsense.cloudfilemanager.utils.EncriptUtils;
import com.netsense.cloudfilemanager.utils.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @Auther: rui
 * @Date: 2018/9/13 20:56
 * @Description:
 */

@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Result login(User user) throws Exception {
        if (user != null) {
            User user1 = userMapper.selectByusercode(user.getUsername());
            if (user1 != null) {
                if (EncriptUtils.authenticatePassword(user1.getPassword(), user.getPassword())) {
                    return ResultUtil.success(ResultEnum.SUCCESS);
                } else {
                    return ResultUtil.success(ResultEnum.authfaild);
                }
            } else {
                return ResultUtil.success(ResultEnum.authfaild);
            }
        } else {
            return ResultUtil.success(ResultEnum.inputnull);
        }
    }

    @Override
    public Result repassword(Map<String,Object> params) throws Exception {
        String username = (String) params.get("username");
        String password = (String) params.get("password");
        String oldpassword = (String) params.get("oldpassword");

        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password) && StringUtils.isNotBlank(oldpassword)) {
            User user = userMapper.selectByusercode(username);
            if (user != null) {
                if (EncriptUtils.authenticatePassword(user.getPassword(), password)) {
                    return ResultUtil.success(ResultEnum.PASSWORDREPEAT);
                } else {
                    user.setCreatdate(new Date());
                    user.setPassword(EncriptUtils.encodeByMD5(password));
                    int result = userMapper.updateByPrimaryKeySelective(user);
                    if (result == 1) {
                        return ResultUtil.success(ResultEnum.SUCCESS);
                    } else {
                        return ResultUtil.success(ResultEnum.FAILD);
                    }
                }
            }
        }
        return ResultUtil.success(ResultEnum.FAILD);
    }
}
