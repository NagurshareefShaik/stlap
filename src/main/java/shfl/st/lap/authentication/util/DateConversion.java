package shfl.st.lap.authentication.util;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

@Component
public class DateConversion {

	public String convertDateTimeToDate(Date date) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Kolkata"));
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return dayMonthModifier(month) + "/" + dayMonthModifier(day) + "/" + year;
	}

	public String convertStringToDate(String dateString) {
		Instant instant = Instant.parse(dateString);
		Date date = Date.from(instant);
		return convertDateTimeToDate(date);
	}

	public String dayMonthModifier(int param) {
		return param < 10 ? "0" + param : String.valueOf(param);
	}

}
