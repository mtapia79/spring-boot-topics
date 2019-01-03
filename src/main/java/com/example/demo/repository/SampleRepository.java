package com.example.demo.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.mapper.SampleMapper;

@Repository
public class SampleRepository {
 
    @Autowired
    private SampleMapper sampleMapper;
 
    public SampleRepository(SampleMapper sampleMapper) {
        this.sampleMapper = sampleMapper;
    }
 
    public String getMessageByUser(String userName) {
        String language = sampleMapper.getLanguageByUser(userName);
 
        return sampleMapper.getMessageByLanguage(language);
    }
}
