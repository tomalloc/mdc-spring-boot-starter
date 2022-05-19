package com.github.tomalloc.mdc.spring.boot.starter.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ToString
@ConfigurationProperties(prefix = "logging.mdc")
public class MdcProperties {
    final static String TRACE_ID = "traceId";

    private String traceId;
}
