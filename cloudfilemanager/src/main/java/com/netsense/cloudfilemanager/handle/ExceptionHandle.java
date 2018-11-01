package com.netsense.cloudfilemanager.handle;

import com.netsense.cloudfilemanager.enums.ResultEnum;
import com.netsense.cloudfilemanager.exception.RunException;
import com.netsense.cloudfilemanager.model.Result;
import com.netsense.cloudfilemanager.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if (e instanceof RunException){
            RunException runException = (RunException) e;
            return ResultUtil.error(runException.getCode(),runException.getMessage());
        }else {
            logger.error("系统异常: {}",e);
            return ResultUtil.error(ResultEnum.UNKNOW_ERROR);
        }
    }
}
