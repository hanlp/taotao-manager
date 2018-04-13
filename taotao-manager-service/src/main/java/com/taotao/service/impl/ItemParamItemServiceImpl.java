package com.taotao.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.service.ItemParamItemService;
import com.taotao.util.JsonUtils;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {
	@Autowired
	private TbItemParamItemMapper itemParamMapper;

	@Override
	public String getIterParamItem(long itemId) {
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = itemParamMapper.selectByExampleWithBLOBs(example);
		// 获取到规格参数信息.
		if (list == null && list.size() == 0) {
			return "";
		}
		String jsonResult = list.get(0).getParamData();
		// 遍历json格式
		// 需要把json转换成为list类型, 泛型map
		List<Map> map = JsonUtils.jsonToList(jsonResult, Map.class);
		StringBuffer sb = new StringBuffer();

		sb.append("<table class=\"Ptable\" width=\"100%\" cellspacing=\"1\" cellpadding=\"0\"\n");
		sb.append("    border=\"1\">\n");
		sb.append("    <tbody>\n");
		for (Map map2 : map) {// 遍历规格组
			sb.append("      <tr>\n");
			sb.append("        <th class=\"tdTitle\" colspan=\"2\">" + map2.get("group") + "</th>\n");
			sb.append("      </tr>\n");
			sb.append("      <tr>\n");
			sb.append("      </tr>\n");
			List<Map> m2 = (List<Map>) map2.get("params");
			for (Map map3 : m2) {// 遍历规格项目
				sb.append("      <tr>\n");
				sb.append("        <td class=\"tdTitle\">" + map3.get("k") + "</td>\n");
				sb.append("        <td>" + map3.get("v") + "</td>\n");
				sb.append("      </tr>\n");
			}
		}
		sb.append("    </tbody>\n");
		sb.append("  </table>");
		return sb.toString();
	}

}
