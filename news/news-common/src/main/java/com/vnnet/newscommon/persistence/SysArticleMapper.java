package com.vnnet.newscommon.persistence;

import com.vnnet.newscommon.model.SysArticle;
import com.vnnet.newscommon.model.SysArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysArticleMapper {
    long countByExample(SysArticleExample example);

    int deleteByExample(SysArticleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysArticle record);

    int insertSelective(SysArticle record);

    List<SysArticle> selectByExample(SysArticleExample example);

    SysArticle selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysArticle record, @Param("example") SysArticleExample example);

    int updateByExample(@Param("record") SysArticle record, @Param("example") SysArticleExample example);

    int updateByPrimaryKeySelective(SysArticle record);

    int updateByPrimaryKey(SysArticle record);
}