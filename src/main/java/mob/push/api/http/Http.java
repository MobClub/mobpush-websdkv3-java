package mob.push.api.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import mob.push.api.config.MobPushConfig;
import mob.push.api.exception.ApiException;
import mob.push.api.utils.MD5;
import mob.push.api.utils.MobHelper;
import mob.push.api.utils.SafeUtil;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "MobPush")
public class Http {

    private static final OkHttpClient CLIENT;
    private static final String WEB_ERROR;

    public static final MediaType JSON_MEDIA = MediaType.parse("application/json; charset=utf-8");

    static {
        CLIENT = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(32, 5, TimeUnit.SECONDS))
                .build();

        Result<?> webError = new Result<>();
        webError.setStatus(Result.ERROR);
        webError.setError("网络请求异常");
        WEB_ERROR = JSON.toJSONString(webError);
    }

    public static <T> Result<T> get(String url, Map<String, String> headers, Class<T> cls) {
        if (headers == null) {
            headers = new HashMap<>();
        }
        headers.put("sign", serverSign("", MobPushConfig.appSecret));
        headers.put("key", MobPushConfig.appkey);
        //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式。
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(headers))
                .get()
                .build();
        //3.创建一个call对象,参数就是Request请求对象
        return callAndGet(CLIENT.newCall(request), cls, null);
    }

    private static <T> Result<T> callAndGet(Call call, Type cls, String data) {
        CountDownLatch latch = new CountDownLatch(1);
        Result<T> result = new Result<>();
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                try {
                    result.setStatus(Result.ERROR);
                    result.setError("网络请求错误: " + e.getMessage());
                    latch.countDown();
                } finally {
                    latch.countDown();
                }
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    result.setResponseCode(response.code());
                    String res = SafeUtil.nullExecRes(response.body(), ResponseBody::string, WEB_ERROR);
                    log.info("MobRequest response: {}, request: {}", res, data);
                    JSONObject obj = JSON.parseObject(res);
                    Integer status = obj.getInteger("status");
                    if (Result.SUCCESS.equals(status)) {
                        String dataStr = obj.getString("res");
                        T data = JSON.parseObject(dataStr, cls);
                        result.setRes(data);
                    } else {
                        result.setStatus(status);
                        result.setError(obj.getString("error"));
                    }
                } catch (Exception e) {
                    result.setStatus(Result.ERROR);
                    throw e;
                } finally {
                    latch.countDown();
                }
            }
        });
        SafeUtil.safeExec(latch::await);
        return result;
    }

    public static <T> Result<T> post(String url, Map<String, String> headers, String data, Type cls) throws ApiException {
        try {
            if (headers == null) {
                headers = new HashMap<>();
            }
            headers.put("sign", serverSign(data, MobPushConfig.appSecret));
            headers.put("key", MobPushConfig.appkey);
            // 1.body
            RequestBody body = RequestBody.create(JSON_MEDIA, data);

            //2. 创建Request对象，设置一个url地址（百度地址）,设置请求方式。
            Request request = new Request.Builder()
                    .url(url)
                    .headers(Headers.of(headers))
                    .post(body)
                    .build();
            //3.创建一个call对象,参数就是Request请求对象
            return callAndGet(CLIENT.newCall(request), cls, data);
        } catch (Exception e) {
            throw new ApiException(e);
        }
    }

    public static StringResult post(String url, Map<String, String> headers, String data) throws ApiException {
        try {
            if (headers == null) {
                headers = new HashMap<>();
            }
            headers.put("sign", serverSign(data, MobPushConfig.appSecret));
            headers.put("key", MobPushConfig.appkey);
            // 1.body
            RequestBody body = RequestBody.create(JSON_MEDIA, data);

            //2. 创建Request对象，设置一个url地址（百度地址）,设置请求方式。
            Request request = new Request.Builder()
                    .url(url)
                    .headers(Headers.of(headers))
                    .post(body)
                    .build();
            //3.创建一个call对象,参数就是Request请求对象
            return callAndGet(CLIENT.newCall(request), data);
        } catch (Exception e) {
            throw new ApiException(e);
        }
    }

    public static StringResult get(String url, Map<String, String> headers) {
        if (headers == null) {
            headers = new HashMap<>();
        }
        headers.put("sign", serverSign("", MobPushConfig.appSecret));
        headers.put("key", MobPushConfig.appkey);
        //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式。
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(headers))
                .get()
                .build();
        //3.创建一个call对象,参数就是Request请求对象
        return callAndGet(CLIENT.newCall(request),null);
    }

    private static StringResult callAndGet(Call call, String data) {
        CountDownLatch latch = new CountDownLatch(1);
        StringResult res = new StringResult();
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                try {
                    res.setRes(JSON.toJSONString(Result.newServerError("网络请求错误")));
                    res.setResponseCode(500);
                    latch.countDown();
                } finally {
                    latch.countDown();
                }
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    res.setRes(SafeUtil.nullExecRes(response.body(), ResponseBody::string, WEB_ERROR));
                    res.setResponseCode(response.code());
                    log.info("MobRequest response: {}, request: {}", res, data);
                } catch (Exception e) {
                    res.setRes(JSON.toJSONString(Result.newError(500, "请求结果处理异常")));
                    res.setResponseCode(500);
                    throw e;
                } finally {
                    latch.countDown();
                }
            }
        });
        SafeUtil.safeExec(latch::await);
        return res;
    }

    public static String serverSign(String decodeData, String appSecret) {
        return MD5.hash(decodeData + appSecret, MobHelper.CHARSET_UTF8);
    }

    public static  <R> Result<R> getResult(Map<String, Object> params, R res  ,String url){
        params.put("appkey", MobPushConfig.appkey);
        Result<R> result = Http.post(MobPushConfig.baseUrl + url,
                null, JSON.toJSONString(params), res.getClass());
        if (!result.success()) {
            throw new ApiException(result);
        }
        return result;
    }
}
