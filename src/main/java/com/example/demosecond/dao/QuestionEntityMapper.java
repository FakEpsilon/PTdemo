package com.example.demosecond.dao;

import com.example.demosecond.dao.entity.QuestionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface QuestionEntityMapper {
//    创建问题信息
    int insert(QuestionEntity questionEntity);

//    根据问卷ID查询问题列表
    List<QuestionEntity> queryQuestionById(QuestionEntity questionEntity);
}
