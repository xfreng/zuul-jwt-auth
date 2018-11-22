package com.fui.cloud.dao;

import com.fui.cloud.model.JwtUser;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtUserMapper extends BaseMapper<JwtUser, Long> {

    JwtUser findByName(String username);
}