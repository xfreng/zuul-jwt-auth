package com.fui.cloud.security;

import com.fui.cloud.model.JwtUser;
import com.fui.cloud.service.JwtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component("jdbcUserDetailsService")
public class JdbcUserDetailsService implements UserDetailsService {

    @Autowired
    private JwtUserService jwtUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JwtUser jwtUser = jwtUserService.findByName(username);
        Optional<JwtUser> realUser = Optional.ofNullable(jwtUser);
        return realUser.map(user -> {
            Set<GrantedAuthority> grantedAuthorities = jwtUserService.getAuthorities(user.getId());
            user.setAuthorities(grantedAuthorities);
            return user;
        }).orElseThrow(() -> new UsernameNotFoundException("用户" + username + "不存在!"));
    }

}
