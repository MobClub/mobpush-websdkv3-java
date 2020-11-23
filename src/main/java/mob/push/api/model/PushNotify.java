package mob.push.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mob.push.api.utils.SetUtil;

import java.io.Serializable;
import java.util.*;

/**
 * 推送展示细节配置
 *
 * @author wuch
 */
@Data
@Builder()
@NoArgsConstructor
@AllArgsConstructor
public class PushNotify implements Serializable {
    /**
     * 是否是定时任务：0否，1是，默认0
     */
    @Builder.Default
    protected Integer taskCron = 0;
    /**
     * 定时消息 发送时间
     */
    protected Long taskTime;

    /**
     * 定速推送, 设置平均每秒推送速度
     * 0: 不限制
     * 其他限制速度
     * 例如: 每秒1条 每秒100条, 建议最小设置为100条
     * 这个只是模糊的控制, 只保证推送整体上的平均数值, 比如设置为1, 每5秒推送一条
     */
    protected Integer speed = 0;

    /**
     * 推送时, 跳过在线设备, 不对在线设备做推送
     * 1: 开启
     * 其他: 关闭
     */
    protected Integer skipOnline;

    /**
     * 可使用平台，1 android;2 ios ;3 winphone(暂不使用) ;
     */
    @Builder.Default
    protected Set<Integer> plats = SetUtil.newSet(1, 2);

    /**
     * plat = 2下，0测试环境，1生产环境，默认1
     */
    @Builder.Default
    protected Integer iosProduction = 1;

    /**
     * 离线时间，秒
     */
    @Builder.Default
    protected Integer offlineSeconds = 3600;

    /**
     * 推送内容
     * NotEmpty(message = "推送消息不能为空")
     */
    protected String content;


    /**
     * 推送标题
     */
    protected String title;

    /**
     * 推送类型：1通知；2自定义
     * NotNull(message = "消息类型不能为空")
     * Determine(values = {1, 2}, message = "消息类型1：通知，2：自定义")
     */
    @Builder.Default
    protected Integer type = 1;

    /**
     * 自定义内容, type=2
     * CustomMessage.class
     */
    protected CustomNotify customNotify;

    /**
     * android通知消息, type=1, android
     * AndroidNotify.class
     */
    protected AndroidNotify androidNotify;

    /**
     * ios通知消息, type=1, ios
     * IosNotify.class
     */
    protected IosNotify iosNotify;

    /**
     * 打开链接
     */
    protected String url;

    /**
     * 附加字段键值对的方式
     */
    @Builder.Default
    protected List<PushMap> extrasMapList = new ArrayList<>();

}
