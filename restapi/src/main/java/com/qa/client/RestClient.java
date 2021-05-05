package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
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
	
	//2. Get MEthod with Headers 
	public CloseableHttpResponse get(String url, HashMap<String , String> headerMap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient =  HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //http get request
		
		for(Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		
		
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget); //Hit the GET URL
		
	
	return closeableHttpResponse;

	}
	
	//3 Post method
	public CloseableHttpResponse post(String Url, String entityString , HashMap <String , String> headerMap) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient =  HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(Url); //http post request
		httpPost.setEntity(new StringEntity(entityString)); // for payload
		
		//for Headers 
		for(Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		
		
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost); //Hit the GET URL
		
	
	return closeableHttpResponse;
		
	}
}
