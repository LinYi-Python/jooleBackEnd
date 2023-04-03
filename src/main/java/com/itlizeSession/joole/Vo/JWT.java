package com.itlizeSession.joole.Vo;

/**
 * @ClassName JWT
 * @Description TODO
 * @Author Yi Lin
 * @Date 6/13/22 16:52
 * @Version 1.0
 **/
public class JWT {
    private String token;

    public JWT(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
//JWT.token
