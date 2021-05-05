package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetApiTestAdvance extends TestBase {
	
	TestBase testBase;
	String serviceUrl ;
	String apiUrl ;
	String url;
	RestClient restClient ;
	
	
	CloseableHttpResponse closeableHttpResponse;
	
	
	
	
	
	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		
		url = serviceUrl + apiUrl ;
		
		
	}
	
	@Test(priority = 0)
	public void getAPITest() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(url);
		
		
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code -- > "+statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE__STATUS_CODE_200, "Status code is not 200");
		
		String responsString =  EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		
		
//		JSONObject responseJson = new JSONObject(responsString);
//		System.out.println("Response in Json format --- "+responseJson);
		
		
		
		//Json String
		
		JSONObject responseJson = new JSONObject(responsString);
		
		String perPage = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println(perPage +" ==== Value of s");
		
		
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println(totalValue +" ==== Value of total");
		
		
		//get Value from JSON Array:
		
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String firstname = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		
		System.out.println(lastName + " --- Last name");
		
		System.out.println(id + " --- id");
		System.out.println(avatar + " --- avatar");
		System.out.println(firstname + " --- first name");
		
		
		
		//To get the headers
		Header[] headerArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for(Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Header Array-- > " +allHeaders);
	}
		
	
	@Test(priority = 1)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		
		HashMap<String, String> headers = new HashMap<String, String>();
		
		headers.put("Content-Type", "application/json");
		
		closeableHttpResponse = restClient.get(url,headers);
		
		
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code -- > "+statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE__STATUS_CODE_200, "Status code is not 200");
		
		String responsString =  EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		
		
//		JSONObject responseJson = new JSONObject(responsString);
//		System.out.println("Response in Json format --- "+responseJson);
		
		
		
		//Json String
		
		JSONObject responseJson = new JSONObject(responsString);
		
		String perPage = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println(perPage +" ==== Value of s");
		
		
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println(totalValue +" ==== Value of total");
		
		
		//get Value from JSON Array:
		
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String firstname = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		
		System.out.println(lastName + " --- Last name");
		
		System.out.println(id + " --- id");
		System.out.println(avatar + " --- avatar");
		System.out.println(firstname + " --- first name");
		
		
		
		//To get the headers
		Header[] headerArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for(Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Header Array-- > " +allHeaders);
	}
		
	
	

}
