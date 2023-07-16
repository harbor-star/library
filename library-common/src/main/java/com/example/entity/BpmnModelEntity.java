package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/5/14 17:11
 */
@Data
@TableName("act_de_model")
public class BpmnModelEntity {
    @TableId
    private String Id;

    @TableField("name")
    private String name;

    @TableField("model_key")
    private String model_key;

    @TableField("description")
    private String description;

    @TableField("thumbnail")
    private byte[] thumbnail;
}
