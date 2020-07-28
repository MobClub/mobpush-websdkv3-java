package mob.push.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 运营保障
 * @author wuch
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PushOperator  implements Serializable {
    /**
     * 运营保障 消息 修改类型： 1 取消 2 替换 3 撤回
     */
    protected Integer dropType;

    /**
     * 撤回的消息的id
     */
    protected String dropId;

    /**
     * 给每条消息设置唯一的消息id
     * 有替换和撤回
     * 1 根据workId 查询到 原来的消息id
     * 2 如果是替换 直接 重置 notifyId
     * 3 如果是撤回 设置dropId 为 notifyId 通知客户端去撤回那条消息
     */
    protected String notifyId;

}
