package mob.push.api.res;

import lombok.Data;

import java.io.Serializable;

/**
 * 推送任务详情统计 2017年9月26日 下午6:47:34 hlliu@youzu.com
 */
@Data
public class StatsV3Res implements Serializable {
    private static final long serialVersionUID = -258327454138179322L;

    /*
     * 主键 , 批次编号(同WorkDetail主键)
     */

    private String id;

    /**
     * 创建时间
     */
    private Long createAt = System.currentTimeMillis();

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    /**
     * 更新时间
     */
    private Long updateAt;

    /**
     * 第三方ID
     */
    private String workno;

    /**
     * 应用APPKEY
     */
    private String appkey;

    /**
     * 属性名 StatCategoryEnum  的constName 相同 一一对应
     */
    private WorkStatCountDetail android;
    private WorkStatCountDetail ios;
    private WorkStatCountDetail factory;
    private WorkStatCountDetail iostcp;
    private WorkStatCountDetail androidtcp;
    private WorkStatCountDetail apns;
    private WorkStatCountDetail huawei;
    private WorkStatCountDetail xiaomi;
    private WorkStatCountDetail flyme;
    private WorkStatCountDetail fcm;
    private WorkStatCountDetail oppo;
    private WorkStatCountDetail vivo;
    private WorkStatCountDetail offlineIos;
    private WorkStatCountDetail offlineAndroid;
    private WorkStatCountDetail offlineDrop;
    private WorkStatCountDetail offlineVivo;

    /**
     * @author shhgeng
     * 2020-01-02 18:11
     */
    @Data
    public static class WorkStatCountDetail {

        /** 目标数 */
        public Long fetchNum = 0L;
        /** 下发数 */
        public Long deliverNum = 0L;
        /** 下发失败数 */
        public Long deliverFailNum = 0L;
        /** 回执数目 */
        public Long reportNum = 0L;
        /** 点击数目 */
        public Long clickNum = 0L;

    }
}
