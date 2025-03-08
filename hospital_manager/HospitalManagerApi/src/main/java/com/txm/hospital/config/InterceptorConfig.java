package com.txm.hospital.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.txm.hospital.interceptors.JwtInterceptor;

/***
 * 这段代码通过拦截器实现了 “精准拦截”，
 * 确保只有特定路径（如 /666）需要 JWT 认证，而登录、生成 PDF 等接口可以直接访问。
 */

@Component
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/666")                //拦截
                .excludePathPatterns("/patient/pdf")    //放行
                .excludePathPatterns("/**/login");      //放行
    }
}
