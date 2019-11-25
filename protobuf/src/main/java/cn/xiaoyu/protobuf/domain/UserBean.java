package cn.xiaoyu.protobuf.domain;

import java.util.List;
import java.util.Map;

public class UserBean {
    private int id;
    private String name;
    private List<UrlBean> urls;
    private Map<String, String> contactMap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<UrlBean> getUrls() {
        return urls;
    }

    public void setUrls(List<UrlBean> urls) {
        this.urls = urls;
    }

    public Map<String, String> getContactMap() {
        return contactMap;
    }

    public void setContactMap(Map<String, String> contactMap) {
        this.contactMap = contactMap;
    }
}
