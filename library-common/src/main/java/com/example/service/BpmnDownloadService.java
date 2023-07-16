package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.BpmnModelEntity;

import java.io.IOException;

public interface BpmnDownloadService extends IService<BpmnModelEntity> {
    boolean getAndSaveBpmnModel(String model_key) throws IOException;
}
