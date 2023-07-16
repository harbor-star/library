package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dto.MusicPlayerDto;
import com.example.entity.MusicInfoEntity;
import com.example.mapper.MusicInfoMapper;
import com.example.service.MusicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/4/14 18:52
 */
@Service
public class MusicInfoServiceImpl extends ServiceImpl<MusicInfoMapper, MusicInfoEntity> implements MusicInfoService {

    @Autowired
    private MusicInfoMapper musicInfoMapper;

    @Override
    public MusicPlayerDto getMusicInfo() {
        MusicPlayerDto musicPlayerDto = new MusicPlayerDto();
        Wrapper<MusicInfoEntity> wrapper = new QueryWrapper<>();
        List<MusicInfoEntity> result = musicInfoMapper.selectList(wrapper);
        musicPlayerDto.setMusicData(result);
        musicPlayerDto.setSize(result.size());
        return musicPlayerDto;
    }
}
