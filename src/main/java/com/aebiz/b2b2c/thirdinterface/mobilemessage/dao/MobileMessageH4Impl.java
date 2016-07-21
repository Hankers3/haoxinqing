package com.aebiz.b2b2c.thirdinterface.mobilemessage.dao;

import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.thirdinterface.mobilemessage.vo.MobileMessageModel;
import com.aebiz.b2b2c.thirdinterface.mobilemessage.vo.MobileMessageQueryModel;

@Repository
public class MobileMessageH4Impl extends BaseH4Impl<MobileMessageModel,MobileMessageQueryModel> implements MobileMessageDAO {

}
