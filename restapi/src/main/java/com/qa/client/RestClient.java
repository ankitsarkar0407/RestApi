package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	//1. Get MEthod without Headers
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient =  HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //http get request
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget); //Hit the GET URL
		
	
	return closeableHttpResponse;

	}
	
	//1. Get MEthod with Headers 
	public CloseableHttpResponse get(String url, HashMap<String , String> headerMap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient =  HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //http get request
		
		for(Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		
		
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget); //Hit the GET URL
		
	
	return closeableHttpResponse;

}
}
