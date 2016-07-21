package com.aebiz.b2b2c.basebusiness.ueditor.define;

/**
 * 处理状态接口
 * @author hancong03@baidu.com
 *
 */
public interface State {
	
	public boolean isSuccess ();
	
	public void putInfo( String name, String val );
	
	public void putInfo ( String name, long val );
	
	public String toJSONString ();

}
