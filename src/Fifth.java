import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

public class Fifth {

	public static void main ( String Args [ ] )
	{
		
		
		LocalDateTime now = LocalDateTime.now();
		System.out.println ( now ) ;
		
		int year = now.getYear();
		System.out.println ( year ) ;
		
		int month = now.getMonthValue();
		System.out.println ( month ) ;
		
		int day = now.getDayOfMonth();
		System.out.println ( day  ) ;
		
		int hour = now.getHour();
		int minute = now.getMinute();
		int second = now.getSecond();
		int millis = now.get(ChronoField.MILLI_OF_SECOND); // Note: no direct getter available.

		System.out.printf("%d-%02d-%02d %02d:%02d:%02d.%03d", year, month, day, hour, minute, second, millis);
		
	}

}
