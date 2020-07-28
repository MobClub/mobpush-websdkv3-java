/**
 * Project Name:Project Name:mobpush-api-java-client
 * File Name:MobPushConfig.java
 * Package Name:mob.push.api
 * Date: 2018年2月2日
 * Time: 下午6:11:51
 *
*/

package mob.push.api.config;
/**
 * ClassName:MobPushConfig
 * MobPush WebApi Java Client Base Config 
 */
public class MobPushConfig {
	
	/**
	 * appkey: 需要先设置此数据，怎样获取appkey请至http://www.mob.com官网
	 */
	public static String appkey ;
	
	/**
	 * appSecret: appkey对应密钥,需要先设置此数据
	 */
	public static String appSecret ;
	
	/** 
	 * baseUrl: webapi http 接口基础url
	 */
	public static String baseUrl = "http://api.push.mob.com";

}

