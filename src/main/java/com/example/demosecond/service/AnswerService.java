package com.example.demosecond.service;

import com.example.demosecond.common.utils.UUIDUtil;
import com.example.demosecond.dao.AnswerEntityMapper;
import com.example.demosecond.dao.entity.AnswerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    @Autowired
    private AnswerEntityMapper answerEntityMapper;

    public int addAnswerInfo(AnswerEntity answerEntity){
        answerEntity.setId(UUIDUtil.getOneUUID());
        int answerResult = answerEntityMapper.insert(answerEntity);
        if(answerResult != 0){
            return 3;
        }else{
            return answerResult;
        }
    }
}
