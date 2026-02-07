package com.systemdesign.tinyurl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.systemdesign.tinyurl.service.TinyUrlService;

@RestController
@RequestMapping("/api")
public class TinyUrlController {
	@Autowired
	TinyUrlService tinyUrlService;

	@PostMapping(value = "/shorten", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String shorten(@RequestParam("longUrl") String longUrl) {
		String tinyUrl = tinyUrlService.getTinyUrl(longUrl);
		return tinyUrl;
	}
}
