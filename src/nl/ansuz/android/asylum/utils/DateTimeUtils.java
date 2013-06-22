package nl.ansuz.android.asylum.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Collection of Data and/or Time utilities.
 *
 * @author Wijnand
 */
public class DateTimeUtils {

	public static final long ONE_SECOND = 1000;
	public static final long ONE_MINUTE = 60 * ONE_SECOND;
	public static final long ONE_HOUR = 60 * ONE_MINUTE;
	
	/**
	 * Formats a duration in ms to something more readable.
	 * Splits the duration in hours, minutes and seconds. 
	 * Only shows hours when the duration exceeds an hour.
	 *
	 * @param duration The duration in milliseconds.
	 *
	 * @return "(h:)mm:ss", i.e. "(1:)23:45".
	 */
	public static String formatDuration(int duration) {
		int hours = (int) (duration / ONE_HOUR);
		duration -= hours * ONE_HOUR;
		int minutes = (int) (duration / ONE_MINUTE);
		duration -= minutes * ONE_MINUTE;
		int seconds = (int) (duration / ONE_SECOND);
		
		String formattedDuration = "";
		if(hours > 0) {
			formattedDuration = String.format("%d:%02d:%02d", hours, minutes, seconds);
		} else {
			formattedDuration = String.format("%02d:%02d", minutes, seconds);
		}

		return formattedDuration;
	}

	/**
	 * Formats a Unix timestamp in something human readable.
	 *
	 * @see http://docs.oracle.com/javase/1.4.2/docs/api/java/text/SimpleDateFormat.html
	 *
	 * @param timestamp int - Unix timestamp (in seconds).
	 * @param pattern String - Pattern to use to format the date and time.
	 *
	 * @return Formatted date and time.
	 */
	public static String formatDateAndTime(int timestamp, String pattern) {
		long time = timestamp * ONE_SECOND;
		Date date = new Date(time);

		SimpleDateFormat formattedDate = new SimpleDateFormat(pattern, Locale.getDefault());
		return formattedDate.format(date);
	}
}
