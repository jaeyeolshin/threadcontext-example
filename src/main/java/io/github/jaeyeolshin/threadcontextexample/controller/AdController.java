package io.github.jaeyeolshin.threadcontextexample.controller;

import io.github.jaeyeolshin.threadcontextexample.data.Ad;
import io.github.jaeyeolshin.threadcontextexample.log.MemoryLog;
import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class AdController {
    private static final Logger logger = LoggerFactory.getLogger(AdController.class);
    private static final String DEBUG_PARAM_NAME = "debug";

    @RequestMapping("/ad")
    public Ad ad(
            @RequestParam("param") String param,
            @RequestParam(value = "debug", defaultValue = "false") boolean debug
    ) {
        try {
            if (debug) {
                ThreadContext.put(DEBUG_PARAM_NAME, "true");
            }
            logger.debug(param);
            callApiInParallel();
            return new Ad("<h1>광고</h1>", MemoryLog.get());
        } finally {
            MemoryLog.clear();
            ThreadContext.remove(DEBUG_PARAM_NAME);
        }
    }

    public void callApiInParallel() {
        CompletableFuture.runAsync(() -> {
            logger.debug("api called");
        }).join();
    }
}
