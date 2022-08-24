package com.lk.hdlazy_images.pojo;

import lombok.*;

/**
 * ClassName:ImageInfo
 * Package:pojo
 * Description:
 *
 * @date:2022/6/27 13:31
 * @author:LiaoKe
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ImageInfo {
    private Integer imageId;
    private String imageName;
    private long size;
    private java.util.Date uploadTime;
    private String md5;
    private String contentType;
    private String path;
}
