package mob.push.api.model.factory;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Vivo厂商配置参数
 */
@Getter
@Setter
@Builder
public class VivoExtra {

    /**
     * Vivo渠道Id
     * 适配定制化渠道
     * notification_channel
     */
    private String channelId;
}
