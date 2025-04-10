package com.vnnet.newscommon.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vnnet.newscommon.bean.ColumnFilter;
import com.vnnet.newscommon.bean.PageRequest;
import com.vnnet.newscommon.bean.PageResult;
import com.vnnet.newscommon.model.SysArticle;
import com.vnnet.newscommon.model.SysArticleExample;
import com.vnnet.newscommon.model.SysCategory;
import com.vnnet.newscommon.model.SysCategoryExample;
import com.vnnet.newscommon.persistence.SysArticleMapper;
import com.vnnet.newscommon.persistence.SysCategoryMapper;
import com.vnnet.newscommon.utils.MybatisPageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysCategoryServiceImpl implements SysCategoryService {
    private static final Logger logger = LoggerFactory.getLogger(SysArticleServiceImpl.class);

    @Autowired
    private SysCategoryMapper sysCategoryMapper;

    @Override
    public int insert(SysCategory record) {
        return sysCategoryMapper.insertSelective(record);
    }

    @Override
    public int update(SysCategory record) {
        return sysCategoryMapper.updateByPrimaryKey(record);
    }

    @Override
    public int delete(SysCategory record) {
        SysCategoryExample example = new SysCategoryExample();
        example.createCriteria().andIdEqualTo(record.getId());
        return sysCategoryMapper.deleteByExample(example);
    }

    @Override
    public PageResult list(PageRequest pageRequest) {
        try{
            int pageNum = pageRequest.getPageNum();
            int pageSize = pageRequest.getPageSize();
            if (pageNum < 1)
                pageNum = 1;
            if (pageSize < 0)
                pageSize = 10;

            SysCategoryExample nExample = new SysCategoryExample();
//            SysCategoryExample.Criteria criteria = nExample.createCriteria();

//            criteria.andNTinhTrangEqualTo((byte) 1);
            nExample.setOrderByClause("created_at DESC");

            PageHelper.startPage(pageNum, pageSize);
            List<SysCategory> nRecords = sysCategoryMapper.selectByExample(nExample);
            return MybatisPageHelper.getPageResult(pageRequest, new PageInfo(nRecords));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex.fillInStackTrace());
        }
        return null;
    }


    @Override
    public PageResult show(PageRequest pageRequest) {
        try{
            int pageNum = pageRequest.getPageNum();
            int pageSize = pageRequest.getPageSize();
            if (pageNum < 1)
                pageNum = 1;
            if (pageSize < 0)
                pageSize = 10;

            String name  = MybatisPageHelper.getColumnFilterValue(pageRequest, "name");
            String description  = MybatisPageHelper.getColumnFilterValue(pageRequest, "description");

            System.out.println("name:" + name.trim());
            System.out.println("description:" + description.trim());

            SysCategoryExample nExample = new SysCategoryExample();
            SysCategoryExample.Criteria criteria = nExample.createCriteria();

                criteria.andNameLikeInsensitive("%" + name + "%");


                criteria.andDescriptionLikeInsensitive("%" + description + "%");



            nExample.setOrderByClause("created_at DESC");

            PageHelper.startPage(pageNum, pageSize);
            List<SysCategory> nRecords = sysCategoryMapper.selectByExample(nExample);
            return MybatisPageHelper.getPageResult(pageRequest, new PageInfo(nRecords));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex.fillInStackTrace());
        }
        return null;
    }

    public SysCategory findById(Integer id){
        return sysCategoryMapper.selectByPrimaryKey(id);
    }

}
