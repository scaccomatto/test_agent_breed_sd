package com.simudynetest.utils;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.ParseBool;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.simudynetest.model.Agent;
/**
 * This is one of the many library for parsing CSV
 * I've get the code directly from their web: https://super-csv.github.io/super-csv/examples_reading_variable_cols.html
 * 
 * */

public class CsvFileReader  extends InputFileReader{
	
public static List<Agent> readWithCsvBeanReader(BufferedReader br) throws Exception {
        
        ICsvBeanReader beanReader = null;
        List<Agent> agents = new ArrayList<Agent>();
        try {
                beanReader = new CsvBeanReader(br, CsvPreference.STANDARD_PREFERENCE);
                
                // the header elements are used to map the values to the bean (names must match)
                final String[] header = beanReader.getHeader(true);
                final CellProcessor[] processors = getProcessors();
                
                Agent agent;
                while( (agent = beanReader.read(Agent.class, header, processors)) != null ) {
                	agents.add(agent);
                }
                
        }
        finally {
                if( beanReader != null ) {
                        beanReader.close();
                }
        }
        
        return agents;
	}
	
	private static CellProcessor[] getProcessors() {
        
        final CellProcessor[] processors = new CellProcessor[] { 
                new NotNull(), // Agent_Breed
                new ParseLong(), // Policy_ID
                new ParseInt(), // age
                new ParseInt(), // Social_Grade
                new ParseDouble(), // Payment_at_Purchase
                new ParseDouble(), // Attribute_Brand
                new ParseDouble(), // Attribute_Price
                new ParseDouble(),// Attribute_Promotions
                new ParseBool(),// Auto_Renew
                new ParseInt()//Inertia_for_Switch
        };
        
        return processors;
}

}
