package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbContent;
import com.taotao.service.ContentCategoryService;
import com.taotao.util.TaotaoResult;
import com.taotao.util.TreeNode;

@Controller
@RequestMapping("/content")
public class ContentCateoryController {

	@Autowired
	private ContentCategoryService categoryService;

	// 查询内容分类管理
	@RequestMapping("/category/list")
	@ResponseBody
	public List<TreeNode> geTreeNodes(@RequestParam(value = "id", defaultValue = "0") long parentId) {
		return categoryService.getListCateGory(parentId);
	}

	// 保存内容分类
	@RequestMapping("/category/create")
	@ResponseBody
	public TaotaoResult saveCategory(long parentId, String name) {
		return categoryService.saveCateGory(parentId, name);
	}

	// 保存内容
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult saveContent(TbContent tbContent) {
		return categoryService.saveContent(tbContent);
	}
}
