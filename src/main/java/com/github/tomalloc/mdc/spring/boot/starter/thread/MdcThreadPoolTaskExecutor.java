package com.github.tomalloc.mdc.spring.boot.starter.thread;

import com.github.tomalloc.mdc.spring.boot.starter.mdc.MdcTraceIdService;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;

public class MdcThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
    @Override
    public void execute(Runnable runnable) {
        Map<String, String> context = MdcTraceIdService.getCopyOfContextMap();
        super.execute(() -> run(runnable, context));
    }


    private void run(Runnable runnable, Map<String, String> context) {
        MdcTraceIdService.setContextMap(context);
        try {
            runnable.run();
        } finally {
            MdcTraceIdService.clear();
        }
    }
}
