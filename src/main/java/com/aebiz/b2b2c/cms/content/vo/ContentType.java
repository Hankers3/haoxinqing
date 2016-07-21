package com.aebiz.b2b2c.cms.content.vo;

/**
 * Created by sunchao on 15/12/23 and { content来源类型 }
 */
public enum ContentType {
    /*
    * 1\本地录入资源 3\医脉通资源
    * */
    Local("1"), Ymt("3");

    private String type;

    private ContentType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
