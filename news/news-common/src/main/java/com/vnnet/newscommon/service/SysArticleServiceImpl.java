package com.vnnet.newscommon.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vnnet.newscommon.bean.PageRequest;
import com.vnnet.newscommon.bean.PageResult;
import com.vnnet.newscommon.model.SysArticle;
import com.vnnet.newscommon.model.SysArticleExample;
import com.vnnet.newscommon.model.SysUserExample;
import com.vnnet.newscommon.persistence.SysArticleMapper;
import com.vnnet.newscommon.utils.MybatisPageHelper;
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
//        try{
//            int pageNum = pageRequest.getPageNum();
//            int pageSize = pageRequest.getPageSize();
//            if (pageNum < 1)
//                pageNum = 1;
//            if (pageSize < 0)
//                pageSize = 10;
//
//            String cTieuDe  = MybatisPageHelper.getColumnFilterValue(pageRequest, "cTieuDe");
//            String cMoTa  = MybatisPageHelper.getColumnFilterValue(pageRequest, "cMoTa");
//            String nTinhTrang = (MybatisPageHelper.getColumnFilterValue(pageRequest, "nTinhTrang"));
//
//            SysUserExample nExample = new SysUserExample();
//            SysUserExample.Criteria criteria = nExample.createCriteria();
//            criteria.("%" + cTieuDe + "%");
//            criteria.andFullNameLike("%" + cMoTa + "%");
//
//            if (nTinhTrang.length() > 0) {
//                criteria.andNTinhTrangEqualTo(Byte.valueOf(nTinhTrang));
//            }
//
//            nExample.setOrderByClause("d_ngay_tao DESC");
//
//            PageHelper.startPage(pageNum, pageSize);
//            List<SysBaiViet> nRecords = sysBaiVietMapper.selectByExample(nExample);
//            return MybatisPageHelper.getPageResult(pageRequest, new PageInfo(nRecords));
//        } catch (Exception ex) {
//            logger.error(ex.getMessage(), ex.fillInStackTrace());
//        }
//        return null;
//    }
}
