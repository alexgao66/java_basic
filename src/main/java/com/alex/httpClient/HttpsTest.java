package com.alex.httpClient;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Created by gaojun on 17/1/17.
 */
public class HttpsTest {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://openplatform-hotel.meituan.com/v1/pois/1");
        try {
            CloseableHttpResponse response = httpclient.execute(httpget);
            System.out.println(IOUtils.toString(response.getEntity().getContent()));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpclient.close();
            httpget.releaseConnection();
        }
    }
}
