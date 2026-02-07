package com.systemdesign.tinyurl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.systemdesign.tinyurl.service.TinyUrlService;

@SpringBootApplication
public class TinyUrlApplication {

	TinyUrlService service;
	public static void main(String[] args) {
		SpringApplication.run(TinyUrlApplication.class, args);
	}

	public void run(String...args) {
		String longUrl = "abc.com/xyz";
		System.out.println(service.getTinyUrl(longUrl));
	}
}
