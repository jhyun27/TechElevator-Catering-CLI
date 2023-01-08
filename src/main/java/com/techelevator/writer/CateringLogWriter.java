package com.techelevator.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;



public class CateringLogWriter {
	
	private final static String FILE_NAME = "Log.txt";
	
	public void write(List<String> logList)  {
		
		File outputFile = new File(FILE_NAME);
		
		try (FileWriter writer = new FileWriter(outputFile, true);
				BufferedWriter buffered = new BufferedWriter(writer)) {
			
			for (String s : logList) {
				buffered.write(s + System.getProperty("line.separator"));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
