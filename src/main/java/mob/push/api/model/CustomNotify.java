package mob.push.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 推送内容-- 自定义消息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomNotify  implements Serializable {

	/**
	 * 自定义消息类型：text 文本消息
	 * CustomTypeEnum
	 */
	private String customType;

	/**
	 * 自定义类型标题
	 */
	private String customTitle;

}
