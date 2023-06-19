package com.example.demosecond.service;

import com.example.demosecond.common.utils.UUIDUtil;
import com.example.demosecond.dao.QuestionEntityMapper;
import com.example.demosecond.dao.entity.QuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuestionService {
    @Autowired
    private QuestionEntityMapper questionEntityMapper;

    public int addQuestionInfo(QuestionEntity questionEntity){
        questionEntity.setId(UUIDUtil.getOneUUID());
        int questionResult = questionEntityMapper.insert(questionEntity);
        if(questionResult != 0){
            return 3;
        }else{
            return questionResult;
        }
    }

//    根据问卷ID查询问题列表
    public List<QuestionEntity> queryQuestionById(String id){
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuestionnaireId(id);
        List<QuestionEntity> result = questionEntityMapper.queryQuestionById(questionEntity);
        return result;
    }
}
