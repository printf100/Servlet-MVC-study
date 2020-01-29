package com.cal.DAO;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Util {
	
	private String todates;
	
	public String getTodates() {
		return todates;
	}
	
	public void setTodates(String mDate) {
		
		// yyyyMMddmm -> yyyy-MM-dd hh:mm:00
		String m = mDate.substring(0, 4) + "-" +
				mDate.substring(4, 6) + "-" +
				mDate.substring(6, 8) + " " +
				mDate.substring(8, 10) + ":" +
				mDate.substring(10) + ":00";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일 hh시mm분");
		Timestamp tm = Timestamp.valueOf(m);
		todates = sdf.format(tm);
	}
	
	// font color 변경
	public static String fontColor(int date, int dayOfWeek) {
		
		String color = "";
		int tmp = dayOfWeek - 1 + date;
		
		if(tmp % 7 == 1) { // 일요일
			color = "red";
		} else if(tmp % 7 == 0) { // 토요일
			color = "blue";
		} else {
			color = "black";
		}
		
		return color;
	}

	// 한자리수 -> 두자리수
	public static String isTwo(String msg) {
		
		return (msg.length() < 2) ? "0"+msg : msg;
	}
}
