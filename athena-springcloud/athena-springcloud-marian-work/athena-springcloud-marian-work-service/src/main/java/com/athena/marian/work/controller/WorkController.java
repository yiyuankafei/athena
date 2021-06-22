package com.athena.marian.work.controller;

import com.athena.marian.work.api.WorkApi;
import com.athena.marian.work.vo.WorkVo;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * eip
 */
@RestController
public class WorkController implements WorkApi {

    @Override
    public WorkVo getByCode(String code) {
        WorkVo workVo = new WorkVo();
        workVo.setCode(code);
        workVo.setTitle("=x=");
        workVo.setCreateTime(new Date());
        return workVo;
    }

    @Override
    public WorkVo getByUser(String userCode) {
        WorkVo workVo = new WorkVo();
        workVo.setTitle(userCode + "的作品");
        workVo.setCreateTime(new Date());
        workVo.setCode("code:???");
        return workVo;
    }
}
