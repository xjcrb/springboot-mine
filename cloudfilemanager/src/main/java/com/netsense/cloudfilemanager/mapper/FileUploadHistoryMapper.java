package com.netsense.cloudfilemanager.mapper;


import com.netsense.cloudfilemanager.model.FileUploadHistory;
import com.netsense.cloudfilemanager.model.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
public interface FileUploadHistoryMapper {
    int insert(FileUploadHistory record);

    int insertSelective(FileUploadHistory record);

    List<FileUploadHistory> selectAll();

    int deleteByPrimaryKey(String token);

    int updateByPrimaryKeySelective(FileUploadHistory fileUploadHistory);

    List<FileUploadHistory> search(@Param("starttime")Date starttime,@Param("endtime") Date endtime,@Param("usercode") String usercode);

    List<FileUploadHistory> getlastfewday(@Param("starttime")Date starttime);
}