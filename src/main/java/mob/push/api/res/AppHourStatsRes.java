package mob.push.api.res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppHourStatsRes extends AppStatsBaseRes{
    /**
     * appkey_hour
     */
    private String id;

    private String appkey;
    /**
     * 显示日期
     * yyyyMMddHH
     * 2019092500
     * 2019092523
     */
    private String hour;

}
