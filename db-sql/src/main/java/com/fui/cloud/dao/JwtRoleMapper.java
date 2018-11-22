package com.fui.cloud.dao;

import com.fui.cloud.model.JwtRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JwtRoleMapper extends BaseMapper<JwtRole, Long> {

    List<JwtRole> getRolesByUserId(Long jwtUserId);
}