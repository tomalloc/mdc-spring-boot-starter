package com.github.tomalloc.mdc.spring.boot.starter.autoconfigure;

import com.github.tomalloc.mdc.spring.boot.starter.mdc.MdcTraceIdService;
import com.github.tomalloc.mdc.spring.boot.starter.mdc.TraceIdGenerator;
import com.github.tomalloc.mdc.spring.boot.starter.mdc.UUIDTraceIdGenerator;
import feign.Feign;
import com.github.tomalloc.mdc.spring.boot.starter.feign.MdcFeignRequestInterceptor;
import com.github.tomalloc.mdc.spring.boot.starter.web.MdcMvcConfig;
import lombok.AllArgsConstructor;
import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@AllArgsConstructor
@ConditionalOnClass(MDC.class)
@EnableConfigurationProperties(MdcProperties.class)
@Configuration
public class MdcAutoConfiguration {

    private MdcProperties mdcProperties;

    @Bean
    @ConditionalOnMissingBean
    public TraceIdGenerator traceIdGenerator() {
        return new UUIDTraceIdGenerator();
    }

    @Bean
    public MdcTraceIdService traceIdService(TraceIdGenerator traceIdGenerator) {
        String traceId = mdcProperties.getTraceId();
        if (!StringUtils.hasText(traceId)) {
            traceId = MdcProperties.TRACE_ID;
        }
        return new MdcTraceIdService(traceId, traceIdGenerator);
    }

    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    @Configuration
    static class ServletConfiguration {
        @Bean
        public MdcMvcConfig mdcMvcConfig(MdcTraceIdService traceIdService) {
            return new MdcMvcConfig(traceIdService);
        }
    }


    @ConditionalOnClass(Feign.class)
    @Configuration
    static class FeignConfiguration {
        @Bean
        public MdcFeignRequestInterceptor mdcFeignRequestInterceptor(MdcTraceIdService traceIdService) {
            return new MdcFeignRequestInterceptor(traceIdService);
        }
    }
}
