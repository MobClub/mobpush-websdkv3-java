package mob.push.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * 短信补量配置
 * @author wuch
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PushSms implements Serializable {

    /**
     * 指定补量范围
     * 1广播；
     * 2别名；
     * 3标签；
     * 4regid；
     *
     * NotNull
     * Determine(values = {1, 2, 3, 4}, message = "短信补量target错误")
     */
    private Integer target;

    /**
     * 短信模板id
     * NotEmpty(message = "短信模板id不能为空")
     */
    private String templateId;

    /**
     * 模板自定义参数
     * 例如：
     * 模板：【XXX签名】您的收益{income}元已经到账，请及时查收。
     * templateParams: {"income":"8.8"}
     */
    private Map<String, String> templateParams;

    /**
     * 补量别名
     */
    private Set<String> alias;

    /**
     * 补量tags, 含有这些表情的设备全补量
     */
    private Set<String> tags;

    /**
     * 补量rid
     */
    private Set<String> rids;

}

