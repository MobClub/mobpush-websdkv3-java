package mob.push.api.model.factory;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Vivo厂商配置参数
 */
@Getter
@Setter
@Builder
public class VivoExtra {

    /** 系统消息 */
    public static final Integer CLASSIFICATION_SYSTEM = 1;
    /** 运营线消息 */
    public static final Integer CLASSIFICATION_OPERATE = 0;

    /**
     * Vivo渠道Id
     * 适配定制化渠道
     * notification_channel
     */
    private String channelId;

    /**
     * 消息类型 0：运营类消息，1：系统类消息
     * ①系统消息只能通过API接口创建，web端运营平台暂不支持此功能。API接口新增请求字段classification，“0”代表运营消息，“1”代表系统消息，不填默认为0。
     * ①参数传“0”代表运营消息，不经过智能分类二次修正，直接从运营消息总量扣除额度，并受用户接收条数限制的频控；
     * ②参数传“1”代表系统消息，经过智能分类二次修正，若智能分类识别出不是系统消息，会自动修正为运营消息，并扣除运营消息额度；若识别为系统消息，则从系统消息总量扣除额度。
     * ③部分特殊情况下，会出现误判，智能分类可能会将系统消息判定为运营消息。发生此情况时，请按下面误判反馈邮件模板填写发送至：push@vivo.com
     */
    private Integer classification;

}
