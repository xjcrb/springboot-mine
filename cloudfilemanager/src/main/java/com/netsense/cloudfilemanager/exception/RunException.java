package com.netsense.cloudfilemanager.exception;

import com.netsense.cloudfilemanager.enums.ResultEnum;

public class RunException extends RuntimeException {

    private Integer code;

    public RunException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
