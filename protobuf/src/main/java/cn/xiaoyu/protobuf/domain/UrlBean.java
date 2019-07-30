package cn.xiaoyu.protobuf.domain;

public class UrlBean {
  private String name;
  private String detail;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public UrlBean() {
  }

  public UrlBean(String name, String detail) {
    this.name = name;
    this.detail = detail;
  }
}
