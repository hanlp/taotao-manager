package com.taotao.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.support.RemoteInvocationResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.tools.internal.xjc.generator.bean.ImplStructureStrategy.Result;
import com.taotao.pojo.TbItemCat;
import com.taotao.service.TbItemCatService;

@Controller
public class TbItemCatController {

	@Autowired
	private TbItemCatService tbItemCatService;

	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List getTbItemCatList(@RequestParam(value = "id", defaultValue = "0") long parentId) {
		List<TbItemCat> list = tbItemCatService.getItemCatList(parentId);// 查询数据
		// id text state
		List result = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			Map<String, Object> map = new HashMap();
			map.put("id", tbItemCat.getId());
			map.put("text", tbItemCat.getName());
			// 判断是否为叶子节点
			map.put("state", tbItemCat.getIsParent() ? "closed" : "open");
			result.add(map);
		}
		return result;
	}
}
