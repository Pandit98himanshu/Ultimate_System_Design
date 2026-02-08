package com.shorturl.generator.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shorturl.generator.model.ShortUrlList;
import com.shorturl.generator.service.ShortUrlGeneratorService;


@Controller
public class ShortUrlGeneratorController {
	@Autowired
	ShortUrlGeneratorService shortUrlGeneratorService;

	@GetMapping("/generate/{qty}")
	public ResponseEntity<ShortUrlList> getShortUrls(@PathVariable(value="qty") String qty) {
		ShortUrlList urlList = null;
		try{
			urlList = shortUrlGeneratorService.generateShortUrls(Integer.parseInt(qty));
		} catch(NoSuchAlgorithmException | UnsupportedEncodingException e) {
			System.err.println(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(urlList);
	}
}
