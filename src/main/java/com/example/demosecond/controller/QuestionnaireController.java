package com.example.demosecond.controller;

import com.example.demosecond.beans.HttpResponseEntity;
import com.example.demosecond.common.utils.UUIDUtil;
import com.example.demosecond.dao.entity.QuestionnaireEntity;
import com.example.demosecond.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://q.luckyf.cc")
//@RequestMapping("/admin")
public class QuestionnaireController {
    @Autowired
    private QuestionnaireService questionnaireService;

    //    全查问卷列表
    @RequestMapping(value = "/queryQuestionnaireList", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryQuestionnaireList(@RequestBody QuestionnaireEntity questionnaireEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try{
            List<QuestionnaireEntity> hasQuestionnaire = questionnaireService.queryQuestionnaireList(questionnaireEntity);
            if(CollectionUtils.isEmpty(hasQuestionnaire)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(hasQuestionnaire.get(0));
                httpResponseEntity.setMessage("无问卷信息");
            }else{
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasQuestionnaire);
                httpResponseEntity.setMessage("查询成功");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }
    //    根据项目ID查询问卷
    @RequestMapping(value = "/selectQuestionnaireById", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity selectQuestionnaireById(@RequestBody QuestionnaireEntity questionnaireEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try{
            List<QuestionnaireEntity> hasQuestionnaire = questionnaireService.selectQuestionnaireById(questionnaireEntity);
            if(CollectionUtils.isEmpty(hasQuestionnaire)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setMessage("无问卷信息");
            }else{
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasQuestionnaire);
                httpResponseEntity.setMessage("查询成功");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }
    //    根据问卷ID查询问卷
    @RequestMapping(value = "/selectQuestionnaireInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity selectQuestionnaireInfo(@RequestBody QuestionnaireEntity questionnaireEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try{
            List<QuestionnaireEntity> hasQuestionnaire = questionnaireService.selectQuestionnaireInfo(questionnaireEntity);
            if(CollectionUtils.isEmpty(hasQuestionnaire)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(hasQuestionnaire.get(0));
                httpResponseEntity.setMessage("无问卷信息");
            }else{
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasQuestionnaire);
                httpResponseEntity.setMessage("查询成功");
                httpResponseEntity.setID(questionnaireEntity.getId());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }
    //    创建问卷
    @RequestMapping(value = "/addQuestionnaireInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity addQuestionnaireInfo(@RequestBody QuestionnaireEntity questionnaireEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try{
            int result = questionnaireService.addQuestionnaireInfo(questionnaireEntity);
            if(result != 0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("创建成功");
                httpResponseEntity.setID(questionnaireEntity.getId());
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
    //    根据ID删除
    @RequestMapping(value = "/deleteQuestionnaireById", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity deleteQuestionnaireById(@RequestBody QuestionnaireEntity questionnaireEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try{
            int result = questionnaireService.deleteQuestionnaireById(questionnaireEntity);
            if(result != 0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("删除成功");
            }else{
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("删除失败");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }
    //    更新
    @RequestMapping(value = "/modifyQuestionnaireInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity modifyQuestionnaireInfo(@RequestBody QuestionnaireEntity questionnaireEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try{
            int result = questionnaireService.modifyQuestionnaireInfo(questionnaireEntity);
            if(result != 0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("更新成功");
            }else{
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("更新失败");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }
    //    发布

    @RequestMapping(value = "/issueQuestionnaireById", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity issueQuestionnaireById(@RequestBody QuestionnaireEntity questionnaireEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try{
            int result = questionnaireService.issueQuestionnaireById(questionnaireEntity);
            if(result != 0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("发布成功");
            }else{
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("发布失败");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }
    //    关闭
    //@CacheEvict(value = "QuestionnaireViaProject"/*, key = "#questionnaireEntity.id"*/)
    @RequestMapping(value = "/closeQuestionnaireById", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity closeQuestionnaireById(@RequestBody QuestionnaireEntity questionnaireEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try{
            int result = questionnaireService.closeQuestionnaireById(questionnaireEntity);
            if(result != 0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("发布成功");
            }else{
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("发布失败");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }
}
