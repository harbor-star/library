package com.example.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/6/23 18:19
 */
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = -4535385050330679543L;

    private Integer id;

    private String name;

    private String description;

}
