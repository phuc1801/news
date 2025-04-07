package com.vnnet.newscommon.service;

import com.vnnet.newscommon.model.SysArticle;
import org.springframework.stereotype.Service;



public interface SysArticleService {
    int insert(SysArticle record);

    int update(SysArticle record);

    int delete(SysArticle record);

//    PageResult show(PageRequest pageRequest);

//    PageResult list(PageRequest pageRequest);
//
//    SysBaiViet findById(Integer id);
}
