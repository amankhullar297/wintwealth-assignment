package com.wintweatlh.assignment.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@Builder
@Getter
@Setter
public class BaseException extends IOException {
    private Object errorInfo;
    private HttpStatus status;
}
