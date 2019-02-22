package com.fui.cloud.common;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

@Getter
@ToString
public class JwtAuthenticationConfig {

    @Value("${fui.zuul.prefix.url:/fui}")
    private String zuulPrefixUrl;

    @Value("${fui.security.jwt.url:/getToken}")
    private String url;

    @Value("${fui.index.url:/index}")
    private String indexUrl;

    @Value("${fui.guest.url:/guest}")
    private String guestUrl;

    @Value("${fui.security.jwt.header:Authorization}")
    private String header;

    @Value("${fui.security.jwt.prefix:Bearer}")
    private String prefix;

    @Value("${fui.security.jwt.expiration:#{24*60*60}}")
    private long expiration; // default 24 hours

    @Value("${fui.security.jwt.secret}")
    private String secret;
}
