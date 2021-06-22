package com.athena.marian.user.api;

import com.athena.marian.user.vo.UserWorkVo;
import org.springframework.web.bind.annotation.GetMapping;

public interface UserWorkApi {

    @GetMapping("/user/work/getByUser")
    UserWorkVo getWorkByUser(String userCode);

}
