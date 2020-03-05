package com.athena.common.test.response;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.athena.common.util.XmlUtil;

public class Test {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Administrator\\Desktop\\xx.txt")));
		StringBuilder builder = new StringBuilder();
		String s = null;
		while ((s = r.readLine()) != null) {
			builder.append(s).append("\n");
		}
		String xml = builder.toString();
		
		
		QueryResponse bean = XmlUtil.xmlToBean(xml, QueryResponse.class);
		System.out.println(bean);
	}

}
