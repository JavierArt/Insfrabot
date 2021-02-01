package com.javier.firsttelegrambot;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;
import com.jayway.jsonpath.JsonPath;

public class JsonReader {

	private int randomNumber(int sizeOfArray)
	{
		int random_int = (int)(Math.random() * (sizeOfArray - 0 + 1) + 0);
		return random_int;
	}
	
	public String randomPhrase()
	{
		 JSONParser parser = new JSONParser();
		 String fr = "Si eres bueno en algo no lo hagas gratis";
		 try {
			 	Reader reader = new FileReader("C:\\Users\\javie\\eclipse-workspace\\com.javier.firsttelegrambot\\src\\main\\resources\\frases.json");
			 	JSONObject jsonObject = (JSONObject) parser.parse(reader);
	            JSONArray msg = (JSONArray) jsonObject.get("Phrase");
	            String process = msg.get(randomNumber(msg.size())).toString().replace("{", "").replace("}", "").replace(":", "-").replace("\"", "").replace("frase", "");
	            fr = process;
		 }
		 catch (IOException e) {
			 e.printStackTrace();
	     } 
		 catch (ParseException e) {
	         e.printStackTrace();
	     }
		return fr;
	}
	
	public List<String> search(String qry) throws FileNotFoundException, IOException, ParseException
	{
		String json = "C:\\Users\\javie\\eclipse-workspace\\com.javier.firsttelegrambot\\src\\main\\resources\\frases.json";
		JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(json));
		List<String> frases = JsonPath.read(obj, "$.Phrase[?(@.frase =~ /^"+qry+".*/i)].frase");
		return frases;
	}
		
	public List<InlineQueryResult> getInlineQueryResults(String qry) throws FileNotFoundException, IOException, ParseException
	{
		List<InlineQueryResult> arr = new ArrayList<InlineQueryResult>();
		List<String> list = search(qry);
		int cont=0;
		for(String el:list)
		{
			arr.add(new InlineQueryResultArticle(String.valueOf(cont),el,new InputTextMessageContent(el)));
			cont++;
		}
		return arr;
	}
	
}
