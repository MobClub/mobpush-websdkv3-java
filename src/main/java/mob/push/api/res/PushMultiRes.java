package mob.push.api.res;

import lombok.Data;

import java.util.Map;

@Data
public class PushMultiRes {
    private Map<String, String> batchIds;
    private Map<String, String> errors;
}
