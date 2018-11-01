package com.netsense.cloudfilemanager.model;

/**
 * @Auther: rui
 * @Date: 2018/9/14 19:47
 * @Description:
 */

import lombok.Data;

/**
 * 分页bean
 */

@Data
public class PageBean {
    // 当前页
    private Integer currentPage;
    // 每页显示的总条数
    private Integer pageSize;

}
