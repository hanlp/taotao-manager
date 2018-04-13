package com.taotao.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;

public class FTPClicentTest {

	public static void main(String[] args) throws SocketException, IOException {
		// 图片上传到ftp服务器的步骤
		// 1.创建ftpclient类
		FTPClient ftpClient = new FTPClient();
		// 2.连接ftp服务器
		ftpClient.connect("172.18.12.19", 21);
		// 3.登录ftp服务器
		ftpClient.login("ftpuser", "ftpuser");
		// 4.读取本地文件
		FileInputStream inputStream = new FileInputStream(new File("D:\\image\\4.jpg"));
		// 5.设置远程服务器的目录
		ftpClient.changeWorkingDirectory("/home/ftpuser/images");
		// 设置上传文件类型
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		// 6.上传文件 hh.jpg上传到远程服务器上的名字
		ftpClient.storeFile("aa.jpg", inputStream);
		// 7.登出
		ftpClient.logout();
	}
}
