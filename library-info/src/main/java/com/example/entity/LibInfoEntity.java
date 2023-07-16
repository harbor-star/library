package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/4/2 16:49
 */
@Data
@TableName(value = "lib_info")
public class LibInfoEntity {
    @TableId(value = "lib_sno")
    private Long libSno;

    @TableField(value = "lib_name")
    private String libName;

    @TableField(value = "lib_type")
    private String libType;

    @TableField(value = "lib_datetime")
    private Timestamp libDatetime;

    @TableField(value = "lib_price")
    private Float libPrice;
}
