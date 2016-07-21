package com.hxq.mobile.util.mybatis;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxcommon.Constants;
import com.wxcommon.http.RequestUtil;
import com.wxcommon.util.MathUtils;
import com.wxcommon.util.ObjectUtils;

public class PagingUtils {

	/**
	 * 设置分页查询条件，紧跟着的mybatis第一个select方法会被分页，后面的不会被分页，除非再次调用startPage
	 * @param searchCondition 查询条件
	 */
	public static void startPage(Map<String, Object> searchCondition) {
		int page_no=MathUtils.toInt(RequestUtil.getFormValue(searchCondition,Constants.PAGINATION_PAGE_NO),1);
		int page_size=MathUtils.toInt(RequestUtil.getFormValue(searchCondition,Constants.PAGINATION_PAGE_SIZE),20);
		page_no = page_no < 1 ? 1 : page_no;
		page_size = page_size < 1 ? 1 : page_size;
		//紧跟着的mybatis第一个select方法会被分页，后面的不会被分页，除非再次调用PageHelper.startPage
		PageHelper.startPage(page_no, page_size);
	}

	/**
	 * 查询后的分页属性处理
	 * @param searchCondition 分页查询条件
	 * @param result 分页查询结果
	 */
	public static <T> List<T> endPage(Map<String, Object> searchCondition, List<T> result) {
		if(searchCondition == null) return result;
		if(ObjectUtils.isEmpty(result)) {
			searchCondition.put(Constants.PAGINATION_PAGE_NO, "0");
			searchCondition.put(Constants.PAGINATION_TOTAL_ROWS, "0");
			searchCondition.put(Constants.PAGINATION_TOTAL_PAGE, "0");
			return result;
		} else {
			//用PageInfo对结果进行包装，PageInfo包含了非常全面的分页属性
			PageInfo<T> page = new PageInfo<T>(result);
			searchCondition.put(Constants.PAGINATION_PAGE_NO, String.valueOf(page.getPageNum()));
			searchCondition.put(Constants.PAGINATION_PAGE_SIZE, String.valueOf(page.getPageSize()));
			searchCondition.put(Constants.PAGINATION_TOTAL_ROWS, String.valueOf(page.getTotal()));
			searchCondition.put(Constants.PAGINATION_TOTAL_PAGE, String.valueOf(page.getPages()));
			return page.getList();
		}
	}
}
