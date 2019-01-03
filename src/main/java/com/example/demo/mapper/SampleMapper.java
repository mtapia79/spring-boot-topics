package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SampleMapper {
 
   String getLanguageByUser(@Param("userName") String userName);
    
   String getMessageByLanguage(@Param("language") String language);
}
