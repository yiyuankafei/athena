package com.application.mapper;

import com.application.entity.Emoticon;
import com.application.entity.EmoticonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmoticonMapper {
    long countByExample(EmoticonExample example);

    int deleteByExample(EmoticonExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Emoticon record);

    int insertSelective(Emoticon record);

    List<Emoticon> selectByExample(EmoticonExample example);

    Emoticon selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Emoticon record, @Param("example") EmoticonExample example);

    int updateByExample(@Param("record") Emoticon record, @Param("example") EmoticonExample example);

    int updateByPrimaryKeySelective(Emoticon record);

    int updateByPrimaryKey(Emoticon record);
}