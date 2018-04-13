package com.taotao.service;

import com.taotao.pojo.TbItem;
import com.taotao.util.EUDateGrideResult;
import com.taotao.util.TaotaoResult;

public interface TbItemService {

	public TbItem geTbItemById(long itemId);

	// 查询商品信息
	public EUDateGrideResult getListTbiem(int page, int pageSize);

	// 保存商品..
	public TaotaoResult saveTbItem(TbItem tbItem, String desc, String paramData) throws Exception;
}
