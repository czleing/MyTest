package com.my.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class MyTest {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
//		for(int i=41;i<=90;i++){
//			System.out.print(" else if(\"objVal"+i+"\" == valName) {\n\treturn objVal"+i+";\n}");
//		}
		//System.out.println((Math.round(12345678/100.0))/100.0);
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		//Date date = sdf.parse("201604");
		//System.out.println(date.toLocaleString());
		//Map<String,Object> map = new HashMap<String,Object>();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//Date date = sdf.parse("2017-03-24 24:00:00");
		//System.out.println("S-"+UUID.randomUUID().toString().toUpperCase());
		/*Map<String,Object> map = new HashMap<String, Object>();
		map.put("price", 299.00);
		Double d = new Double(""+map.get("price"));
		System.out.println(d == 299.00);*/
		//System.out.println(new Integer(1) != 1);
		
		/*Student d = new Student();
		d.name = "aaa";
		Map<String,Student> m = new HashMap<String, Student>();
		m.put("s", d);
		Student s = m.get("s");
		s.name = "bbb"; 
		System.out.println(d.name);*/
		
		//System.out.println(new Integer(1) == new Integer(1));
		BigDecimal b = new BigDecimal("168.00");
		System.out.println(b.equals(new BigDecimal("168.00")));
	}
	public static List<Date[]> method1(Date[] beginDates,Date[] endDates,Date[] newBeginDates,Date[] newEndDates,int type){
		List<Date[]> dl = new ArrayList<Date[]>();
		for(int i=0,j=newBeginDates.length;i<j;i++){
			if(type == 1){//并集
				//
			} else if (type == 2){//交集
				
			}
		}
		return dl;
	}
	
}
