package com.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.LibInfoEntity;

public interface LibInfoService extends IService<LibInfoEntity> {
    Page<LibInfoEntity> getPageByNum(String search, int num);
}
