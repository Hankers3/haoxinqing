package com.hxq.mobile.entity.common;/** *  * @author hedongfei * */public class ServiceStaffComb {	/* 家政员信息信息 */	private ServiceStaff serviceStaff = new ServiceStaff();	/* 家政员基础信息*/	private ServiceStaffInfo serviceStaffInfo = new ServiceStaffInfo();	public ServiceStaff getServiceStaff() {		return serviceStaff;	}	public void setServiceStaff(ServiceStaff serviceStaff) {		this.serviceStaff = serviceStaff;	}	public ServiceStaffInfo getServiceStaffInfo() {		return serviceStaffInfo;	}	public void setServiceStaffInfo(ServiceStaffInfo serviceStaffInfo) {		this.serviceStaffInfo = serviceStaffInfo;	}	@Override	public String toString() {		return "ServiceStaffComb [serviceStaff=" + serviceStaff + ", serviceStaffInfo=" + serviceStaffInfo + "]";	}	}