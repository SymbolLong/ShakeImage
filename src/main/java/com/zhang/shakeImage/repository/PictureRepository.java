package com.zhang.shakeImage.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.zhang.shakeImage.entity.Picture;

/**
 * Created by zhangsl on 2017/2/11.
 */
public interface PictureRepository extends CrudRepository<Picture, Long> {

	Picture findByMd5(String md5);

	@Query("select new Picture(p.id,p.url,p.md5,p.type,p.statusCode,p.contentType,p.createTime,p.updateTime) from Picture p where contentType = :type ")
	List<Picture> findByContentType(@Param("type")String type,Pageable page);

}
