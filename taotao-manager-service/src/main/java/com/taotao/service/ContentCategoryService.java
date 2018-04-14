package com.taotao.service;

import java.util.List;

import com.taotao.pojo.TbContent;
import com.taotao.util.TaotaoResult;
import com.taotao.util.TreeNode;

public interface ContentCategoryService {
	public List<TreeNode> getListCateGory(long parendId);

	public TaotaoResult saveCateGory(long parentId, String name);

	// 保存内容

	public TaotaoResult saveContent(TbContent tbContent);
}
