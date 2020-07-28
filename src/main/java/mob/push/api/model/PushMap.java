package mob.push.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author shhgeng
 * 2020-03-19 18:33
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PushMap implements Serializable {
    private String key;
    private String value;

}
