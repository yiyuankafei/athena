package com.application.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.utils.RandomUtils;

@RestController
public class TestGenericController {
	
	@NacosInjected
    private NamingService namingService;
	
	@RequestMapping("/test")
	public Object test() throws NacosException {
		
		
		List<Instance> list = namingService.getAllInstances("providers:com.shxhome.zhuanche.common.service.CarGenericService:0.0.1");
		
		Instance instance = list.get(RandomUtils.nextInt(list.size()));
		
		System.out.println(instance.getIp());
		System.out.println(instance.getPort());
		
		System.out.println(instance.getMetadata().get("interface"));
		System.out.println(instance.getMetadata().get("version"));
		
		
		List<String> params = Arrays.asList("java","jquery", "unknown");
		String[] invokeParamTyeps = new String[1];
		Object[] invokeParams = new Object[1];
		for (int i = 0; i < 1; i++) {
			invokeParamTyeps[i] = String.class.getName();
			invokeParams[i] = params.get(RandomUtils.nextInt(params.size()));
		}
		
		// 创建服务实例
		ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
        reference.setGeneric(true);
        reference.setInterface(instance.getMetadata().get("interface"));
        reference.setVersion(instance.getMetadata().get("version"));
        
        // 获取缓存中的实例
        ReferenceConfigCache cache = ReferenceConfigCache.getCache(); 
        GenericService genericService = cache.get(reference);
        
        // 调用实例
        Object result = genericService.$invoke("getBook", invokeParamTyeps, invokeParams);
        return result;
	}
	
	public static void main(String[] args) {
		List<String> l = new ArrayList<String>();
		l.add("a");
		l.add("f");
		int nextInt = RandomUtils.nextInt(l.size());
		System.out.println(nextInt);
		System.out.println(l.get(nextInt));
		
	}

}
