package com.shorturl.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.InvalidUrlException;

@Component
public class UrlVerificationService {
	@Value("${app.shorturl.prefix-uri}")
	public String PREFIX_URI;
	@Value("${app.shorturl.short-code-length}")
	private int SHORT_CODE_LENGTH;

	/*
		URL structure = protocol://domain/path
	 */
	public void verifyUrl(String url) throws InvalidUrlException {
		int protocolIndex = url.indexOf(':');
		String protocol = url.substring(0, protocolIndex);
		for (char c : protocol.toCharArray()) {
			if (c >= 'a' && c <= 'z') continue;
			throw new InvalidUrlException("The URL is invalid!");
		}
		url = url.substring(protocolIndex + 1);

		int domainIndex = url.indexOf('/');
		String domain = url.substring(0, domainIndex);
		String path = url.substring(domainIndex + 1);
		if (domain.equals(PREFIX_URI)) {
			if (path.length() != SHORT_CODE_LENGTH)
				throw new InvalidUrlException("The short URL is invalid!");
		}
	}
}
