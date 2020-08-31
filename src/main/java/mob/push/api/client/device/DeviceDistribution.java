package mob.push.api.client.device;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tianzong
 * @Date: 2020-08-31 01:22
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DeviceDistribution {
    /**
     * 开启推送
     */
    private Integer openPush;
    /**
     * 关闭推送
     */
    private Integer closePush;
    /**
     * 卸载数量
     */
    private Integer uninstall;
}
