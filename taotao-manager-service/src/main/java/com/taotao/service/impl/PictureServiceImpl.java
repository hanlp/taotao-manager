package com.taotao.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.service.PictureService;
import com.taotao.util.FtpUtil;
import com.taotao.util.IDUtils;

@Service
public class PictureServiceImpl implements PictureService {
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private int FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;

	// 图片回显地址
	@Value("${FTP_BASE_IMG}")
	private String FTP_BASE_IMG;

	@Override
	public Map pictureUpload(MultipartFile uploadPicture) {
		// 1.获取到文件名
		// 2.创建新文件名字.(每天上传几千张图片)
		// 3.调用ftp工具类,实现上传功能
		// 4.上传成功 error: 0 url:图片地址
		// 5.上传失败,返回error:1. message:'图片上传失败'
		String oldName = uploadPicture.getOriginalFilename();// 获取到上传的文件名
		String newName = IDUtils.genImageName();// 生成随机数,作为图片名字;
		// 举例 11.jpg 结果: 11112113213123123.jpg
		newName = newName + oldName.substring(oldName.lastIndexOf("."));
		// 内容抽离出来, 因为再以后部署项目的时候,这些内容都有可能变.
		String imgPath = new DateTime().toString("/yyyy/MM/dd");
		Map resultmap = new HashMap();
		try {
			boolean flag = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH, imgPath,
					newName, uploadPicture.getInputStream());
			// 返回json字符串
			if (flag) {// 上传成功
				resultmap.put("error", 0);
				resultmap.put("url", FTP_BASE_IMG + imgPath + "/" + newName);
				return resultmap;
			} else {
				resultmap.put("error", 1);
				resultmap.put("message", "上传失败!!");
				return resultmap;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultmap.put("error", 1);
			resultmap.put("message", "上传失败!!");
			return resultmap;
		}
	}
}
