package com.zhang.shakeImage.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.zhang.shakeImage.entity.Picture;

/**
 * Created by zhangsl on 2017/2/11.
 */
public interface PictureRepository extends CrudRepository<Picture, Long> {

	Picture findByMd5(String md5);

	@Query("select new Picture(p.id) from Picture p where contentType = 'text/html' ")
	List<Picture> findByContentType(Pageable page);

}
