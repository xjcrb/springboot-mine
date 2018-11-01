package com.netsense.cloudfilemanager.mapper;


import com.netsense.cloudfilemanager.model.Power;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PowerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Power record);

    int insertSelective(Power record);

    Power selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Power record);

    int updateByPrimaryKey(Power record);
}