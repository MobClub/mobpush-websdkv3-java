package mob.push.api.model;

import lombok.Data;

import java.util.List;

/**
 * 单个标签类型组合方式
 * 标签搭配方式, 1:与, 2:或, 3:非
 * 比如:
 * 1. 年龄标签或组合
 * {
 *     "mobId": 2,
 *     "tags": ["35-40岁", "40-45岁"]
 * }
 * 2. 人群组合 出行 & 理财
 * {
 *     "mobId": 1,
 *     "tags": ["出行达人", "理财达人"]
 * }
 *
 * @author wuch
 */
@Data
public class PushLabel {
    /**
     * 标签列表, 内容是 SmartLabel.id
     * 标签Id不能为空
     */
    private List<String> labelIds;

    /**
     * mobId不能为空
     */
    private String mobId;

    /**
     * 标签搭配方式, 1:与, 2:或, 3:非
     */
    private int type = 2;

    public static final int AND = 1;
    public static final int OR = 2;
    public static final int NOT = 3;
}
