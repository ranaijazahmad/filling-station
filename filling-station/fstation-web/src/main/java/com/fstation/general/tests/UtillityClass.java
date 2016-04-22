package com.fstation.general.tests;

import java.io.File;
import java.util.ArrayList;

public class UtillityClass
{
	public static void main (String []args){
		/*ArrayList<String> removeList = new ArrayList<String>();
		String text = new XMLUnitTest().readFileContents(new File("E:/expected.xml"));
		try {			
			if (text.indexOf("<!--") != -1) {
				String[] textArray = text.split(java.util.regex.Pattern.quote("<!--"));
				if(textArray != null && textArray.length > 0){
					for(int index = 1; index < textArray.length; index++){
						
						int beginIndex = textArray[index].indexOf("<")+1;
						int endIndex = textArray[index].indexOf(">");
						removeList.add(textArray[index].substring(beginIndex, endIndex));
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error removing unwanted text in '<-- ... -->'. Did you forget to close the unwanted area?");
		}*/
		
		System.out.println("CRSCHEMA: "+System.getProperty("CRSCHEMA"));
		System.out.println("DBHOST: "+System.getProperty("DBHOST"));
		System.out.println("SID: "+System.getProperty("SID"));
		System.out.println("TPASCHEMA: "+System.getProperty("TPASCHEMA"));
		
		String url = "jdbc:oracle:thin:@"+System.getProperty("DBHOST")+":1522:"+System.getProperty("SID");
	}
}
