package com.athena.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;

import com.alibaba.fastjson.JSON;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeUtil {
	
	private static String parseImage(String url) {
		return "";
	}
	
	private static void generateQrCode(String content) {
		int width = 600;
		int height = 600;
		//文件格式
		String format = "png";
		
		//定义二维码参数
		HashMap hints = new HashMap();
		//编码显示中文
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		//规避错误大小
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		//页面大小宽度
		hints.put(EncodeHintType.MARGIN, 2);
		
		try {
			//生成二维码
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height,hints);
			//生成路径
			Path file = new File("F:/QRCode.png").toPath();
			//MatrixToImageWriter.writeToPath(bitMatrix, format, file);
			ByteArrayOutputStream o = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, format, o);
			byte[] byteArray = o.toByteArray();
			System.out.println(JSON.toJSONString(byteArray));
			System.out.println(byteArray.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		generateQrCode("www.baidu.com");
	}

}
