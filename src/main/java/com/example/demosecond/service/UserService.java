package com.example.demosecond.service;

import com.example.demosecond.common.utils.UUIDUtil;
import com.example.demosecond.dao.UserEntityMapper;
import com.example.demosecond.dao.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserEntityMapper userEntityMapper;

    /* 查询用户列表 */
    public List<UserEntity> queryUserList(UserEntity userEntity){
        List<UserEntity> result = userEntityMapper.queryUserList(userEntity);
        return result;
    }

    /* 登录 */
    public List<UserEntity> selectUserInfo(UserEntity userEntity){
        List<UserEntity> result = userEntityMapper.selectUserInfo(userEntity);
        return result;
    }

    /* 创建用户 */
    public int addUserInfo(UserEntity userEntity){
        userEntity.setId(UUIDUtil.getOneUUID());
        int userResult = userEntityMapper.insert(userEntity);
        if(userResult != 0){
            return 3;//用户存在
        }
        else{
            return userResult;
        }

    }

    /* 修改用户信息 */
    public int modifyUserInfo(UserEntity userEntity){
        int userResult = userEntityMapper.updateByPrimaryKeySelective(userEntity);
        return userResult;
    }

    /* 删除用户信息id */
    public int deleteUserById(UserEntity userEntity){
        int userResult = userEntityMapper.deleteUserById(userEntity);
        return userResult;
    }

    /* 关闭 */
    public int closeUserById(UserEntity userEntity){
        int result = userEntityMapper.closeUserById(userEntity);
        return result;
    }

    public int resetUserById(UserEntity userEntity){
        int result = userEntityMapper.resetUserById(userEntity);
        return result;
    }
}
