package com.vnnet.newsconsole.controller;



import com.vnnet.newscommon.bean.HttpResult;
import com.vnnet.newscommon.bean.PageRequest;
//import com.vnnet.newscommon.config.Constants;
import com.vnnet.newscommon.model.SysArticle;
import com.vnnet.newscommon.service.SysArticleService;
import com.vnnet.newscommon.bean.HttpStatus;
import com.vnnet.newscommon.model.SysArticle;
import com.vnnet.newsconsole.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.nio.file.Path;


@RestController
@RequestMapping("api/v1/news")
public class ArticleController {
    private final static Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);
    @GetMapping(value = "/show")
    public String chuoi(){
        return "ok";
    }


    private SysArticleService sysArticleService;
//
//    @Autowired
//    private StorageService storageService;
//
//
//
//
//
////    @PreAuthorize("hasAuthority('sys:news:view')")
////    @PostMapping(value = "/show")
////    public HttpResult show(@RequestBody(required = false) PageRequest pageRequest) {
////
////        return HttpResult.ok(sysArticleService.show(pageRequest));
////    }
//
//
////    @PreAuthorize("hasAuthority('sys:news:add')")
//    @PostMapping(value = "/insert")
//    public HttpResult save(
//            @RequestPart("record") SysArticle record,
//            @RequestPart(value = "image", required = false) MultipartFile image) throws Exception {
//        if (image != null && !image.isEmpty()) {
//            String currentUrl = record.getImageUrl();
//            String newUrl = "";
//            newUrl = uploadNewsBanner(image, currentUrl);
//            record.setcBannerUrl(newUrl);
//        }
//        return HttpResult.ok(sysArticleService.insert(record));
//    }
//
//    private String uploadNewsBanner(MultipartFile image, String currentUrl) throws Exception {
//        try {
//            String bannerUrl = "";
//            bannerUrl = "sys_news_banner_" + System.currentTimeMillis() + "."
//                    + FilenameUtils.getExtension(image.getOriginalFilename());
//            storageService.store(image, bannerUrl, Constants.PRODUCT_IMAGE_PATH);
//            if ("/image/" + bannerUrl != currentUrl && currentUrl != null) {
//                if (currentUrl.contains("/image/")) {
//                    Path filePath = storageService.load(currentUrl.substring(7));
//                    File oldFile = filePath.toFile();
//                    oldFile.delete();
//                }
//            }
//            return "/image/" + bannerUrl;
//        } catch (Exception e) {
//            throw new Exception("Lưu ảnh thất bại");
//        }
//
//    }
}
