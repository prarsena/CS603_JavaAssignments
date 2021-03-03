package assignment02;

import java.net.URI;
import java.net.http.*;
import org.json.*;
import java.util.Scanner;

public class StockTickerWebRequest {

	public static void main(String[] args) throws Exception {
		final String[] key = apikeys();
		
		Scanner kbd = new Scanner(System.in);
		String stockTicker = "";
		while (!stockTicker.equals("EXIT")) {
			
			System.out.print("Enter the stock ticket symbol: ");
			stockTicker = kbd.nextLine().toUpperCase();
			
			if (stockTicker.equals("EXIT")) {
				System.out.println("Exiting Program..");
			} 
			else {
				final String URI_COMPANY_INFO = "https://www.alphavantage.co/query?function=OVERVIEW&symbol=" + stockTicker + "&apikey=" + key[0];
				final HttpResponse<String> COMPANY_INFO = getWebsite(URI_COMPANY_INFO);
				final String URI_STOCK_PRICE = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + stockTicker + "&apikey=" + key[1];
				final HttpResponse<String> COMPANY_STOCK_PRICE = getWebsite(URI_STOCK_PRICE);
				
				if (!COMPANY_INFO.body().equals("{}")) {
					final JSONObject infoObj = new JSONObject(COMPANY_INFO.body());
					String name = infoObj.getString("Name");
					double DividendPerShare = infoObj.getDouble("DividendPerShare");
					System.out.println("Name: " + name);
					System.out.println("Annual Dividend Per Share: " + (DividendPerShare * 4));
					
					final JSONObject priceObj = new JSONObject(COMPANY_STOCK_PRICE.body());
					double stockPrice = priceObj.getJSONObject("Global Quote").getDouble("08. previous close");
					System.out.println("Quote: " + stockPrice);
					
				} else {
					System.out.println("Invalid stock ticker");
				}	
			}
		}
		kbd.close();
		System.out.println("Program complete");
	}

	public static String[] apikeys() {
		/*
		 * API rate limit is 5 requests per minute. 
		 * I needed two API keys because I make 2 API calls per each company.
		 */
		
		String keys[] = new String[2];
		keys[0] = "TPIXIMJRZ3HMYB81";
		keys[1] = "ZLL7SMH9WTV8UKAV";
		return keys;
	}

	public static HttpResponse<String> getWebsite(String uri) throws Exception {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).header("accept", "application/json")
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return response;
	}

}
