package com.example.demosecond.dao;

import com.example.demosecond.dao.entity.ProjectEntity;
import com.example.demosecond.dao.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ProjectEntityMapper {
    /* 全查项目列表 */
    List<ProjectEntity> queryProjectList(ProjectEntity projectEntity);

    /* 查询项目根据名字 */
    List<ProjectEntity> selectProjectInfo(ProjectEntity projectEntity);

    /* 根据ID查询项目信息 */
    List<ProjectEntity> selectProjectById(ProjectEntity projectEntity);

    /* 创建项目信息 */
    int insert(ProjectEntity projectEntity);

    /* 根据id删除项目信息 */
    int deleteProjectById(ProjectEntity projectEntity);

    /* 更新项目信息 */
    int updateByPrimaryKeySelective(ProjectEntity projectEntity);

    /* 查询用户 */
//    List<ProjectEntity> selectProjectInfo(ProjectEntity projectEntity);
}
