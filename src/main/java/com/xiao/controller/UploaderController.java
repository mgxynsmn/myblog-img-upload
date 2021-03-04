package com.xiao.controller;


import com.xiao.common.*;
import com.xiao.domain.UploaderRepository;
import com.xiao.pojo.UploadFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class UploaderController {

    protected static final Logger logger = LoggerFactory.getLogger(UploaderController.class);

     @Autowired
     private UploaderRepository uploaderRepository;

    @Value("${img-dir}")
    private String imageUploadUrl;

    @Value("${img-download-url}")
    private String imageDownloadUrl;

    /**
     * 单文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/uploadfile")
    @ResponseBody
    public Result uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("relatedBusiness") String relatedBusiness,
                             @RequestParam("remark") String remark) {
        if (!file.isEmpty()) {
            String saveFileName = file.getOriginalFilename();
            logger.info(saveFileName + "开始上传+" + new Date().toString());
            //生成新的文件名
            String newName = UploadUtil.genImageName();
            String ext = saveFileName.substring(saveFileName.lastIndexOf("."));
            newName += ext;
            //图片上传
            if (!imageUploadUrl.endsWith("/")) {
                imageUploadUrl += "/";
            }
            String relativePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + "/";
            String absolutePath = imageUploadUrl + relativePath;
            Boolean result = true;
            UploadUtil.uploadPicture(file, absolutePath, newName);
            if (result) {

                UploadFile uploaderfile = new UploadFile(SnowFlake.instant().nextId(), saveFileName, newName, ext, file.getSize(), relatedBusiness, remark, relativePath + newName, imageDownloadUrl + relativePath + newName, new Date());
                uploaderRepository.insert(uploaderfile);
                logger.info(saveFileName + "上传结束+" + new Date().toString());
                return Result.successData(uploaderfile);
            } else {
                return Result.error(1, "上传失败");
            }
        } else
            return Result.error(1, "请选择文件");

    }


    /**
     * 文件列表
     *
     * @param pageIndex
     * @param pageSize
     * @param nameContain
     * @return
     */
/*    @PostMapping("/list")
    @ResponseBody
    public HashMap list(@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize, @RequestParam(name = "nameContain", required = false) String nameContain) {

        if (pageIndex == null || pageSize == null) {
            Result.error(1, "入参错误");
        }
        if (StringUtils.isEmpty(nameContain) || nameContain == null) {

            List<UploadFile> uploadFiles = uploaderRepository.selectByPage(pageIndex, pageSize);
            Integer count = uploaderRepository.selectCount(nameContain);
            HashMap<String, Object> result = Result.successData(uploadFiles).toMap();
            result.put("count", count);
            return result;

        } else {
            List<UploadFile> uploadFiles = uploaderRepository.selectByPage(pageIndex, pageSize, nameContain);
            Integer count = uploaderRepository.selectCount(nameContain);
            HashMap<String, Object> result = Result.successData(uploadFiles).toMap();
            result.put("count", count);
            return result;
        }
    }*/

    /**
     * 删除文件
     *
     * @param id
     * @return
     */
/*    @PostMapping("/dele")
    @ResponseBody
    public Result list(@RequestParam("id") String id) {

        if (id == null || Long.valueOf(id) == null) {
            Result.error(1, "入参错误");
        }

        UploadFile uploadFile = uploaderRepository.selectById(Long.valueOf(id));
        if (uploadFile == null)
            return Result.error(2, "文件不存在");
        uploaderRepository.delete(uploadFile.getId());
        File file = new File(uploadFile.getAbsolutePath() + "/" + uploadFile.getSavedFileName());
        if (file.exists()) {
            file.delete();
        }
        return Result.success();
    }*/



}


