package shfl.st.lap.authentication.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

@Component
public class DateConversion {
	
	public String convertDate(Date date) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Kolkata"));
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return (month<10?"0"+month:month)+"/"+day+"/"+year;
	}

}
