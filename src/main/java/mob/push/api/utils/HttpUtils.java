package mob.push.api.utils;

import mob.push.api.exception.ApiException;
import mob.push.api.http.Http;
import mob.push.api.http.StringResult;

import java.util.*;

/**
 * HTTP 工具类
 * @author hlliu
 * Date: 2018年2月5日
 * Time: 下午9:03:09
 */
public class HttpUtils {

	/**
	 * HTTP  POST 处理工具类
	 * @author hlliu
	 * Date: 2018年2月5日
	 * Time: 下午9:03:21
	 */
	public static class PostEntity {

		/**
		 * httpUrl:请求接口地址
		 */
		private String httpUrl;
		/**
		 * statusCode: 接口返回状态
		 */
		private int statusCode;
		/**
		 * resp: 接口返回内容
		 */
		private String resp;
		/**
		 * appkey: MobPush AppKey
		 */
		private String appkey;
		/**
		 * appSecret: MobPush AppKey 对应 secret 
		 */
		private String appSecret;

		/**
		 * orgData: 请求数据体 ： JSON.toString()
		 */
		private String orgData;
 
		public PostEntity(String httpUrl, String appkey, String appSecret, String orgData) {
			this.httpUrl = httpUrl;
			this.orgData = orgData;
			this.appkey = appkey;
			this.appSecret = appSecret;
		}

		public int getStatusCode() {
			return statusCode;
		}

		public String getResp() {
			return resp;
		}

		public String getAppkey() {
			return appkey;
		}

		public String getAppSecret() {
			return appSecret;
		}
  
		protected String serverSign(String decodeData, String appSecret) {
			return MD5.hash(decodeData + appSecret, MobHelper.CHARSET_UTF8);
		}

		public PostEntity invoke(boolean useGzip) throws ApiException {
			if(MobHelper.isBlank(appkey) || MobHelper.isBlank(appSecret)){
				throw new ApiException(MobHelper.HTTP_STATUS_400, -1, "appkey or appSecret is null,Please go to MobPushConfig to set them");
			}
			StringResult res = Http.post(httpUrl, null, this.orgData);
			resp = res.getRes();
			statusCode = res.getResponseCode();
			return this;
		} 
	}
 
	/**
	 * HTTP GET 处理工具类
	 * @author hlliu
	 * Date: 2018年2月5日
	 * Time: 下午9:03:40
	 */
	public static class GetEntity {

		/**
		 * httpUrl:请求接口地址
		 */
		private String httpUrl;
		/**
		 * statusCode: 接口返回状态
		 */
		private int statusCode;
		/**
		 * resp: 接口返回内容
		 */
		private String resp;
		/**
		 * appkey: MobPush AppKey
		 */
		private String appkey;
		/**
		 * appSecret: MobPush AppKey 对应 secret 
		 */
		private String appSecret;

		/**
		 * par: 请求参数对
		 */
		private Map<String, Object> par;

		public GetEntity(String httpUrl, String appkey, String appSecret, Map<String, Object> par) {
			this.httpUrl = httpUrl;
			this.par = par;
			this.appkey = appkey;
			this.appSecret = appSecret;
		}

		/**
		 * 键值对排序 
		 */
		public String sort() {
			StringBuffer buffer = new StringBuffer();
			if (par == null) {
				return null;
			}
			List<Map.Entry<String, Object>> keys = new ArrayList<Map.Entry<String, Object>>(par.entrySet());
			Collections.sort(keys, new Comparator<Map.Entry<String, Object>>() {
				public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
					return (o1.getKey()).toString().compareTo(o2.getKey());
				}
			});
			for (Map.Entry<String, Object> key : keys) {
				buffer.append(key.getKey() + "=" + key.getValue() + "&");
			}
			if (buffer.length() > 0) {
				return buffer.toString().substring(0, buffer.length() - 1);
			}

			return buffer.toString();
		}

		public GetEntity invoke() throws ApiException {
			if(MobHelper.isBlank(appkey) || MobHelper.isBlank(appSecret)){
				throw new ApiException(MobHelper.HTTP_STATUS_400, -1, "appkey or appSecret is null,Please go to MobPushConfig to set them");
			}
			StringResult res = Http.get(httpUrl, null);
			resp = res.getRes();
			statusCode = res.getResponseCode();
			return this;
		}

		protected String serverSign(String decodeData, String appSecret) {
			if (decodeData == null || decodeData.equals("")) {
				return MD5.hash(appSecret, MobHelper.CHARSET_UTF8);
			}
			return MD5.hash(decodeData + appSecret, MobHelper.CHARSET_UTF8);
		}

		public String getAppkey() {
			return appkey;
		}

		public int getStatusCode() {
			return statusCode;
		}

		public String getResp() {
			return resp;
		}

		public String getAppSecret() {
			return appSecret;
		}
 
	}

}
