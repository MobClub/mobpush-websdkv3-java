package mob.push.api.utils;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SetUtil {

    @SafeVarargs
    public static <T> Set<T> newSet(T... params) {
        return Arrays.stream(params).collect(Collectors.toSet());
    }

}
