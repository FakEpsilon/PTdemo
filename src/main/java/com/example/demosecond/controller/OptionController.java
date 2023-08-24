package com.example.demosecond.controller;

import com.example.demosecond.beans.HttpResponseEntity;
import com.example.demosecond.dao.entity.OptionEntity;
import com.example.demosecond.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://q.luckyf.cc")
public class OptionController {
    @Autowired
    private OptionService optionService;

//    创建选项选项
    @RequestMapping(value = "/addOptionInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity addOptionInfo(@RequestBody OptionEntity optionEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try{
            int result = optionService.addOptionInfo(optionEntity);
            if(result != 0){
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("创建成功");
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

//      根据问题ID查询选项信息
    @Cacheable(value = "optionViaQuestion")
    @RequestMapping(value = "/queryOptionById", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryOptionById(@RequestBody OptionEntity optionEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try{
            List<OptionEntity> hasOption = optionService.queryOptionById(optionEntity);
            if(CollectionUtils.isEmpty(hasOption)){
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(hasOption.get(0));
                httpResponseEntity.setMessage("无选项信息");
            }else{
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasOption);
                httpResponseEntity.setMessage("查询成功");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }
}
