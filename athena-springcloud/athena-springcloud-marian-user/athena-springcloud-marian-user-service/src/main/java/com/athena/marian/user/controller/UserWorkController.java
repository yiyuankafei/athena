package com.athena.marian.user.controller;

import com.athena.marian.user.api.UserWorkApi;
import com.athena.marian.user.service.rpc.WorkService;
import com.athena.marian.user.vo.UserWorkVo;
import com.athena.marian.work.vo.WorkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserWorkController implements UserWorkApi {

    @Autowired
    WorkService workService;

    @Override
    public UserWorkVo getWorkByUser(String userCode) {
        WorkVo workVo = workService.getByUser(userCode);
        UserWorkVo userWorkVo = new UserWorkVo();
        userWorkVo.setUserName(userCode);
        userWorkVo.setWorkName(workVo.getTitle());
        return userWorkVo;
    }
}
