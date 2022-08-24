package com.lk.hdlazy_images.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * ClassName:ErrorPageConfig
 * Package:com.lk.demo.config
 * Description:
 *
 * @date:2022/8/20 13:56
 * @author:LiaoKe
 */
@Configuration
public class ErrorPageConfig implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage page404 = new ErrorPage(HttpStatus.NOT_FOUND, "/notFound");//需要给改请求加一个Controller
        ErrorPage page500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/notFound");
        registry.addErrorPages(page404,page500);
    }
}
