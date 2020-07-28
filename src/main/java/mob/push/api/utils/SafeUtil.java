package mob.push.api.utils;

public class SafeUtil {

    public static <T> void nullExec(T data, Worker<T> worker) {
        if (!BaseUtils.isEmpty(data)) {
            safeExec(worker);
        }
    }

    public static <T> void nullExec(T data, WorkerP1<T> worker) {
        if (!BaseUtils.isEmpty(data)) {
            safeExec(() -> worker.process(data));
        }
    }

    public static <T> void nullExec(T data, Worker<T> worker, RuntimeException e) {
        if (!BaseUtils.isEmpty(data)) {
            safeExec(worker);
        }
        throw e;
    }

    public static <T> void nullExec(T data, WorkerP1<T> worker, RuntimeException e) {
        if (!BaseUtils.isEmpty(data)) {
            safeExec(() -> worker.process(data));
        }
        throw e;
    }

    public static <T, R> R nullExecRes(T data, RWorker<T, R> worker, R defaultValue) {
        if (!BaseUtils.isEmpty(data)) {
            return safeExecRes(worker::process);
        }
        return defaultValue;
    }

    public static <T, R> R nullExecRes(T data, RWorkerP1<T, R> worker, R defaultValue) {
        if (!BaseUtils.isEmpty(data)) {
            return safeExecRes(() -> worker.process(data));
        }
        return defaultValue;
    }

    public static <T, R> R nullExecResThrow(T data, RWorkerP1<T, R> worker, RuntimeException e) {
        if (!BaseUtils.isEmpty(data)) {
            return safeExecRes(() -> worker.process(data));
        }
        throw e;
    }

    public static void safeExec(Worker<?> worker) {
        try {
             worker.process();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T, R> R safeExecRes(RWorker<T, R> worker) {
        try {
             return worker.process();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public interface Worker<T> {
        void process() throws Exception;
    }

    public interface WorkerP1<T> {
        void process(T data) throws Exception;
    }

    public interface RWorker<T, R> {
        R process() throws Exception;
    }

    public interface RWorkerP1<T, R> {
        R process(T data) throws Exception;
    }


}
