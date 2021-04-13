package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	//1. Get MEthod
	public void get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient =  HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //http get request
		CloseableHttpResponse closabelCloseableHttpResponse = httpClient.execute(httpget); //Hit the GET URL
		int statusCode = closabelCloseableHttpResponse.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		
		String responsString =  EntityUtils.toString(closabelCloseableHttpResponse.getEntity(),"UTF-8");
		
		
		JSONObject responseJson = new JSONObject(responsString);
		System.out.println("Response in Json format --- "+responseJson);
		
		//To get the headers
		Header[] headerArray = closabelCloseableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for(Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Header Array-- > " +allHeaders);
	}

}
