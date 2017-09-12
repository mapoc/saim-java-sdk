package com.shop.sdk;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestHouseholds {
	
	private static final String BASE_URL = "https://apisb.shop.com/saim/v1";
	private static final String API_KEY = "b16e9f1a1c27456ea87e2b272f44bca1";
	
	private static String householdId = "1025";
	
	@Test
	public void test1PostHouseHolds() throws IOException {
		JSONObject json = new JSONObject();
		
		json.put("first_name", "John")
			.put("last_name", "Doe")
			.put("address", "Cardboard Box #3")
			.put("email", "homeless@nowhere.com")
			.put("primary_phone", "(123) 456-7890");
		
		HttpURLConnection connection = null;
		
		URL url = new URL(BASE_URL + "/households");
		connection = (HttpURLConnection) url.openConnection();
		 
		connection.setRequestMethod("POST");
		
		connection.setRequestProperty("Accept", 
			    "application/json");
		connection.setRequestProperty("Content-Type", 
		    "application/json");
		connection.setRequestProperty("api_key", 
				API_KEY);
		connection.setUseCaches(false);
		connection.setDoOutput(true);
		    
	    // Send in the data
	    OutputStreamWriter requestWriter = null;
		    
		requestWriter = new OutputStreamWriter(connection.getOutputStream());
		requestWriter.write(json.toString());
		requestWriter.flush();

		
		// Read the response
	    int responseCode = 0;
		BufferedReader responseReader = null;
    		responseCode = connection.getResponseCode();
		    		
    		if (responseCode >= 200 && responseCode < 300) {
    			responseReader = new BufferedReader(
			    		new InputStreamReader(connection.getInputStream()));
    		} else {
    			responseReader = new BufferedReader(
			    		new InputStreamReader(connection.getErrorStream()));
    		}
		
	    	String responseData = new BufferedReader(responseReader)
	    			  .lines()
	    			  .collect(Collectors.joining("\n"));
	    	
	    	
	    	
	    	requestWriter.close();
	    	responseReader.close();
	    	connection.disconnect();
	    	
	    	System.out.println("Printing out output");
	    	
		System.out.println(responseData);
	    	
	    	// Test the result
	    	Assert.assertEquals(201, responseCode);
	    	
	    	// Get the ID to be used for the next test
	    	JSONObject responseJsonData = new JSONObject(responseData);
	    	householdId = responseJsonData.getString("id");
	    	
	}
	
	@Test
	public void testGetHouseholds() throws IOException {
		HttpURLConnection connection = null;
		
		URL url = new URL(BASE_URL + "/households/" + householdId);
		connection = (HttpURLConnection) url.openConnection();
		 
		connection.setRequestMethod("GET");
		
		connection.setRequestProperty("Accept", 
			    "application/json");
		connection.setRequestProperty("api_key", 
				API_KEY);
		connection.setUseCaches(false);
		
		// Read the response
	    int responseCode = 0;
		BufferedReader responseReader = null;
    		responseCode = connection.getResponseCode();
		    		
    		if (responseCode >= 200 && responseCode < 300) {
    			responseReader = new BufferedReader(
			    		new InputStreamReader(connection.getInputStream()));
    		} else {
    			responseReader = new BufferedReader(
			    		new InputStreamReader(connection.getErrorStream()));
    		}
		
	    	String responseData = new BufferedReader(responseReader)
	    			  .lines()
	    			  .collect(Collectors.joining("\n"));
	    	
	    	responseReader.close();
	    	connection.disconnect();
	    	
	    	System.out.println("Printing response information:");
	    System.out.println(responseData);
	    	
	    	
	    	// Test the result
	    	Assert.assertEquals(200, responseCode);
	}
	
	@Test
	public void testPutHouseholds() throws IOException {
		JSONObject json = new JSONObject();
		
		json.put("last_name", "Smith")
			.put("first_name", "Randy")
			.put("address", "House, City, ST 12345")
			.put("primary_phone", "336-888-8888")
			.put("email", "me@somewhere.com");
		
		HttpURLConnection connection = null;
		
		URL url = new URL(BASE_URL + "/households/" + householdId);
		connection = (HttpURLConnection) url.openConnection();
		 
		connection.setRequestMethod("PUT");
		
		connection.setRequestProperty("Accept", 
			    "application/json");
		connection.setRequestProperty("Content-Type", 
		    "application/json");
		connection.setRequestProperty("api_key", 
				API_KEY);
		connection.setUseCaches(false);
		connection.setDoOutput(true);
		
		// Send in the data
	    OutputStreamWriter requestWriter = null;
		    
		requestWriter = new OutputStreamWriter(connection.getOutputStream());
		requestWriter.write(json.toString());
		requestWriter.flush();

		
		// Read the response
	    int responseCode = 0;
		BufferedReader responseReader = null;
    		responseCode = connection.getResponseCode();
		    		
    		if (responseCode >= 200 && responseCode < 300) {
    			responseReader = new BufferedReader(
			    		new InputStreamReader(connection.getInputStream()));
    		} else {
    			responseReader = new BufferedReader(
			    		new InputStreamReader(connection.getErrorStream()));
    		}
		
	    	String responseData = new BufferedReader(responseReader)
	    			  .lines()
	    			  .collect(Collectors.joining("\n"));
	    	
	    	
	    	
	    	requestWriter.close();
	    	responseReader.close();
	    	connection.disconnect();
	    	
	    	System.out.println("Printing out output");
	    	
		System.out.println(responseData);
	    	
	    	// Test the result
	    	Assert.assertEquals(200, responseCode);
		
	}
	
	@Test
	public void testPutHouseholdsSubset() throws IOException {
		JSONObject json = new JSONObject();
		
		json.put("primary_phone", "111-222-3344")
			.put("email", "testing@test.com");
		
		HttpURLConnection connection = null;
		
		URL url = new URL(BASE_URL + "/households/" + householdId);
		connection = (HttpURLConnection) url.openConnection();
		 
		connection.setRequestMethod("PUT");
		
		connection.setRequestProperty("Accept", 
			    "application/json");
		connection.setRequestProperty("Content-Type", 
		    "application/json");
		connection.setRequestProperty("api_key", 
				API_KEY);
		connection.setUseCaches(false);
		connection.setDoOutput(true);
		
		// Send in the data
	    OutputStreamWriter requestWriter = null;
		    
		requestWriter = new OutputStreamWriter(connection.getOutputStream());
		requestWriter.write(json.toString());
		requestWriter.flush();

		
		// Read the response
	    int responseCode = 0;
		BufferedReader responseReader = null;
    		responseCode = connection.getResponseCode();
		    		
    		if (responseCode >= 200 && responseCode < 300) {
    			responseReader = new BufferedReader(
			    		new InputStreamReader(connection.getInputStream()));
    		} else {
    			responseReader = new BufferedReader(
			    		new InputStreamReader(connection.getErrorStream()));
    		}
		
	    	String responseData = new BufferedReader(responseReader)
	    			  .lines()
	    			  .collect(Collectors.joining("\n"));
	    	
	    	
	    	
	    	requestWriter.close();
	    	responseReader.close();
	    	connection.disconnect();
	    	
	    	System.out.println("Printing out output");
	    	
		System.out.println(responseData);
	    	
	    	// Test the result
	    	Assert.assertEquals(200, responseCode);
	}
	
	@Test
	public void testPostHouseholdsMissingAddressBadEmail() throws IOException {
		JSONObject json = new JSONObject();
		
		json.put("last_name", "John")
			.put("first_name", "Doe")
			.put("email", "homeless@nowhere")
			.put("primary_phone", "(123) 456-7890");
		
		HttpURLConnection connection = null;
		
		URL url = new URL(BASE_URL + "/households/" + householdId);
		connection = (HttpURLConnection) url.openConnection();
		 
		connection.setRequestMethod("PUT");
		
		connection.setRequestProperty("Accept", 
			    "application/json");
		connection.setRequestProperty("Content-Type", 
		    "application/json");
		connection.setRequestProperty("api_key", 
				API_KEY);
		connection.setUseCaches(false);
		connection.setDoOutput(true);
		
		// Send in the data
	    OutputStreamWriter requestWriter = null;
		    
		requestWriter = new OutputStreamWriter(connection.getOutputStream());
		requestWriter.write(json.toString());
		requestWriter.flush();

		
		// Read the response
	    int responseCode = 0;
		BufferedReader responseReader = null;
    		responseCode = connection.getResponseCode();
		    		
    		if (responseCode >= 200 && responseCode < 300) {
    			responseReader = new BufferedReader(
			    		new InputStreamReader(connection.getInputStream()));
    		} else {
    			responseReader = new BufferedReader(
			    		new InputStreamReader(connection.getErrorStream()));
    		}
		
	    	String responseData = new BufferedReader(responseReader)
	    			  .lines()
	    			  .collect(Collectors.joining("\n"));
	    	
	    	
	    	
	    	requestWriter.close();
	    	responseReader.close();
	    	connection.disconnect();
	    	
	    	System.out.println("Printing out output");
	    	
		System.out.println(responseData);
	    	
	    	// Test the result
	    	Assert.assertEquals(400, responseCode);
	}
	
	
}