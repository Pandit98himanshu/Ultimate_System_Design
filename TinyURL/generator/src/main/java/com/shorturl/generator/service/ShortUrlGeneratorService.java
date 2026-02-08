package com.shorturl.generator.service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.shorturl.generator.model.ShortUrlList;

@Service
public class ShortUrlGeneratorService {
	private final int SHORT_URL_LENGTH = 8;
	private MessageDigest md5;

	public ShortUrlList generateShortUrls(int qty) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		md5 = MessageDigest.getInstance("MD5");

		List<String> urls = new ArrayList<>();
		while (qty-- > 0) {
			String uuid = UUID.randomUUID().toString();
			md5.update(uuid.getBytes("UTF-8"));
			byte[] digest = md5.digest();
			String hashtext = new BigInteger(1, digest).toString(16);

			// System.out.println(hashtext);

			StringBuilder sb = new StringBuilder(hashtext);
			int i = (int) Math.floor(Math.random() % hashtext.length());
			while (sb.length() > SHORT_URL_LENGTH) {
				sb.deleteCharAt(i);
				i = (i + 5) % sb.length();
			}
			urls.add(sb.toString());
		}

		return new ShortUrlList(urls);
	}
}
