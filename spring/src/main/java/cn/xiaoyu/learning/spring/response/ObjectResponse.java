package cn.xiaoyu.learning.spring.response;

public class ObjectResponse implements Response {
    /**
     * 业务信息
     */
    private Object result;

    /**
     * 相应的结果
     */
    private String succ;

    /**
     * 提示信息，错误信息
     */
    private String msg;


    public ObjectResponse() {
    }

    public ObjectResponse(String succ) {
        this.succ = succ;
    }

    public Object getResult() {
        return result;
    }


    public String getSucc() {
        return succ;
    }

    public void setSucc(String succ) {
        this.succ = succ;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public void setResult(Object result) {
        this.result = result;
    }
}
