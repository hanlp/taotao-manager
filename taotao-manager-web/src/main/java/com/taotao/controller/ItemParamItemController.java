package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.service.ItemParamItemService;

@Controller
public class ItemParamItemController {

	@Autowired
	private ItemParamItemService itemParamItemService;

	@RequestMapping("/itemParamitem/{itemId}")
	public String showItemParam(@PathVariable long itemId, Model model) {
		String jsons = itemParamItemService.getIterParamItem(itemId);
		model.addAttribute("items", jsons);
		return "item";
	}

}
