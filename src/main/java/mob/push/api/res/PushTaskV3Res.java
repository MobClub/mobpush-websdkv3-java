package mob.push.api.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mob.push.api.model.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PushTaskV3Res implements Serializable {

    public static final Integer TASK_CRON_ENABLE = 1;
    public static final Integer BUSINESS_TYPE_AD = 1;
    public static final Integer IOS_PROD = 1;
    public static final Integer IOS_DEV = 0;
    private static final long serialVersionUID = 3365392369623543343L;

    /**
     * 推送任务标识，对接用户服务端唯一ID，传入后原样返回（用户服务端自有）
     */
    protected String workno;

    /**
     * 推送任务来源：webapi 、developerPlatform
     *
     * StrDetermine(values = {"webapi", "upsapi", "sdkapi", "devplat"}, message = "消息源格式错误")
     */
    @Builder.Default
    protected String source = "webapi";

    /**
     * NotEmpty(message = "[appkey]不能为空")
     */
    protected String appkey;

    /**
     * NotNull(message = "[pushTarget]不能为空")
     */
    private PushTarget pushTarget;

    /**
     * 推送展示细节
     * NotNull(message = "[pushNotify]不能为空")
     */
    private PushNotify pushNotify;

    /**
     * 运营保障相关配置
     */
    @Builder.Default
    private PushOperator pushOperator = new PushOperator();

    /**
     * link 相关打开配置
     */
    private PushForward pushForward;

    /**
     * 短信补量相关配置
     */
//    protected PushSms pushSms;

}
