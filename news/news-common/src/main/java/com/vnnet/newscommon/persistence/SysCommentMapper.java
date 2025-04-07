package com.vnnet.newscommon.persistence;

import com.vnnet.newscommon.model.SysComment;
import com.vnnet.newscommon.model.SysCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysCommentMapper {
    long countByExample(SysCommentExample example);

    int deleteByExample(SysCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysComment record);

    int insertSelective(SysComment record);

    List<SysComment> selectByExample(SysCommentExample example);

    SysComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysComment record, @Param("example") SysCommentExample example);

    int updateByExample(@Param("record") SysComment record, @Param("example") SysCommentExample example);

    int updateByPrimaryKeySelective(SysComment record);

    int updateByPrimaryKey(SysComment record);
}