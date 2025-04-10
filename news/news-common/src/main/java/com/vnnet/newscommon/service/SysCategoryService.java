package com.vnnet.newscommon.service;

import com.vnnet.newscommon.bean.PageRequest;
import com.vnnet.newscommon.bean.PageResult;
import com.vnnet.newscommon.model.SysCategory;

public interface SysCategoryService {
    int insert(SysCategory record);

    int update(SysCategory record);

    int delete(SysCategory record);

    PageResult show(PageRequest pageRequest);

    PageResult list(PageRequest pageRequest);

    SysCategory findById(Integer id);
}
