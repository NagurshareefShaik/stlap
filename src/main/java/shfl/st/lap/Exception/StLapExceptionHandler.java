package shfl.st.lap.Exception;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StLapExceptionHandler {
	
	Logger logger=LoggerFactory.getLogger(StLapExceptionHandler.class);
	
   	@ExceptionHandler(value = SQLException.class)
	public ResponseEntity<String> sqlException(SQLException exception) {
   		logger.error(exception.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
	}
   	
   	@ExceptionHandler(value = NoSuchElementException.class)
   	public ResponseEntity<String> noSuchElementFound(SQLException exception) {
   		logger.error(exception.getMessage());
   		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
   	}
   	
   	

}
