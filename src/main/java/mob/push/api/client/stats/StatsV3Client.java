package mob.push.api.client.stats;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import mob.push.api.config.MobPushConfig;
import mob.push.api.exception.ApiException;
import mob.push.api.http.Http;
import mob.push.api.http.Result;
import mob.push.api.page.PageData;
import mob.push.api.res.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsV3Client {

    public static final String GET_BY_WORKID_URI = "/v3/stats/getByWorkId";
    public static final String GET_BY_WORKIDS_URI = "/v3/stats/getByWorkIds";
    public static final String GET_BY_WORKNO_URI = "/v3/stats/getByWorkno";
    public static final String GET_BY_HOUR_URI = "/v3/stats/getByHour";
    public static final String GET_BY_DAY_URI = "/v3/stats/getByDay";
    public static final String GET_BY_DEVICE_URI = "/v3/stats/getByDevice";


    public static Result<StatsV3Res> getStatsByWorkId(String workId) {
        Map<String, Object> params = new HashMap<>();
        params.put("workId", workId);
        StatsV3Res statsV3Res=new StatsV3Res();
        Result<StatsV3Res> result =Http.getResult(params,statsV3Res,GET_BY_WORKID_URI );
        return result;
    }

    public static Result<List<StatsV3Res>> getStatsByWorkIds(List<String> workIds) {
        Map<String, Object> params = new HashMap<>();
        params.put("appkey", MobPushConfig.appkey);
        params.put("workIds", workIds);
        Result<List<StatsV3Res>> result = Http.post(MobPushConfig.baseUrl + GET_BY_WORKIDS_URI,
                null, JSON.toJSONString(params),
                new TypeReference<List<StatsV3Res>>(){}.getType());
        if (!result.success()) {
            throw new ApiException(result);
        }
        return result;
    }

    public static Result<StatsV3Res> getStatsByWorkno(String workno) {
        Map<String, Object> params = new HashMap<>();
        params.put("workno", workno);
        StatsV3Res statsV3Res=new StatsV3Res();
        Result<StatsV3Res> result =Http.getResult(params,statsV3Res,GET_BY_WORKNO_URI );
        return result;
    }

    public static Result<AppHourStatsRes> getStatsByHour(String hour) {
        Map<String, Object> params = new HashMap<>();
        params.put("hour", hour);
        AppHourStatsRes appHourStatsRes=new AppHourStatsRes();
        Result<AppHourStatsRes> result =Http.getResult(params,appHourStatsRes,GET_BY_HOUR_URI);
        return result;
    }

    public static Result<AppDayStatsRes> getStatsByDay(String day) {
        Map<String, Object> params = new HashMap<>();
        params.put("day", day);
        AppDayStatsRes appDayStatsRes=new AppDayStatsRes();
        Result<AppDayStatsRes> result =Http.getResult(params,appDayStatsRes,GET_BY_DAY_URI);
        return result;
    }

    public static Result<PageData<AppDeviceStatsRes>>  getStatsByDevice(String workId, int pageIndex, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("workId", workId);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        params.put("appkey", MobPushConfig.appkey);
        Result<PageData<AppDeviceStatsRes>> result = Http.post(MobPushConfig.baseUrl + GET_BY_DEVICE_URI,
                null, JSON.toJSONString(params),
                new TypeReference<PageData<AppDeviceStatsRes>>(){}.getType());
        if (!result.success()) {
            throw new ApiException(result);
        }
        return result;
    }

}
