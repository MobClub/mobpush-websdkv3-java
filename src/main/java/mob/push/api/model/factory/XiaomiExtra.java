package mob.push.api.model.factory;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 小米厂商配置参数
 */
@Getter
@Setter
@Builder
public class XiaomiExtra {

    /**
     * 小米渠道Id
     * 适配定制化渠道
     * 比如: 小米运营消息
     * extra.channel_id
     */
    private String channelId;
}
