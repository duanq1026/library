package com.hniu.util;

import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 文件上传工具包
 */
public class FileUtils {

    /**
     *
     * @param file 文件
     * @param path 文件存放路径
     * @param  path 源文件名
     * @return
     */
    public static String generateImage(String file, String path) {
        if (file == null) {
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
        // 解密
        byte[] b = decoder.decodeBuffer(file);
        // 处理数据
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {
            b[i] += 256;
            }
        }
        String fileName = UUIDUtils.getUUID()+".jpg";
        path = path + fileName;
        OutputStream out = new FileOutputStream(path);
        out.write(b);
        out.flush();
        out.close();
        return fileName;
        } catch (Exception e) {
        return null;
        }

    }
}
