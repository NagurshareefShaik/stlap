package shfl.st.lap.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class MobileNumberChecker {
	
	public Boolean check(String user) {
		Pattern p = Pattern.compile("^\\d{10}$");
		Matcher m = p.matcher(user);
		return m.matches();
		
	}

}
