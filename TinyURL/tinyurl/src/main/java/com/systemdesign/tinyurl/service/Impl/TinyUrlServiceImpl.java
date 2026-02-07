package com.systemdesign.tinyurl.service.Impl;

import org.springframework.stereotype.Service;

import com.systemdesign.tinyurl.service.TinyUrlService;

@Service
public class TinyUrlServiceImpl implements TinyUrlService {
	private String tinyUrl;

	@Override
	public String getTinyUrl(String longUrl) {
		this.tinyUrl = longUrl;
		// ToDo: logic to convert long-url to tiny-url
		return tinyUrl;
	}
}
