package com.shorturl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shorturl.model.UrlResponse;
import com.shorturl.service.ShortUrlService;


@RestController
public class ShortUrlController {
	@Autowired
	ShortUrlService shortUrlService;

	@PostMapping(value = "/shorten", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String getShortUrl(@RequestParam("longUrl") String longUrl) {
		UrlResponse rsp = shortUrlService.convertToShortUrl(longUrl);
		return rsp.url();
	}

	@PostMapping(value = "/expand", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String getLongUrl(@RequestParam("shortUrl") String shortUrl) {
		UrlResponse rsp = shortUrlService.convertToOriginalUrl(shortUrl);
		// return ResponseEntity.status(HttpStatus.CREATED).body(rsp.url());
		return rsp.url();
	}
}
