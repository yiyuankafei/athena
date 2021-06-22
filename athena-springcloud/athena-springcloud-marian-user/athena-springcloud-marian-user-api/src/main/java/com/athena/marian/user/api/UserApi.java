package com.athena.marian.user.api;

import com.athena.marian.user.vo.UserVo;
import org.springframework.web.bind.annotation.GetMapping;

public interface UserApi {

    @GetMapping("/user/query")
    UserVo queryUser(String name);

}
