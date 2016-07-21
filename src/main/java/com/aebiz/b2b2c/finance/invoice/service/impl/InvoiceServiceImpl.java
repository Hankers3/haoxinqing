package com.aebiz.b2b2c.finance.invoice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aebiz.b2b2c.baseframework.basecrud.service.BaseServiceImpl;
import com.aebiz.b2b2c.baseframework.basecrud.vo.BaseModel;
import com.aebiz.b2b2c.baseframework.utils.DateFormatHelper;
import com.aebiz.b2b2c.baseframework.utils.LoginUserHelper;
import com.aebiz.b2b2c.baseframework.uuidservice.service.UuidService;
import com.aebiz.b2b2c.finance.invoice.dao.InvoiceDAO;
import com.aebiz.b2b2c.finance.invoice.service.InvoiceService;
import com.aebiz.b2b2c.finance.invoice.vo.InvoiceModel;
import com.aebiz.b2b2c.finance.invoice.vo.InvoiceQueryModel;
import com.aebiz.b2b2c.finance.invoice.vo.InvoiceState;

@Service
@Transactional
public class InvoiceServiceImpl extends BaseServiceImpl<InvoiceModel, InvoiceQueryModel> implements InvoiceService {
	
	private InvoiceDAO myDao = null;
	@Autowired
	private UuidService us;

	@Autowired
	public void setMyDao(InvoiceDAO dao) {
		this.myDao = dao;
		super.setDao(dao);
	}

	@Override
	public String create(InvoiceModel m) {
		m.setUuid(us.getNextUuid("Invoice", 10));
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());

		String ret = super.create(m);
		return ret;
	}

	@Override
	public void update(InvoiceModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_VALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	@Override
	public void delete(InvoiceModel m) {
		m.setDelFlag(BaseModel.DEL_FLAG_INVALID);
		m.setOper(LoginUserHelper.getLoginUserUuid());
		m.setOpeTime(DateFormatHelper.getNowTimeStr());
		super.update(m);
	}

	/**
	 * 根据订单编号获得订单的发票信息
	 * 一个订单可能有多个发票信息，因此获得的是一个LIST集合
	 * 
	 * @param orderUuid
	 * @return
	 */
	public List<InvoiceModel> getInvoiceListByOrderId(String orderUuid) {
		return myDao.getInvoiceListByOrderId(orderUuid);
	}

	/**
	 * 保存一条发票记录
	 * @param invoiceModel
	 */
	public void saveInvoiceModel(InvoiceModel invoiceModel){
		this.create(invoiceModel);
	}
	
	/**
	 * 更新一条发票记录
	 * @param invoiceModel
	 */
	public void updateInvoiceModel(InvoiceModel invoiceModel){
		this.update(invoiceModel);
	}
	
	/**
	 * 根据Uuid查询出一条发票记录
	 * @param invoiceUuid
	 * @return InvoiceModel
	 */
	public InvoiceModel getByInvoiceUuid(String invoiceUuid){
		return this.getByUuid(invoiceUuid);
	}
	
	/**
	 * 保存一条发票记录
	 * @param invoiceCate
	 * @param invoiceType
	 * @param invoiceNO
	 * @param invoiceContent
	 */
	public void save( String invoiceCate, String invoiceType,String invoiceNO, String[] invoiceContents){
		
		InvoiceModel invoiceModel=new InvoiceModel();
		invoiceModel.setInvoiceNO(invoiceNO);
		invoiceModel.setInvoiceContent(this.arrayToString(invoiceContents));
		invoiceModel.setInvoiceCate(invoiceCate);
		invoiceModel.setInvoiceType(invoiceType);

		//当前保存一条未开票状态的发票
		invoiceModel.setInvoiceState(InvoiceState.UNINVOICED.getName());
		invoiceModel.setCreateTime(DateFormatHelper.getNowTimeStr());
		this.saveInvoiceModel(invoiceModel);
	}
	
	/**
	 * 将数组转化为String
	 * @param array
	 * @return
	 */
	public String arrayToString(String[] array){
		if(array.length<=0){
			return null;
		}
		else{
			StringBuffer result=new StringBuffer("");
			for(int i=0;i<array.length;i++){
				result.append(array[i]).append(",");
			}
			return result.substring(0,result.length()-1).toString();
		}
	}
}