package com.athena.marian.work.api;

import com.athena.marian.work.vo.WorkVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface WorkApi {

    @GetMapping("/work/getByCode")
    WorkVo getByCode(String code);

    @GetMapping("/work/getByUser")
    WorkVo getByUser(@RequestParam("userCode") String userCode);

}
