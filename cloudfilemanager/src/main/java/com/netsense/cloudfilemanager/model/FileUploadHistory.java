package com.netsense.cloudfilemanager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class FileUploadHistory {
    private String token;

    private String filename;

    private String filepath;

    private String md5;

    private Integer length;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updatetime;

    private String userid;

    private String usercode;

    private String username;

    private Integer flag;
}