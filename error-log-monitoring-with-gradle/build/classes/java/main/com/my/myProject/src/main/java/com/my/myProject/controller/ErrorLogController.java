package com.my.myProject.controller;

import com.my.myProject.domain.ErrorLog;
import com.my.myProject.service.ErrorLogService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/errors")
public class ErrorLogController {

    private final ErrorLogService errorLogService;
    private final WebClient webClient;
    
    @Value("${slack.webhook-url}")
    private String slackWebhookUrl;

    public ErrorLogController(ErrorLogService errorLogService) {
        this.errorLogService = errorLogService;
        this.webClient = WebClient.create();
    }

    @PostMapping
    public String logError(@RequestBody ErrorLog errorLog) {
        errorLog.setTimestamp(LocalDateTime.now());
        errorLogService.save(errorLog);

        if ("CRITICAL".equalsIgnoreCase(errorLog.getLevel())) {
            webClient.post()
                .uri(slackWebhookUrl)
                .bodyValue("{}")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe();
        }

        return "Logged";
    }
}