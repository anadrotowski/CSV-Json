package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.*;
import java.util.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConverterTest {
    private String csvString;
    private String jsonString;
	private Converter conv; 
    private static String line; 

    /*private static String readFile(String string) throws IOException{
        File file = new File(string);
        Scanner scanner = new Scanner(file);
        String lineSeparator = System.getProperty("line.separator");

        try{
            while(scanner.hasNextLine()){
                line = line + scanner.nextLine() + lineSeparator; 
            }
            return line;
        }
        finally{
            scanner.close();
        }
    }*/

    @Before
    public void setUp() {
		conv = new Converter();
        try{
            csvString = readFile("src/test/resources/grades.csv");
            jsonString = readFile("src/test/resources/grades.json");
        }
        catch(FileNotFoundException e){}
    }
    
    @Test
    public void testConvertCSVtoJSON() {
        // You should test using the files in src/test/resources.
        assertTrue(false);
    }

    @Test
    public void testConvertJSONtoCSV() {
        // You should test using the files in src/test/resources.
        assertTrue(false);
    }
}







