package mob.push.api.res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppDayStatsRes extends AppStatsBaseRes{

    /**
     * appkey_day
     */
    private String id;


    private String appkey;
    /**
     * 显示日期
     * yyyyMMdd
     * 20190925
     */
    private String day;
}
