package com.example.demo.configuration;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


@Getter
public class CustomUser extends User {
    String id;

    public void setId(String id) {
        this.id = id;
    }

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String id) {
        super(username, password, authorities);
        this.id = id;
    }

    public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String id) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
    }
}

