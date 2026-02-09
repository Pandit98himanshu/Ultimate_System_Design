package com.shorturl.dao;

import org.springframework.data.repository.CrudRepository;

import com.shorturl.model.UrlMap;

public interface UrlMapRepository extends CrudRepository<UrlMap, String> {
	
}
