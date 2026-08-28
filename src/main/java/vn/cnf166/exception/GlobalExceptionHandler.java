package vn.cnf166.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handlingValidationException(Exception e, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(new Date());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
		errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());

		String message = e.getMessage();
		int startIndexMessage = message.lastIndexOf("[");
		int endIndexMessage = message.lastIndexOf("]");
		message = message.substring(startIndexMessage + 1, endIndexMessage - 1);
		errorResponse.setMessage(message);

		return errorResponse;
	}
}
