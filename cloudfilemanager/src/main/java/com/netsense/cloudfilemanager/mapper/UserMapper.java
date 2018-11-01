package com.netsense.cloudfilemanager.mapper;


import com.netsense.cloudfilemanager.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByusercode(String usercode);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}