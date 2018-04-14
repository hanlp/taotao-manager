package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;
import com.taotao.util.TaotaoResult;
import com.taotao.util.TreeNode;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;

	@Autowired
	private TbContentMapper tbContentMapper;

	@Override
	public List<TreeNode> getListCateGory(long parendId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parendId);
		List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
		List<TreeNode> resultlist = new ArrayList<>();
		// 遍历数据查询出的数据,进行二次封装.
		for (TbContentCategory tbContentCategory : list) {
			TreeNode node = new TreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
			resultlist.add(node);
		}
		return resultlist;
	}

	// 保存分类信息
	@Override
	public TaotaoResult saveCateGory(long parentId, String name) {
		// 数据补全
		TbContentCategory category = new TbContentCategory();
		category.setCreated(new Date());
		category.setUpdated(new Date());
		category.setName(name);// 新增的数据
		category.setParentId(parentId);
		category.setStatus(1);// '状态。可选值:1(正常),2(删除)',
		category.setSortOrder(1);// '排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数'
		category.setIsParent(false);// 是否有子节点
		tbContentCategoryMapper.insert(category);// 保存
		// 根据获取到的选中节点的id,查询他的isparent的状态
		TbContentCategory parent = tbContentCategoryMapper.selectByPrimaryKey(parentId);
		// 判断当前选中的节点isparent状态
		if (!parent.getIsParent()) {
			parent.setIsParent(true);// 设置为true
			tbContentCategoryMapper.updateByPrimaryKeySelective(parent);// 修改
		}

		return TaotaoResult.ok();
	}

	// 保存内容
	@Override
	public TaotaoResult saveContent(TbContent tbContent) {
		// 数据补全
		tbContent.setUpdated(new Date());
		tbContent.setCreated(new Date());
		tbContentMapper.insert(tbContent);
		return TaotaoResult.ok();
	}

}
