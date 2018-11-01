package com.netsense.cloudfilemanager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Power {
    private Integer id;

    private Integer day;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updatetime;

    private Integer updatetype;

}