package com.alex.httpClient.test;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import com.alex.httpClient.HttpClientUtil;


public class HttpClientUtilTest extends TestCase{
	
	@Test
	public void testGet() throws Exception {
		String result = HttpClientUtil.get("http://www.baidu.com", null, null, -1);
		Assert.assertNotNull(result);
	}
}
