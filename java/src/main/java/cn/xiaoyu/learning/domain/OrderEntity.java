package cn.xiaoyu.learning.domain;

import lombok.Data;

/**
 * @author roin.zhang
 * @date 2019-08-27
 */
@Data
public class OrderEntity extends BaseEntity {
    private Long orderId;
    private String source;
}
