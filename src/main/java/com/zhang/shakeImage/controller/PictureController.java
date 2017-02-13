package com.zhang.shakeImage.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhang.shakeImage.entity.Picture;
import com.zhang.shakeImage.repository.PictureRepository;
import com.zhang.shakeImage.service.PictureService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by zhangsl on 2017/2/10.
 */
@Controller
@RequestMapping(value = "picture")
public class PictureController {

    private Logger logger = LoggerFactory.getLogger(PictureController.class);

    @Autowired
    private PictureRepository pictureRepository;


    @GetMapping(value = "add")
    @ResponseBody
    public String addPicture(@RequestParam String url) {
        String md5 = DigestUtils.md5Hex(url);
        List<Picture> pictures = pictureRepository.findByMd5(md5);

        if (pictures.isEmpty()) {
            String type = PictureService.getTypeByURL(url);
            if (!type.isEmpty()) {
                Picture picture = new Picture();
                picture.setUrl(url);
                picture.setType(type);
                picture.setMd5(md5);
                pictureRepository.save(picture);
                logger.info(picture.getId() + "入库成功！");
            }
        } else {
            logger.info("已存在！");
        }

        JSONObject json = new JSONObject();
        json.put("success", true);
        return json.toString();
    }


    @GetMapping(value = "random")
    public void icon(@RequestParam(defaultValue = "640") String width, @RequestParam(defaultValue = "1136") String height, HttpServletResponse response) {
        int total = 64628;

        try {
            Random random = new Random();
            while (true) {

                Long id = Long.parseLong(random.nextInt(total) + "");
                Picture picture = pictureRepository.findOne(id);
                if (!picture.getContentType().contains("image") || !picture.getStatusCode().equals("200")){
                    continue;
                }
                String picUrl = PictureService.getURL(picture.getUrl(), width, height);

                URL url = new URL(picUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                int code = connection.getResponseCode();
                String type = connection.getHeaderField("Content-Type");
                picture.setStatusCode(code+"");
                picture.setContentType(type);
                picture.setUpdateTime(new Date());
                pictureRepository.save(picture);

                if (code != 200 || !type.contains("image")) {
                    logger.info("staus code:" + code+";content-type:"+type);
                    continue;
                }
                byte[] data = IOUtils.toByteArray(connection.getInputStream());

                response.setContentType("image/" + picture.getType());
                OutputStream stream = response.getOutputStream();
                stream.write(data);
                stream.flush();
                stream.close();
                System.out.println(picture.getId());
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @GetMapping("fix")
    public void fix() {
        System.out.println("执行中....");
        Iterable<Picture> pics = pictureRepository.findAll();
        Iterator<Picture> iterator = pics.iterator();
        while (iterator.hasNext()) {
            Picture picture = iterator.next();
            if (!picture.getContentType().equals("image/")){
                continue;
            }
            String picUrl = PictureService.getURL(picture.getUrl(),"640","1136");
            try {
                URL url = new URL(picUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                int code = connection.getResponseCode();
                String type = connection.getHeaderField("Content-Type");
                picture.setStatusCode(code+"");
                picture.setContentType(type);
                picture.setUpdateTime(new Date());
                pictureRepository.save(picture);
                System.out.println(picture.getId()+"-->"+code+":"+type);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
