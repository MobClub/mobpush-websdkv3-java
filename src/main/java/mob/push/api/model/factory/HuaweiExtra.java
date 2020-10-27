package mob.push.api.model.factory;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 华为厂商配置参数
 */
@Getter
@Setter
@Builder
public class HuaweiExtra {

    /**
     * 华为渠道Id
     * 适配定制化渠道
     * AndroidNotification.channel_id
     */
    private String channelId;

    /**
     * app在前端时是否展示推送
     */
    private Boolean foregroundShow;

    /**
     * 透传消息投递优先级，取值如下：
     *
     * HIGH
     * NORMAL
     * 设置为HIGH时需要申请权限，请参见特殊权限如何申请？(https://developer.huawei.com/consumer/cn/doc/HMSCore-Guides-V5/faq-0000001050042183-V5#ZH-CN_TOPIC_0000001050042183__p1582519236460)。
     *
     * HIGH级别消息到达用户手机时可强制拉起应用进程。
     */
    private String urgency;
}
