package com.fui.cloud.dao;

import com.fui.cloud.model.JwtAuthority;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JwtAuthorityMapper extends BaseMapper<JwtAuthority, Long> {

    List<JwtAuthority> getJwtAuthorityByRoleId(Long oauthRoleId);
}