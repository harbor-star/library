package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/4/14 18:42
 */
@Data
@TableName(value = "music_info")
public class MusicInfoEntity {
    @TableId(value = "music_id")
    private Long musicId;

    @TableField(value = "music_name")
    private String musicName;

    @TableField(value = "music_url")
    private String musicUrl;

    @TableLogic(value = "1", delval = "0")
    private Integer canPlay;
}
