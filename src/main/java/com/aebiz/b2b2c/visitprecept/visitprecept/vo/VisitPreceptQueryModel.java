package com.aebiz.b2b2c.visitprecept.visitprecept.vo;

public class VisitPreceptQueryModel extends VisitPreceptModel {
        
        private String  doctorName =""; 
        private String  doctorMobile ="";
        private String  hospitalName ="";
	private String  preceptName ="";
	    
	
	
	
	public String getDoctorName() {
        return doctorName;
        }
        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }
        public String getDoctorMobile() {
            return doctorMobile;
        }
        public void setDoctorMobile(String doctorMobile) {
            this.doctorMobile = doctorMobile;
        }
        public String getHospitalName() {
            return hospitalName;
        }
        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }
        public void setPreceptName(String obj){
    		this.preceptName = obj;
	}
	public String getPreceptName(){
		return this.preceptName;
	}
	
	
	
	public String toString(){
		return "Model"+this.getClass().getName()+","+super.toString()+" ,[preceptName=" + this.getPreceptName() + ",]";
	}
}
