package com.example.demosecond.service;


import com.example.demosecond.common.utils.UUIDUtil;
import com.example.demosecond.dao.QuestionnaireEntityMapper;
import com.example.demosecond.dao.entity.QuestionnaireEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class QuestionnaireService {
    @Autowired
    private QuestionnaireEntityMapper questionnaireEntityMapper;

//全查问卷列表
    @Cacheable(value = "QuestionnaireList")
    public List<QuestionnaireEntity> queryQuestionnaireList(QuestionnaireEntity questionnaireEntity){
        List<QuestionnaireEntity> result = questionnaireEntityMapper.queryQuestionnaireList(questionnaireEntity);
        return result;
    }
//    根据项目ID查询
    @Transactional
    @CachePut(value = "QuestionnaireViaProjectId", key = "#questionnaireEntity.projectId")
    public List<QuestionnaireEntity> selectQuestionnaireById(QuestionnaireEntity questionnaireEntity){
        List<QuestionnaireEntity> result = questionnaireEntityMapper.selectQuestionnaireById(questionnaireEntity);
        return result;
    }

    //    根据问卷ID查询问卷
    @Transactional
    @Cacheable(value = "QuestionnaireViaId", key = "#questionnaireEntity.id")
    public List<QuestionnaireEntity> selectQuestionnaireInfo(QuestionnaireEntity questionnaireEntity){
        List<QuestionnaireEntity> result = questionnaireEntityMapper.selectQuestionnaireInfo(questionnaireEntity);
        return result;
    }
//    创建问卷
    public int addQuestionnaireInfo(QuestionnaireEntity questionnaireEntity){
        questionnaireEntity.setId(UUIDUtil.getOneUUID());
        int questionnaireResult = questionnaireEntityMapper.insert(questionnaireEntity);
        if(questionnaireResult != 0){
            return 3;
        }else {
            return questionnaireResult;
        }
    }
//    根据ID删除
    public int deleteQuestionnaireById(QuestionnaireEntity questionnaireEntity){
        int questionnaireResult = questionnaireEntityMapper.deleteQuestionnaireById(questionnaireEntity);
        return questionnaireResult;
    }
//    更新
    public int modifyQuestionnaireInfo(QuestionnaireEntity questionnaireEntity){
        int questionnaireResult = questionnaireEntityMapper.updateByPrimaryKeySelective(questionnaireEntity);
        return questionnaireResult;
    }
//    发布
    //@CacheEvict(value = "QuestionnaireViaId", key = "#questionnaireEntity.id")
    public int issueQuestionnaireById(QuestionnaireEntity questionnaireEntity){
        int questionnaireResult = questionnaireEntityMapper.issueQuestionnaireById(questionnaireEntity);
        return questionnaireResult;
    }
//    关闭
    //@CacheEvict(value = "QuestionnaireViaId", key = "#questionnaireEntity.id")
    public int closeQuestionnaireById(QuestionnaireEntity questionnaireEntity){
        int questionnaireResult = questionnaireEntityMapper.closeQuestionnaireById(questionnaireEntity);
        return questionnaireResult;
    }
}
