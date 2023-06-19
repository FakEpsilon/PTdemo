package com.example.demosecond.dao;


import com.example.demosecond.dao.entity.AnswerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface AnswerEntityMapper {
//    插入答案
    int insert(AnswerEntity answerEntity);
}
