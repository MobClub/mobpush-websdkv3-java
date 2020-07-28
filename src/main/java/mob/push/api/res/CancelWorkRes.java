package mob.push.api.res;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class CancelWorkRes implements Serializable {

    private static final long serialVersionUID = -5708586329117384238L;
    private String batchId;
    /** 0 取消失败 1 取消成功 */
    private Integer code;
    /** 任务状态  1创建中；2等待发送；3发送中；4发送完成；5发送失败；6停止发送 7已取消 8 取消成功 9 已撤回*/
    private Integer status;
    /** 错误消息 **/
    private String errorMsg;
}
