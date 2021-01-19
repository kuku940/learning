package cn.xiaoyu.learning.spring.response;

import java.util.List;

public class ListResponse implements Response {

    private List result;

    private String succ;

    private String msg;

    @Override
    public List getResult() {
        return result;
    }

    @Override
    public void setResult(Object result) {
        this.result = (List) result;
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
