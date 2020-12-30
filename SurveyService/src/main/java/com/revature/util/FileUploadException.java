package com.revature.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class FileUploadException {

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<String> handleMaxSizeExceededException(MaxUploadSizeExceededException exc) {
		return ResponseEntity
				.status(HttpStatus.EXPECTATION_FAILED)
				.body("One or more files are too large!");
	}
}
