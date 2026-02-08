package com.shorturl.service;

import org.springframework.stereotype.Service;

import com.shorturl.model.UrlResponse;

@Service
public class ShortUrlService {
	public UrlResponse convertToShortUrl(String longUrl) {
		return new UrlResponse(longUrl);
	}
	public UrlResponse convertToLongUrl(String shortUrl) {
		return new UrlResponse(shortUrl);
	}
}
