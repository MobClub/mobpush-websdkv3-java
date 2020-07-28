package mob.push.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 短信补量
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PushSmsSupply {
    /**
     * NotEmpty(message = "短信补量rid不能为空")
     */
    private String rid;
    private String phone;

}
