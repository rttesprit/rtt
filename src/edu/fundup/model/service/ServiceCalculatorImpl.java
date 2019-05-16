package edu.fundup.model.service;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import edu.fundup.model.iservice.ServiceCalculator;

public class ServiceCalculatorImpl implements ServiceCalculator{

	@Override
	public String calculDate(String dateEnd) {
		// TODO Auto-generated method stub
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MMM-dd");
		LocalDate localDate = new LocalDate();
		LocalDate lDateEnd = new LocalDate().parse(dateEnd);		
		int days = Days.daysBetween(localDate.now(),lDateEnd).getDays();
		return Integer.toString(days);
	}
	
	

}
