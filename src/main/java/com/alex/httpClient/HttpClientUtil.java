package com.alex.httpClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * HttpClient工具类<br>
 * 支持HttpClient 4.3以上版本
 * @author gao.jun 
 * @date 2015年9月10日
 *
 */
public class HttpClientUtil {
	
	private static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
	
	public static final String UTF_8 = "UTF-8";
    
    public static final String GBK = "GBK";
    
    /**
     * 连接超时时间默认为2秒
     */
    private static final int CONNECTION_TIMEOUT = 2000;
    
    /**
     * 连接池最大值
     */
    private static final int MAX_CONNETIONS = 1000;
    
    /**
     * 每个路由最大连接数
     */
    private static final int MAX_PER_ROUTE = 200;
	
    private static PoolingHttpClientConnectionManager cm;
    
    private static CloseableHttpClient httpClient;
    
    static {
    	cm = new PoolingHttpClientConnectionManager();
    	cm.setMaxTotal(MAX_CONNETIONS);
    	cm.setDefaultMaxPerRoute(MAX_PER_ROUTE);
    	httpClient = HttpClients.custom().setConnectionManager(cm).build();
    	
    	/**
    	 * 使用后台线程删除失效的连接和30秒以上没有使用的连接
    	 */
    	new Thread(new Runnable() {
			@Override
			public void run() {
				try {
		            while (true) {
		                synchronized (this) {
		                    wait(5000);
		                    // Close expired connections
		                    cm.closeExpiredConnections();
		                    // Optionally, close connections
		                    // that have been idle longer than 30 sec
		                    cm.closeIdleConnections(30, TimeUnit.SECONDS);
		                }
		            }
		        } catch (InterruptedException ex) {
		        	log.info("IdleConnectionMonitorThread");
		        }
			}
		});
    }
    
