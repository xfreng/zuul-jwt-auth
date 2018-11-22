package com.fui.cloud.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Api(description = "用户登录controller")
public class LoginController {

    @ApiOperation(value = "转发到登录页面")
    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("html/login");
    }

    @ApiOperation(value = "转发到首页")
    @GetMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("html/index/index");
    }

    @ApiOperation(value = "获取登录人的授权信息")
    @GetMapping("/get")
    @Secured("ROLE_ADMIN")
    @ResponseBody
    public Authentication get() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @ApiOperation(value = "退出登录", notes = "通过移除token来达到退出登录的效果", httpMethod = "GET")
    @ApiImplicitParam(name = "accessToken", value = "用来认证的access_token", required = true, dataType = "String")
    @GetMapping("/logout")
    public ModelAndView logout() {
        return new ModelAndView("html/login");
    }

}
