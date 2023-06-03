package com.example.demosecond.service;

import com.example.demosecond.common.utils.UUIDUtil;
import com.example.demosecond.dao.entity.ProjectEntity;
import com.example.demosecond.dao.ProjectEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectEntityMapper projectEntityMapper;

    /* 全查项目列表 */
    public List<ProjectEntity> queryProjectList(ProjectEntity projectEntity){
        List<ProjectEntity> result = projectEntityMapper.queryProjectList(projectEntity);
        return result;
    }

    /* 根据名字查项目 */
    public List<ProjectEntity> selectProjectInfo(ProjectEntity projectEntity){
        List<ProjectEntity> result = projectEntityMapper.selectProjectInfo(projectEntity);
        return result;
    }

    /* 根据ID查项目 */
    public List<ProjectEntity> selectProjectById(ProjectEntity projectEntity){
        List<ProjectEntity> result = projectEntityMapper.selectProjectById(projectEntity);
        return result;
    }

    /* 删除项目ById */
    public int deleteProjectById(ProjectEntity projectEntity){
        int projectResult = projectEntityMapper.deleteProjectById(projectEntity);
        return projectResult;
    }

    /* 修改项目列表 */
    public int modifyProjectInfo(ProjectEntity projectEntity){
        int projectResult = projectEntityMapper.updateByPrimaryKeySelective(projectEntity);
        return projectResult;
    }

    /* 增加项目列表 */
    public int addProjectInfo(ProjectEntity projectEntity){
        projectEntity.setId(UUIDUtil.getOneUUID());
        int projectResult = projectEntityMapper.insert(projectEntity);
        if(projectResult != 0){
            return 3;//项目存在
        }
        else{
            return projectResult;
        }

    }

}
