package mob.push.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author wuch
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PushTarget implements Serializable {

    public static final int TARGET_ALL = 1;
    public static final int TARGET_ALIAS = 2;
    public static final int TARGET_TAGS = 3;
    public static final int TARGET_RIDS = 4;
    public static final int TARGET_AREA = 5;
    public static final int TARGET_LABEL = 5;
    public static final int TARGET_SMS = 8;
    public static final int TARGET_AREAS = 9;
    private static final long serialVersionUID = 3205738723648870635L;

    /**
     * 推送范围:0 全部；1广播；2别名；3标签；4regid；5地理位置；7智能标签;8短信补量; 9复杂地理位置
     * WorkDetailTargetEnum
     *
     * NotNull
     * Determine(values = {1, 2, 3, 4, 5, 7,8, 9}, message = "推送消息target错误")
     */
    protected Integer target;

    /**
     * target:3 => 设置推送标签集合["tag1","tag2"]
     */
    protected Set<String> tags;

    /**
     * target:3 => 标签组合方式：1并集；2交集；3补集(3暂不考虑)
     */
    protected Integer tagsType = 1;

    /**
     * target:2 => 设置推送别名集合["alias1","alias2"]
     */
    protected Set<String> alias;

    /**
     * target:4 => 设置推送Registration Id集合["id1","id2"]
     */
    protected Set<String> rids;

    /**
     * target:6 => 用户分群ID
     */
    protected String block;

    /**
     * target:5 => 推送地理位置
     */
    protected String city;
    protected String country;
    protected String province;

    /**
     *     target:7 => 智能标签列表, 关联关系为与, 必须同时满足条件
     */
    protected List<PushLabel> smartLabels;

    /**
     * target:8 => 短信补量
     */
//    protected List<PushSmsSupply> smsSupply;

    protected PushAreas pushAreas;
}
