package com.shorturl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ShortLongUrlMap {
	@Id
	String shortCode;
	String originalUrl;
}
