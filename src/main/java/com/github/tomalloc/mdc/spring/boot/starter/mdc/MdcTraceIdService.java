package com.github.tomalloc.mdc.spring.boot.starter.mdc;

import lombok.AllArgsConstructor;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.function.Supplier;

@AllArgsConstructor
public class MdcTraceIdService {
    public final static String HTTP_TRACE_ID = "X-traceId";

    private final String traceId;
    private final TraceIdGenerator generator;




    public void put(Supplier<String> defaultTraceIdSupplier){
        String traceId=defaultTraceIdSupplier.get();
        if(!StringUtils.hasText(traceId)){
            traceId=generator.generateTraceId();
        }
        MDC.put(traceId, traceId);
    }

    public void remove(){
        MDC.remove(traceId);
    }

    public String get(){
        return MDC.get(traceId);
    }

    public static Map<String, String>  getCopyOfContextMap(){
        return MDC.getCopyOfContextMap();
    }

    public static void setContextMap(Map<String, String> contextMap){
        MDC.setContextMap(contextMap);
    }
    public static void clear() {
        MDC.clear();
    }
}
