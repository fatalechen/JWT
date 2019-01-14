package cn.Jwt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author:cgz
 * @Description:
 * @Date: create in 21:22 2019/1/13
 * @Version:
 * @Modified by:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class UserInfo {
    private String username;
    private String password;
}
