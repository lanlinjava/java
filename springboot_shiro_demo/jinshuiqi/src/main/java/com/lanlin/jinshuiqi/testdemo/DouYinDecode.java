package com.lanlin.jinshuiqi.testdemo;


import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;



    /**
     * tips:导入jsoup依赖
     * @author hedong
     * @time 2019年2月8日 02:14:39
     */

    public class DouYinDecode {
        /**
         * 特别注意，特别注意，特别注意
         JDK版本必须是1.8.0_192
         以前的方法也一样也一样可以使用
         */
        /**
         * jsoup依赖，自取
         <dependency>
         <groupId>org.jsoup</groupId>
         <artifactId>jsoup</artifactId>
         <version>1.11.3</version>
         </dependency>
         */


        public static void main(String[] args) throws Exception {
            // 需要解析的抖音地址
            String url2 = "#在抖音，记录美好生活#男人还是不要自着si！！！ http://v.douyin.com/2WLUod/  复制此链接，打开【抖音短视频】，直接观看视频！";
            String url = decodeHttpUrl(url2);
            Document doc = null;
            try {
                doc = Jsoup.connect(url).timeout(12138).get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Elements elem = doc.getElementsByTag("script");
            String url1 = elem.toString();
            int start = url1.indexOf("playAddr");
            url1 = url1.substring(start);
            int end = url1.indexOf("\",");
            String replaceAll = url1.substring(11, end).replaceAll("playwm", "play");
            String uri = originalUrl(replaceAll);
            if (StringUtils.isEmpty(uri)) {
                System.out.println("网址无效");
            } else {
                // 控制台打印解析地址
                System.out.println("网站地址:" + uri);
            }
        }

        public static String decodeHttpUrl(String url) {
            int start = url.indexOf("http");
            int end = url.lastIndexOf("/");
            String decodeurl = url.substring(start, end);
            return decodeurl;
        }

        public static String originalUrl(String url) {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpContext httpContext = new BasicHttpContext();
            HttpClientContext clientContext = HttpClientContext.adapt(httpContext);
            HttpGet httpGet = new HttpGet(url);
            try {
                httpClient.execute(httpGet, httpContext);
                return clientContext.getTargetHost() + ((HttpUriRequest) clientContext.getRequest()).getURI().toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

