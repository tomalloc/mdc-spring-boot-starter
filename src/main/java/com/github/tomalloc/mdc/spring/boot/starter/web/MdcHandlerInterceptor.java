package com.github.tomalloc.mdc.spring.boot.starter.web;


import lombok.AllArgsConstructor;
import com.github.tomalloc.mdc.spring.boot.starter.mdc.MdcTraceIdService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
public class MdcHandlerInterceptor implements HandlerInterceptor {

    private MdcTraceIdService mdcTraceIdService;

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        mdcTraceIdService.remove();
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        mdcTraceIdService.put(()->request.getHeader(MdcTraceIdService.HTTP_TRACE_ID));
        return true;
    }
}