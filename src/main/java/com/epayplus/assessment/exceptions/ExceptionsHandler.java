package com.epayplus.assessment.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorModel> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(
				error -> {
					errors.put(error.getField(), error.getDefaultMessage());
				}
		);
		return ResponseEntity.status(400).body(new ErrorModel(
				errors.entrySet().iterator().next().getValue(),  errors
		));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorModel> handleAnyException(Exception e) {
		return ResponseEntity.status(500).body(new ErrorModel(e.getLocalizedMessage(), null));
	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorModel> handleAnyException(CustomException e) {
		return ResponseEntity.status(500).body(new ErrorModel(e.getMessage(), e.getErrors()));
	}
}