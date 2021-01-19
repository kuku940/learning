package cn.xiaoyu.learning.spring.response;

public class MsgResponse implements Response {

    /**
     * 业务信息
     */
    private String result;

    /**
     * 相应的结果
     */
    private String succ;

    /**
     * 提示信息，错误信息
     */
    private String msg;


    public MsgResponse() {
        this.succ = "";
    }

    public MsgResponse(String succ) {
        this.succ = succ;
    }

    @Override
    public String getResult() {
        return result;
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

    @Override
    public void setResult(Object result) {
        this.result = result.toString();
    }
}