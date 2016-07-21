package com.aebiz.b2b2c.finance.sysback;

/**
 * 获取平台统一的分账比率和运费属否参与分账的key值
 *
 * @author tangyunkai
 *
 * @date 2014年12月12日 下午3:36:47 
 *
 */
public class PlatLedgerConst {
	
	/**
	 * 平台统一分账比率的key值<br>
	 * 
	 * 可以根据这个key值得到平台设置的统一分账比率,也可以根据key值和新的value值更改统一分账比率
	 */
	public static final String FINANCE_ROUNTING_KEY = "finance_Routing";
	
	/**
	 * 平台设置的运费是否参与分账的key值<br>
	 * 
	 * 可以根据这个key值得到平台设置的运费是否参与分账,也可以根据key值和新的value值更改运费是否参与分账的值
	 */
	public static final String FINANCE_JOIN_ROUNTING_KEY = "freight_join_routing";

	
	/**
	 * 队长统一分账比率的key值<br>
	 * 
	 * 可以根据这个key值得到队长设置的统一分账比率,也可以根据key值和新的value值更改统一分账比率
	 */
	public static final String FINANCE_CAPTAIN_KEY = "finance_Captain_Routing";
	
	/**
	 * 平台设置的队长是否参与分账的key值<br>
	 * 
	 * 可以根据这个key值得到平台设置的运费是否参与分账,也可以根据key值和新的value值更改运费是否参与分账的值
	 */
	public static final String FINANCE_CAPTAIN_JOIN_ROUNTING_KEY = "freight_captain_join_routing";
	
	
	
	
}
