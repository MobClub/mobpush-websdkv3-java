package mob.push.api.http;

import lombok.Data;

@Data
public class Result<T> {
    public static final Integer ERROR = 500;
    public static final Integer SUCCESS = 200;

    private int responseCode = 200;
    private int status = 200;
    private T res;
    private String error;

    public boolean success() {
        return status == 200;
    }

    public static <T> Result<T> newSuccess() {
        Result<T> res = new Result<>();
        res.status = 200;
        return res;
    }

    public static <T> Result<T> newSuccess(T data) {
        Result<T> res = new Result<>();
        res.status = 200;
        res.res = data;
        return res;
    }

    public static <T> Result<T> newError(int code) {
        Result<T> res = new Result<>();
        res.status = code;
        res.error = "ERROR";
        return res;
    }

    public static <T> Result<T> newError(int code, String error) {
        Result<T> res = new Result<>();
        res.status = code;
        res.error = error;
        return res;
    }

    public static Result<?> newServerError(String error) {
        Result<?> res = new Result<>();
        res.setResponseCode(500);
        res.status = 500;
        res.error = error;
        return res;
    }
}