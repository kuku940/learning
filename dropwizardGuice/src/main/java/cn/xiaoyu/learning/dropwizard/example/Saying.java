package cn.xiaoyu.learning.dropwizard.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

/**
 * @author roin.zhang
 * @date 2019/10/12
 */
public class Saying {
    private Long id;
    @Length(max = 3)
    private String content;

    public Saying() {
    }

    public Saying(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    @JsonProperty
    public Long getId() {
        return this.id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }
}
