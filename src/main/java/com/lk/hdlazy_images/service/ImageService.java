package com.lk.hdlazy_images.service;

import com.lk.hdlazy_images.mapper.ImageMapper;
import com.lk.hdlazy_images.pojo.ImageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:ImageService
 * Package:com.lk.hdlazy_images.service
 * Description:
 *
 * @date:2022/8/21 13:40
 * @author:LiaoKe
 */
@Service
public class ImageService {

    @Autowired
    private ImageMapper imageMapper;


    //插入
    public int insertImage(ImageInfo imageInfo){
        int i = imageMapper.insertImage(imageInfo);
        return i;
    }

    //照片列表
    public List<ImageInfo> listImages(){
        List<ImageInfo> imageInfos = imageMapper.listImages();
        return imageInfos;
    }

    //具体照片信息
    public ImageInfo selectImageById(Integer id){
        ImageInfo imageInfo = imageMapper.selectImageById(id);
        return imageInfo;
    }

    //删除照片
    public int deleteImage(Integer id){
        int i = imageMapper.deleteImage(id);
        return i;
    }

    //根据MD5的值查询
    public ImageInfo selectImageByMD5(String md5){
        ImageInfo imageInfo = imageMapper.selectImageByMD5(md5);
        return imageInfo;
    }

}
