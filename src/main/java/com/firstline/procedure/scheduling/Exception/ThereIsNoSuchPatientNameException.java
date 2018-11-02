package com.firstline.procedure.scheduling.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no such patient name")
public class ThereIsNoSuchPatientNameException extends RuntimeException {
}
