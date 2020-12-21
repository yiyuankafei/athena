package com.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.application.entity.Emoticon;
import com.application.entity.EmoticonExample;
import com.application.mapper.EmoticonMapper;
import com.application.service.EmoticonService;

@Service
public class EmoticonServiceImpl extends BaseServiceImpl<EmoticonMapper, Emoticon, EmoticonExample> implements EmoticonService {
	
}
