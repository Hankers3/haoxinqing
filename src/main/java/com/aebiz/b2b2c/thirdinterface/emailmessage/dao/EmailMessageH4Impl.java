package com.aebiz.b2b2c.thirdinterface.emailmessage.dao;

import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.thirdinterface.emailmessage.vo.EmailMessageModel;
import com.aebiz.b2b2c.thirdinterface.emailmessage.vo.EmailMessageModelQueryModel;

@Repository
public class EmailMessageH4Impl extends
		BaseH4Impl<EmailMessageModel, EmailMessageModelQueryModel> implements
		EmailMessageDAO {

}
