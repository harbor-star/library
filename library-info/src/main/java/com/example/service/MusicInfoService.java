package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dto.MusicPlayerDto;
import com.example.entity.MusicInfoEntity;

public interface MusicInfoService extends IService<MusicInfoEntity> {
    MusicPlayerDto getMusicInfo();
}
