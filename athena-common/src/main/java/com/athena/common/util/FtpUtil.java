package com.athena.common.util;

import java.io.File;
import java.io.FileOutputStream;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import com.athena.common.config.FtpConfig.Ftp;

@Slf4j
public class FtpUtil {
	
	public static void downloadFile(Ftp ftp) throws Exception {
		
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect(ftp.getUrl(), ftp.getPort());
		ftpClient.setControlEncoding(ftp.getCharset());
		boolean login = ftpClient.login(ftp.getUser(), ftp.getPassword());
		log.info("FTP连接状态:{}", login);
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		FTPFile[] ftpFiles = ftpClient.listFiles("/");
		log.info("文件个数:{}", ftpFiles.length);
		
		for (FTPFile ftpFile : ftpFiles) {
			log.info("FTP文件名：{},文件大小：{}", ftpFile.getName(), ftpFile.getSize());
		}
		
		File file = new File(ftp.getLocalFileName().substring(0, ftp.getLocalFileName().lastIndexOf(File.separator)));
		if (!file.exists()) {
			file.mkdirs();
		}
		
		log.info("FTP下载开始");
		FileOutputStream outputStream = new FileOutputStream(new File(ftp.getLocalFileName()));
		ftpClient.retrieveFile(ftp.getTargetFileName(), outputStream);
		log.info("FTP下载结束");
	}

}
