package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}

	// 导航栏访问
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}
}
