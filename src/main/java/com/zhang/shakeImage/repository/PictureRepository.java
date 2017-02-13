package com.zhang.shakeImage.repository;

import com.zhang.shakeImage.entity.Picture;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by zhangsl on 2017/2/11.
 */
public interface PictureRepository extends CrudRepository<Picture,Long> {

    public List<Picture> findByMd5(String md5);

}