    public static void main(String[] args) {
		String filePathPrefix = "d:/data/domain_ca/www.2322.com/b.pem";
		File caFile = new File(filePathPrefix);
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("user", 138867);
			params.put("access", "www.2322.com");
			params.put("caName", "b.pem");
			params.put("caMd5", "ae27a4b4821b13cad2a17a75d219853e");
			String result = HttpClientUtil.postWithFile(
					"http://10.58.139.215:9000/control/user/cert/ca/add", 
//					"http://localhost:8090/hello/post",
					params, null, caFile, -1);
			log.info("result:{}", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Post请求<br>
     * url和消息体均按UTF-8进行编码
     * 2015年9月11日<br>
     * @author gao.jun
     * @param uri
     * @param urlParams url中的参数
     * @param headerMap header中的参数
     * @param entityBody 消息体中的参数
     * @param connectionTimeout 超时时间，如果为-1，则为默认时间CONNECTION_TIMEOUT，单位毫秒
     * @return response中的消息体，如果消息体为null，则返回null
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String post(String uri, Map<String, Object> urlParams, Map<String, Object> headerMap, String entityBody, 
    		int connectionTimeout) throws Exception {
    	if(urlParams != null && !urlParams.isEmpty()) {
    		uri = addParams(uri, urlParams, UTF_8);
    	}
    	HttpPost post = new HttpPost(uri);
    	if(StringUtils.isNotBlank(entityBody)) {
    		post.setEntity(new StringEntity(entityBody, UTF_8));
    	}
    	return request(post, urlParams, headerMap, connectionTimeout);
    }
    
    /**
     * 使用Post请求提交文件<br>
     * url和消息体均按UTF-8进行编码，文件会转化为String进行提交，所以文件必须较小<br>
     * 2015年11月10日<br>
     * @author gao.jun
     * @param uri
     * @param urlParams url中的参数
     * @param headerMap header中的参数
     * @param entityFile 消息体中的文件
     * @param connectionTimeout 超时时间，如果为-1，则为默认时间CONNECTION_TIMEOUT，单位毫秒
     * @return response中的消息体，如果消息体为null，则返回null
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String postWithFile(String uri, Map<String, Object> urlParams, Map<String, Object> headerMap, File entityFile, 
    		int connectionTimeout) throws Exception  {
    	if(urlParams != null && !urlParams.isEmpty()) {
    		uri = addParams(uri, urlParams, UTF_8);
    	}
    	HttpPost post = new HttpPost(uri);
    	if(entityFile != null) {
//    		FileBody file = new FileBody(entityFile);
//    		MultipartEntity reqEntity = new MultipartEntity();  
//            reqEntity.addPart("file", file);  
//    		post.setEntity(reqEntity);
    		post.setEntity(new StringEntity(IOUtils.toString(new FileInputStream(entityFile))));
    	}
    	return request(post, urlParams, headerMap, connectionTimeout);
    }
    
    /**
     * Get请求<br>
     * url和消息体均按UTF-8进行编码
     * 2015年9月11日<br>
     * @author gao.jun
     * @param uri
     * @param urlParams url中的参数
     * @param headerMap header中的参数
     * @param connectionTimeout 超时时间，如果为-1，则为默认时间CONNECTION_TIMEOUT，单位毫秒
     * @return response中的消息体，如果消息体为null，则返回null
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String get(String uri, Map<String, Object> urlParams, Map<String, Object> headerMap, 
    		int connectionTimeout) throws Exception {
    	if(urlParams != null && !urlParams.isEmpty()) {
    		uri = addParams(uri, urlParams, UTF_8);
    	}
    	HttpGet get = new HttpGet(uri);
    	
    	return request(get, urlParams, headerMap, connectionTimeout);
    }
    
    /**
     * Head请求<br>
     * url和消息体均按UTF-8进行编码
     * 2015年9月11日<br>
     * @author gao.jun
     * @param uri
     * @param urlParams url中的参数
     * @param headerMap header中的参数
     * @param connectionTimeout 超时时间，如果为-1，则为默认时间CONNECTION_TIMEOUT，单位毫秒
     * @return response中的消息体，如果消息体为null，则返回null
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String head(String uri, Map<String, Object> urlParams, Map<String, Object> headerMap, 
    		int connectionTimeout) throws Exception {
    	if(urlParams != null && !urlParams.isEmpty()) {
    		uri = addParams(uri, urlParams, UTF_8);
    	}
    	HttpHead head = new HttpHead(uri);
    	
    	return request(head, urlParams, headerMap, connectionTimeout);
    }
    
    /**
     * Delete请求<br>
     * url和消息体均按UTF-8进行编码
     * 2015年9月11日<br>
     * @author gao.jun
     * @param uri
     * @param urlParams url中的参数
     * @param headerMap header中的参数
     * @param entityBody 消息体中的参数
     * @param connectionTimeout 超时时间，如果为-1，则为默认时间CONNECTION_TIMEOUT，单位毫秒
     * @return response中的消息体，如果消息体为null，则返回null
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String delete(String uri, Map<String, Object> urlParams, Map<String, Object> headerMap, String entityBody, 
    		int connectionTimeout) throws Exception {
    	if(urlParams != null && !urlParams.isEmpty()) {
    		uri = addParams(uri, urlParams, UTF_8);
    	}
    	MyHttpDelete delete = new MyHttpDelete(uri); 
    	if(StringUtils.isNotBlank(entityBody)) {
    		delete.setEntity(new StringEntity(entityBody, UTF_8));
    	}
    	return request(delete, urlParams, headerMap, connectionTimeout);
    }
    
    private static String request(HttpRequestBase reqsust, Map<String, Object> urlParams, Map<String, Object> headerMap, 
    		int connectionTimeout) throws ClientProtocolException, IOException {
    	if(headerMap != null && !headerMap.isEmpty()) {
    		for(Map.Entry<String, Object> entry : headerMap.entrySet()) {
    			reqsust.addHeader(entry.getKey(), entry.getValue().toString());
    		}
    	}
    	
    	RequestConfig reqConf = RequestConfig.custom().setConnectTimeout(connectionTimeout == -1 ? connectionTimeout : CONNECTION_TIMEOUT).build();
    	reqsust.setConfig(reqConf);
    	long start = System.currentTimeMillis();
    	HttpResponse httpResponse = httpClient.execute(reqsust);
    	long cost = System.currentTimeMillis() - start;
    	HttpEntity respEntity = httpResponse.getEntity();
    	int statusCode = httpResponse.getStatusLine().getStatusCode();
    	if(respEntity == null) {
    		log.warn("Get response entity is null, url:{}, statusCode:{}, cost:{}", 
    				new Object[]{reqsust.getURI(), statusCode, cost});
    		return null;
    	}
    	String resultString = EntityUtils.toString(respEntity, UTF_8);
    	log.info("Get request, url:{}, statusCode:{}, Result:{}, cost:{}", 
    			new Object[]{reqsust.getURI(), statusCode, resultString, cost});
    	EntityUtils.consume(respEntity);
    	return resultString;
    }
    
    private static String addParams(String baseURI, Map<String, Object> params,
			String encode) {

		if (baseURI == null)
			return null;
		String paramStr = URLEncodedUtils.format(getParamList(params), encode);
		char sp = (baseURI.indexOf('?') != -1) ? '&' : '?';
		return baseURI + sp + paramStr;
	}

    private static List<NameValuePair> getParamList(Map<String, Object> params) {

		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			qparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
		}
		return qparams;
	}
}
