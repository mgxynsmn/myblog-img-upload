package com.xiao.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Random;


public class UploadUtil {

    /**
     * 文件上传
     *
     * @param uploadFile 上传文件
     * @param imagePath  上传路径
     * @param imageName  文件名称
     * @return 成功返回 true  失败返回 false
     */
    public static Boolean uploadPicture(MultipartFile uploadFile, String imagePath, String imageName) {
        if (!imagePath.endsWith("/")) {
            imagePath += "/";
        }
        createFilePath(imagePath);
        try {
//            Files.write(Paths.get(imagePath + imageName), uploadFile.getBytes());
            uploadFile.transferTo(new File(imagePath + imageName));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 创建文件夹
     *
     * @param path
     */
    public static void createFilePath(String path) {
        if (path != null) {
            File file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.mkdirs();
        }
    }

    /**
     * 生成文件名
     *
     * @return
     */
    public static String genImageName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //加上4位随机数
        Random random = new Random();
        int end4 = random.nextInt(9999);
        //如果不足四位前面补0
        String imageName = millis + String.format("%04d", end4);
        return imageName;
    }

//    public static void main(String[] args) {
//        for (int i = 0; i < 100; i++) {
//            System.out.println(genImageName());
//        }
//    }

}
