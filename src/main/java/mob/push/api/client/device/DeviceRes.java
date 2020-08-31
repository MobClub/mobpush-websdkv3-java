package mob.push.api.client.device;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * @author tianzong
 * @Date: 2020-08-31 00:40
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceRes implements Serializable {

    private String registrationId;
    private Set<String> tags;
    private String alias;
    private String mobile;
    private Integer openPush;
    private Integer status;
}
