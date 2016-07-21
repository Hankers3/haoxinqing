package com.hxq.mobile.support.service;

import java.util.List;

import com.hxq.mobile.entity.common.AppPublish;

public interface AppVersionService  {
	AppPublish selectVersion(String type);
    List<AppPublish> selectLast(String type,String last);
}