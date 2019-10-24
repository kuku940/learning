package cn.xiaoyu.learning.im.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author roin.zhang
 * @date 2019/10/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Session {
    /**
     * 用户唯一标识
     */
    private String userId;
    private String userName;
}
