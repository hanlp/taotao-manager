package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;
import com.taotao.util.EUDateGrideResult;
import com.taotao.util.TaotaoResult;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;

	@RequestMapping("/list")
	@ResponseBody
	// 查询模板 信息
	public EUDateGrideResult getItemParam(int page, int rows) {
		return itemParamService.getListParam(page, rows);
	}

	// 分类模板
	@RequestMapping("/query/itemcatid/{cid}")
	@ResponseBody
	public TaotaoResult getItemCatById(@PathVariable long cid) {
		return itemParamService.geTbItemParam(cid);
	}

	// 保存规格参数模板
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public TaotaoResult saveItemParam(@PathVariable long cid, String paramData) {
		TbItemParam tItemParam = new TbItemParam();
		tItemParam.setItemCatId(cid);
		tItemParam.setParamData(paramData);
		return itemParamService.saveItemParam(tItemParam);
	}
}
