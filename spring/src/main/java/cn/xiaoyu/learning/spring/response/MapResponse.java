package cn.xiaoyu.learning.spring.response;

import java.util.Map;

public class MapResponse implements Response {

    private Map result;
    /**
     * 相应的结果
     */
    private String succ;

    /**
     * 提示信息，错误信息
     */
    private String msg;

    @Override
    public Map getResult() {
        return result;
    }

    @Override
    public void setResult(Object result) {
        this.result = (Map) result;
    }

    @Override
    public String getSucc() {
        return succ;
    }

    @Override
    public void setSucc(String succ) {
        this.succ = succ;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
