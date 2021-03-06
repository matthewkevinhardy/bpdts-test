package bpdts.exception;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BpdtsControllerAdvice {
	
	private static final Logger LOG = LoggerFactory.getLogger(BpdtsControllerAdvice.class);
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e,HttpServletRequest request) {
		return error(HttpStatus.INTERNAL_SERVER_ERROR,e,request);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e,HttpServletRequest request) {
		return error(HttpStatus.NOT_FOUND,e,request);
	}
	
	private ResponseEntity<ErrorResponse> error(HttpStatus status, Exception e,HttpServletRequest request) {
		LOG.error("Exception: ",e);
		return ResponseEntity.status(status).body(
				new ErrorResponse(status, status.value()+"", e.getMessage(), request.getRequestURI(), LocalDateTime.now(ZoneOffset.UTC)));
	}
}
