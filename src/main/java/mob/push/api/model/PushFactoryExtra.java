package mob.push.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mob.push.api.model.factory.HuaweiExtra;
import mob.push.api.model.factory.OppoExtra;
import mob.push.api.model.factory.VivoExtra;
import mob.push.api.model.factory.XiaomiExtra;

/**
 * 厂商推送参数
 */
@Getter
@Setter
@Builder
public class PushFactoryExtra {
    private XiaomiExtra xiaomiExtra;
    private HuaweiExtra huaweiExtra;
    private OppoExtra oppoExtra;
    private VivoExtra vivoExtra;
}
