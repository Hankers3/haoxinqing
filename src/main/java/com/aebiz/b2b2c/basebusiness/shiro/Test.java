package com.aebiz.b2b2c.basebusiness.shiro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	private String attrbuteName = "";

	private String attrbuteId = "";

	private String choose = "";

	public String getAttrbuteName() {
		return attrbuteName;
	}

	public void setAttrbuteName(String attrbuteName) {
		this.attrbuteName = attrbuteName;
	}

	public String getChoose() {
		return choose;
	}

	public void setChoose(String choose) {
		this.choose = choose;
	}

	public String getAttrbuteId() {
		return attrbuteId;
	}

	public void setAttrbuteId(String attrbuteId) {
		this.attrbuteId = attrbuteId;
	}

}

class Test2 {
	public void save() {
		Map<String, List> map1 = new HashMap<String, List>();
		Map<Test, List> valueMap2 = new HashMap<Test, List>();

		List parentList = new ArrayList();
		List subList = new ArrayList();
		subList.add("淡黄");
		subList.add("深黄");

		Test test = new Test();
		test.setAttrbuteName("黄色");
		test.setAttrbuteId("0001");
		test.setChoose("0");

		Test test2 = new Test();
		test.setAttrbuteName("绿色");
		test.setAttrbuteId("0002");
		test.setChoose("1");

		List subList2 = new ArrayList();
		subList2.add("淡绿");
		subList2.add("深绿");

		valueMap2.put(test, subList);
		valueMap2.put(test2, subList2);

		parentList.add(valueMap2);

		map1.put("uuid", parentList);
	}
}
