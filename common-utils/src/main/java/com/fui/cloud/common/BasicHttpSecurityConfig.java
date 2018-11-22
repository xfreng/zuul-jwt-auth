package com.fui.cloud.common;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

public class BasicHttpSecurityConfig {

    public static void basicHttpSecurity(HttpSecurity httpSecurity, JwtAuthenticationConfig config, String type) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry httpSecurityBuilder = httpSecurity.csrf().disable()// 由于使用的是JWT，我们这里不需要csrf
                .logout().disable()
                .formLogin().loginPage(config.getLoginUrl()).defaultSuccessUrl(config.getIndexUrl(), true).permitAll()
                .and()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .anonymous()
                .and()
                .exceptionHandling().authenticationEntryPoint(
                        (request, response, e) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                // 添加JWT filter
                .addFilterAfter(new JwtTokenAuthenticationFilter(config),
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(config.getUrl()).permitAll()
                // 允许对于网站静态资源的无授权访问
                .antMatchers(HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/images/**",
                        "/webjars/**",
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/configuration/security"
                ).permitAll();
        if ("auth".equals(type)) {
            // 除上面外的所有请求全部需要鉴权认证
            httpSecurityBuilder.anyRequest().authenticated();
        } else if ("jwt".equals(type)) {
            httpSecurityBuilder.antMatchers(config.getLoginUrl(), config.getIndexUrl(), config.getGuestUrl()).permitAll();
        }
        // 禁用缓存
        httpSecurity.headers().cacheControl();
    }
}
