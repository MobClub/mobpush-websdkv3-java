package mob.push.api.client.device;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import mob.push.api.config.MobPushConfig;
import mob.push.api.exception.ApiException;
import mob.push.api.http.Http;
import mob.push.api.http.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tianzong
 * @Date: 2020-08-31 00:38
 */
public class DeviceV3Client {

    public static final String GET_BY_RID = "/device-v3/getById/";
    public static final String GET_DEVICE_DISTRIBUTION = "/device-v3/distribution/";
    public static final String GET_BY_ALIAS = "/device-v3/getByAlias/";
    public static final String UPDATE_ALIAS = "/device-v3/updateAlias/";
    public static final String UPDATE_TAGS = "/device-v3/updateTags";
    public static final String QUERY_BY_TAGS = "/device-v3/queryByTags";

    public static Result<DeviceRes> getByRid(String registrationId) {
        Map<String, Object> params = new HashMap<>();
        params.put("appkey", MobPushConfig.appkey);
        params.put("registrationId", registrationId);
        Result<DeviceRes> result= Http.get(MobPushConfig.baseUrl + GET_BY_RID+registrationId,null, JSON.toJSONString(params),
                DeviceRes.class);
        if (!result.success()) {
            throw new ApiException(result);
        }
        return result;
    }

    public static Result<DeviceDistribution> getDeviceDistribution(){
        Map<String, Object> params = new HashMap<>();
        params.put("appkey", MobPushConfig.appkey);
        Result<DeviceDistribution> result= Http.get(MobPushConfig.baseUrl + GET_DEVICE_DISTRIBUTION,null, JSON.toJSONString(params),
                DeviceDistribution.class);
        if (!result.success()) {
            throw new ApiException(result);
        }
        return result;
    }

    public static Result<DeviceRes> queryByAlias(String alias) {
        Map<String, Object> params = new HashMap<>();
        params.put("appkey", MobPushConfig.appkey);
        params.put("alias", alias);
        Result<DeviceRes> result= Http.get(MobPushConfig.baseUrl + GET_BY_ALIAS+alias,null, JSON.toJSONString(params),
                DeviceRes.class);
        if (!result.success()) {
            throw new ApiException(result);
        }
        return result;
    }

    public static Result<DeviceRes> updateAlias(String alias, String registrationId){
        DeviceAliasReq deviceAliasReq=DeviceAliasReq.builder()
                .appkey(MobPushConfig.appkey)
                .alias(alias)
                .registrationId(registrationId)
                .build();
        Result<DeviceRes> result = Http.post(MobPushConfig.baseUrl + UPDATE_ALIAS,
                null, JSON.toJSONString(deviceAliasReq), DeviceRes.class);
        if (!result.success()) {
            throw new ApiException(result);
        }
        return result;
    }

    public static Result<DeviceRes> updateTags(String[] tags, String registrationId, Integer opType){
        DeviceTagsReq deviceTagsReq=DeviceTagsReq.builder()
                .appkey(MobPushConfig.appkey)
                .tags(tags)
                .registrationId(registrationId)
                .opType(opType)
                .build();
        Result<DeviceRes> result = Http.post(MobPushConfig.baseUrl + UPDATE_TAGS,
                null, JSON.toJSONString(deviceTagsReq), DeviceRes.class);
        if (!result.success()) {
            throw new ApiException(result);
        }
        return result;
    }

    public static Result<List<DeviceRes>> queryByTags(String[] tags){
        DeviceTagsReq deviceTagsReq=DeviceTagsReq.builder()
                .appkey(MobPushConfig.appkey)
                .tags(tags)
                .build();
        Result<List<DeviceRes>> result = Http.post(MobPushConfig.baseUrl + QUERY_BY_TAGS,
                null, JSON.toJSONString(deviceTagsReq),new TypeReference<List<DeviceRes>>(){}.getType());
        if (!result.success()) {
            throw new ApiException(result);
        }
        return result;
    }

}
