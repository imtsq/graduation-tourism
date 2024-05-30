package com.tourism.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 文件上传只需要调用save方法，然后返回一个url保存到服务器中
 * 其中Picaddress是你服务器的地址，文件会保存其中，名字是执行保存的时间
 */
@Slf4j
public  class PicStore {

    private static String Picaddress = "D:/img";

    //    保存到数据库的路径
    private static String localUrl =  "http://localhost:8080/img/";

    private static String getImgUrl(String name){
        return localUrl + name;
    }
    public static String getImgaddress() {
        return Picaddress;
    }
//    通过上传文件存储图片
    public static String savePic(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
//        时间.jpg  或者时间.其他后缀
        String newName = PicStore.setPicNewname(originalFilename);
        String url = PicStore.getImgUrl(newName);
        Path path = Paths.get(PicStore.getImgaddress());
        if (!Files.exists(path)) {
            log.info("目录不存在创建对应目录");
            Files.createDirectories(path);
        }
        String filename = StringUtils.cleanPath(newName);
        Path resolve = path.resolve(filename);
        Files.copy(file.getInputStream(), resolve, StandardCopyOption.REPLACE_EXISTING);
        return url;
    }
    //   对ip的图片分析地址然后删除
    public static void DelImgByUrl(String name) throws Exception {
        URL url = new URL(name);
        // 获取URL路径中的文件名
        String fileName = url.getFile();
        // 由于getFile()方法返回的是整个路径，我们需要进一步提取最后一部分作为文件名
        String actualFileName = fileName.substring(fileName.lastIndexOf('/') + 1);
        Path file = Paths.get(PicStore.DelImgaddress(actualFileName));
        if (Files.exists(file)) {
            Files.delete(file);
            log.info("删除成功");
        }
    }
//    获取时间加源文件后缀
    private static String setPicNewname(String originalFilename) {
        LocalDateTime now = LocalDateTime.now();
        // 创建一个日期时间格式器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHH-mm-ss");
        // 将LocalDateTime格式化为字符串
        String formattedDateTime = now.format(formatter);
        int index = originalFilename.lastIndexOf(".");
        if (index != -1) {
            formattedDateTime = formattedDateTime + originalFilename.substring((index));
        }
        return formattedDateTime;
    }


    private static String DelImgaddress(String name) {
        return Picaddress + "/" + name;
    }



}
