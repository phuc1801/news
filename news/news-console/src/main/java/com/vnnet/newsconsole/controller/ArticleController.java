package com.vnnet.newsconsole.controller;



//import com.vnnet.newscommon.model.SysArticle;

import com.vnnet.newscommon.bean.HttpStatus;
import com.vnnet.newscommon.model.SysArticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("api/v1/news")
public class ArticleController {
    private final static Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);
    @GetMapping(value = "/show")
    public String chuoi(){
        return "ok";
    }


    private SysArticle sysArticle;

//
//    @PreAuthorize("hasAuthority('sys:news:view')")
//    @PostMapping(value = "/show")
//    public HttpResult show(@RequestBody(required = false) PageRequest pageRequest) {
//        return HttpResult.ok(sysArticleService.show(pageRequest));
//    }
//
//
//    @PreAuthorize("hasAuthority('sys:news:add')")
//    @PostMapping(value = "/insert")
//    public HttpResult save(
//            @RequestPart("record") SysArticle record,
//            @RequestPart(value = "image", required = false) MultipartFile image) throws Exception {
//        if (image != null && !image.isEmpty()) {
//            String currentUrl = record.getcBannerUrl();
//            String newUrl = "";
//            newUrl = uploadNewsBanner(image, currentUrl);
//            record.setcBannerUrl(newUrl);
//        }
//        return HttpResult.ok(sysBaiVietService.insert(record));
//    }
}
