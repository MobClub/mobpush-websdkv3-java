package mob.push.api.res;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AppDeviceStatsRes {

    protected String id;

    private String rid;

    private String alias;

    private String guardId;

    private String tag;

    private String workId;

    private String patchId;

    private List<String> deliver;

    private List<Long> deliverTime;

    private List<String> report;

    private List<Long> reportTime;

    private List<String> click;

    private List<Long> clickTime;
}
