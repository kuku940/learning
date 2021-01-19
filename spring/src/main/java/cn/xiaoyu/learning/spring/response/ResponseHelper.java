package cn.xiaoyu.learning.spring.response;

import java.util.List;
import java.util.Map;

public class ResponseHelper {
    public static final String OK = "ok";

    public static final String FAIL = "fail";

    private ResponseHelper() {

    }

    public static Response buildOk() {
        return new MsgResponse(OK);
    }

    public static Response buildOk(String msg) {
        return build(OK, null, msg);
    }

    public static Response buildOk(Object result) {
        return build(OK, result, null);
    }

    private static Response build(String succ, Object result, String msg) {
        Response resp;

        if (result instanceof List) {

            resp = new ListResponse();
        } else if (result instanceof Map) {
            resp = new MapResponse();
        } else if (result instanceof String) {
            resp = new MsgResponse();
        } else {
            resp = new ObjectResponse();
        }
        resp.setResult(result);
        resp.setSucc(succ);
        resp.setMsg(msg);
        return resp;
    }

    public static Response buildFail(String msg) {
        return build(FAIL, null, msg);
    }

    public static Response buildFail(Object result, String msg) {
        return build(FAIL, result, msg);
    }
}
