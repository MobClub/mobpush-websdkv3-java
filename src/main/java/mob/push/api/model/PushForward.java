package mob.push.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 引用 linkSdk 功能
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PushForward implements Serializable {

    /**
     * 0 打开首页 1 url跳转 2  scheme 跳转
     */
    @Builder.Default
    private Integer nextType = 0;

    /**
     * 跳转
     */
    private String url;
    /**
     * scheme功能的的uri
     */
    private String scheme;
    /**
     * moblink功能的参数 a=123&b=345
     */
    @Builder.Default
    private List<PushMap> schemeDataList = new ArrayList<>();
}
