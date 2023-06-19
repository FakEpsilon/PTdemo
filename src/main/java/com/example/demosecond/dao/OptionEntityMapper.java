package com.example.demosecond.dao;

import com.example.demosecond.dao.entity.OptionEntity;
import com.example.demosecond.dao.entity.QuestionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface OptionEntityMapper {
//    创建选项
    int insert(OptionEntity optionEntity);

    //    根据问题ID查询选项列表
    List<OptionEntity> queryOptionById(OptionEntity optionEntity);
}
