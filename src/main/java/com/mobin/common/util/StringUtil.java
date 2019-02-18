package com.mobin.common.util;

import java.util.Random;

public class StringUtil {
	
	/**
	 * @param str 문자열
	 * @return 
	 * str이 null 또는 length가 0이라면 true, 아니라면 false
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
	/**
	 * @param str 문자열
	 * @return 
	 * str이 null이 아니고 length가 0보다 크다면 true, 아니라면 false
	 */
	public static boolean isNotEmpty(String str) {
		return (str != null && str.length() > 0);
	}
	
	/**
	 * @param str 문자열
	 * @param strict 공백을 제거한 후 검사를 진행할 것인지 여부
	 * @return 
	 * strict가 true라면 str의 공백을 제거하고 검사 진행, false라면 isEmpty(String str)과 동일
	 * 
	 */
	public static boolean isEmpty(String str, boolean strict) {
		boolean isEmpty = isEmpty(str);
		if(!strict || isEmpty) return isEmpty;
		else str = str.replaceAll("\\s", "");
		return isEmpty(str);
	}

	/**
	 * @author 김준혁
	 * @param source 랜덤으로 나타낼 문자 배열
	 * @param loop 랜덤 문자열의 길이
	 * @return 
	 * source에 있는 문자 배열을 loop번 만큼 선택함 
	 */
	public static String randomString(char[] source, int loop) {
		Random random = new Random();
		String result = "";
		for(int i=0; i<loop; i++)
			result += source[random.nextInt(source.length)];
		return result;
	}
	
	public static String ifNull(String str, String str2){
		if ( str == null )	return str2;
		else if ( str == "null" )	return str2;
		else if ( str == "" )	return str2;
		else	return str;
	}	
	public static String isNull(String str){
		if ( str == null )	return "";
		else if ( str == "null" )	return "";
		else	return str;
  	}
	public static String isSpace(String str){
		if (StringUtil.isNull(str).equals(""))	return null;
		else	return str;
  	}
	public static String isNotNull(String str, String prestr){
		if ( str == null )	return "";
		else if ( str == "null" )	return "";
		else	return prestr+str;
  	}	 	
	public static String isNullZero(String str){
		if ( str == null || str.equals("") || str.equals("null"))	return "0";
		else	return str;
  	}	
  	public static String isNullReplace(String str, String rep){
		if ( str == null || str.equals("") || str.equals("null"))	return rep;
		else	return str;
  	}
  	public static String isNullMax(String str){
		if ( str == null || str.equals(""))	return "9999";
		else	return str;
  	}
  	public static String addComma(int num) {

        String str = num + "";
        StringBuffer sb = new StringBuffer(str);
        StringBuffer rsb = new StringBuffer();
        sb = sb.reverse();
        int p = 0;

        for (int i = 0; i < str.length(); i++) {
            p = i % 3;

            if (i > 0)
                if (p == 0)
                    rsb.append(",");

            rsb.append(sb.substring(i, (i + 1)));
        }

        return (rsb.reverse()).toString();
    }
}
