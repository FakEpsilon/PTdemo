package com.example.demosecond.controller;

import com.example.demosecond.beans.HttpResponseEntity;
import com.example.demosecond.dao.entity.QuestionEntity;
import com.example.demosecond.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class QuestionController<model> {
    @Autowired
    private QuestionService questionService;

//    创建问题信息
    @RequestMapping(value = "/addQuestionInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity addQuestionInfo(@RequestBody QuestionEntity questionEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try{
            int result = questionService.addQuestionInfo(questionEntity);
            if(result != 0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("创建成功");
                httpResponseEntity.setID(questionEntity.getId());
            }else{
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("创建失败");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

//    根据问卷ID查询问题列表
    @Cacheable(value = "answerSheetViaQuestionnaire")
    @RequestMapping(value = "/answerSheet", method = RequestMethod.GET, headers = "Accept=application/json")
    public HttpResponseEntity queryQuestionById(@RequestParam("id") String id){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try{
            List<QuestionEntity> hasQuestion = questionService.queryQuestionById(id);
            if(CollectionUtils.isEmpty(hasQuestion)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(hasQuestion.get(0));
                httpResponseEntity.setMessage("无项目信息");
            }else{
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasQuestion);
                httpResponseEntity.setMessage("查询成功");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }
}
