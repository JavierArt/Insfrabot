package com.javier.firsttelegrambot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Insfrabot extends TelegramLongPollingBot{

	public void onUpdateReceived(Update update) {
		JsonReader json = new JsonReader();
		// We check if the update has an inline query
		if(update.hasInlineQuery())
		{
			InlineQuery query = update.getInlineQuery();
			List<InlineQueryResult> arr;
			try {
				arr = json.getInlineQueryResults(query.getQuery());
				//arr = new ArrayList<InlineQueryResult>(Arrays.asList(new InlineQueryResultArticle("1","hola",new InputTextMessageContent("hola")), new InlineQueryResultArticle("2","adios",new InputTextMessageContent("adios") )));
				AnswerInlineQuery answer = new AnswerInlineQuery(update.getInlineQuery().getId(), arr);
		        try {
		            execute(answer); // Call method to send the message
		        } catch (TelegramApiException e) {
		            e.printStackTrace();
		        }
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
		/*else
		{
			if (!update.hasInlineQuery()) {
				System.out.println("why");
				SendMessage answer = new SendMessage();
			    answer.setChatId(update.getMessage().getChatId().toString());
			    answer.setText(json.randomPhrase()); 
		        try {
		            execute(answer); // Call method to send the message
		        } catch (TelegramApiException e) {
		            e.printStackTrace();
		        }
			}
		}*/
	}

	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "Insfrabot";
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "a token goes here";
	}

}
