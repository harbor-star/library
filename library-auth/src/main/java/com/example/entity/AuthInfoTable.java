package com.example.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/6/23 17:02
 */
@Data
public class AuthInfoTable {
    private Long auth_id;
    private String auth_name;
    private String auth_password;
    private String auth_status;
    private String auth_type;
    private Timestamp last_login;
}
