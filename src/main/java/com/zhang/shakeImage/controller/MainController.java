package com.zhang.shakeImage.controller;

import com.zhang.shakeImage.entity.User;
import com.zhang.shakeImage.repository.UserRepository;
import org.apache.commons.io.IOUtils;
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
import java.util.Iterator;

/**
 * Created by zhangsl on 2017/2/10.
 */
@Controller
@RequestMapping(value = "test")
public class MainController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping(value = "all")
    @ResponseBody
    public Iterable<User> findAll(){
        return userRepository.findAll();
    }


    @GetMapping(value = "icon")
    public void icon(HttpServletResponse response){
        try {
            URL url = new URL("http://desk.fd.zol-img.com.cn/t_s720x360c5/g5/M00/09/09/ChMkJ1iIUImIUZGOABfjUFlpK8oAAZixQFws_UAF-No381.jpg");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int code = connection.getResponseCode();
            if (code != 200){
                return;
            }
            byte[] data = IOUtils.toByteArray(connection.getInputStream());

            response.setContentType("image/jpg");
            OutputStream stream = response.getOutputStream();
            stream.write(data);
            stream.flush();
            stream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
