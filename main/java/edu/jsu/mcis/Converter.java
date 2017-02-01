package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Converter {
    /*
        Consider a CSV file like the following:
        
        ID,Total,Assignment 1,Assignment 2,Exam 1
        111278,611,146,128,337
        111352,867,227,228,412
        111373,461,96,90,275
        111305,835,220,217,398
        111399,898,226,229,443
        111160,454,77,125,252
        111276,579,130,111,338
        111241,973,236,237,500
        
        The corresponding JSON file would be as follows (note the curly braces):
        
        {
            "colHeaders":["Total","Assignment 1","Assignment 2","Exam 1"],
            "rowHeaders":["111278","111352","111373","111305","111399","111160","111276","111241"],
            "data":[[611,146,128,337],
                    [867,227,228,412],
                    [461,96,90,275],
                    [835,220,217,398],
                    [898,226,229,443],
                    [454,77,125,252],
                    [579,130,111,338],
                    [973,236,237,500]
            ]
        }  
    */
    
    @SuppressWarnings("unchecked")
    public static String csvToJson(String csvString) {
        JSONObject json = new JSONObject();
		JSONArray data = new JSONArray();
		JSONArray columnHeaders = new JSONArray();
		JSONArray rowHeaders = new JSONArray();
		
		columnHeaders.add("Total");
		columnHeaders.add("Assignment 1");
		columnHeaders.add("Assignment 2");
		columnHeaders.add("Exam 1");
		
		json.put("columnHeaders", columnHeaders);
		json.put("rowHeaders", rowHeaders);
		json.put("data", data);
		
		CSVParser parser = new CSVParser();
		BufferedReader reader = new BufferedReader(new StringReader(csvString));
		try{
			String line = reader.readline();
			while((line = reader.readline()) != null){
				String[] pString = parser.parseLine(line);
				rowHeaders.add(pString[0]);
				JSONArray row = new JSONArray();
				row.add(new Long(pString[1]));
				row.add(new Long(pString[2]));
				row.add(new Long(pString[3]));
				row.add(new Long(pString[4]));
				data.add(row);
			}
			
		}
		catch(IOException e) {}
		
		return json.toString();
    }
    
    public static String jsonToCsv(String jsonString) {
       JSONObject json = null;
	   
	   try {
		   JSONParser jParser = new JSONParser();
		   json = (JSONObject) jParser.parse(jsonString);
	   }
		 
		catch (Exception e) {}
		
		String csv = "\"ID"\"," + Converter.<String>joinArray((JSONArray) json.get("colHeaders")) + "\n";
		
		JSONArray rowHeader = (JSONArray) json.get("rowHeaders");
		JSONArray data = (JSONArray) json.get("data");
		
		for (int i = 0; i < rowHeader.size(); i++) {
			csv = (csv + "\"" + (String)rowHeader.get(i) + "\"" + Converter.<Long>joinArray((JSONArray) data.get(i)) + "\n");
		}
		return csv;
    }
	
	
	@SuppressWarnings("unchecked")
	private static <T> String joinArray(JSONArray array) {
        String line = "";
        for (int i = 0; i < array.size(); i++) {
            line = (line + "\"" + ((T) array.get(i)) + "\"");
            if (i < array.size() - 1) {
                line = line + ",";
            }
        }
        return line;
    }
}













