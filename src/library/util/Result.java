package library.util;

import org.json.JSONObject;

/**
 * 控制层返回对象
 */
public class Result {

    private int code;

    private String errMsg;

    private Object data;

    public static Result OK(Object data) {
        Result res = new Result();
        res.code = 200;
        res.data = data;
        res.errMsg = "OK";
        return res;
    }

    public static Result Fail(String errMsg) {
        Result res = new Result();
        res.code = 500;
        res.data = null;
        res.errMsg = errMsg;
        return res;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject(this);
        return jsonObject.toString();
    }
}
