package com.alex.j2se.socket;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLEncoderTest {

	public static void main(String[] args) {
		try {
			String encodeingUrl = URLEncoder.encode("http://www.ba.com/a?ab=12.2.2.2:80", "UTF-8");
			System.out.println("encodeingUrl: " + encodeingUrl);
			String decodingUrl = URLDecoder.decode(encodeingUrl, "UTF-8");
			System.out.println("decodingUrl: " + decodingUrl);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
