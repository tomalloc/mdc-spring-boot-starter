package com.github.tomalloc.mdc.spring.boot.starter.web;

import lombok.AllArgsConstructor;
import com.github.tomalloc.mdc.spring.boot.starter.mdc.MdcTraceIdService;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@AllArgsConstructor
public class MdcMvcConfig implements WebMvcConfigurer {
    private MdcTraceIdService mdcTraceIdService;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MdcHandlerInterceptor(mdcTraceIdService)).addPathPatterns("/**");
    }
}
