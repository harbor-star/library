package com.example.service;

import com.example.mapper.AuthMapper;
import com.example.mapper.AuthSecurityMapper;
import com.example.pojo.Role;
import com.example.pojo.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/6/23 18:29
 */
@Service
@Slf4j
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private AuthSecurityMapper authSecurityMapper;

    @Autowired
    private AuthMapper authMapper;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = authSecurityMapper.getUserInfoByUsername(s);

        Role role = authSecurityMapper.getRoleInfoByUsername(s);

        List<Role> roles = new ArrayList<>();

        roles.add(role);

        log.info("users: {}, authorities: {}", s, roles);

        if (user != null) {
            user.setUserRoles(roles);
            return user;
        } else {
            throw new Exception("not found user info");
        }
    }
}
