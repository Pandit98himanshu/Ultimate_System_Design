package com.shorturl.service;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.shorturl.dao.UrlMapRepository;
import com.shorturl.model.ShortUrlList;
import com.shorturl.model.UrlMap;
import com.shorturl.model.UrlResponse;

@Service
public class ShortUrlService {
	private final String GENERATOR_URI = "http://localhost:8085/generate/{qty}";
	private final String PREFIX_URI = "http://shorturl.com/";
	private final int SHORT_CODE_LENGTH = 8;
	private final String INVALID_URL = "Invalid Url";
	private final int SHORT_CODE_BUFFER_MIN_THREASHOLD = 3;
	private final int SHORT_CODE_BUFFER_LENGTH = 10;
	private final RestTemplate restTemplate = new RestTemplate();
	@Autowired
	private UrlMapRepository urlMapRepo;
	private final Queue<String> urlQueue = new LinkedList<>();

	public UrlResponse convertToShortUrl(String longUrl) {
		// TODO: verify longUrl
		String shortCode = getShortCode();
		UrlMap map = new UrlMap(shortCode, longUrl);
		urlMapRepo.save(map);
		String shortUrl = PREFIX_URI + shortCode;
		return new UrlResponse(shortUrl);
	}

	public UrlResponse convertToOriginalUrl(String shortUrl) {
		String longUrl;
		String shortCode = getCodeFromUrl(shortUrl);
		if (shortCode.equals(INVALID_URL)) {
			longUrl = INVALID_URL;
		} else {
			UrlMap retv = urlMapRepo.findById(shortCode).get();
			longUrl = (retv != null) ? retv.getOriginalUrl() : "No Data!";
		}
		return new UrlResponse(longUrl);
	}

	private String getShortCode() {
		if (urlQueue.size() < SHORT_CODE_BUFFER_MIN_THREASHOLD) {
			ShortUrlList rsp = restTemplate.getForObject(GENERATOR_URI, ShortUrlList.class, String.valueOf(SHORT_CODE_BUFFER_LENGTH));
			urlQueue.addAll(rsp.shortUrls());
		}
		return urlQueue.poll();
	}

	private String getCodeFromUrl(String shortUrl) {
		String[] temp = shortUrl.split("/");
		String shortCode = temp[temp.length - 1];
		if(shortCode == null || shortCode.length() != SHORT_CODE_LENGTH) {
			shortCode = INVALID_URL;
		}
		return shortCode;
	}
}
