package com.taotao.service;

import com.taotao.pojo.TbItemParam;
import com.taotao.util.EUDateGrideResult;
import com.taotao.util.TaotaoResult;

public interface ItemParamService {
	public EUDateGrideResult getListParam(int page, int pageSize);

	public TaotaoResult geTbItemParam(long cid);

	public TaotaoResult saveItemParam(TbItemParam tbItemParam);
}
