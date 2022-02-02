package com.my.gn.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomUtil {

	/**
	 * <pre>
	 * 1. 개요 : 뺄셈계산
	 * 2. 처리내용 :
	 * </pre>
	 *	@since 2020. 2. 6.
	 * @param str1
	 * @param str2
	 * @return  str1 - str2 
	 */
	public static String calculateSub(String str1, String str2){
		int result = Integer.parseInt(str1) - Integer.parseInt(str2);
		return String.valueOf(result);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 날짜계산
	 * 2. 처리내용 :
	 * </pre>
	 *	@since 2020. 2. 10.
	 * @param date1
	 * @param date2
	 * @return date1-date2
	 */
	public static int calculateDate(String date1, String date2, String dateType){
	    try{ 
	        SimpleDateFormat format = new SimpleDateFormat(dateType);
	        Date FirstDate = format.parse(date1);
	        Date SecondDate = format.parse(date2);
	        
	        long calDate = FirstDate.getTime() - SecondDate.getTime(); 
	        
	        int calDateDays = (int) (calDate / ( 24*60*60*1000)); 
	 
	        //System.out.println("두 날짜의 날짜 차이: "+calDateDays);
	        return calDateDays;
	    }catch(ParseException e){
            // 예외 처리
        }
	    return 0;
	}
	/**
	 * <pre>
	 * 1. 개요 : 특수문자 제거
	 * 2. 처리내용 :
	 * </pre>
	 *	@since 2020. 2. 11.
	 * @param str
	 * @return
	 */
	public static String isRegx(String str){
		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
		return str.replaceAll(match, "");
	}
}
