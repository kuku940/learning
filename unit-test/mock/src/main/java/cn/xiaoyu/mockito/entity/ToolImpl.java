package cn.xiaoyu.mockito.entity;

/**
 * @author roin.zhang
 * @date 2020/6/11
 */
public class ToolImpl implements ITool {
    @Override
    public String say(String name) {
        System.out.println("hello " + name);
        return "hello " + name;
    }

    @Override
    public void show() {
        System.out.println("hello world!");
    }
}
