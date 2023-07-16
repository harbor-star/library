package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.LibInfoEntity;
import com.example.request.QueryConditionRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/4/2 16:47
 */
@Mapper
@Repository
public interface LibInfoMapper extends BaseMapper<LibInfoEntity> {
    /**
     * 获取图书信息
     * @param ids
     * @return
     */
    List<LibInfoEntity> getLibInformations(@Param("ids") List<Long> ids);

    /**
     * 获取图书信息模糊
     * @param
     * @return
     */
    Page<LibInfoEntity> getLibInfoByCondition(Page<LibInfoEntity> page, @Param("condition") QueryConditionRequest request);
}
