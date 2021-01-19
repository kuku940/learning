package cn.xiaoyu.learning.spring.response;

public interface Response {

    String getSucc();

    void setSucc(String succ);

    String getMsg();

    void setMsg(String msg);

    Object getResult();

    void setResult(Object result);

}