package com.firstline.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no such patient")
public class ThereIsNoSuchPatientException extends RuntimeException {
}
