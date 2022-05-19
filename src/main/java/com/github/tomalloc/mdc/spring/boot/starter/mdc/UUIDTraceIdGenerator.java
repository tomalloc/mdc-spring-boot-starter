package com.github.tomalloc.mdc.spring.boot.starter.mdc;

import java.util.UUID;

public class UUIDTraceIdGenerator implements TraceIdGenerator{
    @Override
    public String generateTraceId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
