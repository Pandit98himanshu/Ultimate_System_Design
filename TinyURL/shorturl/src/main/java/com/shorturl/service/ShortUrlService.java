package com.shorturl.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.shorturl.model.ShortUrlList;
import com.shorturl.model.UrlResponse;

@Service
public class ShortUrlService {
	private final String GENERATOR_URI = "http://localhost:8085/generate/{qty}";
	public UrlResponse convertToShortUrl(String longUrl) {
		RestTemplate restTemplate = new RestTemplate();
		ShortUrlList rsp = restTemplate.getForObject(GENERATOR_URI, ShortUrlList.class, "5");

		System.out.println("URL list in ShortUrl Service: " + rsp);
		return new UrlResponse(longUrl);
	}
	public UrlResponse convertToOriginalUrl(String shortUrl) {
		return new UrlResponse(shortUrl);
	}
}
