package com.xj.sample.web.api;

import com.xj.sample.tool.CookieUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/test")
@RestController
public class TestController {
    @GetMapping("/setCookie")
    public  void setCookie(HttpServletResponse response){
        CookieUtils.writeCookie(response,"sg","xj");
    }

    @GetMapping("/getCookie")
    public String setCookie(HttpServletRequest request, String cookieName){
        return CookieUtils.getCookie(request,"sg");
    }
}
