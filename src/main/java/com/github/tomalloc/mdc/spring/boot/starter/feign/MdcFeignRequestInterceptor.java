package com.github.tomalloc.mdc.spring.boot.starter.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.AllArgsConstructor;
import com.github.tomalloc.mdc.spring.boot.starter.mdc.MdcTraceIdService;

@AllArgsConstructor
public class MdcFeignRequestInterceptor implements RequestInterceptor {

    private MdcTraceIdService mdcTraceIdService;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(MdcTraceIdService.HTTP_TRACE_ID, mdcTraceIdService.get());
    }
}
