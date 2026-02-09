package com.shorturl.service;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.InvalidUrlException;

import com.shorturl.dao.UrlMapRepository;
import com.shorturl.model.ShortUrlList;
import com.shorturl.model.UrlMap;
import com.shorturl.model.UrlResponse;

@Service
public class ShortUrlService {
	private final String GENERATOR_URI = "http://localhost:8085/generate/{qty}";
	@Value("${app.shorturl.prefix-uri}")
	public String PREFIX_URI;
	@Value("${app.shorturl.buffer-length}")
	private int SHORT_CODE_BUFFER_LENGTH;
	@Value("${app.shorturl.buffer-min-threshold}")
	private int SHORT_CODE_BUFFER_MIN_THREASHOLD;

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private UrlMapRepository urlMapRepo;
	@Autowired
	private UrlVerificationService urlVerificationService;
	private final Queue<String> urlQueue = new LinkedList<>();

	public UrlResponse convertToShortUrl(String longUrl) {
		UrlResponse retv = new UrlResponse(false, "");
		try {
			urlVerificationService.verifyUrl(longUrl);
		} catch (InvalidUrlException e) {
			return retv;
		}
		String shortCode = getShortCode();
		UrlMap map = new UrlMap(shortCode, longUrl);
		urlMapRepo.save(map);
		String shortUrl = PREFIX_URI + shortCode;
		retv = new UrlResponse(true, shortUrl);
		return retv;
	}

	public UrlResponse convertToOriginalUrl(String shortUrl) {
		UrlResponse retv = new UrlResponse(false, "");
		try {
			urlVerificationService.verifyUrl(shortUrl);
		} catch (InvalidUrlException e) {
			return retv;
		}
		String longUrl;
		String shortCode = getShortCodeFromUrl(shortUrl);
		try {
			Optional<UrlMap> longUrlMap = urlMapRepo.findById(shortCode);
			if (longUrlMap.isEmpty())
				return retv;
			longUrl = longUrlMap.get().getOriginalUrl();
		} catch (IllegalArgumentException e) {
			return retv;
		}

		retv = new UrlResponse(true, longUrl);
		return retv;
	}

	private String getShortCode() {
		if (urlQueue.size() < SHORT_CODE_BUFFER_MIN_THREASHOLD) {
			ShortUrlList rsp = restTemplate.getForObject(GENERATOR_URI, ShortUrlList.class, String.valueOf(SHORT_CODE_BUFFER_LENGTH));
			urlQueue.addAll(rsp.shortUrls());
		}
		return urlQueue.poll();
	}

	private String getShortCodeFromUrl(String shortUrl) {
		String[] temp = shortUrl.split("/");
		String shortCode = temp[temp.length - 1];
		return shortCode;
	}
}
