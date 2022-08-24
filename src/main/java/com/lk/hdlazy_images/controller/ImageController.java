package com.lk.hdlazy_images.controller;

import com.lk.hdlazy_images.pojo.ImageInfo;
import com.lk.hdlazy_images.service.ImageService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:ImageController
 * Package:com.lk.hdlazy_images.controller
 * Description:
 *
 * @date:2022/8/21 13:40
 * @author:LiaoKe
 */
@RestController
public class ImageController {


    @Autowired
    private ImageService imageService;

    public static final String LOCAL_PATH_PREFIX = "/root/hdlazy_images";

    //public static final String LOCAL_PATH_PREFIX ="F:\\JavaIOSpace";

    //白名单列表：本机访问本机时，是以下路径，如果放在云服务器，需要改
    private static final List<String> WHITE_LIST = Arrays.asList(

            "http://81.68.218.227:8080/",
            "http://81.68.218.227:8081/"
    );

    //展示照片
    @GetMapping("/imageShow")
    public void imageShow(@RequestHeader("Referer") String referer, HttpServletResponse resp, @RequestParam("imageId") String id) throws IOException {
        if (!WHITE_LIST.contains(referer)) {
            //返回403
            resp.setStatus(403);
            return;
        }
        ImageInfo imageInfo = imageService.selectImageById(Integer.parseInt(id));
        String path =LOCAL_PATH_PREFIX + imageInfo.getPath();
        File pic = new File(path);
        byte[] data = Files.readAllBytes(pic.toPath());
        resp.getOutputStream().write(data);
    }


    //上传照片
    @PostMapping("/image")
    public Object addImage(@RequestPart("uploadImage") MultipartFile uploadImage) throws IOException {

        FileInputStream inputStream=null;
        String md5=null;

        Map<String, Object> data = new HashMap<>();

        //获取输入流
        inputStream= (FileInputStream) uploadImage.getInputStream();

        //生成MD5
        md5= DigestUtils.md5Hex(inputStream);



        //根据MD5查询
        ImageInfo imageInfo = imageService.selectImageByMD5(md5);
        if (imageInfo != null) {//已经存在这个图片，说明重复

            data.put("ok", false);
            data.put("msg", "上传图片重复");
            return data;
        }

        //写到本地
        uploadImage.transferTo(new File(LOCAL_PATH_PREFIX + "/" + md5));

        //写到数据库
        ImageInfo image = new ImageInfo();
        //设置图片名称：上传的文件名
        image.setImageName(uploadImage.getOriginalFilename());
        //设置图片大小：上传的文件大小
        image.setSize(uploadImage.getSize());
        //设置上传日期：当前日期
        image.setUploadTime(new java.util.Date());
        //设置md5
        image.setMd5(md5);
        //设置数据格式/类型: 上传的文件格式(注意，不是请求的数据格式，是form-data上传的图片字段的格式)
        image.setContentType(uploadImage.getContentType());
        //设置路径：设置为路径后缀（/md5值）
        image.setPath("/" + md5);

        int i = imageService.insertImage(image);

        data.put("ok", true);//我们不返回错误，出错就让tomcat返回500状态码

        return data;

    }


    //获取照片列表
    @GetMapping("/image")
    public Object listImages(){
        List<ImageInfo> imageInfos = imageService.listImages();
        return imageInfos;
    }

    //删除照片
    @DeleteMapping("/image")
    public Object deleteImage(@RequestParam("imageId") String id){
        ImageInfo imageInfo = imageService.selectImageById(Integer.parseInt(id));

        String path = LOCAL_PATH_PREFIX + imageInfo.getPath();

        File pic = new File(path);

        pic.delete();

        int i = imageService.deleteImage(Integer.parseInt(id));

        Map<String, Object> data = new HashMap<>();
        data.put("ok", true);
        return data;
    }




}
