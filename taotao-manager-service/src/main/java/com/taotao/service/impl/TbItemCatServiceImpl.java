package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.TbItemCatService;

@Service
public class TbItemCatServiceImpl implements TbItemCatService {
	@Autowired
	private TbItemCatMapper tbitemCatMapper;

	@Override
	public List<TbItemCat> getItemCatList(long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		// 根据父节点查询
		criteria.andParentIdEqualTo(parentId);
		// 执行查询方法
		List<TbItemCat> list = tbitemCatMapper.selectByExample(example);
		return list;
	}

}
