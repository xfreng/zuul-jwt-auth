package com.fui.cloud.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private static Logger logger = LoggerFactory.getLogger(JwtTokenAuthenticationFilter.class);

    private final JwtAuthenticationConfig config;

    public JwtTokenAuthenticationFilter(JwtAuthenticationConfig config) {
        this.config = config;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String reqUrl = request.getRequestURI();
        logger.info("send {} request to {}", request.getMethod(), reqUrl);
        String token = request.getHeader(config.getHeader());
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(config.getHeader());
        }
        if (token != null && token.startsWith(config.getPrefix() + " ")) {
            token = token.replace(config.getPrefix() + " ", "");
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(config.getSecret().getBytes())
                        .parseClaimsJws(token)
                        .getBody();
                String username = claims.getSubject();
                @SuppressWarnings("unchecked")
                List<String> authorities = claims.get("authorities", List.class);
                if (username != null) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
                            authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    if (!isIgnoredUrl(reqUrl)) {//过滤不需要权限的地址
                        for (String url : authorities) {
                            if (!url.equals(reqUrl)) {//无权限
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED); //响应体
                                break;
                            }
                        }
                    }
                }
            } catch (Exception ignore) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED); //响应体
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean isIgnoredUrl(String url) {
        boolean bool = false;
        String[] excludedUrls = new String[]{
                "/v2/api-docs",
                "/configuration/ui",
                "/configuration/security",
                config.getZuulPrefixUrl() + config.getLoginUrl(),
                config.getZuulPrefixUrl() + config.getIndexUrl(),
                config.getZuulPrefixUrl() + config.getGuestUrl()
        };
        for (String excludedUrl : excludedUrls) {
            if (excludedUrl.equals(url)) {
                bool = true;
                break;
            }
        }
        return bool;
    }
}
