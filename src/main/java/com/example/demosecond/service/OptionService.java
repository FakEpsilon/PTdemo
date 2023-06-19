package com.example.demosecond.service;

import com.example.demosecond.common.utils.UUIDUtil;
import com.example.demosecond.dao.OptionEntityMapper;
import com.example.demosecond.dao.entity.OptionEntity;
import com.example.demosecond.dao.entity.QuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService {
    @Autowired
    private OptionEntityMapper optionEntityMapper;

    public int addOptionInfo(OptionEntity optionEntity){
        optionEntity.setId(UUIDUtil.getOneUUID());
        int optionResult = optionEntityMapper.insert(optionEntity);
        if(optionResult != 0){
            return 3;
        }else{
            return optionResult;
        }
    }

    //    根据问题ID查询选项列表
    public List<OptionEntity> queryOptionById(OptionEntity optionEntity){
        List<OptionEntity> result = optionEntityMapper.queryOptionById(optionEntity);
        return result;
    }
}
