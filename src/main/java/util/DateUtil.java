package util;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

	public static Date nowAsDate() {
		return asDate(LocalDateTime.now());
	}

	public static Date asDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date addMonth(Date date, int day) {
		LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(),ZoneId.systemDefault()).plusMonths(day);
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	public static Date minusMonth(Date date, int day) {
		LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).minusMonths(day);
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static boolean lowerThan(Date firstDate, Date secondDate) {
		return firstDate.compareTo(secondDate) < 0;
	}

}
