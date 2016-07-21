package com.aebiz.b2b2c.servicestaff.telephonetime.dao;

import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.basecrud.dao.BaseH4Impl;
import com.aebiz.b2b2c.servicestaff.telephonetime.vo.TelephoneTimeModel;
import com.aebiz.b2b2c.servicestaff.telephonetime.vo.TelephoneTimeQueryModel;


@Repository
public class TelephoneTimeH4Impl extends BaseH4Impl<TelephoneTimeModel,TelephoneTimeQueryModel> implements TelephoneTimeDAO {

}
