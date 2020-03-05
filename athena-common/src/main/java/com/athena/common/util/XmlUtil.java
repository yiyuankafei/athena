package com.athena.common.util;

import java.io.IOException;

import org.dom4j.DocumentException;

import com.athena.common.test.request.OrderRequest;
import com.athena.common.test.request.base.AvailabilityRequest;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XmlUtil {
	
	private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
	
	public static  String beanToXml(Object object) {
		XStream xStream = new XStream();
        xStream.processAnnotations(object.getClass());
        xStream.aliasSystemAttribute(null, "class");
        String xml = xStream.toXML(object);
        return XML_HEADER + xml;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T xmlToBean(String xml, Class<T> clazz) {
		XStream xstream = new XStream(new DomDriver());
        xstream.processAnnotations(clazz);
        return (T) xstream.fromXML(xml);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws IOException, DocumentException {
		
		OrderRequest request = new OrderRequest();
		request.setReqType("hotel_price");
		request.setAdult(1);
		request.setCheckInDate("2020-03-05");
		request.setCheckOutDate("2020-03-06");
		request.setChildren(2);
		request.setChildrenAge("7,11");
		request.setCityID(3);
		request.setHotelID("20139901");
		request.setNationality("CN");
		request.setRoomCount(1);
		
		AvailabilityRequest baseRequest = new AvailabilityRequest("AO987DJ37F8A9DF8D6ALOP019410CJS", request);
		
		String xml = beanToXml(baseRequest);
		System.out.println(xml);
		
		/*BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Administrator\\Desktop\\xx.txt")));
		StringBuilder builder = new StringBuilder();
		String s = null;
		while ((s = r.readLine()) != null) {
			builder.append(s).append("\n");
		}
		String xml = builder.toString();
		
		Document document = DocumentHelper.parseText(xml);
		Element rootElement = document.getRootElement();
		Element element = rootElement.element("HotelRequest");
		element.addAttribute("class", OrderRequest.class.toString());
		System.out.println(document.asXML());
		
		AvailabilityRequest bean = xmlToBean(xml, AvailabilityRequest.class);
		System.out.println(bean);*/
	}

}
