package com.athena.marian.user.controller;

import com.athena.marian.user.api.UserApi;
import com.athena.marian.user.vo.UserVo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserApi {

    @Override
    public UserVo queryUser(String name) {
        UserVo userVo = new UserVo();
        userVo.setName(name);
        userVo.setAge(18);
        userVo.setPhone("13344556677");
        return userVo;
    }
}
