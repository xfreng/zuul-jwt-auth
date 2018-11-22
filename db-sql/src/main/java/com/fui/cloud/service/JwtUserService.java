package com.fui.cloud.service;

import com.fui.cloud.dao.JwtUserMapper;
import com.fui.cloud.model.JwtAuthority;
import com.fui.cloud.model.JwtRole;
import com.fui.cloud.model.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JwtUserService extends BaseService<JwtUser, Long> {

    @Autowired
    private JwtUserMapper jwtUserMapper;
    @Autowired
    private JwtRoleService jwtRoleService;
    @Autowired
    private JwtAuthorityService jwtAuthorityService;

    @PostConstruct
    public void initMapper() {
        this.baseMapper = jwtUserMapper;
    }

    public JwtUser findByName(String username) {
        return jwtUserMapper.findByName(username);
    }

    public Set<GrantedAuthority> getAuthorities(Long oauthUserId) {
        Set<GrantedAuthority> userAuthorities = new HashSet<>();
        List<JwtRole> roles = jwtRoleService.getRolesByUserId(oauthUserId);
        for (JwtRole role : roles) {
            List<JwtAuthority> authorities = jwtAuthorityService.getJwtAuthorityByRoleId(role.getId());
            for (JwtAuthority authority : authorities) {
                userAuthorities.add(new SimpleGrantedAuthority(authority.getValue()));
            }
        }
        return userAuthorities;
    }
}
