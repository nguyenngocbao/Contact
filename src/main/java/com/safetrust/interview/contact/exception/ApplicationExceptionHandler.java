package com.safetrust.interview.contact.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * This class used to handle all exceptions and return specific errors object
 * based on the exception
 */
@ControllerAdvice
@ResponseBody
public class ApplicationExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);
	private static final String FIELD_ERROR_FORMAT = "%s field: %s, ";
	

	/**
	 * Handle INTERNAL SERVER ERROR
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<ErrorMessageInfo> handleServerError(Exception e) {
		logger.warn("INTERNAL SERVER ERROR", e);
		return new ResponseEntity<ErrorMessageInfo>(exceptionHandling(e), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handle bad request
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ApplicationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<ErrorMessageInfo> handleApplicationException(ApplicationException e) {
		logger.warn("Bad request ", e);
		return new ResponseEntity<ErrorMessageInfo>(exceptionHandling(e), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle bad request
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessageInfo handleArgumentNotValidException(MethodArgumentNotValidException e) {
		StringBuilder errorMessages = new StringBuilder();
		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errorMessages.append(String.format(FIELD_ERROR_FORMAT, fieldName,errorMessage));
		});
		return new ErrorMessageInfo(errorMessages.toString());
	}

	/**
	 * Handle request not found
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ResponseEntity<ErrorMessageInfo> handleRequestNotFound(NoHandlerFoundException e) {
		logger.warn("Request is not found ", e);
		return new ResponseEntity<ErrorMessageInfo>(exceptionHandling(e), HttpStatus.NOT_FOUND);
	}

	/**
	 * Handle constraint violation exception
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ ConstraintViolationException.class })
	@ResponseBody
	public ResponseEntity<ErrorMessageInfo> handleConstraintViolationException(ConstraintViolationException e) {
		logger.warn("Constraint violation exception ", e);
		return new ResponseEntity<ErrorMessageInfo>(exceptionHandling(e), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Exception handling
	 *
	 * @param e
	 * @return
	 */
	private ErrorMessageInfo exceptionHandling(Exception e) {
		return new ErrorMessageInfo(e.getMessage());
	}

}
