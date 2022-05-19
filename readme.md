# MDC

MDC的全称是 Mapped Diagnostic Contexts。具体可参见 [logback](https://logback.qos.ch/manual/mdc.html) 的说明

## traceId
支持在日志中输出自定义的追踪id。目前支持在spring interceptor、fegin、thread pool中传递 traceId。

