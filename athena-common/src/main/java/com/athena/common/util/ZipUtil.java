package com.athena.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ZipUtil {
	
	public static void unzipFile(File originFile, String targetDir) throws Exception {
		log.info("解压文件:{}", originFile.getAbsolutePath());
		try (ZipFile zipFile = new ZipFile(originFile)) {
			Enumeration<?> entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				if (entry.isDirectory()) {
	                String dirPath = targetDir +  File.separator + entry.getName();
	                File file = new File(dirPath);
	                if(! file.exists()) {
	                	file.mkdirs();
	                }
	            } else {
	                File targetFile = new File(targetDir +  File.separator + entry.getName());
	                if(! targetFile.getParentFile().exists()) {
	                	targetFile.getParentFile().mkdirs();
	                }
	                targetFile.createNewFile();
	                try (InputStream inputStream = zipFile.getInputStream(entry); 
	                	 FileOutputStream fileOutputStream = new FileOutputStream(targetFile)) {
	                    int lenth;
	                    byte[] buffer = new byte[1024];
	                    while ((lenth = inputStream.read(buffer)) != -1) {
	                    	fileOutputStream.write(buffer, 0, lenth);
	                    }
	                }
	            }
			}
			log.info("解压完成");
		} 
		
	}
}
