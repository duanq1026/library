package com.hniu.controller;

import com.hniu.constan.StateCode;
import com.hniu.util.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片..文件上传
 */
@RestController
public class FileContorller extends Base{

    @Value("${web.upload-path}")
    private String path;

    @Value("${web.imagePath}")
    private String imagePath;

    /**
     * 文件上传
     */
    @PostMapping("/fileUpload")
    public Object upload(String file){
        String upload = FileUtils.generateImage(file, this.path);
        if(StringUtils.isEmpty(upload)){
            return packaging(StateCode.FAIL,null);
        }else{
            return packaging(StateCode.SUCCESS,imagePath+upload);
        }
    }
}
