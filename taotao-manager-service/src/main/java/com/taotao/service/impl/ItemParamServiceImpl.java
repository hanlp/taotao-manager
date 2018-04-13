package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;
import com.taotao.util.EUDateGrideResult;
import com.taotao.util.TaotaoResult;

@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper tbitemParamMapper;

	// 查询模板信息
	@Override
	public EUDateGrideResult getListParam(int page, int pageSize) {
		// 分页
		PageHelper.startPage(page, pageSize);
		// 调用mapper方法
		List<TbItemParam> list = tbitemParamMapper.queryListParam();
		EUDateGrideResult result = new EUDateGrideResult();
		result.setRows(list);
		PageInfo<TbItemParam> info = new PageInfo<>(list);
		// 设置总条数
		result.setTotal(info.getTotal());

		return result;
	}

	// 根据类目id查询模板表
	@Override
	public TaotaoResult geTbItemParam(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = tbitemParamMapper.selectByExampleWithBLOBs(example);
		// 判断list是否为空
		if (list != null && list.size() > 0) {
			TbItemParam tbItemParam = list.get(0);
			return TaotaoResult.ok(tbItemParam);
		}
		return TaotaoResult.ok();
	}

	// 保存规格模板
	@Override
	public TaotaoResult saveItemParam(TbItemParam tbItemParam) {
		tbItemParam.setCreated(new Date());
		tbItemParam.setUpdated(new Date());
		tbitemParamMapper.insert(tbItemParam);
		return TaotaoResult.ok();
	}
}
