package com.lk.hdlazy_images.mapper;

import com.lk.hdlazy_images.pojo.ImageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName:ImageMapper
 * Package:com.lk.hdlazy_images.mapper
 * Description:
 *
 * @date:2022/8/21 13:12
 * @author:LiaoKe
 */
@Mapper
public interface ImageMapper {

    //插入
    int insertImage(ImageInfo imageInfo);

    //照片列表
    List<ImageInfo> listImages();

    //具体照片信息
    ImageInfo selectImageById(Integer id);

    //删除照片
    int deleteImage(Integer id);

    //根据MD5的值查询
    ImageInfo selectImageByMD5(String md5);


}
