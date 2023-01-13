package shfl.st.lap.Exception;

import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Exception {
	
	@ExceptionHandler(value = NoSuchElementException.class)
	public void exception() {
		System.out.println("exception called");
	}

}
