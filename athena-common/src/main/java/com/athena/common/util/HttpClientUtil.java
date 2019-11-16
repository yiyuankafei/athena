package com.athena.common.util;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class HttpClientUtil {
	
	public static String doGet(String url) {
		
		String webContent = null;
		try {
			CloseableHttpClient getClient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);
			
			CloseableHttpResponse getResponse = getClient.execute(httpGet);
			HttpEntity entity = getResponse.getEntity();
	        webContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return webContent;
	}
	
	public static String doPostXml(String url, String xml) {
		
		String webContent = null;
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			StringEntity stringEntity = new StringEntity(xml);
			httpPost.setEntity(stringEntity);
			httpPost.setHeader("Content-type", "text/xml;charset=UTF-8");
			
			CloseableHttpResponse getResponse = client.execute(httpPost);
			HttpEntity entity = getResponse.getEntity();
	        webContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return webContent;
	}
}

