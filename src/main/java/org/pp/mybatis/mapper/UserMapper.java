package org.pp.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.pp.mybatis.entity.UserEntity;

import java.util.List;

public interface UserMapper {

    List<UserEntity> listAllUser();

    @Select("select * from user where id=#{userId,jdbcType=INTEGER}")
    UserEntity getUserById(@Param("userId") String userId);

    List<UserEntity> getUserByEntity(UserEntity user);

    UserEntity getUserByPhone(@Param("phone") String phone);

}