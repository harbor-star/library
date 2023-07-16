package com.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.pojo.Role;
import com.example.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * security user表操作
 */
@Repository
@Mapper
@DS("library-database")
public interface AuthSecurityMapper {
    User getUserInfoByUsername(@Param("username") String username);
    Integer insertUserInfo(@Param("user") User user);
    Role getRoleInfoByUsername(@Param("username") String username);
}
