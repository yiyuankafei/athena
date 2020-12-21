package com.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.entity.Emoticon;
import com.application.entity.EmoticonExample;
import com.application.service.EmoticonService;

@Controller
@RequestMapping("/emoticon")
public class EmoticonController {

	@Value("${xx.abc}")
	private String x;
	
	@Autowired
	EmoticonService emoticonService;	
	
	@GetMapping
	@ResponseBody
	public List<Emoticon> list(String keywords) {
		EmoticonExample example = new EmoticonExample();
		example.createCriteria().andTagLike("%" + keywords + "%");
		List<Emoticon> list = emoticonService.selectByExample(example);
		return (list);
	}

	@GetMapping("/test")
	@ResponseBody
	public String test() {
		return x;
	}
}
