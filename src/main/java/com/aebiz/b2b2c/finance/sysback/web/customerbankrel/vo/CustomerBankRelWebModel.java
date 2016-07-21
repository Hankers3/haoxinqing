package com.aebiz.b2b2c.finance.sysback.web.customerbankrel.vo;

import java.util.ArrayList;
import java.util.List;

import com.aebiz.b2b2c.baseframework.basecrud.vo.DataTablesPageParam;
import com.aebiz.b2b2c.baseframework.basecrud.web.vo.BaseWebModel;

public class CustomerBankRelWebModel extends BaseWebModel {

	/* 验证状态 */
	List<DataTablesPageParam> validateStateList = new ArrayList<DataTablesPageParam>();

	public List<DataTablesPageParam> getValidateStateList() {
		return validateStateList;
	}

	public void setValidateStateList(List<DataTablesPageParam> validateStateList) {
		this.validateStateList = validateStateList;
	}

}