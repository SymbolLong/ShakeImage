package com.zhang.shakeImage.service;

import com.zhang.shakeImage.contants.Contant;

/**
 * Created by zhangsl on 2017/2/11.
 */
public class PictureService {

    public static String getTypeByURL(String url) {
        String type = "";
        if (url.contains(Contant.DOT + Contant.BMP)) {
            type = Contant.BMP;
        } else if (url.contains(Contant.DOT + Contant.GIF)) {
            type = Contant.GIF;
        } else if (url.contains(Contant.DOT + Contant.JPEG)) {
            type = Contant.JPEG;
        } else if (url.contains(Contant.DOT + Contant.JPG)) {
            type = Contant.JPG;
        } else if (url.contains(Contant.DOT + Contant.PNG)) {
            type = Contant.PNG;
        }
        return type;
    }

    public static String getURL(String url, String width, String height) {
        String result = url;

        if (url.startsWith(Contant.MANMANKAN)) {
            result = url.replace("WidthHeight", width + "x" + height);
        } else if (url.startsWith(Contant.ZOL)) {
            result = url.replace("WidthHeight", width + "x" + height);
        }
        return result;
    }

    public static void main(String[] args) {
        String url = "";
        url = getURL(url, "640", "1136");
        System.out.println(url);
    }
}
