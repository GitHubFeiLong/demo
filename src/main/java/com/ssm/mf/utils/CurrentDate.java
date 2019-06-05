/**
 * 
 */
package com.ssm.mf.utils;

import java.util.Calendar;

/**   获取当前年、当前月
 * @ClassName:  CurrentYear   
 * @Description:TODO   
 * @author: cfl
 * @date:  2019年5月31日 上午11:33:43     
 */
public class CurrentDate {
	
	public static Integer getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return year;
	}
	
	public static Integer getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH )+1;
		return month;
	}

}
