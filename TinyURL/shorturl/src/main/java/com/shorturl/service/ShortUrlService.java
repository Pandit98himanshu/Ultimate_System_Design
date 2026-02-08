package com.shorturl.service;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.shorturl.model.ShortUrlList;
import com.shorturl.model.UrlResponse;

@Service
public class ShortUrlService {
	private final String GENERATOR_URI = "http://localhost:8085/generate/{qty}";
	private final String PREFIX_URI = "http://shorturl.com/";
	private final RestTemplate restTemplate = new RestTemplate();
	private final Queue<String> urlQueue = new LinkedList<>();

	public UrlResponse convertToShortUrl(String longUrl) {
		// TODO: verify longUrl
		String shortUrl = getShortUrl();
		// TODO: add the mapping if {shortUrl, longUrl} to the H2 database
		shortUrl = PREFIX_URI + shortUrl;
		return new UrlResponse(shortUrl);
	}

	public UrlResponse convertToOriginalUrl(String shortUrl) {
		// TODO: retrieve the original url from H2 database
		return new UrlResponse(shortUrl);
	}

	private String getShortUrl() {
		if (urlQueue.isEmpty() || urlQueue.size() < 3) {
			ShortUrlList rsp = restTemplate.getForObject(GENERATOR_URI, ShortUrlList.class, "10");
			urlQueue.addAll(rsp.shortUrls());
		}
		return urlQueue.poll();
	}
}
