package com.taotao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.support.json.JSONUtils;
import com.taotao.service.PictureService;
import com.taotao.util.JsonUtils;

@Controller
public class PictureController {
	@Autowired
	private PictureService pictureService;

	@RequestMapping("/pic/upload")
	@ResponseBody
	public String uploadPicture(MultipartFile uploadFile) {
		Map map = pictureService.pictureUpload(uploadFile);
		String resultMap = JsonUtils.objectToJson(map);
		return resultMap;
	}
}
