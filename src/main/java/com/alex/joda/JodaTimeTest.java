package com.alex.joda;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

public class JodaTimeTest {

	public static void main(String[] args) {
//		testPeriod();
		System.out.println(new DateTime(2015, 10, 8, 0, 0).getMillis());
	}
	
	/**
	 * 区间Period
	 * 2015年10月14日<br>
	 * @author gao.jun
	 */
	public static void testPeriod() {
		Period p = new Period(new DateTime(1443628800906L), new DateTime(1446307200678L), PeriodType.months());
		System.out.println(p.getMonths());
	}
	
	/**
	 * 设置DateTime
	 * 2015年10月14日<br>
	 * @author gao.jun
	 */
	public static void setDateValue() {
		DateTime weekAgo = new DateTime().minusDays(6);
		DateTime time = new DateTime(weekAgo.getYear(), weekAgo.getMonthOfYear(), weekAgo.getDayOfMonth(), 0, 0);
		System.out.println(time);
		System.out.println(time.getMillis());
	}

}
