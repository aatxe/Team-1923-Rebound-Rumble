package org.usfirst.frc1923;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Vector;
import edu.wpi.first.wpilibj.Timer;

public class Output {
	private static Vector stringQueue;
	 
	public static void speak() {
		//System.out.println("[MidKnight Inventors] [" + now() + "] " + stringQueue.firstElement());
		//stringQueue.removeElementAt(0);
		//Timer.delay(0.02);	
	}
	
	public static void queue(String statement) {
		//stringQueue.addElement(statement);
		System.out.println("[MidKnight Inventors] [" + now() + "] " + statement);
		Timer.delay(0.02);
	}

	public static String now() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		// int milliseconds = calendar.get(Calendar.MILLISECOND);
		return ((hours + (TimeZone.getDefault().useDaylightTime() ? 1 : 0)) + ":" + ((minutes < 10) ? "0" + minutes : "" + minutes) + ":" + ((seconds < 10) ? "0" + seconds : "" + seconds));
	}
}