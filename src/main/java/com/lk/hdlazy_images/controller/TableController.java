package com.lk.hdlazy_images.controller;

import com.lk.hdlazy_images.pojo.ImageInfo;
import com.lk.hdlazy_images.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * ClassName:TableController
 * Package:com.lk.hdlazy_images.controller
 * Description:
 *
 * @date:2022/8/21 13:41
 * @author:LiaoKe
 */
@Controller
public class TableController {


    //首页
    @RequestMapping("/")
    public String index(){
        return "index.html";
    }

    //404
    @RequestMapping("/notFound")
    public String notFound(){
        return "404.html";
    }




}
