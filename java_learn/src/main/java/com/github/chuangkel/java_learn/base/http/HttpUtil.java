package com.github.chuangkel.java_learn.base.http;

import com.alibaba.dubbo.common.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-12-04 10:43
 **/
public class HttpUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static  void postWithCookie(){
        CloseableHttpClient client = HttpClients.createDefault();
        URI uri = null;
        try {
            uri = new URI("http://192.168.39.44:8088/pms/hsfund.pms.tqm-yqb/1.0/listConvertRedeemInfo");
        } catch (URISyntaxException e) {
            logger.warn("传入的url字符串转换异常");
        }
        HttpPost httpPost = new HttpPost(uri);
        //body
        StringEntity body = new StringEntity("","UTF-8");
        body.setContentEncoding("UTF-8");
        body.setContentType("application/json");
        httpPost.setEntity(body);

        //HTTP上下文 token
        HttpClientContext httpContext = new HttpClientContext();
        //Cookie
        CookieStore cookieStore = new BasicCookieStore();
        Cookie cookie = new BasicClientCookie("","");
        Cookie cookie1 = new BasicClientCookie("","");
        cookieStore.addCookie(cookie);
        cookieStore.addCookie(cookie1);
        httpContext.setAttribute(HttpClientContext.COOKIE_STORE,cookieStore);

        httpContext.setUserToken("af9e0d08-82db-4de1-918e-4ae4b72e57df5");

        try {
            HttpResponse response = client.execute(httpPost,httpContext);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        HttpUtil.postWithCookie();
    }
}
