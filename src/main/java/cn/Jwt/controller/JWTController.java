package cn.Jwt.controller;

import cn.Jwt.domain.*;
import cn.Jwt.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @Author:cgz
 * @Description:
 * @Date: create in 21:18 2019/1/13
 * @Version:
 * @Modified by:
 */
@RestController
@Slf4j
public class JWTController {
    @RequestMapping("/login")
    public Object login(@RequestParam String username, @RequestParam String password){
        JWTResponseData responseData = null;
        // 认证用户信息。本案例中访问静态数据。
            JWTSubject subject = new JWTSubject(username);
            String jwtToken = JWTUtils.createJWT(UUID.randomUUID().toString(), "sxt-test-jwt",
                    JWTUtils.generalSubject(subject), 1*60*1000);
            responseData = new JWTResponseData();
            responseData.setCode(200);
            responseData.setData(null);
            responseData.setMsg("登录成功");
            responseData.setToken(jwtToken);

        return responseData;
    }

    @RequestMapping("/testAll")
    @ResponseBody
    public Object testAll(HttpServletRequest request){

        String token = request.getHeader("Authorization");//头中获取Authorization

        JWTResult result = JWTUtils.validateJWT(token);

        JWTResponseData responseData = new JWTResponseData();

        if(result.isSuccess()){
            responseData.setCode(200);
            responseData.setData(result.getClaims().getSubject());
            // 重新生成token，就是为了重置token的有效期。
            String newToken = JWTUtils.createJWT(result.getClaims().getId(),
                    result.getClaims().getIssuer(), result.getClaims().getSubject(),
                    1*60*1000);
            responseData.setToken(newToken);
            return responseData;
        }else{
            responseData.setCode(500);
            responseData.setMsg("用户未登录");
            return responseData;
        }
    }
}
