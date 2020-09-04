package mob.push.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 推送任务结构
 *
 * @author wuch
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Push implements Serializable {
    public static final Integer TASK_CRON_ENABLE = 1;
    public static final Integer BUSINESS_TYPE_AD = 1;
    public static final Integer IOS_PROD = 1;
    public static final Integer IOS_DEV = 0;
    private static final long serialVersionUID = -1914482721382496441L;

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
    private PushNotify pushNotify= new PushNotify();

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
    protected PushSms pushSms;

    public static void main(String[] args) {
        Push push = new Push();
    }
}
