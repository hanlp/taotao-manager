package com.taotao.controller;

import javax.print.attribute.standard.RequestingUserName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbItem;
import com.taotao.service.TbItemService;
import com.taotao.util.EUDateGrideResult;
import com.taotao.util.TaotaoResult;

@Controller
@RequestMapping("/item")
public class TBItemController {

	@Autowired
	private TbItemService tbItemService;

	// 根据id查询商品详情
	@RequestMapping("/{itemId}")
	@ResponseBody
	public TbItem geTbItemById(@PathVariable long itemId) {
		return tbItemService.geTbItemById(itemId);
	}

	// 访问的url
	@RequestMapping("/list")
	@ResponseBody
	public EUDateGrideResult getTbitemList(int page, int rows) {
		return tbItemService.getListTbiem(page, rows);
	}

	// 保存商品基本信息

	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult saveItem(TbItem tbItem, String desc, String itemParams) throws Exception {
		return tbItemService.saveTbItem(tbItem, desc, itemParams);
	}

}
