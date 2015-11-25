package com.alex.spring.mvc;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Controller
@RequestMapping("/hello")
public class HelloController {
	
	private static Logger log = LoggerFactory.getLogger(HelloController.class);
	
	private static Map<String, Object> map = new HashMap<String, Object>();
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public JSONObject get() {
		JSONObject obj = new JSONObject();
		obj.put("success", true);
		return obj;
	}
	
	@ResponseBody
	@RequestMapping(value= "/perform", method=RequestMethod.GET)
	public JSONObject performanceTest() {
		long start = System.currentTimeMillis();
		List<String> vals = new ArrayList<String>();
		for(int i = 0; i < 10000; ++i) {
			vals.add("value" + i);
		}
		for(int i = 0; i < 10000; ++i) {
			map.put(String.valueOf(map.size()) + i, vals);
		}
		JSONObject obj = new JSONObject();
		obj.put("success", true);
		log.info("size:{}, perform cost:{}", map.size(), System.currentTimeMillis() - start);
		return obj;
	}
	
	@ResponseBody
	@RequestMapping(value= "/post", method=RequestMethod.POST)
	public JSONObject uploadTest(MultipartHttpServletRequest request, HttpSession session) throws IOException {
		Iterator<String> itr = request.getFileNames();
		Assert.isTrue(itr != null, "上传文件不存在！");
		MultipartFile mpf = request.getFile(itr.next());
		Assert.isTrue(mpf != null, "上传文件不存在！");
		JSONObject jsonResult = new JSONObject();
		InputStream in = null;
		FileOutputStream fos = null;
		try {
			log.info("上传文件名称:{}，大小:{}", mpf.getOriginalFilename(), mpf.getBytes().length);
			in = mpf.getInputStream();
			String domainPathStr = "D:/data/upload_test/";
			File domainPath = new File(domainPathStr);
			if(!domainPath.exists()) {
				if(!domainPath.mkdirs()) {
					throw new RuntimeException("创建上传路径失败！");
				}
			}
			String filePath = domainPathStr.concat(mpf.getOriginalFilename());
			fos = new FileOutputStream(filePath);
			IOUtils.copy(in, fos);
			fos.flush();
			in.close();
			fos.close();
		}catch(IOException e) {
			log.error("upload");
		}finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(fos);
		}
		jsonResult.put("success", true);
		return jsonResult;
	}
}
