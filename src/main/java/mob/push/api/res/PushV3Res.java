package mob.push.api.res;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PushV3Res implements Serializable {
    private static final long serialVersionUID = -5378406587583064259L;
    private String batchId;
}