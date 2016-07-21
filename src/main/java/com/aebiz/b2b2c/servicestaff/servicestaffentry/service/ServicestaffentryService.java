package com.aebiz.b2b2c.servicestaff.servicestaffentry.service;import org.springframework.web.multipart.MultipartFile;import com.aebiz.b2b2c.baseframework.basecrud.service.BaseService;import com.aebiz.b2b2c.servicestaff.servicestaffentry.vo.ServicestaffentryModel;import com.aebiz.b2b2c.servicestaff.servicestaffentry.vo.ServicestaffentryQueryModel;public interface ServicestaffentryService extends BaseService<ServicestaffentryModel,ServicestaffentryQueryModel>{	/**	 * 根据家政员编号号获取入职信息	 */	public ServicestaffentryModel getServicestaffentryModelByServicestaffUuid(			String servicestaffUuid);		/**	 *  更新家政员入职信息	 * @param sem	 * @param imgFiles	 */	public void updateServicestaffentry(ServicestaffentryModel sem,			MultipartFile[] imgFiles,MultipartFile[] contractImgFiles,String[] whethercompletes);		/**	 *  更新家政员入职信息公司标准照	 * @param sem	 * @param imgFiles	 * by hdf	 */	public void updateSNTstandardimage(ServicestaffentryModel sem,			MultipartFile[] imgFiles);}