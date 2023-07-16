package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/5/21 23:25
 */

@Data
@TableName(value = "STUDENT")
public class OracleEntity {
    @TableId(value = "id")
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("age")
    private Integer age;
}
