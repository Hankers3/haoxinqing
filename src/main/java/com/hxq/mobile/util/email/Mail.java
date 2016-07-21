package com.hxq.mobile.util.email;

import java.io.Serializable;
import java.util.Map;

/**
 * Mail属性实体
 *
 * Created by alice_company on 2016/6/3.
 */
@SuppressWarnings("serial")
public class Mail implements Serializable{

    private String title;
    private String content;
    private String email;
    private Map<String, Object> map; //参数
    private String from;			 //发件人
    private String to;				 //收件人
    private String subject;			 //邮件主题
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
    


    
}
