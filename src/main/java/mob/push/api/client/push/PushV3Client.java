package mob.push.api.client.push;

import com.alibaba.fastjson.JSON;
import mob.push.api.builder.PushWorkBuilder;
import mob.push.api.config.MobPushConfig;
import mob.push.api.exception.ApiException;
import mob.push.api.http.Http;
import mob.push.api.http.Result;
import mob.push.api.model.*;
import mob.push.api.res.CancelWorkRes;
import mob.push.api.res.PushTaskV3Res;
import mob.push.api.res.PushV3Res;
import java.util.HashMap;
import java.util.Map;



public class PushV3Client {

    public static final String PUSH_URI = "/v3/push/createPush";
    public static final String GET_BY_WORKID_URI = "/v3/push/getByWorkId";
    public static final String GET_BY_WORKNO_URI = "/v3/push/getByWorkno";
    public static final String CANCEL_TASK_URI = "/push/drop";
    public static final String REPLACE_TASK_URI = "/push/replace";
    public static final String RECALL_TASK_URI = "/push/recall";


    public static Result<PushV3Res> pushTaskV3(Push push) {
        push.setAppkey(MobPushConfig.appkey);
        Result<PushV3Res> result = Http.post(MobPushConfig.baseUrl + PUSH_URI,
                null, JSON.toJSONString(push), PushV3Res.class);
        if (!result.success()) {
            throw new ApiException(result);
        }
        return result;
    }

    public static Result<PushV3Res> pushAll(String workNo, String title, String content) {
        return pushTaskV3(new PushWorkBuilder()
                .setTargetAll(workNo,title,content)
                .build());
    }

    public static Result<PushV3Res> pushByAlias(String workNo, String title, String content, String... alias) {
        return pushTaskV3(new PushWorkBuilder()
                .setTargetByAlias(workNo,title,content,alias)
                .build());
    }

    public static Result<PushV3Res> pushByTags(String workNo, String title, String content, String... tags) {
        return pushTaskV3(new PushWorkBuilder()
                .setTargetTags(workNo,title,content,tags)
                .build());
    }

    public static Result<PushV3Res> pushByRids(String workNo, String title, String content, String... rids) {
        return pushTaskV3(new PushWorkBuilder()
                .setTargetRids(workNo,title,content,rids)
                .build());
    }

    public static Result<PushV3Res> pushByAreas(String workNo, String title, String content, PushAreas pushAreas) {
        return pushTaskV3(new PushWorkBuilder()
                .setTargetByAreas(workNo,title,content,pushAreas)
                .build());
    }

    public static Result<CancelWorkRes> cancelPushTask(String workId) {
        Map<String, Object> params = new HashMap<>();
        params.put("batchId", workId);
        CancelWorkRes cancelWorkRes=new CancelWorkRes();
        Result<CancelWorkRes> result =Http.getResult(params,cancelWorkRes,CANCEL_TASK_URI );
        return result;
    }

    public static Result<CancelWorkRes> replacePushTask(String workId,String content) {
        Map<String, Object> params = new HashMap<>();
        params.put("batchId", workId);
        params.put("content", content);
        CancelWorkRes cancelWorkRes=new CancelWorkRes();
        Result<CancelWorkRes> result =Http.getResult(params,cancelWorkRes,REPLACE_TASK_URI );
        return result;
    }

    public static Result<CancelWorkRes> recallPushTask(String workId) {
        Map<String, Object> params = new HashMap<>();
        params.put("batchId", workId);
        CancelWorkRes cancelWorkRes=new CancelWorkRes();
        Result<CancelWorkRes> result =Http.getResult(params,cancelWorkRes,RECALL_TASK_URI );
        return result;
    }

    public static Result<PushTaskV3Res> getPushByBatchId(String batchId) throws ApiException {
        Map<String, Object> params = new HashMap<>();
        params.put("workId", batchId);
        PushTaskV3Res pushTaskV3Res=new PushTaskV3Res();
        Result<PushTaskV3Res> result =Http.getResult(params,pushTaskV3Res,GET_BY_WORKID_URI );
        return result;
    }

    /**
     * 只能获取到3天之内的任务，长时间的任务请使用 getPushByBatchId 方法获取
     */
    public static Result<PushTaskV3Res> getPushByWorkno(String workno) throws ApiException {
        Map<String, Object> params = new HashMap<>();
        params.put("workno", workno);
        PushTaskV3Res pushTaskV3Res=new PushTaskV3Res();
        Result<PushTaskV3Res> result =Http.getResult(params,pushTaskV3Res,GET_BY_WORKNO_URI );
        return result;
    }


}
