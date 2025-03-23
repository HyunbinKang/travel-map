package com.my.myProject.service;

import com.my.myProject.domain.ErrorLog;
import com.my.myProject.repository.ErrorLogRepository;
import org.springframework.stereotype.Service;

@Service
public class ErrorLogService {
    private final ErrorLogRepository repository;

    public ErrorLogService(ErrorLogRepository repository) {
        this.repository = repository;
    }

    public void save(ErrorLog errorLog) {
        repository.save(errorLog);
    }
}