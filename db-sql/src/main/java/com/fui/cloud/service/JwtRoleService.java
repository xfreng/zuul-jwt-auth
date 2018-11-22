package com.fui.cloud.service;

import com.fui.cloud.dao.JwtRoleMapper;
import com.fui.cloud.model.JwtRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class JwtRoleService extends BaseService<JwtRole, Long> {

    @Autowired
    private JwtRoleMapper jwtRoleMapper;

    @PostConstruct
    public void initMapper() {
        this.baseMapper = jwtRoleMapper;
    }

    public List<JwtRole> getRolesByUserId(Long jwtUserId) {
        return jwtRoleMapper.getRolesByUserId(jwtUserId);
    }
}
