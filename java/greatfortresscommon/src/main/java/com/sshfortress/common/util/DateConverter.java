package com.sshfortress.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.expression.ParseException;

public class DateConverter implements Converter<String, Date> {    

public Date convert(String source) {    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
    dateFormat.setLenient(false);    
    try {    
        try {
			return dateFormat.parse(source);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
    } catch (ParseException e) {    
        e.printStackTrace();    
    }           
    return null;    
}  
}