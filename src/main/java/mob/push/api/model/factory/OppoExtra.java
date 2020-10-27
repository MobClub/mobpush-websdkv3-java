package mob.push.api.model.factory;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * OPPO厂商配置参数
 */
@Getter
@Setter
@Builder
public class OppoExtra {

    /**
     * OPPO渠道Id
     * 适配定制化渠道
     * channel_id
     */
    private String channelId;
}
