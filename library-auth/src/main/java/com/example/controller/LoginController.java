package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/6/23 17:03
 */
@RestController
@RequestMapping(value = "/auth")
public class LoginController {
    @RequestMapping(value = "/test")
    public String test() {
        return "hello";
    }
}
