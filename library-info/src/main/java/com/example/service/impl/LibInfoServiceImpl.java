package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.LibInfoEntity;
import com.example.mapper.LibInfoMapper;
import com.example.request.QueryConditionRequest;
import com.example.service.LibInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/4/13 18:25
 */
@Service
public class LibInfoServiceImpl extends ServiceImpl<LibInfoMapper, LibInfoEntity> implements LibInfoService {
    @Autowired
    private LibInfoMapper libInfoMapper;

    @Override
    public Page<LibInfoEntity> getPageByNum(String search, int num) {
        Page<LibInfoEntity> page = new Page<>(num, 10);
        return this.getBaseMapper().getLibInfoByCondition(page, new QueryConditionRequest());
    }
}
