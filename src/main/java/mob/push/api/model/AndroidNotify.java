package mob.push.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 推送内容-- android通知消息
 *
 * @author wuch
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AndroidNotify implements Serializable {

    /**
     * 通知标题
     */
    private String appName;

    /**
     * 如果不设置，则默认的通知标题为应用的名称。
     * Length(max = 20, message = "推送标题最大长度20")
     */
    private String title;

    /**
     * warn:  提醒类型： 1提示音；2震动；3指示灯
     * 如果多个组合则对应编号组合如：12 标识提示音+震动
     */
    @Builder.Default
    private String warn = "12";

    /**
     * 显示样式标识  0、默认通知无； 1、长内容则为内容数据； 2、大图则为图片地址； 3、横幅则为多行内容
     * Determine(values = {0, 1, 2, 3}, message = "安卓消息格式参数错误")
     */
    @Builder.Default
    private Integer style = 0;

    /**
     * content:  style样式具体内容
     */
    private String[] content;

    /**
     * 自定义声音
     */
    private String sound;

}
