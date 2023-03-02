package com.example.demo.config.utils;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperUtils {

	private static final ObjectMapper instance = new ObjectMapper();

	public static <T> T parseToObject(String str, Class<T> t) throws JsonMappingException, JsonProcessingException {
		return instance.readValue(str, t);
	}
	
	public static <T> List<T> parseToListObject(String str, Class<T[]> t) throws JsonMappingException, JsonProcessingException{
		
		T[] objectArray = null; 
		objectArray = instance.readValue(str, t);
		return Arrays.asList(objectArray);
	}
	
	public static String parseToString(Object o) throws JsonProcessingException {
		return instance.writeValueAsString(o);
	}
	
}
