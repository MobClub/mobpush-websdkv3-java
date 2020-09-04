package mob.push.api.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

/**
 * 短信补量配置
 * @author wuch
 */
@Getter
@Setter
public class PushSms implements Serializable {
    private static final long serialVersionUID = -6141124345583714923L;

    public static final int COMPENSATE_COMMON = 1;
    public static final int COMPENSATE_FAC_FAIL = 2;

    /**
     * 短信模板id
     * NotEmpty(message = "短信模板id不能为空"
     */
    private String templateId;

    /**
     * 短信签名
     * NotEmpty(message = "短信签名不能为空")
     */
    private String sign;

    /**
     * 延时秒数
     * NotNull(message = "短信延时时间不能为空")
     * Min(value = 10, message = "短信延时时间最短10秒")
     */
    private Integer delaySeconds = 7200;

    /**
     * rid 匹配关系补充
     * ridPhones 匹配关系优先级大于数据库中存储的匹配关系
     */
    private Map<String, String> ridPhones;

    /**
     * alias 匹配关系补充
     * aliasPhones 匹配关系的优先级比 ridPhones 高
     */
    private Map<String, String> aliasPhones;

    /**
     * 默认1
     * 1. tcp和厂商推送都无法走时, 尝试短信下发
     * 2. 厂商推送失败时, 也走短信下发, 可能会产生较高的短信费用. 例如: 华为关键字黑名单, Vivo限流等
     */
    private Integer compensateType = 1;

}

