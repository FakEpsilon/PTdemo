package com.example.demosecond.controller;

import com.example.demosecond.beans.HttpResponseEntity;
import com.example.demosecond.dao.entity.AnswerEntity;
import com.example.demosecond.dao.entity.OptionEntity;
import com.example.demosecond.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "https://q.luckyf.cc")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

//    插入答案
    @RequestMapping(value = "/addAnswerInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity addAnswerInfo(@RequestBody AnswerEntity answerEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try{
            int result = answerService.addAnswerInfo(answerEntity);
            if(result != 0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("答案插入成功");
            }else{
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("答案插入失败");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }
}
