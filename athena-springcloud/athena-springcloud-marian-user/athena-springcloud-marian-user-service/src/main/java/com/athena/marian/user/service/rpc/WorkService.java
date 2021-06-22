package com.athena.marian.user.service.rpc;

import com.athena.marian.work.api.WorkApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("athena-marian-work")
public interface WorkService extends WorkApi {
}
