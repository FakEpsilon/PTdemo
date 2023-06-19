package com.example.demosecond.dao;

import com.example.demosecond.dao.entity.QuestionnaireEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface QuestionnaireEntityMapper {
    /* 全查问卷列表 */
    List<QuestionnaireEntity> queryQuestionnaireList(QuestionnaireEntity questionnaireEntity);

    /* 根据项目ID查询问卷信息*/
    List<QuestionnaireEntity> selectQuestionnaireById(QuestionnaireEntity questionnaireEntity);

    /* 根据问卷ID查询问卷信息*/
    List<QuestionnaireEntity> selectQuestionnaireInfo(QuestionnaireEntity questionnaireEntity);

    /* 创建问卷信息 */
    int insert(QuestionnaireEntity questionnaireEntity);

    /* 根据id删除问卷信息 */
    int deleteQuestionnaireById(QuestionnaireEntity questionnaireEntity);

    /* 发布问卷信息 */
    int updateByPrimaryKeySelective(QuestionnaireEntity questionnaireEntity);

    /* 发布问卷信息 */
    int issueQuestionnaireById(QuestionnaireEntity questionnaireEntity);

    /*关闭问卷*/
    int closeQuestionnaireById(QuestionnaireEntity questionnaireEntity);
}
