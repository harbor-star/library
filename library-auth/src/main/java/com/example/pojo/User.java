package com.example.pojo;

import com.mysql.cj.util.StringUtils;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/6/23 18:14
 */
@Data
public class User implements UserDetails {

    private static final long serialVersionUID = -2656338569364825673L;

    private Integer id;

    private String userName;

    private String password;

    private boolean enable;

    private boolean locked;

    private List<Role> userRoles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new LinkedList<>();
        if (CollectionUtils.isEmpty(userRoles)) {
            try {
                throw new Exception("not found userInfo");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (Role role : userRoles) {
            String[] authority = role.getDescription().split(",");
            for (String auth : authority) {
                authorities.add(new SimpleGrantedAuthority(auth));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
