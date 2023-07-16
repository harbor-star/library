package com.example.dto;

import com.example.entity.MusicInfoEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
* @author 陈磊
* @version 2.0
* @date 2023/4/14 19:14
*
*/
@Data
public class MusicPlayerDto implements Serializable {
    // 歌曲列表
    private List<MusicInfoEntity> musicData;

    // 歌曲总数
    private Integer size;
}
