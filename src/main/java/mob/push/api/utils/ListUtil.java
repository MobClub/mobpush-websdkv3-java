package mob.push.api.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListUtil {

    @SafeVarargs
    public static <T> List<T> newList(T... params) {
        return Arrays.stream(params).collect(Collectors.toList());
    }

}
