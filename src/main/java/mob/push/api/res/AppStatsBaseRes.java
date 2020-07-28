package mob.push.api.res;

import lombok.Getter;
import lombok.Setter;

/**
 * 描述:
 *
 * @author wuch
 * 2019-09-25 21:10
 */
@Getter
@Setter
public class AppStatsBaseRes {

    private Long fetchNum = 0L;

    private Long fetchNumAndroid = 0L;

    private Long fetchNumIos = 0L;

    /**
     * 推送任务数
     */
    private Long workNum = 0L;

    private Long workNumAndroid = 0L;

    private Long workNumIos = 0L;


    private Long deliverNumTcp = 0L;

    private Long reportNumTcp = 0L;

    private Long clickNumTcp = 0L;


    private Long deliverNumGuard = 0L;

    private Long reportNumGuard = 0L;

    private Long clickNumGuard = 0L;


    private Long deliverNumUdp = 0L;

    private Long reportNumUdp = 0L;

    private Long clickNumUdp = 0L;

    /**
     * 消息下发数
     */
    private Long deliverNum = 0L;

    private Long deliverNumAndroid = 0L;

    private Long deliverNumIos = 0L;

    /**
     * 推送回执数
     */
    private Long reportNum = 0L;

    private Long reportNumAndroid = 0L;

    private Long reportNumIos = 0L;

    /**
     * 推送点击数
     */
    private Long clickNum = 0L;

    private Long clickNumAndroid = 0L;

    private Long clickNumIos = 0L;

    /**
     * Api调用次数
     */

    private Long apiNum = 0L;

    private Long apiNumAndroid = 0L;

    private Long apiNumIos = 0L;

    /**
     * 设备活跃数量
     */
    private Long activeNum = 0L;

    private Long activeNumAndroid = 0L;

    private Long activeNumIos = 0L;

    /**
     * 新增设备数量
     */
    private Long newDeviceNum = 0L;

    private Long newDeviceNumAndroid = 0L;

    private Long newDeviceNumIos = 0L;

    /**
     * 设备平均在线时长
     */
    private Long onlineTimeAvg = 0L;
    private Long onlineTimeAvgAndroid = 0L;
    private Long onlineTimeAvgIos = 0L;

    /**
     * 平均在线时间
     */
    private Long onlineTimeAll = 0L;

    private Long onlineTimeAllAndroid = 0L;

    private Long onlineTimeAllIos = 0L;

    /**
     * 平均打开次数
     */
    private Long openAppAvg = 0L;
    private Long openAppAvgAndroid = 0L;
    private Long openAppAvgIos = 0L;

    /**
     * 设备打开次数总数
     */
    private Long openAppAll = 0L;

    private Long openAppAllAndroid = 0L;

    private Long openAppAllIos = 0L;

}
