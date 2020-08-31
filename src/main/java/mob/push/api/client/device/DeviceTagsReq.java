package mob.push.api.client.device;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author tianzong
 * @Date: 2020-08-31 15:08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceTagsReq implements Serializable {

    /**
     * 应用key
     */
    public String appkey;

    /**
     * 设备Registration Id
     */
    public String registrationId;

    /**
     * 操作类型：1新增标签；2删除标签；3清除所有
     */
    public Integer opType;

    /**
     * 标签
     */
    public String[] tags;

}
