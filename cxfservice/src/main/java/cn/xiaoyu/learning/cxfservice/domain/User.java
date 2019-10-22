package cn.xiaoyu.learning.cxfservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long id;
    private String username;
    private int age;
    private String[] hobby;
}
