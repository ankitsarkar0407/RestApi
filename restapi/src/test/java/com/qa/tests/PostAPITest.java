
/*
 * Naveen Automation Labs HTTPClient Post call class
 * URL for Video:
 * https://www.youtube.com/watch?v=kV8UKPh-HBM&list=RDCMUCXJKOPxx4O1f63nnfsoiEug&start_radio=1&t=3433s&ab_channel=NaveenAutomationLabs
 * Title: Automate Rest POST Call using HTTP Client - Rest API Automation - Part-5
 */

package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends TestBase {
	

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
	
	@Test
	public void postApiTest() throws JsonGenerationException, JsonMappingException, IOException {
		restClient  = new RestClient();
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		
		//Jackson API:
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("morpheus", "Leader"); //Expected User Object
		
		//Object to JSON file
		mapper.writeValue(new File("C:\\Users\\Anke\\git\\RestApi\\restapi\\src\\main\\java\\com\\qa\\data\\users.json"), users);
		
		//Object to Json in String
		String userJsonString = mapper.writeValueAsString(users);
		System.out.println(userJsonString);
		
		closeableHttpResponse = restClient.post(url, userJsonString, headers);
		
		//Status Code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		
		Assert.assertEquals(statusCode, RESPONSE__STATUS_CODE_201);
		
		//Json String 
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The Response from API is : " + responseJson);
		
		// Json to JAva Object
		
		Users userRespObj = mapper.readValue(responseString, Users.class);  // Actual user object
		
		System.out.println(userRespObj);
		Assert.assertEquals(users.getName(), userRespObj.getName());
		Assert.assertEquals(users.getJob(), userRespObj.getJob());
		
		
		
	}

}
