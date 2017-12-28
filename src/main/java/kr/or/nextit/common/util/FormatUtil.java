package kr.or.nextit.common.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtil {

	public static String number(long number, String pattern) {
		DecimalFormat formatter = new DecimalFormat(pattern);
		return formatter.format(number);
	}
	
	public static String date(Date date, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}
	
}
