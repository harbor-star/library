package com.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.entity.AuthInfoTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 涉及验证的mapper方法
 */
@Repository
@Mapper
@DS("library-database")
public interface AuthMapper {
    Integer submitLibInfo(@Param("libTable") AuthInfoTable authInfoTable);
    Integer updateStatus(@Param("libTable") AuthInfoTable authInfoTable);
    AuthInfoTable getAuthInfoById(@Param("id") Long id);
    Integer deleteAuthInfo(@Param("id") Long id);
}
