package juc.future;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Auther: cqp
 * @Date: 2023/8/31 15:40
 * @Description:
 */

@Data
@AllArgsConstructor
public class UserInfo {

    private String userId;

    private String userName;

    private Integer age;
}
