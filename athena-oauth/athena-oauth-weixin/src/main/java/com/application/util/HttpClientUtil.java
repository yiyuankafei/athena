package com.application.util;




import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class HttpClientUtil {
	
	public static String doGet(String url) {
		
		String webContent = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			
			CloseableHttpClient getClient = HttpClients.createDefault();
			
			CloseableHttpResponse getResponse = getClient.execute(httpGet);
			
			HttpEntity entity = getResponse.getEntity();
	        webContent = EntityUtils.toString(entity, "UTF-8");
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return webContent;
	}
}

