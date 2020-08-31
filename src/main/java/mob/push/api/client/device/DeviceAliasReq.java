package mob.push.api.client.device;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author tianzong
 * @Date: 2020-08-31 01:31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceAliasReq implements Serializable {

    private String registrationId;

    private String appkey;

    private String alias;
}
