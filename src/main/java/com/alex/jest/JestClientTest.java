package com.alex.jest;

import com.google.common.base.Stopwatch;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Get;

import java.io.IOException;

/**
 * Created by gaojun on 16/5/9.
 */
public class JestClientTest {

    private static JestClient client = ESFactory.getClient();

    public static void search() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Get get = new Get.Builder("megacorp", "3").build();
        try {
            JestResult rs = client.execute(get);
            System.out.println(rs.getJsonString());
            client.shutdownClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stopwatch.stop();
        System.out.println(stopwatch.toString());
    }

    public static void main(String[] args) {
        search();
    }

}
