package com.example.demosecond.dao;

import com.example.demosecond.dao.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface UserEntityMapper {
    /* 查询用户列表 */
    List<UserEntity> queryUserList(UserEntity userEntity);

    /* 创建用户信息 */
    int insert(UserEntity userEntity);

    /* 根据id删除用户信息 */
    int deleteUserById(UserEntity userEntity);

    /* 更新用户信息 */
    int updateByPrimaryKeySelective(UserEntity userEntity);

    /* 查询用户 */
    List<UserEntity> selectUserInfo(UserEntity userEntity);

    //根据id关闭
    int closeUserById(UserEntity userEntity);
    //重设密码为123
    int resetUserById(UserEntity userEntity);

}
