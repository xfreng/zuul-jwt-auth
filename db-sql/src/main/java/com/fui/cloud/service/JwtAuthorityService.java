package com.fui.cloud.service;

import com.fui.cloud.dao.JwtAuthorityMapper;
import com.fui.cloud.model.JwtAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class JwtAuthorityService extends BaseService<JwtAuthority, Long> {

    @Autowired
    private JwtAuthorityMapper jwtAuthorityMapper;

    @PostConstruct
    public void initMapper() {
        this.baseMapper = jwtAuthorityMapper;
    }

    public List<JwtAuthority> getJwtAuthorityByRoleId(Long jwtRoleId) {
        return jwtAuthorityMapper.getJwtAuthorityByRoleId(jwtRoleId);
    }
}
