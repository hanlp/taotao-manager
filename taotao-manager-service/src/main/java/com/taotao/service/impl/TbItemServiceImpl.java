package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.TbItemService;
import com.taotao.util.EUDateGrideResult;
import com.taotao.util.IDUtils;
import com.taotao.util.TaotaoResult;

/**
 * 
 * <p>
 * Title: TbItemServiceImpl
 * </p>
 * <p>
 * Description: 商品Service
 * </p>
 * <p>
 * Company: www.bochy.com
 * </p>
 * 
 * @author 涛哥
 * @date 2017年4月6日上午10:37:06
 * @version 1.0
 */
@Service
public class TbItemServiceImpl implements TbItemService {
	@Autowired
	private TbItemMapper tbitemMapper;
	@Autowired
	private TbItemDescMapper tbitemdescMapper;

	@Autowired
	private TbItemParamItemMapper paramMapper;

	// 根据id查询商品详情
	@Override
	public TbItem geTbItemById(long itemId) {
		return tbitemMapper.selectByPrimaryKey(itemId);
	}

	// 查询商品
	@Override
	public EUDateGrideResult getListTbiem(int page, int pageSize) {
		TbItemExample example = new TbItemExample();
		// 添加分页插件
		PageHelper.startPage(page, pageSize);
		List<TbItem> list = tbitemMapper.selectByExample(example);
		// 总条数
		PageInfo<TbItem> info = new PageInfo<>(list);
		EUDateGrideResult result = new EUDateGrideResult();
		// 总条数
		result.setTotal(info.getTotal());
		result.setRows(list);
		return result;
	}

	// 保存商品信息
	@Override
	public TaotaoResult saveTbItem(TbItem tbItem, String desc, String paramData) throws Exception {
		// 数据补
		tbItem.setCreated(new Date());
		tbItem.setUpdated(new Date());
		tbItem.setStatus((byte) 1);// 商品状态，1-正常，2-下架，3-删除
		// 商品ID
		long itemId = IDUtils.genItemId();// 生成随机数id
		tbItem.setId(itemId);
		tbitemMapper.insert(tbItem);// 保存商品信息
		TaotaoResult result = saveItemDesc(itemId, desc);
		if (result.getStatus() != 200) {
			throw new Exception();// 事务,让spring
		}
		// 保存规格信息
		TaotaoResult result2 = saveItemParam(itemId, paramData);
		if (result2.getStatus() != 200) {
			throw new Exception();// 事务,让spring
		}
		return TaotaoResult.ok();
	}
	// 保存商品描述

	private TaotaoResult saveItemDesc(Long itemId, String desc) {
		// 数据补全
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(itemId);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(new Date());
		tbItemDesc.setUpdated(new Date());
		tbitemdescMapper.insert(tbItemDesc);// 保存商品详情
		return TaotaoResult.ok();
	}

	// 保存商品模板信息
	private TaotaoResult saveItemParam(Long itemId, String paramData) {
		// 数据补全
		TbItemParamItem tbItemParam = new TbItemParamItem();
		tbItemParam.setItemId(itemId);
		tbItemParam.setCreated(new Date());
		tbItemParam.setUpdated(new Date());
		tbItemParam.setParamData(paramData);
		// 保存规格参数
		paramMapper.insert(tbItemParam);
		return TaotaoResult.ok();
	}

}
