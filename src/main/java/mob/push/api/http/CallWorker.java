package mob.push.api.http;

import okhttp3.Call;
import okhttp3.Response;

public interface CallWorker<T> {
    void call(Result<T> result, Call call, Response response);
}
