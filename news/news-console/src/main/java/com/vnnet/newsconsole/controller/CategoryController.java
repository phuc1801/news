package com.vnnet.newsconsole.controller;

import com.vnnet.newscommon.bean.HttpResult;
import com.vnnet.newscommon.bean.PageRequest;
import com.vnnet.newscommon.config.Constants;
import com.vnnet.newscommon.model.SysCategory;
import com.vnnet.newscommon.persistence.SysCategoryMapper;
import com.vnnet.newscommon.service.SysArticleService;
import com.vnnet.newscommon.service.SysCategoryService;
import com.vnnet.newscommon.utils.StringUtils;
import com.vnnet.newsconsole.service.StorageService;
import com.vnnet.newsconsole.utils.MySecurityUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);


    @Autowired
    private SysCategoryService sysCategoryService;

    @Autowired
    private StorageService storageService;



//    @PreAuthorize("hasAuthority('sys:news:view')")
    @PostMapping(value = "/show")
    public HttpResult show(@RequestBody(required = false) PageRequest pageRequest) {
        return HttpResult.ok(sysCategoryService.show(pageRequest));
    }

    @PostMapping(value = "/list")
    public HttpResult list(@RequestBody(required = false) PageRequest pageRequest) {
        return HttpResult.ok(sysCategoryService.list(pageRequest));
    }

    @PostMapping(value = "/insert")
    public HttpResult save(@RequestBody SysCategory record) throws Exception {
        return HttpResult.ok(sysCategoryService.insert(record));
    }

    @PostMapping(value = "/update")
    public HttpResult update(@RequestPart("record") SysCategory record) throws Exception {
        SysCategory currRecord = sysCategoryService.findById(record.getId());
        sysCategoryService.update(record);
        return HttpResult.ok("OK");
    }

    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestPart("record") SysCategory record) throws Exception {
        try {
            int result = sysCategoryService.update(record);
            if (result > 0)
                return HttpResult.ok();
            else
                return HttpResult.error("Có lỗi xảy ra. Vui lòng thử lại sau.");
        } catch (Exception ex) {
            return HttpResult.error(ex.getMessage());
        }
    }

    @PostMapping(value = "/uploadBanner")
    public HttpResult uploadBanner(@RequestPart(value = "banner", required = false) MultipartFile banner)
            throws Exception {
        String bannerFileName = "";
        bannerFileName = "sys_news_banner_" + System.currentTimeMillis() + "."
                + FilenameUtils.getExtension(banner.getOriginalFilename());
        storageService.store(banner, bannerFileName, Constants.PRODUCT_IMAGE_PATH);
        return HttpResult.ok("/image/" + bannerFileName);
    }

    @PostMapping(value = "/removeBanner")
    public HttpResult removeArticleImage(@RequestBody String imageName) throws Exception {
        System.out.println(imageName);
        Path path = storageService.loadNewsImage(imageName.trim());
        File newsImage = path.toFile();
        if (newsImage.exists()){
            newsImage.delete();
            return HttpResult.ok("Xóa ảnh thành công");
        }else{
            return HttpResult.error("xoa anh khong thanh cong");
        }
    }

    @PostMapping(value = "/showSecurity")
    public HttpResult showSecurity(@RequestBody(required = false) PageRequest pageRequest) {
        String userName = MySecurityUtils.getUsername();
        if(StringUtils.isBlank(userName))
            return HttpResult.error(403, "access denied");
        return HttpResult.ok(sysCategoryService.show(pageRequest));
    }
}
