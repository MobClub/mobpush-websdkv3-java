package mob.push.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 推送内容-- IOS通知消息
 * 2017年9月26日  下午6:23:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IosNotify implements Serializable {
    private static final long serialVersionUID = 6316980682876425791L;

    public static final Integer BADGE_TYPE_SET = 1;
    public static final Integer BADGE_TYPE_ADD = 2;

    /**
     * 标题- 不填写则为应用名称
     */
    private String title;

    /**
     * 副标题
     */
    private String subtitle;

    /**
     * APNs通知，通过这个字段指定声音。默认为default，即系统默认声音。 如果设置为空值，则为静音。
     * 如果设置为特殊的名称，则需要你的App里配置了该声音才可以正常。
     */
    private String sound = "default";

    /**
     * 可直接指定 APNs 推送通知的 badge，未设置这个值角标则不带角标推送
     */
    private Integer badge;

    /**
     * badgeAdd=true 时，增加badge对应的角标数，负数时，算减法
     * 当这个数值设置了值时，会更新数据库数据
     * 未设置这个值角标则不带角标推送
     * 1: 绝对值，2: 修改值
     */
    private Integer badgeType;

    /**
     * 只有IOS8及以上系统才支持此参数推送
     */
    private String category;

    /**
     * 如果只携带content-available: 1,不携带任何badge，sound 和消息内容等参数，
     * 则可以不打扰用户的情况下进行内容更新等操作即为“Silent Remote Notifications”。
     */
    public static final Integer SLIENT = 1;
    private Integer slientPush;

    /**
     * 将该键设为 1 则表示有新的可用内容。带上这个键值，意味着你的 App 在后台启动了或恢复运行了，application:didReceiveRemoteNotification:fetchCompletionHandler:被调用了。
     */
    private Integer contentAvailable;

    /**
     * 需要在附加字段中配置相应参数
     */
    private Integer mutableContent;

    /**
     * ios富文本0无 ；1 图片 ；2 视频 ；3 音频
     */
    private Integer attachmentType;

    private String attachment;

}
