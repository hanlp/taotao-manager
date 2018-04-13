package com.taotao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

public class PageTest {

	@Test
	public void testPage() {
		// 1.读取spring配置文件
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		// 2.找到测试的bean
		TbItemMapper tbItemMapper = context.getBean(TbItemMapper.class);
		TbItemExample example = new TbItemExample();
		// 分页插件使用,显示出10条数据 第一个参数 页数 第二参数 显示的条数
		PageHelper.startPage(2, 10);

		List<TbItem> list = tbItemMapper.selectByExample(example);
		PageInfo<TbItem> info = new PageInfo<>(list);
		// 总条数
		System.out.println(info.getTotal());

		for (TbItem tbItem : list) {
			System.out.println(tbItem.getTitle() + "==" + tbItem.getPrice());
		}
	}
}
