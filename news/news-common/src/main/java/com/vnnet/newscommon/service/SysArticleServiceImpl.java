package com.vnnet.newscommon.service;

import com.github.pagehelper.PageHelper;
import com.vnnet.newscommon.bean.PageRequest;
import com.vnnet.newscommon.bean.PageResult;
import com.vnnet.newscommon.model.SysArticle;
import com.vnnet.newscommon.model.SysArticleExample;
import com.vnnet.newscommon.persistence.SysArticleMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;


@Service
public class SysArticleServiceImpl implements SysArticleService {
    private static final Logger logger = LoggerFactory.getLogger(SysArticleServiceImpl.class);

    @Autowired
    private SysArticleMapper sysArticleMapper;

    @Override
    public int insert(SysArticle record) {
        return sysArticleMapper.insertSelective(record);
    }

    @Override
    public int update(SysArticle record) {
        return sysArticleMapper.updateByPrimaryKey(record);
    }

    @Override
    public int delete(SysArticle record) {
        SysArticleExample example = new SysArticleExample();
        example.createCriteria().andIdEqualTo(record.getId());
        return sysArticleMapper.deleteByExample(example);
    }

//
//    @Override
//    public PageResult show(PageRequest pageRequest) {
//        int pageNum = pageRequest.getPageNum() <= 0 ? 1 : pageRequest.getPageNum();
//        int pageSize = pageRequest.getPageSize() <= 0 ? 10 : pageRequest.getPageSize();
//
//        String title = (String) pageRequest.getFilters().getOrDefault("title", "");
//        String content = (String) pageRequest.getFilters().getOrDefault("content", "");
//        String publishedStr = (String) pageRequest.getFilters().getOrDefault("isPublished", "");
//        Boolean isPublished = publishedStr.equals("") ? null : Boolean.valueOf(publishedStr);
//
//        PageHelper.startPage(pageNum, pageSize);
//        List<Article> list = articleMapper.selectByFilter(title, content, isPublished);
//        PageInfo<Article> pageInfo = new PageInfo<>(list);
//
//        PageResult result = new PageResult();
//        result.setPageNum(pageNum);
//        result.setPageSize(pageSize);
//        result.setTotal(pageInfo.getTotal());
//        result.setContent(pageInfo.getList());
//
//        return result;
//    }
}
